package org.spongepowered.asm.mixin.injection;

import com.google.common.base.Joiner;

abstract class InjectionPoint$CompositeInjectionPoint extends InjectionPoint {
  protected final InjectionPoint[] components;
  
  protected InjectionPoint$CompositeInjectionPoint(InjectionPoint... paramVarArgs) {
    if (paramVarArgs.length < 2)
      throw new IllegalArgumentException("Must supply two or more component injection points for composite point!"); 
    this.components = paramVarArgs;
  }
  
  public String toString() {
    return "CompositeInjectionPoint(" + getClass().getSimpleName() + ")[" + Joiner.on(',').join((Object[])this.components) + "]";
  }
}
