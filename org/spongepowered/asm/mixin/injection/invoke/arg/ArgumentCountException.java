package org.spongepowered.asm.mixin.injection.invoke.arg;

public class ArgumentCountException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;
  
  public ArgumentCountException(int paramInt1, int paramInt2, String paramString) {
    super("Invalid number of arguments for setAll, received " + paramInt1 + " but expected " + paramInt2 + ": " + paramString);
  }
}
