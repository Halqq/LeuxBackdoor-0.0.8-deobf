package me.obsidianbreaker.leux.client.modules.client;

import give up;
import io.netty.buffer.Unpooled;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;

public class VanillaPayload extends Module {
  @EventHandler
  public Listener send_listener = new Listener(VanillaPayload::lambda$new$0, new java.util.function.Predicate[0]);
  
  public VanillaPayload() {
    super(Category.client);
  }
  
  public static void lambda$new$0(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (paramSendPacket.get_packet() instanceof net.minecraftforge.fml.common.network.internal.FMLProxyPacket && !mc.func_71356_B())
      paramSendPacket.cancel(); 
    if (paramSendPacket.get_packet() instanceof CPacketCustomPayload) {
      CPacketCustomPayload cPacketCustomPayload = (CPacketCustomPayload)paramSendPacket.get_packet();
      if (cPacketCustomPayload.func_149559_c().equalsIgnoreCase("MC|Brand"))
        cPacketCustomPayload.field_149561_c = (new PacketBuffer(Unpooled.buffer())).func_180714_a("vanilla"); 
    } 
  }
}
