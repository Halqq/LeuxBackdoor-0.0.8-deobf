package me.obsidianbreaker.leux.client.modules.combat;

import give up;

public enum BedAura$spoof_face {
  SOUTH, WEST, EAST, NORTH;
  
  public static BedAura$spoof_face[] $VALUES;
  
  static {
    WEST = new BedAura$spoof_face("WEST", 1);
    NORTH = new BedAura$spoof_face("NORTH", 2);
    SOUTH = new BedAura$spoof_face("SOUTH", 3);
    $VALUES = new BedAura$spoof_face[] { EAST, WEST, NORTH, SOUTH };
  }
}
