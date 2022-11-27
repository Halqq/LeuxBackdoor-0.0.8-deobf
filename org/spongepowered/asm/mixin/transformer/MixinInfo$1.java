package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Function;
import org.spongepowered.asm.lib.Type;

class MixinInfo$1 implements Function {
  final MixinInfo this$0;
  
  public String apply(Type paramType) {
    return paramType.getClassName();
  }
  
  public Object apply(Object paramObject) {
    return apply((Type)paramObject);
  }
}
