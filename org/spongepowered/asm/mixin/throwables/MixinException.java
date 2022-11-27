package org.spongepowered.asm.mixin.throwables;

public class MixinException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public MixinException() {}
  
  public MixinException(String paramString) {
    super(paramString);
  }
  
  public MixinException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public MixinException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
