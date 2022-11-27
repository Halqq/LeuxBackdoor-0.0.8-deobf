package org.spongepowered.asm.lib.tree;

import java.util.ListIterator;
import java.util.NoSuchElementException;

final class InsnList$InsnListIterator implements ListIterator {
  AbstractInsnNode next;
  
  AbstractInsnNode prev;
  
  AbstractInsnNode remove;
  
  final InsnList this$0;
  
  InsnList$InsnListIterator(int paramInt) {
    if (paramInt == paramInsnList.size()) {
      this.next = null;
      this.prev = paramInsnList.getLast();
    } else {
      this.next = paramInsnList.get(paramInt);
      this.prev = this.next.prev;
    } 
  }
  
  public boolean hasNext() {
    return (this.next != null);
  }
  
  public Object next() {
    if (this.next == null)
      throw new NoSuchElementException(); 
    AbstractInsnNode abstractInsnNode = this.next;
    this.prev = abstractInsnNode;
    this.next = abstractInsnNode.next;
    this.remove = abstractInsnNode;
    return abstractInsnNode;
  }
  
  public void remove() {
    if (this.remove != null) {
      if (this.remove == this.next) {
        this.next = this.next.next;
      } else {
        this.prev = this.prev.prev;
      } 
      InsnList.this.remove(this.remove);
      this.remove = null;
      return;
    } 
    throw new IllegalStateException();
  }
  
  public boolean hasPrevious() {
    return (this.prev != null);
  }
  
  public Object previous() {
    AbstractInsnNode abstractInsnNode = this.prev;
    this.next = abstractInsnNode;
    this.prev = abstractInsnNode.prev;
    this.remove = abstractInsnNode;
    return abstractInsnNode;
  }
  
  public int nextIndex() {
    if (this.next == null)
      return InsnList.this.size(); 
    if (InsnList.this.cache == null)
      InsnList.this.cache = InsnList.this.toArray(); 
    return this.next.index;
  }
  
  public int previousIndex() {
    if (this.prev == null)
      return -1; 
    if (InsnList.this.cache == null)
      InsnList.this.cache = InsnList.this.toArray(); 
    return this.prev.index;
  }
  
  public void add(Object paramObject) {
    if (this.next != null) {
      InsnList.this.insertBefore(this.next, (AbstractInsnNode)paramObject);
    } else if (this.prev != null) {
      InsnList.this.insert(this.prev, (AbstractInsnNode)paramObject);
    } else {
      InsnList.this.add((AbstractInsnNode)paramObject);
    } 
    this.prev = (AbstractInsnNode)paramObject;
    this.remove = null;
  }
  
  public void set(Object paramObject) {
    if (this.remove != null) {
      InsnList.this.set(this.remove, (AbstractInsnNode)paramObject);
      if (this.remove == this.prev) {
        this.prev = (AbstractInsnNode)paramObject;
      } else {
        this.next = (AbstractInsnNode)paramObject;
      } 
    } else {
      throw new IllegalStateException();
    } 
  }
}
