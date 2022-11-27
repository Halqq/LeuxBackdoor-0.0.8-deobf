package me.zero.alpine.fork.listener;

import give up;
import java.util.function.Predicate;
import net.jodah.typetools.TypeResolver;

public class Listener implements EventHook {
  public EventHook hook;
  
  public Class target;
  
  public int priority;
  
  public Predicate[] filters;
  
  public Class getTarget() {
    (give up)null;
    return this.target;
  }
  
  @SafeVarargs
  @SafeVarargs
  public Listener(EventHook paramEventHook, Predicate... paramVarArgs) {
    this(paramEventHook, 0, paramVarArgs);
  }
  
  public void invoke(Object paramObject) {
    (give up)null;
    if (this.filters.length > 0)
      for (Predicate<Object> predicate : this.filters) {
        if (!predicate.test(paramObject))
          return; 
      }  
    this.hook.invoke(paramObject);
  }
  
  @SafeVarargs
  @SafeVarargs
  public Listener(EventHook paramEventHook, int paramInt, Predicate... paramVarArgs) {
    this.hook = paramEventHook;
    this.priority = paramInt;
    this.target = TypeResolver.resolveRawArgument(EventHook.class, paramEventHook.getClass());
    this.filters = paramVarArgs;
  }
  
  public int getPriority() {
    (give up)null;
    return this.priority;
  }
}
