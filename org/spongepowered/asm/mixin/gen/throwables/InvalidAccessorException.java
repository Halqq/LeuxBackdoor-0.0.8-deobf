package org.spongepowered.asm.mixin.gen.throwables;

import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class InvalidAccessorException extends InvalidMixinException {
  private static final long serialVersionUID = 2L;
  
  private final AccessorInfo info = null;
  
  public InvalidAccessorException(IMixinContext paramIMixinContext, String paramString) {
    super(paramIMixinContext, paramString);
  }
  
  public InvalidAccessorException(AccessorInfo paramAccessorInfo, String paramString) {
    super(paramAccessorInfo.getContext(), paramString);
  }
  
  public InvalidAccessorException(IMixinContext paramIMixinContext, Throwable paramThrowable) {
    super(paramIMixinContext, paramThrowable);
  }
  
  public InvalidAccessorException(AccessorInfo paramAccessorInfo, Throwable paramThrowable) {
    super(paramAccessorInfo.getContext(), paramThrowable);
  }
  
  public InvalidAccessorException(IMixinContext paramIMixinContext, String paramString, Throwable paramThrowable) {
    super(paramIMixinContext, paramString, paramThrowable);
  }
  
  public InvalidAccessorException(AccessorInfo paramAccessorInfo, String paramString, Throwable paramThrowable) {
    super(paramAccessorInfo.getContext(), paramString, paramThrowable);
  }
  
  public AccessorInfo getAccessorInfo() {
    return this.info;
  }
}
