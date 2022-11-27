package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.List;
import java.util.Objects;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityUtil {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static boolean basicChecksEntity(Entity paramEntity) {
    (give up)null;
    return (paramEntity.func_70005_c_().equals(mc.field_71439_g.func_70005_c_()) || FriendUtil.isFriend(paramEntity.func_70005_c_()) || paramEntity.field_70128_L);
  }
  
  public static boolean isEntityMoving(Entity paramEntity) {
    (give up)null;
    return false;
  }
  
  public static Vec3d process_interpolated_pos(Entity paramEntity, float paramFloat) {
    (give up)null;
    return (new Vec3d(paramEntity.field_70142_S, paramEntity.field_70137_T, paramEntity.field_70136_U)).func_178787_e(get_interpolated_amout(paramEntity, paramFloat));
  }
  
  public static boolean isPlayer(Entity paramEntity) {
    (give up)null;
    return paramEntity instanceof EntityPlayer;
  }
  
  public static double getMaxSpeed() {
    (give up)null;
    double d = 0.2873D;
    if (mc.field_71439_g.func_70644_a(Objects.<Potion>requireNonNull(Potion.func_188412_a(1))))
      d *= 1.0D + 0.2D * (((PotionEffect)Objects.<PotionEffect>requireNonNull(mc.field_71439_g.func_70660_b(Objects.<Potion>requireNonNull(Potion.func_188412_a(1))))).func_76458_c() + 1); 
    return d;
  }
  
  public static boolean isAboveWater(Entity paramEntity, boolean paramBoolean) {
    (give up)null;
    return false;
  }
  
  public static Vec3d get_interpolated_amout(Entity paramEntity, float paramFloat) {
    (give up)null;
    return process_interpolated_amount(paramEntity, paramFloat, paramFloat, paramFloat);
  }
  
  public static boolean isIntercepted(BlockPos paramBlockPos) {
    (give up)null;
    for (Entity entity : mc.field_71441_e.field_72996_f) {
      if ((new AxisAlignedBB(paramBlockPos)).func_72326_a(entity.func_174813_aQ()))
        return true; 
    } 
    return false;
  }
  
  public static Vec3d process_interpolated_amount(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return new Vec3d((paramEntity.field_70165_t - paramEntity.field_70142_S) * paramDouble1, (paramEntity.field_70163_u - paramEntity.field_70137_T) * paramDouble2, (paramEntity.field_70161_v - paramEntity.field_70136_U) * paramDouble3);
  }
  
  public static Vec3d getInterpolatedPos(Entity paramEntity, float paramFloat) {
    (give up)null;
    return (new Vec3d(paramEntity.field_70142_S, paramEntity.field_70137_T, paramEntity.field_70136_U)).func_178787_e(getInterpolatedAmount(paramEntity, paramFloat));
  }
  
  public static void attackEntity(Entity paramEntity) {
    (give up)null;
    mc.field_71442_b.func_78764_a((EntityPlayer)mc.field_71439_g, paramEntity);
    mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
  }
  
  public static Vec3d getInterpolatedRenderPos(Entity paramEntity, float paramFloat) {
    (give up)null;
    return getInterpolatedPos(paramEntity, paramFloat).func_178786_a((mc.func_175598_ae()).field_78725_b, (mc.func_175598_ae()).field_78726_c, (mc.func_175598_ae()).field_78723_d);
  }
  
  public static BlockPos getPlayerPosWithEntity() {
    (give up)null;
    return new BlockPos((mc.field_71439_g.func_184187_bx() != null) ? (mc.field_71439_g.func_184187_bx()).field_70165_t : mc.field_71439_g.field_70165_t, (mc.field_71439_g.func_184187_bx() != null) ? (mc.field_71439_g.func_184187_bx()).field_70163_u : mc.field_71439_g.field_70163_u, (mc.field_71439_g.func_184187_bx() != null) ? (mc.field_71439_g.func_184187_bx()).field_70161_v : mc.field_71439_g.field_70161_v);
  }
  
  public static Vec3d get_interpolated_render_pos(Entity paramEntity, float paramFloat) {
    (give up)null;
    return process_interpolated_pos(paramEntity, paramFloat).func_178786_a((mc.func_175598_ae()).field_78725_b, (mc.func_175598_ae()).field_78726_c, (mc.func_175598_ae()).field_78723_d);
  }
  
  public static boolean isInWater(Entity paramEntity) {
    (give up)null;
    return false;
  }
  
  public static boolean isDrivenByPlayer(Entity paramEntity) {
    (give up)null;
    if (Wrapper.getPlayer() != null)
      if (paramEntity.equals(Wrapper.getPlayer().func_184187_bx())); 
    return false;
  }
  
  public static boolean isLiving(Entity paramEntity) {
    (give up)null;
    return paramEntity instanceof net.minecraft.entity.EntityLivingBase;
  }
  
  public static BlockPos getPosition(Entity paramEntity) {
    (give up)null;
    return new BlockPos(Math.floor(paramEntity.field_70165_t), Math.floor(paramEntity.field_70163_u), Math.floor(paramEntity.field_70161_v));
  }
  
  public static Vec3d getInterpolatedAmount(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return new Vec3d((paramEntity.field_70165_t - paramEntity.field_70142_S) * paramDouble1, 0.0D * paramDouble2, (paramEntity.field_70161_v - paramEntity.field_70136_U) * paramDouble3);
  }
  
  public static void attackEntity(Entity paramEntity, boolean paramBoolean, Setting paramSetting) {
    (give up)null;
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(paramEntity));
    if (paramSetting.in("Mainhand") || paramSetting.in("Both"))
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND); 
    if (paramSetting.in("Offhand") || paramSetting.in("Both"))
      mc.field_71439_g.func_184609_a(EnumHand.OFF_HAND); 
  }
  
  public static boolean isAboveWater(Entity paramEntity) {
    (give up)null;
    return isAboveWater(paramEntity, false);
  }
  
  public static boolean isMoving() {
    (give up)null;
    return (mc.field_71439_g.field_191988_bg != 0.0D || mc.field_71439_g.field_70702_br != 0.0D);
  }
  
  public static BlockPos is_cityable(EntityPlayer paramEntityPlayer, boolean paramBoolean) {
    (give up)null;
    BlockPos blockPos = new BlockPos(paramEntityPlayer.field_70165_t, paramEntityPlayer.field_70163_u, paramEntityPlayer.field_70161_v);
    return (mc.field_71441_e.func_180495_p(blockPos.func_177978_c()).func_177230_c() == Blocks.field_150343_Z) ? blockPos.func_177978_c() : ((mc.field_71441_e.func_180495_p(blockPos.func_177974_f()).func_177230_c() == Blocks.field_150343_Z) ? blockPos.func_177974_f() : ((mc.field_71441_e.func_180495_p(blockPos.func_177968_d()).func_177230_c() == Blocks.field_150343_Z) ? blockPos.func_177968_d() : ((mc.field_71441_e.func_180495_p(blockPos.func_177976_e()).func_177230_c() == Blocks.field_150343_Z) ? blockPos.func_177976_e() : null)));
  }
  
  public static EntityPlayer findClosestTarget(double paramDouble, EntityPlayer paramEntityPlayer) {
    (give up)null;
    paramDouble *= paramDouble;
    List list = mc.field_71441_e.field_73010_i;
    EntityPlayer entityPlayer = null;
    for (EntityPlayer entityPlayer1 : list) {
      if (basicChecksEntity((Entity)entityPlayer1))
        continue; 
      if (mc.field_71439_g.func_70068_e((Entity)entityPlayer1) <= paramDouble) {
        entityPlayer = entityPlayer1;
        continue;
      } 
      if (mc.field_71439_g.func_70068_e((Entity)entityPlayer1) > paramDouble || mc.field_71439_g.func_70068_e((Entity)entityPlayer1) >= mc.field_71439_g.func_70068_e((Entity)paramEntityPlayer))
        continue; 
      entityPlayer = entityPlayer1;
    } 
    return entityPlayer;
  }
  
  public static Vec3d get_interpolated_entity(Entity paramEntity, float paramFloat) {
    (give up)null;
    return (new Vec3d(paramEntity.field_70142_S, paramEntity.field_70137_T, paramEntity.field_70136_U)).func_178787_e(get_interpolated_amout(paramEntity, paramFloat));
  }
  
  public static Vec3d getInterpolatedAmount(Entity paramEntity, double paramDouble) {
    (give up)null;
    return getInterpolatedAmount(paramEntity, paramDouble, paramDouble, paramDouble);
  }
}
