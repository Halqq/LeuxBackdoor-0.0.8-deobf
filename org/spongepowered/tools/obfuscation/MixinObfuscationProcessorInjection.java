package org.spongepowered.tools.obfuscation;

import java.lang.annotation.Annotation;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

@SupportedAnnotationTypes({"org.spongepowered.asm.mixin.injection.Inject", "org.spongepowered.asm.mixin.injection.ModifyArg", "org.spongepowered.asm.mixin.injection.ModifyArgs", "org.spongepowered.asm.mixin.injection.Redirect", "org.spongepowered.asm.mixin.injection.At"})
public class MixinObfuscationProcessorInjection extends MixinObfuscationProcessor {
  public boolean process(Set paramSet, RoundEnvironment paramRoundEnvironment) {
    if (paramRoundEnvironment.processingOver()) {
      postProcess(paramRoundEnvironment);
      return true;
    } 
    processMixins(paramRoundEnvironment);
    processInjectors(paramRoundEnvironment, Inject.class);
    processInjectors(paramRoundEnvironment, ModifyArg.class);
    processInjectors(paramRoundEnvironment, ModifyArgs.class);
    processInjectors(paramRoundEnvironment, Redirect.class);
    processInjectors(paramRoundEnvironment, ModifyVariable.class);
    processInjectors(paramRoundEnvironment, ModifyConstant.class);
    postProcess(paramRoundEnvironment);
    return true;
  }
  
  protected void postProcess(RoundEnvironment paramRoundEnvironment) {
    super.postProcess(paramRoundEnvironment);
    this.mixins.writeReferences();
  }
  
  private void processInjectors(RoundEnvironment paramRoundEnvironment, Class<? extends Annotation> paramClass) {
    for (Element element1 : paramRoundEnvironment.getElementsAnnotatedWith(paramClass)) {
      Element element2 = element1.getEnclosingElement();
      if (!(element2 instanceof TypeElement))
        throw new IllegalStateException("@" + paramClass.getSimpleName() + " element has unexpected parent with type " + TypeUtils.getElementType(element2)); 
      AnnotationHandle annotationHandle = AnnotationHandle.of(element1, paramClass);
      if (element1.getKind() == ElementKind.METHOD) {
        this.mixins.registerInjector((TypeElement)element2, (ExecutableElement)element1, annotationHandle);
        continue;
      } 
      this.mixins.printMessage(Diagnostic.Kind.WARNING, "Found an @" + paramClass.getSimpleName() + " annotation on an element which is not a method: " + element1.toString());
    } 
  }
}
