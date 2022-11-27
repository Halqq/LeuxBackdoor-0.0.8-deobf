package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@AtCode("INVOKE_ASSIGN")
public class AfterInvoke extends BeforeInvoke {
  public AfterInvoke(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
  }
  
  protected boolean addInsn(InsnList paramInsnList, Collection<AbstractInsnNode> paramCollection, AbstractInsnNode paramAbstractInsnNode) {
    MethodInsnNode methodInsnNode = (MethodInsnNode)paramAbstractInsnNode;
    if (Type.getReturnType(methodInsnNode.desc) == Type.VOID_TYPE)
      return false; 
    paramAbstractInsnNode = InjectionPoint.nextNode(paramInsnList, paramAbstractInsnNode);
    if (paramAbstractInsnNode instanceof org.spongepowered.asm.lib.tree.VarInsnNode && paramAbstractInsnNode.getOpcode() >= 54)
      paramAbstractInsnNode = InjectionPoint.nextNode(paramInsnList, paramAbstractInsnNode); 
    paramCollection.add(paramAbstractInsnNode);
    return true;
  }
}
