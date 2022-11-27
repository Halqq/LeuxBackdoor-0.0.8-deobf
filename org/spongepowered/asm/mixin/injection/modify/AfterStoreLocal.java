package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@AtCode("STORE")
public class AfterStoreLocal extends BeforeLoadLocal {
  public AfterStoreLocal(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData, 54, true);
  }
}
