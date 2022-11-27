package me.obsidianbreaker.leux.client.modules.misc;

import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.SnappleFacts;

public class FactSpammer extends Module {
  private long starttime = 0L;
  
  Setting delay = create("Delay", "Delay", 7.0D, 2.0D, 60.0D);
  
  public FactSpammer() {
    super(Category.misc);
  }
  
  public void enable() {
    this.starttime = System.currentTimeMillis();
  }
  
  public void update() {
    if (System.currentTimeMillis() - this.starttime >= this.delay.get_value(0) * 1000L) {
      mc.field_71439_g.func_71165_d(SnappleFacts.randomfact());
      this.starttime = System.currentTimeMillis();
    } 
  }
}
