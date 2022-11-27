package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventBlock;
import me.obsidianbreaker.leux.client.event.events.EventDamageBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PlayerControllerMP.class})
public class MixinPlayerControllerMP {
  @Redirect(method = {"onPlayerDamageBlock"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
  @Redirect(method = {"onPlayerDamageBlock"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
  private float onPlayerDamageBlockSpeed(IBlockState paramIBlockState, EntityPlayer paramEntityPlayer, World paramWorld, BlockPos paramBlockPos) {
    return paramIBlockState.func_185903_a(paramEntityPlayer, paramWorld, paramBlockPos) * Leux.get_event_handler().get_tick_rate() / 20.0F;
  }
  
  @Inject(method = {"onPlayerDamageBlock"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"onPlayerDamageBlock"}, at = {@At("HEAD")}, cancellable = true)
  public void onPlayerDamageBlock(BlockPos paramBlockPos, EnumFacing paramEnumFacing, CallbackInfoReturnable paramCallbackInfoReturnable) {
    EventDamageBlock eventDamageBlock = new EventDamageBlock(paramBlockPos, paramEnumFacing);
    EventClientBus.EVENT_BUS.post(eventDamageBlock);
    if (eventDamageBlock.isCancelled()) {
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false));
      paramCallbackInfoReturnable.cancel();
    } 
    EventBlock eventBlock = new EventBlock(4, paramBlockPos, paramEnumFacing);
    EventClientBus.EVENT_BUS.post(eventBlock);
  }
  
  @Inject(method = {"clickBlock"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"clickBlock"}, at = {@At("HEAD")}, cancellable = true)
  private void clickBlockHook(BlockPos paramBlockPos, EnumFacing paramEnumFacing, CallbackInfoReturnable paramCallbackInfoReturnable) {
    EventBlock eventBlock = new EventBlock(3, paramBlockPos, paramEnumFacing);
    EventClientBus.EVENT_BUS.post(eventBlock);
  }
}
