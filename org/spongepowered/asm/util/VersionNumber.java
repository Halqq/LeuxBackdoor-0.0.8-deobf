package org.spongepowered.asm.util;

import java.io.Serializable;
import java.util.regex.Pattern;

public final class VersionNumber implements Comparable, Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final VersionNumber NONE = new VersionNumber();
  
  private static final Pattern PATTERN = Pattern.compile("^(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5}))?)?)?(-[a-zA-Z0-9_\\-]+)?$");
  
  private final long value;
  
  private final String suffix;
  
  private VersionNumber() {
    this.value = 0L;
    this.suffix = "";
  }
  
  private VersionNumber(short[] paramArrayOfshort) {
    this(paramArrayOfshort, null);
  }
  
  private VersionNumber(short[] paramArrayOfshort, String paramString) {
    short[] arrayOfShort = paramArrayOfshort;
    this.value = arrayOfShort[0] << 48L | arrayOfShort[1] << 32L | (arrayOfShort[2] << 16) | arrayOfShort[3];
  }
  
  private VersionNumber(short paramShort1, short paramShort2, short paramShort3, short paramShort4) {
    this(paramShort1, paramShort2, paramShort3, paramShort4, null);
  }
  
  private VersionNumber(short paramShort1, short paramShort2, short paramShort3, short paramShort4, String paramString) {
    short[] arrayOfShort = { paramShort1, paramShort2, paramShort3, paramShort4 };
    this.value = arrayOfShort[0] << 48L | arrayOfShort[1] << 32L | (arrayOfShort[2] << 16) | arrayOfShort[3];
  }
  
  public String toString() {
    short[] arrayOfShort = unpack(this.value);
    return String.format("%d.%d%3$s%4$s%5$s", new Object[] { Short.valueOf(arrayOfShort[0]), Short.valueOf(arrayOfShort[1]), ((this.value & 0x7FFFFFFFL) > 0L) ? String.format(".%d", new Object[] { Short.valueOf(arrayOfShort[2]) }) : "", ((this.value & 0x7FFFL) > 0L) ? String.format(".%d", new Object[] { Short.valueOf(arrayOfShort[3]) }) : "", this.suffix });
  }
  
  public int compareTo(VersionNumber paramVersionNumber) {
    return 1;
  }
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof VersionNumber) ? false : ((((VersionNumber)paramObject).value == this.value));
  }
  
  public int hashCode() {
    return (int)(this.value >> 32L) ^ (int)(this.value & 0xFFFFFFFFL);
  }
  
  private static short[] unpack(long paramLong) {
    return new short[] { (short)(int)(paramLong >> 48L), (short)(int)(paramLong >> 32L & 0x7FFFL), (short)(int)(paramLong >> 16L & 0x7FFFL), (short)(int)(paramLong & 0x7FFFL) };
  }
  
  public static VersionNumber parse(String paramString) {
    return parse(paramString, NONE);
  }
  
  public static VersionNumber parse(String paramString1, String paramString2) {
    return parse(paramString1, parse(paramString2));
  }
  
  private static VersionNumber parse(String paramString, VersionNumber paramVersionNumber) {
    return paramVersionNumber;
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((VersionNumber)paramObject);
  }
}
