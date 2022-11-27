package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.modify.LocalVariableDiscriminator;
import org.spongepowered.asm.mixin.injection.modify.ModifyVariableInjector;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

public class ModifyVariableInjectionInfo extends InjectionInfo {
  public ModifyVariableInjectionInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, AnnotationNode paramAnnotationNode) {
    super(paramMixinTargetContext, paramMethodNode, paramAnnotationNode);
  }
  
  protected Injector parseInjector(AnnotationNode paramAnnotationNode) {
    return (Injector)new ModifyVariableInjector(this, LocalVariableDiscriminator.parse(paramAnnotationNode));
  }
  
  protected String getDescription() {
    return "Variable modifier method";
  }
}
