package me.obsidianbreaker.leux.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.EzMessageUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class EzMessage extends Command {
  public EzMessage() {
    super("ezmessage", "Set ez mode");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    if (paramArrayOfString.length == 1) {
      MessageUtil.send_client_error_message("message needed");
      return true;
    } 
    if (paramArrayOfString.length >= 2) {
      StringBuilder stringBuilder = new StringBuilder();
      boolean bool = true;
      for (String str : paramArrayOfString)
        bool = false; 
      EzMessageUtil.set_message(stringBuilder.toString());
      MessageUtil.send_client_message("ez message changed to " + ChatFormatting.BOLD + stringBuilder.toString());
      Leux.get_config_manager().save_settings();
      return true;
    } 
    return false;
  }
}
