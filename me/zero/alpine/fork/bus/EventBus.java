package me.zero.alpine.fork.bus;

import give up;
import java.util.Arrays;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public interface EventBus {
  void subscribe(Listenable paramListenable);
  
  void subscribeAll(Listenable... paramVarArgs) {
    (give up)null;
    Arrays.<Listenable>stream(paramVarArgs).forEach(this::subscribe);
  }
  
  void unsubscribeAll(Listenable... paramVarArgs) {
    (give up)null;
    Arrays.<Listenable>stream(paramVarArgs).forEach(this::unsubscribe);
  }
  
  void unsubscribe(Listenable paramListenable);
  
  default void subscribeAll(Iterable paramIterable) {
    (give up)null;
    paramIterable.forEach(this::subscribe);
  }
  
  void post(Object paramObject);
  
  void subscribeAll(Listener... paramVarArgs) {
    (give up)null;
    Arrays.<Listener>stream(paramVarArgs).forEach(this::subscribe);
  }
  
  void unsubscribe(Listener paramListener);
  
  void unsubscribeAll(Listener... paramVarArgs) {
    (give up)null;
    Arrays.<Listener>stream(paramVarArgs).forEach(this::unsubscribe);
  }
  
  default void unsubscribeAll(Iterable paramIterable) {
    (give up)null;
    paramIterable.forEach(this::unsubscribe);
  }
  
  void subscribe(Listener paramListener);
}
