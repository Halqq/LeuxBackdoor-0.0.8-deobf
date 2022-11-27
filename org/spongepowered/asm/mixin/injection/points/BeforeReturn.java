package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@AtCode("RETURN")
public class BeforeReturn extends InjectionPoint {
  private final int ordinal;
  
  public BeforeReturn(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
    this.ordinal = paramInjectionPointData.getOrdinal();
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection<AbstractInsnNode> paramCollection) {
    boolean bool = false;
    int i = Type.getReturnType(paramString).getOpcode(172);
    byte b = 0;
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof org.spongepowered.asm.lib.tree.InsnNode && abstractInsnNode.getOpcode() == i) {
        if (this.ordinal == -1 || this.ordinal == b) {
          paramCollection.add(abstractInsnNode);
          bool = true;
        } 
        b++;
      } 
    } 
    return bool;
  }
}
