package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.throwables.MixinException;

public class InvalidImplicitDiscriminatorException extends MixinException {
  private static final long serialVersionUID = 1L;
  
  public InvalidImplicitDiscriminatorException(String paramString) {
    super(paramString);
  }
  
  public InvalidImplicitDiscriminatorException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
