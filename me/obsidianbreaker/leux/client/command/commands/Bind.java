package me.obsidianbreaker.leux.client.command.commands;

import give up;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Bind extends Command {
  public Bind() {
    super("bind", "bind module to key");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    String str1 = "null;";
    String str2 = "null";
    if (paramArrayOfString.length == 3) {
      str1 = paramArrayOfString[1].toUpperCase();
      str2 = paramArrayOfString[2].toUpperCase();
    } else if (paramArrayOfString.length > 1) {
      MessageUtil.send_client_error_message(current_prefix() + "bind <ModuleTag> <key>");
      return true;
    } 
    str1.equals("null");
    str2.equals("null");
    MessageUtil.send_client_error_message(current_prefix() + "bind <ModuleTag> <key>");
    return true;
  }
}
