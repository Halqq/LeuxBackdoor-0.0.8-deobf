package me.obsidianbreaker.leux.client.guiscreen.render.components.widgets;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.components.AbstractWidget;
import me.obsidianbreaker.leux.client.guiscreen.render.components.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.components.ModuleButton;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.turok.values.TurokDouble;

public class Slider extends AbstractWidget {
  public int save_y;
  
  public Setting setting;
  
  public boolean click;
  
  public double double_;
  
  public int intenger;
  
  public boolean compare;
  
  public int y;
  
  public int width;
  
  public int height;
  
  public ModuleButton master;
  
  public int x;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.10637402E9F ^ 0x7E71ED47));
  
  public int border_size = 0;
  
  public boolean can;
  
  public String slider_name;
  
  public Frame frame;
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    if (motion(paramInt1, paramInt2) && this.master.is_open() && can()) {
      this.frame.does_can(false);
      this.click = true;
    } 
  }
  
  public void release(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    this.click = false;
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    set_width(this.master.get_width() - paramInt2);
    this.save_y = this.y + paramInt1;
    int i = Leux.click_gui.theme_widget_name_r;
    int j = Leux.click_gui.theme_widget_name_g;
    int k = Leux.click_gui.theme_widget_name_b;
    int m = Leux.click_gui.theme_widget_name_b;
    int n = Leux.click_gui.theme_widget_background_r;
    int i1 = Leux.click_gui.theme_widget_background_g;
    int i2 = Leux.click_gui.theme_widget_background_b;
    int i3 = Leux.click_gui.theme_widget_background_a;
    100;
    if (this.double_ != 8192.0D && this.intenger == 8192)
      this.compare = false; 
    if (this.double_ == 8192.0D && this.intenger != 8192)
      this.compare = true; 
    double d = Math.min(this.width, Math.max(0, paramInt3 - get_x()));
    if (this.click)
      if (d != 0.0D) {
        this.setting.set_value(TurokDouble.round(d / this.width * (this.setting.get_max(1.0D) - this.setting.get_min(1.0D)) + this.setting.get_min(1.0D)));
      } else {
        this.setting.set_value(this.setting.get_min(1.0D));
      }  
    String str = !this.compare ? Double.toString(this.setting.get_value(this.double_)) : Integer.toString(this.setting.get_value(this.intenger));
    Draw.draw_rect(this.x, this.save_y, this.x + this.width * (this.setting.get_value(1) - this.setting.get_min(1)) / (this.setting.get_max(1) - this.setting.get_min(1)), this.save_y + this.height, n, i1, i2, i3);
    Draw.draw_string_shadow(this.slider_name, this.x + 2, this.save_y, i, j, k, m);
    Draw.draw_string_shadow(str, this.x + this.width - paramInt2 - this.font.get_string_width(str) + 2, this.save_y, i, j, k, m);
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public boolean motion_pass(int paramInt1, int paramInt2) {
    (give up)null;
    return motion(paramInt1, paramInt2);
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public int get_save_y() {
    (give up)null;
    return this.save_y;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public Setting get_setting() {
    (give up)null;
    return this.setting;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public Slider(Frame paramFrame, ModuleButton paramModuleButton, String paramString, int paramInt) {
    this.frame = paramFrame;
    this.master = paramModuleButton;
    this.setting = Leux.get_setting_manager().get_setting_with_tag(paramModuleButton.get_module(), paramString);
    this.x = paramModuleButton.get_x();
    this.y = paramInt;
    this.save_y = this.y;
    this.width = paramModuleButton.get_width();
    this.height = this.font.get_string_height();
    this.slider_name = this.setting.get_name();
    this.can = true;
    this.double_ = 8192.0D;
    this.intenger = 8192;
    if (this.setting.get_type().equals("doubleslider")) {
      this.double_ = this.setting.get_value(1.0D);
    } else if (this.setting.get_type().equals("integerslider")) {
      this.intenger = this.setting.get_value(1);
    } 
  }
  
  public void does_can(boolean paramBoolean) {
    (give up)null;
    this.can = paramBoolean;
  }
  
  public boolean can() {
    (give up)null;
    return this.can;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_save_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_save_y() + get_height());
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
}
