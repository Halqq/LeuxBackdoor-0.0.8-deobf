package me.obsidianbreaker.leux.client.command.commands;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Config extends Command {
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    if (paramArrayOfString.length == 1) {
      MessageUtil.send_client_error_message("config needed");
      return true;
    } 
    if (paramArrayOfString.length == 2) {
      String str = paramArrayOfString[1];
      if (Leux.get_config_manager().set_active_config_folder(str + "/")) {
        MessageUtil.send_client_message("new config folder set as " + str);
      } else {
        MessageUtil.send_client_error_message("cannot set folder to " + str);
      } 
      return true;
    } 
    MessageUtil.send_client_error_message("config path may only be one word");
    return true;
  }
  
  public Config() {
    super("config", "changes which config is loaded");
  }
}
