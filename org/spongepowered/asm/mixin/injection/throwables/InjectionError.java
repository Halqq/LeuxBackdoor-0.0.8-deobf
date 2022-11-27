package org.spongepowered.asm.mixin.injection.throwables;

public class InjectionError extends Error {
  private static final long serialVersionUID = 1L;
  
  public InjectionError() {}
  
  public InjectionError(String paramString) {
    super(paramString);
  }
  
  public InjectionError(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public InjectionError(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
