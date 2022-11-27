package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.network.Packet;

public class EventPacket extends EventCancellable {
  public Packet packet;
  
  public Packet get_packet() {
    (give up)null;
    return this.packet;
  }
  
  public EventPacket(Packet paramPacket) {
    this.packet = paramPacket;
  }
}
