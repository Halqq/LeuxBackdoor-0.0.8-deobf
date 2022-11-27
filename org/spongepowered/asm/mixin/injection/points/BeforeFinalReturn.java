package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;

@AtCode("TAIL")
public class BeforeFinalReturn extends InjectionPoint {
  private final IMixinContext context;
  
  public BeforeFinalReturn(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
    this.context = paramInjectionPointData.getContext();
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection paramCollection) {
    AbstractInsnNode abstractInsnNode = null;
    int i = Type.getReturnType(paramString).getOpcode(172);
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode1 = listIterator.next();
      if (abstractInsnNode1 instanceof org.spongepowered.asm.lib.tree.InsnNode && abstractInsnNode1.getOpcode() == i)
        abstractInsnNode = abstractInsnNode1; 
    } 
    throw new InvalidInjectionException(this.context, "TAIL could not locate a valid RETURN in the target method!");
  }
}
