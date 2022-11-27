package org.spongepowered.asm.util;

class ClassSignature$SignatureParser$InterfaceElement extends ClassSignature$SignatureParser$TokenElement {
  final ClassSignature$SignatureParser this$1;
  
  ClassSignature$SignatureParser$InterfaceElement() {
    super(paramClassSignature$SignatureParser);
  }
  
  public void visitEnd() {
    this.this$1.this$0.addInterface(this.token);
  }
}
