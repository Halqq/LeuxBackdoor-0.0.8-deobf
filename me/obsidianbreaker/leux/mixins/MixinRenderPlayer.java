package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventRenderName;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderPlayer.class})
public class MixinRenderPlayer {
  @Inject(method = {"renderEntityName"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"renderEntityName"}, at = {@At("HEAD")}, cancellable = true)
  public void renderLivingLabel(AbstractClientPlayer paramAbstractClientPlayer, double paramDouble1, double paramDouble2, double paramDouble3, String paramString, double paramDouble4, CallbackInfo paramCallbackInfo) {
    EventRenderName eventRenderName = new EventRenderName(paramAbstractClientPlayer, paramDouble1, paramDouble2, paramDouble3, paramString, paramDouble4);
    EventClientBus.EVENT_BUS.post(eventRenderName);
    if (eventRenderName.isCancelled())
      paramCallbackInfo.cancel(); 
  }
}
