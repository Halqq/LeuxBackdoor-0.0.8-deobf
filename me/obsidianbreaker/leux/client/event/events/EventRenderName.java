package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.client.entity.AbstractClientPlayer;

public class EventRenderName extends EventCancellable {
  public double X;
  
  public AbstractClientPlayer Entity;
  
  public double Y;
  
  public double Z;
  
  public String Name;
  
  public double DistanceSq;
  
  public EventRenderName(AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, String paramString, double paramDouble4) {
    this.Entity = paramAbstractClientPlayer;
    this.Name = paramString;
    this.DistanceSq = paramDouble4;
  }
}
