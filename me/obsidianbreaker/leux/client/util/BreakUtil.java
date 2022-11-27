package me.obsidianbreaker.leux.client.util;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class BreakUtil {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static boolean is_mining;
  
  public static BlockPos current_block = null;
  
  public static boolean finished(IBlockState paramIBlockState) {
    (give up)null;
    return (paramIBlockState.func_177230_c() == Blocks.field_150357_h || paramIBlockState.func_177230_c() == Blocks.field_150350_a || paramIBlockState.func_177230_c() instanceof net.minecraft.block.BlockLiquid);
  }
  
  static {
    is_mining = false;
  }
  
  public static boolean breakblock(int paramInt) {
    (give up)null;
    if (current_block == null)
      return false; 
    IBlockState iBlockState = mc.field_71441_e.func_180495_p(current_block);
    if (finished(iBlockState) || mc.field_71439_g.func_174818_b(current_block) > (paramInt * paramInt)) {
      Leux.get_module_manager().get_module_with_tag("AutoObiBreaker").set_disable();
      current_block = null;
      return false;
    } 
    mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
    EnumFacing enumFacing = EnumFacing.UP;
    if (!is_mining) {
      is_mining = true;
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, current_block, enumFacing));
    } else {
      mc.field_71442_b.func_180512_c(current_block, enumFacing);
    } 
    return true;
  }
  
  public static void setblock(BlockPos paramBlockPos) {
    (give up)null;
    current_block = paramBlockPos;
  }
}
