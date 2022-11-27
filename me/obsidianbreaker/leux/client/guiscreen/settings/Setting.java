package me.obsidianbreaker.leux.client.guiscreen.settings;

import give up;
import java.util.List;
import me.obsidianbreaker.leux.client.modules.Module;

public class Setting {
  public String tag;
  
  public Module master;
  
  public double min;
  
  public String type;
  
  public double slider;
  
  public String current;
  
  public boolean button;
  
  public String label;
  
  public String name;
  
  public List combobox;
  
  public double max;
  
  public Module get_master() {
    (give up)null;
    return this.master;
  }
  
  public void set_value(String paramString) {
    (give up)null;
    this.label = paramString;
  }
  
  public boolean get_value(boolean paramBoolean) {
    paramBoolean = true;
    (give up)null;
    return this.button;
  }
  
  public void set_value(boolean paramBoolean) {
    (give up)null;
    this.button = paramBoolean;
  }
  
  public void set_current_value(String paramString) {
    (give up)null;
    this.current = paramString;
  }
  
  public double get_value(double paramDouble) {
    (give up)null;
    return this.slider;
  }
  
  public Setting(Module paramModule, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3) {
    this.master = paramModule;
    this.name = paramString1;
    this.tag = paramString2;
    this.slider = paramInt1;
    this.min = paramInt2;
    this.max = paramInt3;
    this.type = "integerslider";
  }
  
  public String get_current_value() {
    (give up)null;
    return this.current;
  }
  
  public double get_max(double paramDouble) {
    (give up)null;
    return this.max;
  }
  
  public double get_min(double paramDouble) {
    (give up)null;
    return this.min;
  }
  
  public void set_value(int paramInt) {
    (give up)null;
    if (paramInt >= get_max(paramInt)) {
      this.slider = get_max(paramInt);
    } else if (paramInt <= get_min(paramInt)) {
      this.slider = get_min(paramInt);
    } else {
      this.slider = paramInt;
    } 
  }
  
  public int get_max(int paramInt) {
    (give up)null;
    return (int)this.max;
  }
  
  public boolean in(String paramString) {
    (give up)null;
    return this.current.equalsIgnoreCase(paramString);
  }
  
  public String get_name() {
    (give up)null;
    return this.name;
  }
  
  public List get_values() {
    (give up)null;
    return this.combobox;
  }
  
  public String get_type() {
    (give up)null;
    return this.type;
  }
  
  public boolean is_info() {
    (give up)null;
    return this.name.equalsIgnoreCase("info");
  }
  
  public int get_min(int paramInt) {
    (give up)null;
    return (int)this.min;
  }
  
  public void set_value(double paramDouble) {
    (give up)null;
    if (paramDouble >= get_max(paramDouble)) {
      this.slider = get_max(paramDouble);
    } else if (paramDouble <= get_min(paramDouble)) {
      this.slider = get_min(paramDouble);
    } else {
      this.slider = paramDouble;
    } 
  }
  
  public Setting(Module paramModule, String paramString1, String paramString2, String paramString3) {
    this.master = paramModule;
    this.name = paramString1;
    this.tag = paramString2;
    this.label = paramString3;
    this.type = "label";
  }
  
  public Setting(Module paramModule, String paramString1, String paramString2, double paramDouble1, double paramDouble2, double paramDouble3) {
    this.master = paramModule;
    this.name = paramString1;
    this.tag = paramString2;
    this.slider = paramDouble1;
    this.min = paramDouble2;
    this.max = paramDouble3;
    this.type = "doubleslider";
  }
  
  public String get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public String get_value(String paramString) {
    paramString = "me";
    (give up)null;
    return this.label;
  }
  
  public int get_value(int paramInt) {
    (give up)null;
    return (int)Math.round(this.slider);
  }
  
  public Setting(Module paramModule, String paramString1, String paramString2, List paramList, String paramString3) {
    this.master = paramModule;
    this.name = paramString1;
    this.tag = paramString2;
    this.combobox = paramList;
    this.current = paramString3;
    this.type = "combobox";
  }
  
  public Setting(Module paramModule, String paramString1, String paramString2, boolean paramBoolean) {
    this.master = paramModule;
    this.name = paramString1;
    this.tag = paramString2;
    this.button = paramBoolean;
    this.type = "button";
  }
}
