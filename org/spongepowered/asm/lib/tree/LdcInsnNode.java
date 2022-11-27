package org.spongepowered.asm.lib.tree;

import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;

public class LdcInsnNode extends AbstractInsnNode {
  public Object cst;
  
  public LdcInsnNode(Object paramObject) {
    super(18);
    this.cst = paramObject;
  }
  
  public int getType() {
    return 9;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitLdcInsn(this.cst);
    acceptAnnotations(paramMethodVisitor);
  }
  
  public AbstractInsnNode clone(Map paramMap) {
    return (new LdcInsnNode(this.cst)).cloneAnnotations(this);
  }
}
