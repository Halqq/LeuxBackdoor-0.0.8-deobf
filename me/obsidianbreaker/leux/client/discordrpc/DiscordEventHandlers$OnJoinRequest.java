package me.obsidianbreaker.leux.client.discordrpc;

import com.sun.jna.Callback;

public interface DiscordEventHandlers$OnJoinRequest extends Callback {
  void accept(DiscordUser paramDiscordUser);
}
