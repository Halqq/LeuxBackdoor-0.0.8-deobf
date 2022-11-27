package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import java.text.DecimalFormat;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;

public class Speedometer extends Pinnable {
  public static double get_speed() {
    (give up)null;
    Minecraft minecraft = Minecraft.func_71410_x();
    double d1 = minecraft.field_71439_g.field_70165_t - minecraft.field_71439_g.field_70169_q;
    double d2 = minecraft.field_71439_g.field_70161_v - minecraft.field_71439_g.field_70166_s;
    float f = minecraft.field_71428_T.field_194149_e / 1000.0F;
    return (MathHelper.func_76133_a(d1 * d1 + d2 * d2) / f) * 3.6D;
  }
  
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    double d1 = this.mc.field_71439_g.field_70165_t - this.mc.field_71439_g.field_70169_q;
    double d2 = this.mc.field_71439_g.field_70161_v - this.mc.field_71439_g.field_70166_s;
    float f = this.mc.field_71428_T.field_194149_e / Float.intBitsToFloat(9.7479245E8F ^ 0x7E602717);
    String str = "Speed: " + (new DecimalFormat("§a#.#")).format((MathHelper.func_76133_a(d1 * d1 + d2 * d2) / f) * 3.6D);
    create_line(str, docking(1, str), 2, i, j, k, m);
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public Speedometer() {
    super("Speedometer", "Speedometer", Float.intBitsToFloat(1.09416755E9F ^ 0x7EB7AC01), 0, 0);
  }
}
