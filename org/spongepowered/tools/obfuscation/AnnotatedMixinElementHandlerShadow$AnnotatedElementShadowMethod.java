package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.ExecutableElement;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

class AnnotatedMixinElementHandlerShadow$AnnotatedElementShadowMethod extends AnnotatedMixinElementHandlerShadow$AnnotatedElementShadow {
  final AnnotatedMixinElementHandlerShadow this$0;
  
  public AnnotatedMixinElementHandlerShadow$AnnotatedElementShadowMethod(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    super(paramExecutableElement, paramAnnotationHandle, paramBoolean, IMapping.Type.METHOD);
  }
  
  public MappingMethod getMapping(TypeHandle paramTypeHandle, String paramString1, String paramString2) {
    return paramTypeHandle.getMappingMethod(paramString1, paramString2);
  }
  
  public void addMapping(ObfuscationType paramObfuscationType, IMapping paramIMapping) {
    AnnotatedMixinElementHandlerShadow.this.addMethodMapping(paramObfuscationType, setObfuscatedName(paramIMapping), getDesc(), paramIMapping.getDesc());
  }
  
  public IMapping getMapping(TypeHandle paramTypeHandle, String paramString1, String paramString2) {
    return (IMapping)getMapping(paramTypeHandle, paramString1, paramString2);
  }
}
