package me.obsidianbreaker.leux.client.modules.render;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public class AlwaysNight extends Module {
  @EventHandler
  public Listener recieve_packet = new Listener(AlwaysNight::lambda$new$1, new java.util.function.Predicate[0]);
  
  @EventHandler
  public Listener on_render = new Listener(AlwaysNight::lambda$new$0, new java.util.function.Predicate[0]);
  
  public static void lambda$new$1(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (paramReceivePacket.get_packet() instanceof net.minecraft.network.play.server.SPacketTimeUpdate)
      paramReceivePacket.cancel(); 
  }
  
  public AlwaysNight() {
    super(Category.render);
  }
  
  public static void lambda$new$0(EventRender paramEventRender) {
    (give up)null;
    if (mc.field_71441_e == null)
      return; 
    mc.field_71441_e.func_72877_b(18000L);
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71441_e == null)
      return; 
    mc.field_71441_e.func_72877_b(18000L);
  }
}
