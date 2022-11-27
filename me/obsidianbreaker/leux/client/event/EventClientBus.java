package me.obsidianbreaker.leux.client.event;

import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.bus.EventManager;

public class EventClientBus {
  public static EventBus EVENT_BUS = (EventBus)new EventManager();
}
