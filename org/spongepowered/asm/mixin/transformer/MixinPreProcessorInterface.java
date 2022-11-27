package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidInterfaceMixinException;
import org.spongepowered.asm.util.Bytecode;

class MixinPreProcessorInterface extends MixinPreProcessorStandard {
  MixinPreProcessorInterface(MixinInfo paramMixinInfo, MixinInfo$MixinClassNode paramMixinInfo$MixinClassNode) {
    super(paramMixinInfo, paramMixinInfo$MixinClassNode);
  }
  
  protected void prepareMethod(MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, ClassInfo$Method paramClassInfo$Method) {
    if (!Bytecode.hasFlag(paramMixinInfo$MixinMethodNode, 1) && !Bytecode.hasFlag(paramMixinInfo$MixinMethodNode, 4096))
      throw new InvalidInterfaceMixinException(this.mixin, "Interface mixin contains a non-public method! Found " + paramClassInfo$Method + " in " + this.mixin); 
    super.prepareMethod(paramMixinInfo$MixinMethodNode, paramClassInfo$Method);
  }
  
  protected boolean validateField(MixinTargetContext paramMixinTargetContext, FieldNode paramFieldNode, AnnotationNode paramAnnotationNode) {
    if (!Bytecode.hasFlag(paramFieldNode, 8))
      throw new InvalidInterfaceMixinException(this.mixin, "Interface mixin contains an instance field! Found " + paramFieldNode.name + " in " + this.mixin); 
    return super.validateField(paramMixinTargetContext, paramFieldNode, paramAnnotationNode);
  }
}
