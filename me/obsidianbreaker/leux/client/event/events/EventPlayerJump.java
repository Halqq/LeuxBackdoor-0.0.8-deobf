package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;

public class EventPlayerJump extends EventCancellable {
  public double motion_y;
  
  public double motion_x;
  
  public EventPlayerJump(double paramDouble1, double paramDouble2) {
    this.motion_x = paramDouble1;
    this.motion_y = paramDouble2;
  }
}
