package org.spongepowered.asm.util;

class PrettyPrinter$CentredText {
  private final Object centred;
  
  final PrettyPrinter this$0;
  
  public PrettyPrinter$CentredText(Object paramObject) {
    this.centred = paramObject;
  }
  
  public String toString() {
    String str = this.centred.toString();
    return String.format("%" + ((PrettyPrinter.this.width - str.length()) / 2 + str.length()) + "s", new Object[] { str });
  }
}
