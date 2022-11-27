package org.spongepowered.asm.lib.tree;

import java.util.List;
import org.spongepowered.asm.lib.MethodVisitor;

public class TryCatchBlockNode {
  public LabelNode start;
  
  public LabelNode end;
  
  public LabelNode handler;
  
  public String type;
  
  public List visibleTypeAnnotations;
  
  public List invisibleTypeAnnotations;
  
  public TryCatchBlockNode(LabelNode paramLabelNode1, LabelNode paramLabelNode2, LabelNode paramLabelNode3, String paramString) {
    this.start = paramLabelNode1;
    this.end = paramLabelNode2;
    this.handler = paramLabelNode3;
    this.type = paramString;
  }
  
  public void updateIndex(int paramInt) {
    int i = 0x42000000 | paramInt << 8;
    if (this.visibleTypeAnnotations != null)
      for (TypeAnnotationNode typeAnnotationNode : this.visibleTypeAnnotations)
        typeAnnotationNode.typeRef = i;  
    if (this.invisibleTypeAnnotations != null)
      for (TypeAnnotationNode typeAnnotationNode : this.invisibleTypeAnnotations)
        typeAnnotationNode.typeRef = i;  
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    paramMethodVisitor.visitTryCatchBlock(this.start.getLabel(), this.end.getLabel(), (this.handler == null) ? null : this.handler.getLabel(), this.type);
    byte b1 = (this.visibleTypeAnnotations == null) ? 0 : this.visibleTypeAnnotations.size();
    byte b2;
    for (b2 = 0; b2 < b1; b2++) {
      TypeAnnotationNode typeAnnotationNode = this.visibleTypeAnnotations.get(b2);
      typeAnnotationNode.accept(paramMethodVisitor.visitTryCatchAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, true));
    } 
    b1 = (this.invisibleTypeAnnotations == null) ? 0 : this.invisibleTypeAnnotations.size();
    for (b2 = 0; b2 < b1; b2++) {
      TypeAnnotationNode typeAnnotationNode = this.invisibleTypeAnnotations.get(b2);
      typeAnnotationNode.accept(paramMethodVisitor.visitTryCatchAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, false));
    } 
  }
}
