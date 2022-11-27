package org.spongepowered.asm.lib;

public class Attribute {
  public final String type;
  
  byte[] value;
  
  Attribute next;
  
  protected Attribute(String paramString) {
    this.type = paramString;
  }
  
  public boolean isUnknown() {
    return true;
  }
  
  public boolean isCodeAttribute() {
    return false;
  }
  
  protected Label[] getLabels() {
    return null;
  }
  
  protected Attribute read(ClassReader paramClassReader, int paramInt1, int paramInt2, char[] paramArrayOfchar, int paramInt3, Label[] paramArrayOfLabel) {
    Attribute attribute = new Attribute(this.type);
    attribute.value = new byte[paramInt2];
    System.arraycopy(paramClassReader.b, paramInt1, attribute.value, 0, paramInt2);
    return attribute;
  }
  
  protected ByteVector write(ClassWriter paramClassWriter, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    ByteVector byteVector = new ByteVector();
    byteVector.data = this.value;
    byteVector.length = this.value.length;
    return byteVector;
  }
  
  final int getCount() {
    byte b = 0;
    for (Attribute attribute = this;; attribute = attribute.next)
      b++; 
  }
  
  final int getSize(ClassWriter paramClassWriter, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    Attribute attribute = this;
    int i = 0;
    while (true) {
      paramClassWriter.newUTF8(attribute.type);
      i += (attribute.write(paramClassWriter, paramArrayOfbyte, paramInt1, paramInt2, paramInt3)).length + 6;
      attribute = attribute.next;
    } 
  }
  
  final void put(ClassWriter paramClassWriter, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, ByteVector paramByteVector) {
    for (Attribute attribute = this;; attribute = attribute.next) {
      ByteVector byteVector = attribute.write(paramClassWriter, paramArrayOfbyte, paramInt1, paramInt2, paramInt3);
      paramByteVector.putShort(paramClassWriter.newUTF8(attribute.type)).putInt(byteVector.length);
      paramByteVector.putByteArray(byteVector.data, 0, byteVector.length);
    } 
  }
}
