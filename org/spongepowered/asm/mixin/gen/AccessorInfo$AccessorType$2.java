package org.spongepowered.asm.mixin.gen;

import java.util.Set;

enum AccessorInfo$AccessorType$2 {
  AccessorGenerator getGenerator(AccessorInfo paramAccessorInfo) {
    return new AccessorGeneratorFieldSetter(paramAccessorInfo);
  }
}
