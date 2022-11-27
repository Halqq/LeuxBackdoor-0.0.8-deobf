package me.obsidianbreaker.leux.client.discordrpc;

import com.sun.jna.Structure;
import give up;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DiscordUser extends Structure {
  public String discriminator;
  
  public String username;
  
  public String avatar;
  
  public static List FIELD_ORDER = Collections.unmodifiableList(Arrays.asList((Object[])new String[] { "userId", "username", "discriminator", "avatar" }));
  
  public String userId;
  
  public int hashCode() {
    (give up)null;
    return Objects.hash(new Object[] { this.userId, this.username, this.discriminator, this.avatar });
  }
  
  public boolean equals(Object paramObject) {
    (give up)null;
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof DiscordUser))
      return false; 
    DiscordUser discordUser = (DiscordUser)paramObject;
    return (Objects.equals(this.userId, discordUser.userId) && Objects.equals(this.username, discordUser.username) && Objects.equals(this.discriminator, discordUser.discriminator) && Objects.equals(this.avatar, discordUser.avatar));
  }
  
  public DiscordUser(String paramString) {
    setStringEncoding(paramString);
  }
  
  public DiscordUser() {
    this("UTF-8");
  }
  
  public List getFieldOrder() {
    (give up)null;
    return FIELD_ORDER;
  }
}
