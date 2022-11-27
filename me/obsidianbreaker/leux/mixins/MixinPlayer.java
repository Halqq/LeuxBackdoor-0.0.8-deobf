package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventPlayerTravel;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayer.class})
public class MixinPlayer extends MixinEntity {
  @Inject(method = {"travel"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"travel"}, at = {@At("HEAD")}, cancellable = true)
  public void travel(float paramFloat1, float paramFloat2, float paramFloat3, CallbackInfo paramCallbackInfo) {
    EventPlayerTravel eventPlayerTravel = new EventPlayerTravel(paramFloat1, paramFloat2, paramFloat3);
    EventClientBus.EVENT_BUS.post(eventPlayerTravel);
    if (eventPlayerTravel.isCancelled()) {
      func_70091_d(MoverType.SELF, this.field_70159_w, this.field_70181_x, this.field_70179_y);
      paramCallbackInfo.cancel();
    } 
  }
}
