package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

class ClassSignature$SignatureParser$TypeArgElement extends ClassSignature$SignatureParser$TokenElement {
  private final ClassSignature$SignatureParser$TokenElement type;
  
  private final char wildcard;
  
  final ClassSignature$SignatureParser this$1;
  
  ClassSignature$SignatureParser$TypeArgElement(ClassSignature$SignatureParser$TokenElement paramClassSignature$SignatureParser$TokenElement, char paramChar) {
    super(paramClassSignature$SignatureParser);
    this.type = paramClassSignature$SignatureParser$TokenElement;
    this.wildcard = paramChar;
  }
  
  public SignatureVisitor visitArrayType() {
    this.type.setArray();
    return this;
  }
  
  public void visitBaseType(char paramChar) {
    this.token = this.type.addTypeArgument(paramChar).asToken();
  }
  
  public void visitTypeVariable(String paramString) {
    ClassSignature$TokenHandle classSignature$TokenHandle = this.this$1.this$0.getType(paramString);
    this.token = this.type.addTypeArgument(classSignature$TokenHandle).setWildcard(this.wildcard).asToken();
  }
  
  public void visitClassType(String paramString) {
    this.token = this.type.addTypeArgument(paramString).setWildcard(this.wildcard).asToken();
  }
  
  public void visitTypeArgument() {
    this.token.addTypeArgument('*');
  }
  
  public SignatureVisitor visitTypeArgument(char paramChar) {
    return new ClassSignature$SignatureParser$TypeArgElement(this, paramChar);
  }
  
  public void visitEnd() {}
}
