package org.spongepowered.asm.mixin.injection.invoke.arg;

public abstract class Args {
  protected final Object[] values;
  
  protected Args(Object[] paramArrayOfObject) {
    this.values = paramArrayOfObject;
  }
  
  public int size() {
    return this.values.length;
  }
  
  public Object get(int paramInt) {
    return this.values[paramInt];
  }
  
  public abstract void set(int paramInt, Object paramObject);
  
  public abstract void setAll(Object... paramVarArgs);
}
