package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.lib.tree.MethodInsnNode;

public final class MemberRef$Method extends MemberRef {
  private static final int OPCODES = 191;
  
  public final MethodInsnNode insn;
  
  public MemberRef$Method(MethodInsnNode paramMethodInsnNode) {
    this.insn = paramMethodInsnNode;
  }
  
  public boolean isField() {
    return false;
  }
  
  public int getOpcode() {
    return this.insn.getOpcode();
  }
  
  public void setOpcode(int paramInt) {
    if ((paramInt & 0xBF) == 0)
      throw new IllegalArgumentException("Invalid opcode for method instruction: 0x" + Integer.toHexString(paramInt)); 
    this.insn.setOpcode(paramInt);
  }
  
  public String getOwner() {
    return this.insn.owner;
  }
  
  public void setOwner(String paramString) {
    this.insn.owner = paramString;
  }
  
  public String getName() {
    return this.insn.name;
  }
  
  public void setName(String paramString) {
    this.insn.name = paramString;
  }
  
  public String getDesc() {
    return this.insn.desc;
  }
  
  public void setDesc(String paramString) {
    this.insn.desc = paramString;
  }
}
