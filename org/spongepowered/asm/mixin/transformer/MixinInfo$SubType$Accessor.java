package org.spongepowered.asm.mixin.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

class MixinInfo$SubType$Accessor extends MixinInfo$SubType {
  private final Collection interfaces = new ArrayList();
  
  MixinInfo$SubType$Accessor(MixinInfo paramMixinInfo) {
    super(paramMixinInfo, "@Mixin", false);
    this.interfaces.add(paramMixinInfo.getClassRef());
  }
  
  boolean isLoadable() {
    return true;
  }
  
  Collection getInterfaces() {
    return this.interfaces;
  }
  
  void validateTarget(String paramString, ClassInfo paramClassInfo) {
    boolean bool = paramClassInfo.isInterface();
    if (!MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces())
      throw new InvalidMixinException(this.mixin, "Accessor mixin targetting an interface is not supported in current enviromnment"); 
  }
  
  void validate(MixinInfo$State paramMixinInfo$State, List paramList) {
    MixinInfo$MixinClassNode mixinInfo$MixinClassNode = paramMixinInfo$State.getClassNode();
    if (!"java/lang/Object".equals(mixinInfo$MixinClassNode.superName))
      throw new InvalidMixinException(this.mixin, "Super class of " + this + " is invalid, found " + mixinInfo$MixinClassNode.superName.replace('/', '.')); 
  }
  
  MixinPreProcessorStandard createPreProcessor(MixinInfo$MixinClassNode paramMixinInfo$MixinClassNode) {
    return new MixinPreProcessorAccessor(this.mixin, paramMixinInfo$MixinClassNode);
  }
}
