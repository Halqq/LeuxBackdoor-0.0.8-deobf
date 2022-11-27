package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InventoryXCarryPreview extends Pinnable {
  public void render() {
    (give up)null;
    if (this.mc.field_71439_g != null) {
      GlStateManager.func_179094_E();
      RenderHelper.func_74520_c();
      create_rect(0, 0, get_width(), get_height(), 0, 0, 0, 60);
      set_width(32);
      set_height(32);
      byte b = 1;
      while (true) {
        5;
        ItemStack itemStack = ((Slot)this.mc.field_71439_g.field_71069_bz.field_75151_b.get(b)).func_75211_c();
        int i = get_x();
        int j = get_y();
        true;
        i += 0;
        j += 0;
        2;
        3;
        4;
        this.mc.func_175599_af().func_180450_b(itemStack, i, j);
        this.mc.func_175599_af().func_180453_a(this.mc.field_71466_p, itemStack, i, j, null);
      } 
    } 
  }
  
  public InventoryXCarryPreview() {
    super("Inventory XCarry", "InventoryXPreview", Float.intBitsToFloat(1.10488742E9F ^ 0x7E5B3EBB), 0, 0);
  }
}
