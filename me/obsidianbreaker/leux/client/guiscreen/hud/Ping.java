package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;

public class Ping extends Pinnable {
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str = "Ping: " + get_ping();
    create_line(str, docking(1, str), 2, i, j, k, m);
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public Ping() {
    super("Ping", "Ping", Float.intBitsToFloat(1.08740966E9F ^ 0x7F508E00), 0, 0);
  }
  
  public String get_ping() {
    (give up)null;
    int i = this.mc.func_147114_u().func_175102_a(this.mc.field_71439_g.func_110124_au()).func_178853_c();
    return (i <= 150) ? ("§a" + Integer.toString(i)) : ((i <= 300) ? ("§e" + Integer.toString(i)) : ("§c" + Integer.toString(i)));
  }
}
