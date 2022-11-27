package org.spongepowered.asm.mixin;

public enum Interface$Remap {
  ALL,
  FORCE(true),
  ONLY_PREFIXED,
  NONE;
  
  private final boolean forceRemap;
  
  private static final Interface$Remap[] $VALUES = new Interface$Remap[] { ALL, FORCE, ONLY_PREFIXED, NONE };
  
  Interface$Remap(boolean paramBoolean) {
    this.forceRemap = paramBoolean;
  }
  
  public boolean forceRemap() {
    return this.forceRemap;
  }
}
