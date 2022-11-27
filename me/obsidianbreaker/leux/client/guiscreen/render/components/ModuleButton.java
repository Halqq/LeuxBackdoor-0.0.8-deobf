package me.obsidianbreaker.leux.client.guiscreen.render.components;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.components.widgets.Button;
import me.obsidianbreaker.leux.client.guiscreen.render.components.widgets.ButtonBind;
import me.obsidianbreaker.leux.client.guiscreen.render.components.widgets.Combobox;
import me.obsidianbreaker.leux.client.guiscreen.render.components.widgets.Label;
import me.obsidianbreaker.leux.client.guiscreen.render.components.widgets.Slider;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.TurokMath;
import me.obsidianbreaker.leux.client.turok.TurokRect;
import me.obsidianbreaker.leux.client.turok.TurokTick;
import me.obsidianbreaker.leux.client.turok.mouse.TurokMouse;

public class ModuleButton {
  public ArrayList widget;
  
  public int master_height_cache;
  
  public int border_size = 1;
  
  public int settings_height;
  
  public int border_a = 200;
  
  public boolean opened;
  
  public int opened_height;
  
  public int save_y;
  
  public int count;
  
  public int y;
  
  public Frame master;
  
  public int height;
  
  public int width;
  
  public int x;
  
  public TurokTick tick_height_animation = new TurokTick();
  
  public Module module;
  
