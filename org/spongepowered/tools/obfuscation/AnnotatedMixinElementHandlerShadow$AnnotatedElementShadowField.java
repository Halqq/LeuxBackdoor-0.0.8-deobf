package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.VariableElement;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

class AnnotatedMixinElementHandlerShadow$AnnotatedElementShadowField extends AnnotatedMixinElementHandlerShadow$AnnotatedElementShadow {
  final AnnotatedMixinElementHandlerShadow this$0;
  
  public AnnotatedMixinElementHandlerShadow$AnnotatedElementShadowField(VariableElement paramVariableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    super(paramVariableElement, paramAnnotationHandle, paramBoolean, IMapping.Type.FIELD);
  }
  
  public MappingField getMapping(TypeHandle paramTypeHandle, String paramString1, String paramString2) {
    return new MappingField(paramTypeHandle.getName(), paramString1, paramString2);
  }
  
  public void addMapping(ObfuscationType paramObfuscationType, IMapping paramIMapping) {
    AnnotatedMixinElementHandlerShadow.this.addFieldMapping(paramObfuscationType, setObfuscatedName(paramIMapping), getDesc(), paramIMapping.getDesc());
  }
  
  public IMapping getMapping(TypeHandle paramTypeHandle, String paramString1, String paramString2) {
    return (IMapping)getMapping(paramTypeHandle, paramString1, paramString2);
  }
}
