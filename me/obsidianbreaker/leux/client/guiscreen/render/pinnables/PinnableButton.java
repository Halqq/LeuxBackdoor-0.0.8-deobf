package me.obsidianbreaker.leux.client.guiscreen.render.pinnables;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;

public class PinnableButton {
  public static int nc_g;
  
  public String tag;
  
  public static int bg_g;
  
  public Pinnable pinnable;
  
  public int width;
  
  public static int nc_r = 0;
  
  public static int bd_r;
  
  public static int nc_b;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.08259456E9F ^ 0x7F071528));
  
  public static int bd_g;
  
  public int height;
  
  public static int bd_b;
  
  public static int bg_b;
  
  public static int nc_a;
  
  public static int bg_a;
  
  public String name;
  
  public int x;
  
  public int y;
  
  public static int bg_r;
  
  public boolean first;
  
  public Frame master;
  
  public int save_y;
  
  public void click(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    this.pinnable.click(paramInt1, paramInt2, paramInt3);
    if (motion(paramInt1, paramInt2)) {
      this.master.does_can(false);
      this.pinnable.set_active(!this.pinnable.is_active());
    } 
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public void set_save_y(int paramInt) {
    (give up)null;
    this.save_y = paramInt;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3) {
    paramInt3 = 2;
    (give up)null;
    set_width(this.master.get_width() - paramInt3);
    this.save_y = this.y + this.master.get_y() - 10;
    if (this.pinnable.is_active()) {
      Draw.draw_rect(this.x, this.save_y, this.x + this.width - paramInt3, this.save_y + this.height, bg_r, bg_g, bg_b, bg_a);
      Draw.draw_string_shadow(this.pinnable.get_title(), this.x + paramInt3, this.save_y, nc_r, nc_g, nc_b, nc_a);
    } else {
      Draw.draw_string_shadow(this.pinnable.get_title(), this.x + paramInt3, this.save_y, nc_r, nc_g, nc_b, nc_a);
    } 
    this.pinnable.render(paramInt1, paramInt2, 0);
  }
  
  static {
    nc_g = 0;
    nc_b = 0;
    nc_a = 0;
    bg_r = 0;
    bg_g = 0;
    bg_b = 0;
    bg_a = 0;
    bd_r = 0;
    bd_g = 0;
    bd_b = 0;
  }
  
  public int get_save_y() {
    (give up)null;
    return this.save_y;
  }
  
  public PinnableButton(Frame paramFrame, String paramString1, String paramString2) {
    this.master = paramFrame;
    this.name = paramString1;
    this.tag = paramString2;
    this.pinnable = Leux.get_hud_manager().get_pinnable_with_tag(paramString2);
    this.x = paramFrame.get_x();
    this.save_y = this.y = paramFrame.get_y();
    this.width = this.master.get_width();
    this.height = this.font.get_string_height();
    this.first = true;
  }
  
  public boolean motion(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    (give up)null;
    return (paramInt1 >= paramInt3 && paramInt2 >= paramInt4 && paramInt1 <= paramInt3 + paramInt5 && paramInt2 <= paramInt4 + paramInt6);
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_save_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_save_y() + get_height());
  }
  
  public void release(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    this.pinnable.release(paramInt1, paramInt2, paramInt3);
    this.master.does_can(true);
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
}
