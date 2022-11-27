package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BlockInteractHelper {
  public static Minecraft mc;
  
  public static List shulkerList;
  
  public static List blackList = Arrays.asList(new Block[] { 
        Blocks.field_150477_bB, (Block)Blocks.field_150486_ae, Blocks.field_150447_bR, Blocks.field_150462_ai, Blocks.field_150467_bQ, Blocks.field_150382_bo, (Block)Blocks.field_150438_bZ, Blocks.field_150409_cd, Blocks.field_150367_z, Blocks.field_150415_aT, 
        Blocks.field_150381_bn });
  
  public static BlockInteractHelper$ValidResult valid(BlockPos paramBlockPos) {
    (give up)null;
    if (!mc.field_71441_e.func_72855_b(new AxisAlignedBB(paramBlockPos)))
      return BlockInteractHelper$ValidResult.NoEntityCollision; 
    if (!checkForNeighbours(paramBlockPos))
      return BlockInteractHelper$ValidResult.NoNeighbors; 
    IBlockState iBlockState = mc.field_71441_e.func_180495_p(paramBlockPos);
    if (iBlockState.func_177230_c() == Blocks.field_150350_a) {
      BlockPos[] arrayOfBlockPos = { paramBlockPos.func_177978_c(), paramBlockPos.func_177968_d(), paramBlockPos.func_177974_f(), paramBlockPos.func_177976_e(), paramBlockPos.func_177984_a(), paramBlockPos.func_177977_b() };
      for (BlockPos blockPos : arrayOfBlockPos) {
        IBlockState iBlockState1 = mc.field_71441_e.func_180495_p(blockPos);
        if (iBlockState1.func_177230_c() != Blocks.field_150350_a)
          for (EnumFacing enumFacing : EnumFacing.values()) {
            BlockPos blockPos1 = paramBlockPos.func_177972_a(enumFacing);
            if (mc.field_71441_e.func_180495_p(blockPos1).func_177230_c().func_176209_a(mc.field_71441_e.func_180495_p(blockPos1), false))
              return BlockInteractHelper$ValidResult.Ok; 
          }  
      } 
      return BlockInteractHelper$ValidResult.NoNeighbors;
    } 
    return BlockInteractHelper$ValidResult.AlreadyBlockThere;
  }
  
  public static Block getBlock(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return mc.field_71441_e.func_180495_p(new BlockPos(paramDouble1, paramDouble2, paramDouble3)).func_177230_c();
  }
  
  public static Vec3d getEyesPos() {
    (give up)null;
    return new Vec3d(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v);
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
  
  public static boolean checkForNeighbours(BlockPos paramBlockPos) {
    (give up)null;
    if (!hasNeighbour(paramBlockPos)) {
      for (EnumFacing enumFacing : EnumFacing.values()) {
        BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing);
        if (hasNeighbour(blockPos))
          return true; 
      } 
      return false;
    } 
    return true;
  }
  
  public static boolean hasNeighbour(BlockPos paramBlockPos) {
    (give up)null;
    for (EnumFacing enumFacing : EnumFacing.values()) {
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing);
      if (!mc.field_71441_e.func_180495_p(blockPos).func_185904_a().func_76222_j())
        return true; 
    } 
    return false;
  }
  
  static {
    shulkerList = Arrays.asList(new Block[] { 
          Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, 
          Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA });
    mc = Minecraft.func_71410_x();
  }
  
  public static List getSphere(BlockPos paramBlockPos, float paramFloat, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    // Byte code:
    //   0: ldc_w 0
    //   3: istore #5
    //   5: ldc_w 1
    //   8: istore #4
    //   10: ldc_w 0
    //   13: istore_3
    //   14: goto -> 17
    //   17: aconst_null
    //   18: checkcast give up
    //   21: pop
    //   22: new java/util/ArrayList
    //   25: dup
    //   26: invokespecial <init> : ()V
    //   29: astore #6
    //   31: aload_0
    //   32: invokevirtual func_177958_n : ()I
    //   35: istore #7
    //   37: aload_0
    //   38: invokevirtual func_177956_o : ()I
    //   41: istore #8
    //   43: aload_0
    //   44: invokevirtual func_177952_p : ()I
    //   47: istore #9
    //   49: iload #7
    //   51: fload_1
    //   52: f2i
    //   53: isub
    //   54: istore #10
    //   56: iload #10
    //   58: i2f
    //   59: iload #7
    //   61: i2f
    //   62: fload_1
    //   63: fadd
    //   64: fcmpg
    //   65: ifgt -> 268
    //   68: iload #9
    //   70: fload_1
    //   71: f2i
    //   72: isub
    //   73: istore #11
    //   75: iload #11
    //   77: i2f
    //   78: iload #9
    //   80: i2f
    //   81: fload_1
    //   82: fadd
    //   83: fcmpg
    //   84: ifgt -> 98
    //   87: iload #4
    //   89: pop
    //   90: iload #8
    //   92: fload_1
    //   93: f2i
    //   94: isub
    //   95: goto -> 106
    //   98: iinc #10, 1
    //   101: goto -> 56
    //   104: nop
    //   105: athrow
    //   106: istore #12
    //   108: iload #4
    //   110: pop
    //   111: iload #8
    //   113: i2f
    //   114: fload_1
    //   115: fadd
    //   116: goto -> 127
    //   119: nop
    //   120: nop
    //   121: athrow
    //   122: nop
    //   123: nop
    //   124: nop
    //   125: nop
    //   126: athrow
    //   127: fstore #13
    //   129: iload #12
    //   131: i2f
    //   132: fload #13
    //   134: fcmpg
    //   135: iflt -> 144
    //   138: iinc #11, 1
    //   141: goto -> 75
    //   144: iload #7
    //   146: iload #10
    //   148: isub
    //   149: iload #7
    //   151: iload #10
    //   153: isub
    //   154: imul
    //   155: iload #9
    //   157: iload #11
    //   159: isub
    //   160: iload #9
    //   162: iload #11
    //   164: isub
    //   165: imul
    //   166: iadd
    //   167: iload #4
    //   169: pop
    //   170: iload #8
    //   172: iload #12
    //   174: isub
    //   175: iload #8
    //   177: iload #12
    //   179: isub
    //   180: imul
    //   181: goto -> 188
    //   184: nop
    //   185: nop
    //   186: athrow
    //   187: athrow
    //   188: iadd
    //   189: i2d
    //   190: dstore #14
    //   192: dload #14
    //   194: fload_1
    //   195: fload_1
    //   196: fmul
    //   197: f2d
    //   198: dcmpg
    //   199: ifge -> 262
    //   202: iload_3
    //   203: pop
    //   204: dload #14
    //   206: fload_1
    //   207: ldc_w 1.10199258E9
    //   210: ldc_w 2117014107
    //   213: ixor
    //   214: invokestatic intBitsToFloat : (I)F
    //   217: fsub
    //   218: fload_1
    //   219: ldc_w 1.08573517E9
    //   222: ldc_w 2134311116
    //   225: ixor
    //   226: invokestatic intBitsToFloat : (I)F
    //   229: fsub
    //   230: fmul
    //   231: f2d
    //   232: dcmpg
    //   233: iflt -> 262
    //   236: new net/minecraft/util/math/BlockPos
    //   239: dup
    //   240: iload #10
    //   242: iload #12
    //   244: iload #5
    //   246: iadd
    //   247: iload #11
    //   249: invokespecial <init> : (III)V
    //   252: astore #16
    //   254: aload #6
    //   256: aload #16
    //   258: invokevirtual add : (Ljava/lang/Object;)Z
    //   261: pop
    //   262: iinc #12, 1
    //   265: goto -> 108
    //   268: aload #6
    //   270: areturn
    //   271: nop
    //   272: nop
    //   273: nop
    //   274: nop
    //   275: nop
    //   276: nop
    //   277: nop
    //   278: nop
    //   279: nop
    //   280: nop
    //   281: nop
    //   282: nop
    //   283: nop
    //   284: nop
    //   285: nop
    //   286: nop
    //   287: athrow
    //   288: nop
    //   289: nop
    //   290: nop
    //   291: nop
    //   292: nop
    //   293: nop
    //   294: nop
    //   295: nop
    //   296: nop
    //   297: nop
    //   298: nop
    //   299: nop
    //   300: nop
    //   301: nop
    //   302: nop
    //   303: nop
    //   304: nop
    //   305: nop
    //   306: nop
    //   307: nop
    //   308: nop
    //   309: nop
    //   310: nop
    //   311: nop
    //   312: nop
    //   313: nop
    //   314: nop
    //   315: nop
    //   316: nop
    //   317: nop
    //   318: nop
    //   319: nop
    //   320: nop
    //   321: nop
    //   322: nop
    //   323: nop
    //   324: nop
    //   325: nop
    //   326: athrow
    //   327: nop
    //   328: nop
    //   329: nop
    //   330: nop
    //   331: nop
    //   332: nop
    //   333: nop
    //   334: nop
    //   335: nop
    //   336: nop
    //   337: nop
    //   338: athrow
    //   339: nop
    //   340: nop
    //   341: nop
    //   342: nop
    //   343: nop
    //   344: nop
    //   345: athrow
    //   346: nop
    //   347: nop
    //   348: nop
    //   349: nop
    //   350: nop
    //   351: nop
    //   352: nop
    //   353: nop
    //   354: nop
    //   355: nop
    //   356: nop
    //   357: athrow
    //   358: nop
    //   359: nop
    //   360: nop
    //   361: nop
    //   362: nop
    //   363: nop
    //   364: nop
    //   365: nop
    //   366: nop
    //   367: nop
    //   368: athrow
    //   369: nop
    //   370: nop
    //   371: nop
    //   372: nop
    //   373: nop
    //   374: athrow
    //   375: nop
    //   376: athrow
    //   377: nop
    //   378: athrow
    //   379: nop
    //   380: nop
    //   381: nop
    //   382: nop
    //   383: nop
    //   384: nop
    //   385: nop
    //   386: nop
    //   387: nop
    //   388: nop
    //   389: athrow
    //   390: nop
    //   391: nop
    //   392: athrow
    //   393: nop
    //   394: nop
    //   395: nop
    //   396: nop
    //   397: athrow
    //   398: nop
    //   399: nop
    //   400: nop
    //   401: nop
    //   402: nop
    //   403: nop
    //   404: nop
    //   405: nop
    //   406: nop
    //   407: nop
    //   408: athrow
    //   409: nop
    //   410: nop
    //   411: nop
    //   412: nop
    //   413: nop
    //   414: athrow
    //   415: nop
    //   416: nop
    //   417: nop
    //   418: nop
    //   419: nop
    //   420: nop
    //   421: nop
    //   422: nop
    //   423: nop
    //   424: nop
    //   425: nop
    //   426: nop
    //   427: nop
    //   428: nop
    //   429: nop
    //   430: nop
    //   431: nop
    //   432: nop
    //   433: nop
    //   434: nop
    //   435: nop
    //   436: nop
    //   437: nop
    //   438: nop
    //   439: nop
    //   440: nop
    //   441: nop
    //   442: nop
    //   443: nop
    //   444: nop
    //   445: nop
    //   446: nop
    //   447: nop
    //   448: nop
    //   449: nop
    //   450: nop
    //   451: nop
    //   452: nop
    //   453: nop
    //   454: athrow
    //   455: nop
    //   456: nop
    //   457: athrow
    //   458: athrow
    //   459: nop
    //   460: nop
    //   461: nop
    //   462: nop
    //   463: nop
    //   464: nop
    //   465: nop
    //   466: nop
    //   467: nop
    //   468: nop
    //   469: nop
    //   470: nop
    //   471: nop
    //   472: athrow
    //   473: nop
    //   474: nop
    //   475: nop
    //   476: nop
    //   477: nop
    //   478: nop
    //   479: nop
    //   480: nop
    //   481: nop
    //   482: nop
    //   483: nop
    //   484: nop
    //   485: nop
    //   486: nop
    //   487: nop
    //   488: nop
    //   489: nop
    //   490: nop
    //   491: nop
    //   492: nop
    //   493: nop
    //   494: nop
    //   495: nop
    //   496: nop
    //   497: nop
    //   498: nop
    //   499: nop
    //   500: nop
    //   501: nop
    //   502: nop
    //   503: nop
    //   504: nop
    //   505: nop
    //   506: athrow
    //   507: nop
    //   508: nop
    //   509: nop
    //   510: nop
    //   511: nop
    //   512: nop
    //   513: nop
    //   514: nop
    //   515: nop
    //   516: nop
    //   517: nop
    //   518: nop
    //   519: nop
    //   520: nop
    //   521: nop
    //   522: nop
    //   523: nop
    //   524: nop
    //   525: nop
    //   526: nop
    //   527: nop
    //   528: nop
    //   529: nop
    //   530: nop
    //   531: nop
    //   532: athrow
    //   533: nop
    //   534: nop
    //   535: nop
    //   536: nop
    //   537: nop
    //   538: athrow
    //   539: nop
    //   540: nop
    //   541: athrow
  }
  
  public static IBlockState getState(BlockPos paramBlockPos) {
    (give up)null;
    return mc.field_71441_e.func_180495_p(paramBlockPos);
  }
  
  public static boolean isInterceptedByOther(BlockPos paramBlockPos) {
    (give up)null;
    Iterator<Entity> iterator = mc.field_71441_e.field_72996_f.iterator();
    while (true) {
      if (iterator.hasNext()) {
        Entity entity = iterator.next();
        if (!entity.equals(mc.field_71439_g) && (new AxisAlignedBB(paramBlockPos)).func_72326_a(entity.func_174813_aQ()))
          return true; 
        continue;
      } 
      return false;
    } 
  }
  
  public static boolean isIntercepted(BlockPos paramBlockPos) {
    (give up)null;
    for (Entity entity : mc.field_71441_e.field_72996_f) {
      if ((new AxisAlignedBB(paramBlockPos)).func_72326_a(entity.func_174813_aQ()))
        return true; 
    } 
    return false;
  }
  
  public static Block getBlock(BlockPos paramBlockPos) {
    (give up)null;
    return getState(paramBlockPos).func_177230_c();
  }
  
  public static float[] getLegitRotations(Vec3d paramVec3d) {
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
  
  public static BlockInteractHelper$PlaceResult place(BlockPos paramBlockPos, float paramFloat, boolean paramBoolean1, boolean paramBoolean2) {
    (give up)null;
    IBlockState iBlockState = mc.field_71441_e.func_180495_p(paramBlockPos);
    boolean bool1 = iBlockState.func_185904_a().func_76222_j();
    boolean bool2 = iBlockState.func_177230_c() instanceof net.minecraft.block.BlockSlab;
    return BlockInteractHelper$PlaceResult.NotReplaceable;
  }
  
  public static void placeBlock(BlockPos paramBlockPos) {
    (give up)null;
    for (EnumFacing enumFacing : EnumFacing.values()) {
      if (!mc.field_71441_e.func_180495_p(paramBlockPos.func_177972_a(enumFacing)).func_177230_c().equals(Blocks.field_150350_a) && !isIntercepted(paramBlockPos)) {
        Vec3d vec3d = new Vec3d(paramBlockPos.func_177958_n() + 0.5D + enumFacing.func_82601_c() * 0.5D, paramBlockPos.func_177956_o() + 0.5D + enumFacing.func_96559_d() * 0.5D, paramBlockPos.func_177952_p() + 0.5D + enumFacing.func_82599_e() * 0.5D);
        float[] arrayOfFloat = { mc.field_71439_g.field_70177_z, mc.field_71439_g.field_70125_A };
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(vec3d.field_72449_c - mc.field_71439_g.field_70161_v, vec3d.field_72450_a - mc.field_71439_g.field_70165_t)) - Float.intBitsToFloat(1.03790106E9F ^ 0x7F691D06), (float)-Math.toDegrees(Math.atan2(vec3d.field_72448_b - mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), Math.sqrt((vec3d.field_72450_a - mc.field_71439_g.field_70165_t) * (vec3d.field_72450_a - mc.field_71439_g.field_70165_t) + (vec3d.field_72449_c - mc.field_71439_g.field_70161_v) * (vec3d.field_72449_c - mc.field_71439_g.field_70161_v)))), mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING));
        mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, paramBlockPos.func_177972_a(enumFacing), enumFacing.func_176734_d(), new Vec3d((Vec3i)paramBlockPos), EnumHand.MAIN_HAND);
        mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(arrayOfFloat[0], arrayOfFloat[1], mc.field_71439_g.field_70122_E));
        return;
      } 
    } 
  }
  
  public static boolean canBeClicked(BlockPos paramBlockPos) {
    (give up)null;
    return getBlock(paramBlockPos).func_176209_a(getState(paramBlockPos), false);
  }
  
  public static EnumFacing getPlaceableSide(BlockPos paramBlockPos) {
    (give up)null;
    for (EnumFacing enumFacing : EnumFacing.values()) {
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing);
      if (mc.field_71441_e.func_180495_p(blockPos).func_177230_c().func_176209_a(mc.field_71441_e.func_180495_p(blockPos), false)) {
        IBlockState iBlockState = mc.field_71441_e.func_180495_p(blockPos);
        if (!iBlockState.func_185904_a().func_76222_j())
          return enumFacing; 
      } 
    } 
    return null;
  }
  
  public static void faceVectorPacketInstant(Vec3d paramVec3d) {
    (give up)null;
    float[] arrayOfFloat = getLegitRotations(paramVec3d);
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(arrayOfFloat[0], arrayOfFloat[1], mc.field_71439_g.field_70122_E));
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
  
  public static void faceVectorPacketInstant(Vec3d paramVec3d, Boolean paramBoolean) {
    (give up)null;
    float[] arrayOfFloat = getNeededRotations2(paramVec3d);
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Rotation(arrayOfFloat[0], paramBoolean.booleanValue() ? MathHelper.func_180184_b((int)arrayOfFloat[1], 360) : arrayOfFloat[1], mc.field_71439_g.field_70122_E));
  }
}
