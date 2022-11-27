package me.obsidianbreaker.leux.client.util;

import give up;
import me.obsidianbreaker.leux.client.discordrpc.DiscordEventHandlers;
import me.obsidianbreaker.leux.client.discordrpc.DiscordRPC;
import me.obsidianbreaker.leux.client.discordrpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;

public class DiscordUtil {
  public static DiscordRPC rpc;
  
  public static String ClientId = "799204685139542057";
  
  public static String state;
  
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static DiscordRichPresence presence;
  
  public static String details;
  
  public static void lambda$init$0(int paramInt, String paramString) {
    (give up)null;
    System.out.println("Discord RPC disconnected, var1: " + paramInt + ", var2: " + paramString);
  }
  
  static {
    rpc = DiscordRPC.INSTANCE;
    presence = new DiscordRichPresence();
  }
  
  public static void shutdown() {
    (give up)null;
    rpc.Discord_Shutdown();
  }
  
  public static void lambda$init$1() {
    (give up)null;
    while (!Thread.currentThread().isInterrupted()) {
      rpc.Discord_RunCallbacks();
      details = "IGN: " + mc.field_71439_g.func_70005_c_();
      state = "";
      if (mc.func_71387_A()) {
        state = "Playing on Singleplayer";
      } else if (mc.func_147104_D() != null) {
        if (!(mc.func_147104_D()).field_78845_b.equals(""))
          state = "Playing on " + (mc.func_147104_D()).field_78845_b; 
      } else {
        state = "Main Menu";
      } 
      if (!details.equals(presence.details) || !state.equals(presence.state))
        presence.startTimestamp = System.currentTimeMillis() / (472803280L ^ 0x1C2E6438L); 
      presence.details = details;
      presence.state = state;
      rpc.Discord_UpdatePresence(presence);
      Thread.sleep(592424863L ^ 0x234FBC17L);
    } 
  }
  
  public static void init() {
    (give up)null;
    DiscordEventHandlers discordEventHandlers = new DiscordEventHandlers();
    discordEventHandlers.disconnected = DiscordUtil::lambda$init$0;
    rpc.Discord_Initialize("799204685139542057", discordEventHandlers, true, "");
    presence.startTimestamp = System.currentTimeMillis() / (989721406L ^ 0x3AFDF0D6L);
    presence.details = "IGN: " + mc.field_71439_g.func_70005_c_();
    presence.state = "Main Menu";
    presence.largeImageKey = "logo";
    presence.largeImageText = "0.8";
    rpc.Discord_UpdatePresence(presence);
    (new Thread(DiscordUtil::lambda$init$1, "Discord-RPC-Callback-Handler")).start();
  }
}
