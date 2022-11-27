package org.spongepowered.asm.mixin.throwables;

public class MixinPrepareError extends Error {
  private static final long serialVersionUID = 1L;
  
  public MixinPrepareError(String paramString) {
    super(paramString);
  }
  
  public MixinPrepareError(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public MixinPrepareError(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
