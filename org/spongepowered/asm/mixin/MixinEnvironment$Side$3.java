package org.spongepowered.asm.mixin;

import org.spongepowered.asm.service.MixinService;

enum MixinEnvironment$Side$3 {
  protected boolean detect() {
    String str = MixinService.getService().getSideName();
    return ("SERVER".equals(str) || "DEDICATEDSERVER".equals(str));
  }
}
