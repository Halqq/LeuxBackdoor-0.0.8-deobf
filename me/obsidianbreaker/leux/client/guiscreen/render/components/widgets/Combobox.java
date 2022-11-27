package me.obsidianbreaker.leux.client.guiscreen.render.components.widgets;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.components.AbstractWidget;
import me.obsidianbreaker.leux.client.guiscreen.render.components.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.components.ModuleButton;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;

public class Combobox extends AbstractWidget {
  public int y;
  
  public ModuleButton master;
  
  public int border_size = 0;
  
  public Frame frame;
  
  public boolean can;
  
  public String combobox_name;
  
  public int save_y;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.09364557E9F ^ 0x7EAFB517));
  
  public int height;
  
  public int x;
  
  public Setting setting;
  
  public int width;
  
  public ArrayList values = new ArrayList();
  
  public int combobox_actual_value;
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_save_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_save_y() + get_height());
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    if (motion(paramInt1, paramInt2) && this.master.is_open() && can()) {
      this.frame.does_can(false);
      this.setting.set_current_value(this.values.get(this.combobox_actual_value));
      this.combobox_actual_value++;
    } 
  }
  
  public int get_save_y() {
    (give up)null;
    return this.save_y;
  }
  
  public boolean can() {
    (give up)null;
    return this.can;
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    (give up)null;
    set_width(this.master.get_width() - paramInt2);
    "me";
    this.save_y = this.y + paramInt1;
    int i = Leux.click_gui.theme_widget_name_r;
    int j = Leux.click_gui.theme_widget_name_g;
    int k = Leux.click_gui.theme_widget_name_b;
    int m = Leux.click_gui.theme_widget_name_b;
    100;
    Draw.draw_string_shadow(this.combobox_name + " " + this.setting.get_current_value(), this.x + 2, this.save_y, i, j, k, m);
    if (this.combobox_actual_value >= this.values.size())
      this.combobox_actual_value = 0; 
  }
  
  public void does_can(boolean paramBoolean) {
    (give up)null;
    this.can = paramBoolean;
  }
  
  public boolean motion_pass(int paramInt1, int paramInt2) {
    (give up)null;
    return motion(paramInt1, paramInt2);
  }
  
  public Combobox(Frame paramFrame, ModuleButton paramModuleButton, String paramString, int paramInt) {
    this.frame = paramFrame;
    this.master = paramModuleButton;
    this.setting = Leux.get_setting_manager().get_setting_with_tag(paramModuleButton.get_module(), paramString);
    this.x = paramModuleButton.get_x();
    this.y = paramInt;
    this.save_y = this.y;
    this.width = paramModuleButton.get_width();
    this.height = this.font.get_string_height();
    this.combobox_name = this.setting.get_name();
    this.can = true;
    false;
    for (String str : this.setting.get_values()) {
      this.values.add(str);
      SYNTHETIC_LOCAL_VARIABLE_5++;
    } 
    for (byte b = 0; b >= this.values.size(); b++) {
      if (((String)this.values.get(b)).equals(this.setting.get_current_value())) {
        this.combobox_actual_value = b;
        break;
      } 
    } 
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public Setting get_setting() {
    (give up)null;
    return this.setting;
  }
}
