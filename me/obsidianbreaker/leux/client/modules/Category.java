package me.obsidianbreaker.leux.client.modules;

import give up;

public enum Category {
  client,
  combat,
  render,
  misc,
  exploit,
  secret,
  player("PLAYER", "PLAYER", false),
  movement("PLAYER", "PLAYER", false);
  
  public static Category[] $VALUES;
  
  public boolean hidden;
  
  public String name;
  
  public String tag;
  
  public String get_tag() {
    (give up)null;
    return this.tag;
  }
  
  public String get_name() {
    (give up)null;
    return this.name;
  }
  
  Category(String paramString1, String paramString2, boolean paramBoolean) {
    this.name = paramString1;
    this.tag = paramString2;
    this.hidden = paramBoolean;
  }
  
  static {
    combat = new Category("combat", 1, "COMBAT", "COMBAT", false);
    movement = new Category("movement", 2, "MOVEMENT", "MOVEMENT", false);
    render = new Category("render", 3, "RENDER", "RENDER", false);
    exploit = new Category("exploit", 4, "EXPLOIT", "EXPLOIT", false);
    misc = new Category("misc", 5, "MISC", "MISC", false);
    client = new Category("client", 6, "CLIENT", "CLIENT", false);
    secret = new Category("secret", 7, "SECRET", "SECRET", true);
    $VALUES = new Category[] { player, combat, movement, render, exploit, misc, client, secret };
  }
  
  public boolean is_hidden() {
    (give up)null;
    return this.hidden;
  }
}
