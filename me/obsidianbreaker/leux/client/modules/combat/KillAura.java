package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.stream.Collectors;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class KillAura extends Module {
  public Setting range = create("Range", "KillAuraRange", 5.0D, 0.5D, 6.0D);
  
  public Setting player = create("Player", "KillAuraPlayer", true);
  
  public Setting delay = create("Delay", "KillAuraDelay", 2, 0, 10);
  
  public double tick = 0.0D;
  
  public EnumHand actual_hand = EnumHand.MAIN_HAND;
  
  public Setting hostile = create("Hostile", "KillAuraHostile", true);
  
  public Setting sword = create("Sword", "KillAuraSword", true);
  
  public boolean start_verify = true;
  
  public Setting sync_tps = create("Sync TPS", "KillAuraSyncTps", true);
  
  public Setting mode = create("Mode", "KillAuraMode", "Normal", combobox(new String[] { "32k", "Normal" }));
  
  public boolean is_compatible(Entity paramEntity) {
    (give up)null;
    if (this.player.get_value(true) && paramEntity instanceof EntityPlayer && paramEntity != mc.field_71439_g && !paramEntity.func_70005_c_().equals(mc.field_71439_g.func_70005_c_()))
      return true; 
    if (this.hostile.get_value(true) && paramEntity instanceof net.minecraft.entity.monster.IMob)
      return true; 
    if (paramEntity instanceof EntityLivingBase) {
      EntityLivingBase entityLivingBase = (EntityLivingBase)paramEntity;
      if (entityLivingBase.func_110143_aJ() <= 0.0F)
        return false; 
    } 
    return false;
  }
  
  public boolean checkSharpness(ItemStack paramItemStack) {
    (give up)null;
    if (paramItemStack.func_77978_p() == null)
      return false; 
    NBTTagList nBTTagList = (NBTTagList)paramItemStack.func_77978_p().func_74781_a("ench");
    return false;
  }
  
  public Entity find_entity() {
    (give up)null;
    Entity entity = null;
    for (Entity entity1 : mc.field_71441_e.field_73010_i.stream().filter(KillAura::lambda$find_entity$0).collect(Collectors.toList())) {
      if (is_compatible(entity1) && mc.field_71439_g.func_70032_d(entity1) <= this.range.get_value(1.0D))
        entity = entity1; 
    } 
    return entity;
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g != null && mc.field_71441_e != null) {
      this.tick++;
      if ((mc.field_71439_g.field_70128_L | ((mc.field_71439_g.func_110143_aJ() <= 0.0F) ? 1 : 0)) != 0)
        return; 
      if (this.mode.in("Normal")) {
        if (!(mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword) && this.sword.get_value(true)) {
          this.start_verify = false;
        } else if (mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword && this.sword.get_value(true)) {
          this.start_verify = true;
        } else if (!this.sword.get_value(true)) {
          this.start_verify = true;
        } 
        Entity entity = find_entity();
        if (this.start_verify) {
          float f = 20.0F - Leux.get_event_handler().get_tick_rate();
          boolean bool = (mc.field_71439_g.func_184825_o(this.sync_tps.get_value(true) ? -f : 0.0F) >= 1.0F) ? true : false;
        } 
      } else {
        if (!(mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword))
          return; 
        if (this.tick < this.delay.get_value(1))
          return; 
        this.tick = 0.0D;
        Entity entity = find_entity();
        attack_entity(entity);
      } 
    } 
  }
  
  public String array_detail() {
    (give up)null;
    return this.mode.get_current_value();
  }
  
  public void attack_entity(Entity paramEntity) {
    (give up)null;
    if (this.mode.in("32k")) {
      byte b = -1;
      byte b1 = 0;
      while (true) {
        9;
        ItemStack itemStack1 = mc.field_71439_g.field_71071_by.func_70301_a(b1);
        if (itemStack1 != ItemStack.field_190927_a && checkSharpness(itemStack1)) {
          b = b1;
          -1;
          break;
        } 
      } 
    } 
    ItemStack itemStack = mc.field_71439_g.func_184592_cb();
    if (itemStack.func_77973_b() == Items.field_185159_cQ)
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, mc.field_71439_g.func_174811_aO())); 
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(paramEntity));
    mc.field_71439_g.func_184609_a(this.actual_hand);
    mc.field_71439_g.func_184821_cY();
  }
  
  public void enable() {
    (give up)null;
    this.tick = 0.0D;
  }
  
  public static boolean lambda$find_entity$0(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return !FriendUtil.isFriend(paramEntityPlayer.func_70005_c_());
  }
  
  public KillAura() {
    super(Category.combat);
  }
}
