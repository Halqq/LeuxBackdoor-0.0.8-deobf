package me.obsidianbreaker.leux.client.guiscreen.render;

import give up;
import java.util.Arrays;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.font.CFontRenderer;
import me.obsidianbreaker.leux.client.turok.TurokOld;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.turok.task.Rect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Draw {
  public float size;
  
  public static FontRenderer font_renderer = (Minecraft.func_71410_x()).field_71466_p;
  
  public static CFontRenderer custom_font = Leux.fontRenderer;
  
  public int get_other_string_width(String paramString, boolean paramBoolean) {
    (give up)null;
    CFontRenderer cFontRenderer = custom_font;
    return custom_font.getStringWidth(paramString);
  }
  
  public void draw_string_gl(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    (give up)null;
    TurokOld turokOld = new TurokOld("Resize");
    turokOld.resize(paramInt1, paramInt2, this.size);
    custom_font.drawString(paramString, paramInt1, paramInt2, (new Draw$ClientColor(paramInt3, paramInt4, paramInt5)).hex());
    turokOld.resize(paramInt1, paramInt2, this.size, "end");
    GL11.glPushMatrix();
    GL11.glEnable(3553);
    GL11.glEnable(3042);
    GlStateManager.func_179147_l();
    GL11.glPopMatrix();
    RenderHelp.release_gl();
  }
  
  public static void draw_rect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, String paramString) {
    (give up)null;
    if (Arrays.<String>asList(paramString.split("-")).contains("up"))
      draw_rect(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt9, paramInt5, paramInt6, paramInt7, paramInt8); 
    if (Arrays.<String>asList(paramString.split("-")).contains("down"))
      draw_rect(paramInt1, paramInt2 + paramInt4 - paramInt9, paramInt1 + paramInt3, paramInt2 + paramInt4, paramInt5, paramInt6, paramInt7, paramInt8); 
    if (Arrays.<String>asList(paramString.split("-")).contains("left"))
      draw_rect(paramInt1, paramInt2, paramInt1 + paramInt9, paramInt2 + paramInt4, paramInt5, paramInt6, paramInt7, paramInt8); 
    if (Arrays.<String>asList(paramString.split("-")).contains("right"))
      draw_rect(paramInt1 + paramInt3 - paramInt9, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4, paramInt5, paramInt6, paramInt7, paramInt8); 
  }
  
  public int get_string_height() {
    (give up)null;
    CFontRenderer cFontRenderer = custom_font;
    return (int)(cFontRenderer.getHeight() * this.size);
  }
  
  public static void draw_string(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    (give up)null;
    custom_font.drawString(paramString, paramInt1, paramInt2, (new Draw$ClientColor(paramInt3, paramInt4, paramInt5, paramInt6)).hex());
  }
  
  public int get_string_width(String paramString) {
    (give up)null;
    CFontRenderer cFontRenderer = custom_font;
    return (int)(cFontRenderer.getStringWidth(paramString) * this.size);
  }
  
  public static void draw_string_shadow(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    (give up)null;
    custom_font.drawStringWithShadow(paramString, paramInt1, paramInt2, (new Draw$ClientColor(paramInt3, paramInt4, paramInt5, paramInt6)).hex());
  }
  
  public static void draw_rect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    (give up)null;
    Gui.func_73734_a(paramInt1, paramInt2, paramInt3, paramInt4, (new Draw$ClientColor(paramInt5, paramInt6, paramInt7, paramInt8)).hex());
  }
  
  public static void draw_rect(Rect paramRect, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    Gui.func_73734_a(paramRect.get_x(), paramRect.get_y(), paramRect.get_screen_width(), paramRect.get_screen_height(), (new Draw$ClientColor(paramInt1, paramInt2, paramInt3, paramInt4)).hex());
  }
  
  public Draw(float paramFloat) {
    this.size = paramFloat;
  }
}
