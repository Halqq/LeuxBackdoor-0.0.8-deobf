package org.spongepowered.asm.transformers;

import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.mixin.transformer.ClassInfo;

public class MixinClassWriter extends ClassWriter {
  public MixinClassWriter(int paramInt) {
    super(paramInt);
  }
  
  public MixinClassWriter(ClassReader paramClassReader, int paramInt) {
    super(paramClassReader, paramInt);
  }
  
  protected String getCommonSuperClass(String paramString1, String paramString2) {
    return ClassInfo.getCommonSuperClass(paramString1, paramString2).getName();
  }
}
