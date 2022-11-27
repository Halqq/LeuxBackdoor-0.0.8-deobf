package me.obsidianbreaker.leux.client.modules.client;

import give up;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.DiscordUtil;

public class RPC extends Module {
  public void disable() {
    (give up)null;
    DiscordUtil.shutdown();
  }
  
  public RPC() {
    super(Category.client);
  }
  
  public void enable() {
    (give up)null;
    DiscordUtil.init();
  }
}
