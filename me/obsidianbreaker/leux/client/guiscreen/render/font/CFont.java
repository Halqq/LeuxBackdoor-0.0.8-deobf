package me.obsidianbreaker.leux.client.guiscreen.render.font;

import give up;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;

public class CFont {
  public float imgSize = Float.intBitsToFloat(9.8929158E8F ^ 0x7EF76453);
  
  public boolean fractionalMetrics;
  
  public DynamicTexture tex;
  
  public boolean antiAlias;
  
  public int charOffset = 0;
  
  public CFont$CharData[] charData = new CFont$CharData[256];
  
  public int fontHeight = -1;
  
  public Font font;
  
  public int getHeight() {
    (give up)null;
    return (this.fontHeight - 8) / 2;
  }
  
  public DynamicTexture setupTexture(Font paramFont, boolean paramBoolean1, boolean paramBoolean2, CFont$CharData[] paramArrayOfCFont$CharData) {
    (give up)null;
    BufferedImage bufferedImage = generateFontImage(paramFont, paramBoolean1, paramBoolean2, paramArrayOfCFont$CharData);
    return new DynamicTexture(bufferedImage);
  }
  
  public Font getFont() {
    (give up)null;
    return this.font;
  }
  
  public int getStringHeight(String paramString) {
    (give up)null;
    return getHeight();
  }
  
  public CFont(Font paramFont, boolean paramBoolean1, boolean paramBoolean2) {
    this.font = paramFont;
    this.antiAlias = paramBoolean1;
    this.fractionalMetrics = paramBoolean2;
    this.tex = setupTexture(paramFont, paramBoolean1, paramBoolean2, this.charData);
  }
  
  public boolean isFractionalMetrics() {
    (give up)null;
    return this.fractionalMetrics;
  }
  
  public int getStringWidth(String paramString) {
    (give up)null;
    int i = 0;
    for (char c : paramString.toCharArray()) {
      if (c < this.charData.length)
        i += (this.charData[c]).width - 8 + this.charOffset; 
    } 
    return i / 2;
  }
  
  public void setFractionalMetrics(boolean paramBoolean) {
    (give up)null;
    if (this.fractionalMetrics != paramBoolean) {
      this.fractionalMetrics = paramBoolean;
      this.tex = setupTexture(this.font, this.antiAlias, paramBoolean, this.charData);
    } 
  }
  
  public void drawQuad(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8) {
    (give up)null;
    float f1 = paramFloat5 / 512.0F;
    float f2 = paramFloat6 / 512.0F;
    float f3 = paramFloat7 / 512.0F;
    float f4 = paramFloat8 / 512.0F;
    GL11.glTexCoord2f(f1 + f3, f2);
    GL11.glVertex2d((paramFloat1 + paramFloat3), paramFloat2);
    GL11.glTexCoord2f(f1, f2);
    GL11.glVertex2d(paramFloat1, paramFloat2);
    GL11.glTexCoord2f(f1, f2 + f4);
    GL11.glVertex2d(paramFloat1, (paramFloat2 + paramFloat4));
    GL11.glTexCoord2f(f1, f2 + f4);
    GL11.glVertex2d(paramFloat1, (paramFloat2 + paramFloat4));
    GL11.glTexCoord2f(f1 + f3, f2 + f4);
    GL11.glVertex2d((paramFloat1 + paramFloat3), (paramFloat2 + paramFloat4));
    GL11.glTexCoord2f(f1 + f3, f2);
    GL11.glVertex2d((paramFloat1 + paramFloat3), paramFloat2);
  }
  
  public boolean isAntiAlias() {
    (give up)null;
    return this.antiAlias;
  }
  
  public void drawChar(CFont$CharData[] paramArrayOfCFont$CharData, char paramChar, float paramFloat1, float paramFloat2) throws ArrayIndexOutOfBoundsException {
    (give up)null;
    drawQuad(paramFloat1, paramFloat2, (paramArrayOfCFont$CharData[paramChar]).width, (paramArrayOfCFont$CharData[paramChar]).height, (paramArrayOfCFont$CharData[paramChar]).storedX, (paramArrayOfCFont$CharData[paramChar]).storedY, (paramArrayOfCFont$CharData[paramChar]).width, (paramArrayOfCFont$CharData[paramChar]).height);
  }
  
  public BufferedImage generateFontImage(Font paramFont, boolean paramBoolean1, boolean paramBoolean2, CFont$CharData[] paramArrayOfCFont$CharData) {
    (give up)null;
    getClass();
    'Ȁ';
    BufferedImage bufferedImage = new BufferedImage(512, 512, 2);
    Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
    graphics2D.setFont(paramFont);
    graphics2D.setColor(new Color(255, 255, 255, 0));
    graphics2D.fillRect(0, 0, 512, 512);
    graphics2D.setColor(Color.WHITE);
  }
  
  public void setAntiAlias(boolean paramBoolean) {
    (give up)null;
    if (this.antiAlias != paramBoolean) {
      this.antiAlias = paramBoolean;
      this.tex = setupTexture(this.font, paramBoolean, this.fractionalMetrics, this.charData);
    } 
  }
  
  public void setFont(Font paramFont) {
    (give up)null;
    this.font = paramFont;
    this.tex = setupTexture(paramFont, this.antiAlias, this.fractionalMetrics, this.charData);
  }
}
