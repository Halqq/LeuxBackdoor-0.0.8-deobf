package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class InvalidInterfaceMixinException extends InvalidMixinException {
  private static final long serialVersionUID = 2L;
  
  public InvalidInterfaceMixinException(IMixinInfo paramIMixinInfo, String paramString) {
    super(paramIMixinInfo, paramString);
  }
  
  public InvalidInterfaceMixinException(IMixinContext paramIMixinContext, String paramString) {
    super(paramIMixinContext, paramString);
  }
  
  public InvalidInterfaceMixinException(IMixinInfo paramIMixinInfo, Throwable paramThrowable) {
    super(paramIMixinInfo, paramThrowable);
  }
  
  public InvalidInterfaceMixinException(IMixinContext paramIMixinContext, Throwable paramThrowable) {
    super(paramIMixinContext, paramThrowable);
  }
  
  public InvalidInterfaceMixinException(IMixinInfo paramIMixinInfo, String paramString, Throwable paramThrowable) {
    super(paramIMixinInfo, paramString, paramThrowable);
  }
  
  public InvalidInterfaceMixinException(IMixinContext paramIMixinContext, String paramString, Throwable paramThrowable) {
    super(paramIMixinContext, paramString, paramThrowable);
  }
}
