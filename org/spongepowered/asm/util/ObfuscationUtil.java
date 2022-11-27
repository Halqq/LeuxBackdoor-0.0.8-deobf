package org.spongepowered.asm.util;

public abstract class ObfuscationUtil {
  public static String mapDescriptor(String paramString, ObfuscationUtil$IClassRemapper paramObfuscationUtil$IClassRemapper) {
    return remapDescriptor(paramString, paramObfuscationUtil$IClassRemapper, false);
  }
  
  public static String unmapDescriptor(String paramString, ObfuscationUtil$IClassRemapper paramObfuscationUtil$IClassRemapper) {
    return remapDescriptor(paramString, paramObfuscationUtil$IClassRemapper, true);
  }
  
  private static String remapDescriptor(String paramString, ObfuscationUtil$IClassRemapper paramObfuscationUtil$IClassRemapper, boolean paramBoolean) {
    StringBuilder stringBuilder1 = new StringBuilder();
    StringBuilder stringBuilder2 = null;
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      if (c == ';') {
        stringBuilder1.append('L').append(remap(stringBuilder2.toString(), paramObfuscationUtil$IClassRemapper, paramBoolean)).append(';');
        stringBuilder2 = null;
      } else {
        stringBuilder2.append(c);
      } 
    } 
    throw new IllegalArgumentException("Invalid descriptor '" + paramString + "', missing ';'");
  }
  
  private static Object remap(String paramString, ObfuscationUtil$IClassRemapper paramObfuscationUtil$IClassRemapper, boolean paramBoolean) {}
}
