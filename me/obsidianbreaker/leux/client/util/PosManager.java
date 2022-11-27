package me.obsidianbreaker.leux.client.util;

import give up;
import net.minecraft.client.Minecraft;

public class PosManager {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static double z;
  
  public static double x;
  
  public static double y;
  
  public static boolean onground;
  
  public static void restorePosition() {
    (give up)null;
    mc.field_71439_g.field_70165_t = x;
    mc.field_71439_g.field_70163_u = y;
    mc.field_71439_g.field_70161_v = z;
    mc.field_71439_g.field_70122_E = onground;
  }
  
  public static void updatePosition() {
    (give up)null;
    x = mc.field_71439_g.field_70165_t;
    y = mc.field_71439_g.field_70163_u;
    z = mc.field_71439_g.field_70161_v;
    onground = mc.field_71439_g.field_70122_E;
  }
}
