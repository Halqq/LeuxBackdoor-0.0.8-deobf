package org.spongepowered.asm.lib.signature;

public class SignatureWriter extends SignatureVisitor {
  private final StringBuilder buf = new StringBuilder();
  
  private boolean hasFormals;
  
  private boolean hasParameters;
  
  private int argumentStack;
  
  public SignatureWriter() {
    super(327680);
  }
  
  public void visitFormalTypeParameter(String paramString) {
    if (!this.hasFormals) {
      this.hasFormals = true;
      this.buf.append('<');
    } 
    this.buf.append(paramString);
    this.buf.append(':');
  }
  
  public SignatureVisitor visitClassBound() {
    return this;
  }
  
  public SignatureVisitor visitInterfaceBound() {
    this.buf.append(':');
    return this;
  }
  
  public SignatureVisitor visitSuperclass() {
    endFormals();
    return this;
  }
  
  public SignatureVisitor visitInterface() {
    return this;
  }
  
  public SignatureVisitor visitParameterType() {
    endFormals();
    if (!this.hasParameters) {
      this.hasParameters = true;
      this.buf.append('(');
    } 
    return this;
  }
  
  public SignatureVisitor visitReturnType() {
    endFormals();
    if (!this.hasParameters)
      this.buf.append('('); 
    this.buf.append(')');
    return this;
  }
  
  public SignatureVisitor visitExceptionType() {
    this.buf.append('^');
    return this;
  }
  
  public void visitBaseType(char paramChar) {
    this.buf.append(paramChar);
  }
  
  public void visitTypeVariable(String paramString) {
    this.buf.append('T');
    this.buf.append(paramString);
    this.buf.append(';');
  }
  
  public SignatureVisitor visitArrayType() {
    this.buf.append('[');
    return this;
  }
  
  public void visitClassType(String paramString) {
    this.buf.append('L');
    this.buf.append(paramString);
    this.argumentStack *= 2;
  }
  
  public void visitInnerClassType(String paramString) {
    endArguments();
    this.buf.append('.');
    this.buf.append(paramString);
    this.argumentStack *= 2;
  }
  
  public void visitTypeArgument() {
    if (this.argumentStack % 2 == 0) {
      this.argumentStack++;
      this.buf.append('<');
    } 
    this.buf.append('*');
  }
  
  public SignatureVisitor visitTypeArgument(char paramChar) {
    if (this.argumentStack % 2 == 0) {
      this.argumentStack++;
      this.buf.append('<');
    } 
    if (paramChar != '=')
      this.buf.append(paramChar); 
    return this;
  }
  
  public void visitEnd() {
    endArguments();
    this.buf.append(';');
  }
  
  public String toString() {
    return this.buf.toString();
  }
  
  private void endFormals() {
    if (this.hasFormals) {
      this.hasFormals = false;
      this.buf.append('>');
    } 
  }
  
  private void endArguments() {
    if (this.argumentStack % 2 != 0)
      this.buf.append('>'); 
    this.argumentStack /= 2;
  }
}
