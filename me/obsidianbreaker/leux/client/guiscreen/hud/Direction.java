package me.obsidianbreaker.leux.client.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.util.math.MathHelper;

public class Direction extends Pinnable {
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str = String.format("%s " + ChatFormatting.GRAY + "%s", new Object[] { getFacing(), getTowards() });
    create_line(str, docking(1, str), 2, i, j, k, m);
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public String getFacing() {
    (give up)null;
    switch (MathHelper.func_76128_c((this.mc.field_71439_g.field_70177_z * 8.0F / 360.0F) + 0.5D) & 0x7) {
      case 0:
        return "South";
      case 1:
        return "South West";
      case 2:
        return "West";
      case 3:
        return "North West";
      case 4:
        return "North";
      case 5:
        return "North East";
      case 6:
        return "East";
      case 7:
        return "South East";
    } 
    return "Invalid";
  }
  
  public String getTowards() {
    (give up)null;
    switch (MathHelper.func_76128_c((this.mc.field_71439_g.field_70177_z * 8.0F / 360.0F) + 0.5D) & 0x7) {
      case 0:
        return "+Z";
      case 1:
        return "-X +Z";
      case 2:
        return "-X";
      case 3:
        return "-X -Z";
      case 4:
        return "-Z";
      case 5:
        return "+X -Z";
      case 6:
        return "+X";
      case 7:
        return "+X +Z";
    } 
    return "Invalid";
  }
  
  public Direction() {
    super("Direction", "Direction", Float.intBitsToFloat(1.09406477E9F ^ 0x7EB61A99), 0, 0);
  }
}
