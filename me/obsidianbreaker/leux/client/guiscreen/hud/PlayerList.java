package me.obsidianbreaker.leux.client.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerList extends Pinnable {
  public DecimalFormat df_health = new DecimalFormat("#.#");
  
  public PlayerList() {
    super("Player List", "PlayerList", Float.intBitsToFloat(1.08838861E9F ^ 0x7F5F7E22), 0, 0);
  }
  
  public void render() {
    (give up)null;
    byte b1 = 12;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    this.df_health.setRoundingMode(RoundingMode.HALF_UP);
    List list = this.mc.field_71441_e.field_73010_i;
    HashMap<Object, Object> hashMap = new HashMap<>();
    for (EntityPlayer entityPlayer : list) {
      StringBuilder stringBuilder = new StringBuilder();
      if (entityPlayer == this.mc.field_71439_g)
        continue; 
      String str1 = (entityPlayer.field_70163_u > this.mc.field_71439_g.field_70163_u) ? (ChatFormatting.DARK_GREEN + "+") : ((entityPlayer.field_70163_u == this.mc.field_71439_g.field_70163_u) ? " " : (ChatFormatting.DARK_RED + "-"));
      float f = entityPlayer.func_110143_aJ() + entityPlayer.func_110139_bj();
      String str2 = this.df_health.format(f);
      stringBuilder.append('§');
      if (f >= Float.intBitsToFloat(1.00858304E9F ^ 0x7DBDC16F)) {
        stringBuilder.append("a");
      } else if (f >= Float.intBitsToFloat(1.04687315E9F ^ 0x7F460454)) {
        stringBuilder.append("e");
      } else if (f >= Float.intBitsToFloat(1.04371891E9F ^ 0x7E95E30D)) {
        stringBuilder.append("6");
      } else {
        stringBuilder.append("c");
      } 
      stringBuilder.append(str2);
      hashMap.put(str1 + " " + stringBuilder.toString() + " " + (FriendUtil.isFriend(entityPlayer.func_70005_c_()) ? ChatFormatting.GREEN : ChatFormatting.RESET) + entityPlayer.func_70005_c_(), Integer.valueOf((int)this.mc.field_71439_g.func_70032_d((Entity)entityPlayer)));
    } 
    if (hashMap.isEmpty())
      return; 
    Map map = sortByValue(hashMap);
    int n = Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDMaxPlayers").get_value(1);
    byte b2 = 0;
    for (Map.Entry entry : map.entrySet()) {
      if (n < b2)
        return; 
      b2++;
      b1 += 12;
      String str = (String)entry.getKey() + " " + entry.getValue();
      create_line(str, docking(1, str), b1, i, j, k, m);
    } 
    set_width(24);
    set_height(b1 + 2);
  }
  
  public static Map sortByValue(Map paramMap) {
    (give up)null;
    LinkedList linkedList = new LinkedList(paramMap.entrySet());
    linkedList.sort(Map.Entry.comparingByValue());
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
    for (Map.Entry entry : linkedList)
      linkedHashMap.put(entry.getKey(), entry.getValue()); 
    return linkedHashMap;
  }
}
