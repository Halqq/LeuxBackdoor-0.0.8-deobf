package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.code.ISliceContext;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

public class InvalidSliceException extends InvalidInjectionException {
  private static final long serialVersionUID = 1L;
  
  public InvalidSliceException(IMixinContext paramIMixinContext, String paramString) {
    super(paramIMixinContext, paramString);
  }
  
  public InvalidSliceException(ISliceContext paramISliceContext, String paramString) {
    super(paramISliceContext.getContext(), paramString);
  }
  
  public InvalidSliceException(IMixinContext paramIMixinContext, Throwable paramThrowable) {
    super(paramIMixinContext, paramThrowable);
  }
  
  public InvalidSliceException(ISliceContext paramISliceContext, Throwable paramThrowable) {
    super(paramISliceContext.getContext(), paramThrowable);
  }
  
  public InvalidSliceException(IMixinContext paramIMixinContext, String paramString, Throwable paramThrowable) {
    super(paramIMixinContext, paramString, paramThrowable);
  }
  
  public InvalidSliceException(ISliceContext paramISliceContext, String paramString, Throwable paramThrowable) {
    super(paramISliceContext.getContext(), paramString, paramThrowable);
  }
}
