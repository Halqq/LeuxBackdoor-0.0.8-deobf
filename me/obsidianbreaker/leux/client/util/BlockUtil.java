package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BlockUtil {
  public static List emptyBlocks;
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static List rightclickableBlocks;
  
  public static void placeCrystalOnBlock(BlockPos paramBlockPos, EnumHand paramEnumHand) {
    (give up)null;
    RayTraceResult rayTraceResult = mc.field_71441_e.func_72933_a(new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v), new Vec3d(paramBlockPos.func_177958_n() + 0.5D, paramBlockPos.func_177956_o() - 0.5D, paramBlockPos.func_177952_p() + 0.5D));
    EnumFacing enumFacing = (rayTraceResult.field_178784_b == null) ? EnumFacing.UP : rayTraceResult.field_178784_b;
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItemOnBlock(paramBlockPos, enumFacing, paramEnumHand, Float.intBitsToFloat(2.1318665E9F ^ 0x7F11B351), Float.intBitsToFloat(2.12916198E9F ^ 0x7EE86EF1), Float.intBitsToFloat(2.13742106E9F ^ 0x7F6674D3)));
  }
  
  public static boolean lambda$isHole$0(Map.Entry paramEntry) {
    (give up)null;
    return (paramEntry.getValue() == BlockUtil$BlockSafety.RESISTANT);
  }
  
  public static void faceVectorPacketInstant(Vec3d paramVec3d) {
    (give up)null;
    float[] arrayOfFloat = getNeededRotations2(paramVec3d);
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(arrayOfFloat[0], arrayOfFloat[1], mc.field_71439_g.field_70122_E));
  }
  
  public static boolean isScaffoldPos(BlockPos paramBlockPos) {
    (give up)null;
    return (mc.field_71441_e.func_175623_d(paramBlockPos) || mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() == Blocks.field_150431_aC || mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() == Blocks.field_150329_H || mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() instanceof net.minecraft.block.BlockLiquid);
  }
  
  public static Vec3d getEyesPos() {
    (give up)null;
    return new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v);
  }
  
  public static boolean isBlockEmpty(BlockPos paramBlockPos) {
    (give up)null;
    if (emptyBlocks.contains(mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c())) {
      Entity entity;
      AxisAlignedBB axisAlignedBB = new AxisAlignedBB(paramBlockPos);
      Iterator<Entity> iterator = mc.field_71441_e.field_72996_f.iterator();
      do {
        if (!iterator.hasNext())
          return true; 
        entity = iterator.next();
      } while (!(entity instanceof net.minecraft.entity.EntityLivingBase) || !axisAlignedBB.func_72326_a(entity.func_174813_aQ()));
    } 
    return false;
  }
  
  public static boolean rayTracePlaceCheck(BlockPos paramBlockPos, boolean paramBoolean, float paramFloat) {
    (give up)null;
    return (mc.field_71441_e.func_147447_a(new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v), new Vec3d(paramBlockPos.func_177958_n(), (paramBlockPos.func_177956_o() + paramFloat), paramBlockPos.func_177952_p()), false, true, false) == null);
  }
  
  public static void openBlock(BlockPos paramBlockPos) {
    (give up)null;
    EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
    for (EnumFacing enumFacing : arrayOfEnumFacing) {
      Block block = mc.field_71441_e.func_180495_p(paramBlockPos.func_177972_a(enumFacing)).func_177230_c();
      if (emptyBlocks.contains(block)) {
        mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, paramBlockPos, enumFacing.func_176734_d(), new Vec3d((Vec3i)paramBlockPos), EnumHand.MAIN_HAND);
        return;
      } 
    } 
  }
  
  public static void rotatePacket(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    double d1 = paramDouble1 - mc.field_71439_g.field_70165_t;
    double d2 = paramDouble2 - mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e();
    double d3 = paramDouble3 - mc.field_71439_g.field_70161_v;
    double d4 = Math.sqrt(d1 * d1 + d3 * d3);
    float f1 = (float)Math.toDegrees(Math.atan2(d3, d1)) - Float.intBitsToFloat(1.03371974E9F ^ 0x7F294FBF);
    float f2 = (float)-Math.toDegrees(Math.atan2(d2, d4));
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(f1, f2, mc.field_71439_g.field_70122_E));
  }
  
  public static boolean canPlaceBlock(BlockPos paramBlockPos) {
    (give up)null;
    if (isBlockEmpty(paramBlockPos)) {
      EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
      for (EnumFacing enumFacing : arrayOfEnumFacing) {
        if (!emptyBlocks.contains(mc.field_71441_e.func_180495_p(paramBlockPos.func_177972_a(enumFacing)).func_177230_c()) && mc.field_71439_g.func_174824_e(mc.func_184121_ak()).func_72438_d(new Vec3d(paramBlockPos.func_177958_n() + 0.5D + enumFacing.func_82601_c() * 0.5D, paramBlockPos.func_177956_o() + 0.5D + enumFacing.func_96559_d() * 0.5D, paramBlockPos.func_177952_p() + 0.5D + enumFacing.func_82599_e() * 0.5D)) <= 4.25D)
          return true; 
      } 
    } 
    return false;
  }
  
  public static boolean canBeClicked(BlockPos paramBlockPos) {
    (give up)null;
    return mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c().func_176209_a(mc.field_71441_e.func_180495_p(paramBlockPos), false);
  }
  
  public static boolean lambda$isDoubleHole$1(Map.Entry paramEntry) {
    (give up)null;
    return (paramEntry.getValue() == BlockUtil$BlockSafety.RESISTANT);
  }
  
  public static BlockUtil$HoleInfo isDoubleHole(BlockUtil$HoleInfo paramBlockUtil$HoleInfo, BlockPos paramBlockPos, BlockUtil$BlockOffset paramBlockUtil$BlockOffset) {
    (give up)null;
    BlockPos blockPos = paramBlockUtil$BlockOffset.offset(paramBlockPos);
    HashMap hashMap = getUnsafeSides(blockPos);
    int i = hashMap.size();
    hashMap.entrySet().removeIf(BlockUtil::lambda$isDoubleHole$1);
    if (hashMap.size() != i)
      paramBlockUtil$HoleInfo.setSafety(BlockUtil$BlockSafety.RESISTANT); 
    if (hashMap.containsKey(BlockUtil$BlockOffset.DOWN)) {
      paramBlockUtil$HoleInfo.setType(BlockUtil$HoleType.CUSTOM);
      hashMap.remove(BlockUtil$BlockOffset.DOWN);
    } 
    if (hashMap.size() > 1) {
      paramBlockUtil$HoleInfo.setType(BlockUtil$HoleType.NONE);
      return paramBlockUtil$HoleInfo;
    } 
    double d1 = Math.min(paramBlockPos.func_177958_n(), blockPos.func_177958_n());
    double d2 = (Math.max(paramBlockPos.func_177958_n(), blockPos.func_177958_n()) + 1);
    double d3 = Math.min(paramBlockPos.func_177952_p(), blockPos.func_177952_p());
    double d4 = (Math.max(paramBlockPos.func_177952_p(), blockPos.func_177952_p()) + 1);
    paramBlockUtil$HoleInfo.setCentre(new AxisAlignedBB(d1, paramBlockPos.func_177956_o(), d3, d2, (paramBlockPos.func_177956_o() + 1), d4));
    if (paramBlockUtil$HoleInfo.getType() != BlockUtil$HoleType.CUSTOM)
      paramBlockUtil$HoleInfo.setType(BlockUtil$HoleType.DOUBLE); 
    return paramBlockUtil$HoleInfo;
  }
  
  public static float[] getNeededRotations2(Vec3d paramVec3d) {
    (give up)null;
    Vec3d vec3d = getEyesPos();
    double d1 = paramVec3d.field_72450_a - vec3d.field_72450_a;
    double d2 = paramVec3d.field_72448_b - vec3d.field_72448_b;
    double d3 = paramVec3d.field_72449_c - vec3d.field_72449_c;
    double d4 = Math.sqrt(d1 * d1 + d3 * d3);
    float f1 = (float)Math.toDegrees(Math.atan2(d3, d1)) - 90.0F;
    float f2 = (float)-Math.toDegrees(Math.atan2(d2, d4));
    return new float[] { mc.field_71439_g.field_70177_z + MathHelper.func_76142_g(f1 - mc.field_71439_g.field_70177_z), mc.field_71439_g.field_70125_A + MathHelper.func_76142_g(f2 - mc.field_71439_g.field_70125_A) };
  }
  
  public static boolean isValidBlock(BlockPos paramBlockPos) {
    (give up)null;
    Block block = mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c();
    return (!(block instanceof net.minecraft.block.BlockLiquid) && block.func_149688_o(null) != Material.field_151579_a);
  }
  
  public static void betterPlaceBlock(BlockPos paramBlockPos, boolean paramBoolean) {
    (give up)null;
    EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
    int i = arrayOfEnumFacing.length;
    byte b = 0;
    while (b < i) {
      EnumFacing enumFacing1 = arrayOfEnumFacing[b];
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing1);
      EnumFacing enumFacing2 = enumFacing1.func_176734_d();
      if (canBeClicked(blockPos)) {
        b++;
        continue;
      } 
      Vec3d vec3d = (new Vec3d((Vec3i)blockPos)).func_178787_e(new Vec3d(0.5D, 0.5D, 0.5D)).func_178787_e((new Vec3d(enumFacing2.func_176730_m())).func_186678_a(0.5D));
      faceVectorPacketInstant(vec3d);
      mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, paramBlockPos, enumFacing1, vec3d, EnumHand.MAIN_HAND);
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
      mc.field_71467_ac = 0;
      return;
    } 
  }
  
  public static boolean rayTracePlaceCheck(BlockPos paramBlockPos, boolean paramBoolean) {
    (give up)null;
    return rayTracePlaceCheck(paramBlockPos, paramBoolean, Float.intBitsToFloat(1.08609766E9F ^ 0x7F3C8931));
  }
  
  public static boolean canSeeBlock(BlockPos paramBlockPos) {
    (give up)null;
    return (mc.field_71439_g != null && mc.field_71441_e.func_147447_a(new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v), new Vec3d(paramBlockPos.func_177958_n(), paramBlockPos.func_177956_o(), paramBlockPos.func_177952_p()), false, true, false) == null);
  }
  
  public static HashMap getUnsafeSides(BlockPos paramBlockPos) {
    (give up)null;
    HashMap<Object, Object> hashMap = new HashMap<>();
    BlockUtil$BlockSafety blockUtil$BlockSafety = isBlockSafe(mc.field_71441_e.func_180495_p(BlockUtil$BlockOffset.DOWN.offset(paramBlockPos)).func_177230_c());
    if (blockUtil$BlockSafety != BlockUtil$BlockSafety.UNBREAKABLE)
      hashMap.put(BlockUtil$BlockOffset.DOWN, blockUtil$BlockSafety); 
    blockUtil$BlockSafety = isBlockSafe(mc.field_71441_e.func_180495_p(BlockUtil$BlockOffset.NORTH.offset(paramBlockPos)).func_177230_c());
    if (blockUtil$BlockSafety != BlockUtil$BlockSafety.UNBREAKABLE)
      hashMap.put(BlockUtil$BlockOffset.NORTH, blockUtil$BlockSafety); 
    blockUtil$BlockSafety = isBlockSafe(mc.field_71441_e.func_180495_p(BlockUtil$BlockOffset.SOUTH.offset(paramBlockPos)).func_177230_c());
    if (blockUtil$BlockSafety != BlockUtil$BlockSafety.UNBREAKABLE)
      hashMap.put(BlockUtil$BlockOffset.SOUTH, blockUtil$BlockSafety); 
    blockUtil$BlockSafety = isBlockSafe(mc.field_71441_e.func_180495_p(BlockUtil$BlockOffset.EAST.offset(paramBlockPos)).func_177230_c());
    if (blockUtil$BlockSafety != BlockUtil$BlockSafety.UNBREAKABLE)
      hashMap.put(BlockUtil$BlockOffset.EAST, blockUtil$BlockSafety); 
    blockUtil$BlockSafety = isBlockSafe(mc.field_71441_e.func_180495_p(BlockUtil$BlockOffset.WEST.offset(paramBlockPos)).func_177230_c());
    if (blockUtil$BlockSafety != BlockUtil$BlockSafety.UNBREAKABLE)
      hashMap.put(BlockUtil$BlockOffset.WEST, blockUtil$BlockSafety); 
    return hashMap;
  }
  
  public static boolean placeBlock(BlockPos paramBlockPos, int paramInt, boolean paramBoolean1, boolean paramBoolean2, Setting paramSetting) {
    (give up)null;
    if (isBlockEmpty(paramBlockPos)) {
      int i = -1;
      if (paramInt != mc.field_71439_g.field_71071_by.field_70461_c) {
        i = mc.field_71439_g.field_71071_by.field_70461_c;
        mc.field_71439_g.field_71071_by.field_70461_c = paramInt;
      } 
      EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
      for (EnumFacing enumFacing : arrayOfEnumFacing) {
        Block block = mc.field_71441_e.func_180495_p(paramBlockPos.func_177972_a(enumFacing)).func_177230_c();
        Vec3d vec3d = new Vec3d(paramBlockPos.func_177958_n() + 0.5D + enumFacing.func_82601_c() * 0.5D, paramBlockPos.func_177956_o() + 0.5D + enumFacing.func_96559_d() * 0.5D, paramBlockPos.func_177952_p() + 0.5D + enumFacing.func_82599_e() * 0.5D);
        if (!emptyBlocks.contains(block) && mc.field_71439_g.func_174824_e(mc.func_184121_ak()).func_72438_d(vec3d) <= 4.25D) {
          float[] arrayOfFloat = { mc.field_71439_g.field_70177_z, mc.field_71439_g.field_70125_A };
          rotatePacket(vec3d.field_72450_a, vec3d.field_72448_b, vec3d.field_72449_c);
          if (rightclickableBlocks.contains(block))
            mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING)); 
          mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, paramBlockPos.func_177972_a(enumFacing), enumFacing.func_176734_d(), new Vec3d((Vec3i)paramBlockPos), EnumHand.MAIN_HAND);
          if (rightclickableBlocks.contains(block))
            mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING)); 
          mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(arrayOfFloat[0], arrayOfFloat[1], mc.field_71439_g.field_70122_E));
          swingArm(paramSetting);
          -1;
          return true;
        } 
      } 
    } 
    return false;
  }
  
  public static BlockUtil$HoleInfo isHole(BlockPos paramBlockPos, boolean paramBoolean1, boolean paramBoolean2) {
    paramBoolean2 = true;
    paramBoolean1 = true;
    (give up)null;
    BlockUtil$HoleInfo blockUtil$HoleInfo = new BlockUtil$HoleInfo();
    HashMap hashMap = getUnsafeSides(paramBlockPos);
    if (hashMap.containsKey(BlockUtil$BlockOffset.DOWN) && hashMap.remove(BlockUtil$BlockOffset.DOWN, BlockUtil$BlockSafety.BREAKABLE)) {
      blockUtil$HoleInfo.setSafety(BlockUtil$BlockSafety.BREAKABLE);
      return blockUtil$HoleInfo;
    } 
    int i = hashMap.size();
    hashMap.entrySet().removeIf(BlockUtil::lambda$isHole$0);
    if (hashMap.size() != i)
      blockUtil$HoleInfo.setSafety(BlockUtil$BlockSafety.RESISTANT); 
    i = hashMap.size();
    blockUtil$HoleInfo.setType(BlockUtil$HoleType.SINGLE);
    blockUtil$HoleInfo.setCentre(new AxisAlignedBB(paramBlockPos));
    return blockUtil$HoleInfo;
  }
  
  public static BlockUtil$BlockSafety isBlockSafe(Block paramBlock) {
    (give up)null;
    return (paramBlock == Blocks.field_150357_h) ? BlockUtil$BlockSafety.UNBREAKABLE : ((paramBlock == Blocks.field_150343_Z || paramBlock == Blocks.field_150477_bB || paramBlock == Blocks.field_150467_bQ) ? BlockUtil$BlockSafety.RESISTANT : BlockUtil$BlockSafety.BREAKABLE);
  }
  
  public static void swingArm(Setting paramSetting) {
    (give up)null;
    if (paramSetting.in("Mainhand") || paramSetting.in("Both"))
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND); 
    if (paramSetting.in("Offhand") || paramSetting.in("Both"))
      mc.field_71439_g.func_184609_a(EnumHand.OFF_HAND); 
  }
  
  static {
    emptyBlocks = Arrays.asList(new Block[] { Blocks.field_150350_a, (Block)Blocks.field_150356_k, (Block)Blocks.field_150353_l, (Block)Blocks.field_150358_i, (Block)Blocks.field_150355_j, Blocks.field_150395_bd, Blocks.field_150431_aC, (Block)Blocks.field_150329_H, (Block)Blocks.field_150480_ab });
    rightclickableBlocks = Arrays.asList(new Block[] { 
          (Block)Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150477_bB, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, 
          Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.field_150467_bQ, 
          Blocks.field_150471_bO, Blocks.field_150430_aB, (Block)Blocks.field_150441_bU, (Block)Blocks.field_150413_aR, (Block)Blocks.field_150416_aS, (Block)Blocks.field_150455_bV, Blocks.field_180390_bo, Blocks.field_180391_bp, Blocks.field_180392_bq, Blocks.field_180386_br, 
          Blocks.field_180385_bs, Blocks.field_180387_bt, Blocks.field_150382_bo, Blocks.field_150367_z, Blocks.field_150409_cd, Blocks.field_150442_at, Blocks.field_150323_B, Blocks.field_150421_aI, (Block)Blocks.field_150461_bJ, Blocks.field_150324_C, 
          Blocks.field_150460_al, (Block)Blocks.field_180413_ao, (Block)Blocks.field_180414_ap, (Block)Blocks.field_180412_aq, (Block)Blocks.field_180411_ar, (Block)Blocks.field_180410_as, (Block)Blocks.field_180409_at, Blocks.field_150414_aQ, Blocks.field_150381_bn, Blocks.field_150380_bt, 
          (Block)Blocks.field_150438_bZ, Blocks.field_185776_dc, Blocks.field_150483_bI, Blocks.field_185777_dd, Blocks.field_150462_ai });
  }
}
