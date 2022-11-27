package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public class ChatColors extends Module {
  public Setting color = create("Color", "ChatColor", "Green", combobox(new String[] { "Green", "Yellow", "Blue", "None" }));
  
  @EventHandler
  public Listener listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting ignore = create("Ignore", "ChatColorIgnore", true);
  
  public void lambda$new$0(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (!(paramSendPacket.get_packet() instanceof CPacketChatMessage))
      return; 
    boolean bool = true;
    String str1 = "";
    String str2 = ((CPacketChatMessage)paramSendPacket.get_packet()).func_149439_c();
    boolean bool1 = this.ignore.get_value(true);
    if (str2.startsWith("/"))
      bool = false; 
    if (str2.startsWith("\\"))
      bool = false; 
    if (str2.startsWith("!"))
      bool = false; 
    if (str2.startsWith(":"))
      bool = false; 
    if (str2.startsWith(";"))
      bool = false; 
    if (str2.startsWith("."))
      bool = false; 
    if (str2.startsWith(","))
      bool = false; 
    if (str2.startsWith("@"))
      bool = false; 
    if (str2.startsWith("&"))
      bool = false; 
    if (str2.startsWith("*"))
      bool = false; 
    if (str2.startsWith("$"))
      bool = false; 
    if (str2.startsWith("#"))
      bool = false; 
    if (str2.startsWith("("))
      bool = false; 
    if (str2.startsWith(")"))
      bool = false; 
    if (this.color.in("Green"))
      str1 = ">"; 
    if (this.color.in("Yellow"))
      str1 = "#"; 
    if (this.color.in("Blue"))
      str1 = "``"; 
    if (this.color.in("None"))
      str1 = ""; 
    str2 = str1 + str2;
    ((CPacketChatMessage)paramSendPacket.get_packet()).field_149440_a = str2;
  }
  
  public ChatColors() {
    super(Category.misc);
  }
}
