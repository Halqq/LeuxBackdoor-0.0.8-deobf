package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventGUIScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Minecraft.class})
public class MixinMinecraft {
  @Shadow
  public EntityPlayerSP field_71439_g;
  
  @Shadow
  public PlayerControllerMP field_71442_b;
  
  @Inject(method = {"displayGuiScreen"}, at = {@At("HEAD")})
  @Inject(method = {"displayGuiScreen"}, at = {@At("HEAD")})
  private void displayGuiScreen(GuiScreen paramGuiScreen, CallbackInfo paramCallbackInfo) {
    EventGUIScreen eventGUIScreen = new EventGUIScreen(paramGuiScreen);
    EventClientBus.EVENT_BUS.post(eventGUIScreen);
  }
  
  @Inject(method = {"shutdown"}, at = {@At("HEAD")})
  @Inject(method = {"shutdown"}, at = {@At("HEAD")})
  private void shutdown(CallbackInfo paramCallbackInfo) {
    Leux.get_config_manager().save_settings();
  }
  
  @Redirect(method = {"sendClickBlockToController"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"))
  @Redirect(method = {"sendClickBlockToController"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"))
  private boolean isHandActive(EntityPlayerSP paramEntityPlayerSP) {
    return (!Leux.get_hack_manager().get_module_with_tag("MultiTask").is_active() && this.field_71439_g.func_184587_cr());
  }
  
  @Redirect(method = {"rightClickMouse"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z"))
  @Redirect(method = {"rightClickMouse"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z"))
  private boolean isHittingBlock(PlayerControllerMP paramPlayerControllerMP) {
    return (!Leux.get_hack_manager().get_module_with_tag("MultiTask").is_active() && this.field_71442_b.func_181040_m());
  }
}
