package org.spongepowered.asm.util;

class ClassSignature$SignatureParser$SuperClassElement extends ClassSignature$SignatureParser$TokenElement {
  final ClassSignature$SignatureParser this$1;
  
  ClassSignature$SignatureParser$SuperClassElement() {
    super(paramClassSignature$SignatureParser);
  }
  
  public void visitEnd() {
    this.this$1.this$0.setSuperClass(this.token);
  }
}
