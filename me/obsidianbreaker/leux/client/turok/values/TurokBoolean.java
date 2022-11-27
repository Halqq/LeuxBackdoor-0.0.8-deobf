package me.obsidianbreaker.leux.client.turok.values;

import give up;

public class TurokBoolean {
  public TurokGeneric value;
  
  public TurokString name;
  
  public TurokString tag;
  
  public TurokString get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public TurokString get_name() {
    (give up)null;
    return this.name;
  }
  
  public boolean get_value() {
    (give up)null;
    return ((Boolean)this.value.get_value()).booleanValue();
  }
  
  public void set_value(boolean paramBoolean) {
    (give up)null;
    this.value.set_value(Boolean.valueOf(paramBoolean));
  }
  
  public TurokBoolean(TurokString paramTurokString1, TurokString paramTurokString2, boolean paramBoolean) {
    this.name = paramTurokString1;
    this.tag = paramTurokString2;
    this.value = new TurokGeneric(Boolean.valueOf(paramBoolean));
  }
}
