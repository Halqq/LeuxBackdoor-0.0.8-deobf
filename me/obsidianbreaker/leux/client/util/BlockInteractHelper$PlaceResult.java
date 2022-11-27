package me.obsidianbreaker.leux.client.util;

import give up;

public enum BlockInteractHelper$PlaceResult {
  Placed, CantPlace, Neighbors, NotReplaceable;
  
  public static BlockInteractHelper$PlaceResult[] $VALUES;
  
  static {
    Neighbors = new BlockInteractHelper$PlaceResult("Neighbors", 1);
    CantPlace = new BlockInteractHelper$PlaceResult("CantPlace", 2);
    Placed = new BlockInteractHelper$PlaceResult("Placed", 3);
    $VALUES = new BlockInteractHelper$PlaceResult[] { NotReplaceable, Neighbors, CantPlace, Placed };
  }
}
