package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraftforge.client.event.InputUpdateEvent;

public class NoSlow extends Module {
  @EventHandler
  public Listener eventListener = new Listener(NoSlow::lambda$new$0, new java.util.function.Predicate[0]);
  
  public NoSlow() {
    super(Category.movement);
  }
  
  public static void lambda$new$0(InputUpdateEvent paramInputUpdateEvent) {
    (give up)null;
    if (mc.field_71439_g.func_184587_cr() && !mc.field_71439_g.func_184218_aH()) {
      (paramInputUpdateEvent.getMovementInput()).field_78902_a *= 5.0F;
      (paramInputUpdateEvent.getMovementInput()).field_192832_b *= 5.0F;
    } 
  }
}
