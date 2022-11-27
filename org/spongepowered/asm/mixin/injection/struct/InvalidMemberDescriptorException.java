package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.throwables.MixinException;

public class InvalidMemberDescriptorException extends MixinException {
  private static final long serialVersionUID = 1L;
  
  public InvalidMemberDescriptorException(String paramString) {
    super(paramString);
  }
  
  public InvalidMemberDescriptorException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public InvalidMemberDescriptorException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
