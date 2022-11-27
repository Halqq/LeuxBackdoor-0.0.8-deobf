package org.spongepowered.asm.mixin.transformer;

import java.util.List;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

class MixinInfo$SubType$Interface extends MixinInfo$SubType {
  MixinInfo$SubType$Interface(MixinInfo paramMixinInfo) {
    super(paramMixinInfo, "@Mixin", true);
  }
  
  void validate(MixinInfo$State paramMixinInfo$State, List paramList) {
    if (!MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces())
      throw new InvalidMixinException(this.mixin, "Interface mixin not supported in current enviromnment"); 
    MixinInfo$MixinClassNode mixinInfo$MixinClassNode = paramMixinInfo$State.getClassNode();
    if (!"java/lang/Object".equals(mixinInfo$MixinClassNode.superName))
      throw new InvalidMixinException(this.mixin, "Super class of " + this + " is invalid, found " + mixinInfo$MixinClassNode.superName.replace('/', '.')); 
  }
  
  MixinPreProcessorStandard createPreProcessor(MixinInfo$MixinClassNode paramMixinInfo$MixinClassNode) {
    return new MixinPreProcessorInterface(this.mixin, paramMixinInfo$MixinClassNode);
  }
}
