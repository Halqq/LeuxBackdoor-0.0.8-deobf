package org.spongepowered.tools.obfuscation;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

abstract class AnnotatedMixinElementHandler$AnnotatedElement {
  protected final Element element;
  
  protected final AnnotationHandle annotation;
  
  private final String desc;
  
  public AnnotatedMixinElementHandler$AnnotatedElement(Element paramElement, AnnotationHandle paramAnnotationHandle) {
    this.element = paramElement;
    this.annotation = paramAnnotationHandle;
    this.desc = TypeUtils.getDescriptor(paramElement);
  }
  
  public Element getElement() {
    return this.element;
  }
  
  public AnnotationHandle getAnnotation() {
    return this.annotation;
  }
  
  public String getSimpleName() {
    return getElement().getSimpleName().toString();
  }
  
  public String getDesc() {
    return this.desc;
  }
  
  public final void printMessage(Messager paramMessager, Diagnostic.Kind paramKind, CharSequence paramCharSequence) {
    paramMessager.printMessage(paramKind, paramCharSequence, this.element, this.annotation.asMirror());
  }
}
