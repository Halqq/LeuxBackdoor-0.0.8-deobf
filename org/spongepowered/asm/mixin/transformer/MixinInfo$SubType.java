package org.spongepowered.asm.mixin.transformer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

abstract class MixinInfo$SubType {
  protected final MixinInfo mixin;
  
  protected final String annotationType;
  
  protected final boolean targetMustBeInterface;
  
  protected boolean detached;
  
  MixinInfo$SubType(MixinInfo paramMixinInfo, String paramString, boolean paramBoolean) {
    this.mixin = paramMixinInfo;
    this.annotationType = paramString;
    this.targetMustBeInterface = paramBoolean;
  }
  
  Collection getInterfaces() {
    return Collections.emptyList();
  }
  
  boolean isDetachedSuper() {
    return this.detached;
  }
  
  boolean isLoadable() {
    return false;
  }
  
  void validateTarget(String paramString, ClassInfo paramClassInfo) {
    boolean bool = paramClassInfo.isInterface();
    if (bool != this.targetMustBeInterface);
  }
  
  abstract void validate(MixinInfo$State paramMixinInfo$State, List paramList);
  
  abstract MixinPreProcessorStandard createPreProcessor(MixinInfo$MixinClassNode paramMixinInfo$MixinClassNode);
  
  static MixinInfo$SubType getTypeFor(MixinInfo paramMixinInfo) {
    if (!paramMixinInfo.getClassInfo().isInterface())
      return new MixinInfo$SubType$Standard(paramMixinInfo); 
    int i = 0;
    for (ClassInfo$Method classInfo$Method : paramMixinInfo.getClassInfo().getMethods())
      i |= !classInfo$Method.isAccessor() ? 1 : 0; 
    return new MixinInfo$SubType$Accessor(paramMixinInfo);
  }
}
