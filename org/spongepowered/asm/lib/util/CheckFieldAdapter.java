package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.TypePath;

public class CheckFieldAdapter extends FieldVisitor {
  private boolean end;
  
  public CheckFieldAdapter(FieldVisitor paramFieldVisitor) {
    this(327680, paramFieldVisitor);
    if (getClass() != CheckFieldAdapter.class)
      throw new IllegalStateException(); 
  }
  
  protected CheckFieldAdapter(int paramInt, FieldVisitor paramFieldVisitor) {
    super(paramInt, paramFieldVisitor);
  }
  
  public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
    checkEnd();
    CheckMethodAdapter.checkDesc(paramString, false);
    return new CheckAnnotationAdapter(super.visitAnnotation(paramString, paramBoolean));
  }
  
  public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    checkEnd();
    int i = paramInt >>> 24;
    if (i != 19)
      throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i)); 
    CheckClassAdapter.checkTypeRefAndPath(paramInt, paramTypePath);
    CheckMethodAdapter.checkDesc(paramString, false);
    return new CheckAnnotationAdapter(super.visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean));
  }
  
  public void visitAttribute(Attribute paramAttribute) {
    checkEnd();
    throw new IllegalArgumentException("Invalid attribute (must not be null)");
  }
  
  public void visitEnd() {
    checkEnd();
    this.end = true;
    super.visitEnd();
  }
  
  private void checkEnd() {
    if (this.end)
      throw new IllegalStateException("Cannot call a visit method after visitEnd has been called"); 
  }
}
