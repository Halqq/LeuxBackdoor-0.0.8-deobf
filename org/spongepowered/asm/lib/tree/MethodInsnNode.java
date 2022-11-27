package org.spongepowered.asm.lib.tree;

import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;

public class MethodInsnNode extends AbstractInsnNode {
  public String owner;
  
  public String name;
  
  public String desc;
  
  public boolean itf;
  
  @Deprecated
  @Deprecated
  public MethodInsnNode(int paramInt, String paramString1, String paramString2, String paramString3) {
    this(paramInt, paramString1, paramString2, paramString3, (paramInt == 185));
  }
  
  public MethodInsnNode(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    super(paramInt);
    this.owner = paramString1;
    this.name = paramString2;
    this.desc = paramString3;
    this.itf = paramBoolean;
  }
  
  public void setOpcode(int paramInt) {
    this.opcode = paramInt;
  }
  
  public int getType() {
    return 5;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitMethodInsn(this.opcode, this.owner, this.name, this.desc, this.itf);
    acceptAnnotations(paramMethodVisitor);
  }
  
  public AbstractInsnNode clone(Map paramMap) {
    return new MethodInsnNode(this.opcode, this.owner, this.name, this.desc, this.itf);
  }
}
