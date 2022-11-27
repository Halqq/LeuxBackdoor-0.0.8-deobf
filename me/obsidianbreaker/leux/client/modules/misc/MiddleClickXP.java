package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class MiddleClickXP extends Module {
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null || mc.field_71441_e == null)
      return; 
    int i = InventoryUtil.getHotbarItemSlot(Items.field_151062_by);
    if (i != -1 && Mouse.isButtonDown(2)) {
      InventoryUtil.switchToSlot(i);
      mc.field_71442_b.func_187101_a((EntityPlayer)mc.field_71439_g, (World)mc.field_71441_e, EnumHand.MAIN_HAND);
    } 
  }
  
  public MiddleClickXP() {
    super(Category.misc);
  }
}
