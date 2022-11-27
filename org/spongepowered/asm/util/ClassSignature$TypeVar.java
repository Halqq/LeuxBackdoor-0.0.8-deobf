package org.spongepowered.asm.util;

class ClassSignature$TypeVar implements Comparable {
  private final String originalName;
  
  private String currentName;
  
  ClassSignature$TypeVar(String paramString) {
    this.currentName = this.originalName = paramString;
  }
  
  public int compareTo(ClassSignature$TypeVar paramClassSignature$TypeVar) {
    return this.currentName.compareTo(paramClassSignature$TypeVar.currentName);
  }
  
  public String toString() {
    return this.currentName;
  }
  
  String getOriginalName() {
    return this.originalName;
  }
  
  void rename(String paramString) {
    this.currentName = paramString;
  }
  
  public boolean matches(String paramString) {
    return this.originalName.equals(paramString);
  }
  
  public boolean equals(Object paramObject) {
    return this.currentName.equals(paramObject);
  }
  
  public int hashCode() {
    return this.currentName.hashCode();
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((ClassSignature$TypeVar)paramObject);
  }
}
