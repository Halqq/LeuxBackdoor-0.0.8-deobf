package org.spongepowered.asm.lib.tree;

import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;

public class TypeInsnNode extends AbstractInsnNode {
  public String desc;
  
  public TypeInsnNode(int paramInt, String paramString) {
    super(paramInt);
    this.desc = paramString;
  }
  
  public void setOpcode(int paramInt) {
    this.opcode = paramInt;
  }
  
  public int getType() {
    return 3;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitTypeInsn(this.opcode, this.desc);
    acceptAnnotations(paramMethodVisitor);
  }
  
  public AbstractInsnNode clone(Map paramMap) {
    return (new TypeInsnNode(this.opcode, this.desc)).cloneAnnotations(this);
  }
}
