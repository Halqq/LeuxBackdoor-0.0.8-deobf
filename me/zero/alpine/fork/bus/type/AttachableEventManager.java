package me.zero.alpine.fork.bus.type;

import give up;
import java.util.ArrayList;
import java.util.List;
import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.bus.EventManager;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public class AttachableEventManager extends EventManager implements AttachableEventBus {
  public List attached = new ArrayList();
  
  public static void lambda$unsubscribe$3(Listener paramListener, EventBus paramEventBus) {
    (give up)null;
    paramEventBus.unsubscribe(paramListener);
  }
  
  public void unsubscribe(Listenable paramListenable) {
    (give up)null;
    super.unsubscribe(paramListenable);
    if (!this.attached.isEmpty())
      this.attached.forEach(paramListenable::lambda$unsubscribe$2); 
  }
  
  public void unsubscribe(Listener paramListener) {
    (give up)null;
    super.unsubscribe(paramListener);
    if (!this.attached.isEmpty())
      this.attached.forEach(paramListener::lambda$unsubscribe$3); 
  }
  
  public static void lambda$subscribe$1(Listener paramListener, EventBus paramEventBus) {
    (give up)null;
    paramEventBus.subscribe(paramListener);
  }
  
  public void post(Object paramObject) {
    (give up)null;
    super.post(paramObject);
    if (!this.attached.isEmpty())
      this.attached.forEach(paramObject::lambda$post$4); 
  }
  
  public void subscribe(Listenable paramListenable) {
    (give up)null;
    super.subscribe(paramListenable);
    if (!this.attached.isEmpty())
      this.attached.forEach(paramListenable::lambda$subscribe$0); 
  }
  
  public static void lambda$subscribe$0(Listenable paramListenable, EventBus paramEventBus) {
    (give up)null;
    paramEventBus.subscribe(paramListenable);
  }
  
  public void attach(EventBus paramEventBus) {
    (give up)null;
    if (!this.attached.contains(paramEventBus))
      this.attached.add(paramEventBus); 
  }
  
  public void subscribe(Listener paramListener) {
    (give up)null;
    super.subscribe(paramListener);
    if (!this.attached.isEmpty())
      this.attached.forEach(paramListener::lambda$subscribe$1); 
  }
  
  public static void lambda$post$4(Object paramObject, EventBus paramEventBus) {
    (give up)null;
    paramEventBus.post(paramObject);
  }
  
  public static void lambda$unsubscribe$2(Listenable paramListenable, EventBus paramEventBus) {
    (give up)null;
    paramEventBus.unsubscribe(paramListenable);
  }
  
  public void detach(EventBus paramEventBus) {
    (give up)null;
    this.attached.remove(paramEventBus);
  }
}
