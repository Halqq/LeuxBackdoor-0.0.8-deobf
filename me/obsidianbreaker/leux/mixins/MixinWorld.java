package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventEntityRemoved;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({World.class})
public class MixinWorld {
  @Inject(method = {"onEntityRemoved"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"onEntityRemoved"}, at = {@At("HEAD")}, cancellable = true)
  public void onEntityRemoved(Entity paramEntity, CallbackInfo paramCallbackInfo) {
    EventEntityRemoved eventEntityRemoved = new EventEntityRemoved(paramEntity);
    EventClientBus.EVENT_BUS.post(eventEntityRemoved);
  }
  
  @Inject(method = {"spawnEntity"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"spawnEntity"}, at = {@At("HEAD")}, cancellable = true)
  public void spawnEntity(Entity paramEntity, CallbackInfoReturnable paramCallbackInfoReturnable) {
    if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "Fireworks").get_value(true) && paramEntity instanceof net.minecraft.entity.item.EntityFireworkRocket)
      paramCallbackInfoReturnable.cancel(); 
  }
  
  @Inject(method = {"updateEntity"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"updateEntity"}, at = {@At("HEAD")}, cancellable = true)
  public void updateEntity(Entity paramEntity, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "Fireworks").get_value(true) && paramEntity instanceof net.minecraft.entity.item.EntityFireworkRocket)
      paramCallbackInfo.cancel(); 
  }
}
