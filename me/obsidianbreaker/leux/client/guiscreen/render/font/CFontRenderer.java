package me.obsidianbreaker.leux.client.guiscreen.render.font;

import give up;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;

public class CFontRenderer extends CFont {
  public DynamicTexture texItalicBold;
  
  public String colorcodeIdentifiers = "0123456789abcdefklmnor";
  
  public CFont$CharData[] italicChars = new CFont$CharData[256];
  
  public int fontSize;
  
  public DynamicTexture texItalic;
  
  public CFont$CharData[] boldItalicChars = new CFont$CharData[256];
  
  public DynamicTexture texBold;
  
  public int[] colorCode = new int[32];
  
  public CFont$CharData[] boldChars = new CFont$CharData[256];
  
  public String fontName;
  
  public float drawStringWithShadow(String paramString, double paramDouble1, double paramDouble2, int paramInt) {
    (give up)null;
    float f = drawString(paramString, paramDouble1 + 1.0D, paramDouble2 + 1.0D, paramInt, true);
    return Math.max(f, drawString(paramString, paramDouble1, paramDouble2, paramInt, false));
  }
  
  public float drawString(String paramString, double paramDouble1, double paramDouble2, int paramInt, boolean paramBoolean) {
    (give up)null;
    paramDouble1--;
    paramDouble2 -= 2.0D;
    return Float.intBitsToFloat(2.1387817E9F ^ 0x7F7B3825);
  }
  
  public void setFractionalMetrics(boolean paramBoolean) {
    (give up)null;
    super.setFractionalMetrics(paramBoolean);
    setupBoldItalicIDs();
  }
  
  public void setAntiAlias(boolean paramBoolean) {
    (give up)null;
    super.setAntiAlias(paramBoolean);
    setupBoldItalicIDs();
  }
  
  public int getFontSize() {
    (give up)null;
    return this.fontSize;
  }
  
  public List wrapWords(String paramString, double paramDouble) {
    (give up)null;
    ArrayList<String> arrayList = new ArrayList();
    if (getStringWidth(paramString) > paramDouble) {
      String[] arrayOfString = paramString.split(" ");
      String str = "";
      char c = '￿';
      for (String str1 : arrayOfString) {
        for (byte b = 0; b < (str1.toCharArray()).length; b++) {
          char c1 = str1.toCharArray()[b];
          if (c1 == '§' && b < (str1.toCharArray()).length - 1)
            c = str1.toCharArray()[b + 1]; 
        } 
        if (getStringWidth(str + str1 + " ") < paramDouble) {
          str = str + str1 + " ";
        } else {
          arrayList.add(str);
          str = "§" + c + str1 + " ";
        } 
      } 
      str.length();
    } else {
      arrayList.add(paramString);
    } 
    return arrayList;
  }
  
  public int getStringWidth(String paramString) {
    (give up)null;
    return 0;
  }
  
  public float drawCenteredString(String paramString, float paramFloat1, float paramFloat2, int paramInt) {
    (give up)null;
    return drawString(paramString, paramFloat1 - (getStringWidth(paramString) / 2), paramFloat2, paramInt);
  }
  
  public void setFontSize(int paramInt) {
    (give up)null;
    this.fontSize = paramInt;
  }
  
  public CFontRenderer(Font paramFont, boolean paramBoolean1, boolean paramBoolean2) {
    super(paramFont, paramBoolean1, paramBoolean2);
    setupMinecraftColorcodes();
    setupBoldItalicIDs();
  }
  
  public float drawCenteredStringWithShadow(String paramString, float paramFloat1, float paramFloat2, int paramInt) {
    (give up)null;
    return drawStringWithShadow(paramString, (paramFloat1 - (getStringWidth(paramString) / 2)), paramFloat2, paramInt);
  }
  
  public void setupBoldItalicIDs() {
    (give up)null;
    this.texBold = setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
    this.texItalic = setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
    this.texItalicBold = setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
  }
  
  public float drawString(String paramString, float paramFloat1, float paramFloat2, int paramInt) {
    (give up)null;
    return drawString(paramString, paramFloat1, paramFloat2, paramInt, false);
  }
  
  public String getFontName() {
    (give up)null;
    return this.fontName;
  }
  
  public void setFont(Font paramFont) {
    (give up)null;
    super.setFont(paramFont);
    setupBoldItalicIDs();
  }
  
  public List formatString(String paramString, double paramDouble) {
    (give up)null;
    ArrayList<String> arrayList = new ArrayList();
    String str = "";
    char c = '￿';
    char[] arrayOfChar = paramString.toCharArray();
    for (byte b = 0;; b++) {
      char c1;
      if (b < arrayOfChar.length) {
        c1 = arrayOfChar[b];
        if (c1 == '§' && b < arrayOfChar.length - 1)
          c = arrayOfChar[b + 1]; 
      } else {
        str.length();
        return arrayList;
      } 
      if (getStringWidth(str + c1) < paramDouble) {
        str = str + c1;
      } else {
        arrayList.add(str);
        str = "§" + c + c1;
      } 
    } 
  }
  
  public void drawLine(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, float paramFloat) {
    (give up)null;
    GL11.glDisable(3553);
    GL11.glLineWidth(paramFloat);
    GL11.glBegin(1);
    GL11.glVertex2d(paramDouble1, paramDouble2);
    GL11.glVertex2d(paramDouble3, paramDouble4);
    GL11.glEnd();
    GL11.glEnable(3553);
  }
  
  public void setupMinecraftColorcodes() {
    (give up)null;
    byte b = 0;
    while (true) {
      32;
      int i = (b >> 3 & 0x1) * 85;
      int j = (b >> 2 & 0x1) * 170 + i;
      int k = (b >> 1 & 0x1) * 170 + i;
      int m = (b >> 0 & 0x1) * 170 + i;
      6;
      16;
      this.colorCode[b] = (j & 0xFF) << 16 | (k & 0xFF) << 8 | m & 0xFF;
    } 
  }
  
  public void setFontName(String paramString) {
    (give up)null;
    this.fontName = paramString;
  }
}
