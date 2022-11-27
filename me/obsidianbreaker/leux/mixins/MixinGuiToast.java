package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.toasts.GuiToast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiToast.class})
public class MixinGuiToast {
  @Inject(method = {"drawToast"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"drawToast"}, at = {@At("HEAD")}, cancellable = true)
  public void drawToastHook(ScaledResolution paramScaledResolution, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("NoRender").is_active() && Leux.get_setting_manager().get_setting_with_tag("NoRender", "Advancements").get_value(true))
      paramCallbackInfo.cancel(); 
  }
}
