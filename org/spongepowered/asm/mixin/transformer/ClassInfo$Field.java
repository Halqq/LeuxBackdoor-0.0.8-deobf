package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.util.Annotations;

class ClassInfo$Field extends ClassInfo$Member {
  final ClassInfo this$0;
  
  public ClassInfo$Field(ClassInfo$Member paramClassInfo$Member) {
    super(paramClassInfo$Member);
  }
  
  public ClassInfo$Field(FieldNode paramFieldNode) {
    this(paramFieldNode, false);
  }
  
  public ClassInfo$Field(FieldNode paramFieldNode, boolean paramBoolean) {
    super(ClassInfo$Member$Type.FIELD, paramFieldNode.name, paramFieldNode.desc, paramFieldNode.access, paramBoolean);
    setUnique((Annotations.getVisible(paramFieldNode, Unique.class) != null));
    if (Annotations.getVisible(paramFieldNode, Shadow.class) != null) {
      boolean bool1 = (Annotations.getVisible(paramFieldNode, Final.class) != null) ? true : false;
      boolean bool2 = (Annotations.getVisible(paramFieldNode, Mutable.class) != null) ? true : false;
      setDecoratedFinal(bool1, bool2);
    } 
  }
  
  public ClassInfo$Field(String paramString1, String paramString2, int paramInt) {
    super(ClassInfo$Member$Type.FIELD, paramString1, paramString2, paramInt, false);
  }
  
  public ClassInfo$Field(String paramString1, String paramString2, int paramInt, boolean paramBoolean) {
    super(ClassInfo$Member$Type.FIELD, paramString1, paramString2, paramInt, paramBoolean);
  }
  
  public ClassInfo getOwner() {
    return ClassInfo.this;
  }
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof ClassInfo$Field) ? false : super.equals(paramObject);
  }
  
  protected String getDisplayFormat() {
    return "%s:%s";
  }
}
