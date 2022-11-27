package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventRenderArmorOverlay;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LayerArmorBase.class})
public class MixinLayerArmorBase {
  @Inject(method = {"renderArmorLayer"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"renderArmorLayer"}, at = {@At("HEAD")}, cancellable = true)
  private void renderArmorLayer(EntityLivingBase paramEntityLivingBase, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, EntityEquipmentSlot paramEntityEquipmentSlot, CallbackInfo paramCallbackInfo) {
    EventRenderArmorOverlay eventRenderArmorOverlay = new EventRenderArmorOverlay(paramEntityLivingBase);
    EventClientBus.EVENT_BUS.post(eventRenderArmorOverlay);
    if (eventRenderArmorOverlay.isCancelled())
      paramCallbackInfo.cancel(); 
  }
}
