package org.spongepowered.asm.mixin.transformer;

import java.util.Iterator;
import java.util.Map;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidInterfaceMixinException;

class MixinApplicatorInterface extends MixinApplicatorStandard {
  MixinApplicatorInterface(TargetClassContext paramTargetClassContext) {
    super(paramTargetClassContext);
  }
  
  protected void applyInterfaces(MixinTargetContext paramMixinTargetContext) {
    for (String str : paramMixinTargetContext.getInterfaces()) {
      if (!this.targetClass.name.equals(str) && !this.targetClass.interfaces.contains(str)) {
        this.targetClass.interfaces.add(str);
        paramMixinTargetContext.getTargetClassInfo().addInterface(str);
      } 
    } 
  }
  
  protected void applyFields(MixinTargetContext paramMixinTargetContext) {
    for (Map.Entry entry : paramMixinTargetContext.getShadowFields()) {
      FieldNode fieldNode = (FieldNode)entry.getKey();
      this.logger.error("Ignoring redundant @Shadow field {}:{} in {}", new Object[] { fieldNode.name, fieldNode.desc, paramMixinTargetContext });
    } 
    mergeNewFields(paramMixinTargetContext);
  }
  
  protected void applyInitialisers(MixinTargetContext paramMixinTargetContext) {}
  
  protected void prepareInjections(MixinTargetContext paramMixinTargetContext) {
    Iterator<MethodNode> iterator = this.targetClass.methods.iterator();
    if (iterator.hasNext()) {
      MethodNode methodNode = iterator.next();
      InjectionInfo injectionInfo = InjectionInfo.parse(paramMixinTargetContext, methodNode);
      throw new InvalidInterfaceMixinException(paramMixinTargetContext, injectionInfo + " is not supported on interface mixin method " + methodNode.name);
    } 
  }
  
  protected void applyInjections(MixinTargetContext paramMixinTargetContext) {}
}
