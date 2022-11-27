package me.obsidianbreaker.leux.client.command.commands;

import give up;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Settings extends Command {
  public Settings() {
    super("settings", "To save/load settings.");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    String str = "null";
    if (paramArrayOfString.length > 1)
      str = paramArrayOfString[1]; 
    str.equals("null");
    MessageUtil.send_client_error_message(current_prefix() + "settings <save/load>");
    return true;
  }
}
