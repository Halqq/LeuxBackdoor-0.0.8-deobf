package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;

public enum Compass$Direction {
  W, N, S, E;
  
  public static Compass$Direction[] $VALUES;
  
  static {
    W = new Compass$Direction("W", 1);
    S = new Compass$Direction("S", 2);
    E = new Compass$Direction("E", 3);
    $VALUES = new Compass$Direction[] { N, W, S, E };
  }
}
