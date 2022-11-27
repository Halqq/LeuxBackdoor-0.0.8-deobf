package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.FieldVisitor;
import org.spongepowered.asm.lib.MethodVisitor;

class MixinPostProcessor$1 extends ClassVisitor {
  final MixinPostProcessor this$0;
  
  MixinPostProcessor$1(int paramInt, ClassVisitor paramClassVisitor) {
    super(paramInt, paramClassVisitor);
  }
  
  public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    super.visit(paramInt1, paramInt2 | 0x1, paramString1, paramString2, paramString3, paramArrayOfString);
  }
  
  public FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject) {
    if ((paramInt & 0x6) == 0)
      paramInt |= 0x1; 
    return super.visitField(paramInt, paramString1, paramString2, paramString3, paramObject);
  }
  
  public MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    if ((paramInt & 0x6) == 0)
      paramInt |= 0x1; 
    return super.visitMethod(paramInt, paramString1, paramString2, paramString3, paramArrayOfString);
  }
}
