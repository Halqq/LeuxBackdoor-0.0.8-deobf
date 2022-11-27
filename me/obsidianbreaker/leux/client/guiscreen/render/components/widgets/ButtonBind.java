package me.obsidianbreaker.leux.client.guiscreen.render.components.widgets;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.components.AbstractWidget;
import me.obsidianbreaker.leux.client.guiscreen.render.components.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.components.ModuleButton;

public class ButtonBind extends AbstractWidget {
  public int x;
  
  public boolean can;
  
  public Frame frame;
  
  public int width;
  
  public String points;
  
  public boolean waiting;
  
  public float tick;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.08320128E9F ^ 0x7F1056CF));
  
  public String button_name;
  
  public int height;
  
  public ModuleButton master;
  
  public int save_y;
  
  public int border_size = 0;
  
  public int y;
  
  public void does_can(boolean paramBoolean) {
    (give up)null;
    this.can = paramBoolean;
  }
  
  public boolean motion_pass(int paramInt1, int paramInt2) {
    (give up)null;
    return motion(paramInt1, paramInt2);
  }
  
  public boolean can() {
    (give up)null;
    return this.can;
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_save_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_save_y() + get_height());
  }
  
  public int get_save_y() {
    (give up)null;
    return this.save_y;
  }
  
  public boolean is_binding() {
    (give up)null;
    return this.waiting;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public ButtonBind(Frame paramFrame, ModuleButton paramModuleButton, String paramString, int paramInt) {
    this.frame = paramFrame;
    this.master = paramModuleButton;
    this.x = paramModuleButton.get_x();
    this.y = paramInt;
    this.save_y = this.y;
    this.width = paramModuleButton.get_width();
    this.height = this.font.get_string_height();
    this.button_name = paramString;
    this.can = true;
    this.points = ".";
    this.tick = Float.intBitsToFloat(2.10974963E9F ^ 0x7DC03977);
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public void bind(char paramChar, int paramInt) {
    (give up)null;
    if (this.waiting) {
      switch (paramInt) {
        case 1:
          this.waiting = false;
          return;
        case 211:
          this.master.get_module().set_bind(0);
          this.waiting = false;
          return;
      } 
      this.master.get_module().set_bind(paramInt);
      this.waiting = false;
    } 
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    if (motion(paramInt1, paramInt2) && this.master.is_open() && can()) {
      this.frame.does_can(false);
      this.waiting = true;
    } 
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    set_width(this.master.get_width() - paramInt2);
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % (-2142819597L ^ 0xFFFFFFFF804707F3L)) / Float.intBitsToFloat(1.00199194E9F ^ 0x7D8D2EE7) };
    int i = Color.HSBtoRGB(arrayOfFloat[0], Float.intBitsToFloat(1.09333939E9F ^ 0x7EAB08F1), Float.intBitsToFloat(1.10554701E9F ^ 0x7E654F03));
    if (i <= 100) {
      100;
    } else if (i >= 200) {
      'È';
    } else {
    
    } 
    if (this.waiting) {
      if (this.tick >= Float.intBitsToFloat(1.04534982E9F ^ 0x7F3EC5C8))
        this.points = ".."; 
      if (this.tick >= Float.intBitsToFloat(1.05427379E9F ^ 0x7F26F0ED))
        this.points = "..."; 
      if (this.tick >= Float.intBitsToFloat(1.00893613E9F ^ 0x7E1724CF)) {
        this.points = ".";
        this.tick = Float.intBitsToFloat(2.13274074E9F ^ 0x7F1F0A67);
      } 
    } 
    true;
    this.save_y = this.y + paramInt1;
    int j = Leux.click_gui.theme_widget_name_r;
    int k = Leux.click_gui.theme_widget_name_g;
    int m = Leux.click_gui.theme_widget_name_b;
    int n = Leux.click_gui.theme_widget_name_a;
    int i1 = Leux.click_gui.theme_widget_background_r;
    int i2 = Leux.click_gui.theme_widget_background_g;
    int i3 = Leux.click_gui.theme_widget_background_b;
    int i4 = Leux.click_gui.theme_widget_background_a;
    if (this.waiting) {
      Draw.draw_rect(get_x(), this.save_y, get_x() + this.width, this.save_y + this.height, i1, i2, i3, i4);
      this.tick += Float.intBitsToFloat(1.09841062E9F ^ 0x7E786A6B);
      Draw.draw_string_shadow("Listening " + this.points, this.x + 2, this.save_y, j, k, m, n);
    } else {
      Draw.draw_string_shadow("Bind <" + this.master.get_module().get_bind("string") + ">", this.x + 2, this.save_y, j, k, m, n);
    } 
    arrayOfFloat[0] = arrayOfFloat[0] + Float.intBitsToFloat(1.04820307E9F ^ 0x7EDA4F5F);
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
}
