package me.obsidianbreaker.leux.client.turok.mouse;

import give up;
import org.lwjgl.input.Mouse;

public class TurokMouse {
  public int x;
  
  public int y;
  
  public int scroll;
  
  public int getX() {
    (give up)null;
    return this.x;
  }
  
  public TurokMouse(int paramInt1, int paramInt2) {
    this.x = paramInt1;
    this.y = paramInt2;
  }
  
  public int getScroll() {
    (give up)null;
    return this.scroll;
  }
  
  public void setScroll(int paramInt) {
    (give up)null;
    this.scroll = paramInt;
  }
  
  public int getY() {
    (give up)null;
    return this.y;
  }
  
  public boolean hasWheel() {
    (give up)null;
    return Mouse.hasWheel();
  }
}
