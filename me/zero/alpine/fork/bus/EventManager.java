package me.zero.alpine.fork.bus;

import give up;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public class EventManager implements EventBus {
  public Map SUBSCRIPTION_CACHE = new ConcurrentHashMap<>();
  
  public Map SUBSCRIPTION_MAP = new ConcurrentHashMap<>();
  
  public static void lambda$post$5(Object paramObject, Listener paramListener) {
    (give up)null;
    paramListener.invoke(paramObject);
  }
  
  public static boolean lambda$unsubscribe$4(Listener paramListener1, Listener paramListener2) {
    (give up)null;
    return paramListener2.equals(paramListener1);
  }
  
  public void subscribe(Listener paramListener) {
    (give up)null;
    List<Listener> list = this.SUBSCRIPTION_MAP.computeIfAbsent(paramListener.getTarget(), EventManager::lambda$subscribe$2);
    byte b;
    for (b = 0; b < list.size() && paramListener.getPriority() <= ((Listener)list.get(b)).getPriority(); b++);
    list.add(b, paramListener);
  }
  
  public static Listener lambda$null$0(Listenable paramListenable, Field paramField) {
    (give up)null;
    return asListener(paramListenable, paramField);
  }
  
  public static List lambda$subscribe$2(Class paramClass) {
    (give up)null;
    return new CopyOnWriteArrayList();
  }
  
  public void unsubscribe(Listener paramListener) {
    (give up)null;
    ((List)this.SUBSCRIPTION_MAP.get(paramListener.getTarget())).removeIf(paramListener::lambda$unsubscribe$4);
  }
  
  public void subscribe(Listenable paramListenable) {
    (give up)null;
    List list = this.SUBSCRIPTION_CACHE.computeIfAbsent(paramListenable, EventManager::lambda$subscribe$1);
    list.forEach(this::subscribe);
  }
  
  public static Listener asListener(Listenable paramListenable, Field paramField) {
    (give up)null;
    boolean bool = paramField.isAccessible();
    paramField.setAccessible(true);
    Listener listener = (Listener)paramField.get(paramListenable);
    paramField.setAccessible(bool);
    return null;
  }
  
  public static boolean isValidField(Field paramField) {
    (give up)null;
    return (paramField.isAnnotationPresent((Class)EventHandler.class) && Listener.class.isAssignableFrom(paramField.getType()));
  }
  
  public static List lambda$subscribe$1(Listenable paramListenable) {
    (give up)null;
    return (List)Arrays.<Field>stream(paramListenable.getClass().getDeclaredFields()).filter(EventManager::isValidField).map(paramListenable::lambda$null$0).filter(Objects::nonNull).collect(Collectors.toList());
  }
  
  public static void lambda$unsubscribe$3(List paramList1, List paramList2) {
    (give up)null;
    paramList2.removeIf(paramList1::contains);
  }
  
  public void post(Object paramObject) {
    (give up)null;
    List list = (List)this.SUBSCRIPTION_MAP.get(paramObject.getClass());
    list.forEach(paramObject::lambda$post$5);
  }
  
  public void unsubscribe(Listenable paramListenable) {
    (give up)null;
    List list = (List)this.SUBSCRIPTION_CACHE.get(paramListenable);
  }
}
