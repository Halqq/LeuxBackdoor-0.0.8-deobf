package me.obsidianbreaker.leux.client.turok.render.font.management;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.turok.render.font.TurokFont;
import me.obsidianbreaker.leux.client.turok.render.opengl.TurokRenderGL;
import net.minecraft.client.Minecraft;

public class TurokFontManager {
  public static void render(TurokFont paramTurokFont, String paramString, int paramInt1, int paramInt2, boolean paramBoolean, Color paramColor) {
    (give up)null;
    TurokRenderGL.enableState(3553);
    TurokRenderGL.enableState(3042);
    TurokRenderGL.color(paramColor.getRed(), paramColor.getGreen(), paramColor.getBlue(), paramColor.getAlpha());
    if (paramTurokFont.isRenderingCustomFont()) {
      paramTurokFont.drawStringWithShadow(paramString, paramInt1, paramInt2, paramColor.getRGB());
    } else {
      (Minecraft.func_71410_x()).field_71466_p.func_175063_a(paramString, paramInt1, paramInt2, paramColor.getRGB());
    } 
    TurokRenderGL.disableState(3553);
  }
  
  public static int getStringWidth(TurokFont paramTurokFont, String paramString) {
    (give up)null;
    return paramTurokFont.isRenderingCustomFont() ? paramTurokFont.getStringWidth(paramString) : (Minecraft.func_71410_x()).field_71466_p.func_78256_a(paramString);
  }
  
  public static int getStringHeight(TurokFont paramTurokFont, String paramString) {
    (give up)null;
    return paramTurokFont.isRenderingCustomFont() ? paramTurokFont.getStringHeight(paramString) : ((Minecraft.func_71410_x()).field_71466_p.field_78288_b * paramTurokFont.getFontSize());
  }
}
