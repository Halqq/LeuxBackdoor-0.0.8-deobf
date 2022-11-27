package org.spongepowered.asm.mixin.transformer.ext.extensions;

import org.spongepowered.asm.mixin.throwables.MixinException;

public class ExtensionCheckClass$ValidationFailedException extends MixinException {
  private static final long serialVersionUID = 1L;
  
  public ExtensionCheckClass$ValidationFailedException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
  
  public ExtensionCheckClass$ValidationFailedException(String paramString) {
    super(paramString);
  }
  
  public ExtensionCheckClass$ValidationFailedException(Throwable paramThrowable) {
    super(paramThrowable);
  }
}
