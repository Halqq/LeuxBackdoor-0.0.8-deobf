package org.spongepowered.asm.util;

public final class Counter {
  public int value;
  
  public boolean equals(Object paramObject) {
    return (paramObject.getClass() == Counter.class && ((Counter)paramObject).value == this.value);
  }
  
  public int hashCode() {
    return this.value;
  }
}
