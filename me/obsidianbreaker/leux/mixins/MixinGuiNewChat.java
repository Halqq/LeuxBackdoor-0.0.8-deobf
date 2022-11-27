package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({GuiNewChat.class})
public class MixinGuiNewChat {
  @Redirect(method = {"drawChat"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
  @Redirect(method = {"drawChat"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
  private void overrideChatBackgroundColour(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (!Leux.get_hack_manager().get_module_with_tag("ClearChatbox").is_active())
      Gui.func_73734_a(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5); 
  }
}
