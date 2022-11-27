package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.throwables.MixinException;

public class InvalidMixinException extends MixinException {
  private static final long serialVersionUID = 2L;
  
  private final IMixinInfo mixin;
  
  public InvalidMixinException(IMixinInfo paramIMixinInfo, String paramString) {
    super(paramString);
    this.mixin = paramIMixinInfo;
  }
  
  public InvalidMixinException(IMixinContext paramIMixinContext, String paramString) {
    this(paramIMixinContext.getMixin(), paramString);
  }
  
  public InvalidMixinException(IMixinInfo paramIMixinInfo, Throwable paramThrowable) {
    super(paramThrowable);
    this.mixin = paramIMixinInfo;
  }
  
  public InvalidMixinException(IMixinContext paramIMixinContext, Throwable paramThrowable) {
    this(paramIMixinContext.getMixin(), paramThrowable);
  }
  
  public InvalidMixinException(IMixinInfo paramIMixinInfo, String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
    this.mixin = paramIMixinInfo;
  }
  
  public InvalidMixinException(IMixinContext paramIMixinContext, String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
    this.mixin = paramIMixinContext.getMixin();
  }
  
  public IMixinInfo getMixin() {
    return this.mixin;
  }
}
