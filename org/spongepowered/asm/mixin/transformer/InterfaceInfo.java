package org.spongepowered.asm.mixin.transformer;

import java.util.HashSet;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.transformer.meta.MixinRenamed;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.util.Annotations;

public final class InterfaceInfo {
  private final MixinInfo mixin;
  
  private final String prefix;
  
  private final Type iface;
  
  private final boolean unique;
  
  private Set methods;
  
  private InterfaceInfo(MixinInfo paramMixinInfo, String paramString, Type paramType, boolean paramBoolean) {
    if (paramString.length() < 2 || !paramString.endsWith("$"))
      throw new InvalidMixinException(paramMixinInfo, String.format("Prefix %s for iface %s is not valid", new Object[] { paramString, paramType.toString() })); 
    this.mixin = paramMixinInfo;
    this.prefix = paramString;
    this.iface = paramType;
    this.unique = paramBoolean;
  }
  
  private void initMethods() {
    this.methods = new HashSet();
    readInterface(this.iface.getInternalName());
  }
  
  private void readInterface(String paramString) {
    ClassInfo classInfo = ClassInfo.forName(paramString);
    for (ClassInfo$Method classInfo$Method : classInfo.getMethods())
      this.methods.add(classInfo$Method.toString()); 
    for (String str : classInfo.getInterfaces())
      readInterface(str); 
  }
  
  public String getPrefix() {
    return this.prefix;
  }
  
  public Type getIface() {
    return this.iface;
  }
  
  public String getName() {
    return this.iface.getClassName();
  }
  
  public String getInternalName() {
    return this.iface.getInternalName();
  }
  
  public boolean isUnique() {
    return this.unique;
  }
  
  public boolean renameMethod(MethodNode paramMethodNode) {
    if (this.methods == null)
      initMethods(); 
    if (!paramMethodNode.name.startsWith(this.prefix)) {
      if (this.methods.contains(paramMethodNode.name + paramMethodNode.desc))
        decorateUniqueMethod(paramMethodNode); 
      return false;
    } 
    String str1 = paramMethodNode.name.substring(this.prefix.length());
    String str2 = str1 + paramMethodNode.desc;
    if (!this.methods.contains(str2))
      throw new InvalidMixinException(this.mixin, String.format("%s does not exist in target interface %s", new Object[] { str1, getName() })); 
    if ((paramMethodNode.access & 0x1) == 0)
      throw new InvalidMixinException(this.mixin, String.format("%s cannot implement %s because it is not visible", new Object[] { str1, getName() })); 
    Annotations.setVisible(paramMethodNode, MixinRenamed.class, new Object[] { "originalName", paramMethodNode.name, "isInterfaceMember", Boolean.valueOf(true) });
    decorateUniqueMethod(paramMethodNode);
    paramMethodNode.name = str1;
    return true;
  }
  
  private void decorateUniqueMethod(MethodNode paramMethodNode) {
    if (!this.unique)
      return; 
    if (Annotations.getVisible(paramMethodNode, Unique.class) == null) {
      Annotations.setVisible(paramMethodNode, Unique.class, new Object[0]);
      this.mixin.getClassInfo().findMethod(paramMethodNode).setUnique(true);
    } 
  }
  
  static InterfaceInfo fromAnnotation(MixinInfo paramMixinInfo, AnnotationNode paramAnnotationNode) {
    String str = (String)Annotations.getValue(paramAnnotationNode, "prefix");
    Type type = (Type)Annotations.getValue(paramAnnotationNode, "iface");
    Boolean bool = (Boolean)Annotations.getValue(paramAnnotationNode, "unique");
    throw new InvalidMixinException(paramMixinInfo, String.format("@Interface annotation on %s is missing a required parameter", new Object[] { paramMixinInfo }));
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (getClass() != paramObject.getClass())
      return false; 
    InterfaceInfo interfaceInfo = (InterfaceInfo)paramObject;
    return (this.mixin.equals(interfaceInfo.mixin) && this.prefix.equals(interfaceInfo.prefix) && this.iface.equals(interfaceInfo.iface));
  }
  
  public int hashCode() {
    null = this.mixin.hashCode();
    null = 31 * null + this.prefix.hashCode();
    return 31 * null + this.iface.hashCode();
  }
}
