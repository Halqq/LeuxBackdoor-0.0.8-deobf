package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.ExecutableElement;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

class AnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker extends AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor {
  public AnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    super(paramExecutableElement, paramAnnotationHandle, paramBoolean);
  }
  
  public String getAccessorDesc() {
    return TypeUtils.getDescriptor((ExecutableElement)getElement());
  }
  
  public AccessorInfo.AccessorType getAccessorType() {
    return AccessorInfo.AccessorType.METHOD_PROXY;
  }
  
  public String getTargetTypeName() {
    return TypeUtils.getJavaSignature(getElement());
  }
}
