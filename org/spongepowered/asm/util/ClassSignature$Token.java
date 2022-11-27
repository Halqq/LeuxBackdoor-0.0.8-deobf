package org.spongepowered.asm.util;

import java.util.ArrayList;
import java.util.List;

class ClassSignature$Token implements ClassSignature$IToken {
  static final String SYMBOLS = "+-*";
  
  private final boolean inner;
  
  private boolean array;
  
  private char symbol = Character.MIN_VALUE;
  
  private String type;
  
  private List classBound;
  
  private List ifaceBound;
  
  private List signature;
  
  private List suffix;
  
  private ClassSignature$Token tail;
  
  ClassSignature$Token() {
    this(false);
  }
  
  ClassSignature$Token(String paramString) {
    this(paramString, false);
  }
  
  ClassSignature$Token(char paramChar) {
    this();
    this.symbol = paramChar;
  }
  
  ClassSignature$Token(boolean paramBoolean) {
    this(null, paramBoolean);
  }
  
  ClassSignature$Token(String paramString, boolean paramBoolean) {
    this.inner = paramBoolean;
    this.type = paramString;
  }
  
  ClassSignature$Token setSymbol(char paramChar) {
    if (this.symbol == '\000' && "+-*".indexOf(paramChar) > -1)
      this.symbol = paramChar; 
    return this;
  }
  
  ClassSignature$Token setType(String paramString) {
    if (this.type == null)
      this.type = paramString; 
    return this;
  }
  
  boolean hasClassBound() {
    return (this.classBound != null);
  }
  
  boolean hasInterfaceBound() {
    return (this.ifaceBound != null);
  }
  
  public ClassSignature$IToken setArray(boolean paramBoolean) {
    this.array |= paramBoolean;
    return this;
  }
  
  public ClassSignature$IToken setWildcard(char paramChar) {
    return ("+-".indexOf(paramChar) == -1) ? this : setSymbol(paramChar);
  }
  
  private List getClassBound() {
    if (this.classBound == null)
      this.classBound = new ArrayList(); 
    return this.classBound;
  }
  
  private List getIfaceBound() {
    if (this.ifaceBound == null)
      this.ifaceBound = new ArrayList(); 
    return this.ifaceBound;
  }
  
  private List getSignature() {
    if (this.signature == null)
      this.signature = new ArrayList(); 
    return this.signature;
  }
  
  private List getSuffix() {
    if (this.suffix == null)
      this.suffix = new ArrayList(); 
    return this.suffix;
  }
  
  ClassSignature$IToken addTypeArgument(char paramChar) {
    if (this.tail != null)
      return this.tail.addTypeArgument(paramChar); 
    ClassSignature$Token classSignature$Token = new ClassSignature$Token(paramChar);
    getSignature().add(classSignature$Token);
    return classSignature$Token;
  }
  
  ClassSignature$IToken addTypeArgument(String paramString) {
    if (this.tail != null)
      return this.tail.addTypeArgument(paramString); 
    ClassSignature$Token classSignature$Token = new ClassSignature$Token(paramString);
    getSignature().add(classSignature$Token);
    return classSignature$Token;
  }
  
  ClassSignature$IToken addTypeArgument(ClassSignature$Token paramClassSignature$Token) {
    if (this.tail != null)
      return this.tail.addTypeArgument(paramClassSignature$Token); 
    getSignature().add(paramClassSignature$Token);
    return paramClassSignature$Token;
  }
  
  ClassSignature$IToken addTypeArgument(ClassSignature$TokenHandle paramClassSignature$TokenHandle) {
    if (this.tail != null)
      return this.tail.addTypeArgument(paramClassSignature$TokenHandle); 
    ClassSignature$TokenHandle classSignature$TokenHandle = paramClassSignature$TokenHandle.clone();
    getSignature().add(classSignature$TokenHandle);
    return classSignature$TokenHandle;
  }
  
  ClassSignature$Token addBound(String paramString, boolean paramBoolean) {
    return addClassBound(paramString);
  }
  
  ClassSignature$Token addClassBound(String paramString) {
    ClassSignature$Token classSignature$Token = new ClassSignature$Token(paramString);
    getClassBound().add(classSignature$Token);
    return classSignature$Token;
  }
  
  ClassSignature$Token addInterfaceBound(String paramString) {
    ClassSignature$Token classSignature$Token = new ClassSignature$Token(paramString);
    getIfaceBound().add(classSignature$Token);
    return classSignature$Token;
  }
  
  ClassSignature$Token addInnerClass(String paramString) {
    this.tail = new ClassSignature$Token(paramString, true);
    getSuffix().add(this.tail);
    return this.tail;
  }
  
  public String toString() {
    return asType();
  }
  
  public String asBound() {
    StringBuilder stringBuilder = new StringBuilder();
    if (this.type != null)
      stringBuilder.append(this.type); 
    if (this.classBound != null)
      for (ClassSignature$Token classSignature$Token : this.classBound)
        stringBuilder.append(classSignature$Token.asType());  
    if (this.ifaceBound != null)
      for (ClassSignature$Token classSignature$Token : this.ifaceBound)
        stringBuilder.append(':').append(classSignature$Token.asType());  
    return stringBuilder.toString();
  }
  
  public String asType() {
    return asType(false);
  }
  
  public String asType(boolean paramBoolean) {
    paramBoolean = false;
    StringBuilder stringBuilder = new StringBuilder();
    if (this.array)
      stringBuilder.append('['); 
    if (this.symbol != '\000')
      stringBuilder.append(this.symbol); 
    if (this.type == null)
      return stringBuilder.toString(); 
    if (!this.inner)
      stringBuilder.append('L'); 
    stringBuilder.append(this.type);
    if (this.signature != null) {
      stringBuilder.append('<');
      for (ClassSignature$IToken classSignature$IToken : this.signature)
        stringBuilder.append(classSignature$IToken.asType()); 
      stringBuilder.append('>');
    } 
    if (this.suffix != null)
      for (ClassSignature$IToken classSignature$IToken : this.suffix)
        stringBuilder.append('.').append(classSignature$IToken.asType());  
    if (!this.inner)
      stringBuilder.append(';'); 
    return stringBuilder.toString();
  }
  
  boolean isRaw() {
    return (this.signature == null);
  }
  
  String getClassType() {
    return (this.type != null) ? this.type : "java/lang/Object";
  }
  
  public ClassSignature$Token asToken() {
    return this;
  }
}
