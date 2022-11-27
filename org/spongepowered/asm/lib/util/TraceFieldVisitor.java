package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.TypePath;

public final class TraceFieldVisitor extends FieldVisitor {
  public final Printer p;
  
  public TraceFieldVisitor(Printer paramPrinter) {
    this(null, paramPrinter);
  }
  
  public TraceFieldVisitor(FieldVisitor paramFieldVisitor, Printer paramPrinter) {
    super(327680, paramFieldVisitor);
    this.p = paramPrinter;
  }
  
  public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
    Printer printer = this.p.visitFieldAnnotation(paramString, paramBoolean);
    AnnotationVisitor annotationVisitor = (this.fv == null) ? null : this.fv.visitAnnotation(paramString, paramBoolean);
    return new TraceAnnotationVisitor(annotationVisitor, printer);
  }
  
  public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    Printer printer = this.p.visitFieldTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
    AnnotationVisitor annotationVisitor = (this.fv == null) ? null : this.fv.visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
    return new TraceAnnotationVisitor(annotationVisitor, printer);
  }
  
  public void visitAttribute(Attribute paramAttribute) {
    this.p.visitFieldAttribute(paramAttribute);
    super.visitAttribute(paramAttribute);
  }
  
  public void visitEnd() {
    this.p.visitFieldEnd();
    super.visitEnd();
  }
}
