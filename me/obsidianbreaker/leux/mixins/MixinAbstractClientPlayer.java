package me.obsidianbreaker.leux.mixins;

import javax.annotation.Nullable;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.util.CapeUtil;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({AbstractClientPlayer.class})
public abstract class MixinAbstractClientPlayer {
  static final boolean $assertionsDisabled;
  
  @Shadow
  @Nullable
  @Shadow
  @Nullable
  protected abstract NetworkPlayerInfo func_175155_b();
  
  @Inject(method = {"getLocationCape"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"getLocationCape"}, at = {@At("HEAD")}, cancellable = true)
  public void getLocationCape(CallbackInfoReturnable paramCallbackInfoReturnable) {
    if (Leux.get_hack_manager().get_module_with_tag("Capes").is_active()) {
      ResourceLocation resourceLocation;
      NetworkPlayerInfo networkPlayerInfo = func_175155_b();
      assert false;
      if (!CapeUtil.is_uuid_valid(networkPlayerInfo.func_178845_a().getId()))
        return; 
      if (Leux.get_setting_manager().get_setting_with_tag("Capes", "CapeCape").in("Obsidian")) {
        resourceLocation = new ResourceLocation("custom/cape-obsidian.png");
      } else if (Leux.get_setting_manager().get_setting_with_tag("Capes", "CapeCape").in("LeuxNew")) {
        resourceLocation = new ResourceLocation("custom/cape-leuxnew.png");
      } else {
        resourceLocation = new ResourceLocation("custom/cape-leuxold.png");
      } 
      paramCallbackInfoReturnable.setReturnValue(resourceLocation);
    } 
  }
}
