package me.obsidianbreaker.leux.client.util;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;

public class TabUtil {
  public static String get_player_name(NetworkPlayerInfo paramNetworkPlayerInfo) {
    (give up)null;
    String str = (paramNetworkPlayerInfo.func_178854_k() != null) ? paramNetworkPlayerInfo.func_178854_k().func_150254_d() : ScorePlayerTeam.func_96667_a((Team)paramNetworkPlayerInfo.func_178850_i(), paramNetworkPlayerInfo.func_178845_a().getName());
    if (Leux.get_module_manager().get_module_with_tag("Settings").is_active() && Leux.get_setting_manager().get_setting_with_tag("Settings", "TabColor").get_value(true)) {
      if (FriendUtil.isFriend(str))
        return "§9" + str; 
      if (EnemyUtil.isEnemy(str))
        return "§c" + str; 
    } 
    return str;
  }
}
