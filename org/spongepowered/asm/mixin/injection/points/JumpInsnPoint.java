package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;

@AtCode("JUMP")
public class JumpInsnPoint extends InjectionPoint {
  private final int opCode;
  
  private final int ordinal;
  
  public JumpInsnPoint(InjectionPointData paramInjectionPointData) {
    this.opCode = paramInjectionPointData.getOpcode(-1, new int[] { 
          153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 
          163, 164, 165, 166, 167, 168, 198, 199, -1 });
    this.ordinal = paramInjectionPointData.getOrdinal();
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection<AbstractInsnNode> paramCollection) {
    boolean bool = false;
    byte b = 0;
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof org.spongepowered.asm.lib.tree.JumpInsnNode && (this.opCode == -1 || abstractInsnNode.getOpcode() == this.opCode)) {
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
