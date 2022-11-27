package org.spongepowered.asm.mixin;

import com.google.common.collect.ImmutableList;
import java.util.List;

public final class MixinEnvironment$Phase {
  static final MixinEnvironment$Phase NOT_INITIALISED = new MixinEnvironment$Phase(-1, "NOT_INITIALISED");
  
  public static final MixinEnvironment$Phase PREINIT = new MixinEnvironment$Phase(0, "PREINIT");
  
  public static final MixinEnvironment$Phase INIT = new MixinEnvironment$Phase(1, "INIT");
  
  public static final MixinEnvironment$Phase DEFAULT = new MixinEnvironment$Phase(2, "DEFAULT");
  
  static final List phases = (List)ImmutableList.of(PREINIT, INIT, DEFAULT);
  
  final int ordinal;
  
  final String name;
  
  private MixinEnvironment environment;
  
  private MixinEnvironment$Phase(int paramInt, String paramString) {
    this.ordinal = paramInt;
    this.name = paramString;
  }
  
  public String toString() {
    return this.name;
  }
  
  public static MixinEnvironment$Phase forName(String paramString) {
    for (MixinEnvironment$Phase mixinEnvironment$Phase : phases) {
      if (mixinEnvironment$Phase.name.equals(paramString))
        return mixinEnvironment$Phase; 
    } 
    return null;
  }
  
  MixinEnvironment getEnvironment() {
    if (this.ordinal < 0)
      throw new IllegalArgumentException("Cannot access the NOT_INITIALISED environment"); 
    if (this.environment == null)
      this.environment = new MixinEnvironment(this); 
    return this.environment;
  }
}
