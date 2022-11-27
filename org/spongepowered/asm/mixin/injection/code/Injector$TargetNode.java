package org.spongepowered.asm.mixin.injection.code;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;

public final class Injector$TargetNode {
  final AbstractInsnNode insn;
  
  final Set nominators = new HashSet();
  
  Injector$TargetNode(AbstractInsnNode paramAbstractInsnNode) {
    this.insn = paramAbstractInsnNode;
  }
  
  public AbstractInsnNode getNode() {
    return this.insn;
  }
  
  public Set getNominators() {
    return Collections.unmodifiableSet(this.nominators);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject.getClass() != Injector$TargetNode.class) ? false : ((((Injector$TargetNode)paramObject).insn == this.insn));
  }
  
  public int hashCode() {
    return this.insn.hashCode();
  }
}
