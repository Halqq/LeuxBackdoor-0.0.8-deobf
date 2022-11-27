package me.obsidianbreaker.leux.client.discordrpc;

import com.sun.jna.Callback;

public interface DiscordEventHandlers$OnStatus extends Callback {
  void accept(int paramInt, String paramString);
}
