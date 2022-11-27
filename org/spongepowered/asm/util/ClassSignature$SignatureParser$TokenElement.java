package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

abstract class ClassSignature$SignatureParser$TokenElement extends ClassSignature$SignatureParser$SignatureElement {
  protected ClassSignature$Token token;
  
  private boolean array;
  
  final ClassSignature$SignatureParser this$1;
  
  ClassSignature$SignatureParser$TokenElement() {
    super(paramClassSignature$SignatureParser);
  }
  
  public ClassSignature$Token getToken() {
    if (this.token == null)
      this.token = new ClassSignature$Token(); 
    return this.token;
  }
  
  protected void setArray() {
    this.array = true;
  }
  
  private boolean getArray() {
    boolean bool = this.array;
    this.array = false;
    return bool;
  }
  
  public void visitClassType(String paramString) {
    getToken().setType(paramString);
  }
  
  public SignatureVisitor visitClassBound() {
    getToken();
    return new ClassSignature$SignatureParser$BoundElement(ClassSignature$SignatureParser.this, this, true);
  }
  
  public SignatureVisitor visitInterfaceBound() {
    getToken();
    return new ClassSignature$SignatureParser$BoundElement(ClassSignature$SignatureParser.this, this, false);
  }
  
  public void visitInnerClassType(String paramString) {
    this.token.addInnerClass(paramString);
  }
  
  public SignatureVisitor visitArrayType() {
    setArray();
    return this;
  }
  
  public SignatureVisitor visitTypeArgument(char paramChar) {
    return new ClassSignature$SignatureParser$TypeArgElement(ClassSignature$SignatureParser.this, this, paramChar);
  }
  
  ClassSignature$Token addTypeArgument() {
    return this.token.addTypeArgument('*').asToken();
  }
  
  ClassSignature$IToken addTypeArgument(char paramChar) {
    return this.token.addTypeArgument(paramChar).setArray(getArray());
  }
  
  ClassSignature$IToken addTypeArgument(String paramString) {
    return this.token.addTypeArgument(paramString).setArray(getArray());
  }
  
  ClassSignature$IToken addTypeArgument(ClassSignature$Token paramClassSignature$Token) {
    return this.token.addTypeArgument(paramClassSignature$Token).setArray(getArray());
  }
  
  ClassSignature$IToken addTypeArgument(ClassSignature$TokenHandle paramClassSignature$TokenHandle) {
    return this.token.addTypeArgument(paramClassSignature$TokenHandle).setArray(getArray());
  }
}
