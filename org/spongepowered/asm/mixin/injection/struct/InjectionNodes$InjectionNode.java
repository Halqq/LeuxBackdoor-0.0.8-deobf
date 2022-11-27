package org.spongepowered.asm.mixin.injection.struct;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.util.Bytecode;

public class InjectionNodes$InjectionNode implements Comparable {
  private static int nextId = 0;
  
  private final int id;
  
  private final AbstractInsnNode originalTarget;
  
  private AbstractInsnNode currentTarget;
  
  private Map decorations;
  
  public InjectionNodes$InjectionNode(AbstractInsnNode paramAbstractInsnNode) {
    this.currentTarget = this.originalTarget = paramAbstractInsnNode;
    this.id = nextId++;
  }
  
  public int getId() {
    return this.id;
  }
  
  public AbstractInsnNode getOriginalTarget() {
    return this.originalTarget;
  }
  
  public AbstractInsnNode getCurrentTarget() {
    return this.currentTarget;
  }
  
  public InjectionNodes$InjectionNode replace(AbstractInsnNode paramAbstractInsnNode) {
    this.currentTarget = paramAbstractInsnNode;
    return this;
  }
  
  public InjectionNodes$InjectionNode remove() {
    this.currentTarget = null;
    return this;
  }
  
  public boolean matches(AbstractInsnNode paramAbstractInsnNode) {
    return (this.originalTarget == paramAbstractInsnNode || this.currentTarget == paramAbstractInsnNode);
  }
  
  public boolean isReplaced() {
    return (this.originalTarget != this.currentTarget);
  }
  
  public boolean isRemoved() {
    return (this.currentTarget == null);
  }
  
  public InjectionNodes$InjectionNode decorate(String paramString, Object paramObject) {
    if (this.decorations == null)
      this.decorations = new HashMap<Object, Object>(); 
    this.decorations.put(paramString, paramObject);
    return this;
  }
  
  public boolean hasDecoration(String paramString) {
    return (this.decorations != null && this.decorations.get(paramString) != null);
  }
  
  public Object getDecoration(String paramString) {
    return (this.decorations == null) ? null : this.decorations.get(paramString);
  }
  
  public int compareTo(InjectionNodes$InjectionNode paramInjectionNodes$InjectionNode) {}
  
  public String toString() {
    return String.format("InjectionNode[%s]", new Object[] { Bytecode.describeNode(this.currentTarget).replaceAll("\\s+", " ") });
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((InjectionNodes$InjectionNode)paramObject);
  }
}
