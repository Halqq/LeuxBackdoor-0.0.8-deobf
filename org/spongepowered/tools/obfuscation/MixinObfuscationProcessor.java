package org.spongepowered.tools.obfuscation;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.Mixin;

public abstract class MixinObfuscationProcessor extends AbstractProcessor {
  protected AnnotatedMixins mixins;
  
  public synchronized void init(ProcessingEnvironment paramProcessingEnvironment) {
    super.init(paramProcessingEnvironment);
    this.mixins = AnnotatedMixins.getMixinsForEnvironment(paramProcessingEnvironment);
  }
  
  protected void processMixins(RoundEnvironment paramRoundEnvironment) {
    this.mixins.onPassStarted();
    for (Element element : paramRoundEnvironment.getElementsAnnotatedWith((Class)Mixin.class)) {
      if (element.getKind() == ElementKind.CLASS || element.getKind() == ElementKind.INTERFACE) {
        this.mixins.registerMixin((TypeElement)element);
        continue;
      } 
      this.mixins.printMessage(Diagnostic.Kind.ERROR, "Found an @Mixin annotation on an element which is not a class or interface", element);
    } 
  }
  
  protected void postProcess(RoundEnvironment paramRoundEnvironment) {
    this.mixins.onPassCompleted(paramRoundEnvironment);
  }
  
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.valueOf("RELEASE_8");
  }
  
  public Set getSupportedOptions() {
    return SupportedOptions.getAllOptions();
  }
}
