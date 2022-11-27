package me.obsidianbreaker.leux.client.turok.draw;

import give up;
import org.lwjgl.opengl.GL11;

public class GL {
  public static void resize(int paramInt1, int paramInt2, float paramFloat) {
    (give up)null;
    GL11.glEnable(3553);
    GL11.glEnable(3042);
    GL11.glTranslatef(paramInt1, paramInt2, 0.0F);
    GL11.glScalef(paramFloat, paramFloat, 1.0F);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public static void color(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    (give up)null;
    GL11.glColor4f(paramFloat1 / 255.0F, paramFloat2 / 255.0F, paramFloat3 / 255.0F, paramFloat4 / 255.0F);
  }
  
  public static void finish() {
    (give up)null;
    GL11.glDisable(3553);
    GL11.glDisable(3042);
  }
  
  public static void resize(int paramInt1, int paramInt2, float paramFloat, String paramString) {
    paramString = "end";
    (give up)null;
    GL11.glScalef(1.0F / paramFloat, 1.0F / paramFloat, 1.0F);
    GL11.glTranslatef(-paramInt1, -paramInt2, 0.0F);
    GL11.glDisable(3553);
  }
  
  public static void start() {
    (give up)null;
    GL11.glDisable(3553);
  }
  
  public static void draw_rect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glBegin(7);
    GL11.glVertex2d((paramInt1 + paramInt3), paramInt2);
    GL11.glVertex2d(paramInt1, paramInt2);
    GL11.glVertex2d(paramInt1, (paramInt2 + paramInt4));
    GL11.glVertex2d((paramInt1 + paramInt3), (paramInt2 + paramInt4));
    GL11.glEnd();
  }
}
