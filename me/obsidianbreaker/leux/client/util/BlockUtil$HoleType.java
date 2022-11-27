package me.obsidianbreaker.leux.client.util;

import give up;

public enum BlockUtil$HoleType {
  DOUBLE, NONE, SINGLE, CUSTOM;
  
  public static BlockUtil$HoleType[] $VALUES;
  
  static {
    DOUBLE = new BlockUtil$HoleType("DOUBLE", 1);
    CUSTOM = new BlockUtil$HoleType("CUSTOM", 2);
    NONE = new BlockUtil$HoleType("NONE", 3);
    $VALUES = new BlockUtil$HoleType[] { SINGLE, DOUBLE, CUSTOM, NONE };
  }
}
