package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;

public class Sprint extends Module {
  public Setting rage = create("Rage", "SprintRage", true);
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (this.rage.get_value(true) && (mc.field_71439_g.field_191988_bg != 0.0F || mc.field_71439_g.field_70702_br != 0.0F)) {
      mc.field_71439_g.func_70031_b(true);
    } else {
      mc.field_71439_g.func_70031_b((mc.field_71439_g.field_191988_bg > 0.0F || mc.field_71439_g.field_70702_br > 0.0F));
    } 
  }
  
  public Sprint() {
    super(Category.movement);
  }
}
