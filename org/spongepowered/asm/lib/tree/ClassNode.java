package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.TypePath;

public class ClassNode extends ClassVisitor {
  public int version;
  
  public int access;
  
  public String name;
  
  public String signature;
  
  public String superName;
  
  public List interfaces = new ArrayList();
  
  public String sourceFile;
  
  public String sourceDebug;
  
  public String outerClass;
  
  public String outerMethod;
  
  public String outerMethodDesc;
  
  public List visibleAnnotations;
  
  public List invisibleAnnotations;
  
  public List visibleTypeAnnotations;
  
  public List invisibleTypeAnnotations;
  
  public List attrs;
  
  public List innerClasses = new ArrayList();
  
  public List fields = new ArrayList();
  
  public List methods = new ArrayList();
  
  public ClassNode() {
    this(327680);
    if (getClass() != ClassNode.class)
      throw new IllegalStateException(); 
  }
  
  public ClassNode(int paramInt) {
    super(paramInt);
  }
  
  public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    this.version = paramInt1;
    this.access = paramInt2;
    this.name = paramString1;
    this.signature = paramString2;
    this.superName = paramString3;
    this.interfaces.addAll(Arrays.asList(paramArrayOfString));
  }
  
  public void visitSource(String paramString1, String paramString2) {
    this.sourceFile = paramString1;
    this.sourceDebug = paramString2;
  }
  
  public void visitOuterClass(String paramString1, String paramString2, String paramString3) {
    this.outerClass = paramString1;
    this.outerMethod = paramString2;
    this.outerMethodDesc = paramString3;
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
  
  public void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt) {
    InnerClassNode innerClassNode = new InnerClassNode(paramString1, paramString2, paramString3, paramInt);
    this.innerClasses.add(innerClassNode);
  }
  
  public FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject) {
    FieldNode fieldNode = new FieldNode(paramInt, paramString1, paramString2, paramString3, paramObject);
    this.fields.add(fieldNode);
    return fieldNode;
  }
  
  public MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    MethodNode methodNode = new MethodNode(paramInt, paramString1, paramString2, paramString3, paramArrayOfString);
    this.methods.add(methodNode);
    return (MethodVisitor)methodNode;
  }
  
  public void visitEnd() {}
  
  public void check(int paramInt) {
    if (paramInt == 262144) {
      if (this.visibleTypeAnnotations != null && this.visibleTypeAnnotations.size() > 0)
        throw new RuntimeException(); 
      if (this.invisibleTypeAnnotations != null && this.invisibleTypeAnnotations.size() > 0)
        throw new RuntimeException(); 
      for (FieldNode fieldNode : this.fields)
        fieldNode.check(paramInt); 
      for (MethodNode methodNode : this.methods)
        methodNode.check(paramInt); 
    } 
  }
  
  public void accept(ClassVisitor paramClassVisitor) {
    String[] arrayOfString = new String[this.interfaces.size()];
    this.interfaces.toArray((Object[])arrayOfString);
    paramClassVisitor.visit(this.version, this.access, this.name, this.signature, this.superName, arrayOfString);
    if (this.sourceFile != null || this.sourceDebug != null)
      paramClassVisitor.visitSource(this.sourceFile, this.sourceDebug); 
    if (this.outerClass != null)
      paramClassVisitor.visitOuterClass(this.outerClass, this.outerMethod, this.outerMethodDesc); 
    byte b1 = (this.visibleAnnotations == null) ? 0 : this.visibleAnnotations.size();
    byte b2;
    for (b2 = 0; b2 < b1; b2++) {
      AnnotationNode annotationNode = this.visibleAnnotations.get(b2);
      annotationNode.accept(paramClassVisitor.visitAnnotation(annotationNode.desc, true));
    } 
    b1 = (this.invisibleAnnotations == null) ? 0 : this.invisibleAnnotations.size();
    for (b2 = 0; b2 < b1; b2++) {
      AnnotationNode annotationNode = this.invisibleAnnotations.get(b2);
      annotationNode.accept(paramClassVisitor.visitAnnotation(annotationNode.desc, false));
    } 
    b1 = (this.visibleTypeAnnotations == null) ? 0 : this.visibleTypeAnnotations.size();
    for (b2 = 0; b2 < b1; b2++) {
      TypeAnnotationNode typeAnnotationNode = this.visibleTypeAnnotations.get(b2);
      typeAnnotationNode.accept(paramClassVisitor.visitTypeAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, true));
    } 
    b1 = (this.invisibleTypeAnnotations == null) ? 0 : this.invisibleTypeAnnotations.size();
    for (b2 = 0; b2 < b1; b2++) {
      TypeAnnotationNode typeAnnotationNode = this.invisibleTypeAnnotations.get(b2);
      typeAnnotationNode.accept(paramClassVisitor.visitTypeAnnotation(typeAnnotationNode.typeRef, typeAnnotationNode.typePath, typeAnnotationNode.desc, false));
    } 
    b1 = (this.attrs == null) ? 0 : this.attrs.size();
    for (b2 = 0; b2 < b1; b2++)
      paramClassVisitor.visitAttribute(this.attrs.get(b2)); 
    for (b2 = 0; b2 < this.innerClasses.size(); b2++)
      ((InnerClassNode)this.innerClasses.get(b2)).accept(paramClassVisitor); 
    for (b2 = 0; b2 < this.fields.size(); b2++)
      ((FieldNode)this.fields.get(b2)).accept(paramClassVisitor); 
    for (b2 = 0; b2 < this.methods.size(); b2++)
      ((MethodNode)this.methods.get(b2)).accept(paramClassVisitor); 
    paramClassVisitor.visitEnd();
  }
}
