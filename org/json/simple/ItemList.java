package org.json.simple;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
  private String sp = ",";
  
  List items = new ArrayList();
  
  public ItemList() {}
  
  public ItemList(String paramString) {
    split(paramString, this.sp, this.items);
  }
  
  public ItemList(String paramString1, String paramString2) {
    this.sp = paramString1;
    split(paramString1, paramString2, this.items);
  }
  
  public ItemList(String paramString1, String paramString2, boolean paramBoolean) {
    split(paramString1, paramString2, this.items, paramBoolean);
  }
  
  public List getItems() {
    return this.items;
  }
  
  public String[] getArray() {
    return (String[])this.items.toArray();
  }
  
  public void split(String paramString1, String paramString2, List paramList, boolean paramBoolean) {}
  
  public void split(String paramString1, String paramString2, List paramList) {}
  
  public void setSP(String paramString) {
    this.sp = paramString;
  }
  
  public void add(int paramInt, String paramString) {}
  
  public void add(String paramString) {}
  
  public void addAll(ItemList paramItemList) {
    this.items.addAll(paramItemList.items);
  }
  
  public void addAll(String paramString) {
    split(paramString, this.sp, this.items);
  }
  
  public void addAll(String paramString1, String paramString2) {
    split(paramString1, paramString2, this.items);
  }
  
  public void addAll(String paramString1, String paramString2, boolean paramBoolean) {
    split(paramString1, paramString2, this.items, paramBoolean);
  }
  
  public String get(int paramInt) {
    return this.items.get(paramInt);
  }
  
  public int size() {
    return this.items.size();
  }
  
  public String toString() {
    return toString(this.sp);
  }
  
  public String toString(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < this.items.size(); b++)
      stringBuffer.append(this.items.get(b)); 
    return stringBuffer.toString();
  }
  
  public void clear() {
    this.items.clear();
  }
  
  public void reset() {
    this.sp = ",";
    this.items.clear();
  }
}
