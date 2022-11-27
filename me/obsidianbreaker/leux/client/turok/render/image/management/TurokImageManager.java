package me.obsidianbreaker.leux.client.turok.render.image.management;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.turok.render.image.TurokImage;
import me.obsidianbreaker.leux.client.turok.render.opengl.TurokRenderGL;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class TurokImageManager {
  public static void render(TurokImage paramTurokImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Color paramColor) {
    (give up)null;
    render(paramTurokImage, paramInt1, paramInt2, paramInt3, paramInt4, 0, 0, Float.intBitsToFloat(1.10752333E9F ^ 0x7D8376C7), Float.intBitsToFloat(1.10677926E9F ^ 0x7E781C8B), paramColor);
  }
  
  public static void render(TurokImage paramTurokImage, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, int paramInt3, int paramInt4, float paramFloat3, float paramFloat4, Color paramColor) {
    paramInt4 = 0;
    paramInt3 = 0;
    (give up)null;
    TurokRenderGL.enableState(3042);
    TurokRenderGL.blendFunc(770, 771);
    TurokRenderGL.enableState(3553);
    TurokRenderGL.enableState(2884);
    TurokRenderGL.disableState(2929);
    GL11.glColor4f(paramColor.getRed() / Float.intBitsToFloat(1.03710579E9F ^ 0x7EAFFA75), paramColor.getGreen() / Float.intBitsToFloat(1.03311245E9F ^ 0x7EEB0B67), paramColor.getBlue() / Float.intBitsToFloat(1.00680941E9F ^ 0x7F7DB139), paramColor.getAlpha() / Float.intBitsToFloat(1.02545075E9F ^ 0x7E602303));
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(paramTurokImage.getResourceLocation());
    GL11.glTexParameteri(3553, 10242, 10497);
    GL11.glTexParameteri(3553, 10243, 10497);
    TurokRenderGL.drawTextureInterpolated(paramInt1, paramInt2, paramFloat1, paramFloat2, paramInt3, paramInt4, paramFloat3, paramFloat4);
    TurokRenderGL.disableState(3042);
    TurokRenderGL.disableState(3553);
    TurokRenderGL.disableState(2884);
    TurokRenderGL.enableState(2929);
  }
}
