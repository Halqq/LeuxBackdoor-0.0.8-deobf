package me.obsidianbreaker.leux.client.modules.combat;

import me.obsidianbreaker.leux.client.util.PlayerUtil;

public class AntiCityBoss$1 {
  public static int[] $SwitchMap$me$obsidianbreaker$leux$client$util$PlayerUtil$FacingDirection = new int[(PlayerUtil.FacingDirection.values()).length];
  
  static {
    $SwitchMap$me$obsidianbreaker$leux$client$util$PlayerUtil$FacingDirection[PlayerUtil.FacingDirection.East.ordinal()] = 1;
    $SwitchMap$me$obsidianbreaker$leux$client$util$PlayerUtil$FacingDirection[PlayerUtil.FacingDirection.North.ordinal()] = 2;
    $SwitchMap$me$obsidianbreaker$leux$client$util$PlayerUtil$FacingDirection[PlayerUtil.FacingDirection.South.ordinal()] = 3;
    $SwitchMap$me$obsidianbreaker$leux$client$util$PlayerUtil$FacingDirection[PlayerUtil.FacingDirection.West.ordinal()] = 4;
  }
}
