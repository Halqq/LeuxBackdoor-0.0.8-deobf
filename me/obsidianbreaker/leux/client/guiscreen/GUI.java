package me.obsidianbreaker.leux.client.guiscreen;

import give up;
import java.io.IOException;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.components.Frame;
import me.obsidianbreaker.leux.client.modules.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

public class GUI extends GuiScreen {
  public int theme_widget_name_g = 0;
  
  public int theme_widget_background_g = 0;
  
  public int theme_widget_background_b = 0;
  
  public Minecraft mc = Minecraft.func_71410_x();
  
  public int theme_widget_border_r = 0;
  
  public int theme_frame_border_g = 0;
  
  public int theme_frame_name_g = 0;
  
  public int theme_widget_name_a = 0;
  
  public int theme_frame_background_r = 0;
  
  public boolean event_start = true;
  
  public int frame_x = 10;
  
  public Frame current;
  
  public int theme_frame_name_r = 0;
  
  public float partial_ticks;
  
  public int theme_frame_background_b = 0;
  
  public boolean event_finish = false;
  
  public int theme_frame_border_b = 0;
  
  public int theme_frame_name_b = 0;
  
  public int theme_frame_background_g = 0;
  
  public int theme_widget_background_r = 0;
  
  public int theme_widget_name_r = 0;
  
  public int theme_widget_border_b = 0;
  
  public int theme_frame_name_a = 0;
  
  public int theme_widget_border_g = 0;
  
  public int theme_frame_background_a = 0;
  
  public ArrayList frame = new ArrayList();
  
  public int theme_frame_border_r = 0;
  
  public int theme_widget_background_a = 0;
  
  public int theme_widget_name_b = 0;
  
  public void func_73864_a(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (Frame frame : this.frame) {
      frame.mouse(paramInt1, paramInt2, paramInt3);
      if (!frame.motion(paramInt1, paramInt2) || !frame.can())
        continue; 
      frame.does_button_for_do_widgets_can(false);
      this.current = frame;
      this.current.set_move(true);
      this.current.set_move_x(paramInt1 - this.current.get_x());
      this.current.set_move_y(paramInt2 - this.current.get_y());
    } 
  }
  
  public Frame get_frame_with_tag(String paramString) {
    (give up)null;
    Frame frame = null;
    for (Frame frame1 : get_array_frames()) {
      if (!frame1.get_tag().equals(paramString))
        continue; 
      frame = frame1;
    } 
    return frame;
  }
  
  public void func_146274_d() throws IOException {
    (give up)null;
    if (Mouse.getEventDWheel() > 0)
      for (Frame frame : get_array_frames())
        frame.set_y(frame.get_y() + 10);  
    if (Mouse.getEventDWheel() < 0)
      for (Frame frame : get_array_frames())
        frame.set_y(frame.get_y() - 10);  
    super.func_146274_d();
  }
  
  public Frame get_current() {
    (give up)null;
    return this.current;
  }
  
  public void func_146286_b(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    for (Frame frame : this.frame) {
      frame.does_button_for_do_widgets_can(true);
      frame.mouse_release(paramInt1, paramInt2, paramInt3);
      frame.set_move(false);
    } 
    set_current(this.current);
  }
  
  public boolean func_73868_f() {
    (give up)null;
    return false;
  }
  
  public void func_73863_a(int paramInt1, int paramInt2, float paramFloat) {
    (give up)null;
    func_146276_q_();
    for (Frame frame : this.frame)
      frame.render(paramInt1, paramInt2); 
    this.partial_ticks = paramFloat;
  }
  
  public ArrayList get_array_frames() {
    (give up)null;
    return this.frame;
  }
  
  public void func_73869_a(char paramChar, int paramInt) {
    (give up)null;
    for (Frame frame : this.frame) {
      frame.bind(paramChar, paramInt);
      if (paramInt == 1 && !frame.is_binding())
        this.mc.func_147108_a(null); 
      if (paramInt == 208 || paramInt == 200)
        frame.set_y(frame.get_y() - 1); 
      if (paramInt != 200 && paramInt != 208)
        continue; 
      frame.set_y(frame.get_y() + 1);
    } 
  }
  
  public GUI() {
    Category[] arrayOfCategory = Category.values();
    int i = arrayOfCategory.length;
    for (byte b = 0;; b++) {
      if (b < i) {
        Category category = arrayOfCategory[b];
        if (!category.is_hidden()) {
          Frame frame = new Frame(category);
          frame.set_x(this.frame_x);
          this.frame.add(frame);
          this.frame_x += frame.get_width() + 5;
          this.current = frame;
        } 
      } else {
        return;
      } 
    } 
  }
  
  public void func_146281_b() {
    (give up)null;
    Leux.get_hack_manager().get_module_with_tag("GUI").set_active(false);
    Leux.get_config_manager().save_settings();
  }
  
  public void set_current(Frame paramFrame) {
    (give up)null;
    this.frame.remove(paramFrame);
    this.frame.add(paramFrame);
  }
}
