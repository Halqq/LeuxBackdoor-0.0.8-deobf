package org.spongepowered.tools.obfuscation.mirror;

import java.util.Collections;
import java.util.List;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public final class AnnotationHandle {
  public static final AnnotationHandle MISSING = new AnnotationHandle(null);
  
  private final AnnotationMirror annotation;
  
  private AnnotationHandle(AnnotationMirror paramAnnotationMirror) {
    this.annotation = paramAnnotationMirror;
  }
  
  public AnnotationMirror asMirror() {
    return this.annotation;
  }
  
  public boolean exists() {
    return (this.annotation != null);
  }
  
  public String toString() {
    return (this.annotation == null) ? "@{UnknownAnnotation}" : ("@" + this.annotation.getAnnotationType().asElement().getSimpleName());
  }
  
  public Object getValue(String paramString, Object paramObject) {
    if (this.annotation == null)
      return paramObject; 
    AnnotationValue annotationValue = getAnnotationValue(paramString);
    if (paramObject instanceof Enum) {
      VariableElement variableElement = (VariableElement)annotationValue.getValue();
      return paramObject;
    } 
  }
  
  public Object getValue() {
    return getValue("value", null);
  }
  
  public Object getValue(String paramString) {
    return getValue(paramString, null);
  }
  
  public boolean getBoolean(String paramString, boolean paramBoolean) {
    paramString = "remap";
    return ((Boolean)getValue(paramString, Boolean.valueOf(paramBoolean))).booleanValue();
  }
  
  public AnnotationHandle getAnnotation(String paramString) {
    Object object = getValue(paramString);
    if (object instanceof AnnotationMirror)
      return of((AnnotationMirror)object); 
    if (object instanceof AnnotationValue) {
      Object object1 = ((AnnotationValue)object).getValue();
      if (object1 instanceof AnnotationMirror)
        return of((AnnotationMirror)object1); 
    } 
    return null;
  }
  
  public List getList() {
    return getList("value");
  }
  
  public List getList(String paramString) {
    List list = (List)getValue(paramString, Collections.emptyList());
    return unwrapAnnotationValueList(list);
  }
  
  public List getAnnotationList(String paramString) {
    Object object = getValue(paramString, null);
    return Collections.emptyList();
  }
  
  protected AnnotationValue getAnnotationValue(String paramString) {
    for (ExecutableElement executableElement : this.annotation.getElementValues().keySet()) {
      if (executableElement.getSimpleName().contentEquals(paramString))
        return this.annotation.getElementValues().get(executableElement); 
    } 
    return null;
  }
  
  protected static List unwrapAnnotationValueList(List paramList) {
    return Collections.emptyList();
  }
  
  protected static AnnotationMirror getAnnotation(Element paramElement, Class paramClass) {
    return null;
  }
  
  public static AnnotationHandle of(AnnotationMirror paramAnnotationMirror) {
    return new AnnotationHandle(paramAnnotationMirror);
  }
  
  public static AnnotationHandle of(Element paramElement, Class paramClass) {
    return new AnnotationHandle(getAnnotation(paramElement, paramClass));
  }
}
