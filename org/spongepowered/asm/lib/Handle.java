package org.spongepowered.asm.lib;

public final class Handle {
  final int tag;
  
  final String owner;
  
  final String name;
  
  final String desc;
  
  final boolean itf;
  
  @Deprecated
  @Deprecated
  public Handle(int paramInt, String paramString1, String paramString2, String paramString3) {
    this(paramInt, paramString1, paramString2, paramString3, (paramInt == 9));
  }
  
  public Handle(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    this.tag = paramInt;
    this.owner = paramString1;
    this.name = paramString2;
    this.desc = paramString3;
    this.itf = paramBoolean;
  }
  
  public int getTag() {
    return this.tag;
  }
  
  public String getOwner() {
    return this.owner;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDesc() {
    return this.desc;
  }
  
  public boolean isInterface() {
    return this.itf;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof Handle))
      return false; 
    Handle handle = (Handle)paramObject;
    return (this.tag == handle.tag && this.itf == handle.itf && this.owner.equals(handle.owner) && this.name.equals(handle.name) && this.desc.equals(handle.desc));
  }
  
  public int hashCode() {
    return this.tag + (this.itf ? 64 : 0) + this.owner.hashCode() * this.name.hashCode() * this.desc.hashCode();
  }
  
  public String toString() {
    return this.owner + '.' + this.name + this.desc + " (" + this.tag + (this.itf ? " itf" : "") + ')';
  }
}
