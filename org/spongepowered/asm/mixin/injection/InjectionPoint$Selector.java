package org.spongepowered.asm.mixin.injection;

public enum InjectionPoint$Selector {
  FIRST, LAST, ONE;
  
  public static final InjectionPoint$Selector DEFAULT;
  
  private static final InjectionPoint$Selector[] $VALUES = new InjectionPoint$Selector[] { FIRST, LAST, ONE };
  
  static {
    DEFAULT = FIRST;
  }
}
