package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.lib.Type;

public class LocalVariableDiscriminator$Context$Local {
  int ord = 0;
  
  String name;
  
  Type type;
  
  final LocalVariableDiscriminator$Context this$0;
  
  public LocalVariableDiscriminator$Context$Local(String paramString, Type paramType) {
    this.name = paramString;
    this.type = paramType;
  }
  
  public String toString() {
    return String.format("Local[ordinal=%d, name=%s, type=%s]", new Object[] { Integer.valueOf(this.ord), this.name, this.type });
  }
}
