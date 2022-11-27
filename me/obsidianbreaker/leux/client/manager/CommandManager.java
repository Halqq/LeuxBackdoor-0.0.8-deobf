package me.obsidianbreaker.leux.client.manager;

import give up;
import me.obsidianbreaker.leux.client.command.Commands;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

public class CommandManager {
  public static Commands command_list;
  
  public static String get_prefix() {
    (give up)null;
    return command_list.get_prefix();
  }
  
  public static void set_prefix(String paramString) {
    (give up)null;
    command_list.set_prefix(paramString);
  }
  
  public CommandManager() {
    command_list = new Commands((new Style()).func_150238_a(TextFormatting.BLUE));
  }
}
