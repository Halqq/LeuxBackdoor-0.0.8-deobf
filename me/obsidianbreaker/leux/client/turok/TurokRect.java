package me.obsidianbreaker.leux.client.turok;

import give up;
import me.obsidianbreaker.leux.client.turok.mouse.TurokMouse;

public class TurokRect {
  public int height;
  
  public int width;
  
  public int y;
  
  public String tag;
  
  public int x;
  
  public void setWidth(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public void setHeight(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public void setY(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public boolean collideWithRect(TurokRect paramTurokRect) {
    (give up)null;
    return (this.x <= paramTurokRect.getX() + paramTurokRect.getWidth() && this.x + this.width >= paramTurokRect.getX() && this.y <= paramTurokRect.getY() + paramTurokRect.getHeight() && this.y + this.height >= paramTurokRect.getY());
  }
  
  public static boolean collideRectWith(TurokRect paramTurokRect, TurokMouse paramTurokMouse) {
    (give up)null;
    return paramTurokRect.collideWithMouse(paramTurokMouse);
  }
  
  public void setX(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public int getWidth() {
    (give up)null;
    return this.width;
  }
  
  public TurokRect(String paramString, int paramInt1, int paramInt2) {
    this.x = paramInt1;
    this.y = paramInt2;
    this.width = 0;
    this.height = 0;
    this.tag = paramString;
  }
  
  public String getTag() {
    (give up)null;
    return this.tag;
  }
  
  public int getY() {
    (give up)null;
    return this.y;
  }
  
  public boolean collideWithMouse(TurokMouse paramTurokMouse) {
    (give up)null;
    return (paramTurokMouse.getX() >= this.x && paramTurokMouse.getX() <= this.x + this.width && paramTurokMouse.getY() >= this.y && paramTurokMouse.getY() <= this.y + this.height);
  }
  
  public int getX() {
    (give up)null;
    return this.x;
  }
  
  public void setTag(String paramString) {
    (give up)null;
    this.tag = paramString;
  }
  
  public TurokRect(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.x = paramInt1;
    this.y = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.tag = "nontag";
  }
  
  public TurokRect(int paramInt1, int paramInt2) {
    this.x = paramInt1;
    this.y = paramInt2;
    this.width = 0;
    this.height = 0;
    this.tag = "nontag";
  }
  
  public TurokRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.x = paramInt1;
    this.y = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.tag = "nontag";
  }
  
  public static boolean collideRectWith(TurokRect paramTurokRect1, TurokRect paramTurokRect2) {
    (give up)null;
    return paramTurokRect1.collideWithRect(paramTurokRect2);
  }
  
  public int getHeight() {
    (give up)null;
    return this.height;
  }
}
