package me.obsidianbreaker.leux.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Friend extends Command {
  public static ChatFormatting green;
  
  public static ChatFormatting bold;
  
  public static ChatFormatting reset;
  
  public static ChatFormatting red = ChatFormatting.RED;
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    if (paramArrayOfString.length == 1) {
      MessageUtil.send_client_message("Add - add friend");
      MessageUtil.send_client_message("Del - delete friend");
      MessageUtil.send_client_message("List - list friends");
      return true;
    } 
    if (paramArrayOfString.length == 2) {
      if (paramArrayOfString[1].equalsIgnoreCase("list")) {
        if (FriendUtil.friends.isEmpty()) {
          MessageUtil.send_client_message("You appear to have " + red + bold + "no" + reset + " friends :(");
        } else {
          for (FriendUtil.Friend friend : FriendUtil.friends)
            MessageUtil.send_client_message("" + green + bold + friend.getUsername()); 
        } 
      } else {
        if (FriendUtil.isFriend(paramArrayOfString[1])) {
          MessageUtil.send_client_message("Player " + green + bold + paramArrayOfString[1] + reset + " is your friend :D");
          return true;
        } 
        MessageUtil.send_client_error_message("Player " + red + bold + paramArrayOfString[1] + reset + " is not your friend :(");
        return true;
      } 
      return true;
    } 
    if (paramArrayOfString.length >= 3) {
      if (paramArrayOfString[1].equalsIgnoreCase("add")) {
        if (FriendUtil.isFriend(paramArrayOfString[2])) {
          MessageUtil.send_client_message("Player " + green + bold + paramArrayOfString[2] + reset + " is already your friend :D");
          return true;
        } 
        FriendUtil.Friend friend = FriendUtil.get_friend_object(paramArrayOfString[2]);
        MessageUtil.send_client_error_message("Cannot find " + red + bold + "UUID" + reset + " for that player :(");
        return true;
      } 
      if (paramArrayOfString[1].equalsIgnoreCase("del") || paramArrayOfString[1].equalsIgnoreCase("remove") || paramArrayOfString[1].equalsIgnoreCase("delete")) {
        if (!FriendUtil.isFriend(paramArrayOfString[2])) {
          MessageUtil.send_client_message("Player " + red + bold + paramArrayOfString[2] + reset + " is already not your friend :/");
          return true;
        } 
        FriendUtil.Friend friend = FriendUtil.friends.stream().filter(paramArrayOfString::lambda$get_message$0).findFirst().get();
        FriendUtil.friends.remove(friend);
        MessageUtil.send_client_message("Player " + red + bold + paramArrayOfString[2] + reset + " is now not your friend :(");
        return true;
      } 
    } 
    return true;
  }
  
  public Friend() {
    super("friend", "To add friends");
  }
  
  public static boolean lambda$get_message$0(String[] paramArrayOfString, FriendUtil.Friend paramFriend) {
    (give up)null;
    return paramFriend.getUsername().equalsIgnoreCase(paramArrayOfString[2]);
  }
  
  static {
    green = ChatFormatting.GREEN;
    bold = ChatFormatting.BOLD;
    reset = ChatFormatting.RESET;
  }
}
