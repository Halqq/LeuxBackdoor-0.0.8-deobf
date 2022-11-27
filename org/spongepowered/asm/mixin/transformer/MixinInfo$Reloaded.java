package org.spongepowered.asm.mixin.transformer;

import java.util.HashSet;
import java.util.List;
import org.spongepowered.asm.mixin.transformer.throwables.MixinReloadException;

class MixinInfo$Reloaded extends MixinInfo$State {
  private final MixinInfo$State previous;
  
  final MixinInfo this$0;
  
  MixinInfo$Reloaded(MixinInfo$State paramMixinInfo$State, byte[] paramArrayOfbyte) {
    super(paramMixinInfo, paramArrayOfbyte, paramMixinInfo$State.getClassInfo());
    this.previous = paramMixinInfo$State;
  }
  
  protected void validateChanges(MixinInfo$SubType paramMixinInfo$SubType, List<?> paramList) {
    if (!this.syntheticInnerClasses.equals(this.previous.syntheticInnerClasses))
      throw new MixinReloadException(MixinInfo.this, "Cannot change inner classes"); 
    if (!this.interfaces.equals(this.previous.interfaces))
      throw new MixinReloadException(MixinInfo.this, "Cannot change interfaces"); 
    if (!(new HashSet(this.softImplements)).equals(new HashSet(this.previous.softImplements)))
      throw new MixinReloadException(MixinInfo.this, "Cannot change soft interfaces"); 
    List<?> list = MixinInfo.this.readTargetClasses(this.classNode, true);
    if (!(new HashSet(list)).equals(new HashSet(paramList)))
      throw new MixinReloadException(MixinInfo.this, "Cannot change target classes"); 
    int i = MixinInfo.this.readPriority(this.classNode);
    if (i != MixinInfo.this.getPriority())
      throw new MixinReloadException(MixinInfo.this, "Cannot change mixin priority"); 
  }
}
