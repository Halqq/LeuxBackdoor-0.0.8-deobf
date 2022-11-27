package me.obsidianbreaker.leux.client.turok.values;

import give up;

public class TurokFloat {
  public float value;
  
  public float min;
  
  public TurokString name;
  
  public TurokString tag;
  
  public float max;
  
  public TurokString get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public TurokFloat(TurokString paramTurokString1, TurokString paramTurokString2, float paramFloat1, float paramFloat2, float paramFloat3) {
    this.name = paramTurokString1;
    this.tag = paramTurokString2;
    this.value = this.value;
    this.max = paramFloat3;
    this.min = paramFloat2;
  }
  
  public void set_value(float paramFloat) {
    (give up)null;
    this.value = paramFloat;
  }
  
  public float get_value() {
    (give up)null;
    return this.value;
  }
  
  public void set_slider_value(float paramFloat) {
    (give up)null;
    if (paramFloat >= this.max) {
      this.value = this.max;
    } else if (paramFloat <= this.min) {
      this.value = this.min;
    } else {
      this.value = paramFloat;
    } 
  }
  
  public TurokString get_name() {
    (give up)null;
    return this.name;
  }
}
