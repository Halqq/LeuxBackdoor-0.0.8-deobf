package me.obsidianbreaker.leux.client.turok;

import give up;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TurokMath {
  public static float lerp(float paramFloat1, float paramFloat2, float paramFloat3) {
    (give up)null;
    return paramFloat1 + (paramFloat2 - paramFloat1) * paramFloat3;
  }
  
  public static float normalize(float... paramVarArgs) {
    (give up)null;
    float f1 = Float.intBitsToFloat(2.12431782E9F ^ 0x7E9E849D);
    float f2 = Float.intBitsToFloat(2.08910682E9F ^ 0x7C853DBF);
    float[] arrayOfFloat = paramVarArgs;
    int i = arrayOfFloat.length;
    for (byte b = 0; b < i; b++) {
      float f = arrayOfFloat[b];
      f1 = f / f2 * f2;
    } 
    return f1;
  }
  
  public static int clamp(int paramInt1, int paramInt2, int paramInt3) {
    paramInt2 = 0;
    (give up)null;
    return (paramInt1 <= paramInt2) ? paramInt2 : ((paramInt1 >= paramInt3) ? paramInt3 : paramInt1);
  }
  
  public static float lerp(int paramInt1, int paramInt2, float paramFloat) {
    (give up)null;
    return paramInt1 + (paramInt2 - paramInt1) * paramFloat;
  }
  
  public static float clamp(float paramFloat1, float paramFloat2, float paramFloat3) {
    paramFloat3 = 255.0F;
    paramFloat2 = 0.0F;
    (give up)null;
    return (paramFloat1 <= paramFloat2) ? paramFloat2 : ((paramFloat1 >= paramFloat3) ? paramFloat3 : paramFloat1);
  }
  
  public static int ceiling(float paramFloat) {
    (give up)null;
    int i = (int)paramFloat;
    return (paramFloat >= i) ? (i + 1) : i;
  }
  
  public static double clamp(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    return (paramDouble1 <= paramDouble2) ? paramDouble2 : ((paramDouble1 >= paramDouble3) ? paramDouble3 : paramDouble1);
  }
  
  public static float sqrt(float paramFloat) {
    (give up)null;
    return (float)Math.sqrt(paramFloat);
  }
  
  public static double sqrt(double paramDouble) {
    (give up)null;
    return Math.sqrt(paramDouble);
  }
  
  public static int ceiling(double paramDouble) {
    (give up)null;
    int i = (int)paramDouble;
    return (paramDouble >= i) ? (i + 1) : i;
  }
  
  public static int sqrt(int paramInt) {
    (give up)null;
    return (int)Math.sqrt(paramInt);
  }
  
  public static int normalize(int... paramVarArgs) {
    (give up)null;
    int i = 0;
    int j = 0;
    int[] arrayOfInt = paramVarArgs;
    int k = arrayOfInt.length;
    for (byte b = 0; b < k; b++) {
      int m = arrayOfInt[b];
      i = m / j * j;
    } 
    return i;
  }
  
  public static double normalize(double... paramVarArgs) {
    // Byte code:
    //   0: aconst_null
    //   1: checkcast give up
    //   4: pop
    //   5: dconst_0
    //   6: dstore_1
    //   7: dconst_0
    //   8: dstore_3
    //   9: aload_0
    //   10: astore #5
    //   12: aload #5
    //   14: arraylength
    //   15: istore #6
    //   17: iconst_0
    //   18: istore #7
    //   20: iload #7
    //   22: iload #6
    //   24: if_icmpge -> 49
    //   27: aload #5
    //   29: iload #7
    //   31: daload
    //   32: dup2
    //   33: dstore #8
    //   35: dstore_3
    //   36: dload #8
    //   38: dload_3
    //   39: ddiv
    //   40: dload_3
    //   41: dmul
    //   42: dstore_1
    //   43: iinc #7, 1
    //   46: goto -> 20
    //   49: dload_1
    //   50: dreturn
    //   51: nop
    //   52: nop
    //   53: nop
    //   54: nop
    //   55: nop
    //   56: nop
    //   57: nop
    //   58: nop
    //   59: nop
    //   60: nop
    //   61: nop
    //   62: nop
    //   63: nop
    //   64: nop
    //   65: nop
    //   66: nop
    //   67: nop
    //   68: nop
    //   69: nop
    //   70: athrow
    //   71: nop
    //   72: nop
    //   73: nop
    //   74: nop
    //   75: nop
    //   76: nop
    //   77: athrow
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
    //   99: athrow
    //   100: nop
    //   101: athrow
  }
  
  public static double round(double paramDouble) {
    (give up)null;
    BigDecimal bigDecimal = new BigDecimal(paramDouble);
    bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
    return bigDecimal.doubleValue();
  }
}
