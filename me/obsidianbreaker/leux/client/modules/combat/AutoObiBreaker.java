package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.stream.Collectors;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BreakUtil;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class AutoObiBreaker extends Module {
  public Setting swap = create("Swap to Pick", "MineSwap", true);
  
  public BlockPos target_block = null;
  
  public Setting range = create("Range", "MineRange", 5, 1, 6);
  
  public void enable() {
    (give up)null;
    for (EntityPlayer entityPlayer : mc.field_71441_e.field_73010_i.stream().filter(AutoObiBreaker::lambda$enable$0).collect(Collectors.toList())) {
      if (mc.field_71439_g.func_70032_d((Entity)entityPlayer) > this.range.get_value(1) || mc.field_71439_g == entityPlayer)
        continue; 
      BlockPos blockPos = EntityUtil.is_cityable(entityPlayer, true);
      this.target_block = blockPos;
    } 
    if (this.target_block == null) {
      MessageUtil.send_client_message("Can't find block");
      set_disable();
    } 
    int i = findPickaxe();
    if (this.swap.get_value(true) && i != -1)
      mc.field_71439_g.field_71071_by.field_70461_c = i; 
    BreakUtil.setblock(this.target_block);
  }
  
  public void update() {
    (give up)null;
    BreakUtil.breakblock(this.range.get_value(1));
    if (Leux.get_module_manager().get_module_with_tag("PacketMine").is_active())
      set_disable(); 
  }
  
  public AutoObiBreaker() {
    super(Category.combat);
  }
  
  public static boolean lambda$enable$0(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return !FriendUtil.isFriend(paramEntityPlayer.func_70005_c_());
  }
  
  public void disable() {
    (give up)null;
    BreakUtil.setblock(null);
    this.target_block = null;
  }
  
  public int findPickaxe() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      if (mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b() instanceof net.minecraft.item.ItemPickaxe)
        return b; 
    } 
  }
}
