package org.spongepowered.asm.mixin.transformer;

public class ClassInfo$InterfaceMethod extends ClassInfo$Method {
  private final ClassInfo owner;
  
  final ClassInfo this$0;
  
  public ClassInfo$InterfaceMethod(ClassInfo$Member paramClassInfo$Member) {
    super(paramClassInfo, paramClassInfo$Member);
    this.owner = paramClassInfo$Member.getOwner();
  }
  
  public ClassInfo getOwner() {
    return this.owner;
  }
  
  public ClassInfo getImplementor() {
    return ClassInfo.this;
  }
}