  public String module_name;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.09249306E9F ^ 0x7E9E1EDB));
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public void set_open(boolean paramBoolean) {
    paramBoolean = false;
    (give up)null;
    this.opened = paramBoolean;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_save_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_save_y() + get_height());
  }
  
  public boolean get_state() {
    (give up)null;
    return this.module.is_active();
  }
  
  public void does_widgets_can(boolean paramBoolean) {
    (give up)null;
    for (AbstractWidget abstractWidget : this.widget)
      abstractWidget.does_can(paramBoolean); 
  }
  
  public void bind(char paramChar, int paramInt) {
    (give up)null;
    for (AbstractWidget abstractWidget : this.widget)
      abstractWidget.bind(paramChar, paramInt); 
  }
  
  public int get_settings_height() {
    (give up)null;
    return this.settings_height;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public void button_release(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (AbstractWidget abstractWidget : this.widget)
      abstractWidget.release(paramInt1, paramInt2, paramInt3); 
    this.master.does_can(true);
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public int get_save_y() {
    (give up)null;
    return this.save_y;
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (AbstractWidget abstractWidget : this.widget)
      abstractWidget.mouse(paramInt1, paramInt2, paramInt3); 
    if (motion(paramInt1, paramInt2)) {
      this.master.does_can(false);
      set_pressed(!get_state());
    } 
    if (paramInt3 == 1 && motion(paramInt1, paramInt2)) {
      this.master.does_can(false);
      set_open(!is_open());
      this.master.refresh_frame(this, 0);
    } 
  }
  
  public void render(int paramInt1, int paramInt2, int paramInt3) {
    paramInt3 = 2;
    (give up)null;
    set_width(this.master.get_width() - paramInt3);
    this.save_y = this.master.get_y() + this.y;
    int i = Leux.click_gui.theme_widget_name_r;
    int j = Leux.click_gui.theme_widget_name_g;
    int k = Leux.click_gui.theme_widget_name_b;
    int m = Leux.click_gui.theme_widget_name_a;
    int n = Leux.click_gui.theme_widget_background_r;
    int i1 = Leux.click_gui.theme_widget_background_g;
    int i2 = Leux.click_gui.theme_widget_background_b;
    int i3 = Leux.click_gui.theme_widget_background_a;
    int i4 = Leux.click_gui.theme_widget_border_r;
    int i5 = Leux.click_gui.theme_widget_border_g;
    int i6 = Leux.click_gui.theme_widget_border_b;
    if (Leux.get_setting_manager().get_setting_with_tag("Settings", "Shadow").get_value(true)) {
      if (this.module.is_active()) {
        Draw.draw_rect(this.x, this.save_y, this.x + this.width - paramInt3, this.save_y + this.height, n, i1, i2, i3);
        Draw.draw_string_shadow(this.module_name, this.x + paramInt3, this.save_y + 2, i, j, k, m);
        if (is_open()) {
          Draw.draw_string_shadow("\\/", this.x + paramInt3 + this.width - 4 - this.font.get_string_width("\\/") - 1, this.save_y + 2, i, j, k, m);
        } else {
          Draw.draw_string_shadow(">", this.x + paramInt3 + this.width - 4 - this.font.get_string_width(">") - 1, this.save_y + 2, i, j, k, m);
        } 
      } else {
        Draw.draw_string_shadow(this.module_name, this.x + paramInt3, this.save_y + 2, i, j, k, m);
        if (is_open()) {
          Draw.draw_string_shadow("\\/", this.x + paramInt3 + this.width - 4 - this.font.get_string_width("\\/") - 1, this.save_y + 2, i, j, k, m);
        } else {
          Draw.draw_string_shadow(">", this.x + paramInt3 + this.width - 4 - this.font.get_string_width(">") - 1, this.save_y + 2, i, j, k, m);
        } 
      } 
    } else if (this.module.is_active()) {
      Draw.draw_rect(this.x, this.save_y, this.x + this.width - paramInt3, this.save_y + this.height, n, i1, i2, i3);
      Draw.draw_string(this.module_name, this.x + paramInt3, this.save_y + 2, i, j, k, m);
      if (is_open()) {
        Draw.draw_string("\\/", this.x + paramInt3 + this.width - 4 - this.font.get_string_width("\\/") - 1, this.save_y + 2, i, j, k, m);
      } else {
        Draw.draw_string(">", this.x + paramInt3 + this.width - 4 - this.font.get_string_width(">") - 1, this.save_y + 2, i, j, k, m);
      } 
    } else {
      Draw.draw_string(this.module_name, this.x + paramInt3, this.save_y + 2, i, j, k, m);
      if (is_open()) {
        Draw.draw_string("\\/", this.x + paramInt3 + this.width - 4 - this.font.get_string_width("\\/") - 1, this.save_y + 2, i, j, k, m);
      } else {
        Draw.draw_string(">", this.x + paramInt3 + this.width - 4 - this.font.get_string_width(">") - 1, this.save_y + 2, i, j, k, m);
      } 
    } 
    if ((new TurokRect(this.x + paramInt3, this.save_y, this.width - paramInt3 * 2, this.opened_height)).collideWithMouse(new TurokMouse(paramInt1, paramInt2))) {
      int i7 = TurokMath.clamp(this.tick_height_animation.getCurrentTicksCount(5.0D), 0, this.opened_height);
      Draw.draw_rect(this.master.get_x() - 1, this.save_y, this.master.get_width() + 1, i7, i4, i5, i6, this.border_a, this.border_size, "right-left");
      if (Leux.get_setting_manager().get_setting_with_tag("Settings", "Shadow").get_value(true)) {
        Draw.draw_string_shadow(this.module.get_description(), 1, 1, i, j, k, 255);
      } else {
        Draw.draw_string(this.module.get_description(), 1, 1, i, j, k, 255);
      } 
    } else {
      this.tick_height_animation.reset();
    } 
    for (AbstractWidget abstractWidget : this.widget) {
      abstractWidget.set_x(get_x());
      if (this.opened) {
        this.opened_height = this.height + this.settings_height - this.height;
        abstractWidget.render(get_save_y(), paramInt3, paramInt1, paramInt2);
        continue;
      } 
      this.opened_height = this.height;
    } 
  }
  
  public ModuleButton(Module paramModule, Frame paramFrame) {
    this.module = paramModule;
    this.master = paramFrame;
    this.widget = new ArrayList();
    this.module_name = paramModule.get_name();
    this.x = 0;
    this.y = 0;
    this.width = this.font.get_string_width(paramModule.get_name()) + 5;
    this.opened_height = this.height = 2 + this.font.get_string_height() + 2;
    this.save_y = 0;
    this.opened = false;
    this.master_height_cache = paramFrame.get_height();
    this.settings_height = this.y + this.height + 1;
    this.count = 0;
    for (Setting setting : Leux.get_setting_manager().get_settings_with_hack(paramModule)) {
      if (setting.get_type().equals("button")) {
        this.widget.add(new Button(paramFrame, this, setting.get_tag(), this.settings_height));
        this.settings_height += 10;
        this.count++;
      } 
      if (setting.get_type().equals("combobox")) {
        this.widget.add(new Combobox(paramFrame, this, setting.get_tag(), this.settings_height));
        this.settings_height += 10;
        this.count++;
      } 
      if (setting.get_type().equals("label")) {
        this.widget.add(new Label(paramFrame, this, setting.get_tag(), this.settings_height));
        this.settings_height += 10;
        this.count++;
      } 
      if (!setting.get_type().equals("doubleslider") && !setting.get_type().equals("integerslider"))
        continue; 
      this.widget.add(new Slider(paramFrame, this, setting.get_tag(), this.settings_height));
      this.settings_height += 10;
      this.count++;
    } 
    int i = Leux.get_setting_manager().get_settings_with_hack(paramModule).size();
    if (this.count >= i) {
      this.widget.add(new ButtonBind(paramFrame, this, "bind", this.settings_height));
      this.settings_height += 10;
    } 
  }
  
  public boolean is_binding() {
    (give up)null;
    boolean bool = false;
    for (AbstractWidget abstractWidget : this.widget) {
      if (!abstractWidget.is_binding())
        continue; 
      bool = true;
    } 
    return bool;
  }
  
  public Frame get_master() {
    (give up)null;
    return this.master;
  }
  
  public Module get_module() {
    (give up)null;
    return this.module;
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public boolean is_open() {
    (give up)null;
    return this.opened;
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
  
  public int get_cache_height() {
    (give up)null;
    return this.master_height_cache;
  }
  
  public void set_pressed(boolean paramBoolean) {
    paramBoolean = false;
    (give up)null;
    this.module.set_active(paramBoolean);
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
}
