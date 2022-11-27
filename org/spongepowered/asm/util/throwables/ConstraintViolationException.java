package org.spongepowered.asm.util.throwables;

import org.spongepowered.asm.util.ConstraintParser;

public class ConstraintViolationException extends Exception {
  private static final String MISSING_VALUE = "UNRESOLVED";
  
  private static final long serialVersionUID = 1L;
  
  private final ConstraintParser.Constraint constraint;
  
  private final String badValue;
  
  public ConstraintViolationException(ConstraintParser.Constraint paramConstraint) {
    this.constraint = paramConstraint;
    this.badValue = "UNRESOLVED";
  }
  
  public ConstraintViolationException(ConstraintParser.Constraint paramConstraint, int paramInt) {
    this.constraint = paramConstraint;
    this.badValue = String.valueOf(paramInt);
  }
  
  public ConstraintViolationException(String paramString, ConstraintParser.Constraint paramConstraint) {
    super(paramString);
    this.constraint = paramConstraint;
    this.badValue = "UNRESOLVED";
  }
  
  public ConstraintViolationException(String paramString, ConstraintParser.Constraint paramConstraint, int paramInt) {
    super(paramString);
    this.constraint = paramConstraint;
    this.badValue = String.valueOf(paramInt);
  }
  
  public ConstraintViolationException(Throwable paramThrowable, ConstraintParser.Constraint paramConstraint) {
    super(paramThrowable);
    this.constraint = paramConstraint;
    this.badValue = "UNRESOLVED";
  }
  
  public ConstraintViolationException(Throwable paramThrowable, ConstraintParser.Constraint paramConstraint, int paramInt) {
    super(paramThrowable);
    this.constraint = paramConstraint;
    this.badValue = String.valueOf(paramInt);
  }
  
  public ConstraintViolationException(String paramString, Throwable paramThrowable, ConstraintParser.Constraint paramConstraint) {
    super(paramString, paramThrowable);
    this.constraint = paramConstraint;
    this.badValue = "UNRESOLVED";
  }
  
  public ConstraintViolationException(String paramString, Throwable paramThrowable, ConstraintParser.Constraint paramConstraint, int paramInt) {
    super(paramString, paramThrowable);
    this.constraint = paramConstraint;
    this.badValue = String.valueOf(paramInt);
  }
  
  public ConstraintParser.Constraint getConstraint() {
    return this.constraint;
  }
  
  public String getBadValue() {
    return this.badValue;
  }
}
