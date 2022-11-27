package me.obsidianbreaker.leux.client.modules.render;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;

public class Capes extends Module {
  public Setting cape = create("Cape", "CapeCape", "LeuxNew", combobox(new String[] { "LeuxNew", "LeuxOld", "Obsidian" }));
  
  public String array_detail() {
    (give up)null;
    return this.cape.get_current_value();
  }
  
  public Capes() {
    super(Category.render);
  }
}
