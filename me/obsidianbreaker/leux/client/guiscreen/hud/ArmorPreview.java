package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class ArmorPreview extends Pinnable {
  public RenderItem itemRender = this.mc.func_175599_af();
  
  public ArmorPreview() {
    super("Armor Preview", "ArmorPreview", Float.intBitsToFloat(1.09241024E9F ^ 0x7E9CDB5F), 0, 0);
  }
  
  public void render() {
    (give up)null;
    if (this.mc.field_71439_g != null && is_on_gui())
      create_rect(0, 0, get_width(), get_height(), 0, 0, 0, 50); 
    GlStateManager.func_179098_w();
    ScaledResolution scaledResolution = new ScaledResolution(this.mc);
    int i = scaledResolution.func_78326_a() / 2;
    int j = scaledResolution.func_78328_b() - 55 - (this.mc.field_71439_g.func_70090_H() ? 10 : 0);
    byte b = 0;
    for (ItemStack itemStack : this.mc.field_71439_g.field_71071_by.field_70460_b) {
      b++;
      if (itemStack.func_190926_b())
        continue; 
      int k = i - 90 + (9 - b) * 20 + 2;
      GlStateManager.func_179126_j();
      this.itemRender.field_77023_b = Float.intBitsToFloat(1.00844634E9F ^ 0x7F53AB94);
      this.itemRender.func_180450_b(itemStack, k, j);
      this.itemRender.func_180453_a(this.mc.field_71466_p, itemStack, k, j, "");
      this.itemRender.field_77023_b = Float.intBitsToFloat(2.13462259E9F ^ 0x7F3BC179);
      GlStateManager.func_179098_w();
      GlStateManager.func_179140_f();
      GlStateManager.func_179097_i();
      String str = (itemStack.func_190916_E() > 1) ? (itemStack.func_190916_E() + "") : "";
      this.mc.field_71466_p.func_175063_a(str, (k + 19 - 2 - this.mc.field_71466_p.func_78256_a(str)), (j + 9), 16777215);
      float f1 = (itemStack.func_77958_k() - itemStack.func_77952_i()) / itemStack.func_77958_k();
      float f2 = Float.intBitsToFloat(1.10390976E9F ^ 0x7E4C53BF) - f1;
      int m = 100 - (int)(f2 * Float.intBitsToFloat(1.03860826E9F ^ 0x7F2FE771));
      if (m >= 100) {
        this.mc.field_71466_p.func_175063_a(m + "", (k + 8 - this.mc.field_71466_p.func_78256_a(m + "") / 2), (j - 11), toHex((int)(f2 * Float.intBitsToFloat(1.00850374E9F ^ 0x7F638BD6)), (int)(f1 * Float.intBitsToFloat(1.01258208E9F ^ 0x7F25C6DE)), 0));
        continue;
      } 
      this.mc.field_71466_p.func_175063_a("0%", (k + 8 - this.mc.field_71466_p.func_78256_a("0") / 2), (j - 11), toHex((int)(f2 * Float.intBitsToFloat(1.00738483E9F ^ 0x7F7478E9)), (int)(f1 * Float.intBitsToFloat(1.01111776E9F ^ 0x7F3B6EB6)), 0));
    } 
    GlStateManager.func_179126_j();
    GlStateManager.func_179140_f();
    set_width(50);
    set_height(25);
  }
  
  public static int toHex(int paramInt1, int paramInt2, int paramInt3) {
    paramInt3 = 0;
    (give up)null;
    return 0xFF000000 | (paramInt1 & 0xFF) << 16 | (paramInt2 & 0xFF) << 8 | paramInt3 & 0xFF;
  }
}
