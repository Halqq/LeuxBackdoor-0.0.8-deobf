package me.obsidianbreaker.leux.client.discordrpc;

import com.sun.jna.Structure;
import give up;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordRichPresence extends Structure {
  public String largeImageKey;
  
  public String smallImageText;
  
  public String partyId;
  
  public String smallImageKey;
  
  public String spectateSecret;
  
  public int partySize;
  
  public static List FIELD_ORDER = Collections.unmodifiableList(Arrays.asList((Object[])new String[] { 
          "state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", 
          "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance" }));
  
  public String largeImageText;
  
  public long startTimestamp;
  
  public byte instance;
  
  public String matchSecret;
  
  public String joinSecret;
  
  public long endTimestamp;
  
  public int partyMax;
  
  public String state;
  
  public String details;
  
  public DiscordRichPresence() {
    this("UTF-8");
  }
  
  public DiscordRichPresence(String paramString) {
    setStringEncoding(paramString);
  }
  
  public int hashCode() {
    (give up)null;
    return Objects.hash(new Object[] { 
          this.state, this.details, Long.valueOf(this.startTimestamp), Long.valueOf(this.endTimestamp), this.largeImageKey, this.largeImageText, this.smallImageKey, this.smallImageText, this.partyId, Integer.valueOf(this.partySize), 
          Integer.valueOf(this.partyMax), this.matchSecret, this.joinSecret, this.spectateSecret, Byte.valueOf(this.instance) });
  }
  
  public boolean equals(Object paramObject) {
    (give up)null;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof DiscordRichPresence))
      return false; 
    DiscordRichPresence discordRichPresence = (DiscordRichPresence)paramObject;
    return (this.startTimestamp == discordRichPresence.startTimestamp && this.endTimestamp == discordRichPresence.endTimestamp && this.partySize == discordRichPresence.partySize && this.partyMax == discordRichPresence.partyMax && this.instance == discordRichPresence.instance && Objects.equals(this.state, discordRichPresence.state) && Objects.equals(this.details, discordRichPresence.details) && Objects.equals(this.largeImageKey, discordRichPresence.largeImageKey) && Objects.equals(this.largeImageText, discordRichPresence.largeImageText) && Objects.equals(this.smallImageKey, discordRichPresence.smallImageKey) && Objects.equals(this.smallImageText, discordRichPresence.smallImageText) && Objects.equals(this.partyId, discordRichPresence.partyId) && Objects.equals(this.matchSecret, discordRichPresence.matchSecret) && Objects.equals(this.joinSecret, discordRichPresence.joinSecret) && Objects.equals(this.spectateSecret, discordRichPresence.spectateSecret));
  }
  
  public List getFieldOrder() {
    (give up)null;
    return FIELD_ORDER;
  }
}
