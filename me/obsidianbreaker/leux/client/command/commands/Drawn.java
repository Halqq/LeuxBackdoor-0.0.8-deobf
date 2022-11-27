package me.obsidianbreaker.leux.client.command.commands;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.command.Command;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.DrawnUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;

public class Drawn extends Command {
  public Drawn() {
    super("drawn", "Hide elements of the array list");
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    if (paramArrayOfString.length == 1) {
      MessageUtil.send_client_error_message("module name needed");
      return true;
    } 
    if (paramArrayOfString.length == 2) {
      if (is_module(paramArrayOfString[1])) {
        DrawnUtil.add_remove_item(paramArrayOfString[1]);
        Leux.get_config_manager().save_settings();
      } else {
        MessageUtil.send_client_error_message("cannot find module by name: " + paramArrayOfString[1]);
      } 
      return true;
    } 
    return false;
  }
  
  public boolean is_module(String paramString) {
    (give up)null;
    ArrayList arrayList = Leux.get_hack_manager().get_array_hacks();
    for (Module module : arrayList) {
      if (module.get_tag().equalsIgnoreCase(paramString))
        return true; 
    } 
    return false;
  }
}
