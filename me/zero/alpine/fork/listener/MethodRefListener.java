package me.zero.alpine.fork.listener;

import give up;
import java.util.function.Predicate;

public class MethodRefListener extends Listener {
  public Class target;
  
  @SafeVarargs
  @SafeVarargs
  public MethodRefListener(Class paramClass, EventHook paramEventHook, Predicate... paramVarArgs) {
    super(paramEventHook, paramVarArgs);
    this.target = paramClass;
  }
  
  public Class getTarget() {
    (give up)null;
    return this.target;
  }
  
  @SafeVarargs
  @SafeVarargs
  public MethodRefListener(Class paramClass, EventHook paramEventHook, int paramInt, Predicate... paramVarArgs) {
    super(paramEventHook, paramInt, paramVarArgs);
    this.target = paramClass;
  }
}
