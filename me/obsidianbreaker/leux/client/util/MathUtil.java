package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathUtil {
  public static Random random;
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static int clamp(int paramInt1, int paramInt2, int paramInt3) {
    (give up)null;
    return (paramInt1 < paramInt2) ? paramInt2 : Math.min(paramInt1, paramInt3);
  }
  
  public static boolean areVec3dsAlignedRetarded(Vec3d paramVec3d1, Vec3d paramVec3d2) {
    (give up)null;
    BlockPos blockPos1 = new BlockPos(paramVec3d1);
    BlockPos blockPos2 = new BlockPos(paramVec3d2.field_72450_a, paramVec3d1.field_72448_b, paramVec3d2.field_72449_c);
    return blockPos1.equals(blockPos2);
  }
  
  public static Vec3d mult(Vec3d paramVec3d, float paramFloat) {
    (give up)null;
    return new Vec3d(paramVec3d.field_72450_a * paramFloat, paramVec3d.field_72448_b * paramFloat, paramVec3d.field_72449_c * paramFloat);
  }
  
  public static double[] directionSpeed(double paramDouble) {
    (give up)null;
    float f1 = mc.field_71439_g.field_71158_b.field_192832_b;
    float f2 = mc.field_71439_g.field_71158_b.field_78902_a;
    float f3 = mc.field_71439_g.field_70126_B + (mc.field_71439_g.field_70177_z - mc.field_71439_g.field_70126_B) * mc.func_184121_ak();
    if (f1 != 0.0F) {
      if (f2 > 0.0F) {
        f3 += ((f1 > 0.0F) ? -45 : 45);
      } else if (f2 < 0.0F) {
        f3 += ((f1 > 0.0F) ? 45 : -45);
      } 
      f2 = 0.0F;
      if (f1 > 0.0F) {
        f1 = 1.0F;
      } else if (f1 < 0.0F) {
        f1 = -1.0F;
      } 
    } 
    double d1 = Math.sin(Math.toRadians((f3 + 90.0F)));
    double d2 = Math.cos(Math.toRadians((f3 + 90.0F)));
    double d3 = f1 * paramDouble * d2 + f2 * paramDouble * d1;
    double d4 = f1 * paramDouble * d1 - f2 * paramDouble * d2;
    return new double[] { d3, d4 };
  }
  
  public static Vec3d mult(Vec3d paramVec3d1, Vec3d paramVec3d2) {
    (give up)null;
    return new Vec3d(paramVec3d1.field_72450_a * paramVec3d2.field_72450_a, paramVec3d1.field_72448_b * paramVec3d2.field_72448_b, paramVec3d1.field_72449_c * paramVec3d2.field_72449_c);
  }
  
  public static double clamp(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return (paramDouble1 < paramDouble2) ? paramDouble2 : Math.min(paramDouble1, paramDouble3);
  }
  
  public static float sin(float paramFloat) {
    (give up)null;
    return MathHelper.func_76126_a(paramFloat);
  }
  
  public static Vec3d roundVec(Vec3d paramVec3d, int paramInt) {
    (give up)null;
    return new Vec3d(round(paramVec3d.field_72450_a, paramInt), round(paramVec3d.field_72448_b, paramInt), round(paramVec3d.field_72449_c, paramInt));
  }
  
  public static int getRandom(int paramInt1, int paramInt2) {
    (give up)null;
    return paramInt1 + random.nextInt(paramInt2 - paramInt1 + 1);
  }
  
  static {
    random = new Random();
    random = new Random();
  }
  
  public static float wrap(float paramFloat) {
    (give up)null;
    float f = paramFloat % 360.0F;
    if (f >= 180.0F)
      f -= 360.0F; 
    if (f < -180.0F)
      f += 360.0F; 
    return f;
  }
  
  public static double calculateAngle(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    (give up)null;
    double d = Math.toDegrees(Math.atan2(paramDouble3 - paramDouble1, paramDouble4 - paramDouble2));
    d += Math.ceil(-d / 360.0D) * 360.0D;
    return d;
  }
  
  public static double[] directionSpeedNoForward(double paramDouble) {
    (give up)null;
    Minecraft minecraft = Minecraft.func_71410_x();
    float f1 = 1.0F;
    if (minecraft.field_71474_y.field_74370_x.func_151468_f() || minecraft.field_71474_y.field_74366_z.func_151468_f() || minecraft.field_71474_y.field_74368_y.func_151468_f() || minecraft.field_71474_y.field_74351_w.func_151468_f())
      f1 = minecraft.field_71439_g.field_71158_b.field_192832_b; 
    float f2 = minecraft.field_71439_g.field_71158_b.field_78902_a;
    float f3 = minecraft.field_71439_g.field_70126_B + (minecraft.field_71439_g.field_70177_z - minecraft.field_71439_g.field_70126_B) * minecraft.func_184121_ak();
    if (f1 != 0.0F) {
      if (f2 > 0.0F) {
        f3 += ((f1 > 0.0F) ? -45 : 45);
      } else if (f2 < 0.0F) {
        f3 += ((f1 > 0.0F) ? 45 : -45);
      } 
      f2 = 0.0F;
      if (f1 > 0.0F) {
        f1 = 1.0F;
      } else if (f1 < 0.0F) {
        f1 = -1.0F;
      } 
    } 
    double d1 = Math.sin(Math.toRadians((f3 + 90.0F)));
    double d2 = Math.cos(Math.toRadians((f3 + 90.0F)));
    double d3 = f1 * paramDouble * d2 + f2 * paramDouble * d1;
    double d4 = f1 * paramDouble * d1 - f2 * paramDouble * d2;
    return new double[] { d3, d4 };
  }
  
  public static float getRandom(float paramFloat1, float paramFloat2) {
    (give up)null;
    return MathHelper.func_76131_a(paramFloat1 + random.nextFloat() * paramFloat2, paramFloat1, paramFloat2);
  }
  
  public static double map(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5) {
    (give up)null;
    paramDouble1 = (paramDouble1 - paramDouble2) / (paramDouble3 - paramDouble2);
    return paramDouble4 + paramDouble1 * (paramDouble5 - paramDouble4);
  }
  
  public static double[] calcule_look_at(double paramDouble1, double paramDouble2, double paramDouble3, EntityPlayer paramEntityPlayer) {
    (give up)null;
    double d1 = paramEntityPlayer.field_70165_t - paramDouble1;
    double d2 = paramEntityPlayer.field_70163_u - paramDouble2;
    double d3 = paramEntityPlayer.field_70161_v - paramDouble3;
    Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
    double d4 = Math.asin(d2);
    double d5 = Math.atan2(d3, d1);
    d4 = d4 * 180.0D / Math.PI;
    d5 = d5 * 180.0D / Math.PI;
    d5 += 90.0D;
    return new double[] { d5, d4 };
  }
  
  public static float wrapDegrees(float paramFloat) {
    (give up)null;
    return MathHelper.func_76142_g(paramFloat);
  }
  
  public static double parabolic(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return paramDouble1 + (paramDouble2 - paramDouble1) / paramDouble3;
  }
  
  public static double radToDeg(double paramDouble) {
    (give up)null;
    return paramDouble * 57.295780181884766D;
  }
  
  public static float clamp2(float paramFloat1, float paramFloat2, float paramFloat3) {
    paramFloat3 = 90.0F;
    paramFloat2 = -90.0F;
    (give up)null;
    return (paramFloat1 < paramFloat2) ? paramFloat2 : ((paramFloat1 > paramFloat3) ? paramFloat3 : paramFloat1);
  }
  
  public static String getTimeOfDay() {
    (give up)null;
    Calendar calendar = Calendar.getInstance();
    int i = calendar.get(11);
    return (i < 12) ? "Good Morning " : ((i < 16) ? "Good Afternoon " : ((i < 21) ? "Good Evening " : "Good Night "));
  }
  
  public static double linear(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return (paramDouble1 < paramDouble2 - paramDouble3) ? (paramDouble1 + paramDouble3) : ((paramDouble1 > paramDouble2 + paramDouble3) ? (paramDouble1 - paramDouble3) : paramDouble2);
  }
  
  public static float round(float paramFloat, int paramInt) {
    (give up)null;
    throw new IllegalArgumentException();
  }
  
  public static double degToRad(double paramDouble) {
    (give up)null;
    return paramDouble * 0.01745329238474369D;
  }
  
  public static double round(double paramDouble, int paramInt) {
    (give up)null;
    throw new IllegalArgumentException();
  }
  
  public static List getBlockBlocks(Entity paramEntity) {
    (give up)null;
    ArrayList<Vec3d> arrayList = new ArrayList();
    AxisAlignedBB axisAlignedBB = paramEntity.func_174813_aQ();
    double d1 = paramEntity.field_70163_u;
    double d2 = round(axisAlignedBB.field_72340_a, 0);
    double d3 = round(axisAlignedBB.field_72339_c, 0);
    double d4 = round(axisAlignedBB.field_72336_d, 0);
    double d5 = round(axisAlignedBB.field_72334_f, 0);
    if (d2 != d4) {
      arrayList.add(new Vec3d(d2, d1, d3));
      arrayList.add(new Vec3d(d4, d1, d3));
      if (d3 != d5) {
        arrayList.add(new Vec3d(d2, d1, d5));
        arrayList.add(new Vec3d(d4, d1, d5));
        return arrayList;
      } 
    } else if (d3 != d5) {
      arrayList.add(new Vec3d(d2, d1, d3));
      arrayList.add(new Vec3d(d2, d1, d5));
      return arrayList;
    } 
    arrayList.add(paramEntity.func_174791_d());
    return arrayList;
  }
  
  public static double[] calcIntersection(double[] paramArrayOfdouble1, double[] paramArrayOfdouble2) {
    (give up)null;
    double d1 = paramArrayOfdouble1[3] - paramArrayOfdouble1[1];
    double d2 = paramArrayOfdouble1[0] - paramArrayOfdouble1[2];
    double d3 = d1 * paramArrayOfdouble1[0] + d2 * paramArrayOfdouble1[1];
    double d4 = paramArrayOfdouble2[3] - paramArrayOfdouble2[1];
    double d5 = paramArrayOfdouble2[0] - paramArrayOfdouble2[2];
    double d6 = d4 * paramArrayOfdouble2[0] + d5 * paramArrayOfdouble2[1];
    double d7 = d1 * d5 - d4 * d2;
    return new double[] { (d5 * d3 - d2 * d6) / d7, (d1 * d6 - d4 * d3) / d7 };
  }
  
  public static double getIncremental(double paramDouble1, double paramDouble2) {
    paramDouble2 = 1.0D;
    (give up)null;
    double d = 1.0D / paramDouble2;
    return Math.round(paramDouble1 * d) / d;
  }
  
  public static float[] calcAngle(Vec3d paramVec3d1, Vec3d paramVec3d2) {
    (give up)null;
    double d1 = paramVec3d2.field_72450_a - paramVec3d1.field_72450_a;
    double d2 = (paramVec3d2.field_72448_b - paramVec3d1.field_72448_b) * -1.0D;
    double d3 = paramVec3d2.field_72449_c - paramVec3d1.field_72449_c;
    double d4 = MathHelper.func_76133_a(d1 * d1 + d3 * d3);
    return new float[] { (float)MathHelper.func_76138_g(Math.toDegrees(Math.atan2(d3, d1)) - 90.0D), (float)MathHelper.func_76138_g(Math.toDegrees(Math.atan2(d2, d4))) };
  }
  
  public static double[] movement_speed(double paramDouble) {
    (give up)null;
    float f1 = mc.field_71439_g.field_71158_b.field_192832_b;
    float f2 = mc.field_71439_g.field_71158_b.field_78902_a;
    float f3 = mc.field_71439_g.field_70126_B + (mc.field_71439_g.field_70177_z - mc.field_71439_g.field_70126_B) * mc.func_184121_ak();
    if (f1 != 0.0F) {
      if (f2 > 0.0F) {
        f3 += ((f1 > 0.0F) ? -45 : 45);
      } else if (f2 < 0.0F) {
        f3 += ((f1 > 0.0F) ? 45 : -45);
      } 
      f2 = 0.0F;
      if (f1 > 0.0F) {
        f1 = 1.0F;
      } else if (f1 < 0.0F) {
        f1 = -1.0F;
      } 
    } 
    double d1 = Math.sin(Math.toRadians((f3 + 90.0F)));
    double d2 = Math.cos(Math.toRadians((f3 + 90.0F)));
    double d3 = f1 * paramDouble * d2 + f2 * paramDouble * d1;
    double d4 = f1 * paramDouble * d1 - f2 * paramDouble * d2;
    return new double[] { d3, d4 };
  }
  
  public static Vec3d get_eye_pos() {
    (give up)null;
    return new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v);
  }
  
  public static double getRandom(double paramDouble1, double paramDouble2) {
    (give up)null;
    return MathHelper.func_151237_a(paramDouble1 + random.nextDouble() * paramDouble2, paramDouble1, paramDouble2);
  }
  
  public static Vec3d div(Vec3d paramVec3d1, Vec3d paramVec3d2) {
    (give up)null;
    return new Vec3d(paramVec3d1.field_72450_a / paramVec3d2.field_72450_a, paramVec3d1.field_72448_b / paramVec3d2.field_72448_b, paramVec3d1.field_72449_c / paramVec3d2.field_72449_c);
  }
  
  public static double square(double paramDouble) {
    (give up)null;
    return paramDouble * paramDouble;
  }
  
  public static float[] legit_rotation(Vec3d paramVec3d) {
    (give up)null;
    Vec3d vec3d = get_eye_pos();
    double d1 = paramVec3d.field_72450_a - vec3d.field_72450_a;
    double d2 = paramVec3d.field_72448_b - vec3d.field_72448_b;
    double d3 = paramVec3d.field_72449_c - vec3d.field_72449_c;
    double d4 = Math.sqrt(d1 * d1 + d3 * d3);
    float f1 = (float)Math.toDegrees(Math.atan2(d3, d1)) - 90.0F;
    float f2 = (float)-Math.toDegrees(Math.atan2(d2, d4));
    return new float[] { mc.field_71439_g.field_70177_z + MathHelper.func_76142_g(f1 - mc.field_71439_g.field_70177_z), mc.field_71439_g.field_70125_A + MathHelper.func_76142_g(f2 - mc.field_71439_g.field_70125_A) };
  }
  
  public static float cos(float paramFloat) {
    (give up)null;
    return MathHelper.func_76134_b(paramFloat);
  }
  
  public static Vec3d direction(float paramFloat) {
    (give up)null;
    return new Vec3d(Math.cos(degToRad((paramFloat + Float.intBitsToFloat(1.0393097E9F ^ 0x7F469B83)))), 0.0D, Math.sin(degToRad((paramFloat + Float.intBitsToFloat(1.07130509E9F ^ 0x7D6ED19F)))));
  }
  
  public static double wrapDegrees(double paramDouble) {
    (give up)null;
    return MathHelper.func_76138_g(paramDouble);
  }
  
  public static float clamp(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    return (paramFloat1 < paramFloat2) ? paramFloat2 : Math.min(paramFloat1, paramFloat3);
  }
  
  public static float[] calc_Angle(Vec3d paramVec3d1, Vec3d paramVec3d2) {
    (give up)null;
    double d1 = paramVec3d2.field_72450_a - paramVec3d1.field_72450_a;
    double d2 = (paramVec3d2.field_72448_b - paramVec3d1.field_72448_b) * -1.0D;
    double d3 = paramVec3d2.field_72449_c - paramVec3d1.field_72449_c;
    double d4 = MathHelper.func_76133_a(d1 * d1 + d3 * d3);
    return new float[] { (float)MathHelper.func_76138_g(Math.toDegrees(Math.atan2(d3, d1)) - 90.0D), (float)MathHelper.func_76138_g(Math.toDegrees(Math.atan2(d2, d4))) };
  }
  
  public static Vec3d div(Vec3d paramVec3d, float paramFloat) {
    (give up)null;
    return new Vec3d(paramVec3d.field_72450_a / paramFloat, paramVec3d.field_72448_b / paramFloat, paramVec3d.field_72449_c / paramFloat);
  }
  
  public static double getDistance(Vec3d paramVec3d, double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    double d1 = paramVec3d.field_72450_a - paramDouble1;
    double d2 = paramVec3d.field_72448_b - paramDouble2;
    double d3 = paramVec3d.field_72449_c - paramDouble3;
    return MathHelper.func_76133_a(d1 * d1 + d2 * d2 + d3 * d3);
  }
  
  public static Vec3d interpolateEntity(Entity paramEntity, float paramFloat) {
    (give up)null;
    return new Vec3d(paramEntity.field_70142_S + (paramEntity.field_70165_t - paramEntity.field_70142_S) * paramFloat, paramEntity.field_70137_T + (paramEntity.field_70163_u - paramEntity.field_70137_T) * paramFloat, paramEntity.field_70136_U + (paramEntity.field_70161_v - paramEntity.field_70136_U) * paramFloat);
  }
}
