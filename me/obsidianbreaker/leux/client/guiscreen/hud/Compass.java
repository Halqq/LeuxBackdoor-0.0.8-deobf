package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.MathUtil;

public class Compass extends Pinnable {
  public Draw font = new Draw(Float.intBitsToFloat(1.0838752E9F ^ 0x7F1A9F43));
  
  public static double half_pi = 1.5707963267948966D;
  
  public double get_y(double paramDouble) {
    (give up)null;
    double d1 = MathUtil.clamp2((this.mc.func_175606_aa()).field_70125_A + 30.0F, -90.0F, 90.0F);
    double d2 = Math.toRadians(d1);
    return Math.cos(paramDouble) * Math.sin(d2) * Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDCompassScale").get_value(1);
  }
  
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    for (Compass$Direction compass$Direction : Compass$Direction.values()) {
      double d = get_pos_on_compass(compass$Direction);
      if (compass$Direction.name().equals("N")) {
        create_line(compass$Direction.name(), (int)(docking(1, compass$Direction.name()) + get_x(d)), (int)get_y(d), i, j, k, m);
      } else {
        create_line(compass$Direction.name(), (int)(docking(1, compass$Direction.name()) + get_x(d)), (int)get_y(d), 225, 225, 225, 225);
      } 
    } 
    set_width(50);
    set_height(50);
  }
  
  public Compass() {
    super("Compass", "Compass", Float.intBitsToFloat(1.09505626E9F ^ 0x7EC53BB9), 0, 0);
  }
  
  public double get_pos_on_compass(Compass$Direction paramCompass$Direction) {
    (give up)null;
    double d = Math.toRadians(MathUtil.wrap((this.mc.func_175606_aa()).field_70177_z));
    int i = paramCompass$Direction.ordinal();
    return d + i * 1.5707963267948966D;
  }
  
  public double get_x(double paramDouble) {
    (give up)null;
    return Math.sin(paramDouble) * Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDCompassScale").get_value(1);
  }
}
