package me.obsidianbreaker.leux.client.command.commands;

import give up;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Alert extends Command {
  public Alert() {
    super("alert", "if the module should spam chat or not");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    String str1 = "null";
    String str2 = "null";
    if (paramArrayOfString.length > 1)
      str1 = paramArrayOfString[1]; 
    if (paramArrayOfString.length > 2)
      str2 = paramArrayOfString[2]; 
    if (paramArrayOfString.length > 3) {
      MessageUtil.send_client_error_message(current_prefix() + "t <ModuleName> <True/On/False/Off>");
      return true;
    } 
    str1.equals("null");
    MessageUtil.send_client_error_message(current_prefix() + "t <ModuleName> <True/On/False/Off>");
    return true;
  }
}
