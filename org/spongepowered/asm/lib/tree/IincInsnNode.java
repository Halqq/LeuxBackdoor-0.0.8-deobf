package org.spongepowered.asm.lib.tree;

import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;

public class IincInsnNode extends AbstractInsnNode {
  public int var;
  
  public int incr;
  
  public IincInsnNode(int paramInt1, int paramInt2) {
    super(132);
    this.var = paramInt1;
    this.incr = paramInt2;
  }
  
  public int getType() {
    return 10;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitIincInsn(this.var, this.incr);
    acceptAnnotations(paramMethodVisitor);
  }
  
  public AbstractInsnNode clone(Map paramMap) {
    return (new IincInsnNode(this.var, this.incr)).cloneAnnotations(this);
  }
}
