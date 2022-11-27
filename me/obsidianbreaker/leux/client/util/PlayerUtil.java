package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PlayerUtil {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static PlayerUtil$FacingDirection GetFacing() {
    (give up)null;
    switch (MathHelper.func_76128_c((mc.field_71439_g.field_70177_z * Float.intBitsToFloat(1.0476025E9F ^ 0x7F71255F) / Float.intBitsToFloat(1.03403533E9F ^ 0x7E16208F)) + 0.5D) & 0x7) {
      case 0:
      case 1:
        return PlayerUtil$FacingDirection.South;
      case 2:
      case 3:
        return PlayerUtil$FacingDirection.West;
      case 4:
      case 5:
        return PlayerUtil$FacingDirection.North;
      case 6:
      case 7:
        return PlayerUtil$FacingDirection.East;
    } 
    return PlayerUtil$FacingDirection.North;
  }
  
  public void addSpeed(double paramDouble) {
    (give up)null;
    double d = getMoveYaw();
    mc.field_71439_g.field_70159_w -= Math.sin(d) * paramDouble;
    mc.field_71439_g.field_70179_y += Math.cos(d) * paramDouble;
  }
  
  public double getSpeed() {
    (give up)null;
    return Math.hypot(mc.field_71439_g.field_70159_w, mc.field_71439_g.field_70179_y);
  }
  
  public double getMoveYaw() {
    (give up)null;
    float f1 = 90.0F * mc.field_71439_g.field_70702_br;
    f1 = (float)(f1 * ((mc.field_71439_g.field_191988_bg != 0.0F) ? (mc.field_71439_g.field_191988_bg * 0.5D) : 1.0D));
    float f2 = mc.field_71439_g.field_70177_z - f1;
    return Math.toRadians((f2 - ((mc.field_71439_g.field_191988_bg < 0.0F) ? 180.0F : 0.0F)));
  }
  
  public static BlockPos GetLocalPlayerPosFloored() {
    (give up)null;
    return new BlockPos(Math.floor(mc.field_71439_g.field_70165_t), Math.floor(mc.field_71439_g.field_70163_u), Math.floor(mc.field_71439_g.field_70161_v));
  }
  
  public static boolean isCurrentViewEntity() {
    (give up)null;
    return (mc.func_175606_aa() == mc.field_71439_g);
  }
  
  public static EntityPlayer findLookingPlayer(double paramDouble) {
    (give up)null;
    ArrayList<EntityPlayer> arrayList = new ArrayList();
    for (EntityPlayer entityPlayer1 : mc.field_71441_e.field_73010_i) {
      if (entityPlayer1.func_70005_c_().equals(mc.field_71439_g.func_70005_c_()) || FriendUtil.isFriend(entityPlayer1.func_70005_c_()) || entityPlayer1.field_70128_L || mc.field_71439_g.func_70032_d((Entity)entityPlayer1) > paramDouble)
        continue; 
      arrayList.add(entityPlayer1);
    } 
    EntityPlayer entityPlayer = null;
    Vec3d vec3d1 = mc.field_71439_g.func_174824_e(mc.func_184121_ak());
    Vec3d vec3d2 = mc.field_71439_g.func_70676_i(mc.func_184121_ak());
    2;
    byte b = 0;
    if (b < (int)paramDouble)
      for (byte b1 = 2;; b1--) {
        for (Entity entity : arrayList) {
          AxisAlignedBB axisAlignedBB = entity.func_174813_aQ();
          double d1 = vec3d1.field_72450_a + vec3d2.field_72450_a * b + vec3d2.field_72450_a / b1;
          double d2 = vec3d1.field_72448_b + vec3d2.field_72448_b * b + vec3d2.field_72448_b / b1;
          double d3 = vec3d1.field_72449_c + vec3d2.field_72449_c * b + vec3d2.field_72449_c / b1;
          if (axisAlignedBB.field_72337_e >= d2 && axisAlignedBB.field_72338_b <= d2 && axisAlignedBB.field_72336_d >= d1 && axisAlignedBB.field_72340_a <= d1 && axisAlignedBB.field_72334_f >= d3 && axisAlignedBB.field_72339_c <= d3)
            entityPlayer = (EntityPlayer)entity; 
        } 
      }  
    return entityPlayer;
  }
  
  public void setTimer(float paramFloat) {
    (give up)null;
    mc.field_71428_T.field_194149_e = 50.0F / paramFloat;
  }
  
  public static Vec3d getCenter(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return new Vec3d(Math.floor(paramDouble1) + 0.5D, Math.floor(paramDouble2), Math.floor(paramDouble3) + 0.5D);
  }
  
  public void setBoatSpeed(double paramDouble, Entity paramEntity) {
    (give up)null;
    double d = getMoveYaw();
    paramEntity.field_70159_w = -Math.sin(d) * paramDouble;
    paramEntity.field_70179_y = Math.cos(d) * paramDouble;
  }
  
  public void setSpeed(double paramDouble) {
    (give up)null;
    double d = getMoveYaw();
    mc.field_71439_g.field_70159_w = -Math.sin(d) * paramDouble;
    mc.field_71439_g.field_70179_y = Math.cos(d) * paramDouble;
  }
}
