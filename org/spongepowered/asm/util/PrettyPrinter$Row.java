package org.spongepowered.asm.util;

class PrettyPrinter$Row implements PrettyPrinter$IVariableWidthEntry {
  final PrettyPrinter$Table table;
  
  final String[] args;
  
  public PrettyPrinter$Row(PrettyPrinter$Table paramPrettyPrinter$Table, Object... paramVarArgs) {
    this.table = paramPrettyPrinter$Table.grow(paramVarArgs.length);
    this.args = new String[paramVarArgs.length];
    for (byte b = 0; b < paramVarArgs.length; b++) {
      this.args[b] = paramVarArgs[b].toString();
      ((PrettyPrinter$Column)this.table.columns.get(b)).setMinWidth(this.args[b].length());
    } 
  }
  
  public String toString() {
    Object[] arrayOfObject = new Object[this.table.columns.size()];
    for (byte b = 0; b < arrayOfObject.length; b++) {
      PrettyPrinter$Column prettyPrinter$Column = this.table.columns.get(b);
      if (b >= this.args.length) {
        arrayOfObject[b] = "";
      } else {
        arrayOfObject[b] = (this.args[b].length() > prettyPrinter$Column.getMaxWidth()) ? this.args[b].substring(0, prettyPrinter$Column.getMaxWidth()) : this.args[b];
      } 
    } 
    return String.format(this.table.format, arrayOfObject);
  }
  
  public int getWidth() {
    return toString().length();
  }
}
