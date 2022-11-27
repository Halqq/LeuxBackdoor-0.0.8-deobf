package me.obsidianbreaker.leux.client.discordrpc;

import com.sun.jna.Structure;
import give up;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordEventHandlers extends Structure {
  public DiscordEventHandlers$OnReady ready;
  
  public DiscordEventHandlers$OnStatus disconnected;
  
  public DiscordEventHandlers$OnJoinRequest joinRequest;
  
  public DiscordEventHandlers$OnStatus errored;
  
  public DiscordEventHandlers$OnGameUpdate spectateGame;
  
  public DiscordEventHandlers$OnGameUpdate joinGame;
  
  public static List FIELD_ORDER = Collections.unmodifiableList(Arrays.asList((Object[])new String[] { "ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest" }));
  
  public int hashCode() {
    (give up)null;
    return Objects.hash(new Object[] { this.ready, this.disconnected, this.errored, this.joinGame, this.spectateGame, this.joinRequest });
  }
  
  public List getFieldOrder() {
    (give up)null;
    return FIELD_ORDER;
  }
  
  public boolean equals(Object paramObject) {
    (give up)null;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof DiscordEventHandlers))
      return false; 
    DiscordEventHandlers discordEventHandlers = (DiscordEventHandlers)paramObject;
    return (Objects.equals(this.ready, discordEventHandlers.ready) && Objects.equals(this.disconnected, discordEventHandlers.disconnected) && Objects.equals(this.errored, discordEventHandlers.errored) && Objects.equals(this.joinGame, discordEventHandlers.joinGame) && Objects.equals(this.spectateGame, discordEventHandlers.spectateGame) && Objects.equals(this.joinRequest, discordEventHandlers.joinRequest));
  }
}
