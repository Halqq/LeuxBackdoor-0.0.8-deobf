package me.obsidianbreaker.leux.client.event;

import give up;

public enum EventCancellable$Era {
  EVENT_PRE, EVENT_POST, EVENT_PERI;
  
  public static EventCancellable$Era[] $VALUES;
  
  static {
    EVENT_POST = new EventCancellable$Era("EVENT_POST", 2);
    $VALUES = new EventCancellable$Era[] { EVENT_PRE, EVENT_PERI, EVENT_POST };
  }
}
