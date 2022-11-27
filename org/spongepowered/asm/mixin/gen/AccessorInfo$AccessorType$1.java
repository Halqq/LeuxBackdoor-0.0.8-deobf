package org.spongepowered.asm.mixin.gen;

import java.util.Set;

enum AccessorInfo$AccessorType$1 {
  AccessorGenerator getGenerator(AccessorInfo paramAccessorInfo) {
    return new AccessorGeneratorFieldGetter(paramAccessorInfo);
  }
}
