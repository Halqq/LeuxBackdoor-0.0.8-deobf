package org.spongepowered.asm.lib;

public class Label {
  static final int DEBUG = 1;
  
  static final int RESOLVED = 2;
  
  static final int RESIZED = 4;
  
  static final int PUSHED = 8;
  
  static final int TARGET = 16;
  
  static final int STORE = 32;
  
  static final int REACHABLE = 64;
  
  static final int JSR = 128;
  
  static final int RET = 256;
  
  static final int SUBROUTINE = 512;
  
  static final int VISITED = 1024;
  
  static final int VISITED2 = 2048;
  
  public Object info;
  
  int status;
  
  int line;
  
  int position;
  
  private int referenceCount;
  
  private int[] srcAndRefPositions;
  
  int inputStackTop;
  
  int outputStackMax;
  
  Frame frame;
  
  Label successor;
  
  Edge successors;
  
  Label next;
  
  public int getOffset() {
    if ((this.status & 0x2) == 0)
      throw new IllegalStateException("Label offset position has not been resolved yet"); 
    return this.position;
  }
  
  void put(MethodWriter paramMethodWriter, ByteVector paramByteVector, int paramInt, boolean paramBoolean) {
    if ((this.status & 0x2) == 0) {
      addReference(-1 - paramInt, paramByteVector.length);
      paramByteVector.putInt(-1);
    } else {
      paramByteVector.putInt(this.position - paramInt);
    } 
  }
  
  private void addReference(int paramInt1, int paramInt2) {
    if (this.srcAndRefPositions == null)
      this.srcAndRefPositions = new int[6]; 
    if (this.referenceCount >= this.srcAndRefPositions.length) {
      int[] arrayOfInt = new int[this.srcAndRefPositions.length + 6];
      System.arraycopy(this.srcAndRefPositions, 0, arrayOfInt, 0, this.srcAndRefPositions.length);
      this.srcAndRefPositions = arrayOfInt;
    } 
    this.srcAndRefPositions[this.referenceCount++] = paramInt1;
    this.srcAndRefPositions[this.referenceCount++] = paramInt2;
  }
  
  boolean resolve(MethodWriter paramMethodWriter, int paramInt, byte[] paramArrayOfbyte) {
    boolean bool = false;
    this.status |= 0x2;
    this.position = paramInt;
    byte b = 0;
    while (b < this.referenceCount) {
      int i = this.srcAndRefPositions[b++];
      int j = this.srcAndRefPositions[b++];
      int k = paramInt - i;
      if (k < -32768 || k > 32767) {
        int m = paramArrayOfbyte[j - 1] & 0xFF;
        if (m <= 168) {
          paramArrayOfbyte[j - 1] = (byte)(m + 49);
        } else {
          paramArrayOfbyte[j - 1] = (byte)(m + 20);
        } 
        bool = true;
      } 
      paramArrayOfbyte[j++] = (byte)(k >>> 8);
      paramArrayOfbyte[j] = (byte)k;
    } 
    return bool;
  }
  
  Label getFirst() {
    return (this.frame == null) ? this : this.frame.owner;
  }
  
  boolean inSubroutine(long paramLong) {
    return ((this.status & 0x400) != 0) ? (((this.srcAndRefPositions[(int)(paramLong >>> 32L)] & (int)paramLong) != 0)) : false;
  }
  
  boolean inSameSubroutine(Label paramLabel) {
    if ((this.status & 0x400) == 0 || (paramLabel.status & 0x400) == 0)
      return false; 
    for (byte b = 0; b < this.srcAndRefPositions.length; b++) {
      if ((this.srcAndRefPositions[b] & paramLabel.srcAndRefPositions[b]) != 0)
        return true; 
    } 
    return false;
  }
  
  void addToSubroutine(long paramLong, int paramInt) {
    if ((this.status & 0x400) == 0) {
      this.status |= 0x400;
      this.srcAndRefPositions = new int[paramInt / 32 + 1];
    } 
    this.srcAndRefPositions[(int)(paramLong >>> 32L)] = this.srcAndRefPositions[(int)(paramLong >>> 32L)] | (int)paramLong;
  }
  
  void visitSubroutine(Label paramLabel, long paramLong, int paramInt) {
    Label label = this;
    while (true) {
      Label label1 = label;
      label = label1.next;
      label1.next = null;
      if ((label1.status & 0x800) != 0)
        continue; 
      label1.status |= 0x800;
      if ((label1.status & 0x100) != 0 && !label1.inSameSubroutine(paramLabel)) {
        Edge edge1 = new Edge();
        edge1.info = label1.inputStackTop;
        edge1.successor = paramLabel.successors.successor;
        edge1.next = label1.successors;
        label1.successors = edge1;
      } 
      for (Edge edge = label1.successors;; edge = edge.next) {
        if (((label1.status & 0x80) == 0 || edge != label1.successors.next) && edge.successor.next == null) {
          edge.successor.next = label;
          label = edge.successor;
        } 
      } 
      break;
    } 
  }
  
  public String toString() {
    return "L" + System.identityHashCode(this);
  }
}
