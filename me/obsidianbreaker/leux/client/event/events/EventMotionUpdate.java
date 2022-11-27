package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;

public class EventMotionUpdate extends EventCancellable {
  public int stage;
  
  public EventMotionUpdate(int paramInt) {
    this.stage = paramInt;
  }
}
