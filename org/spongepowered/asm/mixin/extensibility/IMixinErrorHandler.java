package org.spongepowered.asm.mixin.extensibility;

public interface IMixinErrorHandler {
  IMixinErrorHandler$ErrorAction onPrepareError(IMixinConfig paramIMixinConfig, Throwable paramThrowable, IMixinInfo paramIMixinInfo, IMixinErrorHandler$ErrorAction paramIMixinErrorHandler$ErrorAction);
  
  IMixinErrorHandler$ErrorAction onApplyError(String paramString, Throwable paramThrowable, IMixinInfo paramIMixinInfo, IMixinErrorHandler$ErrorAction paramIMixinErrorHandler$ErrorAction);
}
