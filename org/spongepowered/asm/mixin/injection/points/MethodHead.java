package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@AtCode("HEAD")
public class MethodHead extends InjectionPoint {
  public MethodHead(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection<AbstractInsnNode> paramCollection) {
    paramCollection.add(paramInsnList.getFirst());
    return true;
  }
}
