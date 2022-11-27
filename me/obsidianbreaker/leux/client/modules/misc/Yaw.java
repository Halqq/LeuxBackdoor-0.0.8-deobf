package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;

public class Yaw extends Module {
  public Setting yaw = create("Pitch", "Pitch", 180, 0, 360);
  
  public Yaw() {
    super(Category.movement);
  }
  
  public void update() {
    (give up)null;
    mc.field_71439_g.field_70177_z = this.yaw.get_value(1);
  }
}
