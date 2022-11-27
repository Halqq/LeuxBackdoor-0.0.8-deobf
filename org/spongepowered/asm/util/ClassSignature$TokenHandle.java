package org.spongepowered.asm.util;

class ClassSignature$TokenHandle implements ClassSignature$IToken {
  final ClassSignature$Token token;
  
  boolean array;
  
  char wildcard;
  
  final ClassSignature this$0;
  
  ClassSignature$TokenHandle(ClassSignature paramClassSignature) {
    this(new ClassSignature$Token());
  }
  
  ClassSignature$TokenHandle(ClassSignature$Token paramClassSignature$Token) {
    this.token = paramClassSignature$Token;
  }
  
  public ClassSignature$IToken setArray(boolean paramBoolean) {
    this.array |= paramBoolean;
    return this;
  }
  
  public ClassSignature$IToken setWildcard(char paramChar) {
    if ("+-".indexOf(paramChar) > -1)
      this.wildcard = paramChar; 
    return this;
  }
  
  public String asBound() {
    return this.token.asBound();
  }
  
  public String asType() {
    StringBuilder stringBuilder = new StringBuilder();
    if (this.wildcard > '\000')
      stringBuilder.append(this.wildcard); 
    if (this.array)
      stringBuilder.append('['); 
    return stringBuilder.append(ClassSignature.this.getTypeVar(this)).toString();
  }
  
  public ClassSignature$Token asToken() {
    return this.token;
  }
  
  public String toString() {
    return this.token.toString();
  }
  
  public ClassSignature$TokenHandle clone() {
    return new ClassSignature$TokenHandle(this.token);
  }
  
  public Object clone() throws CloneNotSupportedException {
    return clone();
  }
}
