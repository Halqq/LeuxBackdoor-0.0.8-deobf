package me.obsidianbreaker.leux.client.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

public class MessageUtil {
  public static ChatFormatting c;
  
  public static ChatFormatting g;
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static ChatFormatting r;
  
  public static ChatFormatting b = ChatFormatting.BLUE;
  
  public static void toggle_message(Module paramModule) {
    (give up)null;
    if (paramModule.is_active()) {
      client_message_simple(b + Leux.get_client_name() + g + " >>> " + r + paramModule.get_name() + ChatFormatting.GREEN + " Enabled");
      if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationEnable").get_value(true))
        NotificationUtil.send_notification(new Notification(paramModule.get_name() + " Enabled")); 
    } else {
      client_message_simple(b + Leux.get_client_name() + g + " >>> " + r + paramModule.get_name() + c + " Disabled");
      if (Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationEnable").get_value(true))
        NotificationUtil.send_notification(new Notification(paramModule.get_name() + " Disabled")); 
    } 
  }
  
  public static void client_message(String paramString) {
    (give up)null;
    if (mc.field_71439_g != null)
      mc.field_71439_g.func_145747_a((ITextComponent)new MessageUtil$ChatMessage(paramString)); 
  }
  
  public static void send_client_message(String paramString) {
    (give up)null;
    client_message(b + Leux.get_client_name() + g + " >>> " + r + paramString);
  }
  
  static {
    c = ChatFormatting.RED;
    r = ChatFormatting.RESET;
    g = ChatFormatting.GRAY;
  }
  
  public static void send_client_message_simple(String paramString) {
    (give up)null;
    client_message(b + Leux.get_client_name() + g + " >>> " + r + paramString);
  }
  
  public static void send_client_error_message(String paramString) {
    (give up)null;
    client_message(c + Leux.get_client_name() + g + " >>> " + r + paramString);
  }
  
  public static void client_message_simple(String paramString) {
    (give up)null;
    if (mc.field_71439_g != null) {
      ITextComponent iTextComponent = (new TextComponentString(paramString)).func_150255_a((new Style()).func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString("Client by ObsidianBreaker :3"))));
      mc.field_71456_v.func_146158_b().func_146234_a(iTextComponent, 5936);
    } 
  }
}
