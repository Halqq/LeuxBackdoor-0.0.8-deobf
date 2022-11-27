package org.spongepowered.asm.util;

interface ClassSignature$IToken {
  public static final String WILDCARDS = "+-";
  
  String asType();
  
  String asBound();
  
  ClassSignature$Token asToken();
  
  ClassSignature$IToken setArray(boolean paramBoolean);
  
  ClassSignature$IToken setWildcard(char paramChar);
}
