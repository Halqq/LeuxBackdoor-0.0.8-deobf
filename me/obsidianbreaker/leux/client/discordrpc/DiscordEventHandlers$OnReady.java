package me.obsidianbreaker.leux.client.discordrpc;

import com.sun.jna.Callback;

public interface DiscordEventHandlers$OnReady extends Callback {
  void accept(DiscordUser paramDiscordUser);
}
