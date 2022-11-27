package me.obsidianbreaker.leux.client.modules.client;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.client.gui.GuiScreen;

public class ClickGUI extends Module {
  public Setting name_widget_g = create("Name G", "ClickGUINameWidgetG", 210, 0, 255);
  
  public Setting name_widget_r = create("Name R", "ClickGUINameWidgetR", 210, 0, 255);
  
  public Setting name_widget_b = create("Name B", "ClickGUINameWidgetB", 255, 0, 255);
  
  public Setting name_frame_r = create("Name R", "ClickGUINameFrameR", 45, 0, 255);
  
  public Setting background_widget_b = create("Background B", "ClickGUIBackgroundWidgetB", 255, 0, 255);
  
  public Setting background_frame_b = create("Background B", "ClickGUIBackgroundFrameB", 40, 0, 255);
  
  public Setting background_widget_r = create("Background R", "ClickGUIBackgroundWidgetR", 0, 0, 255);
  
  public Setting border_widget_b = create("Border B", "ClickGUIBorderWidgetB", 255, 0, 255);
  
  public Setting border_widget_g = create("Border G", "ClickGUIBorderWidgetG", 15, 0, 255);
  
  public Setting border_frame_g = create("Border G", "ClickGUIBorderFrameG", 60, 0, 255);
  
  public Setting border_frame_r = create("Border R", "ClickGUIBorderFrameR", 60, 0, 255);
  
  public Setting label_widget = create("info", "ClickGUIInfoWidget", "Widgets");
  
  public Setting background_widget_g = create("Background G", "ClickGUIBackgroundWidgetG", 55, 0, 255);
  
  public Setting border_frame_b = create("Border B", "ClickGUIBorderFrameB", 255, 0, 255);
  
  public Setting background_frame_g = create("Background G", "ClickGUIBackgroundFrameG", 0, 0, 255);
  
  public Setting label_frame = create("info", "ClickGUIInfoFrame", "Frames");
  
  public Setting background_frame_a = create("Background A", "ClickGUIBackgroundFrameA", 170, 0, 255);
  
  public Setting name_frame_g = create("Name G", "ClickGUINameFrameG", 45, 0, 255);
  
  public Setting background_frame_r = create("Background R", "ClickGUIBackgroundFrameR", 0, 0, 255);
  
  public Setting name_frame_b = create("Name B", "ClickGUINameFrameB", 255, 0, 255);
  
  public Setting border_widget_r = create("Border R", "ClickGUIBorderWidgetR", 15, 0, 255);
  
  public Setting background_widget_a = create("Background A", "ClickGUIBackgroundWidgetA", 170, 0, 255);
  
  public ClickGUI() {
    super(Category.client);
    set_bind(54);
  }
  
  public void enable() {
    (give up)null;
    if (mc.field_71441_e != null && mc.field_71439_g != null)
      mc.func_147108_a((GuiScreen)Leux.click_gui); 
  }
  
  public void disable() {
    (give up)null;
    if (mc.field_71441_e != null && mc.field_71439_g != null)
      mc.func_147108_a(null); 
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71441_e != null && mc.field_71439_g != null) {
      Leux.click_gui.theme_frame_name_r = this.name_frame_r.get_value(1);
      Leux.click_gui.theme_frame_name_g = this.name_frame_g.get_value(1);
      Leux.click_gui.theme_frame_name_b = this.name_frame_b.get_value(1);
      Leux.click_gui.theme_frame_background_r = this.background_frame_r.get_value(1);
      Leux.click_gui.theme_frame_background_g = this.background_frame_g.get_value(1);
      Leux.click_gui.theme_frame_background_b = this.background_frame_b.get_value(1);
      Leux.click_gui.theme_frame_background_a = this.background_frame_a.get_value(1);
      Leux.click_gui.theme_frame_border_r = this.border_frame_r.get_value(1);
      Leux.click_gui.theme_frame_border_g = this.border_frame_g.get_value(1);
      Leux.click_gui.theme_frame_border_b = this.border_frame_b.get_value(1);
      Leux.click_gui.theme_widget_name_r = this.name_widget_r.get_value(1);
      Leux.click_gui.theme_widget_name_g = this.name_widget_g.get_value(1);
      Leux.click_gui.theme_widget_name_b = this.name_widget_b.get_value(1);
      Leux.click_gui.theme_widget_background_r = this.background_widget_r.get_value(1);
      Leux.click_gui.theme_widget_background_g = this.background_widget_g.get_value(1);
      Leux.click_gui.theme_widget_background_b = this.background_widget_b.get_value(1);
      Leux.click_gui.theme_widget_background_a = this.background_widget_a.get_value(1);
      Leux.click_gui.theme_widget_border_r = this.border_widget_r.get_value(1);
      Leux.click_gui.theme_widget_border_g = this.border_widget_g.get_value(1);
      Leux.click_gui.theme_widget_border_b = this.border_widget_b.get_value(1);
    } 
  }
}
