package org.spongepowered.asm.util;

import com.google.common.base.Strings;

class PrettyPrinter$HorizontalRule implements PrettyPrinter$ISpecialEntry {
  private final char[] hrChars;
  
  final PrettyPrinter this$0;
  
  public PrettyPrinter$HorizontalRule(char... paramVarArgs) {
    this.hrChars = paramVarArgs;
  }
  
  public String toString() {
    return Strings.repeat(new String(this.hrChars), PrettyPrinter.this.width + 2);
  }
}
