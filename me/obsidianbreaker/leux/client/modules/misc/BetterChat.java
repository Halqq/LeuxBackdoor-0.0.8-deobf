package me.obsidianbreaker.leux.client.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;

public class BetterChat extends Module {
  @EventHandler
  public Listener PacketEvent = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting prefix = create("Prefix", "BCPrefix", true);
  
  public Setting name_highlight = create("Name Highlight", "BCNameHighlight", true);
  
  public BetterChat() {
    super(Category.misc);
  }
  
  public void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (paramReceivePacket.get_packet() instanceof SPacketChat) {
      SPacketChat sPacketChat = (SPacketChat)paramReceivePacket.get_packet();
      if (sPacketChat.func_148915_c() instanceof TextComponentString) {
        TextComponentString textComponentString = (TextComponentString)sPacketChat.func_148915_c();
        if (this.prefix.get_value(true))
          textComponentString.field_150267_b = "§9§l[§9Leux§l]§r " + textComponentString.field_150267_b; 
        String str = textComponentString.func_150254_d();
        if (this.name_highlight.get_value(true) && mc.field_71439_g != null && str.contains(mc.field_71439_g.func_70005_c_()))
          str = str.replaceAll("(?i)" + mc.field_71439_g.func_70005_c_(), ChatFormatting.DARK_GRAY + "<<" + ChatFormatting.LIGHT_PURPLE + mc.field_71439_g.func_70005_c_() + ChatFormatting.DARK_GRAY + ">>" + ChatFormatting.RESET); 
        paramReceivePacket.cancel();
        MessageUtil.client_message(str);
      } 
    } 
  }
}
