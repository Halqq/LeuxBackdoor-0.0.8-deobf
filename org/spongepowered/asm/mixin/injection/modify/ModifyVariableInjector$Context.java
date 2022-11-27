package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.struct.Target;

class ModifyVariableInjector$Context extends LocalVariableDiscriminator$Context {
  final InsnList insns = new InsnList();
  
  public ModifyVariableInjector$Context(Type paramType, boolean paramBoolean, Target paramTarget, AbstractInsnNode paramAbstractInsnNode) {
    super(paramType, paramBoolean, paramTarget, paramAbstractInsnNode);
  }
}
