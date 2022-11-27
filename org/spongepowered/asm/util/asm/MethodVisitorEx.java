package org.spongepowered.asm.util.asm;

import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.util.Bytecode;

public class MethodVisitorEx extends MethodVisitor {
  public MethodVisitorEx(MethodVisitor paramMethodVisitor) {
    super(327680, paramMethodVisitor);
  }
  
  public void visitConstant(byte paramByte) {
    if (paramByte > -2 && paramByte < 6) {
      visitInsn(Bytecode.CONSTANTS_INT[paramByte + 1]);
      return;
    } 
    visitIntInsn(16, paramByte);
  }
}
