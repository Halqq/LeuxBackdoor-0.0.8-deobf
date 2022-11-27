package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.Minecraft;

public class FPS extends Pinnable {
  public FPS() {
    super("Fps", "Fps", Float.intBitsToFloat(1.10549082E9F ^ 0x7E64738B), 0, 0);
  }
  
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str = "FPS: " + get_fps();
    create_line(str, docking(1, str), 2, i, j, k, m);
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public String get_fps() {
    (give up)null;
    int i = Minecraft.func_175610_ah();
    return (i >= 50) ? ("§a" + Integer.toString(i)) : ((i >= 25) ? ("§e" + Integer.toString(i)) : ("§c" + Integer.toString(i)));
  }
}
