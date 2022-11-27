package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.MethodVisitor;

public class LocalVariableNode {
  public String name;
  
  public String desc;
  
  public String signature;
  
  public LabelNode start;
  
  public LabelNode end;
  
  public int index;
  
  public LocalVariableNode(String paramString1, String paramString2, String paramString3, LabelNode paramLabelNode1, LabelNode paramLabelNode2, int paramInt) {
    this.name = paramString1;
    this.desc = paramString2;
    this.signature = paramString3;
    this.start = paramLabelNode1;
    this.end = paramLabelNode2;
    this.index = paramInt;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitLocalVariable(this.name, this.desc, this.signature, this.start.getLabel(), this.end.getLabel(), this.index);
  }
}
