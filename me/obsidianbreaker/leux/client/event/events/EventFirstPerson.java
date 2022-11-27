package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.util.EnumHandSide;

public class EventFirstPerson extends EventCancellable {
  public EnumHandSide handSide;
  
  public EnumHandSide getHandSide() {
    (give up)null;
    return this.handSide;
  }
  
  public EventFirstPerson(EnumHandSide paramEnumHandSide) {
    this.handSide = paramEnumHandSide;
  }
}
