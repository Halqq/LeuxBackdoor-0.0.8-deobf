package me.obsidianbreaker.leux.client.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.FontUtil;
import me.obsidianbreaker.leux.client.util.TimeUtil;
import net.minecraft.util.math.MathHelper;

public class User extends Pinnable {
  public int scaled_width;
  
  public int scaled_height;
  
  public int scale_factor;
  
  public void render() {
    String str;
    (give up)null;
    updateResolution();
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    int n = TimeUtil.get_hour();
    if (n < 12) {
      str = "Good Morning, " + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
    } else if (n >= 12 && n < 16) {
      str = "Good Afternoon, " + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
    } else if (n >= 16 && n < 24) {
      str = "Good Evening, " + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
    } else {
      str = "Hello, " + ChatFormatting.BOLD + this.mc.field_71439_g.func_70005_c_() + ChatFormatting.RESET + " welcome to " + Leux.CLIENT_NAME + " :)";
    } 
    FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), str, this.scaled_width / 2 - this.mc.field_71466_p.func_78256_a(str) / 2, 20, (new Draw.ClientColor(i, j, k, m)).hex());
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public void updateResolution() {
    (give up)null;
    this.scaled_width = this.mc.field_71443_c;
    this.scaled_height = this.mc.field_71440_d;
    this.scale_factor = 1;
    boolean bool = this.mc.func_152349_b();
    int i = this.mc.field_71474_y.field_74335_Z;
    i = 1000;
    while (this.scale_factor < i && this.scaled_width / (this.scale_factor + 1) >= 320 && this.scaled_height / (this.scale_factor + 1) >= 240)
      this.scale_factor++; 
    if (this.scale_factor % 2 != 0 && this.scale_factor != 1)
      this.scale_factor--; 
    double d1 = this.scaled_width / this.scale_factor;
    double d2 = this.scaled_height / this.scale_factor;
    this.scaled_width = MathHelper.func_76143_f(d1);
    this.scaled_height = MathHelper.func_76143_f(d2);
  }
  
  public User() {
    super("User", "User", Float.intBitsToFloat(1.09503104E9F ^ 0x7EC4D911), 0, 0);
  }
}
