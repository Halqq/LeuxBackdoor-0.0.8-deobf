package me.obsidianbreaker.leux.client.guiscreen.render.pinnables;

import give up;
import java.awt.Color;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import net.minecraft.client.Minecraft;

public class Frame {
  public int y;
  
  public static int nc_r = 0;
  
  public static int bd_r;
  
  public int width;
  
  public static int bd_a;
  
  public static int bg_b;
  
  public boolean can;
  
  public static int nc_g = 0;
  
  public static int bg_a;
  
  public String tag;
  
  public static int bd_g;
  
  public int height;
  
  public int move_x;
  
  public static int nc_a;
  
  public int move_y;
  
  public boolean move;
  
  public int x;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.08965222E9F ^ 0x7F72C628));
  
  public static int bdw_r;
  
  public ArrayList pinnable_button = new ArrayList();
  
  public static int bd_b;
  
  public static int bdw_a;
  
  public String name;
  
  public int border_size = 2;
  
  public static int nc_b = 0;
  
  public Minecraft mc = Minecraft.func_71410_x();
  
  public static int bdw_b;
  
  public static int bdw_g;
  
  public static int bg_r;
  
  public static int bg_g;
  
  public boolean can() {
    (give up)null;
    return this.can;
  }
  
  static {
    nc_a = 0;
    bg_r = 0;
    bg_g = 0;
    bg_b = 0;
    bg_a = 0;
    bd_r = 0;
    bd_g = 0;
    bd_b = 0;
    bd_a = 0;
    bdw_r = 0;
    bdw_g = 0;
    bdw_b = 0;
    bdw_a = 255;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_y() + get_height());
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public String get_name() {
    (give up)null;
    return this.name;
  }
  
  public void set_move_x(int paramInt) {
    (give up)null;
    this.move_x = paramInt;
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public void does_can(boolean paramBoolean) {
    (give up)null;
    this.can = paramBoolean;
  }
  
  public void release(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (PinnableButton pinnableButton : this.pinnable_button)
      pinnableButton.release(paramInt1, paramInt2, paramInt3); 
    set_move(false);
  }
  
  public Frame(String paramString1, String paramString2, int paramInt1, int paramInt2) {
    this.name = paramString1;
    this.tag = paramString2;
    this.x = paramInt1;
    this.y = paramInt2;
    this.move_x = 0;
    this.move_y = 0;
    this.width = 100;
    this.height = 25;
    this.can = true;
    int i = Leux.get_hud_manager().get_array_huds().size();
    byte b = 0;
    for (Pinnable pinnable : Leux.get_hud_manager().get_array_huds()) {
      PinnableButton pinnableButton = new PinnableButton(this, pinnable.get_title(), pinnable.get_tag());
      pinnableButton.set_y(this.height);
      this.pinnable_button.add(pinnableButton);
      if (++b >= i) {
        this.height += 5;
        continue;
      } 
      this.height += 12;
    } 
  }
  
  public void set_move_y(int paramInt) {
    (give up)null;
    this.move_y = paramInt;
  }
  
  public String get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public boolean is_moving() {
    (give up)null;
    return this.move;
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public void crush(int paramInt1, int paramInt2) {
    (give up)null;
    int i = this.mc.field_71443_c / 2;
    int j = this.mc.field_71440_d / 2;
    set_x(paramInt1 - this.move_x);
    set_y(paramInt2 - this.move_y);
    if (this.x + this.width >= i)
      this.x = i - this.width - 1; 
    if (this.x <= 0)
      this.x = 1; 
    if (this.y + this.height >= j)
      this.y = j - this.height - 1; 
    if (this.y <= 0)
      this.y = 1; 
    if (this.x % 2 != 0)
      this.x += this.x % 2; 
    if (this.y % 2 != 0)
      this.y += this.y % 2; 
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3) {
    paramInt3 = 2;
    (give up)null;
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int i = Color.HSBtoRGB(arrayOfFloat[0], 1.0F, 1.0F);
    boolean bool = (i <= 50) ? true : Math.min(i, 120);
    bd_a = bool;
    bdw_a = 255;
    Draw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, bg_r, bg_g, bg_b, bg_a);
    Draw.draw_rect(this.x - 1, this.y, this.width + 1, this.height, bd_r, bd_g, bd_b, bd_a, this.border_size, "left-right");
    Draw.draw_string_shadow(this.name, this.width / 2 - this.mc.field_71466_p.func_78256_a(this.name) / 2 + this.x, this.y + 4, nc_r, nc_g, nc_b, nc_a);
    if (is_moving())
      crush(paramInt1, paramInt2); 
    for (PinnableButton pinnableButton : this.pinnable_button) {
      pinnableButton.set_x(this.x + paramInt3);
      pinnableButton.render(paramInt1, paramInt2, paramInt3);
      if (!pinnableButton.motion(paramInt1, paramInt2))
        continue; 
      Draw.draw_rect(get_x() - 1, pinnableButton.get_save_y(), get_width() + 1, pinnableButton.get_height(), bdw_r, bdw_g, bdw_b, bdw_a, this.border_size, "right-left");
    } 
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (PinnableButton pinnableButton : this.pinnable_button)
      pinnableButton.click(paramInt1, paramInt2, paramInt3); 
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public void set_move(boolean paramBoolean) {
    (give up)null;
    this.move = paramBoolean;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
}
