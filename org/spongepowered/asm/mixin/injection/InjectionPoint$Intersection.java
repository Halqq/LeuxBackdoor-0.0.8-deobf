package org.spongepowered.asm.mixin.injection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;

final class InjectionPoint$Intersection extends InjectionPoint$CompositeInjectionPoint {
  public InjectionPoint$Intersection(InjectionPoint... paramVarArgs) {
    super(paramVarArgs);
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection<AbstractInsnNode> paramCollection) {
    boolean bool = false;
    ArrayList[] arrayOfArrayList = (ArrayList[])Array.newInstance(ArrayList.class, this.components.length);
    for (byte b1 = 0; b1 < this.components.length; b1++) {
      arrayOfArrayList[b1] = new ArrayList();
      this.components[b1].find(paramString, paramInsnList, arrayOfArrayList[b1]);
    } 
    ArrayList<AbstractInsnNode> arrayList = arrayOfArrayList[0];
    for (byte b2 = 0; b2 < arrayList.size(); b2++) {
      AbstractInsnNode abstractInsnNode = arrayList.get(b2);
      boolean bool1 = true;
      for (byte b = 1; b < arrayOfArrayList.length && arrayOfArrayList[b].contains(abstractInsnNode); b++);
      paramCollection.add(abstractInsnNode);
      bool = true;
    } 
    return bool;
  }
}
