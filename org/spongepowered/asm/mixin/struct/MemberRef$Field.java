package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.lib.tree.FieldInsnNode;

public final class MemberRef$Field extends MemberRef {
  private static final int OPCODES = 183;
  
  public final FieldInsnNode insn;
  
  public MemberRef$Field(FieldInsnNode paramFieldInsnNode) {
    this.insn = paramFieldInsnNode;
  }
  
  public boolean isField() {
    return true;
  }
  
  public int getOpcode() {
    return this.insn.getOpcode();
  }
  
  public void setOpcode(int paramInt) {
    if ((paramInt & 0xB7) == 0)
      throw new IllegalArgumentException("Invalid opcode for field instruction: 0x" + Integer.toHexString(paramInt)); 
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
