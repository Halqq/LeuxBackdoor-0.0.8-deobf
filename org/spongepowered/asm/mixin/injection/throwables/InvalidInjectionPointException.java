package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class InvalidInjectionPointException extends InvalidInjectionException {
  private static final long serialVersionUID = 2L;
  
  public InvalidInjectionPointException(IMixinContext paramIMixinContext, String paramString, Object... paramVarArgs) {
    super(paramIMixinContext, String.format(paramString, paramVarArgs));
  }
  
  public InvalidInjectionPointException(InjectionInfo paramInjectionInfo, String paramString, Object... paramVarArgs) {
    super(paramInjectionInfo, String.format(paramString, paramVarArgs));
  }
  
  public InvalidInjectionPointException(IMixinContext paramIMixinContext, Throwable paramThrowable, String paramString, Object... paramVarArgs) {
    super(paramIMixinContext, String.format(paramString, paramVarArgs), paramThrowable);
  }
  
  public InvalidInjectionPointException(InjectionInfo paramInjectionInfo, Throwable paramThrowable, String paramString, Object... paramVarArgs) {
    super(paramInjectionInfo, String.format(paramString, paramVarArgs), paramThrowable);
  }
}
