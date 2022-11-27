package org.spongepowered.asm.mixin.injection.callback;

public class CancellationException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public CancellationException() {}
  
  public CancellationException(String paramString) {
    super(paramString);
  }
  
  public CancellationException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public CancellationException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
