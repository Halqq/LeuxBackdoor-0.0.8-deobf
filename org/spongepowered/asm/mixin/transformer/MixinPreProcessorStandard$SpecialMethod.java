package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.util.Bytecode;

enum MixinPreProcessorStandard$SpecialMethod {
  MERGE(true),
  OVERWRITE(true, Overwrite.class),
  SHADOW(false, Shadow.class),
  ACCESSOR(false, Accessor.class),
  INVOKER(false, Invoker.class);
  
  final boolean isOverwrite;
  
  final Class annotation;
  
  final String description;
  
  private static final MixinPreProcessorStandard$SpecialMethod[] $VALUES = new MixinPreProcessorStandard$SpecialMethod[] { MERGE, OVERWRITE, SHADOW, ACCESSOR, INVOKER };
  
  MixinPreProcessorStandard$SpecialMethod(boolean paramBoolean, Class paramClass) {
    this.isOverwrite = paramBoolean;
    this.annotation = paramClass;
    this.description = "@" + Bytecode.getSimpleName(paramClass);
  }
  
  MixinPreProcessorStandard$SpecialMethod(boolean paramBoolean) {
    this.isOverwrite = paramBoolean;
    this.annotation = null;
    this.description = "overwrite";
  }
  
  public String toString() {
    return this.description;
  }
}
