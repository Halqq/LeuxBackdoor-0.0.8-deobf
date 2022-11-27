package org.spongepowered.tools.obfuscation.struct;

import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

public class Message {
  private Diagnostic.Kind kind;
  
  private CharSequence msg;
  
  private final Element element;
  
  private final AnnotationMirror annotation;
  
  private final AnnotationValue value;
  
  public Message(Diagnostic.Kind paramKind, CharSequence paramCharSequence) {
    this(paramKind, paramCharSequence, (Element)null, (AnnotationMirror)null, (AnnotationValue)null);
  }
  
  public Message(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement) {
    this(paramKind, paramCharSequence, paramElement, (AnnotationMirror)null, (AnnotationValue)null);
  }
  
  public Message(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationHandle paramAnnotationHandle) {
    this(paramKind, paramCharSequence, paramElement, paramAnnotationHandle.asMirror(), (AnnotationValue)null);
  }
  
  public Message(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationMirror paramAnnotationMirror) {
    this(paramKind, paramCharSequence, paramElement, paramAnnotationMirror, (AnnotationValue)null);
  }
  
  public Message(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationHandle paramAnnotationHandle, AnnotationValue paramAnnotationValue) {
    this(paramKind, paramCharSequence, paramElement, paramAnnotationHandle.asMirror(), paramAnnotationValue);
  }
  
  public Message(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationMirror paramAnnotationMirror, AnnotationValue paramAnnotationValue) {
    this.kind = paramKind;
    this.msg = paramCharSequence;
    this.element = paramElement;
    this.annotation = paramAnnotationMirror;
    this.value = paramAnnotationValue;
  }
  
  public Message sendTo(Messager paramMessager) {
    if (this.value != null) {
      paramMessager.printMessage(this.kind, this.msg, this.element, this.annotation, this.value);
    } else if (this.annotation != null) {
      paramMessager.printMessage(this.kind, this.msg, this.element, this.annotation);
    } else if (this.element != null) {
      paramMessager.printMessage(this.kind, this.msg, this.element);
    } else {
      paramMessager.printMessage(this.kind, this.msg);
    } 
    return this;
  }
  
  public Diagnostic.Kind getKind() {
    return this.kind;
  }
  
  public Message setKind(Diagnostic.Kind paramKind) {
    this.kind = paramKind;
    return this;
  }
  
  public CharSequence getMsg() {
    return this.msg;
  }
  
  public Message setMsg(CharSequence paramCharSequence) {
    this.msg = paramCharSequence;
    return this;
  }
  
  public Element getElement() {
    return this.element;
  }
  
  public AnnotationMirror getAnnotation() {
    return this.annotation;
  }
  
  public AnnotationValue getValue() {
    return this.value;
  }
}
