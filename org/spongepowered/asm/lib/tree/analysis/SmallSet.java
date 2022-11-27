package org.spongepowered.asm.lib.tree.analysis;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

class SmallSet extends AbstractSet implements Iterator {
  Object e1;
  
  Object e2;
  
  static final Set emptySet() {
    return new SmallSet(null, null);
  }
  
  SmallSet(Object paramObject1, Object paramObject2) {
    this.e1 = paramObject1;
    this.e2 = paramObject2;
  }
  
  public Iterator iterator() {
    return new SmallSet(this.e1, this.e2);
  }
  
  public int size() {
    return (this.e1 == null) ? 0 : ((this.e2 == null) ? 1 : 2);
  }
  
  public boolean hasNext() {
    return (this.e1 != null);
  }
  
  public Object next() {
    if (this.e1 == null)
      throw new NoSuchElementException(); 
    Object object = this.e1;
    this.e1 = this.e2;
    this.e2 = null;
    return object;
  }
  
  public void remove() {}
  
  Set union(SmallSet paramSmallSet) {
    if ((paramSmallSet.e1 == this.e1 && paramSmallSet.e2 == this.e2) || (paramSmallSet.e1 == this.e2 && paramSmallSet.e2 == this.e1))
      return this; 
    if (paramSmallSet.e1 == null)
      return this; 
    if (this.e1 == null)
      return paramSmallSet; 
    if (paramSmallSet.e2 == null) {
      if (this.e2 == null)
        return new SmallSet(this.e1, paramSmallSet.e1); 
      if (paramSmallSet.e1 == this.e1 || paramSmallSet.e1 == this.e2)
        return this; 
    } 
    if (this.e2 == null && (this.e1 == paramSmallSet.e1 || this.e1 == paramSmallSet.e2))
      return paramSmallSet; 
    HashSet<Object> hashSet = new HashSet(4);
    hashSet.add(this.e1);
    if (this.e2 != null)
      hashSet.add(this.e2); 
    hashSet.add(paramSmallSet.e1);
    if (paramSmallSet.e2 != null)
      hashSet.add(paramSmallSet.e2); 
    return hashSet;
  }
}
