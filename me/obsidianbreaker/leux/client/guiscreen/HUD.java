package me.obsidianbreaker.leux.client.guiscreen;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.PinnableButton;
import net.minecraft.client.gui.GuiScreen;

public class HUD extends GuiScreen {
  public boolean back = false;
  
  public int frame_height;
  
  public Frame frame = new Frame("HUD", "HUD", 40, 40);
  
  public boolean on_gui = false;
  
  public void func_146281_b() {
    (give up)null;
    if (this.back) {
      Leux.get_hack_manager().get_module_with_tag("GUI").set_active(true);
      Leux.get_hack_manager().get_module_with_tag("HUD").set_active(false);
    } else {
      Leux.get_hack_manager().get_module_with_tag("HUD").set_active(false);
      Leux.get_hack_manager().get_module_with_tag("GUI").set_active(false);
    } 
    this.on_gui = false;
    Leux.get_config_manager().save_settings();
  }
  
  public void func_73866_w_() {
    (give up)null;
    this.on_gui = true;
    Frame.nc_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameR").get_value(1);
    Frame.nc_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameG").get_value(1);
    Frame.nc_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameB").get_value(1);
    Frame.bd_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameR").get_value(1);
    Frame.bd_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameG").get_value(1);
    Frame.bd_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameB").get_value(1);
    Frame.bd_a = 255;
    Frame.bdw_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetR").get_value(1);
    Frame.bdw_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetG").get_value(1);
    Frame.bdw_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetB").get_value(1);
    Frame.bdw_a = 255;
    PinnableButton.nc_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameR").get_value(1);
    PinnableButton.nc_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameG").get_value(1);
    PinnableButton.nc_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameB").get_value(1);
    PinnableButton.bg_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetR").get_value(1);
    PinnableButton.bg_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetG").get_value(1);
    PinnableButton.bg_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetB").get_value(1);
    PinnableButton.bg_a = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetA").get_value(1);
    PinnableButton.bd_r = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameR").get_value(1);
    PinnableButton.bd_g = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameG").get_value(1);
    PinnableButton.bd_b = Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUIBorderFrameB").get_value(1);
  }
  
  public void func_146286_b(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    this.frame.release(paramInt1, paramInt2, paramInt3);
    this.frame.set_move(false);
  }
  
  public Frame get_frame_hud() {
    (give up)null;
    return this.frame;
  }
  
  public void func_73863_a(int paramInt1, int paramInt2, float paramFloat) {
    (give up)null;
    func_146276_q_();
    this.frame.render(paramInt1, paramInt2, 2);
  }
  
  public void func_73864_a(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    this.frame.mouse(paramInt1, paramInt2, paramInt3);
    if (this.frame.motion(paramInt1, paramInt2) && this.frame.can()) {
      this.frame.set_move(true);
      this.frame.set_move_x(paramInt1 - this.frame.get_x());
      this.frame.set_move_y(paramInt2 - this.frame.get_y());
    } 
  }
  
  public boolean func_73868_f() {
    (give up)null;
    return false;
  }
}
