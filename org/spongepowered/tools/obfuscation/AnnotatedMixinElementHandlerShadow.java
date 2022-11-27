package org.spongepowered.tools.obfuscation;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

class AnnotatedMixinElementHandlerShadow extends AnnotatedMixinElementHandler {
  AnnotatedMixinElementHandlerShadow(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, AnnotatedMixin paramAnnotatedMixin) {
    super(paramIMixinAnnotationProcessor, paramAnnotatedMixin);
  }
  
  public void registerShadow(AnnotatedMixinElementHandlerShadow$AnnotatedElementShadow paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow) {
    validateTarget(paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.getElement(), paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.getAnnotation(), paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.getName(), "@Shadow");
    if (!paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.shouldRemap())
      return; 
    for (TypeHandle typeHandle : this.mixin.getTargets())
      registerShadowForTarget(paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow, typeHandle); 
  }
  
  private void registerShadowForTarget(AnnotatedMixinElementHandlerShadow$AnnotatedElementShadow paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow, TypeHandle paramTypeHandle) {
    ObfuscationData obfuscationData = paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.getObfuscationData(this.obf.getDataProvider(), paramTypeHandle);
    if (obfuscationData.isEmpty()) {
      String str = this.mixin.isMultiTarget() ? (" in target " + paramTypeHandle) : "";
      if (paramTypeHandle.isSimulated()) {
        paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + str + " for @Shadow " + paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow);
      } else {
        paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + str + " for @Shadow " + paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow);
      } 
      return;
    } 
    for (ObfuscationType obfuscationType : obfuscationData)
      paramAnnotatedMixinElementHandlerShadow$AnnotatedElementShadow.addMapping(obfuscationType, (IMapping)obfuscationData.get(obfuscationType)); 
  }
}
