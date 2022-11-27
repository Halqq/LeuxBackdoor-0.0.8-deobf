package org.spongepowered.asm.mixin.transformer;

import java.util.List;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

class MixinInfo$SubType$Standard extends MixinInfo$SubType {
  MixinInfo$SubType$Standard(MixinInfo paramMixinInfo) {
    super(paramMixinInfo, "@Mixin", false);
  }
  
  void validate(MixinInfo$State paramMixinInfo$State, List paramList) {
    MixinInfo$MixinClassNode mixinInfo$MixinClassNode = paramMixinInfo$State.getClassNode();
    for (ClassInfo classInfo : paramList) {
      if (mixinInfo$MixinClassNode.superName.equals(classInfo.getSuperName()))
        continue; 
      if (!classInfo.hasSuperClass(mixinInfo$MixinClassNode.superName, ClassInfo$Traversal.SUPER)) {
        ClassInfo classInfo1 = ClassInfo.forName(mixinInfo$MixinClassNode.superName);
        if (classInfo1.isMixin())
          for (ClassInfo classInfo2 : classInfo1.getTargets()) {
            if (paramList.contains(classInfo2))
              throw new InvalidMixinException(this.mixin, "Illegal hierarchy detected. Derived mixin " + this + " targets the same class " + classInfo2.getClassName() + " as its superclass " + classInfo1.getClassName()); 
          }  
        throw new InvalidMixinException(this.mixin, "Super class '" + mixinInfo$MixinClassNode.superName.replace('/', '.') + "' of " + this.mixin.getName() + " was not found in the hierarchy of target class '" + classInfo + "'");
      } 
      this.detached = true;
    } 
  }
  
  MixinPreProcessorStandard createPreProcessor(MixinInfo$MixinClassNode paramMixinInfo$MixinClassNode) {
    return new MixinPreProcessorStandard(this.mixin, paramMixinInfo$MixinClassNode);
  }
}
