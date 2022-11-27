package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiIngame.class})
public class MixinGuiIngame extends Gui {
  @Inject(method = {"renderPortal"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"renderPortal"}, at = {@At("HEAD")}, cancellable = true)
  protected void renderPortalHook(float paramFloat, ScaledResolution paramScaledResolution, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "PortalOverlay").get_value(true))
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"renderPumpkinOverlay"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"renderPumpkinOverlay"}, at = {@At("HEAD")}, cancellable = true)
  protected void renderPumpkinOverlayHook(ScaledResolution paramScaledResolution, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "Pumpkin").get_value(true))
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"renderPotionEffects"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"renderPotionEffects"}, at = {@At("HEAD")}, cancellable = true)
  protected void renderPotionEffectsHook(ScaledResolution paramScaledResolution, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "PotionIcons").get_value(true))
      paramCallbackInfo.cancel(); 
  }
}
