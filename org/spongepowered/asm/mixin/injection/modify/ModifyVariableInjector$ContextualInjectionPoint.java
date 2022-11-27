package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collection;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

abstract class ModifyVariableInjector$ContextualInjectionPoint extends InjectionPoint {
  protected final IMixinContext context;
  
  ModifyVariableInjector$ContextualInjectionPoint(IMixinContext paramIMixinContext) {
    this.context = paramIMixinContext;
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection paramCollection) {
    throw new InvalidInjectionException(this.context, getAtCode() + " injection point must be used in conjunction with @ModifyVariable");
  }
  
  abstract boolean find(Target paramTarget, Collection paramCollection);
}
