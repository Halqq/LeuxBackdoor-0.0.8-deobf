package org.spongepowered.asm.mixin.throwables;

public class ClassAlreadyLoadedException extends MixinException {
  private static final long serialVersionUID = 1L;
  
  public ClassAlreadyLoadedException(String paramString) {
    super(paramString);
  }
  
  public ClassAlreadyLoadedException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public ClassAlreadyLoadedException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
