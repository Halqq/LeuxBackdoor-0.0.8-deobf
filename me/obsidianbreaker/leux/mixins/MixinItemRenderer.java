package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventFirstPerson;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemRenderer.class})
public abstract class MixinItemRenderer {
  private boolean injection = true;
  
  @Shadow
  @Shadow
  public abstract void func_187457_a(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, EnumHand paramEnumHand, float paramFloat3, ItemStack paramItemStack, float paramFloat4);
  
  @Inject(method = {"transformSideFirstPerson"}, at = {@At("HEAD")})
  @Inject(method = {"transformSideFirstPerson"}, at = {@At("HEAD")})
  public void transformSideFirstPerson(EnumHandSide paramEnumHandSide, float paramFloat, CallbackInfo paramCallbackInfo) {
    EventFirstPerson eventFirstPerson = new EventFirstPerson(paramEnumHandSide);
    EventClientBus.EVENT_BUS.post(eventFirstPerson);
  }
  
  @Inject(method = {"transformEatFirstPerson"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"transformEatFirstPerson"}, at = {@At("HEAD")}, cancellable = true)
  public void transformEatFirstPerson(float paramFloat, EnumHandSide paramEnumHandSide, ItemStack paramItemStack, CallbackInfo paramCallbackInfo) {
    EventFirstPerson eventFirstPerson = new EventFirstPerson(paramEnumHandSide);
    EventClientBus.EVENT_BUS.post(eventFirstPerson);
    if (Leux.get_hack_manager().get_module_with_tag("ViewModel").is_active() && Leux.get_setting_manager().get_setting_with_tag("ViewModel", "NoEat").get_value(true))
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"transformFirstPerson"}, at = {@At("HEAD")})
  @Inject(method = {"transformFirstPerson"}, at = {@At("HEAD")})
  public void transformFirstPerson(EnumHandSide paramEnumHandSide, float paramFloat, CallbackInfo paramCallbackInfo) {
    EventFirstPerson eventFirstPerson = new EventFirstPerson(paramEnumHandSide);
    EventClientBus.EVENT_BUS.post(eventFirstPerson);
  }
}
