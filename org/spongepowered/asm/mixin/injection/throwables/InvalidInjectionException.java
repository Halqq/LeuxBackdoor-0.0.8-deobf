package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

public class InvalidInjectionException extends InvalidMixinException {
  private static final long serialVersionUID = 2L;
  
  private final InjectionInfo info = null;
  
  public InvalidInjectionException(IMixinContext paramIMixinContext, String paramString) {
    super(paramIMixinContext, paramString);
  }
  
  public InvalidInjectionException(InjectionInfo paramInjectionInfo, String paramString) {
    super(paramInjectionInfo.getContext(), paramString);
  }
  
  public InvalidInjectionException(IMixinContext paramIMixinContext, Throwable paramThrowable) {
    super(paramIMixinContext, paramThrowable);
  }
  
  public InvalidInjectionException(InjectionInfo paramInjectionInfo, Throwable paramThrowable) {
    super(paramInjectionInfo.getContext(), paramThrowable);
  }
  
  public InvalidInjectionException(IMixinContext paramIMixinContext, String paramString, Throwable paramThrowable) {
    super(paramIMixinContext, paramString, paramThrowable);
  }
  
  public InvalidInjectionException(InjectionInfo paramInjectionInfo, String paramString, Throwable paramThrowable) {
    super(paramInjectionInfo.getContext(), paramString, paramThrowable);
  }
  
  public InjectionInfo getInjectionInfo() {
    return this.info;
  }
}
