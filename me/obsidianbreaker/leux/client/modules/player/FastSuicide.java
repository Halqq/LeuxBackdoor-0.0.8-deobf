package me.obsidianbreaker.leux.client.modules.player;

import give up;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;

public class FastSuicide extends Module {
  public FastSuicide() {
    super(Category.player);
  }
  
  public void enable() {
    (give up)null;
    mc.field_71439_g.func_71165_d("/kill");
    set_disable();
  }
}
