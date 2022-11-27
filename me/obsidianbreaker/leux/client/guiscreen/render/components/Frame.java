package me.obsidianbreaker.leux.client.guiscreen.render.components;

import give up;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.client.Minecraft;

public class Frame {
  public String frame_tag;
  
  public int height = 2 + this.font.get_string_height() + 2 + 1;
  
  public Category category;
  
  public boolean first = false;
  
  public int x = 10;
  
  public int width_abs;
  
  public Draw font = new Draw(Float.intBitsToFloat(1.10016397E9F ^ 0x7E132B8B));
  
  public Minecraft mc = Minecraft.func_71410_x();
  
  public boolean move;
  
  public String frame_name;
  
  public boolean can;
  
  public ArrayList module_button;
  
  public int move_y;
  
  public int move_x;
  
  public int width = 100;
  
  public int width_name;
  
  public int y = 10;
  
  public void render(int paramInt1, int paramInt2) {
    (give up)null;
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int i = Color.HSBtoRGB(arrayOfFloat[0], 1.0F, 1.0F);
    boolean bool1 = (i <= 50) ? true : Math.min(i, 120);
    int j = Leux.click_gui.theme_frame_name_r;
    int k = Leux.click_gui.theme_frame_name_g;
    int m = Leux.click_gui.theme_frame_name_b;
    int n = Leux.click_gui.theme_frame_name_a;
    int i1 = Leux.click_gui.theme_frame_background_r;
    int i2 = Leux.click_gui.theme_frame_background_g;
    int i3 = Leux.click_gui.theme_frame_background_b;
    int i4 = Leux.click_gui.theme_frame_background_a;
    int i5 = Leux.click_gui.theme_frame_border_r;
    int i6 = Leux.click_gui.theme_frame_border_g;
    int i7 = Leux.click_gui.theme_frame_border_b;
    boolean bool2 = bool1;
    this.frame_name = this.category.get_name();
    this.width_name = this.font.get_string_width(this.category.get_name());
    Draw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, i1, i2, i3, i4);
    boolean bool3 = true;
    Draw.draw_rect(this.x - 1, this.y, this.width + 1, this.height, i5, i6, i7, bool2, bool3, "left-right");
    Draw.draw_string_shadow(this.frame_name, this.width / 2 - this.mc.field_71466_p.func_78256_a(this.frame_name) / 2 + this.x, this.y + 4, j, k, m, n);
    if (is_moving())
      crush(paramInt1, paramInt2); 
    for (ModuleButton moduleButton : this.module_button) {
      moduleButton.set_x(this.x + 2);
      moduleButton.render(paramInt1, paramInt2, 2);
    } 
    arrayOfFloat[0] = arrayOfFloat[0] + 1.0F;
  }
  
  public int get_height() {
    (give up)null;
    return this.height;
  }
  
  public int get_x() {
    (give up)null;
    return this.x;
  }
  
  public void does_can(boolean paramBoolean) {
    (give up)null;
    this.can = paramBoolean;
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
  
  public void mouse_release(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (ModuleButton moduleButton : this.module_button)
      moduleButton.button_release(paramInt1, paramInt2, paramInt3); 
  }
  
  public String get_name() {
    (give up)null;
    return this.frame_name;
  }
  
  public void set_height(int paramInt) {
    (give up)null;
    this.height = paramInt;
  }
  
  public void does_button_for_do_widgets_can(boolean paramBoolean) {
    (give up)null;
    for (ModuleButton moduleButton : this.module_button)
      moduleButton.does_widgets_can(paramBoolean); 
  }
  
  public void refresh_frame(ModuleButton paramModuleButton, int paramInt) {
    paramInt = 0;
    (give up)null;
    this.height = 2 + this.font.get_string_height() + 2 + 1;
    Leux.get_hack_manager().get_modules_with_category(this.category).size();
    false;
    for (ModuleButton moduleButton : this.module_button) {
      moduleButton.set_y(this.height);
      if (moduleButton.is_open()) {
        this.height += moduleButton.get_settings_height() + 1;
        continue;
      } 
      this.height += moduleButton.get_height() + 1;
    } 
  }
  
  public void mouse(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (ModuleButton moduleButton : this.module_button)
      moduleButton.mouse(paramInt1, paramInt2, paramInt3); 
  }
  
  public void set_width(int paramInt) {
    (give up)null;
    this.width = paramInt;
  }
  
  public Frame(Category paramCategory) {
    this.category = paramCategory;
    this.module_button = new ArrayList();
    this.width_abs = this.width_name = this.font.get_string_width(this.category.get_name());
    this.frame_name = paramCategory.get_name();
    this.frame_tag = paramCategory.get_tag();
    this.move_x = 0;
    this.move_y = 0;
    Leux.get_hack_manager().get_modules_with_category(paramCategory).size();
    false;
    for (Module module : Leux.get_hack_manager().get_modules_with_category(paramCategory)) {
      ModuleButton moduleButton = new ModuleButton(module, this);
      moduleButton.set_y(this.height);
      this.module_button.add(moduleButton);
      this.height += moduleButton.get_height() + 1;
    } 
    this.move = false;
    this.can = true;
  }
  
  public void set_y(int paramInt) {
    (give up)null;
    this.y = paramInt;
  }
  
  public boolean is_moving() {
    (give up)null;
    return this.move;
  }
  
  public void set_move_y(int paramInt) {
    (give up)null;
    this.move_y = paramInt;
  }
  
  public void bind(char paramChar, int paramInt) {
    (give up)null;
    for (ModuleButton moduleButton : this.module_button)
      moduleButton.bind(paramChar, paramInt); 
  }
  
  public boolean can() {
    (give up)null;
    return this.can;
  }
  
  public void set_move(boolean paramBoolean) {
    (give up)null;
    this.move = paramBoolean;
  }
  
  public boolean motion(int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_y() + get_height());
  }
  
  public void set_x(int paramInt) {
    (give up)null;
    this.x = paramInt;
  }
  
  public boolean is_binding() {
    (give up)null;
    boolean bool = false;
    Iterator<ModuleButton> iterator = this.module_button.iterator();
    while (true) {
      if (iterator.hasNext()) {
        ModuleButton moduleButton = iterator.next();
        if (!moduleButton.is_binding())
          continue; 
      } else {
        return bool;
      } 
      bool = true;
    } 
  }
  
  public int get_width() {
    (give up)null;
    return this.width;
  }
  
  public void set_move_x(int paramInt) {
    (give up)null;
    this.move_x = paramInt;
  }
  
  public boolean motion(String paramString, int paramInt1, int paramInt2) {
    (give up)null;
    return (paramInt1 >= get_x() && paramInt2 >= get_y() && paramInt1 <= get_x() + get_width() && paramInt2 <= get_y() + this.font.get_string_height());
  }
  
  public String get_tag() {
    (give up)null;
    return this.frame_tag;
  }
  
  public int get_y() {
    (give up)null;
    return this.y;
  }
}
