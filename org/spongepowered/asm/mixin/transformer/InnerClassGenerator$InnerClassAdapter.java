package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.commons.ClassRemapper;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

class InnerClassGenerator$InnerClassAdapter extends ClassRemapper {
  private final InnerClassGenerator$InnerClassInfo info;
  
  public InnerClassGenerator$InnerClassAdapter(ClassVisitor paramClassVisitor, InnerClassGenerator$InnerClassInfo paramInnerClassGenerator$InnerClassInfo) {
    super(327680, paramClassVisitor, paramInnerClassGenerator$InnerClassInfo);
    this.info = paramInnerClassGenerator$InnerClassInfo;
  }
  
  public void visitSource(String paramString1, String paramString2) {
    super.visitSource(paramString1, paramString2);
    AnnotationVisitor annotationVisitor = this.cv.visitAnnotation("Lorg/spongepowered/asm/mixin/transformer/meta/MixinInner;", false);
    annotationVisitor.visit("mixin", this.info.getOwner().toString());
    annotationVisitor.visit("name", this.info.getOriginalName().substring(this.info.getOriginalName().lastIndexOf('/') + 1));
    annotationVisitor.visitEnd();
  }
  
  public void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt) {
    if (paramString1.startsWith(this.info.getOriginalName() + "$"))
      throw new InvalidMixinException(this.info.getOwner(), "Found unsupported nested inner class " + paramString1 + " in " + this.info.getOriginalName()); 
    super.visitInnerClass(paramString1, paramString2, paramString3, paramInt);
  }
}
