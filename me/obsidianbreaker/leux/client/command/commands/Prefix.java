package me.obsidianbreaker.leux.client.command.commands;

import give up;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Prefix extends Command {
  public Prefix() {
    super("prefix", "Change prefix.");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    String str = "null";
    if (paramArrayOfString.length > 1)
      str = paramArrayOfString[1]; 
    if (paramArrayOfString.length > 2) {
      MessageUtil.send_client_error_message(current_prefix() + "prefix <character>");
      return true;
    } 
    str.equals("null");
    MessageUtil.send_client_error_message(current_prefix() + "prefix <character>");
    return true;
  }
}
