package org.spongepowered.asm.lib.commons;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.TypePath;

public class ClassRemapper extends ClassVisitor {
  protected final Remapper remapper;
  
  protected String className;
  
  public ClassRemapper(ClassVisitor paramClassVisitor, Remapper paramRemapper) {
    this(327680, paramClassVisitor, paramRemapper);
  }
  
  protected ClassRemapper(int paramInt, ClassVisitor paramClassVisitor, Remapper paramRemapper) {
    super(paramInt, paramClassVisitor);
    this.remapper = paramRemapper;
  }
  
  public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    this.className = paramString1;
  }
  
  public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
    AnnotationVisitor annotationVisitor = super.visitAnnotation(this.remapper.mapDesc(paramString), paramBoolean);
  }
  
  public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    AnnotationVisitor annotationVisitor = super.visitTypeAnnotation(paramInt, paramTypePath, this.remapper.mapDesc(paramString), paramBoolean);
  }
  
  public FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject) {
    FieldVisitor fieldVisitor = super.visitField(paramInt, this.remapper.mapFieldName(this.className, paramString1, paramString2), this.remapper.mapDesc(paramString2), this.remapper.mapSignature(paramString3, true), this.remapper.mapValue(paramObject));
  }
  
  public MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    String str = this.remapper.mapMethodDesc(paramString2);
  }
  
  public void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt) {}
  
  public void visitOuterClass(String paramString1, String paramString2, String paramString3) {}
  
  protected FieldVisitor createFieldRemapper(FieldVisitor paramFieldVisitor) {
    return (FieldVisitor)new FieldRemapper(paramFieldVisitor, this.remapper);
  }
  
  protected MethodVisitor createMethodRemapper(MethodVisitor paramMethodVisitor) {
    return (MethodVisitor)new MethodRemapper(paramMethodVisitor, this.remapper);
  }
  
  protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor paramAnnotationVisitor) {
    return (AnnotationVisitor)new AnnotationRemapper(paramAnnotationVisitor, this.remapper);
  }
}
