package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.invoke.RedirectInjector;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;

public class RedirectInjectionInfo extends InjectionInfo {
  public RedirectInjectionInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, AnnotationNode paramAnnotationNode) {
    super(paramMixinTargetContext, paramMethodNode, paramAnnotationNode);
  }
  
  protected Injector parseInjector(AnnotationNode paramAnnotationNode) {
    return (Injector)new RedirectInjector(this);
  }
  
  protected String getDescription() {
    return "Redirector";
  }
}
