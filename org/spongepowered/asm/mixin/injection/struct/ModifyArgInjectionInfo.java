package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.invoke.ModifyArgInjector;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.util.Annotations;

public class ModifyArgInjectionInfo extends InjectionInfo {
  public ModifyArgInjectionInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, AnnotationNode paramAnnotationNode) {
    super(paramMixinTargetContext, paramMethodNode, paramAnnotationNode);
  }
  
  protected Injector parseInjector(AnnotationNode paramAnnotationNode) {
    int i = ((Integer)Annotations.getValue(paramAnnotationNode, "index", Integer.valueOf(-1))).intValue();
    return (Injector)new ModifyArgInjector(this, i);
  }
  
  protected String getDescription() {
    return "Argument modifier method";
  }
}
