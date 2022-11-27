package org.spongepowered.asm.mixin;

import org.spongepowered.asm.util.JavaVersion;

enum MixinEnvironment$CompatibilityLevel$1 {
  boolean isSupported() {
    return (JavaVersion.current() >= 1.7D);
  }
}
