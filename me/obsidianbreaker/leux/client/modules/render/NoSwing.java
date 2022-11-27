package me.obsidianbreaker.leux.client.modules.render;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.event.events.EventSwing;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.util.EnumHand;

public class NoSwing extends Module {
  public Setting mode = create("Mode", "NoSwingMode", "Full", combobox(new String[] { "Full", "Packet", "Offhand" }));
  
  @EventHandler
  public Listener listener_ = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  @EventHandler
  public Listener listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public void lambda$new$1(EventSwing paramEventSwing) {
    (give up)null;
    if (this.mode.in("Full")) {
      paramEventSwing.cancel();
    } else if (this.mode.in("Offhand")) {
      paramEventSwing.hand = EnumHand.OFF_HAND;
    } 
  }
  
  public void lambda$new$0(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketAnimation && this.mode.in("Packet"))
      paramSendPacket.cancel(); 
  }
  
  public NoSwing() {
    super(Category.render);
  }
}
