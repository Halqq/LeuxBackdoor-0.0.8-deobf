package org.spongepowered.tools.obfuscation;

import java.util.Collection;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IMixinValidator;
import org.spongepowered.tools.obfuscation.interfaces.IOptionProvider;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

public abstract class MixinValidator implements IMixinValidator {
  protected final ProcessingEnvironment processingEnv;
  
  protected final Messager messager;
  
  protected final IOptionProvider options;
  
  protected final IMixinValidator.ValidationPass pass;
  
  public MixinValidator(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, IMixinValidator.ValidationPass paramValidationPass) {
    this.processingEnv = paramIMixinAnnotationProcessor.getProcessingEnvironment();
    this.messager = (Messager)paramIMixinAnnotationProcessor;
    this.options = (IOptionProvider)paramIMixinAnnotationProcessor;
    this.pass = paramValidationPass;
  }
  
  public final boolean validate(IMixinValidator.ValidationPass paramValidationPass, TypeElement paramTypeElement, AnnotationHandle paramAnnotationHandle, Collection paramCollection) {
    return (paramValidationPass != this.pass) ? true : validate(paramTypeElement, paramAnnotationHandle, paramCollection);
  }
  
  protected abstract boolean validate(TypeElement paramTypeElement, AnnotationHandle paramAnnotationHandle, Collection paramCollection);
  
  protected final void note(String paramString, Element paramElement) {
    this.messager.printMessage(Diagnostic.Kind.NOTE, paramString, paramElement);
  }
  
  protected final void warning(String paramString, Element paramElement) {
    this.messager.printMessage(Diagnostic.Kind.WARNING, paramString, paramElement);
  }
  
  protected final void error(String paramString, Element paramElement) {
    this.messager.printMessage(Diagnostic.Kind.ERROR, paramString, paramElement);
  }
  
  protected final Collection getMixinsTargeting(TypeMirror paramTypeMirror) {
    return AnnotatedMixins.getMixinsForEnvironment(this.processingEnv).getMixinsTargeting(paramTypeMirror);
  }
}
