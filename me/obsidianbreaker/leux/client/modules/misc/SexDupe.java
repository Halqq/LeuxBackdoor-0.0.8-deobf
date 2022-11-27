package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;

public class SexDupe extends Module {
  public Setting mode = create("Mode", "Mode", "Sazked", combobox(new String[] { "Sazked", "House" }));
  
  public SexDupe() {
    super(Category.misc);
  }
  
  public void enable() {
    (give up)null;
    if (this.mode.in("Sazked"))
      mc.field_71439_g.func_71165_d("Sazked has doing sex dupe with Luscius and they got 10 shulkers per second 0//_//0"); 
    if (this.mode.in("House"))
      mc.field_71439_g.func_71165_d("I used the HouseHouseHause1 sex dupe and i got 10 shulkers per second 0//_//0"); 
    set_disable();
  }
}
