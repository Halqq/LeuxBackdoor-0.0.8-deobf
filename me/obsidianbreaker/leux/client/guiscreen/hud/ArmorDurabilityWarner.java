package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import java.util.HashMap;
import java.util.Map;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.Notification;
import me.obsidianbreaker.leux.client.util.NotificationUtil;
import net.minecraft.item.ItemStack;

public class ArmorDurabilityWarner extends Pinnable {
  public boolean already_warned_leggings = false;
  
  public boolean already_warned_boots = false;
  
  public boolean already_warned_helmet = false;
  
  public boolean already_warned_chestplate = false;
  
  public ArmorDurabilityWarner() {
    super("Armor Warner", "ArmorWarner", Float.intBitsToFloat(1.0892832E9F ^ 0x7F6D24A5), 0, 0);
  }
  
  public Map get_armor() {
    (give up)null;
    return get_inv_slots(5, 8);
  }
  
  public Map get_inv_slots(int paramInt1, int paramInt2) {
    paramInt2 = 8;
    paramInt1 = 5;
    (give up)null;
    HashMap<Object, Object> hashMap = new HashMap<>();
    while (paramInt1 <= paramInt2) {
      hashMap.put(Integer.valueOf(paramInt1), this.mc.field_71439_g.field_71069_bz.func_75138_a().get(paramInt1));
      paramInt1++;
    } 
    return hashMap;
  }
  
  public void render() {
    (give up)null;
    String str = "See ur armor :p";
    if (is_damaged())
      create_line(str, docking(1, str), 2, 255, 20, 20, 255); 
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public boolean is_damaged() {
    (give up)null;
    for (Map.Entry entry : get_armor().entrySet()) {
      if (((ItemStack)entry.getValue()).func_190926_b())
        continue; 
      ItemStack itemStack = (ItemStack)entry.getValue();
      double d1 = itemStack.func_77958_k();
      double d2 = (itemStack.func_77958_k() - itemStack.func_77952_i());
      double d3 = d2 / d1 * 100.0D;
      String str = "";
      if (this.mc.field_71439_g.field_71069_bz.func_75138_a().get(5) == itemStack) {
        str = str + "Your helmet is at ";
        if (d3 < 30.0D && !this.already_warned_helmet) {
          if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true))
            NotificationUtil.send_notification(new Notification(str + (int)d3 + "%", 214, 38, 26)); 
          this.already_warned_helmet = true;
        } else if (d3 > 30.0D) {
          this.already_warned_helmet = false;
        } 
      } else if (this.mc.field_71439_g.field_71069_bz.func_75138_a().get(6) == itemStack) {
        str = str + "Your chestplate is at ";
        if (d3 < 40.0D && !this.already_warned_chestplate) {
          if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true))
            NotificationUtil.send_notification(new Notification(str + (int)d3 + "%", 214, 38, 26)); 
          this.already_warned_chestplate = true;
        } else if (d3 > 40.0D) {
          this.already_warned_chestplate = false;
        } 
      } else if (this.mc.field_71439_g.field_71069_bz.func_75138_a().get(7) == itemStack) {
        str = str + "Your leggings are at ";
        if (d3 < 40.0D && !this.already_warned_leggings) {
          if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true))
            NotificationUtil.send_notification(new Notification(str + (int)d3 + "%", 214, 38, 26)); 
          this.already_warned_leggings = true;
        } else if (d3 > 40.0D) {
          this.already_warned_leggings = false;
        } 
      } else if (this.mc.field_71439_g.field_71069_bz.func_75138_a().get(8) == itemStack) {
        str = str + "Your boots are at ";
        if (d3 < 30.0D && !this.already_warned_boots) {
          if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationArmor").get_value(true))
            NotificationUtil.send_notification(new Notification(str + (int)d3 + "%", 214, 38, 26)); 
          this.already_warned_boots = true;
        } else if (d3 > 30.0D) {
          this.already_warned_boots = false;
        } 
      } 
      if (d3 < 30.0D)
        return true; 
    } 
    return false;
  }
}
