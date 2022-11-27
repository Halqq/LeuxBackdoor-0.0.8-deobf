package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.lib.MethodVisitor;

public abstract class AbstractInsnNode {
  public static final int INSN = 0;
  
  public static final int INT_INSN = 1;
  
  public static final int VAR_INSN = 2;
  
  public static final int TYPE_INSN = 3;
  
  public static final int FIELD_INSN = 4;
  
  public static final int METHOD_INSN = 5;
  
  public static final int INVOKE_DYNAMIC_INSN = 6;
  
  public static final int JUMP_INSN = 7;
  
  public static final int LABEL = 8;
  
  public static final int LDC_INSN = 9;
  
  public static final int IINC_INSN = 10;
  
  public static final int TABLESWITCH_INSN = 11;
  
  public static final int LOOKUPSWITCH_INSN = 12;
  
  public static final int MULTIANEWARRAY_INSN = 13;
  
  public static final int FRAME = 14;
  
  public static final int LINE = 15;
  
  protected int opcode;
  
  public List visibleTypeAnnotations;
  
  public List invisibleTypeAnnotations;
  
  AbstractInsnNode prev;
  
  AbstractInsnNode next;
  
  int index;
  
  protected AbstractInsnNode(int paramInt) {
    this.opcode = paramInt;
    this.index = -1;
  }
  
  public int getOpcode() {
    return this.opcode;
  }
  
  public abstract int getType();
  
  public AbstractInsnNode getPrevious() {
    return this.prev;
  }
  
  public AbstractInsnNode getNext() {
    return this.next;
  }
  
  public abstract void accept(MethodVisitor paramMethodVisitor);
  
  protected final void acceptAnnotations(MethodVisitor paramMethodVisitor) {
    byte b1 = (this.visibleTypeAnnotations == null) ? 0 : this.visibleTypeAnnotations.size();
    byte b2;
    for (b2 = 0; b2 < b1; b2++) {
      TypeAnnotationNode typeAnnotationNode = this.visibleTypeAnnotations.get(b2);
      typeAnnotationNode.accept(paramMethodVisitor.visitInsnAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, true));
    } 
    b1 = (this.invisibleTypeAnnotations == null) ? 0 : this.invisibleTypeAnnotations.size();
    for (b2 = 0; b2 < b1; b2++) {
      TypeAnnotationNode typeAnnotationNode = this.invisibleTypeAnnotations.get(b2);
      typeAnnotationNode.accept(paramMethodVisitor.visitInsnAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, false));
    } 
  }
  
  public abstract AbstractInsnNode clone(Map paramMap);
  
  static LabelNode clone(LabelNode paramLabelNode, Map paramMap) {
    return (LabelNode)paramMap.get(paramLabelNode);
  }
  
  static LabelNode[] clone(List paramList, Map paramMap) {
    LabelNode[] arrayOfLabelNode = new LabelNode[paramList.size()];
    for (byte b = 0; b < arrayOfLabelNode.length; b++)
      arrayOfLabelNode[b] = (LabelNode)paramMap.get(paramList.get(b)); 
    return arrayOfLabelNode;
  }
  
  protected final AbstractInsnNode cloneAnnotations(AbstractInsnNode paramAbstractInsnNode) {
    if (paramAbstractInsnNode.visibleTypeAnnotations != null) {
      this.visibleTypeAnnotations = new ArrayList();
      for (byte b = 0; b < paramAbstractInsnNode.visibleTypeAnnotations.size(); b++) {
        TypeAnnotationNode typeAnnotationNode1 = paramAbstractInsnNode.visibleTypeAnnotations.get(b);
        TypeAnnotationNode typeAnnotationNode2 = new TypeAnnotationNode(typeAnnotationNode1.typeRef, typeAnnotationNode1.typePath, typeAnnotationNode1.desc);
        typeAnnotationNode1.accept(typeAnnotationNode2);
        this.visibleTypeAnnotations.add(typeAnnotationNode2);
      } 
    } 
    if (paramAbstractInsnNode.invisibleTypeAnnotations != null) {
      this.invisibleTypeAnnotations = new ArrayList();
      for (byte b = 0; b < paramAbstractInsnNode.invisibleTypeAnnotations.size(); b++) {
        TypeAnnotationNode typeAnnotationNode1 = paramAbstractInsnNode.invisibleTypeAnnotations.get(b);
        TypeAnnotationNode typeAnnotationNode2 = new TypeAnnotationNode(typeAnnotationNode1.typeRef, typeAnnotationNode1.typePath, typeAnnotationNode1.desc);
        typeAnnotationNode1.accept(typeAnnotationNode2);
        this.invisibleTypeAnnotations.add(typeAnnotationNode2);
      } 
    } 
    return this;
  }
}
