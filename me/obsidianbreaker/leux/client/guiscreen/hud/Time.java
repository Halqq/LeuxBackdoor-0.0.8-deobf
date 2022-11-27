package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.TimeUtil;

public class Time extends Pinnable {
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str = "";
    str = str + ((TimeUtil.get_hour() < 10) ? ("0" + TimeUtil.get_hour()) : (String)Integer.valueOf(TimeUtil.get_hour()));
    str = str + ":";
    str = str + ((TimeUtil.get_minuite() < 10) ? ("0" + TimeUtil.get_minuite()) : (String)Integer.valueOf(TimeUtil.get_minuite()));
    str = str + ":";
    str = str + ((TimeUtil.get_second() < 10) ? ("0" + TimeUtil.get_second()) : (String)Integer.valueOf(TimeUtil.get_second()));
    create_line(str, docking(1, str), 2, i, j, k, m);
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public Time() {
    super("Time", "Time", Float.intBitsToFloat(1.11485824E9F ^ 0x7DF362EF), 0, 0);
  }
}
