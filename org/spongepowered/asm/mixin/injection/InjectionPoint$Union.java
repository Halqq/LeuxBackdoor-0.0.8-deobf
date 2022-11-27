package org.spongepowered.asm.mixin.injection;

import java.util.Collection;
import java.util.LinkedHashSet;
import org.spongepowered.asm.lib.tree.InsnList;

final class InjectionPoint$Union extends InjectionPoint$CompositeInjectionPoint {
  public InjectionPoint$Union(InjectionPoint... paramVarArgs) {
    super(paramVarArgs);
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection paramCollection) {
    LinkedHashSet linkedHashSet = new LinkedHashSet();
    for (byte b = 0; b < this.components.length; b++)
      this.components[b].find(paramString, paramInsnList, linkedHashSet); 
    paramCollection.addAll(linkedHashSet);
    return (linkedHashSet.size() > 0);
  }
}
