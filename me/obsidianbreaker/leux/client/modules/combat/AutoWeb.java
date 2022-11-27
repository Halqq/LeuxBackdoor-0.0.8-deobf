package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.obsidianbreaker.leux.client.util.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class AutoWeb extends Module {
  public boolean sneak = false;
  
  public Setting rotate = create("Rotate", "AutoWebRotate", true);
  
  public Setting range = create("Enemy Range", "AutoWebRange", 4, 0, 8);
  
  public Setting always_on = create("Always On", "AlwaysOn", true);
  
  public int new_slot = -1;
  
  public void update() {
    (give up)null;
    int i = mc.field_71439_g.field_71071_by.field_70461_c;
    if (mc.field_71439_g == null)
      return; 
    if (this.always_on.get_value(true)) {
      EntityPlayer entityPlayer = find_closest_target();
      return;
    } 
    int j = mc.field_71439_g.field_71071_by.field_70461_c;
    mc.field_71439_g.field_71071_by.field_70461_c = this.new_slot;
    mc.field_71442_b.func_78765_e();
    place_blocks(PlayerUtil.GetLocalPlayerPosFloored());
    mc.field_71439_g.field_71071_by.field_70461_c = j;
    set_disable();
    mc.field_71439_g.field_71071_by.field_70461_c = i;
  }
  
  public void place_blocks(BlockPos paramBlockPos) {
    (give up)null;
    if (!mc.field_71441_e.func_180495_p(paramBlockPos).func_185904_a().func_76222_j())
      return; 
    if (!BlockInteractHelper.checkForNeighbours(paramBlockPos))
      return; 
    EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
    int i = arrayOfEnumFacing.length;
    byte b = 0;
    while (b < i) {
      EnumFacing enumFacing1 = arrayOfEnumFacing[b];
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing1);
      EnumFacing enumFacing2 = enumFacing1.func_176734_d();
      if (!BlockInteractHelper.canBeClicked(blockPos)) {
        b++;
        continue;
      } 
      if (BlockInteractHelper.blackList.contains(mc.field_71441_e.func_180495_p(blockPos).func_177230_c())) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
        this.sneak = true;
      } 
      Vec3d vec3d = (new Vec3d((Vec3i)blockPos)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(enumFacing2.func_176730_m())).func_186678_a(0.5D));
      if (this.rotate.get_value(true))
        BlockInteractHelper.faceVectorPacketInstant(vec3d); 
      mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, blockPos, enumFacing2, vec3d, EnumHand.MAIN_HAND);
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
      return;
    } 
  }
  
  public EntityPlayer find_closest_target() {
    EntityPlayer entityPlayer;
    (give up)null;
    if (mc.field_71441_e.field_73010_i.isEmpty())
      return null; 
    Entity entity = null;
    for (EntityPlayer entityPlayer1 : mc.field_71441_e.field_73010_i) {
      if (entityPlayer1 == mc.field_71439_g || FriendUtil.isFriend(entityPlayer1.func_70005_c_()) || !EntityUtil.isLiving((Entity)entityPlayer1) || entityPlayer1.func_110143_aJ() <= 0.0F)
        continue; 
      if (mc.field_71439_g.func_70032_d((Entity)entityPlayer1) > mc.field_71439_g.func_70032_d(entity))
        continue; 
      entityPlayer = entityPlayer1;
    } 
    return entityPlayer;
  }
  
  public int find_in_hotbar() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack.func_77973_b() == Item.func_150899_d(30))
        return b; 
    } 
  }
  
  public void disable() {
    (give up)null;
    if (mc.field_71439_g != null && this.sneak) {
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
      this.sneak = false;
    } 
  }
  
  public void enable() {
    (give up)null;
    if (mc.field_71439_g != null) {
      this.new_slot = find_in_hotbar();
      if (this.new_slot == -1) {
        MessageUtil.send_client_error_message("cannot find webs in hotbar");
        set_active(false);
      } 
    } 
  }
  
  public AutoWeb() {
    super(Category.combat);
  }
  
  public boolean is_surround() {
    (give up)null;
    BlockPos blockPos = PlayerUtil.GetLocalPlayerPosFloored();
    return (mc.field_71441_e.func_180495_p(blockPos.func_177974_f()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(blockPos.func_177976_e()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(blockPos.func_177978_c()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(blockPos.func_177968_d()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150350_a);
  }
}
