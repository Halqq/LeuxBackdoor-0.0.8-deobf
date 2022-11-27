package org.spongepowered.asm.mixin.transformer;

public enum ClassInfo$Traversal {
  NONE(null, false, ClassInfo$SearchType.SUPER_CLASSES_ONLY),
  ALL(null, true, ClassInfo$SearchType.ALL_CLASSES),
  IMMEDIATE(NONE, true, ClassInfo$SearchType.SUPER_CLASSES_ONLY),
  SUPER(ALL, false, ClassInfo$SearchType.SUPER_CLASSES_ONLY);
  
  private final ClassInfo$Traversal next;
  
  private final boolean traverse;
  
  private final ClassInfo$SearchType searchType;
  
  private static final ClassInfo$Traversal[] $VALUES = new ClassInfo$Traversal[] { NONE, ALL, IMMEDIATE, SUPER };
  
  public ClassInfo$Traversal next() {
    return this.next;
  }
  
  public boolean canTraverse() {
    return this.traverse;
  }
  
  public ClassInfo$SearchType getSearchType() {
    return this.searchType;
  }
}
