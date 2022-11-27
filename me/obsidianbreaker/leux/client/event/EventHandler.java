package me.obsidianbreaker.leux.client.event;

import give up;
import java.util.Arrays;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.util.math.MathHelper;

public class EventHandler implements Listenable {
  public long last_update_tick;
  
  public int next_index = 0;
  
  @me.zero.alpine.fork.listener.EventHandler
  public Listener receive_event_packet = new Listener(EventHandler::lambda$new$0, new java.util.function.Predicate[0]);
  
  public static float[] ticks = new float[20];
  
  public static EventHandler INSTANCE;
  
  public float get_tick_rate() {
    (give up)null;
    float f1 = 0.0F;
    float f2 = 0.0F;
    for (float f : ticks) {
      if (f > 0.0F) {
        f2 += f;
        f1++;
      } 
    } 
    return MathHelper.func_76131_a(f2 / f1, 0.0F, 20.0F);
  }
  
  public static void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (paramReceivePacket.get_packet() instanceof net.minecraft.network.play.server.SPacketTimeUpdate)
      INSTANCE.update_time(); 
  }
  
  public void update_time() {
    (give up)null;
    if (this.last_update_tick != -1L) {
      float f = (float)(System.currentTimeMillis() - this.last_update_tick) / 1000.0F;
      ticks[this.next_index % ticks.length] = MathHelper.func_76131_a(20.0F / f, 0.0F, 20.0F);
      this.next_index++;
    } 
    this.last_update_tick = System.currentTimeMillis();
  }
  
  public EventHandler() {
    EventClientBus.EVENT_BUS.subscribe(this);
    reset_tick();
  }
  
  public void reset_tick() {
    (give up)null;
    this.next_index = 0;
    this.last_update_tick = -1L;
    Arrays.fill(ticks, 0.0F);
  }
}
