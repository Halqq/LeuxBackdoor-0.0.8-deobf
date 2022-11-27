package org.spongepowered.asm.util;

class PrettyPrinter$Column {
  private final PrettyPrinter$Table table;
  
  private PrettyPrinter$Alignment align = PrettyPrinter$Alignment.LEFT;
  
  private int minWidth = 1;
  
  private int maxWidth = Integer.MAX_VALUE;
  
  private int size = 0;
  
  private String title = "";
  
  private String format = "%s";
  
  PrettyPrinter$Column(PrettyPrinter$Table paramPrettyPrinter$Table) {
    this.table = paramPrettyPrinter$Table;
  }
  
  PrettyPrinter$Column(PrettyPrinter$Table paramPrettyPrinter$Table, String paramString) {
    this(paramPrettyPrinter$Table);
    this.title = paramString;
    this.minWidth = paramString.length();
    updateFormat();
  }
  
  PrettyPrinter$Column(PrettyPrinter$Table paramPrettyPrinter$Table, PrettyPrinter$Alignment paramPrettyPrinter$Alignment, int paramInt, String paramString) {
    this(paramPrettyPrinter$Table, paramString);
    this.align = paramPrettyPrinter$Alignment;
    this.size = paramInt;
  }
  
  void setAlignment(PrettyPrinter$Alignment paramPrettyPrinter$Alignment) {
    this.align = paramPrettyPrinter$Alignment;
    updateFormat();
  }
  
  void setWidth(int paramInt) {
    if (paramInt > this.size) {
      this.size = paramInt;
      updateFormat();
    } 
  }
  
  void setMinWidth(int paramInt) {
    if (paramInt > this.minWidth) {
      this.minWidth = paramInt;
      updateFormat();
    } 
  }
  
  void setMaxWidth(int paramInt) {
    this.size = Math.min(this.size, this.maxWidth);
    this.maxWidth = Math.max(1, paramInt);
    updateFormat();
  }
  
  void setTitle(String paramString) {
    this.title = paramString;
    setWidth(paramString.length());
  }
  
  private void updateFormat() {
    int i = Math.min(this.maxWidth, (this.size == 0) ? this.minWidth : this.size);
    this.format = "%" + ((this.align == PrettyPrinter$Alignment.RIGHT) ? "" : "-") + i + "s";
    this.table.updateFormat();
  }
  
  int getMaxWidth() {
    return this.maxWidth;
  }
  
  String getTitle() {
    return this.title;
  }
  
  String getFormat() {
    return this.format;
  }
  
  public String toString() {
    return (this.title.length() > this.maxWidth) ? this.title.substring(0, this.maxWidth) : this.title;
  }
}
