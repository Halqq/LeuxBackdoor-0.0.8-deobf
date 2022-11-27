package me.obsidianbreaker.leux.client.modules.player;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;

public class Timer extends Module {
  public Setting speed = create("Speed", "Speed", 1.2000000476837158D, 1.0D, 3.0D);
  
  public int tick = 0;
  
  public void disable() {
    (give up)null;
    mc.field_71428_T.field_194149_e = 50.0F;
  }
  
  public String array_detail() {
    (give up)null;
    return "" + this.speed.get_value(1.0D);
  }
  
  public void update() {
    (give up)null;
    mc.field_71428_T.field_194149_e = (float)(50.0D / this.speed.get_value(1.0D));
  }
  
  public void enable() {
    (give up)null;
    this.tick = 0;
  }
  
  public Timer() {
    super(Category.player);
  }
}
