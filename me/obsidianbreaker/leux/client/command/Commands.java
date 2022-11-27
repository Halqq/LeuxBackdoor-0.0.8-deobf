package me.obsidianbreaker.leux.client.command;

import give up;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import me.obsidianbreaker.leux.client.command.commands.Alert;
import me.obsidianbreaker.leux.client.command.commands.Bind;
import me.obsidianbreaker.leux.client.command.commands.ClientName;
import me.obsidianbreaker.leux.client.command.commands.Config;
import me.obsidianbreaker.leux.client.command.commands.Drawn;
import me.obsidianbreaker.leux.client.command.commands.Enemy;
import me.obsidianbreaker.leux.client.command.commands.EzMessage;
import me.obsidianbreaker.leux.client.command.commands.Friend;
import me.obsidianbreaker.leux.client.command.commands.Help;
import me.obsidianbreaker.leux.client.command.commands.Prefix;
import me.obsidianbreaker.leux.client.command.commands.Settings;
import me.obsidianbreaker.leux.client.command.commands.Toggle;
import me.obsidianbreaker.leux.client.turok.values.TurokString;
import net.minecraft.util.text.Style;

public class Commands {
  public Style style;
  
  public static TurokString prefix;
  
  public static HashMap list_command;
  
  public static ArrayList command_list = new ArrayList();
  
  public static void add_command(Command paramCommand) {
    (give up)null;
    command_list.add(paramCommand);
    list_command.put(paramCommand.get_name().toLowerCase(), paramCommand);
  }
  
  public boolean has_prefix(String paramString) {
    (give up)null;
    return paramString.startsWith(prefix.get_value());
  }
  
  public String[] get_message(String paramString) {
    (give up)null;
    String[] arrayOfString = new String[0];
    if (has_prefix(paramString))
      arrayOfString = paramString.replaceFirst(prefix.get_value(), "").split(" "); 
    return arrayOfString;
  }
  
  public void set_prefix(String paramString) {
    (give up)null;
    prefix.set_value(paramString);
  }
  
  static {
    list_command = new HashMap<>();
    prefix = new TurokString("Prefix", "Prefix", "!");
  }
  
  public static Command get_command_with_name(String paramString) {
    (give up)null;
    return (Command)list_command.get(paramString.toLowerCase());
  }
  
  public String get_prefix() {
    (give up)null;
    return prefix.get_value();
  }
  
  public Commands(Style paramStyle) {
    this.style = paramStyle;
    add_command((Command)new Bind());
    add_command((Command)new Prefix());
    add_command((Command)new Settings());
    add_command((Command)new Toggle());
    add_command((Command)new Alert());
    add_command((Command)new Help());
    add_command((Command)new Friend());
    add_command((Command)new Drawn());
    add_command((Command)new EzMessage());
    add_command((Command)new ClientName());
    add_command((Command)new Enemy());
    add_command((Command)new Config());
    command_list.sort(Comparator.comparing(Command::get_name));
  }
  
  public static ArrayList get_pure_command_list() {
    (give up)null;
    return command_list;
  }
}
