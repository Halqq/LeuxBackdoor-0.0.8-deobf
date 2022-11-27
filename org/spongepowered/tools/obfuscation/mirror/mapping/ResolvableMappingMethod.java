package org.spongepowered.tools.obfuscation.mirror.mapping;

import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

public final class ResolvableMappingMethod extends MappingMethod {
  private final TypeHandle ownerHandle;
  
  public ResolvableMappingMethod(TypeHandle paramTypeHandle, String paramString1, String paramString2) {
    super(paramTypeHandle.getName(), paramString1, paramString2);
    this.ownerHandle = paramTypeHandle;
  }
  
  public MappingMethod getSuper() {
    if (this.ownerHandle == null)
      return super.getSuper(); 
    String str1 = getSimpleName();
    String str2 = getDesc();
    String str3 = TypeUtils.getJavaSignature(str2);
    TypeHandle typeHandle = this.ownerHandle.getSuperclass();
    if (typeHandle.findMethod(str1, str3) != null)
      return typeHandle.getMappingMethod(str1, str2); 
    for (TypeHandle typeHandle1 : this.ownerHandle.getInterfaces()) {
      if (typeHandle1.findMethod(str1, str3) != null)
        return typeHandle1.getMappingMethod(str1, str2); 
    } 
    return typeHandle.getMappingMethod(str1, str2).getSuper();
  }
  
  public MappingMethod move(TypeHandle paramTypeHandle) {
    return new ResolvableMappingMethod(paramTypeHandle, getSimpleName(), getDesc());
  }
  
  public MappingMethod remap(String paramString) {
    return new ResolvableMappingMethod(this.ownerHandle, paramString, getDesc());
  }
  
  public MappingMethod transform(String paramString) {
    return new ResolvableMappingMethod(this.ownerHandle, getSimpleName(), paramString);
  }
  
  public MappingMethod copy() {
    return new ResolvableMappingMethod(this.ownerHandle, getSimpleName(), getDesc());
  }
  
  public Object getSuper() {
    return getSuper();
  }
  
  public Object copy() {
    return copy();
  }
  
  public Object transform(String paramString) {
    return transform(paramString);
  }
  
  public Object remap(String paramString) {
    return remap(paramString);
  }
}
