package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class AutoWither extends Module {
  public Setting range = create("Range", "WitherRange", 5, 0, 6);
  
  public int head_slot;
  
  public int sand_slot;
  
  public boolean has_blocks() {
    (give up)null;
    byte b1 = 0;
    for (byte b2 = 0;; b2++) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b2);
      if (itemStack != ItemStack.field_190927_a && itemStack.func_77973_b() instanceof ItemBlock) {
        Block block = ((ItemBlock)itemStack.func_77973_b()).func_179223_d();
        if (block instanceof net.minecraft.block.BlockSoulSand) {
          this.sand_slot = b2;
          b1++;
          b2 = 0;
          while (true) {
            9;
            itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b2);
            if (itemStack.func_77973_b() == Items.field_151144_bL && itemStack.func_77952_i() == 1) {
              this.head_slot = b2;
              2;
              return false;
            } 
          } 
          break;
        } 
      } 
      continue;
    } 
  }
  
  public AutoWither() {
    super(Category.misc);
  }
  
  public void enable() {
    (give up)null;
    if (has_blocks()) {
      MessageUtil.send_client_message("This module isn't finished");
    } else {
      MessageUtil.send_client_message("You don't have materials");
    } 
  }
}
