package me.obsidianbreaker.leux.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.EnemyUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Enemy extends Command {
  public static ChatFormatting red = ChatFormatting.GREEN;
  
  public static ChatFormatting bold;
  
  public static ChatFormatting reset;
  
  public static ChatFormatting green = ChatFormatting.RED;
  
  public Enemy() {
    super("enemy", "To add enemy");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    if (paramArrayOfString.length == 1) {
      MessageUtil.send_client_message("Add - add enemy");
      MessageUtil.send_client_message("Del - delete enemy");
      MessageUtil.send_client_message("List - list enemies");
      return true;
    } 
    if (paramArrayOfString.length == 2) {
      if (paramArrayOfString[1].equalsIgnoreCase("list")) {
        if (EnemyUtil.enemies.isEmpty()) {
          MessageUtil.send_client_message("You appear to have " + red + bold + "no" + reset + " enemies :)");
        } else {
          for (EnemyUtil.Enemy enemy : EnemyUtil.enemies)
            MessageUtil.send_client_message("" + green + bold + enemy.getUsername()); 
        } 
        return true;
      } 
    } else {
      if (paramArrayOfString.length >= 3) {
        if (paramArrayOfString[1].equalsIgnoreCase("add")) {
          if (EnemyUtil.isEnemy(paramArrayOfString[2])) {
            MessageUtil.send_client_message("Player " + green + bold + paramArrayOfString[2] + reset + " is already your Enemy D:");
            return true;
          } 
          EnemyUtil.Enemy enemy = EnemyUtil.get_enemy_object(paramArrayOfString[2]);
          MessageUtil.send_client_error_message("Cannot find " + red + bold + "UUID" + reset + " for that player :(");
          return true;
        } 
        if (paramArrayOfString[1].equalsIgnoreCase("del") || paramArrayOfString[1].equalsIgnoreCase("remove") || paramArrayOfString[1].equalsIgnoreCase("delete")) {
          if (!EnemyUtil.isEnemy(paramArrayOfString[2])) {
            MessageUtil.send_client_message("Player " + red + bold + paramArrayOfString[2] + reset + " is already not your Enemy :/");
            return true;
          } 
          EnemyUtil.Enemy enemy = EnemyUtil.enemies.stream().filter(paramArrayOfString::lambda$get_message$0).findFirst().get();
          EnemyUtil.enemies.remove(enemy);
          MessageUtil.send_client_message("Player " + red + bold + paramArrayOfString[2] + reset + " is now not your Enemy :)");
          return true;
        } 
      } 
      return true;
    } 
    if (EnemyUtil.isEnemy(paramArrayOfString[1])) {
      MessageUtil.send_client_message("Player " + green + bold + paramArrayOfString[1] + reset + " is your Enemy D:");
      return true;
    } 
    MessageUtil.send_client_error_message("Player " + red + bold + paramArrayOfString[1] + reset + " is not your Enemy :)");
    return true;
  }
  
  public static boolean lambda$get_message$0(String[] paramArrayOfString, EnemyUtil.Enemy paramEnemy) {
    (give up)null;
    return paramEnemy.getUsername().equalsIgnoreCase(paramArrayOfString[2]);
  }
  
  static {
    bold = ChatFormatting.BOLD;
    reset = ChatFormatting.RESET;
  }
}
