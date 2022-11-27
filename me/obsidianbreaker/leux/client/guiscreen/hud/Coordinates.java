package me.obsidianbreaker.leux.client.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;

public class Coordinates extends Pinnable {
  public ChatFormatting r = ChatFormatting.RED;
  
  public ChatFormatting g = ChatFormatting.GRAY;
  
  public ChatFormatting db = ChatFormatting.DARK_BLUE;
  
  public Coordinates() {
    super("Coordinates", "Coordinates", Float.intBitsToFloat(1.0861367E9F ^ 0x7F3D2188), 0, 0);
  }
  
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str1 = this.g + "[" + Leux.r + Integer.toString((int)this.mc.field_71439_g.field_70165_t) + this.g + "]" + Leux.r;
    String str2 = this.g + "[" + Leux.r + Integer.toString((int)this.mc.field_71439_g.field_70163_u) + this.g + "]" + Leux.r;
    String str3 = this.g + "[" + Leux.r + Integer.toString((int)this.mc.field_71439_g.field_70161_v) + this.g + "]" + Leux.r;
    String str4 = this.g + "[" + this.r + Long.toString(Math.round((this.mc.field_71439_g.field_71093_bK != -1) ? (this.mc.field_71439_g.field_70165_t / 8.0D) : (this.mc.field_71439_g.field_70165_t * 8.0D))) + this.g + "]" + Leux.r;
    String str5 = this.g + "[" + this.r + Long.toString(Math.round((this.mc.field_71439_g.field_71093_bK != -1) ? (this.mc.field_71439_g.field_70161_v / 8.0D) : (this.mc.field_71439_g.field_70161_v * 8.0D))) + this.g + "]" + Leux.r;
    String str6 = "XYZ " + str1 + str2 + str3 + this.r + " XZ " + str4 + str5;
    create_line(str6, docking(1, str6), 2, i, j, k, m);
    set_width(get(str6, "width"));
    set_height(get(str6, "height") + 2);
  }
}
