package me.obsidianbreaker.leux.client.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.ClientNameUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class ClientName extends Command {
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    if (paramArrayOfString.length == 1) {
      MessageUtil.send_client_error_message("client name needed");
      return true;
    } 
    if (paramArrayOfString.length >= 2) {
      StringBuilder stringBuilder = new StringBuilder();
      boolean bool = true;
      for (String str : paramArrayOfString)
        bool = false; 
      ClientNameUtil.set_client_name(stringBuilder.toString());
      MessageUtil.send_client_message("client name changed to " + ChatFormatting.AQUA + ChatFormatting.BOLD + stringBuilder.toString());
      Leux.get_config_manager().save_settings();
      return true;
    } 
    return false;
  }
  
  public ClientName() {
    super("clientname", "Set the client name");
  }
}
