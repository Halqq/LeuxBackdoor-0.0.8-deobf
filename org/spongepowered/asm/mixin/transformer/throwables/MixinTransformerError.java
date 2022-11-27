package org.spongepowered.asm.mixin.transformer.throwables;

public class MixinTransformerError extends Error {
  private static final long serialVersionUID = 1L;
  
  public MixinTransformerError(String paramString) {
    super(paramString);
  }
  
  public MixinTransformerError(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public MixinTransformerError(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
