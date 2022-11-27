package me.obsidianbreaker.leux.mixins;

import java.util.List;
import me.obsidianbreaker.leux.client.util.TabUtil;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({GuiPlayerTabOverlay.class})
public class MixinGuiPlayerTabOverlay {
  @Redirect(method = {"renderPlayerlist"}, at = @At(value = "INVOKE", target = "Ljava/util/List;subList(II)Ljava/util/List;"))
  @Redirect(method = {"renderPlayerlist"}, at = @At(value = "INVOKE", target = "Ljava/util/List;subList(II)Ljava/util/List;"))
  public List subListHook(List paramList, int paramInt1, int paramInt2) {
    return (255 > paramList.size()) ? paramList.subList(paramInt1, paramList.size()) : paramList.subList(paramInt1, 255);
  }
  
  @Inject(method = {"getPlayerName"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"getPlayerName"}, at = {@At("HEAD")}, cancellable = true)
  public void getPlayerNameHook(NetworkPlayerInfo paramNetworkPlayerInfo, CallbackInfoReturnable paramCallbackInfoReturnable) {
    paramCallbackInfoReturnable.setReturnValue(TabUtil.get_player_name(paramNetworkPlayerInfo));
  }
}
