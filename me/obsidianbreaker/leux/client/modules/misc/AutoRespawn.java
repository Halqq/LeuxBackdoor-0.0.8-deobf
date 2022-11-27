package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventGUIScreen;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public class AutoRespawn extends Module {
  @EventHandler
  public Listener listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting coords = create("DeathCoords", "DeathCoords", true);
  
  public void disable() {
    (give up)null;
    EventClientBus.EVENT_BUS.unsubscribe((Listenable)this);
  }
  
  public void enable() {
    (give up)null;
    EventClientBus.EVENT_BUS.subscribe((Listenable)this);
  }
  
  public void lambda$new$0(EventGUIScreen paramEventGUIScreen) {
    (give up)null;
    if (paramEventGUIScreen.get_guiscreen() instanceof net.minecraft.client.gui.GuiGameOver) {
      if (this.coords.get_value(true))
        MessageUtil.send_client_message(String.format("You died at x%d y%d z%d", new Object[] { Integer.valueOf((int)mc.field_71439_g.field_70165_t), Integer.valueOf((int)mc.field_71439_g.field_70163_u), Integer.valueOf((int)mc.field_71439_g.field_70161_v) })); 
      if (mc.field_71439_g != null)
        mc.field_71439_g.func_71004_bE(); 
      mc.func_147108_a(null);
      mc.field_71439_g.field_70145_X = true;
    } 
  }
  
  public AutoRespawn() {
    super(Category.misc);
  }
}
