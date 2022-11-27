package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

public class InventoryPreview extends Pinnable {
  public void render() {
    (give up)null;
    if (this.mc.field_71439_g != null) {
      GlStateManager.func_179094_E();
      RenderHelper.func_74520_c();
      create_rect(0, 0, get_width(), get_height(), 0, 0, 0, 60);
      set_width(144);
      set_height(48);
      byte b = 0;
      while (true) {
        27;
        ItemStack itemStack = (ItemStack)this.mc.field_71439_g.field_71071_by.field_70462_a.get(b + 9);
        int i = get_x() + b % 9 * 16;
        int j = get_y() + b / 9 * 16;
        this.mc.func_175599_af().func_180450_b(itemStack, i, j);
        this.mc.func_175599_af().func_180453_a(this.mc.field_71466_p, itemStack, i, j, null);
      } 
    } 
  }
  
  public InventoryPreview() {
    super("Inventory Preview", "InventoryPreview", Float.intBitsToFloat(1.08935859E9F ^ 0x7F6E4B1F), 0, 0);
  }
}
