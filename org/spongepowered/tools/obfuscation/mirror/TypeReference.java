package org.spongepowered.tools.obfuscation.mirror;

import java.io.Serializable;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

public class TypeReference implements Serializable, Comparable {
  private static final long serialVersionUID = 1L;
  
  private final String name;
  
  private transient TypeHandle handle;
  
  public TypeReference(TypeHandle paramTypeHandle) {
    this.name = paramTypeHandle.getName();
    this.handle = paramTypeHandle;
  }
  
  public TypeReference(String paramString) {
    this.name = paramString;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getClassName() {
    return this.name.replace('/', '.');
  }
  
  public TypeHandle getHandle(ProcessingEnvironment paramProcessingEnvironment) {
    if (this.handle == null) {
      TypeElement typeElement = paramProcessingEnvironment.getElementUtils().getTypeElement(getClassName());
      this.handle = new TypeHandle(typeElement);
    } 
    return this.handle;
  }
  
  public String toString() {
    return String.format("TypeReference[%s]", new Object[] { this.name });
  }
  
  public int compareTo(TypeReference paramTypeReference) {}
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof TypeReference && compareTo((TypeReference)paramObject) == 0);
  }
  
  public int hashCode() {
    return this.name.hashCode();
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((TypeReference)paramObject);
  }
}
