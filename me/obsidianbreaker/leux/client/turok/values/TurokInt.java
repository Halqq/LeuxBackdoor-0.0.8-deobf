package me.obsidianbreaker.leux.client.turok.values;

import give up;

public class TurokInt {
  public int value;
  
  public TurokString name;
  
  public int min;
  
  public TurokString tag;
  
  public int max;
  
  public int get_value() {
    (give up)null;
    return this.value;
  }
  
  public void set_value(int paramInt) {
    (give up)null;
    this.value = paramInt;
  }
  
  public TurokString get_name() {
    (give up)null;
    return this.name;
  }
  
  public void set_slider_value(int paramInt) {
    (give up)null;
    if (paramInt >= this.max) {
      this.value = this.max;
    } else if (paramInt <= this.min) {
      this.value = this.min;
    } else {
      this.value = paramInt;
    } 
  }
  
  public TurokString get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public TurokInt(TurokString paramTurokString1, TurokString paramTurokString2, int paramInt1, int paramInt2, int paramInt3) {
    this.name = paramTurokString1;
    this.tag = paramTurokString2;
    this.value = paramInt1;
    this.max = paramInt3;
    this.min = paramInt2;
  }
}
