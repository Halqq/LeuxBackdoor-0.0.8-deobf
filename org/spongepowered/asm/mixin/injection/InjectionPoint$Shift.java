package org.spongepowered.asm.mixin.injection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;

final class InjectionPoint$Shift extends InjectionPoint {
  private final InjectionPoint input;
  
  private final int shift;
  
  public InjectionPoint$Shift(InjectionPoint paramInjectionPoint, int paramInt) {
    throw new IllegalArgumentException("Must supply an input injection point for SHIFT");
  }
  
  public String toString() {
    return "InjectionPoint(" + getClass().getSimpleName() + ")[" + this.input + "]";
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection<?> paramCollection) {
    List<AbstractInsnNode> list = (paramCollection instanceof List) ? (List)paramCollection : new ArrayList(paramCollection);
    this.input.find(paramString, paramInsnList, paramCollection);
    for (byte b = 0; b < list.size(); b++)
      list.set(b, paramInsnList.get(paramInsnList.indexOf(list.get(b)) + this.shift)); 
    if (paramCollection != list) {
      paramCollection.clear();
      paramCollection.addAll(list);
    } 
    return (paramCollection.size() > 0);
  }
}
