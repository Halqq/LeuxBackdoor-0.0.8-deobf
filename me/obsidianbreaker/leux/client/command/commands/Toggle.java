package me.obsidianbreaker.leux.client.command.commands;

import give up;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.manager.CommandManager;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Toggle extends Command {
  public Toggle() {
    super("t", "turn on and off stuffs");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    String str = "null";
    if (paramArrayOfString.length > 1)
      str = paramArrayOfString[1]; 
    if (paramArrayOfString.length > 2) {
      MessageUtil.send_client_error_message(current_prefix() + "t <ModuleNameNoSpace>");
      return true;
    } 
    str.equals("null");
    MessageUtil.send_client_error_message(CommandManager.get_prefix() + "t <ModuleNameNoSpace>");
    return true;
  }
}
