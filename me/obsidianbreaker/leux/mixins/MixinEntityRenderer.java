package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventSetupFog;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public class MixinEntityRenderer {
  @Redirect(method = {"orientCamera"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"))
  @Redirect(method = {"orientCamera"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;"))
  public RayTraceResult orientCamera(WorldClient paramWorldClient, Vec3d paramVec3d1, Vec3d paramVec3d2) {
    return (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "CameraClip").get_value(true)) ? null : paramWorldClient.func_72933_a(paramVec3d1, paramVec3d2);
  }
  
  @Inject(method = {"setupFog"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"setupFog"}, at = {@At("HEAD")}, cancellable = true)
  public void setupFog(int paramInt, float paramFloat, CallbackInfo paramCallbackInfo) {
    EventSetupFog eventSetupFog = new EventSetupFog(paramInt, paramFloat);
    EventClientBus.EVENT_BUS.post(eventSetupFog);
    if (eventSetupFog.isCancelled())
      return; 
  }
  
  @Inject(method = {"hurtCameraEffect"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"hurtCameraEffect"}, at = {@At("HEAD")}, cancellable = true)
  private void hurtCameraEffect(float paramFloat, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "NoHurtCam").get_value(true))
      paramCallbackInfo.cancel(); 
  }
}
