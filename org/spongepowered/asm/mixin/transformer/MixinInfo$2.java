package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Function;

class MixinInfo$2 implements Function {
  final MixinInfo this$0;
  
  public String apply(String paramString) {
    return MixinInfo.this.getParent().remapClassName(MixinInfo.this.getClassRef(), paramString);
  }
  
  public Object apply(Object paramObject) {
    return apply((String)paramObject);
  }
}
