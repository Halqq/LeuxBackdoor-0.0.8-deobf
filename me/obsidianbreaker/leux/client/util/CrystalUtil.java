package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class CrystalUtil {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static List getSphere(BlockPos paramBlockPos, float paramFloat, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) {
    // Byte code:
    //   0: ldc 0
    //   2: istore #5
    //   4: ldc 1
    //   6: istore #4
    //   8: ldc 0
    //   10: istore_3
    //   11: goto -> 14
    //   14: aconst_null
    //   15: checkcast give up
    //   18: pop
    //   19: new java/util/ArrayList
    //   22: dup
    //   23: invokespecial <init> : ()V
    //   26: astore #6
    //   28: aload_0
    //   29: invokevirtual func_177958_n : ()I
    //   32: istore #7
    //   34: aload_0
    //   35: invokevirtual func_177956_o : ()I
    //   38: istore #8
    //   40: aload_0
    //   41: invokevirtual func_177952_p : ()I
    //   44: istore #9
    //   46: iload #7
    //   48: fload_1
    //   49: f2i
    //   50: isub
    //   51: istore #10
    //   53: iload #10
    //   55: i2f
    //   56: iload #7
    //   58: i2f
    //   59: fload_1
    //   60: fadd
    //   61: fcmpg
    //   62: ifgt -> 253
    //   65: iload #9
    //   67: fload_1
    //   68: f2i
    //   69: isub
    //   70: istore #11
    //   72: iload #11
    //   74: i2f
    //   75: iload #9
    //   77: i2f
    //   78: fload_1
    //   79: fadd
    //   80: fcmpg
    //   81: ifgt -> 95
    //   84: iload #4
    //   86: pop
    //   87: iload #8
    //   89: fload_1
    //   90: f2i
    //   91: isub
    //   92: goto -> 103
    //   95: iinc #10, 1
    //   98: goto -> 53
    //   101: nop
    //   102: athrow
    //   103: istore #12
    //   105: iload #12
    //   107: i2f
    //   108: iload #4
    //   110: pop
    //   111: iload #8
    //   113: i2f
    //   114: fload_1
    //   115: fadd
    //   116: goto -> 124
    //   119: nop
    //   120: nop
    //   121: nop
    //   122: nop
    //   123: athrow
    //   124: fcmpg
    //   125: ifge -> 247
    //   128: iload #7
    //   130: iload #10
    //   132: isub
    //   133: iload #7
    //   135: iload #10
    //   137: isub
    //   138: imul
    //   139: iload #9
    //   141: iload #11
    //   143: isub
    //   144: iload #9
    //   146: iload #11
    //   148: isub
    //   149: imul
    //   150: iadd
    //   151: iload #4
    //   153: pop
    //   154: iload #8
    //   156: iload #12
    //   158: isub
    //   159: iload #8
    //   161: iload #12
    //   163: isub
    //   164: imul
    //   165: goto -> 169
    //   168: athrow
    //   169: iadd
    //   170: i2d
    //   171: dstore #13
    //   173: dload #13
    //   175: fload_1
    //   176: fload_1
    //   177: fmul
    //   178: f2d
    //   179: dcmpg
    //   180: ifge -> 241
    //   183: iload_3
    //   184: pop
    //   185: dload #13
    //   187: fload_1
    //   188: ldc 1.11556915E9
    //   190: ldc 2113813463
    //   192: ixor
    //   193: invokestatic intBitsToFloat : (I)F
    //   196: fsub
    //   197: fload_1
    //   198: ldc 1.08725222E9
    //   200: ldc 2135828271
    //   202: ixor
    //   203: invokestatic intBitsToFloat : (I)F
    //   206: fsub
    //   207: fmul
    //   208: f2d
    //   209: dcmpl
    //   210: iflt -> 241
    //   213: new net/minecraft/util/math/BlockPos
    //   216: dup
    //   217: iload #10
    //   219: iload #12
    //   221: iload #5
    //   223: iadd
    //   224: iload #11
    //   226: invokespecial <init> : (III)V
    //   229: astore #15
    //   231: aload #6
    //   233: aload #15
    //   235: invokeinterface add : (Ljava/lang/Object;)Z
    //   240: pop
    //   241: iinc #12, 1
    //   244: goto -> 105
    //   247: iinc #11, 1
    //   250: goto -> 72
    //   253: aload #6
    //   255: areturn
    //   256: nop
    //   257: nop
    //   258: nop
    //   259: nop
    //   260: nop
    //   261: nop
    //   262: nop
    //   263: nop
    //   264: nop
    //   265: nop
    //   266: nop
    //   267: nop
    //   268: nop
    //   269: athrow
    //   270: nop
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
    //   287: nop
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
    //   308: athrow
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
    //   320: athrow
    //   321: nop
    //   322: nop
    //   323: nop
    //   324: nop
    //   325: nop
    //   326: nop
    //   327: athrow
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
    //   338: nop
    //   339: athrow
    //   340: nop
    //   341: nop
    //   342: nop
    //   343: nop
    //   344: nop
    //   345: nop
    //   346: nop
    //   347: nop
    //   348: nop
    //   349: nop
    //   350: athrow
    //   351: nop
    //   352: nop
    //   353: nop
    //   354: nop
    //   355: nop
    //   356: athrow
    //   357: nop
    //   358: athrow
    //   359: nop
    //   360: athrow
    //   361: nop
    //   362: nop
    //   363: nop
    //   364: nop
    //   365: nop
    //   366: nop
    //   367: nop
    //   368: nop
    //   369: nop
    //   370: nop
    //   371: nop
    //   372: nop
    //   373: nop
    //   374: athrow
    //   375: nop
    //   376: nop
    //   377: nop
    //   378: nop
    //   379: athrow
    //   380: nop
    //   381: nop
    //   382: nop
    //   383: athrow
    //   384: nop
    //   385: nop
    //   386: nop
    //   387: nop
    //   388: nop
    //   389: nop
    //   390: nop
    //   391: nop
    //   392: nop
    //   393: nop
    //   394: nop
    //   395: nop
    //   396: nop
    //   397: nop
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
    //   408: nop
    //   409: nop
    //   410: nop
    //   411: nop
    //   412: nop
    //   413: nop
    //   414: nop
    //   415: nop
    //   416: nop
    //   417: nop
    //   418: nop
    //   419: nop
    //   420: nop
    //   421: nop
    //   422: nop
    //   423: athrow
    //   424: athrow
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
    //   438: athrow
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
    //   454: nop
    //   455: nop
    //   456: nop
    //   457: nop
    //   458: nop
    //   459: nop
    //   460: nop
    //   461: nop
    //   462: nop
    //   463: nop
    //   464: nop
    //   465: nop
    //   466: nop
    //   467: nop
    //   468: athrow
    //   469: nop
    //   470: nop
    //   471: nop
    //   472: nop
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
    //   496: athrow
    //   497: nop
    //   498: nop
    //   499: nop
    //   500: nop
    //   501: nop
    //   502: athrow
    //   503: nop
    //   504: nop
    //   505: nop
    //   506: nop
    //   507: nop
    //   508: athrow
    //   509: nop
    //   510: nop
    //   511: athrow
  }
  
  public static NonNullList possiblePlacePositions(float paramFloat, boolean paramBoolean) {
    (give up)null;
    NonNullList nonNullList = NonNullList.func_191196_a();
    nonNullList.addAll((Collection)getSphere(getPlayerPos((EntityPlayer)mc.field_71439_g), paramFloat, (int)paramFloat, false, true, 0).stream().filter(paramBoolean::lambda$possiblePlacePositions$1).collect(Collectors.toList()));
    return nonNullList;
  }
  
  public static boolean canPlaceCrystal(BlockPos paramBlockPos, boolean paramBoolean1, boolean paramBoolean2) {
    (give up)null;
    BlockPos blockPos1 = paramBlockPos.func_177982_a(0, 1, 0);
    BlockPos blockPos2 = paramBlockPos.func_177982_a(0, 2, 0);
    BlockPos blockPos3 = paramBlockPos.func_177982_a(0, 3, 0);
    if (mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() != Blocks.field_150357_h && mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() != Blocks.field_150343_Z)
      return false; 
    if (mc.field_71441_e.func_180495_p(blockPos1).func_177230_c() == Blocks.field_150350_a) {
      if (mc.field_71441_e.func_180495_p(blockPos2).func_177230_c() != Blocks.field_150350_a)
        return false; 
    } else {
      return false;
    } 
    return (mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(blockPos1)).isEmpty() && mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty());
  }
  
  public static float calculateDamage(double paramDouble1, double paramDouble2, double paramDouble3, Entity paramEntity) {
    (give up)null;
    if (paramEntity == mc.field_71439_g && mc.field_71439_g.field_71075_bZ.field_75098_d)
      return Float.intBitsToFloat(2.09767846E9F ^ 0x7D0808BF); 
    Float.intBitsToFloat(1.0659433E9F ^ 0x7EC900E3);
    double d1 = paramEntity.func_70011_f(paramDouble1, paramDouble2, paramDouble3) / 12.0D;
    Vec3d vec3d = new Vec3d(paramDouble1, paramDouble2, paramDouble3);
    double d2 = 0.0D;
    d2 = paramEntity.field_70170_p.func_72842_a(vec3d, paramEntity.func_174813_aQ());
    double d3 = (1.0D - d1) * d2;
    float f = (int)((d3 * d3 + d3) / 2.0D * 7.0D * 12.0D + 1.0D);
    double d4 = 1.0D;
    if (paramEntity instanceof EntityLivingBase)
      d4 = getBlastReduction((EntityLivingBase)paramEntity, getDamageMultiplied(f), new Explosion((World)mc.field_71441_e, null, paramDouble1, paramDouble2, paramDouble3, Float.intBitsToFloat(1.0655609E9F ^ 0x7F432B3B), false, true)); 
    return (float)d4;
  }
  
  public static float getBlastReduction(EntityLivingBase paramEntityLivingBase, float paramFloat, Explosion paramExplosion) {
    (give up)null;
    null = paramFloat;
    if (paramEntityLivingBase instanceof EntityPlayer) {
      EntityPlayer entityPlayer = (EntityPlayer)paramEntityLivingBase;
      DamageSource damageSource = DamageSource.func_94539_a(paramExplosion);
      null = CombatRules.func_189427_a(null, entityPlayer.func_70658_aO(), (float)entityPlayer.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
      int i = 0;
      i = EnchantmentHelper.func_77508_a(entityPlayer.func_184193_aE(), damageSource);
      float f = MathHelper.func_76131_a(i, Float.intBitsToFloat(2.13276698E9F ^ 0x7F1F70FD), Float.intBitsToFloat(1.02519443E9F ^ 0x7CBB39DF));
      null *= Float.intBitsToFloat(1.16333568E9F ^ 0x7AD717FF) - f / Float.intBitsToFloat(1.06678586E9F ^ 0x7E5DDC23);
      if (paramEntityLivingBase.func_70644_a(MobEffects.field_76429_m))
        null -= null / Float.intBitsToFloat(1.05199757E9F ^ 0x7E34359F); 
      return Math.max(null, Float.intBitsToFloat(2.13846221E9F ^ 0x7F7657E9));
    } 
    return CombatRules.func_189427_a(null, paramEntityLivingBase.func_70658_aO(), (float)paramEntityLivingBase.func_110148_a(SharedMonsterAttributes.field_189429_h).func_111126_e());
  }
  
  public static float getDamageMultiplied(float paramFloat) {
    (give up)null;
    int i = mc.field_71441_e.func_175659_aa().func_151525_a();
  }
  
  public static boolean canPlaceCrystal(BlockPos paramBlockPos) {
    (give up)null;
    Block block = mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c();
    if (block == Blocks.field_150343_Z || block == Blocks.field_150357_h) {
      Block block1 = mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 1, 0)).func_177230_c();
      Block block2 = mc.field_71441_e.func_180495_p(paramBlockPos.func_177982_a(0, 2, 0)).func_177230_c();
      if (block1 == Blocks.field_150350_a && block2 == Blocks.field_150350_a && mc.field_71441_e.func_72839_b(null, new AxisAlignedBB(paramBlockPos.func_177982_a(0, 1, 0))).isEmpty() && mc.field_71441_e.func_72839_b(null, new AxisAlignedBB(paramBlockPos.func_177982_a(0, 2, 0))).isEmpty())
        return true; 
    } 
    return false;
  }
  
  public static boolean canPlaceCrystal(BlockPos paramBlockPos, boolean paramBoolean) {
    (give up)null;
    BlockPos blockPos1 = paramBlockPos.func_177982_a(0, 1, 0);
    BlockPos blockPos2 = paramBlockPos.func_177982_a(0, 2, 0);
    BlockPos blockPos3 = paramBlockPos.func_177982_a(0, 3, 0);
    if (mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() != Blocks.field_150357_h && mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() != Blocks.field_150343_Z)
      return false; 
    if (mc.field_71441_e.func_180495_p(blockPos1).func_177230_c() == Blocks.field_150350_a) {
      if (mc.field_71441_e.func_180495_p(blockPos2).func_177230_c() != Blocks.field_150350_a)
        return false; 
    } else {
      return false;
    } 
    for (Object object : mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(blockPos1))) {
      if (object instanceof EntityEnderCrystal)
        continue; 
      return false;
    } 
    for (Object object : mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(blockPos2))) {
      if (object instanceof EntityEnderCrystal)
        continue; 
      return false;
    } 
    for (Object object : mc.field_71441_e.func_72872_a(Entity.class, new AxisAlignedBB(blockPos3))) {
      if (!(object instanceof EntityEnderCrystal))
        continue; 
      return false;
    } 
    return true;
  }
  
  public static boolean lambda$possiblePlacePositions$1(boolean paramBoolean, BlockPos paramBlockPos) {
    (give up)null;
    return canPlaceCrystal(paramBlockPos, paramBoolean);
  }
  
  public static float calculateDamage(EntityEnderCrystal paramEntityEnderCrystal, Entity paramEntity) {
    (give up)null;
    return calculateDamage(paramEntityEnderCrystal.field_70165_t, paramEntityEnderCrystal.field_70163_u, paramEntityEnderCrystal.field_70161_v, paramEntity);
  }
  
  public static BlockPos getPlayerPos(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return new BlockPos(Math.floor(paramEntityPlayer.field_70165_t), Math.floor(paramEntityPlayer.field_70163_u), Math.floor(paramEntityPlayer.field_70161_v));
  }
  
  public static List possiblePlacePositions(float paramFloat, boolean paramBoolean1, boolean paramBoolean2) {
    // Byte code:
    //   0: ldc 1
    //   2: istore_2
    //   3: aconst_null
    //   4: checkcast give up
    //   7: pop
    //   8: invokestatic func_191196_a : ()Lnet/minecraft/util/NonNullList;
    //   11: astore_3
    //   12: aload_3
    //   13: getstatic me/obsidianbreaker/leux/client/util/CrystalUtil.mc : Lnet/minecraft/client/Minecraft;
    //   16: getfield field_71439_g : Lnet/minecraft/client/entity/EntityPlayerSP;
    //   19: invokestatic getPlayerPos : (Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/util/math/BlockPos;
    //   22: fload_0
    //   23: fload_0
    //   24: f2i
    //   25: iconst_0
    //   26: iconst_1
    //   27: iconst_0
    //   28: invokestatic getSphere : (Lnet/minecraft/util/math/BlockPos;FIZZI)Ljava/util/List;
    //   31: invokeinterface stream : ()Ljava/util/stream/Stream;
    //   36: iload_1
    //   37: iload_2
    //   38: <illegal opcode> test : (ZZ)Ljava/util/function/Predicate;
    //   43: invokeinterface filter : (Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
    //   48: invokestatic toList : ()Ljava/util/stream/Collector;
    //   51: invokeinterface collect : (Ljava/util/stream/Collector;)Ljava/lang/Object;
    //   56: checkcast java/util/Collection
    //   59: invokevirtual addAll : (Ljava/util/Collection;)Z
    //   62: pop
    //   63: aload_3
    //   64: areturn
    //   65: nop
    //   66: nop
    //   67: nop
    //   68: nop
    //   69: nop
    //   70: nop
    //   71: nop
    //   72: nop
    //   73: nop
    //   74: nop
    //   75: nop
    //   76: nop
    //   77: nop
    //   78: nop
    //   79: nop
    //   80: nop
    //   81: nop
    //   82: nop
    //   83: nop
    //   84: nop
    //   85: nop
    //   86: nop
    //   87: nop
    //   88: nop
    //   89: nop
    //   90: nop
    //   91: nop
    //   92: nop
    //   93: nop
    //   94: nop
    //   95: nop
    //   96: nop
    //   97: nop
    //   98: nop
    //   99: nop
    //   100: nop
    //   101: nop
    //   102: nop
    //   103: nop
    //   104: nop
    //   105: nop
    //   106: nop
    //   107: nop
    //   108: nop
    //   109: nop
    //   110: nop
    //   111: nop
    //   112: nop
    //   113: nop
    //   114: nop
    //   115: nop
    //   116: nop
    //   117: nop
    //   118: nop
    //   119: nop
    //   120: nop
    //   121: nop
    //   122: nop
    //   123: nop
    //   124: nop
    //   125: nop
    //   126: nop
    //   127: nop
    //   128: nop
    //   129: athrow
  }
  
  public static boolean lambda$possiblePlacePositions$0(boolean paramBoolean1, boolean paramBoolean2, BlockPos paramBlockPos) {
    (give up)null;
    return canPlaceCrystal(paramBlockPos, paramBoolean1, paramBoolean2);
  }
}
