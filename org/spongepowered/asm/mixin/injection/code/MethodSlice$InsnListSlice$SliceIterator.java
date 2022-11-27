package org.spongepowered.asm.mixin.injection.code;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;

class MethodSlice$InsnListSlice$SliceIterator implements ListIterator {
  private final ListIterator iter;
  
  private int start;
  
  private int end;
  
  private int index;
  
  public MethodSlice$InsnListSlice$SliceIterator(ListIterator paramListIterator, int paramInt1, int paramInt2, int paramInt3) {
    this.iter = paramListIterator;
    this.start = paramInt1;
    this.end = paramInt2;
    this.index = paramInt3;
  }
  
  public boolean hasNext() {
    return (this.index <= this.end && this.iter.hasNext());
  }
  
  public AbstractInsnNode next() {
    if (this.index > this.end)
      throw new NoSuchElementException(); 
    this.index++;
    return this.iter.next();
  }
  
  public boolean hasPrevious() {
    return (this.index > this.start);
  }
  
  public AbstractInsnNode previous() {
    if (this.index <= this.start)
      throw new NoSuchElementException(); 
    this.index--;
    return this.iter.previous();
  }
  
  public int nextIndex() {
    return this.index - this.start;
  }
  
  public int previousIndex() {
    return this.index - this.start - 1;
  }
  
  public void remove() {
    throw new UnsupportedOperationException("Cannot remove insn from slice");
  }
  
  public void set(AbstractInsnNode paramAbstractInsnNode) {
    throw new UnsupportedOperationException("Cannot set insn using slice");
  }
  
  public void add(AbstractInsnNode paramAbstractInsnNode) {
    throw new UnsupportedOperationException("Cannot add insn using slice");
  }
  
  public void add(Object paramObject) {
    add((AbstractInsnNode)paramObject);
  }
  
  public void set(Object paramObject) {
    set((AbstractInsnNode)paramObject);
  }
  
  public Object previous() {
    return previous();
  }
  
  public Object next() {
    return next();
  }
}
