package me.obsidianbreaker.leux.client.util;

import give up;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class OnlineFriends {
  public static List entities = new ArrayList();
  
  public static List getFriends() {
    (give up)null;
    entities.clear();
    entities.addAll((Collection)(Minecraft.func_71410_x()).field_71441_e.field_73010_i.stream().filter(OnlineFriends::lambda$getFriends$0).collect(Collectors.toList()));
    return entities;
  }
  
  public static boolean lambda$getFriends$0(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return FriendUtil.isFriend(paramEntityPlayer.func_70005_c_());
  }
}
