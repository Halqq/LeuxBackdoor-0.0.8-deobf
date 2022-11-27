package org.spongepowered.asm.mixin.transformer;

abstract class ClassInfo$Member {
  private final ClassInfo$Member$Type type;
  
  private final String memberName;
  
  private final String memberDesc;
  
  private final boolean isInjected;
  
  private final int modifiers;
  
  private String currentName;
  
  private String currentDesc;
  
  private boolean decoratedFinal;
  
  private boolean decoratedMutable;
  
  private boolean unique;
  
  protected ClassInfo$Member(ClassInfo$Member paramClassInfo$Member) {
    this(paramClassInfo$Member.type, paramClassInfo$Member.memberName, paramClassInfo$Member.memberDesc, paramClassInfo$Member.modifiers, paramClassInfo$Member.isInjected);
    this.currentName = paramClassInfo$Member.currentName;
    this.currentDesc = paramClassInfo$Member.currentDesc;
    this.unique = paramClassInfo$Member.unique;
  }
  
  protected ClassInfo$Member(ClassInfo$Member$Type paramClassInfo$Member$Type, String paramString1, String paramString2, int paramInt) {
    this(paramClassInfo$Member$Type, paramString1, paramString2, paramInt, false);
  }
  
  protected ClassInfo$Member(ClassInfo$Member$Type paramClassInfo$Member$Type, String paramString1, String paramString2, int paramInt, boolean paramBoolean) {
    this.type = paramClassInfo$Member$Type;
    this.memberName = paramString1;
    this.memberDesc = paramString2;
    this.isInjected = paramBoolean;
    this.currentName = paramString1;
    this.currentDesc = paramString2;
    this.modifiers = paramInt;
  }
  
  public String getOriginalName() {
    return this.memberName;
  }
  
  public String getName() {
    return this.currentName;
  }
  
  public String getOriginalDesc() {
    return this.memberDesc;
  }
  
  public String getDesc() {
    return this.currentDesc;
  }
  
  public boolean isInjected() {
    return this.isInjected;
  }
  
  public boolean isRenamed() {
    return !this.currentName.equals(this.memberName);
  }
  
  public boolean isRemapped() {
    return !this.currentDesc.equals(this.memberDesc);
  }
  
  public boolean isPrivate() {
    return ((this.modifiers & 0x2) != 0);
  }
  
  public boolean isStatic() {
    return ((this.modifiers & 0x8) != 0);
  }
  
  public boolean isAbstract() {
    return ((this.modifiers & 0x400) != 0);
  }
  
  public boolean isFinal() {
    return ((this.modifiers & 0x10) != 0);
  }
  
  public boolean isSynthetic() {
    return ((this.modifiers & 0x1000) != 0);
  }
  
  public boolean isUnique() {
    return this.unique;
  }
  
  public void setUnique(boolean paramBoolean) {
    this.unique = paramBoolean;
  }
  
  public boolean isDecoratedFinal() {
    return this.decoratedFinal;
  }
  
  public boolean isDecoratedMutable() {
    return this.decoratedMutable;
  }
  
  public void setDecoratedFinal(boolean paramBoolean1, boolean paramBoolean2) {
    this.decoratedFinal = paramBoolean1;
    this.decoratedMutable = paramBoolean2;
  }
  
  public boolean matchesFlags(int paramInt) {
    return (((this.modifiers ^ 0xFFFFFFFF | paramInt & 0x2) & 0x2) != 0 && ((this.modifiers ^ 0xFFFFFFFF | paramInt & 0x8) & 0x8) != 0);
  }
  
  public abstract ClassInfo getOwner();
  
  public ClassInfo getImplementor() {
    return getOwner();
  }
  
  public int getAccess() {
    return this.modifiers;
  }
  
  public String renameTo(String paramString) {
    this.currentName = paramString;
    return paramString;
  }
  
  public String remapTo(String paramString) {
    this.currentDesc = paramString;
    return paramString;
  }
  
  public boolean equals(String paramString1, String paramString2) {
    return ((this.memberName.equals(paramString1) || this.currentName.equals(paramString1)) && (this.memberDesc.equals(paramString2) || this.currentDesc.equals(paramString2)));
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof ClassInfo$Member))
      return false; 
    ClassInfo$Member classInfo$Member = (ClassInfo$Member)paramObject;
    return ((classInfo$Member.memberName.equals(this.memberName) || classInfo$Member.currentName.equals(this.currentName)) && (classInfo$Member.memberDesc.equals(this.memberDesc) || classInfo$Member.currentDesc.equals(this.currentDesc)));
  }
  
  public int hashCode() {
    return toString().hashCode();
  }
  
  public String toString() {
    return String.format(getDisplayFormat(), new Object[] { this.memberName, this.memberDesc });
  }
  
  protected String getDisplayFormat() {
    return "%s%s";
  }
}
