package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.entity.Entity;

public class EventEntityRemoved extends EventCancellable {
  public Entity entity;
  
  public EventEntityRemoved(Entity paramEntity) {
    this.entity = paramEntity;
  }
  
  public Entity get_entity() {
    (give up)null;
    return this.entity;
  }
}
