package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.TypePath;

public class FieldNode extends FieldVisitor {
  public int access;
  
  public String name;
  
  public String desc;
  
  public String signature;
  
  public Object value;
  
  public List visibleAnnotations;
  
  public List invisibleAnnotations;
  
  public List visibleTypeAnnotations;
  
  public List invisibleTypeAnnotations;
  
  public List attrs;
  
  public FieldNode(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject) {
    this(327680, paramInt, paramString1, paramString2, paramString3, paramObject);
    if (getClass() != FieldNode.class)
      throw new IllegalStateException(); 
  }
  
  public FieldNode(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, Object paramObject) {
    super(paramInt1);
    this.access = paramInt2;
    this.name = paramString1;
    this.desc = paramString2;
    this.signature = paramString3;
    this.value = paramObject;
  }
  
  public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
    AnnotationNode annotationNode = new AnnotationNode(paramString);
    if (this.visibleAnnotations == null)
      this.visibleAnnotations = new ArrayList(1); 
    this.visibleAnnotations.add(annotationNode);
    return annotationNode;
  }
  
  public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(paramInt, paramTypePath, paramString);
    if (this.visibleTypeAnnotations == null)
      this.visibleTypeAnnotations = new ArrayList(1); 
    this.visibleTypeAnnotations.add(typeAnnotationNode);
    return typeAnnotationNode;
  }
  
  public void visitAttribute(Attribute paramAttribute) {
    if (this.attrs == null)
      this.attrs = new ArrayList(1); 
    this.attrs.add(paramAttribute);
  }
  
  public void visitEnd() {}
  
  public void check(int paramInt) {
    if (paramInt == 262144) {
      if (this.visibleTypeAnnotations != null && this.visibleTypeAnnotations.size() > 0)
        throw new RuntimeException(); 
      if (this.invisibleTypeAnnotations != null && this.invisibleTypeAnnotations.size() > 0)
        throw new RuntimeException(); 
    } 
  }
  
  public void accept(ClassVisitor paramClassVisitor) {
    FieldVisitor fieldVisitor = paramClassVisitor.visitField(this.access, this.name, this.desc, this.signature, this.value);
  }
}
