package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.util.Bytecode;

public abstract class MemberRef {
  private static final int[] H_OPCODES = new int[] { 0, 180, 178, 181, 179, 182, 184, 183, 183, 185 };
  
  public abstract boolean isField();
  
  public abstract int getOpcode();
  
  public abstract void setOpcode(int paramInt) {
    // Byte code:
    //   0: ldc 182
    //   2: istore_1
    //   3: ldc 182
    //   5: istore_1
  }
  
  public abstract String getOwner();
  
  public abstract void setOwner(String paramString);
  
  public abstract String getName();
  
  public abstract void setName(String paramString);
  
  public abstract String getDesc();
  
  public abstract void setDesc(String paramString);
  
  public String toString() {
    String str = Bytecode.getOpcodeName(getOpcode());
    return String.format("%s for %s.%s%s%s", new Object[] { str, getOwner(), getName(), isField() ? ":" : "", getDesc() });
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof MemberRef))
      return false; 
    MemberRef memberRef = (MemberRef)paramObject;
    return (getOpcode() == memberRef.getOpcode() && getOwner().equals(memberRef.getOwner()) && getName().equals(memberRef.getName()) && getDesc().equals(memberRef.getDesc()));
  }
  
  public int hashCode() {
    return toString().hashCode();
  }
  
  static int opcodeFromTag(int paramInt) {
    return (paramInt < H_OPCODES.length) ? H_OPCODES[paramInt] : 0;
  }
  
  static int tagFromOpcode(int paramInt) {
    for (byte b = 1; b < H_OPCODES.length; b++) {
      if (H_OPCODES[b] == paramInt)
        return b; 
    } 
    return 0;
  }
}
