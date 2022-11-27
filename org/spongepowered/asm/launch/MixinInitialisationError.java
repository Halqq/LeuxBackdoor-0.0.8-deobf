package org.spongepowered.asm.launch;

public class MixinInitialisationError extends Error {
  private static final long serialVersionUID = 1L;
  
  public MixinInitialisationError() {}
  
  public MixinInitialisationError(String paramString) {
    super(paramString);
  }
  
  public MixinInitialisationError(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public MixinInitialisationError(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
