package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.MixinEnvironment;

class MixinConfig$1 {
  static final int[] $SwitchMap$org$spongepowered$asm$mixin$MixinEnvironment$Side = new int[(MixinEnvironment.Side.values()).length];
  
  static {
    $SwitchMap$org$spongepowered$asm$mixin$MixinEnvironment$Side[MixinEnvironment.Side.CLIENT.ordinal()] = 1;
    $SwitchMap$org$spongepowered$asm$mixin$MixinEnvironment$Side[MixinEnvironment.Side.SERVER.ordinal()] = 2;
    $SwitchMap$org$spongepowered$asm$mixin$MixinEnvironment$Side[MixinEnvironment.Side.UNKNOWN.ordinal()] = 3;
  }
}
