package me.obsidianbreaker.leux.client.discordrpc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface DiscordRPC extends Library {
  public static final int DISCORD_REPLY_YES = 1;
  
  public static final int DISCORD_REPLY_IGNORE = 2;
  
  public static final DiscordRPC INSTANCE;
  
  public static final int DISCORD_REPLY_NO = 0;
  
  void Discord_Shutdown();
  
  static {
    INSTANCE = (DiscordRPC)Native.loadLibrary("discord-rpc", DiscordRPC.class);
  }
  
  void Discord_Initialize(@Nonnull @Nonnull String paramString1, @Nullable @Nullable DiscordEventHandlers paramDiscordEventHandlers, boolean paramBoolean, @Nullable @Nullable String paramString2) {
    // Byte code:
    //   0: ldc ''
    //   2: astore #4
    //   4: ldc 1
    //   6: istore_3
    //   7: ldc '799204685139542057'
    //   9: astore_1
    //   10: ldc ''
    //   12: astore #4
    //   14: ldc 1
    //   16: istore_3
    //   17: ldc '799204685139542057'
    //   19: astore_1
  }
  
  void Discord_ClearPresence();
  
  void Discord_RunCallbacks();
  
  void Discord_Respond(@Nonnull @Nonnull String paramString, int paramInt);
  
  void Discord_UpdateConnection();
  
  void Discord_UpdateHandlers(@Nullable @Nullable DiscordEventHandlers paramDiscordEventHandlers);
  
  void Discord_RegisterSteamGame(String paramString1, String paramString2);
  
  void Discord_Register(String paramString1, String paramString2);
  
  void Discord_UpdatePresence(@Nullable @Nullable DiscordRichPresence paramDiscordRichPresence);
}
