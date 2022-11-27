package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiScreen.class})
public class MixinGuiScreen {
  RenderItem itemRender = Minecraft.func_71410_x().func_175599_af();
  
  FontRenderer fontRenderer = (Minecraft.func_71410_x()).field_71466_p;
  
  @Inject(method = {"drawWorldBackground"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"drawWorldBackground"}, at = {@At("HEAD")}, cancellable = true)
  public void drawWorldBackground(int paramInt, CallbackInfo paramCallbackInfo) {
    if ((Minecraft.func_71410_x()).field_71439_g != null)
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"renderToolTip"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"renderToolTip"}, at = {@At("HEAD")}, cancellable = true)
  public void renderToolTip(ItemStack paramItemStack, int paramInt1, int paramInt2, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("ShulkerPreview").is_active() && paramItemStack.func_77973_b() instanceof net.minecraft.item.ItemShulkerBox) {
      NBTTagCompound nBTTagCompound = paramItemStack.func_77978_p();
      if (nBTTagCompound.func_150297_b("BlockEntityTag", 10)) {
        NBTTagCompound nBTTagCompound1 = nBTTagCompound.func_74775_l("BlockEntityTag");
        if (nBTTagCompound1.func_150297_b("Items", 9)) {
          paramCallbackInfo.cancel();
          NonNullList nonNullList = NonNullList.func_191197_a(27, ItemStack.field_190927_a);
          ItemStackHelper.func_191283_b(nBTTagCompound1, nonNullList);
          GlStateManager.func_179147_l();
          GlStateManager.func_179101_C();
          RenderHelper.func_74518_a();
          GlStateManager.func_179140_f();
          GlStateManager.func_179097_i();
          int i = Math.max(144, this.fontRenderer.func_78256_a(paramItemStack.func_82833_r()) + 3);
          int j = paramInt1 + 12;
          int k = paramInt2 - 12;
          byte b1 = 57;
          this.itemRender.field_77023_b = 300.0F;
          drawGradientRectP(j - 3, k - 4, j + i + 3, k - 3, -267386864, -267386864);
          drawGradientRectP(j - 3, k + b1 + 3, j + i + 3, k + b1 + 4, -267386864, -267386864);
          drawGradientRectP(j - 3, k - 3, j + i + 3, k + b1 + 3, -267386864, -267386864);
          drawGradientRectP(j - 4, k - 3, j - 3, k + b1 + 3, -267386864, -267386864);
          drawGradientRectP(j + i + 3, k - 3, j + i + 4, k + b1 + 3, -267386864, -267386864);
          drawGradientRectP(j - 3, k - 3 + 1, j - 3 + 1, k + b1 + 3 - 1, 1347420415, 1344798847);
          drawGradientRectP(j + i + 2, k - 3 + 1, j + i + 3, k + b1 + 3 - 1, 1347420415, 1344798847);
          drawGradientRectP(j - 3, k - 3, j + i + 3, k - 3 + 1, 1347420415, 1347420415);
          drawGradientRectP(j - 3, k + b1 + 2, j + i + 3, k + b1 + 3, 1344798847, 1344798847);
          this.fontRenderer.func_78276_b(paramItemStack.func_82833_r(), paramInt1 + 12, paramInt2 - 12, 16777215);
          GlStateManager.func_179147_l();
          GlStateManager.func_179141_d();
          GlStateManager.func_179098_w();
          GlStateManager.func_179145_e();
          GlStateManager.func_179126_j();
          RenderHelper.func_74520_c();
          for (byte b2 = 0; b2 < nonNullList.size(); b2++) {
            int m = paramInt1 + b2 % 9 * 16 + 11;
            int n = paramInt2 + b2 / 9 * 16 - 11 + 8;
            ItemStack itemStack = (ItemStack)nonNullList.get(b2);
            this.itemRender.func_180450_b(itemStack, m, n);
            this.itemRender.func_180453_a(this.fontRenderer, itemStack, m, n, null);
          } 
          RenderHelper.func_74518_a();
          this.itemRender.field_77023_b = 0.0F;
          GlStateManager.func_179145_e();
          GlStateManager.func_179126_j();
          RenderHelper.func_74519_b();
          GlStateManager.func_179091_B();
        } 
      } 
    } 
  }
  
  private void drawGradientRectP(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    float f1 = (paramInt5 >> 24 & 0xFF) / 255.0F;
    float f2 = (paramInt5 >> 16 & 0xFF) / 255.0F;
    float f3 = (paramInt5 >> 8 & 0xFF) / 255.0F;
    float f4 = (paramInt5 & 0xFF) / 255.0F;
    float f5 = (paramInt6 >> 24 & 0xFF) / 255.0F;
    float f6 = (paramInt6 >> 16 & 0xFF) / 255.0F;
    float f7 = (paramInt6 >> 8 & 0xFF) / 255.0F;
    float f8 = (paramInt6 & 0xFF) / 255.0F;
    GlStateManager.func_179090_x();
    GlStateManager.func_179147_l();
    GlStateManager.func_179118_c();
    GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.func_179103_j(7425);
    Tessellator tessellator = Tessellator.func_178181_a();
    BufferBuilder bufferBuilder = tessellator.func_178180_c();
    bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
    bufferBuilder.func_181662_b(paramInt3, paramInt2, 300.0D).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramInt1, paramInt2, 300.0D).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramInt1, paramInt4, 300.0D).func_181666_a(f6, f7, f8, f5).func_181675_d();
    bufferBuilder.func_181662_b(paramInt3, paramInt4, 300.0D).func_181666_a(f6, f7, f8, f5).func_181675_d();
    tessellator.func_78381_a();
    GlStateManager.func_179103_j(7424);
    GlStateManager.func_179084_k();
    GlStateManager.func_179141_d();
    GlStateManager.func_179098_w();
  }
}
