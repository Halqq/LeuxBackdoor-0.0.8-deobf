package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.util.Bytecode;

public final class MemberRef$Handle extends MemberRef {
  private org.spongepowered.asm.lib.Handle handle;
  
  public MemberRef$Handle(org.spongepowered.asm.lib.Handle paramHandle) {
    this.handle = paramHandle;
  }
  
  public org.spongepowered.asm.lib.Handle getMethodHandle() {
    return this.handle;
  }
  
  public boolean isField() {
    switch (this.handle.getTag()) {
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
        return false;
      case 1:
      case 2:
      case 3:
      case 4:
        return true;
    } 
    throw new MixinTransformerError("Invalid tag " + this.handle.getTag() + " for method handle " + this.handle + ".");
  }
  
  public int getOpcode() {
    int i = MemberRef.opcodeFromTag(this.handle.getTag());
    throw new MixinTransformerError("Invalid tag " + this.handle.getTag() + " for method handle " + this.handle + ".");
  }
  
  public void setOpcode(int paramInt) {
    int i = MemberRef.tagFromOpcode(paramInt);
    throw new MixinTransformerError("Invalid opcode " + Bytecode.getOpcodeName(paramInt) + " for method handle " + this.handle + ".");
  }
  
  public String getOwner() {
    return this.handle.getOwner();
  }
  
  public void setOwner(String paramString) {
    boolean bool = (this.handle.getTag() == 9) ? true : false;
    this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), paramString, this.handle.getName(), this.handle.getDesc(), bool);
  }
  
  public String getName() {
    return this.handle.getName();
  }
  
  public void setName(String paramString) {
    boolean bool = (this.handle.getTag() == 9) ? true : false;
    this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), this.handle.getOwner(), paramString, this.handle.getDesc(), bool);
  }
  
  public String getDesc() {
    return this.handle.getDesc();
  }
  
  public void setDesc(String paramString) {
    boolean bool = (this.handle.getTag() == 9) ? true : false;
    this.handle = new org.spongepowered.asm.lib.Handle(this.handle.getTag(), this.handle.getOwner(), this.handle.getName(), paramString, bool);
  }
}
