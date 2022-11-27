package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;

public class EventPlayerTravel extends EventCancellable {
  public float Strafe;
  
  public float Vertical;
  
  public float Forward;
  
  public EventPlayerTravel(float paramFloat1, float paramFloat2, float paramFloat3) {
    this.Strafe = paramFloat1;
    this.Vertical = paramFloat2;
    this.Forward = paramFloat3;
  }
}
