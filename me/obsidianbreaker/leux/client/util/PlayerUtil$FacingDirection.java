package me.obsidianbreaker.leux.client.util;

import give up;

public enum PlayerUtil$FacingDirection {
  West, South, North, East;
  
  public static PlayerUtil$FacingDirection[] $VALUES;
  
  static {
    South = new PlayerUtil$FacingDirection("South", 1);
    East = new PlayerUtil$FacingDirection("East", 2);
    West = new PlayerUtil$FacingDirection("West", 3);
    $VALUES = new PlayerUtil$FacingDirection[] { North, South, East, West };
  }
}
