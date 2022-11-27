package me.obsidianbreaker.leux.client.util;

import give up;

public enum BlockInteractHelper$ValidResult {
  NoEntityCollision, AlreadyBlockThere, Ok, NoNeighbors;
  
  public static BlockInteractHelper$ValidResult[] $VALUES;
  
  static {
    Ok = new BlockInteractHelper$ValidResult("Ok", 3);
    $VALUES = new BlockInteractHelper$ValidResult[] { NoEntityCollision, AlreadyBlockThere, NoNeighbors, Ok };
  }
}
