package me.obsidianbreaker.leux.client.modules.player;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.server.SPacketPlayerPosLook;

public class NoForceLook extends Module {
  @EventHandler
  public Listener receiveListener = new Listener(NoForceLook::lambda$new$0, new java.util.function.Predicate[0]);
  
  public NoForceLook() {
    super(Category.player);
  }
  
  public static void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (paramReceivePacket.get_packet() instanceof SPacketPlayerPosLook) {
      SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)paramReceivePacket.get_packet();
      sPacketPlayerPosLook.field_148936_d = mc.field_71439_g.field_70177_z;
      sPacketPlayerPosLook.field_148937_e = mc.field_71439_g.field_70125_A;
    } 
  }
}
