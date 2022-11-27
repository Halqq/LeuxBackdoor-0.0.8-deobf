package org.spongepowered.asm.lib;

public class TypePath {
  public static final int ARRAY_ELEMENT = 0;
  
  public static final int INNER_TYPE = 1;
  
  public static final int WILDCARD_BOUND = 2;
  
  public static final int TYPE_ARGUMENT = 3;
  
  byte[] b;
  
  int offset;
  
  TypePath(byte[] paramArrayOfbyte, int paramInt) {
    this.b = paramArrayOfbyte;
    this.offset = paramInt;
  }
  
  public int getLength() {
    return this.b[this.offset];
  }
  
  public int getStep(int paramInt) {
    paramInt = 0;
    return this.b[this.offset + 2 * paramInt + 1];
  }
  
  public int getStepArgument(int paramInt) {
    paramInt = 0;
    return this.b[this.offset + 2 * paramInt + 2];
  }
  
  public static TypePath fromString(String paramString) {
    if (paramString.length() == 0)
      return null; 
    int i = paramString.length();
    ByteVector byteVector = new ByteVector(i);
    byteVector.putByte(0);
    byte b = 0;
    while (b < i) {
      char c = paramString.charAt(b++);
      if (c == '[') {
        byteVector.put11(0, 0);
        continue;
      } 
      if (c == '.') {
        byteVector.put11(1, 0);
        continue;
      } 
      if (c == '*') {
        byteVector.put11(2, 0);
        continue;
      } 
      if (c >= '0' && c <= '9') {
        int j = c - 48;
        while (b < i && (c = paramString.charAt(b)) >= '0' && c <= '9') {
          j = j * 10 + c - 48;
          b++;
        } 
        if (b < i && paramString.charAt(b) == ';')
          b++; 
        byteVector.put11(3, j);
      } 
    } 
    byteVector.data[0] = (byte)(byteVector.length / 2);
    return new TypePath(byteVector.data, 0);
  }
  
  public String toString() {
    int i = getLength();
    StringBuilder stringBuilder = new StringBuilder(i * 2);
    for (byte b = 0; b < i; b++) {
      switch (getStep(b)) {
        case 0:
          stringBuilder.append('[');
          break;
        case 1:
          stringBuilder.append('.');
          break;
        case 2:
          stringBuilder.append('*');
          break;
        case 3:
          stringBuilder.append(getStepArgument(b)).append(';');
          break;
        default:
          stringBuilder.append('_');
          break;
      } 
    } 
    return stringBuilder.toString();
  }
}
