package org.spongepowered.tools.obfuscation;

import java.util.Iterator;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.struct.InjectorRemap;

class AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector extends AnnotatedMixinElementHandler$AnnotatedElement {
  private final InjectorRemap state;
  
  public AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, InjectorRemap paramInjectorRemap) {
    super(paramExecutableElement, paramAnnotationHandle);
    this.state = paramInjectorRemap;
  }
  
  public boolean shouldRemap() {
    return this.state.shouldRemap();
  }
  
  public boolean hasCoerceArgument() {
    if (!this.annotation.toString().equals("@Inject"))
      return false; 
    Iterator<? extends VariableElement> iterator = ((ExecutableElement)this.element).getParameters().iterator();
    if (iterator.hasNext()) {
      VariableElement variableElement = iterator.next();
      return AnnotationHandle.of(variableElement, Coerce.class).exists();
    } 
    return false;
  }
  
  public void addMessage(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationHandle paramAnnotationHandle) {
    this.state.addMessage(paramKind, paramCharSequence, paramElement, paramAnnotationHandle);
  }
  
  public String toString() {
    return getAnnotation().toString();
  }
}
