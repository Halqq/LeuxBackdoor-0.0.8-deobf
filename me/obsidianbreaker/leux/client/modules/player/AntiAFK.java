package me.obsidianbreaker.leux.client.modules.player;

import give up;
import java.util.Random;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class AntiAFK extends Module {
  public int afk_delay;
  
  public Setting announce = create("Announce", "AntiAFKAnnounce", true);
  
  public static String lastcode;
  
  public Setting delay = create("Delay", "AntiAFKDelay", 20, 20, 240);
  
  @EventHandler
  public Listener listener = new Listener(AntiAFK::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting swing = create("Hand", "AntiAFKHand", true);
  
  public Setting jump = create("Jump", "AntiAFKJump", true);
  
  public void disable() {
    (give up)null;
    EventClientBus.EVENT_BUS.unsubscribe((Listenable)this);
  }
  
  public static void lambda$new$0(ClientChatReceivedEvent paramClientChatReceivedEvent) {
    (give up)null;
    if (paramClientChatReceivedEvent.getMessage().func_150260_c().contains(lastcode))
      paramClientChatReceivedEvent.setCanceled(true); 
  }
  
  public void enable() {
    (give up)null;
    this.afk_delay = 0;
    EventClientBus.EVENT_BUS.subscribe((Listenable)this);
  }
  
  public String getRandomHexString(int paramInt) {
    paramInt = 8;
    (give up)null;
    Random random = new Random();
    StringBuilder stringBuilder = new StringBuilder();
    while (stringBuilder.length() < paramInt)
      stringBuilder.append(Integer.toHexString(random.nextInt())); 
    return stringBuilder.toString().substring(0, paramInt);
  }
  
  public void update() {
    (give up)null;
    this.afk_delay++;
    if (this.afk_delay < this.delay.get_value(1) * 10)
      return; 
    lastcode = getRandomHexString(8);
    if (this.announce.get_value(true))
      mc.field_71439_g.func_71165_d("Leux AntiAFK " + lastcode); 
    if (this.jump.get_value(true))
      mc.field_71439_g.func_70664_aZ(); 
    if (this.swing.get_value(true))
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND); 
    this.afk_delay = 0;
  }
  
  public AntiAFK() {
    super(Category.player);
  }
}
