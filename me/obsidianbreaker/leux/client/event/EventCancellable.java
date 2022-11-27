package me.obsidianbreaker.leux.client.event;

import give up;
import me.zero.alpine.fork.event.type.Cancellable;
import net.minecraft.client.Minecraft;

public class EventCancellable extends Cancellable {
  public EventCancellable$Era era_switch = EventCancellable$Era.EVENT_PRE;
  
  public float partial_ticks = Minecraft.func_71410_x().func_184121_ak();
  
  public float get_partial_ticks() {
    (give up)null;
    return this.partial_ticks;
  }
  
  public EventCancellable$Era get_era() {
    (give up)null;
    return this.era_switch;
  }
}
