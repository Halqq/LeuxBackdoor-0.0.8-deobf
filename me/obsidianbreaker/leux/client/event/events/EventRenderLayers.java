package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class EventRenderLayers extends EventCancellable {
  public LayerRenderer layerRenderer;
  
  public EntityLivingBase entityLivingBase;
  
  public float HeadPitch;
  
  public LayerRenderer getLayerRenderer() {
    (give up)null;
    return this.layerRenderer;
  }
  
  public EntityLivingBase getEntityLivingBase() {
    (give up)null;
    return this.entityLivingBase;
  }
  
  public void SetHeadPitch(float paramFloat) {
    (give up)null;
    this.HeadPitch = paramFloat;
  }
  
  public float GetHeadPitch() {
    (give up)null;
    return this.HeadPitch;
  }
  
  public EventRenderLayers(EntityLivingBase paramEntityLivingBase, LayerRenderer paramLayerRenderer, float paramFloat) {
    this.entityLivingBase = paramEntityLivingBase;
    this.layerRenderer = paramLayerRenderer;
    this.HeadPitch = paramFloat;
  }
}
