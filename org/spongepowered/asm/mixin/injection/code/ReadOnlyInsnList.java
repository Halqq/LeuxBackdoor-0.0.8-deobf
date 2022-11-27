package org.spongepowered.asm.mixin.injection.code;

import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;

class ReadOnlyInsnList extends InsnList {
  private InsnList insnList;
  
  public ReadOnlyInsnList(InsnList paramInsnList) {
    this.insnList = paramInsnList;
  }
  
  void dispose() {
    this.insnList = null;
  }
  
  public final void set(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    throw new UnsupportedOperationException();
  }
  
  public final void add(AbstractInsnNode paramAbstractInsnNode) {
    throw new UnsupportedOperationException();
  }
  
  public final void add(InsnList paramInsnList) {
    throw new UnsupportedOperationException();
  }
  
  public final void insert(AbstractInsnNode paramAbstractInsnNode) {
    throw new UnsupportedOperationException();
  }
  
  public final void insert(InsnList paramInsnList) {
    throw new UnsupportedOperationException();
  }
  
  public final void insert(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    throw new UnsupportedOperationException();
  }
  
  public final void insert(AbstractInsnNode paramAbstractInsnNode, InsnList paramInsnList) {
    throw new UnsupportedOperationException();
  }
  
  public final void insertBefore(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    throw new UnsupportedOperationException();
  }
  
  public final void insertBefore(AbstractInsnNode paramAbstractInsnNode, InsnList paramInsnList) {
    throw new UnsupportedOperationException();
  }
  
  public final void remove(AbstractInsnNode paramAbstractInsnNode) {
    throw new UnsupportedOperationException();
  }
  
  public AbstractInsnNode[] toArray() {
    return this.insnList.toArray();
  }
  
  public int size() {
    return this.insnList.size();
  }
  
  public AbstractInsnNode getFirst() {
    return this.insnList.getFirst();
  }
  
  public AbstractInsnNode getLast() {
    return this.insnList.getLast();
  }
  
  public AbstractInsnNode get(int paramInt) {
    return this.insnList.get(paramInt);
  }
  
  public boolean contains(AbstractInsnNode paramAbstractInsnNode) {
    return this.insnList.contains(paramAbstractInsnNode);
  }
  
  public int indexOf(AbstractInsnNode paramAbstractInsnNode) {
    return this.insnList.indexOf(paramAbstractInsnNode);
  }
  
  public ListIterator iterator() {
    return this.insnList.iterator();
  }
  
  public ListIterator iterator(int paramInt) {
    return this.insnList.iterator(paramInt);
  }
  
  public final void resetLabels() {
    this.insnList.resetLabels();
  }
}
