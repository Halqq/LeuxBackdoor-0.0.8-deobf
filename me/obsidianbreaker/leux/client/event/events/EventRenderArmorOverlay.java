package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.entity.EntityLivingBase;

public class EventRenderArmorOverlay extends EventCancellable {
  public EntityLivingBase entity;
  
  public EventRenderArmorOverlay(EntityLivingBase paramEntityLivingBase) {
    this.entity = paramEntityLivingBase;
  }
}
