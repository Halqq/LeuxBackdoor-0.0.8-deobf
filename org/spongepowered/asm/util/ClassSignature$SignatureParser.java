package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.signature.SignatureVisitor;

class ClassSignature$SignatureParser extends SignatureVisitor {
  private ClassSignature$SignatureParser$FormalParamElement param;
  
  final ClassSignature this$0;
  
  ClassSignature$SignatureParser() {
    super(327680);
  }
  
  public void visitFormalTypeParameter(String paramString) {
    this.param = new ClassSignature$SignatureParser$FormalParamElement(this, paramString);
  }
  
  public SignatureVisitor visitClassBound() {
    return this.param.visitClassBound();
  }
  
  public SignatureVisitor visitInterfaceBound() {
    return this.param.visitInterfaceBound();
  }
  
  public SignatureVisitor visitSuperclass() {
    return new ClassSignature$SignatureParser$SuperClassElement(this);
  }
  
  public SignatureVisitor visitInterface() {
    return new ClassSignature$SignatureParser$InterfaceElement(this);
  }
}
