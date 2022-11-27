package org.spongepowered.asm.util;

class ClassSignature$Lazy extends ClassSignature {
  private final String sig;
  
  private ClassSignature generated;
  
  ClassSignature$Lazy(String paramString) {
    this.sig = paramString;
  }
  
  public ClassSignature wake() {
    if (this.generated == null)
      this.generated = ClassSignature.of(this.sig); 
    return this.generated;
  }
}
