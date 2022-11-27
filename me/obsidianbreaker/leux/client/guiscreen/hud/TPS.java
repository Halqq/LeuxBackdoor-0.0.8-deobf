package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventHandler;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;

public class TPS extends Pinnable {
  public TPS() {
    super("TPS", "TPS", Float.intBitsToFloat(1.11644736E9F ^ 0x7D0BA25F), 0, 0);
  }
  
  public String getTPS() {
    (give up)null;
    int i = Math.round(EventHandler.INSTANCE.get_tick_rate());
    return (i >= 16) ? ("§a" + Integer.toString(i)) : ((i >= 10) ? ("§e" + Integer.toString(i)) : ("§c" + Integer.toString(i)));
  }
  
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str = "TPS: " + getTPS();
    create_line(str, docking(1, str), 2, i, j, k, m);
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
}
