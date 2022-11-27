package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

class ClassSignature$SignatureParser$BoundElement extends ClassSignature$SignatureParser$TokenElement {
  private final ClassSignature$SignatureParser$TokenElement type;
  
  private final boolean classBound;
  
  final ClassSignature$SignatureParser this$1;
  
  ClassSignature$SignatureParser$BoundElement(ClassSignature$SignatureParser$TokenElement paramClassSignature$SignatureParser$TokenElement, boolean paramBoolean) {
    super(paramClassSignature$SignatureParser);
    this.type = paramClassSignature$SignatureParser$TokenElement;
    this.classBound = paramBoolean;
  }
  
  public void visitClassType(String paramString) {
    this.token = this.type.token.addBound(paramString, this.classBound);
  }
  
  public void visitTypeArgument() {
    this.token.addTypeArgument('*');
  }
  
  public SignatureVisitor visitTypeArgument(char paramChar) {
    return new ClassSignature$SignatureParser$TypeArgElement(ClassSignature$SignatureParser.this, this, paramChar);
  }
}
