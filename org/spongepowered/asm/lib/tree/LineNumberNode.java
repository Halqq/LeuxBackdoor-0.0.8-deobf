package org.spongepowered.asm.lib.tree;

import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;

public class LineNumberNode extends AbstractInsnNode {
  public int line;
  
  public LabelNode start;
  
  public LineNumberNode(int paramInt, LabelNode paramLabelNode) {
    super(-1);
    this.line = paramInt;
    this.start = paramLabelNode;
  }
  
  public int getType() {
    return 15;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitLineNumber(this.line, this.start.getLabel());
  }
  
  public AbstractInsnNode clone(Map paramMap) {
    return new LineNumberNode(this.line, clone(this.start, paramMap));
  }
}
