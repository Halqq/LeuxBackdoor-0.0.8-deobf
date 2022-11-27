package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObfuscationData implements Iterable {
  private final Map data = new HashMap<Object, Object>();
  
  private final Object defaultValue;
  
  public ObfuscationData() {
    this(null);
  }
  
  public ObfuscationData(Object paramObject) {
    this.defaultValue = paramObject;
  }
  
  @Deprecated
  @Deprecated
  public void add(ObfuscationType paramObfuscationType, Object paramObject) {
    put(paramObfuscationType, paramObject);
  }
  
  public void put(ObfuscationType paramObfuscationType, Object paramObject) {
    this.data.put(paramObfuscationType, paramObject);
  }
  
  public boolean isEmpty() {
    return this.data.isEmpty();
  }
  
  public Object get(ObfuscationType paramObfuscationType) {
    Object object = this.data.get(paramObfuscationType);
  }
  
  public Iterator iterator() {
    return this.data.keySet().iterator();
  }
  
  public String toString() {
    return String.format("ObfuscationData[%s,DEFAULT=%s]", new Object[] { listValues(), this.defaultValue });
  }
  
  public String values() {
    return "[" + listValues() + "]";
  }
  
  private String listValues() {
    StringBuilder stringBuilder = new StringBuilder();
    boolean bool = false;
    for (ObfuscationType obfuscationType : this.data.keySet()) {
      stringBuilder.append(obfuscationType.getKey()).append('=').append(this.data.get(obfuscationType));
      bool = true;
    } 
    return stringBuilder.toString();
  }
}
