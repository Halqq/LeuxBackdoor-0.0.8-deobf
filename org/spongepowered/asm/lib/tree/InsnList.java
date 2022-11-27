package org.spongepowered.asm.lib.tree;

import java.util.ListIterator;
import org.spongepowered.asm.lib.MethodVisitor;

public class InsnList {
  private int size;
  
  private AbstractInsnNode first;
  
  private AbstractInsnNode last;
  
  AbstractInsnNode[] cache;
  
  public int size() {
    return this.size;
  }
  
  public AbstractInsnNode getFirst() {
    return this.first;
  }
  
  public AbstractInsnNode getLast() {
    return this.last;
  }
  
  public AbstractInsnNode get(int paramInt) {
    if (paramInt >= this.size)
      throw new IndexOutOfBoundsException(); 
    if (this.cache == null)
      this.cache = toArray(); 
    return this.cache[paramInt];
  }
  
  public boolean contains(AbstractInsnNode paramAbstractInsnNode) {
    AbstractInsnNode abstractInsnNode = this.first;
    while (true) {
      if (abstractInsnNode != paramAbstractInsnNode)
        abstractInsnNode = abstractInsnNode.next; 
    } 
  }
  
  public int indexOf(AbstractInsnNode paramAbstractInsnNode) {
    if (this.cache == null)
      this.cache = toArray(); 
    return paramAbstractInsnNode.index;
  }
  
  public void accept(MethodVisitor paramMethodVisitor) {
    for (AbstractInsnNode abstractInsnNode = this.first;; abstractInsnNode = abstractInsnNode.next)
      abstractInsnNode.accept(paramMethodVisitor); 
  }
  
  public ListIterator iterator() {
    return iterator(0);
  }
  
  public ListIterator iterator(int paramInt) {
    return new InsnList$InsnListIterator(this, paramInt);
  }
  
  public AbstractInsnNode[] toArray() {
    byte b = 0;
    AbstractInsnNode abstractInsnNode = this.first;
    AbstractInsnNode[] arrayOfAbstractInsnNode = new AbstractInsnNode[this.size];
    while (true) {
      arrayOfAbstractInsnNode[b] = abstractInsnNode;
      abstractInsnNode.index = b++;
      abstractInsnNode = abstractInsnNode.next;
    } 
  }
  
  public void set(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    AbstractInsnNode abstractInsnNode1 = paramAbstractInsnNode1.next;
    paramAbstractInsnNode2.next = abstractInsnNode1;
    abstractInsnNode1.prev = paramAbstractInsnNode2;
    AbstractInsnNode abstractInsnNode2 = paramAbstractInsnNode1.prev;
    paramAbstractInsnNode2.prev = abstractInsnNode2;
    abstractInsnNode2.next = paramAbstractInsnNode2;
    if (this.cache != null) {
      int i = paramAbstractInsnNode1.index;
      this.cache[i] = paramAbstractInsnNode2;
      paramAbstractInsnNode2.index = i;
    } else {
      paramAbstractInsnNode2.index = 0;
    } 
    paramAbstractInsnNode1.index = -1;
    paramAbstractInsnNode1.prev = null;
    paramAbstractInsnNode1.next = null;
  }
  
  public void add(AbstractInsnNode paramAbstractInsnNode) {
    this.size++;
    if (this.last == null) {
      this.first = paramAbstractInsnNode;
      this.last = paramAbstractInsnNode;
    } else {
      this.last.next = paramAbstractInsnNode;
      paramAbstractInsnNode.prev = this.last;
    } 
    this.last = paramAbstractInsnNode;
    this.cache = null;
    paramAbstractInsnNode.index = 0;
  }
  
  public void add(InsnList paramInsnList) {
    if (paramInsnList.size == 0)
      return; 
    this.size += paramInsnList.size;
    if (this.last == null) {
      this.first = paramInsnList.first;
      this.last = paramInsnList.last;
    } else {
      AbstractInsnNode abstractInsnNode = paramInsnList.first;
      this.last.next = abstractInsnNode;
      abstractInsnNode.prev = this.last;
      this.last = paramInsnList.last;
    } 
    this.cache = null;
    paramInsnList.removeAll(false);
  }
  
  public void insert(AbstractInsnNode paramAbstractInsnNode) {
    this.size++;
    if (this.first == null) {
      this.first = paramAbstractInsnNode;
      this.last = paramAbstractInsnNode;
    } else {
      this.first.prev = paramAbstractInsnNode;
      paramAbstractInsnNode.next = this.first;
    } 
    this.first = paramAbstractInsnNode;
    this.cache = null;
    paramAbstractInsnNode.index = 0;
  }
  
