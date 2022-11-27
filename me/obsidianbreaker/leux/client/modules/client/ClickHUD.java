package me.obsidianbreaker.leux.client.modules.client;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.client.gui.GuiScreen;

public class ClickHUD extends Module {
  public Setting b = create("Color B", "ColorB", 200, 0, 255);
  
  public Setting max_notifications = create("Max notifications", "MaxNotifications", 3, 1, 10);
  
  public Setting compass_scale = create("Compass Scale", "HUDCompassScale", 16, 1, 60);
  
  public Setting a = create("Color A", "ColorA", 255, 0, 255);
  
  public Setting g = create("Color G", "ColorG", 80, 0, 255);
  
  public Setting max_player_list = create("Max Players", "HUDMaxPlayers", 10, 1, 64);
  
  public Setting notification_armor = create("Armor Notifications", "NotificationArmor", true);
  
  public Setting r = create("Color R", "ColorR", 80, 0, 255);
  
  public Setting show_all_pots = create("All Potions", "HUDAllPotions", true);
  
  public Setting notification_enable = create("Notify on Enable", "NotificationEnable", true);
  
  public Setting arraylist_mode = create("ArrayList", "HUDArrayList", "Top R", combobox(new String[] { "Free", "Top R", "Top L", "Bottom R", "Bottom L" }));
  
  public Setting notification_pop = create("Notify on Totem", "NotificationTotem", true);
  
  public void enable() {
    (give up)null;
    if (mc.field_71441_e != null && mc.field_71439_g != null) {
      Leux.get_hack_manager().get_module_with_tag("GUI").set_active(false);
      Leux.click_hud.back = false;
      mc.func_147108_a((GuiScreen)Leux.click_hud);
    } 
  }
  
  public ClickHUD() {
    super(Category.client);
  }
}
