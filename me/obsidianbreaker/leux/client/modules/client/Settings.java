package me.obsidianbreaker.leux.client.modules.client;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Frame;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.PinnableButton;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.ClientNameUtil;
import org.lwjgl.opengl.Display;

public class Settings extends Module {
  public int color_b;
  
  public int color_r;
  
  public Setting hud = create("RGB HUD", "HUD", true);
  
  public Setting tabcolor = create("Tab Color", "TabColor", false);
  
  public Setting custom_font = create("Custom Font", "CustomFont", true);
  
  public Setting custom_client_name = create("Custom Client Name", "CustomClientName", false);
  
  public Setting gui = create("RGB GUI", "GUI", false);
  
  public int color_g;
  
  public Setting shadow = create("Shadow", "Shadow", true);
  
  public Settings() {
    super(Category.client);
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71441_e != null && mc.field_71439_g != null) {
      if (this.custom_client_name.get_value(true))
        Leux.set_client_name(ClientNameUtil.get_client_name().replace("[", "").replace("]", "")); 
      Display.setTitle(Leux.get_client_name() + " v" + Leux.get_version());
      float[] arrayOfFloat = { (float)(System.currentTimeMillis() % (-1215231808L ^ 0xFFFFFFFFB79125C0L)) / Float.intBitsToFloat(9.5686566E8F ^ 0x7F3C9C7E) };
      int i = Color.HSBtoRGB(arrayOfFloat[0], Float.intBitsToFloat(1.08304128E9F ^ 0x7F0DE636), Float.intBitsToFloat(1.08847411E9F ^ 0x7F60CC05));
      this.color_r = i >> 16 & 0xFF;
      this.color_g = i >> 8 & 0xFF;
      this.color_b = i & 0xFF;
      if (this.hud.get_value(true)) {
        Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").set_value(this.color_r);
        Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").set_value(this.color_g);
        Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").set_value(this.color_b);
      } 
      if (this.gui.get_value(true)) {
        Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameR").set_value(this.color_r);
        Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameG").set_value(this.color_g);
        Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameFrameB").set_value(this.color_b);
        Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetR").set_value(this.color_r);
        Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetG").set_value(this.color_g);
        Leux.get_setting_manager().get_setting_with_tag("GUI", "ClickGUINameWidgetB").set_value(this.color_b);
        Frame.nc_r = this.color_r;
        Frame.nc_g = this.color_g;
        Frame.nc_b = this.color_b;
        Frame.bd_r = this.color_r;
        Frame.bd_g = this.color_g;
        Frame.bd_b = this.color_b;
        Frame.bd_a = 0;
        Frame.bdw_r = this.color_r;
        Frame.bdw_g = this.color_g;
        Frame.bdw_b = this.color_b;
        Frame.bdw_a = 255;
        PinnableButton.nc_r = this.color_r;
        PinnableButton.nc_g = this.color_g;
        PinnableButton.nc_b = this.color_b;
        PinnableButton.bg_r = this.color_r;
        PinnableButton.bg_g = this.color_g;
        PinnableButton.bg_b = this.color_b;
        PinnableButton.bd_r = this.color_r;
        PinnableButton.bd_g = this.color_g;
        PinnableButton.bd_b = this.color_b;
      } 
    } 
  }
}
