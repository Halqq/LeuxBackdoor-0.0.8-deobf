package me.obsidianbreaker.leux.client.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.input.Mouse;

public class MiddleClickGang extends Module {
  public static ChatFormatting reset;
  
  public static ChatFormatting green;
  
  public boolean clicked = false;
  
  public static ChatFormatting bold;
  
  public static ChatFormatting red = ChatFormatting.RED;
  
  public static boolean lambda$update$0(Entity paramEntity, FriendUtil.Friend paramFriend) {
    (give up)null;
    return paramFriend.getUsername().equalsIgnoreCase(paramEntity.func_70005_c_());
  }
  
  public MiddleClickGang() {
    super(Category.misc);
  }
  
  static {
    green = ChatFormatting.GREEN;
    bold = ChatFormatting.BOLD;
    reset = ChatFormatting.RESET;
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71462_r != null)
      return; 
    if (!Mouse.isButtonDown(2)) {
      this.clicked = false;
      return;
    } 
    if (!this.clicked) {
      this.clicked = true;
      RayTraceResult rayTraceResult = mc.field_71476_x;
      if (rayTraceResult.field_72313_a != RayTraceResult.Type.ENTITY)
        return; 
      if (!(rayTraceResult.field_72308_g instanceof net.minecraft.entity.player.EntityPlayer))
        return; 
      Entity entity = rayTraceResult.field_72308_g;
      if (FriendUtil.isFriend(entity.func_70005_c_())) {
        FriendUtil.Friend friend = FriendUtil.friends.stream().filter(entity::lambda$update$0).findFirst().get();
        FriendUtil.friends.remove(friend);
        MessageUtil.send_client_message("Player " + red + bold + entity.func_70005_c_() + reset + " is now not your friend :(");
      } else {
        FriendUtil.Friend friend = FriendUtil.get_friend_object(entity.func_70005_c_());
        FriendUtil.friends.add(friend);
        MessageUtil.send_client_message("Player " + green + bold + entity.func_70005_c_() + reset + " is now your friend :D");
      } 
    } 
  }
}
