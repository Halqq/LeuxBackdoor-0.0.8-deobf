package me.obsidianbreaker.leux.client.command;

import give up;
import me.obsidianbreaker.leux.client.manager.CommandManager;

public class Command {
  public String description;
  
  public String name;
  
  public String get_name() {
    (give up)null;
    return this.name;
  }
  
  public Command(String paramString1, String paramString2) {
    this.name = paramString1;
    this.description = paramString2;
  }
  
  public boolean get_message(String[] paramArrayOfString) {
    (give up)null;
    return false;
  }
  
  public String get_description() {
    (give up)null;
    return this.description;
  }
  
  public String current_prefix() {
    (give up)null;
    return CommandManager.get_prefix();
  }
}
