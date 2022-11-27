package me.obsidianbreaker.leux.client.turok.task;

import give up;

public class Rect {
  public int height;
  
  public int y;
  
  public int x;
  
  public int width;
  
  public String tag;
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public String get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public void transform(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    this.x = paramInt1;
    this.y = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void transform(int paramInt1, int paramInt2) {
    (give up)null;
    this.x = paramInt1;
    this.y = paramInt2;
  }
  
  public Rect(String paramString, int paramInt1, int paramInt2) {
    this.tag = paramString;
    this.width = paramInt1;
    this.height = paramInt2;
  }
  
  public int get_screen_height() {
    (give up)null;
    return this.y + this.height;
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public boolean event_mouse(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_y() && paramInt1 <= get_screen_width() && paramInt2 <= get_screen_height());
  }
  
  public int get_screen_width() {
    (give up)null;
    return this.x + this.width;
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
}
