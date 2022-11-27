package org.spongepowered.asm.util;

class PrettyPrinter$KeyValue implements PrettyPrinter$IVariableWidthEntry {
  private final String key;
  
  private final Object value;
  
  final PrettyPrinter this$0;
  
  public PrettyPrinter$KeyValue(String paramString, Object paramObject) {
    this.key = paramString;
    this.value = paramObject;
  }
  
  public String toString() {
    return String.format(PrettyPrinter.this.kvFormat, new Object[] { this.key, this.value });
  }
  
  public int getWidth() {
    return toString().length();
  }
}
