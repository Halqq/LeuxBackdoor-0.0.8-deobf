package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.client.gui.ScaledResolution;

public class EventGameOverlay extends EventCancellable {
  public float partial_ticks;
  
  public ScaledResolution scaled_resolution;
  
  public EventGameOverlay(float paramFloat, ScaledResolution paramScaledResolution) {
    this.partial_ticks = paramFloat;
    this.scaled_resolution = paramScaledResolution;
  }
  
  public ScaledResolution get_scaled_resoltion() {
    (give up)null;
    return this.scaled_resolution;
  }
}
