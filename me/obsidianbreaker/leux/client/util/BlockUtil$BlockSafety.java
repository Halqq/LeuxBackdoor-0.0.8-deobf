package me.obsidianbreaker.leux.client.util;

import give up;

public enum BlockUtil$BlockSafety {
  RESISTANT, BREAKABLE, UNBREAKABLE;
  
  public static BlockUtil$BlockSafety[] $VALUES;
  
  static {
    RESISTANT = new BlockUtil$BlockSafety("RESISTANT", 1);
    BREAKABLE = new BlockUtil$BlockSafety("BREAKABLE", 2);
    $VALUES = new BlockUtil$BlockSafety[] { UNBREAKABLE, RESISTANT, BREAKABLE };
  }
}
