package org.spongepowered.asm.obfuscation.mapping.common;

import com.google.common.base.Objects;
import org.spongepowered.asm.obfuscation.mapping.IMapping;

public class MappingMethod implements IMapping {
  private final String owner;
  
  private final String name;
  
  private final String desc;
  
  public MappingMethod(String paramString1, String paramString2) {
    this(getOwnerFromName(paramString1), getBaseName(paramString1), paramString2);
  }
  
  public MappingMethod(String paramString1, String paramString2, String paramString3) {
    this.owner = paramString1;
    this.name = paramString2;
    this.desc = paramString3;
  }
  
  public IMapping.Type getType() {
    return IMapping.Type.METHOD;
  }
  
  public String getName() {
    return (this.name == null) ? null : (((this.owner != null) ? (this.owner + "/") : "") + this.name);
  }
  
  public String getSimpleName() {
    return this.name;
  }
  
  public String getOwner() {
    return this.owner;
  }
  
  public String getDesc() {
    return this.desc;
  }
  
  public MappingMethod getSuper() {
    return null;
  }
  
  public boolean isConstructor() {
    return "<init>".equals(this.name);
  }
  
  public MappingMethod move(String paramString) {
    return new MappingMethod(paramString, getSimpleName(), getDesc());
  }
  
  public MappingMethod remap(String paramString) {
    return new MappingMethod(getOwner(), paramString, getDesc());
  }
  
  public MappingMethod transform(String paramString) {
    return new MappingMethod(getOwner(), getSimpleName(), paramString);
  }
  
  public MappingMethod copy() {
    return new MappingMethod(getOwner(), getSimpleName(), getDesc());
  }
  
  public MappingMethod addPrefix(String paramString) {
    String str = getSimpleName();
    return str.startsWith(paramString) ? this : new MappingMethod(getOwner(), paramString + str, getDesc());
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { getName(), getDesc() });
  }
  
  public boolean equals(Object paramObject) {
    return (this == paramObject) ? true : ((paramObject instanceof MappingMethod) ? ((Objects.equal(this.name, ((MappingMethod)paramObject).name) && Objects.equal(this.desc, ((MappingMethod)paramObject).desc))) : false);
  }
  
  public String serialise() {
    return toString();
  }
  
  public String toString() {
    String str = getDesc();
    (new Object[3])[0] = getName();
  }
  
  private static String getBaseName(String paramString) {
    return null;
  }
  
  private static String getOwnerFromName(String paramString) {
    return null;
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
  
  public Object move(String paramString) {
    return move(paramString);
  }
}
