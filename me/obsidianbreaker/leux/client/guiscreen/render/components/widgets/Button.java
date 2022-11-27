package me.obsidianbreaker.leux.client.guiscreen.render.components.widgets;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.components.AbstractWidget;
import me.obsidianbreaker.leux.client.guiscreen.render.components.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.components.ModuleButton;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;

public class Button extends AbstractWidget {
  public Draw font = new Draw(Float.intBitsToFloat(1.08908838E9F ^ 0x7F6A2B95));
  
  public int y;
  
  public Frame frame;
  
  public int width;
  
  public int x;
  
  public int save_y;
  
  public int border_size = 0;
  
  public String button_name;
  
  public int height;
  
  public Setting setting;
  
  public boolean can;
  
  public ModuleButton master;
  
  public Button(Frame paramFrame, ModuleButton paramModuleButton, String paramString, int paramInt) {
    this.frame = paramFrame;
    this.master = paramModuleButton;
    this.setting = Leux.get_setting_manager().get_setting_with_tag(paramModuleButton.get_module(), paramString);
    this.x = paramModuleButton.get_x();
    this.y = paramInt;
    this.save_y = this.y;
    this.width = paramModuleButton.get_width();
    this.height = this.font.get_string_height();
    this.button_name = this.setting.get_name();
    this.can = true;
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    set_width(this.master.get_width() - paramInt2);
    this.save_y = this.y + paramInt1;
    int i = Leux.click_gui.theme_widget_name_r;
    int j = Leux.click_gui.theme_widget_name_g;
    int k = Leux.click_gui.theme_widget_name_b;
    int m = Leux.click_gui.theme_widget_name_a;
    int n = Leux.click_gui.theme_widget_background_r;
    int i1 = Leux.click_gui.theme_widget_background_g;
    int i2 = Leux.click_gui.theme_widget_background_b;
    int i3 = Leux.click_gui.theme_widget_background_a;
    if (this.setting.get_value(true))
      Draw.draw_rect(get_x(), this.save_y, get_x() + this.width, this.save_y + this.height, n, i1, i2, i3); 
    Draw.draw_string_shadow(this.button_name, this.x + 2, this.save_y, i, j, k, m);
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_save_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_save_y() + get_height());
  }
  
  public boolean can() {
    (give up)null;
    return this.can;
  }
  
  public Setting get_setting() {
    (give up)null;
    return this.setting;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void does_can(boolean paramBoolean) {
    (give up)null;
    this.can = paramBoolean;
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    if (motion(paramInt1, paramInt2) && this.master.is_open() && can()) {
      this.frame.does_can(false);
      this.setting.set_value(!this.setting.get_value(true));
    } 
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public int get_save_y() {
    (give up)null;
    return this.save_y;
  }
  
  public boolean motion_pass(int paramInt1, int paramInt2) {
    (give up)null;
    return motion(paramInt1, paramInt2);
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
}
