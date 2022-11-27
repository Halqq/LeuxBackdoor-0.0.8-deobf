package org.spongepowered.tools.obfuscation;

import java.lang.reflect.Method;
import javax.lang.model.element.ExecutableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

class AnnotatedMixinElementHandlerOverwrite extends AnnotatedMixinElementHandler {
  AnnotatedMixinElementHandlerOverwrite(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, AnnotatedMixin paramAnnotatedMixin) {
    super(paramIMixinAnnotationProcessor, paramAnnotatedMixin);
  }
  
  public void registerMerge(ExecutableElement paramExecutableElement) {
    validateTargetMethod(paramExecutableElement, null, new AnnotatedMixinElementHandler$AliasedElementName(paramExecutableElement, AnnotationHandle.MISSING), "overwrite", true, true);
  }
  
  public void registerOverwrite(AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite) {
    AnnotatedMixinElementHandler$AliasedElementName annotatedMixinElementHandler$AliasedElementName = new AnnotatedMixinElementHandler$AliasedElementName(paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement(), paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getAnnotation());
    validateTargetMethod((ExecutableElement)paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement(), paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getAnnotation(), annotatedMixinElementHandler$AliasedElementName, "@Overwrite", true, false);
    checkConstraints((ExecutableElement)paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement(), paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getAnnotation());
    if (paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.shouldRemap())
      for (TypeHandle typeHandle : this.mixin.getTargets()) {
        if (!registerOverwriteForTarget(paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite, typeHandle))
          return; 
      }  
    if (!"true".equalsIgnoreCase(this.ap.getOption("disableOverwriteChecker"))) {
      Diagnostic.Kind kind = "error".equalsIgnoreCase(this.ap.getOption("overwriteErrorLevel")) ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
      String str = this.ap.getJavadocProvider().getJavadoc(paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement());
      this.ap.printMessage(kind, "@Overwrite is missing javadoc comment", paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement());
      return;
    } 
  }
  
  private boolean registerOverwriteForTarget(AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite, TypeHandle paramTypeHandle) {
    MappingMethod mappingMethod = paramTypeHandle.getMappingMethod(paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getSimpleName(), paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getDesc());
    ObfuscationData obfuscationData = this.obf.getDataProvider().getObfMethod(mappingMethod);
    if (obfuscationData.isEmpty()) {
      Diagnostic.Kind kind = Diagnostic.Kind.ERROR;
      Method method = ((ExecutableElement)paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement()).getClass().getMethod("isStatic", new Class[0]);
      if (((Boolean)method.invoke(paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement(), new Object[0])).booleanValue())
        kind = Diagnostic.Kind.WARNING; 
      this.ap.printMessage(kind, "No obfuscation mapping for @Overwrite method", paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement());
      return false;
    } 
    addMethodMappings(paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getSimpleName(), paramAnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getDesc(), obfuscationData);
    return true;
  }
}
