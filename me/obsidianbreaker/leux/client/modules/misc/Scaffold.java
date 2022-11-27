package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.obsidianbreaker.leux.client.util.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Scaffold extends Module {
  public Setting height = create("Height", "Height", 1.0D, 0.0D, 2.0D);
  
  public Setting tower = create("Tower", "Tower", true);
  
  public Setting ticks = create("Ticks", "Ticks", 0, 0, 60);
  
  public Scaffold() {
    super(Category.movement);
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (mc.field_71474_y.field_74314_A.func_151470_d() && this.tower.get_value(true))
      mc.field_71439_g.field_70181_x = this.height.get_value(1.0D); 
    Vec3d vec3d = EntityUtil.getInterpolatedPos((Entity)mc.field_71439_g, this.ticks.get_value(1));
    BlockPos blockPos1 = (new BlockPos(vec3d)).func_177977_b();
    BlockPos blockPos2 = blockPos1.func_177977_b();
    if (!Wrapper.getWorld().func_180495_p(blockPos1).func_185904_a().func_76222_j())
      return; 
    byte b = -1;
    byte b1 = 0;
    while (true) {
      9;
      ItemStack itemStack = (Wrapper.getPlayer()).field_71071_by.func_70301_a(b1);
      Block block;
      if (itemStack == ItemStack.field_190927_a || !(itemStack.func_77973_b() instanceof ItemBlock) || BlockInteractHelper.blackList.contains(block = ((ItemBlock)itemStack.func_77973_b()).func_179223_d()) || block instanceof net.minecraft.block.BlockContainer || !Block.func_149634_a(itemStack.func_77973_b()).func_176223_P().func_185913_b() || (((ItemBlock)itemStack.func_77973_b()).func_179223_d() instanceof net.minecraft.block.BlockFalling && Wrapper.getWorld().func_180495_p(blockPos2).func_185904_a().func_76222_j()))
        continue; 
      b = b1;
      -1;
      return;
    } 
  }
}
