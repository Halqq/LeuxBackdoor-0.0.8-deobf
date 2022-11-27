package org.spongepowered.asm.util;

class ClassSignature$SignatureParser$FormalParamElement extends ClassSignature$SignatureParser$TokenElement {
  private final ClassSignature$TokenHandle handle;
  
  final ClassSignature$SignatureParser this$1;
  
  ClassSignature$SignatureParser$FormalParamElement(String paramString) {
    super(paramClassSignature$SignatureParser);
    this.handle = paramClassSignature$SignatureParser.this$0.getType(paramString);
    this.token = this.handle.asToken();
  }
}
