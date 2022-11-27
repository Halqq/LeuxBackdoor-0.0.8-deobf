package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;

class MethodNode$1 extends ArrayList {
  final MethodNode this$0;
  
  MethodNode$1(int paramInt) {
    super(paramInt);
  }
  
  public boolean add(Object paramObject) {
    MethodNode.this.annotationDefault = paramObject;
    return super.add(paramObject);
  }
}
