package me.obsidianbreaker.leux.client.util;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.client.Minecraft;

public class FontUtil {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static float drawStringWithShadow(boolean paramBoolean, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    return Leux.fontRenderer.drawStringWithShadow(paramString, paramInt1, paramInt2, paramInt3);
  }
  
  public static float drawKeyStringWithShadow(boolean paramBoolean, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    return Leux.fontRenderer.drawStringWithShadow(paramString, paramInt1, paramInt2, paramInt3);
  }
  
  public static int getFontHeight(boolean paramBoolean) {
    (give up)null;
    return Leux.fontRenderer.getHeight();
  }
  
  public static float drawStringWithShadow(boolean paramBoolean, String paramString, double paramDouble1, double paramDouble2, int paramInt) {
    (give up)null;
    return Leux.fontRenderer.drawStringWithShadow(paramString, (float)paramDouble1, (float)paramDouble2, paramInt);
  }
  
  public static int getStringWidth(boolean paramBoolean, String paramString) {
    (give up)null;
    return Leux.fontRenderer.getStringWidth(paramString);
  }
}
