package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collection;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;

class BeforeLoadLocal$SearchState {
  private final boolean print;
  
  private final int targetOrdinal;
  
  private int ordinal = 0;
  
  private boolean pendingCheck = false;
  
  private boolean found = false;
  
  private VarInsnNode varNode;
  
  BeforeLoadLocal$SearchState(int paramInt, boolean paramBoolean) {
    this.targetOrdinal = paramInt;
    this.print = paramBoolean;
  }
  
  boolean success() {
    return this.found;
  }
  
  boolean isPendingCheck() {
    return this.pendingCheck;
  }
  
  void setPendingCheck() {
    this.pendingCheck = true;
  }
  
  void register(VarInsnNode paramVarInsnNode) {
    this.varNode = paramVarInsnNode;
  }
  
  void check(Collection<AbstractInsnNode> paramCollection, AbstractInsnNode paramAbstractInsnNode, int paramInt) {
    this.pendingCheck = false;
    if (paramInt != this.varNode.var && (paramInt > -2 || !this.print))
      return; 
    if (this.targetOrdinal == -1 || this.targetOrdinal == this.ordinal) {
      paramCollection.add(paramAbstractInsnNode);
      this.found = true;
    } 
    this.ordinal++;
    this.varNode = null;
  }
}
