package me.obsidianbreaker.leux.mixins;

import io.netty.channel.ChannelHandlerContext;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NetworkManager.class})
public class MixinNetworkManager {
  @Inject(method = {"channelRead0"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"channelRead0"}, at = {@At("HEAD")}, cancellable = true)
  private void receive(ChannelHandlerContext paramChannelHandlerContext, Packet paramPacket, CallbackInfo paramCallbackInfo) {
    EventPacket.ReceivePacket receivePacket = new EventPacket.ReceivePacket(paramPacket);
    EventClientBus.EVENT_BUS.post(receivePacket);
    if (receivePacket.isCancelled())
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"sendPacket(Lnet/minecraft/network/Packet;)V"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"sendPacket(Lnet/minecraft/network/Packet;)V"}, at = {@At("HEAD")}, cancellable = true)
  private void send(Packet paramPacket, CallbackInfo paramCallbackInfo) {
    EventPacket.SendPacket sendPacket = new EventPacket.SendPacket(paramPacket);
    EventClientBus.EVENT_BUS.post(sendPacket);
    if (sendPacket.isCancelled())
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"exceptionCaught"}, at = {@At("HEAD")}, cancellable = true)
  @Inject(method = {"exceptionCaught"}, at = {@At("HEAD")}, cancellable = true)
  private void exception(ChannelHandlerContext paramChannelHandlerContext, Throwable paramThrowable, CallbackInfo paramCallbackInfo) {
    if (paramThrowable instanceof Exception)
      paramCallbackInfo.cancel(); 
  }
}
