package org.spongepowered.asm.mixin.injection.struct;

import java.util.ArrayList;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;

public class InjectionNodes extends ArrayList {
  private static final long serialVersionUID = 1L;
  
  public InjectionNodes$InjectionNode add(AbstractInsnNode paramAbstractInsnNode) {
    InjectionNodes$InjectionNode injectionNodes$InjectionNode = get(paramAbstractInsnNode);
    injectionNodes$InjectionNode = new InjectionNodes$InjectionNode(paramAbstractInsnNode);
    add((E)injectionNodes$InjectionNode);
    return injectionNodes$InjectionNode;
  }
  
  public InjectionNodes$InjectionNode get(AbstractInsnNode paramAbstractInsnNode) {
    for (InjectionNodes$InjectionNode injectionNodes$InjectionNode : this) {
      if (injectionNodes$InjectionNode.matches(paramAbstractInsnNode))
        return injectionNodes$InjectionNode; 
    } 
    return null;
  }
  
  public boolean contains(AbstractInsnNode paramAbstractInsnNode) {
    return (get(paramAbstractInsnNode) != null);
  }
  
  public void replace(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    InjectionNodes$InjectionNode injectionNodes$InjectionNode = get(paramAbstractInsnNode1);
    injectionNodes$InjectionNode.replace(paramAbstractInsnNode2);
  }
  
  public void remove(AbstractInsnNode paramAbstractInsnNode) {
    InjectionNodes$InjectionNode injectionNodes$InjectionNode = get(paramAbstractInsnNode);
    injectionNodes$InjectionNode.remove();
  }
}
