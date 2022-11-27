package org.spongepowered.asm.mixin.gen;

import java.util.ArrayList;
import org.spongepowered.asm.lib.tree.MethodNode;

public abstract class AccessorGenerator {
  protected final AccessorInfo info;
  
  public AccessorGenerator(AccessorInfo paramAccessorInfo) {
    this.info = paramAccessorInfo;
  }
  
  protected final MethodNode createMethod(int paramInt1, int paramInt2) {
    MethodNode methodNode1 = this.info.getMethod();
    MethodNode methodNode2 = new MethodNode(327680, methodNode1.access & 0xFFFFFBFF | 0x1000, methodNode1.name, methodNode1.desc, null, null);
    methodNode2.visibleAnnotations = new ArrayList();
    methodNode2.visibleAnnotations.add(this.info.getAnnotation());
    methodNode2.maxLocals = paramInt1;
    methodNode2.maxStack = paramInt2;
    return methodNode2;
  }
  
  public abstract MethodNode generate();
}
