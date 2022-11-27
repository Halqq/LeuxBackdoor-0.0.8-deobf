package org.spongepowered.asm.mixin;

import org.spongepowered.asm.service.MixinService;

enum MixinEnvironment$Side$2 {
  protected boolean detect() {
    String str = MixinService.getService().getSideName();
    return "CLIENT".equals(str);
  }
}
