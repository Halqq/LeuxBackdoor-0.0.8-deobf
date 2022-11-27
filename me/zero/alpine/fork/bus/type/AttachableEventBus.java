package me.zero.alpine.fork.bus.type;

import me.zero.alpine.fork.bus.EventBus;

public interface AttachableEventBus extends EventBus {
  void detach(EventBus paramEventBus);
  
  void attach(EventBus paramEventBus);
}
