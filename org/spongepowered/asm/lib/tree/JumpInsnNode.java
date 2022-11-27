package org.spongepowered.asm.lib.tree;

import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;

public class JumpInsnNode extends AbstractInsnNode {
  public LabelNode label;
  
  public JumpInsnNode(int paramInt, LabelNode paramLabelNode) {
    super(paramInt);
    this.label = paramLabelNode;
  }
  
  public void setOpcode(int paramInt) {
    this.opcode = paramInt;
  }
  
  public int getType() {
    return 7;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitJumpInsn(this.opcode, this.label.getLabel());
    acceptAnnotations(paramMethodVisitor);
  }
  
  public AbstractInsnNode clone(Map paramMap) {
    return (new JumpInsnNode(this.opcode, clone(this.label, paramMap))).cloneAnnotations(this);
  }
}
