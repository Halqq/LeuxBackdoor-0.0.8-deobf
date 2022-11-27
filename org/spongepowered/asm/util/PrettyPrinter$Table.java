package org.spongepowered.asm.util;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;

class PrettyPrinter$Table implements PrettyPrinter$IVariableWidthEntry {
  final List columns = new ArrayList();
  
  final List rows = new ArrayList();
  
  String format = "%s";
  
  int colSpacing = 2;
  
  boolean addHeader = true;
  
  void headerAdded() {
    this.addHeader = false;
  }
  
  void setColSpacing(int paramInt) {
    this.colSpacing = Math.max(0, paramInt);
    updateFormat();
  }
  
  PrettyPrinter$Table grow(int paramInt) {
    while (this.columns.size() < paramInt)
      this.columns.add(new PrettyPrinter$Column(this)); 
    updateFormat();
    return this;
  }
  
  PrettyPrinter$Column add(PrettyPrinter$Column paramPrettyPrinter$Column) {
    this.columns.add(paramPrettyPrinter$Column);
    return paramPrettyPrinter$Column;
  }
  
  PrettyPrinter$Row add(PrettyPrinter$Row paramPrettyPrinter$Row) {
    this.rows.add(paramPrettyPrinter$Row);
    return paramPrettyPrinter$Row;
  }
  
  PrettyPrinter$Column addColumn(String paramString) {
    return add(new PrettyPrinter$Column(this, paramString));
  }
  
  PrettyPrinter$Column addColumn(PrettyPrinter$Alignment paramPrettyPrinter$Alignment, int paramInt, String paramString) {
    return add(new PrettyPrinter$Column(this, paramPrettyPrinter$Alignment, paramInt, paramString));
  }
  
  PrettyPrinter$Row addRow(Object... paramVarArgs) {
    return add(new PrettyPrinter$Row(this, paramVarArgs));
  }
  
  void updateFormat() {
    String str = Strings.repeat(" ", this.colSpacing);
    StringBuilder stringBuilder = new StringBuilder();
    boolean bool = false;
    for (PrettyPrinter$Column prettyPrinter$Column : this.columns) {
      bool = true;
      stringBuilder.append(prettyPrinter$Column.getFormat());
    } 
    this.format = stringBuilder.toString();
  }
  
  String getFormat() {
    return this.format;
  }
  
  Object[] getTitles() {
    ArrayList<String> arrayList = new ArrayList();
    for (PrettyPrinter$Column prettyPrinter$Column : this.columns)
      arrayList.add(prettyPrinter$Column.getTitle()); 
    return arrayList.toArray();
  }
  
  public String toString() {
    int i = 0;
    String[] arrayOfString = new String[this.columns.size()];
    for (byte b = 0; b < this.columns.size(); b++) {
      arrayOfString[b] = ((PrettyPrinter$Column)this.columns.get(b)).toString();
      i |= !arrayOfString[b].isEmpty() ? 1 : 0;
    } 
    return null;
  }
  
  public int getWidth() {
    String str = toString();
  }
}
