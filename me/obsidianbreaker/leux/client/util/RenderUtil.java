package me.obsidianbreaker.leux.client.util;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
  public static ICamera camera;
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static RenderItem itemRender = mc.func_175599_af();
  
  public static void GLPre(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, float paramFloat) {
    (give up)null;
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glLineWidth(paramFloat);
    GL11.glDisable(3553);
    GL11.glDisable(2929);
    GL11.glEnable(2848);
    GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    GL11.glHint(3154, 4354);
    GlStateManager.func_179132_a(false);
  }
  
  public static void drawArcOutline(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, int paramInt) {
    (give up)null;
    GL11.glBegin(2);
    for (int i = (int)(paramInt / 360.0F / paramFloat4) + 1; i <= paramInt / 360.0F / paramFloat5; i++) {
      double d = 6.283185307179586D * i / paramInt;
      GL11.glVertex2d(paramFloat1 + Math.cos(d) * paramFloat3, paramFloat2 + Math.sin(d) * paramFloat3);
    } 
    glEnd();
  }
  
  public static void glEnd() {
    (give up)null;
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glPopMatrix();
    GL11.glEnable(2929);
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glDisable(2848);
  }
  
  public static void glSetup() {
    (give up)null;
    GlStateManager.func_179094_E();
    GlStateManager.func_179147_l();
    GlStateManager.func_179097_i();
    GlStateManager.func_179120_a(770, 771, 0, 1);
    GlStateManager.func_179090_x();
    GlStateManager.func_179132_a(false);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    GL11.glLineWidth(1.5F);
  }
  
  public static void glrendermethod() {
    (give up)null;
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    GL11.glLineWidth(2.0F);
    GL11.glDisable(3553);
    GL11.glEnable(2884);
    GL11.glDisable(2929);
    double d1 = (mc.func_175598_ae()).field_78730_l;
    double d2 = (mc.func_175598_ae()).field_78731_m;
    double d3 = (mc.func_175598_ae()).field_78728_n;
    GL11.glPushMatrix();
    GL11.glTranslated(-d1, -d2, -d3);
  }
  
  public static void drawBoundingBox(AxisAlignedBB paramAxisAlignedBB, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
    (give up)null;
    GlStateManager.func_179094_E();
    GlStateManager.func_179147_l();
    GlStateManager.func_179097_i();
    GlStateManager.func_179120_a(770, 771, 0, 1);
    GlStateManager.func_179090_x();
    GlStateManager.func_179132_a(false);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    GL11.glLineWidth(paramFloat1);
    Tessellator tessellator = Tessellator.func_178181_a();
    BufferBuilder bufferBuilder = tessellator.func_178180_c();
    bufferBuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, Float.intBitsToFloat(2.13669286E9F ^ 0x7F5B5898)).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, Float.intBitsToFloat(2.12401229E9F ^ 0x7E99DB21)).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, Float.intBitsToFloat(2.13685107E9F ^ 0x7F5DC2B8)).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, Float.intBitsToFloat(2.12431104E9F ^ 0x7E9E6A3D)).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, paramFloat5).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(paramFloat2, paramFloat3, paramFloat4, Float.intBitsToFloat(2.13069478E9F ^ 0x7EFFD2A7)).func_181675_d();
    tessellator.func_78381_a();
    GL11.glDisable(2848);
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179126_j();
    GlStateManager.func_179098_w();
    GlStateManager.func_179084_k();
    GlStateManager.func_179121_F();
  }
  
  public static void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt) {
    paramInt = 1426063360;
    (give up)null;
    float f1 = (paramInt >> 24 & 0xFF) / Float.intBitsToFloat(1.01010861E9F ^ 0x7F4A08C4);
    float f2 = (paramInt >> 16 & 0xFF) / Float.intBitsToFloat(1.0134672E9F ^ 0x7F174828);
    float f3 = (paramInt >> 8 & 0xFF) / Float.intBitsToFloat(1.00774829E9F ^ 0x7F6E04B4);
    float f4 = (paramInt & 0xFF) / Float.intBitsToFloat(1.03476646E9F ^ 0x7ED2486D);
    Tessellator tessellator = Tessellator.func_178181_a();
    BufferBuilder bufferBuilder = tessellator.func_178180_c();
    GlStateManager.func_179147_l();
    GlStateManager.func_179090_x();
    GlStateManager.func_179120_a(770, 771, 1, 0);
    bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
    bufferBuilder.func_181662_b(paramFloat1, paramFloat4, 0.0D).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramFloat3, paramFloat4, 0.0D).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramFloat3, paramFloat2, 0.0D).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramFloat1, paramFloat2, 0.0D).func_181666_a(f2, f3, f4, f1).func_181675_d();
    tessellator.func_78381_a();
    GlStateManager.func_179098_w();
    GlStateManager.func_179084_k();
  }
  
  public static void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8) {
    (give up)null;
    Tessellator tessellator = Tessellator.func_178181_a();
    BufferBuilder bufferBuilder = tessellator.func_178180_c();
    GlStateManager.func_179147_l();
    GlStateManager.func_179090_x();
    GlStateManager.func_179120_a(770, 771, 1, 0);
    bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
    bufferBuilder.func_181662_b(paramFloat1, paramFloat4, 0.0D).func_181666_a(paramFloat5 / 255.0F, paramFloat6 / 255.0F, paramFloat7 / 255.0F, paramFloat8).func_181675_d();
    bufferBuilder.func_181662_b(paramFloat3, paramFloat4, 0.0D).func_181666_a(paramFloat5 / 255.0F, paramFloat6 / 255.0F, paramFloat7 / 255.0F, paramFloat8).func_181675_d();
    bufferBuilder.func_181662_b(paramFloat3, paramFloat2, 0.0D).func_181666_a(paramFloat5 / 255.0F, paramFloat6 / 255.0F, paramFloat7 / 255.0F, paramFloat8).func_181675_d();
    bufferBuilder.func_181662_b(paramFloat1, paramFloat2, 0.0D).func_181666_a(paramFloat5 / 255.0F, paramFloat6 / 255.0F, paramFloat7 / 255.0F, paramFloat8).func_181675_d();
    tessellator.func_78381_a();
    GlStateManager.func_179098_w();
    GlStateManager.func_179084_k();
  }
  
  public static void setupFBO(Framebuffer paramFramebuffer) {
    (give up)null;
    EXTFramebufferObject.glDeleteRenderbuffersEXT(paramFramebuffer.field_147624_h);
    int i = EXTFramebufferObject.glGenRenderbuffersEXT();
    EXTFramebufferObject.glBindRenderbufferEXT(36161, i);
    EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, mc.field_71443_c, mc.field_71440_d);
    EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, i);
    EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, i);
  }
  
  public static void drawText(BlockPos paramBlockPos, String paramString) {
    (give up)null;
    GlStateManager.func_179094_E();
    glBillboardDistanceScaled(paramBlockPos.func_177958_n() + Float.intBitsToFloat(1.0819177E9F ^ 0x7F7CC137), paramBlockPos.func_177956_o() + Float.intBitsToFloat(1.11762099E9F ^ 0x7D9D8B1F), paramBlockPos.func_177952_p() + Float.intBitsToFloat(1.09914099E9F ^ 0x7E838F53), (EntityPlayer)mc.field_71439_g, Float.intBitsToFloat(1.08663987E9F ^ 0x7F44CED1));
    GlStateManager.func_179097_i();
    GlStateManager.func_179137_b(-(mc.field_71466_p.func_78256_a(paramString) / 2.0D), 0.0D, 0.0D);
    FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), paramString, 0, 0, -5592406);
    GlStateManager.func_179121_F();
  }
  
  public static void renderTwo() {
    (give up)null;
    GL11.glStencilFunc(512, 0, 15);
    GL11.glStencilOp(7681, 7681, 7681);
    GL11.glPolygonMode(1032, 6914);
  }
  
  public static void drawBox(BlockPos paramBlockPos, Color paramColor, boolean paramBoolean1, boolean paramBoolean2) {
    (give up)null;
    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(paramBlockPos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, paramBlockPos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, paramBlockPos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (paramBlockPos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (paramBlockPos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (paramBlockPos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
    drawBox(axisAlignedBB, paramColor.getRed(), paramColor.getGreen(), paramColor.getBlue(), paramColor.getAlpha(), paramBoolean1, paramBoolean2);
  }
  
  public static void glStart(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    glrendermethod();
    GL11.glColor4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public static void setColor(Color paramColor) {
    (give up)null;
    GL11.glColor4d(paramColor.getRed() / 255.0D, paramColor.getGreen() / 255.0D, paramColor.getBlue() / 255.0D, paramColor.getAlpha() / 255.0D);
  }
  
  public static void renderOne(float paramFloat) {
    (give up)null;
    checkSetupFBO();
    GL11.glPushAttrib(1048575);
    GL11.glDisable(3008);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glLineWidth(paramFloat);
    GL11.glEnable(2848);
    GL11.glEnable(2960);
    GL11.glClear(1024);
    GL11.glClearStencil(15);
    GL11.glStencilFunc(512, 1, 15);
    GL11.glStencilOp(7681, 7681, 7681);
    GL11.glPolygonMode(1032, 6913);
  }
  
  public static AxisAlignedBB convertBox(AxisAlignedBB paramAxisAlignedBB) {
    (give up)null;
    return new AxisAlignedBB(paramAxisAlignedBB.field_72340_a - (mc.func_175598_ae()).field_78730_l, paramAxisAlignedBB.field_72338_b - (mc.func_175598_ae()).field_78731_m, paramAxisAlignedBB.field_72339_c - (mc.func_175598_ae()).field_78728_n, paramAxisAlignedBB.field_72336_d - (mc.func_175598_ae()).field_78730_l, paramAxisAlignedBB.field_72337_e - (mc.func_175598_ae()).field_78731_m, paramAxisAlignedBB.field_72334_f - (mc.func_175598_ae()).field_78728_n);
  }
  
  public static void checkSetupFBO() {
    (give up)null;
    Framebuffer framebuffer = mc.func_147110_a();
    if (framebuffer.field_147624_h > -1) {
      setupFBO(framebuffer);
      framebuffer.field_147624_h = -1;
    } 
  }
  
  public static void drawBox(AxisAlignedBB paramAxisAlignedBB, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2) {
    (give up)null;
    glSetup();
    RenderGlobal.func_189696_b(paramAxisAlignedBB, paramInt1 / Float.intBitsToFloat(1.00973286E9F ^ 0x7F504CF9), paramInt2 / Float.intBitsToFloat(1.03190035E9F ^ 0x7EFE8CD7), paramInt3 / Float.intBitsToFloat(1.00958605E9F ^ 0x7F520F66), paramInt4 / Float.intBitsToFloat(1.01290765E9F ^ 0x7F20BE62));
    RenderGlobal.func_189697_a(paramAxisAlignedBB, paramInt1 / Float.intBitsToFloat(1.01298976E9F ^ 0x7F1FFF25), paramInt2 / Float.intBitsToFloat(1.01272006E9F ^ 0x7F23E1CC), paramInt3 / Float.intBitsToFloat(1.01406022E9F ^ 0x7F0E54C2), paramInt4 / Float.intBitsToFloat(1.01328032E9F ^ 0x7F1A6E5E) * Float.intBitsToFloat(1.09413389E9F ^ 0x7EF728A9));
    glCleanup();
  }
  
  public static void renderThree() {
    (give up)null;
    GL11.glStencilFunc(514, 1, 15);
    GL11.glStencilOp(7680, 7680, 7680);
    GL11.glPolygonMode(1032, 6913);
  }
  
  public static void drawBlockOutline(AxisAlignedBB paramAxisAlignedBB, Color paramColor, float paramFloat) {
    (give up)null;
    float f1 = paramColor.getRed() / Float.intBitsToFloat(1.03781062E9F ^ 0x7EA4BBCD);
    float f2 = paramColor.getGreen() / Float.intBitsToFloat(1.0343776E9F ^ 0x7ED85961);
    float f3 = paramColor.getBlue() / Float.intBitsToFloat(1.00752474E9F ^ 0x7F729B93);
    float f4 = paramColor.getAlpha() / Float.intBitsToFloat(1.01244672E9F ^ 0x7F27B602);
    GlStateManager.func_179094_E();
    GlStateManager.func_179147_l();
    GlStateManager.func_179097_i();
    GlStateManager.func_179120_a(770, 771, 0, 1);
    GlStateManager.func_179090_x();
    GlStateManager.func_179132_a(false);
    GL11.glEnable(2848);
    GL11.glHint(3154, 4354);
    GL11.glLineWidth(paramFloat);
    Tessellator tessellator = Tessellator.func_178181_a();
    BufferBuilder bufferBuilder = tessellator.func_178180_c();
    bufferBuilder.func_181668_a(3, DefaultVertexFormats.field_181706_f);
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f1, f2, f3, f4).func_181675_d();
    tessellator.func_78381_a();
    GL11.glDisable(2848);
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179126_j();
    GlStateManager.func_179098_w();
    GlStateManager.func_179084_k();
    GlStateManager.func_179121_F();
  }
  
  public static void drawBoundingBox(AxisAlignedBB paramAxisAlignedBB, float paramFloat, int paramInt) {
    paramInt = 268500991;
    (give up)null;
    float f1 = (paramInt >> 24 & 0xFF) / Float.intBitsToFloat(1.0460167E9F ^ 0x7D27F2AF);
    float f2 = (paramInt >> 16 & 0xFF) / Float.intBitsToFloat(1.01436986E9F ^ 0x7F090E4D);
    float f3 = (paramInt >> 8 & 0xFF) / Float.intBitsToFloat(1.01335667E9F ^ 0x7F19989E);
    float f4 = (paramInt & 0xFF) / Float.intBitsToFloat(1.02448819E9F ^ 0x7E6F72E3);
    drawBoundingBox(paramAxisAlignedBB, paramFloat, f2, f3, f4, f1);
  }
  
  public static void drawFilledBox(AxisAlignedBB paramAxisAlignedBB, int paramInt) {
    paramInt = 268500991;
    (give up)null;
    GlStateManager.func_179094_E();
    GlStateManager.func_179147_l();
    GlStateManager.func_179097_i();
    GlStateManager.func_179120_a(770, 771, 0, 1);
    GlStateManager.func_179090_x();
    GlStateManager.func_179132_a(false);
    float f1 = (paramInt >> 24 & 0xFF) / Float.intBitsToFloat(1.01224678E9F ^ 0x7F2AA8F2);
    float f2 = (paramInt >> 16 & 0xFF) / Float.intBitsToFloat(1.008984E9F ^ 0x7F5CDFA1);
    float f3 = (paramInt >> 8 & 0xFF) / Float.intBitsToFloat(1.01374042E9F ^ 0x7F13738B);
    float f4 = (paramInt & 0xFF) / Float.intBitsToFloat(1.01355341E9F ^ 0x7F1698E9);
    Tessellator tessellator = Tessellator.func_178181_a();
    BufferBuilder bufferBuilder = tessellator.func_178180_c();
    bufferBuilder.func_181668_a(7, DefaultVertexFormats.field_181706_f);
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72336_d, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72338_b, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72334_f).func_181666_a(f2, f3, f4, f1).func_181675_d();
    bufferBuilder.func_181662_b(paramAxisAlignedBB.field_72340_a, paramAxisAlignedBB.field_72337_e, paramAxisAlignedBB.field_72339_c).func_181666_a(f2, f3, f4, f1).func_181675_d();
    tessellator.func_78381_a();
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179126_j();
    GlStateManager.func_179098_w();
    GlStateManager.func_179084_k();
    GlStateManager.func_179121_F();
  }
  
  public static void glBillboard(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    0.02666667F;
    GlStateManager.func_179137_b(paramFloat1 - (mc.func_175598_ae()).field_78725_b, paramFloat2 - (mc.func_175598_ae()).field_78726_c, paramFloat3 - (mc.func_175598_ae()).field_78723_d);
    GlStateManager.func_187432_a(0.0F, 1.0F, 0.0F);
    GlStateManager.func_179114_b(-mc.field_71439_g.field_70177_z, 0.0F, 1.0F, 0.0F);
    GlStateManager.func_179114_b(mc.field_71439_g.field_70125_A, (mc.field_71474_y.field_74320_O == 2) ? -1.0F : 1.0F, 0.0F, 0.0F);
    GlStateManager.func_179152_a(-0.02666667F, -0.02666667F, 0.02666667F);
  }
  
  public static void glBillboardDistanceScaled(float paramFloat1, float paramFloat2, float paramFloat3, EntityPlayer paramEntityPlayer, float paramFloat4) {
    (give up)null;
    glBillboard(paramFloat1, paramFloat2, paramFloat3);
    int i = (int)paramEntityPlayer.func_70011_f(paramFloat1, paramFloat2, paramFloat3);
    float f = i / Float.intBitsToFloat(1.05871437E9F ^ 0x7F1AB310) / (Float.intBitsToFloat(1.06017088E9F ^ 0x7F30EC82) + Float.intBitsToFloat(1.06239386E9F ^ 0x7F52D7EA) - paramFloat4);
    if (f < Float.intBitsToFloat(1.0844713E9F ^ 0x7F23B83D))
      f = Float.intBitsToFloat(1.11201869E9F ^ 0x7DC80EEF); 
    GlStateManager.func_179152_a(f, f, f);
  }
  
  public static void renderFour(Color paramColor) {
    (give up)null;
    setColor(paramColor);
    GL11.glDepthMask(false);
    GL11.glDisable(2929);
    GL11.glEnable(10754);
    GL11.glPolygonOffset(1.0F, -2000000.0F);
    OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, 240.0F, 240.0F);
  }
  
  public static void drawArc(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, int paramInt) {
    (give up)null;
    GL11.glBegin(4);
    for (int i = (int)(paramInt / 360.0F / paramFloat4) + 1; i <= paramInt / 360.0F / paramFloat5; i++) {
      double d1 = 6.283185307179586D * (i - 1) / paramInt;
      double d2 = 6.283185307179586D * i / paramInt;
      GL11.glVertex2d(paramFloat1, paramFloat2);
      GL11.glVertex2d(paramFloat1 + Math.cos(d2) * paramFloat3, paramFloat2 + Math.sin(d2) * paramFloat3);
      GL11.glVertex2d(paramFloat1 + Math.cos(d1) * paramFloat3, paramFloat2 + Math.sin(d1) * paramFloat3);
    } 
    glEnd();
  }
  
  static {
    camera = (ICamera)new Frustum();
  }
  
  public static void glCleanup() {
    (give up)null;
    GL11.glDisable(2848);
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179126_j();
    GlStateManager.func_179098_w();
    GlStateManager.func_179084_k();
    GlStateManager.func_179121_F();
  }
  
  public static void renderFive() {
    (give up)null;
    GL11.glPolygonOffset(1.0F, 2000000.0F);
    GL11.glDisable(10754);
    GL11.glEnable(2929);
    GL11.glDepthMask(true);
    GL11.glDisable(2960);
    GL11.glDisable(2848);
    GL11.glHint(3154, 4352);
    GL11.glEnable(3042);
    GL11.glEnable(2896);
    GL11.glEnable(3553);
    GL11.glEnable(3008);
    GL11.glPopAttrib();
  }
}
