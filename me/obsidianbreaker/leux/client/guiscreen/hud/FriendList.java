package me.obsidianbreaker.leux.client.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.util.OnlineFriends;
import net.minecraft.entity.Entity;

public class FriendList extends Pinnable {
  public int passes;
  
  public static ChatFormatting bold = ChatFormatting.BOLD;
  
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str = bold + "active_gang: ";
    this.passes = 0;
    create_line(str, docking(1, str), 2, i, j, k, m);
    if (!OnlineFriends.getFriends().isEmpty())
      for (Entity entity : OnlineFriends.getFriends()) {
        this.passes++;
        create_line(entity.func_70005_c_(), docking(1, entity.func_70005_c_()), get(str, "height") * this.passes, i, j, k, m);
      }  
    set_width(get(str, "width") + 2);
    set_height(get(str, "height") + 2);
  }
  
  public FriendList() {
    super("Friends", "Friends", Float.intBitsToFloat(1.08918669E9F ^ 0x7F6BAB87), 0, 0);
  }
}
