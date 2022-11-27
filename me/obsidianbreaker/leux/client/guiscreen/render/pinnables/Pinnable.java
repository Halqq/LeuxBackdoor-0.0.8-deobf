package me.obsidianbreaker.leux.client.guiscreen.render.pinnables;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Pinnable {
  public int y;
  
  public int move_x;
  
  public Minecraft mc = Minecraft.func_71410_x();
  
  public Draw font;
  
  public boolean dock = true;
  
  public int x;
  
  public String title;
  
  public int width;
  
  public boolean move;
  
  public String tag;
  
  public int height;
  
  public int move_y;
  
  public boolean state;
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_y() + get_height());
  }
  
  public int docking(int paramInt, String paramString) {
    (give up)null;
    return this.dock ? paramInt : (this.width - get(paramString, "width") - paramInt);
  }
  
  public void create_line(String paramString, int paramInt1, int paramInt2) {
    (give up)null;
    Draw.draw_string_shadow(paramString, this.x + paramInt1, this.y + paramInt2, 255, 255, 255, 255);
  }
  
  public int get(String paramString1, String paramString2) {
    paramString2 = "width";
    (give up)null;
    int i = 0;
    if (paramString2.equals("width"))
      i = this.font.get_string_width(paramString1); 
    if (paramString2.equals("height"))
      i = this.font.get_string_height(); 
    return i;
  }
  
  public void create_rect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    (give up)null;
    Draw.draw_rect(this.x + paramInt1, this.y + paramInt2, this.x + paramInt3, this.y + paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3) {
    paramInt3 = 0;
    (give up)null;
    if (is_moving())
      crush(paramInt1, paramInt2); 
    if (this.x + this.width <= this.mc.field_71443_c / 2 / 2) {
      set_dock(true);
    } else if (this.x + this.width >= this.mc.field_71443_c / 2 / 2) {
      set_dock(false);
    } 
    if (is_active()) {
      render();
      GL11.glPushMatrix();
      GL11.glEnable(3553);
      GL11.glEnable(3042);
      GlStateManager.func_179147_l();
      GL11.glPopMatrix();
      RenderHelp.release_gl();
      if (motion(paramInt1, paramInt2))
        Draw.draw_rect(this.x - 1, this.y - 1, this.width + 1, this.height + 1, 0, 0, 0, 90, 2, "right-left-down-up"); 
    } 
  }
  
  public void set_active(boolean paramBoolean) {
    (give up)null;
    this.state = paramBoolean;
  }
  
  public void create_line(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    (give up)null;
    Draw.draw_string_shadow(paramString, this.x + paramInt1, this.y + paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public boolean is_moving() {
    (give up)null;
    return this.move;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public String get_title() {
    (give up)null;
    return this.title;
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
  
  public boolean is_active() {
    (give up)null;
    return this.state;
  }
  
  public void set_move_y(int paramInt) {
    (give up)null;
    this.move_y = paramInt;
  }
  
  public void set_move_x(int paramInt) {
    (give up)null;
    this.move_x = paramInt;
  }
  
  public void set_move(boolean paramBoolean) {
    (give up)null;
    this.move = paramBoolean;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public String get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public void release(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    set_move(false);
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public void click(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    if (is_active() && motion(paramInt1, paramInt2)) {
      set_move(true);
      set_move_x(paramInt1 - get_x());
      set_move_y(paramInt2 - get_y());
    } 
  }
  
  public boolean is_on_gui() {
    (give up)null;
    return Leux.click_hud.on_gui;
  }
  
  public Pinnable(String paramString1, String paramString2, float paramFloat, int paramInt1, int paramInt2) {
    this.title = paramString1;
    this.tag = paramString2;
    this.font = new Draw(paramFloat);
    this.x = paramInt1;
    this.y = paramInt2;
    this.width = 1;
    this.height = 10;
    this.move = false;
  }
  
  public void render() {
    (give up)null;
  }
  
  public boolean get_dock() {
    (give up)null;
    return this.dock;
  }
  
  public void set_dock(boolean paramBoolean) {
    (give up)null;
    this.dock = paramBoolean;
  }
  
  public int get_title_height() {
    (give up)null;
    return this.font.get_string_height();
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
}
