package org.spongepowered.asm.mixin.throwables;

public class MixinApplyError extends Error {
  private static final long serialVersionUID = 1L;
  
  public MixinApplyError(String paramString) {
    super(paramString);
  }
  
  public MixinApplyError(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public MixinApplyError(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
