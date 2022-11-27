package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import java.util.Random;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketChatMessage;

public class ChatSuffix extends Module {
  @EventHandler
  public Listener listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting separator = create("Separator", "ChatSuffixSeparator", "None", combobox(new String[] { "»", "«", "⏐", "⚝", "None" }));
  
  public String[] random_client_name = new String[] { "leux", "tater", "luscius", "obsidian", "backdoor" };
  
  public boolean accept_suffix;
  
  public Setting ignore = create("Ignore", "ChatSuffixIgnore", true);
  
  public boolean suffix_2b2t = false;
  
  public Setting type = create("Type", "ChatSuffixType", "Leux", combobox(new String[] { "Leux", "Obsidian", "2B2T", "Random" }));
  
  public boolean suffix_leux = false;
  
  public boolean suffix_random = false;
  
  public boolean suffix_obsidian = false;
  
  public StringBuilder suffix;
  
  public String[] random_client_finish = new String[] { 
      " plus", " luscius", "+", " bbcversion", " brrr", " antiniggers", " popper", " backdoor", " obsidian", " miner", 
      " cold", " tater", " user" };
  
  public String text_separator = "";
  
  public String random_string(String[] paramArrayOfString) {
    (give up)null;
    return paramArrayOfString[(new Random()).nextInt(paramArrayOfString.length)];
  }
  
  public String array_detail() {
    (give up)null;
    return this.type.get_current_value();
  }
  
  public String convert_base(String paramString) {
    (give up)null;
    return Leux.smoth(paramString);
  }
  
  public ChatSuffix() {
    super(Category.misc);
  }
  
  public void lambda$new$0(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (!(paramSendPacket.get_packet() instanceof CPacketChatMessage))
      return; 
    this.accept_suffix = true;
    boolean bool = this.ignore.get_value(true);
    String str = ((CPacketChatMessage)paramSendPacket.get_packet()).func_149439_c();
    if (str.startsWith("/"))
      this.accept_suffix = false; 
    if (str.startsWith("\\"))
      this.accept_suffix = false; 
    if (str.startsWith("!"))
      this.accept_suffix = false; 
    if (str.startsWith(":"))
      this.accept_suffix = false; 
    if (str.startsWith(";"))
      this.accept_suffix = false; 
    if (str.startsWith("."))
      this.accept_suffix = false; 
    if (str.startsWith(","))
      this.accept_suffix = false; 
    if (str.startsWith("@"))
      this.accept_suffix = false; 
    if (str.startsWith("&"))
      this.accept_suffix = false; 
    if (str.startsWith("*"))
      this.accept_suffix = false; 
    if (str.startsWith("$"))
      this.accept_suffix = false; 
    if (str.startsWith("#"))
      this.accept_suffix = false; 
    if (str.startsWith("("))
      this.accept_suffix = false; 
    if (str.startsWith(")"))
      this.accept_suffix = false; 
    if (this.separator.in("»"))
      this.text_separator = "»"; 
    if (this.separator.in("«"))
      this.text_separator = "«"; 
    if (this.separator.in("⏐"))
      this.text_separator = "⏐"; 
    if (this.separator.in("⚝"))
      this.text_separator = "⚝"; 
    if (this.separator.in("None"))
      this.text_separator = ""; 
    if (this.type.in("Leux")) {
      this.suffix_leux = true;
      this.suffix_obsidian = false;
      this.suffix_2b2t = false;
      this.suffix_random = false;
    } 
    if (this.type.in("Obsidian")) {
      this.suffix_leux = false;
      this.suffix_obsidian = true;
      this.suffix_2b2t = false;
      this.suffix_random = false;
    } 
    if (this.type.in("2B2T")) {
      this.suffix_leux = false;
      this.suffix_obsidian = false;
      this.suffix_2b2t = true;
      this.suffix_random = false;
    } 
    if (this.type.in("Random")) {
      this.suffix_leux = false;
      this.suffix_obsidian = false;
      this.suffix_2b2t = false;
      this.suffix_random = true;
    } 
    if (this.accept_suffix) {
      if (this.suffix_leux)
        str = str + " " + this.text_separator + " " + "LΣuxBαckdoor"; 
      if (this.suffix_obsidian)
        str = str + " " + this.text_separator + " " + convert_base("obsidian+"); 
      if (this.suffix_2b2t)
        str = str + " " + this.text_separator + " " + "LeuxBackdoor"; 
      if (this.suffix_random) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(convert_base(random_string(this.random_client_name)));
        stringBuilder.append(convert_base(random_string(this.random_client_finish)));
        str = str + " " + this.text_separator + " " + stringBuilder.toString();
      } 
      if (str.length() >= 256)
        str.substring(0, 256); 
    } 
    ((CPacketChatMessage)paramSendPacket.get_packet()).field_149440_a = str;
  }
}
