package org.spongepowered.asm.mixin;

public enum MixinEnvironment$Side {
  UNKNOWN, CLIENT, SERVER;
  
  private static final MixinEnvironment$Side[] $VALUES = new MixinEnvironment$Side[] { UNKNOWN, CLIENT, SERVER };
  
  protected abstract boolean detect();
}
