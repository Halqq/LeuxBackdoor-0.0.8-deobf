package org.spongepowered.asm.lib.signature;

public class SignatureReader {
  private final String signature;
  
  public SignatureReader(String paramString) {
    this.signature = paramString;
  }
  
  public void accept(SignatureVisitor paramSignatureVisitor) {
    int j;
    String str = this.signature;
    int i = str.length();
    if (str.charAt(0) == '<') {
      char c;
      j = 2;
      do {
        int k = str.indexOf(':', j);
        paramSignatureVisitor.visitFormalTypeParameter(str.substring(j - 1, k));
        j = k + 1;
        c = str.charAt(j);
        if (c == 'L' || c == '[' || c == 'T')
          j = parseType(str, j, paramSignatureVisitor.visitClassBound()); 
        while ((c = str.charAt(j++)) == ':')
          j = parseType(str, j, paramSignatureVisitor.visitInterfaceBound()); 
      } while (c != '>');
    } else {
      j = 0;
    } 
    if (str.charAt(j) == '(') {
      while (str.charAt(++j) != ')')
        j = parseType(str, j, paramSignatureVisitor.visitParameterType()); 
      for (j = parseType(str, j + 1, paramSignatureVisitor.visitReturnType()); j < i; j = parseType(str, j + 1, paramSignatureVisitor.visitExceptionType()));
    } else {
      for (j = parseType(str, j, paramSignatureVisitor.visitSuperclass()); j < i; j = parseType(str, j, paramSignatureVisitor.visitInterface()));
    } 
  }
  
  public void acceptType(SignatureVisitor paramSignatureVisitor) {
    parseType(this.signature, 0, paramSignatureVisitor);
  }
  
  private static int parseType(String paramString, int paramInt, SignatureVisitor paramSignatureVisitor) {
    int i;
    char c;
    switch (c = paramString.charAt(paramInt++)) {
      case 'B':
      case 'C':
      case 'D':
      case 'F':
      case 'I':
      case 'J':
      case 'S':
      case 'V':
      case 'Z':
        paramSignatureVisitor.visitBaseType(c);
        return paramInt;
      case '[':
        return parseType(paramString, paramInt, paramSignatureVisitor.visitArrayType());
      case 'T':
        i = paramString.indexOf(';', paramInt);
        paramSignatureVisitor.visitTypeVariable(paramString.substring(paramInt, i));
        return i + 1;
    } 
    int j = paramInt;
    boolean bool1 = false;
    boolean bool2 = false;
    label43: while (true) {
      String str;
      switch (c = paramString.charAt(paramInt++)) {
        case '.':
        case ';':
          str = paramString.substring(j, paramInt - 1);
          paramSignatureVisitor.visitClassType(str);
          if (c == ';') {
            paramSignatureVisitor.visitEnd();
            return paramInt;
          } 
          j = paramInt;
          bool1 = false;
          bool2 = true;
        case '<':
          str = paramString.substring(j, paramInt - 1);
          paramSignatureVisitor.visitClassType(str);
          bool1 = true;
          while (true) {
            switch (c = paramString.charAt(paramInt)) {
              case '>':
                continue label43;
              case '*':
                paramInt++;
                paramSignatureVisitor.visitTypeArgument();
                continue;
              case '+':
              case '-':
                paramInt = parseType(paramString, paramInt + 1, paramSignatureVisitor.visitTypeArgument(c));
                continue;
            } 
            paramInt = parseType(paramString, paramInt, paramSignatureVisitor.visitTypeArgument('='));
          } 
          break;
      } 
    } 
  }
}
