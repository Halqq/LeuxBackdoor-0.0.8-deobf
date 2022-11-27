package org.spongepowered.asm.util.throwables;

public class InvalidConstraintException extends IllegalArgumentException {
  private static final long serialVersionUID = 1L;
  
  public InvalidConstraintException() {}
  
  public InvalidConstraintException(String paramString) {
    super(paramString);
  }
  
  public InvalidConstraintException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public InvalidConstraintException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}
