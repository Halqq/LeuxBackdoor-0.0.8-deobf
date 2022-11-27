package me.obsidianbreaker.leux.client.manager;

import give up;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import me.obsidianbreaker.leux.client.guiscreen.hud.ArmorDurabilityWarner;
import me.obsidianbreaker.leux.client.guiscreen.hud.ArmorPreview;
import me.obsidianbreaker.leux.client.guiscreen.hud.Compass;
import me.obsidianbreaker.leux.client.guiscreen.hud.Coordinates;
import me.obsidianbreaker.leux.client.guiscreen.hud.CrystalCount;
import me.obsidianbreaker.leux.client.guiscreen.hud.Direction;
import me.obsidianbreaker.leux.client.guiscreen.hud.EXPCount;
import me.obsidianbreaker.leux.client.guiscreen.hud.EffectHud;
import me.obsidianbreaker.leux.client.guiscreen.hud.EnemyInfo;
import me.obsidianbreaker.leux.client.guiscreen.hud.FPS;
import me.obsidianbreaker.leux.client.guiscreen.hud.FriendList;
import me.obsidianbreaker.leux.client.guiscreen.hud.GappleCount;
import me.obsidianbreaker.leux.client.guiscreen.hud.InventoryPreview;
import me.obsidianbreaker.leux.client.guiscreen.hud.InventoryXCarryPreview;
import me.obsidianbreaker.leux.client.guiscreen.hud.ModulesArrayList;
import me.obsidianbreaker.leux.client.guiscreen.hud.Notifications;
import me.obsidianbreaker.leux.client.guiscreen.hud.Ping;
import me.obsidianbreaker.leux.client.guiscreen.hud.PlayerList;
import me.obsidianbreaker.leux.client.guiscreen.hud.PlayerModel;
import me.obsidianbreaker.leux.client.guiscreen.hud.PvpHud;
import me.obsidianbreaker.leux.client.guiscreen.hud.Speedometer;
import me.obsidianbreaker.leux.client.guiscreen.hud.SurroundBlocks;
import me.obsidianbreaker.leux.client.guiscreen.hud.TPS;
import me.obsidianbreaker.leux.client.guiscreen.hud.Time;
import me.obsidianbreaker.leux.client.guiscreen.hud.TotemCount;
import me.obsidianbreaker.leux.client.guiscreen.hud.User;
import me.obsidianbreaker.leux.client.guiscreen.hud.Watermark;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;

public class HUDManager {
  public static ArrayList array_hud = new ArrayList();
  
  public Pinnable get_pinnable_with_tag(String paramString) {
    (give up)null;
    Pinnable pinnable = null;
    for (Pinnable pinnable1 : get_array_huds()) {
      if (pinnable1.get_tag().equalsIgnoreCase(paramString))
        pinnable = pinnable1; 
    } 
    return pinnable;
  }
  
  public ArrayList get_array_huds() {
    (give up)null;
    return array_hud;
  }
  
  public HUDManager() {
    add_component_pinnable((Pinnable)new Watermark());
    add_component_pinnable((Pinnable)new ModulesArrayList());
    add_component_pinnable((Pinnable)new Coordinates());
    add_component_pinnable((Pinnable)new InventoryPreview());
    add_component_pinnable((Pinnable)new InventoryXCarryPreview());
    add_component_pinnable((Pinnable)new ArmorPreview());
    add_component_pinnable((Pinnable)new User());
    add_component_pinnable((Pinnable)new TotemCount());
    add_component_pinnable((Pinnable)new CrystalCount());
    add_component_pinnable((Pinnable)new EXPCount());
    add_component_pinnable((Pinnable)new GappleCount());
    add_component_pinnable((Pinnable)new Time());
    add_component_pinnable((Pinnable)new FPS());
    add_component_pinnable((Pinnable)new Ping());
    add_component_pinnable((Pinnable)new SurroundBlocks());
    add_component_pinnable((Pinnable)new FriendList());
    add_component_pinnable((Pinnable)new ArmorDurabilityWarner());
    add_component_pinnable((Pinnable)new PvpHud());
    add_component_pinnable((Pinnable)new Compass());
    add_component_pinnable((Pinnable)new EffectHud());
    add_component_pinnable((Pinnable)new Speedometer());
    add_component_pinnable((Pinnable)new TPS());
    add_component_pinnable((Pinnable)new PlayerList());
    add_component_pinnable((Pinnable)new Direction());
    add_component_pinnable((Pinnable)new PlayerModel());
    add_component_pinnable((Pinnable)new EnemyInfo());
    add_component_pinnable((Pinnable)new Notifications());
    array_hud.sort(Comparator.comparing(Pinnable::get_title));
  }
  
  public void render() {
    (give up)null;
    Iterator<Pinnable> iterator = get_array_huds().iterator();
    while (true) {
      if (iterator.hasNext()) {
        Pinnable pinnable = iterator.next();
        if (pinnable.is_active())
          pinnable.render(); 
        continue;
      } 
      return;
    } 
  }
  
  public void add_component_pinnable(Pinnable paramPinnable) {
    (give up)null;
    array_hud.add(paramPinnable);
  }
}
