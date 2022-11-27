package me.obsidianbreaker.leux.client.turok.values;

import give up;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TurokDouble {
  public TurokString tag;
  
  public double min;
  
  public double value;
  
  public double max;
  
  public TurokString name;
  
  public static double step(double paramDouble1, double paramDouble2) {
    (give up)null;
    double d = floor(paramDouble1, paramDouble2);
    if (d > paramDouble2)
      d = paramDouble2 / 20.0D; 
    if (paramDouble2 > 10.0D)
      d = Math.round(d); 
    if (d == 0.0D)
      d = paramDouble2; 
    return d;
  }
  
  public TurokDouble(TurokString paramTurokString1, TurokString paramTurokString2, double paramDouble1, double paramDouble2, double paramDouble3) {
    this.name = paramTurokString1;
    this.tag = paramTurokString2;
    this.value = paramDouble1;
    this.max = paramDouble3;
    this.min = paramDouble2;
  }
  
  public void set_value(double paramDouble) {
    (give up)null;
    this.value = paramDouble;
  }
  
  public TurokString get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public static double round(double paramDouble) {
    (give up)null;
    BigDecimal bigDecimal = new BigDecimal(paramDouble);
    bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
    return bigDecimal.doubleValue();
  }
  
  public static double floor(double paramDouble1, double paramDouble2) {
    (give up)null;
    paramDouble1 = Math.floor(paramDouble1);
    paramDouble2 = Math.floor(paramDouble2);
    return (paramDouble1 == 0.0D || paramDouble2 == 0.0D) ? (paramDouble1 + paramDouble2) : floor(paramDouble1, paramDouble1 % paramDouble2);
  }
  
  public TurokString get_name() {
    (give up)null;
    return this.name;
  }
  
  public void set_slider_value(double paramDouble) {
    (give up)null;
    if (paramDouble >= this.max) {
      this.value = this.max;
    } else if (paramDouble <= this.min) {
      this.value = this.min;
    } else {
      this.value = paramDouble;
    } 
  }
  
  public double get_value() {
    (give up)null;
    return this.value;
  }
}