  public void insert(InsnList paramInsnList) {
    if (paramInsnList.size == 0)
      return; 
    this.size += paramInsnList.size;
    if (this.first == null) {
      this.first = paramInsnList.first;
      this.last = paramInsnList.last;
    } else {
      AbstractInsnNode abstractInsnNode = paramInsnList.last;
      this.first.prev = abstractInsnNode;
      abstractInsnNode.next = this.first;
      this.first = paramInsnList.first;
    } 
    this.cache = null;
    paramInsnList.removeAll(false);
  }
  
  public void insert(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    this.size++;
    AbstractInsnNode abstractInsnNode = paramAbstractInsnNode1.next;
    this.last = paramAbstractInsnNode2;
    paramAbstractInsnNode1.next = paramAbstractInsnNode2;
    paramAbstractInsnNode2.next = abstractInsnNode;
    paramAbstractInsnNode2.prev = paramAbstractInsnNode1;
    this.cache = null;
    paramAbstractInsnNode2.index = 0;
  }
  
  public void insert(AbstractInsnNode paramAbstractInsnNode, InsnList paramInsnList) {
    if (paramInsnList.size == 0)
      return; 
    this.size += paramInsnList.size;
    AbstractInsnNode abstractInsnNode1 = paramInsnList.first;
    AbstractInsnNode abstractInsnNode2 = paramInsnList.last;
    AbstractInsnNode abstractInsnNode3 = paramAbstractInsnNode.next;
    this.last = abstractInsnNode2;
    paramAbstractInsnNode.next = abstractInsnNode1;
    abstractInsnNode2.next = abstractInsnNode3;
    abstractInsnNode1.prev = paramAbstractInsnNode;
    this.cache = null;
    paramInsnList.removeAll(false);
  }
  
  public void insertBefore(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    this.size++;
    AbstractInsnNode abstractInsnNode = paramAbstractInsnNode1.prev;
    this.first = paramAbstractInsnNode2;
    paramAbstractInsnNode1.prev = paramAbstractInsnNode2;
    paramAbstractInsnNode2.next = paramAbstractInsnNode1;
    paramAbstractInsnNode2.prev = abstractInsnNode;
    this.cache = null;
    paramAbstractInsnNode2.index = 0;
  }
  
  public void insertBefore(AbstractInsnNode paramAbstractInsnNode, InsnList paramInsnList) {
    if (paramInsnList.size == 0)
      return; 
    this.size += paramInsnList.size;
    AbstractInsnNode abstractInsnNode1 = paramInsnList.first;
    AbstractInsnNode abstractInsnNode2 = paramInsnList.last;
    AbstractInsnNode abstractInsnNode3 = paramAbstractInsnNode.prev;
    this.first = abstractInsnNode1;
    paramAbstractInsnNode.prev = abstractInsnNode2;
    abstractInsnNode2.next = paramAbstractInsnNode;
    abstractInsnNode1.prev = abstractInsnNode3;
    this.cache = null;
    paramInsnList.removeAll(false);
  }
  
  public void remove(AbstractInsnNode paramAbstractInsnNode) {
    this.size--;
    AbstractInsnNode abstractInsnNode1 = paramAbstractInsnNode.next;
    AbstractInsnNode abstractInsnNode2 = paramAbstractInsnNode.prev;
    this.first = null;
    this.last = null;
    this.cache = null;
    paramAbstractInsnNode.index = -1;
    paramAbstractInsnNode.prev = null;
    paramAbstractInsnNode.next = null;
  }
  
  void removeAll(boolean paramBoolean) {
    paramBoolean = false;
    for (AbstractInsnNode abstractInsnNode = this.first;; abstractInsnNode = abstractInsnNode1) {
      AbstractInsnNode abstractInsnNode1 = abstractInsnNode.next;
      abstractInsnNode.index = -1;
      abstractInsnNode.prev = null;
      abstractInsnNode.next = null;
    } 
  }
  
  public void clear() {
    removeAll(false);
  }
  
  public void resetLabels() {
    for (AbstractInsnNode abstractInsnNode = this.first;; abstractInsnNode = abstractInsnNode.next) {
      if (abstractInsnNode instanceof LabelNode)
        ((LabelNode)abstractInsnNode).resetLabel(); 
    } 
  }
}
