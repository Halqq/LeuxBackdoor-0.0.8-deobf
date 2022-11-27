package me.obsidianbreaker.leux.client.turok.render.opengl;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.turok.TurokDisplay;
import me.obsidianbreaker.leux.client.turok.TurokRect;
import me.obsidianbreaker.leux.client.turok.mouse.TurokMouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class TurokRenderGL {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public TurokDisplay display;
  
  public TurokMouse mouse;
  
  public static TurokRenderGL INSTANCE;
  
  public static void drawTextureInterpolated(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8) {
    paramFloat6 = 0.0F;
    paramFloat5 = 0.0F;
    (give up)null;
    prepare(7);
    sewTexture(0.0F + paramFloat3, 0.0F + paramFloat8);
    addVertex(paramFloat1, paramFloat2);
    sewTexture(0.0F + paramFloat3, 1.0F + paramFloat8);
    addVertex(paramFloat1, paramFloat2 + paramFloat6);
    sewTexture(1.0F + paramFloat7, 1.0F + paramFloat8);
    addVertex(paramFloat1 + paramFloat5, paramFloat2 + paramFloat6);
    sewTexture(1.0F + paramFloat7, 0.0F + paramFloat8);
    addVertex(paramFloat1 + paramFloat5, paramFloat2);
    release();
  }
  
  public static void drawSolidRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    enableState(3042);
    blendFunc(770, 771);
    prepare(7);
    addVertex(paramFloat1, paramFloat2);
    addVertex(paramFloat1, paramFloat2 + paramFloat4);
    addVertex(paramFloat1 + paramFloat3, paramFloat2 + paramFloat4);
    addVertex(paramFloat1 + paramFloat3, paramFloat2);
    release();
  }
  
  public static void drawOutlineRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10) {
    (give up)null;
    drawRoundedRect(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5);
    color(paramFloat6, paramFloat7, paramFloat8, paramFloat9);
    drawRoundedRect(paramFloat1 + paramFloat10, paramFloat2 + paramFloat10, paramFloat3 - paramFloat10 * Float.intBitsToFloat(1.04740058E9F ^ 0x7E6E1067), paramFloat4 - paramFloat10 * Float.intBitsToFloat(1.05738048E9F ^ 0x7F065879), paramFloat5);
  }
  
  public static void addVertex(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    addVertex(paramInt1, paramInt2, paramInt3);
  }
  
  public static void color(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    GL11.glColor4f(paramFloat1 / 255.0F, paramFloat2 / 255.0F, paramFloat3 / 255.0F, paramFloat4 / 255.0F);
  }
  
  public static void addVertex(float paramFloat1, float paramFloat2) {
    (give up)null;
    GL11.glVertex2f(paramFloat1, paramFloat2);
  }
  
  public static void drawDownTriangle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt) {
    (give up)null;
    enableState(3042);
    blendFunc(770, 771);
    prepare(6);
    addVertex(paramFloat1, paramFloat2);
    addVertex(paramFloat1, paramFloat2 + paramFloat4);
    addVertex(paramFloat1 + paramFloat3 + paramInt, paramFloat2 + paramFloat4);
    release();
  }
  
  public static void sewTexture(float paramFloat1, float paramFloat2) {
    (give up)null;
    GL11.glTexCoord2f(paramFloat1, paramFloat2);
  }
  
  public static void color(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    GL11.glColor3f(paramInt1 / 255.0F, paramInt2 / 255.0F, paramInt3 / 255.0F);
  }
  
  public static void blendFunc(int paramInt1, int paramInt2) {
    paramInt2 = 771;
    paramInt1 = 770;
    (give up)null;
    GL11.glBlendFunc(paramInt1, paramInt2);
  }
  
  public static void addVertex(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    addVertex((float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
  }
  
  public static void color(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    GL11.glColor4f(paramInt1 / 255.0F, paramInt2 / 255.0F, paramInt3 / 255.0F, paramInt4 / 255.0F);
  }
  
  public static void drawArcOutline(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    paramFloat6 = 40.0F;
    paramFloat5 = 360.0F;
    paramFloat4 = 0.0F;
    (give up)null;
    prepare(2);
    for (int i = (int)(paramFloat6 / 360.0F / paramFloat4) + 1; i <= paramFloat6 / 360.0F / paramFloat5; i++) {
      double d = 6.283185307179586D * i / paramFloat6;
      addVertex(paramFloat1 + Math.cos(d) * paramFloat3, paramFloat2 + Math.sin(d) * paramFloat3);
    } 
    release();
  }
  
  public static void addVertex(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    sewTexture(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void release3D() {
    (give up)null;
    GlStateManager.func_179089_o();
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179098_w();
    GlStateManager.func_179147_l();
    GlStateManager.func_179126_j();
  }
  
  public static void enableState(int paramInt) {
    (give up)null;
    GL11.glEnable(paramInt);
  }
  
  public static void hint(int paramInt1, int paramInt2) {
    paramInt2 = 4354;
    paramInt1 = 3154;
    (give up)null;
    GL11.glHint(paramInt1, paramInt2);
  }
  
  public static void init(Object paramObject) {
    (give up)null;
    if (paramObject instanceof TurokDisplay)
      INSTANCE.display = (TurokDisplay)paramObject; 
    if (paramObject instanceof TurokMouse)
      INSTANCE.mouse = (TurokMouse)paramObject; 
  }
  
  public static void scaled(float paramFloat1, float paramFloat2, float paramFloat3) {
    paramFloat3 = 0.5F;
    paramFloat2 = 0.5F;
    paramFloat1 = 0.5F;
    (give up)null;
    GL11.glScaled(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void drawRoundedRect(TurokRect paramTurokRect, float paramFloat) {
    (give up)null;
    drawRoundedRect(paramTurokRect.getX(), paramTurokRect.getY(), paramTurokRect.getWidth(), paramTurokRect.getHeight(), paramFloat);
  }
  
  public static void color(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    GL11.glColor3f(paramFloat1 / 255.0F, paramFloat2 / 255.0F, paramFloat3 / 255.0F);
  }
  
  public static void translated(float paramFloat1, float paramFloat2) {
    (give up)null;
    translated(paramFloat1, paramFloat2, Float.intBitsToFloat(2.13608755E9F ^ 0x7F521C30));
  }
  
  public static void sewTexture(float paramFloat) {
    (give up)null;
    GL11.glTexCoord1f(paramFloat);
  }
  
  public static void color(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    GL11.glColor3f((float)paramDouble1 / 255.0F, (float)paramDouble2 / 255.0F, (float)paramDouble3 / 255.0F);
  }
  
  public static void sewTexture(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    GL11.glTexCoord3f(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void drawArcOutline(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    drawArcOutline(paramFloat1, paramFloat2, paramFloat3, 0.0F, 360.0F, 40.0F);
  }
  
  public static void releaseOverlay() {
    (give up)null;
    GlStateManager.func_179089_o();
    GlStateManager.func_179132_a(true);
    GlStateManager.func_179098_w();
    GlStateManager.func_179147_l();
    GlStateManager.func_179126_j();
  }
  
  public static void prepare3D(float paramFloat) {
    (give up)null;
    blendFunc(770, 771);
    GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    GlStateManager.func_187441_d(paramFloat);
    GlStateManager.func_179090_x();
    GlStateManager.func_179132_a(false);
    GlStateManager.func_179147_l();
    GlStateManager.func_179097_i();
    GlStateManager.func_179140_f();
    GlStateManager.func_179129_p();
    GlStateManager.func_179141_d();
    GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
  }
  
  public static void addVertex(double paramDouble1, double paramDouble2) {
    (give up)null;
    addVertex((float)paramDouble1, (float)paramDouble2);
  }
  
  public static void drawArc(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    (give up)null;
    prepare(4);
    for (int i = (int)(paramFloat6 / 360.0F / paramFloat4) + 1; i <= paramFloat6 / 360.0F / paramFloat5; i++) {
      double d1 = 6.283185307179586D * (i - 1) / paramFloat6;
      double d2 = 6.283185307179586D * i / paramFloat6;
      addVertex(paramFloat1, paramFloat2);
      addVertex(paramFloat1 + Math.cos(d2) * paramFloat3, paramFloat2 + Math.sin(d2) * paramFloat3);
      addVertex(paramFloat1 + Math.cos(d1) * paramFloat3, paramFloat2 + Math.sin(d1) * paramFloat3);
    } 
    release();
  }
  
  public static void drawRectOutlineFadingMouse(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, boolean paramBoolean, Color paramColor) {
    (give up)null;
    enableState(3042);
    blendFunc(770, 771);
    float f1 = paramFloat1 - INSTANCE.mouse.getX();
    float f2 = paramFloat2 - INSTANCE.mouse.getY();
    float f3 = paramFloat1 + paramFloat3 - INSTANCE.mouse.getX();
    float f4 = paramFloat2 + paramFloat4 - INSTANCE.mouse.getY();
  }
  
  public static void color(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    (give up)null;
    GL11.glColor4f((float)paramDouble1 / Float.intBitsToFloat(1.00757626E9F ^ 0x7F7164B7), (float)paramDouble2 / Float.intBitsToFloat(1.00670483E9F ^ 0x7F7E18A6), (float)paramDouble3 / Float.intBitsToFloat(1.02566304E9F ^ 0x7E5D6043), (float)paramDouble4 / Float.intBitsToFloat(1.01452915E9F ^ 0x7F077C94));
  }
  
  public static void drawOutlineRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    drawOutlineRect(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void sewTexture(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    sewTexture((float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
  }
  
  public static void drawSolidRect(TurokRect paramTurokRect) {
    (give up)null;
    drawSolidRect(paramTurokRect.getX(), paramTurokRect.getY(), paramTurokRect.getWidth(), paramTurokRect.getHeight());
  }
  
  public static void drawRectOutlineFadingMouse(TurokRect paramTurokRect, int paramInt, boolean paramBoolean, Color paramColor) {
    (give up)null;
    drawRectOutlineFadingMouse(paramTurokRect.getX(), paramTurokRect.getY(), paramTurokRect.getWidth(), paramTurokRect.getHeight(), paramInt, paramBoolean, paramColor);
  }
  
  public static void drawTexture(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    prepare(7);
    sewTexture(0, 0);
    addVertex(paramFloat1, paramFloat2);
    sewTexture(0, 1);
    addVertex(paramFloat1, paramFloat2 + paramFloat4);
    sewTexture(1, 1);
    addVertex(paramFloat1 + paramFloat3, paramFloat2 + paramFloat4);
    sewTexture(1, 0);
    addVertex(paramFloat1 + paramFloat3, paramFloat2);
    release();
  }
  
  public static void sewTexture(double paramDouble) {
    (give up)null;
    sewTexture((float)paramDouble);
  }
  
  public static void init() {
    (give up)null;
    INSTANCE = new TurokRenderGL();
  }
  
  public static void drawUpTriangle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt) {
    (give up)null;
    enableState(3042);
    blendFunc(770, 771);
    prepare(6);
    addVertex(paramFloat1 + paramFloat3, paramFloat2 + paramFloat4);
    addVertex(paramFloat1 + paramFloat3, paramFloat2);
    addVertex(paramFloat1 - paramInt, paramFloat2);
    release();
  }
  
  public static void lineSize(float paramFloat) {
    (give up)null;
    GL11.glLineWidth(paramFloat);
  }
  
  public static void drawArc(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    drawArc(paramFloat1, paramFloat2, paramFloat3, 0.0F, 360.0F, 40.0F);
  }
  
  public static void pushMatrix() {
    (give up)null;
    GL11.glPushMatrix();
  }
  
  public static void drawOutlineRect(TurokRect paramTurokRect) {
    (give up)null;
    drawOutlineRect(paramTurokRect.getX(), paramTurokRect.getY(), paramTurokRect.getWidth(), paramTurokRect.getHeight());
  }
  
  public static void sewTexture(int paramInt) {
    (give up)null;
    sewTexture(paramInt);
  }
  
  public static void release() {
    (give up)null;
    GL11.glEnd();
  }
  
  public static void prepare(int paramInt) {
    (give up)null;
    GL11.glBegin(paramInt);
  }
  
  public static void drawOutlineRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    float f = paramFloat1 + 0.5F;
    enableState(3042);
    blendFunc(770, 771);
    lineSize(1.0F);
    prepare(2);
    addVertex(f, paramFloat2);
    addVertex(f, paramFloat2 + paramFloat4 + 0.5F);
    addVertex(paramFloat1 + paramFloat3, paramFloat2 + paramFloat4);
    addVertex(paramFloat1 + paramFloat3, paramFloat2);
    release();
  }
  
  public static void autoScale() {
    (give up)null;
    pushMatrix();
    translated(INSTANCE.display.getScaledWidth(), INSTANCE.display.getScaledHeight());
    scaled(0.5F, 0.5F, 0.5F);
    popMatrix();
  }
  
  public static void drawSolidRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    drawSolidRect(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void disableState(int paramInt) {
    (give up)null;
    GL11.glDisable(paramInt);
  }
  
  public static void sewTexture(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    sewTexture(paramInt1, paramInt2, paramInt3);
  }
  
  public static void popMatrix() {
    (give up)null;
    GL11.glPopMatrix();
  }
  
  public static void translated(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    GL11.glTranslated(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void sewTexture(int paramInt1, int paramInt2) {
    (give up)null;
    sewTexture(paramInt1, paramInt2);
  }
  
  public static void drawScissor(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    int i = paramInt1;
    int j = paramInt2;
    int k = i + paramInt3;
    int m = j + paramInt4;
    GL11.glScissor(i * INSTANCE.display.getScaleFactor(), INSTANCE.display.getHeight() - m * INSTANCE.display.getScaleFactor(), (k - i) * INSTANCE.display.getScaleFactor(), (m - j) * INSTANCE.display.getScaleFactor());
  }
  
  public static void drawRectSolidFadingMouse(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, boolean paramBoolean, Color paramColor) {
    (give up)null;
    enableState(3042);
    blendFunc(770, 771);
    float f1 = paramFloat1 - INSTANCE.mouse.getX();
    float f2 = paramFloat2 - INSTANCE.mouse.getY();
    float f3 = paramFloat1 + paramFloat3 - INSTANCE.mouse.getX();
    float f4 = paramFloat2 + paramFloat4 - INSTANCE.mouse.getY();
  }
  
  public static void addVertex(int paramInt1, int paramInt2) {
    (give up)null;
    addVertex(paramInt1, paramInt2);
  }
  
  public static void prepareOverlay() {
    (give up)null;
    pushMatrix();
    enableState(3553);
    enableState(3042);
    GlStateManager.func_179147_l();
    popMatrix();
  }
  
  public static void drawRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
    (give up)null;
    enableState(3042);
    blendFunc(770, 771);
    drawArc(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2 + paramFloat4 - paramFloat5, paramFloat5, Float.intBitsToFloat(2.13764736E9F ^ 0x7F69E927), Float.intBitsToFloat(1.03391162E9F ^ 0x7F143D5C), Float.intBitsToFloat(1.0549865E9F ^ 0x7F61D0FE));
    drawArc(paramFloat1 + paramFloat5, paramFloat2 + paramFloat4 - paramFloat5, paramFloat5, Float.intBitsToFloat(1.03984474E9F ^ 0x7F4EC56D), Float.intBitsToFloat(1.0712409E9F ^ 0x7CEDD6DF), Float.intBitsToFloat(1.05671654E9F ^ 0x7F7C36EB));
    drawArc(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5, paramFloat5, Float.intBitsToFloat(1.00694394E9F ^ 0x7F30BEB5), Float.intBitsToFloat(1.02810413E9F ^ 0x7EC09FC7), Float.intBitsToFloat(1.0502944E9F ^ 0x7F1A3886));
    drawArc(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2 + paramFloat5, paramFloat5, Float.intBitsToFloat(1.0360553E9F ^ 0x7E47F2FF), Float.intBitsToFloat(1.01824435E9F ^ 0x7F052D0C), Float.intBitsToFloat(1.05851962E9F ^ 0x7E97BA5B));
    prepare(4);
    addVertex(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2);
    addVertex(paramFloat1 + paramFloat5, paramFloat2);
    addVertex(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
    addVertex(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
    addVertex(paramFloat1 + paramFloat5, paramFloat2);
    addVertex(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5);
    addVertex(paramFloat1 + paramFloat3, paramFloat2 + paramFloat5);
    addVertex(paramFloat1, paramFloat2 + paramFloat5);
    addVertex(paramFloat1, paramFloat2 + paramFloat4 - paramFloat5);
    addVertex(paramFloat1 + paramFloat3, paramFloat2 + paramFloat5);
    addVertex(paramFloat1, paramFloat2 + paramFloat4 - paramFloat5);
    addVertex(paramFloat1 + paramFloat3, paramFloat2 + paramFloat4 - paramFloat5);
    addVertex(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2 + paramFloat4 - paramFloat5);
    addVertex(paramFloat1 + paramFloat5, paramFloat2 + paramFloat4 - paramFloat5);
    addVertex(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2 + paramFloat4);
    addVertex(paramFloat1 + paramFloat3 - paramFloat5, paramFloat2 + paramFloat4);
    addVertex(paramFloat1 + paramFloat5, paramFloat2 + paramFloat4 - paramFloat5);
    addVertex(paramFloat1 + paramFloat5, paramFloat2 + paramFloat4);
    release();
  }
  
  public static void sewTexture(double paramDouble1, double paramDouble2) {
    (give up)null;
    sewTexture((float)paramDouble1, (float)paramDouble2);
  }
  
  public static void drawRectSolidFadingMouse(TurokRect paramTurokRect, int paramInt, boolean paramBoolean, Color paramColor) {
    (give up)null;
    drawRectSolidFadingMouse(paramTurokRect.getX(), paramTurokRect.getY(), paramTurokRect.getWidth(), paramTurokRect.getHeight(), paramInt, paramBoolean, paramColor);
  }
  
  public static void drawLine3D(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat) {
    (give up)null;
    GlStateManager.func_179094_E();
    GlStateManager.func_179090_x();
    GlStateManager.func_179147_l();
    GlStateManager.func_179118_c();
    GlStateManager.func_179120_a(770, 771, 1, 0);
    GlStateManager.func_179103_j(7425);
    lineSize(paramFloat);
    enableState(2848);
    hint(3154, 4354);
    GlStateManager.func_179097_i();
    enableState(34383);
    Tessellator tessellator = Tessellator.func_178181_a();
    BufferBuilder bufferBuilder = tessellator.func_178180_c();
    bufferBuilder.func_181668_a(1, DefaultVertexFormats.field_181706_f);
    bufferBuilder.func_181662_b(paramDouble1, paramDouble2, paramDouble3).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    bufferBuilder.func_181662_b(paramDouble4, paramDouble5, paramDouble6).func_181669_b(paramInt1, paramInt2, paramInt3, paramInt4).func_181675_d();
    tessellator.func_78381_a();
    GlStateManager.func_179103_j(7424);
    disableState(2848);
    GlStateManager.func_179126_j();
    disableState(34383);
    GlStateManager.func_179084_k();
    GlStateManager.func_179141_d();
    GlStateManager.func_179098_w();
    GlStateManager.func_179121_F();
  }
}
