package org.spongepowered.tools.obfuscation;

import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

@SupportedAnnotationTypes({"org.spongepowered.asm.mixin.Mixin", "org.spongepowered.asm.mixin.Shadow", "org.spongepowered.asm.mixin.Overwrite", "org.spongepowered.asm.mixin.gen.Accessor", "org.spongepowered.asm.mixin.Implements"})
public class MixinObfuscationProcessorTargets extends MixinObfuscationProcessor {
  public boolean process(Set paramSet, RoundEnvironment paramRoundEnvironment) {
    if (paramRoundEnvironment.processingOver()) {
      postProcess(paramRoundEnvironment);
      return true;
    } 
    processMixins(paramRoundEnvironment);
    processShadows(paramRoundEnvironment);
    processOverwrites(paramRoundEnvironment);
    processAccessors(paramRoundEnvironment);
    processInvokers(paramRoundEnvironment);
    processImplements(paramRoundEnvironment);
    postProcess(paramRoundEnvironment);
    return true;
  }
  
  protected void postProcess(RoundEnvironment paramRoundEnvironment) {
    super.postProcess(paramRoundEnvironment);
    this.mixins.writeReferences();
    this.mixins.writeMappings();
  }
  
  private void processShadows(RoundEnvironment paramRoundEnvironment) {
    for (Element element1 : paramRoundEnvironment.getElementsAnnotatedWith((Class)Shadow.class)) {
      Element element2 = element1.getEnclosingElement();
      if (!(element2 instanceof TypeElement)) {
        this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element1);
        continue;
      } 
      AnnotationHandle annotationHandle = AnnotationHandle.of(element1, Shadow.class);
      if (element1.getKind() == ElementKind.FIELD) {
        this.mixins.registerShadow((TypeElement)element2, (VariableElement)element1, annotationHandle);
        continue;
      } 
      if (element1.getKind() == ElementKind.METHOD) {
        this.mixins.registerShadow((TypeElement)element2, (ExecutableElement)element1, annotationHandle);
        continue;
      } 
      this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method or field", element1);
    } 
  }
  
  private void processOverwrites(RoundEnvironment paramRoundEnvironment) {
    for (Element element1 : paramRoundEnvironment.getElementsAnnotatedWith((Class)Overwrite.class)) {
      Element element2 = element1.getEnclosingElement();
      if (!(element2 instanceof TypeElement)) {
        this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element1);
        continue;
      } 
      if (element1.getKind() == ElementKind.METHOD) {
        this.mixins.registerOverwrite((TypeElement)element2, (ExecutableElement)element1);
        continue;
      } 
      this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method", element1);
    } 
  }
  
  private void processAccessors(RoundEnvironment paramRoundEnvironment) {
    for (Element element1 : paramRoundEnvironment.getElementsAnnotatedWith((Class)Accessor.class)) {
      Element element2 = element1.getEnclosingElement();
      if (!(element2 instanceof TypeElement)) {
        this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element1);
        continue;
      } 
      if (element1.getKind() == ElementKind.METHOD) {
        this.mixins.registerAccessor((TypeElement)element2, (ExecutableElement)element1);
        continue;
      } 
      this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method", element1);
    } 
  }
  
  private void processInvokers(RoundEnvironment paramRoundEnvironment) {
    for (Element element1 : paramRoundEnvironment.getElementsAnnotatedWith((Class)Invoker.class)) {
      Element element2 = element1.getEnclosingElement();
      if (!(element2 instanceof TypeElement)) {
        this.mixins.printMessage(Diagnostic.Kind.ERROR, "Unexpected parent with type " + TypeUtils.getElementType(element2), element1);
        continue;
      } 
      if (element1.getKind() == ElementKind.METHOD) {
        this.mixins.registerInvoker((TypeElement)element2, (ExecutableElement)element1);
        continue;
      } 
      this.mixins.printMessage(Diagnostic.Kind.ERROR, "Element is not a method", element1);
    } 
  }
  
  private void processImplements(RoundEnvironment paramRoundEnvironment) {
    for (Element element : paramRoundEnvironment.getElementsAnnotatedWith((Class)Implements.class)) {
      if (element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.INTERFACE) {
        AnnotationHandle annotationHandle = AnnotationHandle.of(element, Implements.class);
        this.mixins.registerSoftImplements((TypeElement)element, annotationHandle);
        continue;
      } 
      this.mixins.printMessage(Diagnostic.Kind.ERROR, "Found an @Implements annotation on an element which is not a class or interface", element);
    } 
  }
}
