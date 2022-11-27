package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventMotionUpdate;
import me.obsidianbreaker.leux.client.event.events.EventMove;
import me.obsidianbreaker.leux.client.event.events.EventSwing;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayerSP.class})
public class MixinEntitySP extends MixinEntity {
  @Inject(method = {"move"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"move"}, at = {@At("HEAD")}, cancellable = true)
  private void move(MoverType paramMoverType, double paramDouble1, double paramDouble2, double paramDouble3, CallbackInfo paramCallbackInfo) {
    EventMove eventMove = new EventMove(paramMoverType, paramDouble1, paramDouble2, paramDouble3);
    EventClientBus.EVENT_BUS.post(eventMove);
    if (eventMove.isCancelled()) {
      func_70091_d(paramMoverType, eventMove.get_x(), eventMove.get_y(), eventMove.get_z());
      paramCallbackInfo.cancel();
    } 
  }
  
  @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At("HEAD")}, cancellable = true)
  public void OnPreUpdateWalkingPlayer(CallbackInfo paramCallbackInfo) {
    EventMotionUpdate eventMotionUpdate = new EventMotionUpdate(0);
    EventClientBus.EVENT_BUS.post(eventMotionUpdate);
    if (eventMotionUpdate.isCancelled())
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At("RETURN")}, cancellable = true)
  @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At("RETURN")}, cancellable = true)
  public void OnPostUpdateWalkingPlayer(CallbackInfo paramCallbackInfo) {
    EventMotionUpdate eventMotionUpdate = new EventMotionUpdate(1);
    EventClientBus.EVENT_BUS.post(eventMotionUpdate);
    if (eventMotionUpdate.isCancelled())
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"swingArm"}, at = {@At("RETURN")}, cancellable = true)
  @Inject(method = {"swingArm"}, at = {@At("RETURN")}, cancellable = true)
  public void swingArm(EnumHand paramEnumHand, CallbackInfo paramCallbackInfo) {
    EventSwing eventSwing = new EventSwing(paramEnumHand);
    EventClientBus.EVENT_BUS.post(eventSwing);
    if (eventSwing.isCancelled())
      paramCallbackInfo.cancel(); 
  }
}
