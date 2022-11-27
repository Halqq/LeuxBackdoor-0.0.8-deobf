package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.MethodVisitor;

public class ParameterNode {
  public String name;
  
  public int access;
  
  public ParameterNode(String paramString, int paramInt) {
    this.name = paramString;
    this.access = paramInt;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitParameter(this.name, this.access);
  }
}
