package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class SurroundBlocks extends Pinnable {
  public Block get_neg_x() {
    (give up)null;
    BlockPos blockPos = PlayerUtil.GetLocalPlayerPosFloored();
    return this.mc.field_71441_e.func_180495_p(blockPos.func_177976_e()).func_177230_c();
  }
  
  public Block get_pos_x() {
    (give up)null;
    BlockPos blockPos = PlayerUtil.GetLocalPlayerPosFloored();
    return this.mc.field_71441_e.func_180495_p(blockPos.func_177974_f()).func_177230_c();
  }
  
  public void render() {
    (give up)null;
    GlStateManager.func_179094_E();
    RenderHelper.func_74520_c();
    Block block1 = get_neg_x();
    Block block2 = get_pos_x();
    Block block3 = get_neg_z();
    Block block4 = get_pos_z();
    switch (SurroundBlocks$1.$SwitchMap$me$obsidianbreaker$leux$client$util$PlayerUtil$FacingDirection[PlayerUtil.GetFacing().ordinal()]) {
      case 1:
        block1 = get_neg_x();
        block2 = get_pos_x();
        block3 = get_neg_z();
        block4 = get_pos_z();
        break;
      case 2:
        block1 = get_neg_z();
        block2 = get_pos_z();
        block3 = get_pos_x();
        block4 = get_neg_x();
        break;
      case 3:
        block1 = get_pos_x();
        block2 = get_neg_x();
        block3 = get_pos_z();
        block4 = get_neg_z();
        break;
      case 4:
        block1 = get_pos_z();
        block2 = get_neg_z();
        block3 = get_neg_x();
        block4 = get_pos_x();
        break;
    } 
    this.mc.func_175599_af().func_180450_b(new ItemStack(block1), get_x() - 20, get_y());
    this.mc.func_175599_af().func_180450_b(new ItemStack(block2), get_x() + 20, get_y());
    this.mc.func_175599_af().func_180450_b(new ItemStack(block3), get_x(), get_y() - 20);
    this.mc.func_175599_af().func_180450_b(new ItemStack(block4), get_x(), get_y() + 20);
    RenderHelper.func_74518_a();
    GlStateManager.func_179121_F();
    set_width(50);
    set_height(25);
  }
  
  public SurroundBlocks() {
    super("Surround Blocks", "SurroundBlocks", Float.intBitsToFloat(1.08257075E9F ^ 0x7F06B817), 0, 0);
  }
  
  public Block get_neg_z() {
    (give up)null;
    BlockPos blockPos = PlayerUtil.GetLocalPlayerPosFloored();
    return this.mc.field_71441_e.func_180495_p(blockPos.func_177978_c()).func_177230_c();
  }
  
  public Block get_pos_z() {
    (give up)null;
    BlockPos blockPos = PlayerUtil.GetLocalPlayerPosFloored();
    return this.mc.field_71441_e.func_180495_p(blockPos.func_177968_d()).func_177230_c();
  }
}
