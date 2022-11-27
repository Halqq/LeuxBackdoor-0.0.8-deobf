package org.spongepowered.asm.mixin.injection.code;

import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;

final class MethodSlice$InsnListSlice extends ReadOnlyInsnList {
  private final int start;
  
  private final int end;
  
  protected MethodSlice$InsnListSlice(InsnList paramInsnList, int paramInt1, int paramInt2) {
    super(paramInsnList);
    this.start = paramInt1;
    this.end = paramInt2;
  }
  
  public ListIterator iterator() {
    return iterator(0);
  }
  
  public ListIterator iterator(int paramInt) {
    paramInt = 0;
    return new MethodSlice$InsnListSlice$SliceIterator(super.iterator(this.start + paramInt), this.start, this.end, this.start + paramInt);
  }
  
  public AbstractInsnNode[] toArray() {
    AbstractInsnNode[] arrayOfAbstractInsnNode1 = super.toArray();
    AbstractInsnNode[] arrayOfAbstractInsnNode2 = new AbstractInsnNode[size()];
    System.arraycopy(arrayOfAbstractInsnNode1, this.start, arrayOfAbstractInsnNode2, 0, arrayOfAbstractInsnNode2.length);
    return arrayOfAbstractInsnNode2;
  }
  
  public int size() {
    return this.end - this.start + 1;
  }
  
  public AbstractInsnNode getFirst() {
    return super.get(this.start);
  }
  
  public AbstractInsnNode getLast() {
    return super.get(this.end);
  }
  
  public AbstractInsnNode get(int paramInt) {
    return super.get(this.start + paramInt);
  }
  
  public boolean contains(AbstractInsnNode paramAbstractInsnNode) {
    for (AbstractInsnNode abstractInsnNode : toArray()) {
      if (abstractInsnNode == paramAbstractInsnNode)
        return true; 
    } 
    return false;
  }
  
  public int indexOf(AbstractInsnNode paramAbstractInsnNode) {
    int i = super.indexOf(paramAbstractInsnNode);
    return (i >= this.start && i <= this.end) ? (i - this.start) : -1;
  }
  
  public int realIndexOf(AbstractInsnNode paramAbstractInsnNode) {
    return super.indexOf(paramAbstractInsnNode);
  }
}
