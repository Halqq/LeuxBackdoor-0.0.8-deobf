package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;

public class EventSetupFog extends EventCancellable {
  public float partial_ticks;
  
  public int start_coords;
  
  public EventSetupFog(int paramInt, float paramFloat) {
    this.start_coords = paramInt;
    this.partial_ticks = paramFloat;
  }
}
