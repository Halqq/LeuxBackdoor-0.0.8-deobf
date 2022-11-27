package me.obsidianbreaker.leux.client.util;

import give up;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class RotationUtil {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static float yaw;
  
  public static float pitch;
  
  public void setYaw(float paramFloat) {
    (give up)null;
    yaw = paramFloat;
  }
  
  public void lookAtPos(BlockPos paramBlockPos) {
    (give up)null;
    float[] arrayOfFloat = MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d((paramBlockPos.func_177958_n() + Float.intBitsToFloat(1.08038259E9F ^ 0x7F655441)), (paramBlockPos.func_177956_o() + Float.intBitsToFloat(1.07896576E9F ^ 0x7F4FB61D)), (paramBlockPos.func_177952_p() + Float.intBitsToFloat(1.07472243E9F ^ 0x7F0EF67F))));
    setPlayerRotations(arrayOfFloat[0], arrayOfFloat[1]);
  }
  
  public void setPlayerPitch(float paramFloat) {
    (give up)null;
    mc.field_71439_g.field_70125_A = paramFloat;
  }
  
  public float getPitch() {
    (give up)null;
    return pitch;
  }
  
  public int getDirection4D() {
    (give up)null;
    return getDirection4D();
  }
  
  public void lookAtVec3d(Vec3d paramVec3d) {
    (give up)null;
    float[] arrayOfFloat = MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), new Vec3d(paramVec3d.field_72450_a, paramVec3d.field_72448_b, paramVec3d.field_72449_c));
    setPlayerRotations(arrayOfFloat[0], arrayOfFloat[1]);
  }
  
  public String getDirection4D(boolean paramBoolean) {
    (give up)null;
    return getDirection4D(paramBoolean);
  }
  
  public static void updateRotations() {
    (give up)null;
    yaw = mc.field_71439_g.field_70177_z;
    pitch = mc.field_71439_g.field_70125_A;
  }
  
  public void lookAtEntity(Entity paramEntity) {
    (give up)null;
    float[] arrayOfFloat = MathUtil.calcAngle(mc.field_71439_g.func_174824_e(mc.func_184121_ak()), paramEntity.func_174824_e(mc.func_184121_ak()));
    this;
    setPlayerRotations(arrayOfFloat[0], arrayOfFloat[1]);
  }
  
  public void lookAtVec3d(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    Vec3d vec3d = new Vec3d(paramDouble1, paramDouble2, paramDouble3);
    lookAtVec3d(vec3d);
  }
  
  public static void setPlayerRotations(float paramFloat1, float paramFloat2) {
    (give up)null;
    mc.field_71439_g.field_70177_z = paramFloat1;
    mc.field_71439_g.field_70759_as = paramFloat1;
    mc.field_71439_g.field_70125_A = paramFloat2;
  }
  
  public void setPlayerYaw(float paramFloat) {
    (give up)null;
    mc.field_71439_g.field_70177_z = paramFloat;
    mc.field_71439_g.field_70759_as = paramFloat;
  }
  
  public static void restoreRotations() {
    (give up)null;
    mc.field_71439_g.field_70177_z = yaw;
    mc.field_71439_g.field_70759_as = yaw;
    mc.field_71439_g.field_70125_A = pitch;
  }
  
  public void setPitch(float paramFloat) {
    (give up)null;
    pitch = paramFloat;
  }
  
  public float getYaw() {
    (give up)null;
    return yaw;
  }
}
