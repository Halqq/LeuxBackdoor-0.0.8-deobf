package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.util.EnumHand;

public class EventSwing extends EventCancellable {
  public EnumHand hand;
  
  public EventSwing(EnumHand paramEnumHand) {
    this.hand = paramEnumHand;
  }
}
