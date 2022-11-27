package me.obsidianbreaker.leux.client.event.events;

import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class EventRenderEntityModel extends EventCancellable {
  public float headPitch;
  
  public ModelBase modelBase;
  
  public float limbSwingAmount;
  
  public Entity entity;
  
  public float scale;
  
  public float age;
  
  public float limbSwing;
  
  public int stage;
  
  public float headYaw;
  
  public EventRenderEntityModel(int paramInt, ModelBase paramModelBase, Entity paramEntity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    this.stage = paramInt;
    this.modelBase = paramModelBase;
    this.entity = paramEntity;
    this.limbSwing = paramFloat1;
    this.limbSwingAmount = paramFloat2;
    this.age = paramFloat3;
    this.headYaw = paramFloat4;
    this.headPitch = paramFloat5;
    this.scale = paramFloat6;
  }
}
