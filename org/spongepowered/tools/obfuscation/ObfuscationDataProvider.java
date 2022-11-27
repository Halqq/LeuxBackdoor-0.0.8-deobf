package org.spongepowered.tools.obfuscation;

import java.util.List;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationDataProvider;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public class ObfuscationDataProvider implements IObfuscationDataProvider {
  private final IMixinAnnotationProcessor ap;
  
  private final List environments;
  
  public ObfuscationDataProvider(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, List paramList) {
    this.ap = paramIMixinAnnotationProcessor;
    this.environments = paramList;
  }
  
  public ObfuscationData getObfEntryRecursive(MemberInfo paramMemberInfo) {
    MemberInfo memberInfo = paramMemberInfo;
    ObfuscationData obfuscationData1 = getObfClass(memberInfo.owner);
    ObfuscationData obfuscationData2 = getObfEntry(memberInfo);
    if (obfuscationData2.isEmpty()) {
      TypeHandle typeHandle = this.ap.getTypeProvider().getTypeHandle(memberInfo.owner);
      return obfuscationData2;
    } 
    return obfuscationData2;
  }
  
  private ObfuscationData getObfEntryUsing(MemberInfo paramMemberInfo, TypeHandle paramTypeHandle) {}
  
  public ObfuscationData getObfEntry(MemberInfo paramMemberInfo) {
    return paramMemberInfo.isField() ? getObfField(paramMemberInfo) : getObfMethod(paramMemberInfo.asMethodMapping());
  }
  
  public ObfuscationData getObfEntry(IMapping paramIMapping) {
    return (paramIMapping.getType() == IMapping.Type.FIELD) ? getObfField((MappingField)paramIMapping) : ((paramIMapping.getType() == IMapping.Type.METHOD) ? getObfMethod((MappingMethod)paramIMapping) : new ObfuscationData());
  }
  
  public ObfuscationData getObfMethodRecursive(MemberInfo paramMemberInfo) {
    return getObfEntryRecursive(paramMemberInfo);
  }
  
  public ObfuscationData getObfMethod(MemberInfo paramMemberInfo) {
    return getRemappedMethod(paramMemberInfo, paramMemberInfo.isConstructor());
  }
  
  public ObfuscationData getRemappedMethod(MemberInfo paramMemberInfo) {
    return getRemappedMethod(paramMemberInfo, true);
  }
  
  private ObfuscationData getRemappedMethod(MemberInfo paramMemberInfo, boolean paramBoolean) {
    ObfuscationData obfuscationData = new ObfuscationData();
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      MappingMethod mappingMethod = obfuscationEnvironment.getObfMethod(paramMemberInfo);
      obfuscationData.put(obfuscationEnvironment.getType(), mappingMethod);
    } 
    if (obfuscationData.isEmpty());
    return obfuscationData;
  }
  
  public ObfuscationData getObfMethod(MappingMethod paramMappingMethod) {
    return getRemappedMethod(paramMappingMethod, paramMappingMethod.isConstructor());
  }
  
  public ObfuscationData getRemappedMethod(MappingMethod paramMappingMethod) {
    return getRemappedMethod(paramMappingMethod, true);
  }
  
  private ObfuscationData getRemappedMethod(MappingMethod paramMappingMethod, boolean paramBoolean) {
    ObfuscationData obfuscationData = new ObfuscationData();
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      MappingMethod mappingMethod = obfuscationEnvironment.getObfMethod(paramMappingMethod);
      obfuscationData.put(obfuscationEnvironment.getType(), mappingMethod);
    } 
    if (obfuscationData.isEmpty());
    return obfuscationData;
  }
  
  public ObfuscationData remapDescriptor(ObfuscationData paramObfuscationData, MemberInfo paramMemberInfo) {
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      MemberInfo memberInfo = obfuscationEnvironment.remapDescriptor(paramMemberInfo);
      paramObfuscationData.put(obfuscationEnvironment.getType(), memberInfo.asMethodMapping());
    } 
    return paramObfuscationData;
  }
  
  public ObfuscationData getObfFieldRecursive(MemberInfo paramMemberInfo) {
    return getObfEntryRecursive(paramMemberInfo);
  }
  
  public ObfuscationData getObfField(MemberInfo paramMemberInfo) {
    return getObfField(paramMemberInfo.asFieldMapping());
  }
  
  public ObfuscationData getObfField(MappingField paramMappingField) {
    ObfuscationData obfuscationData = new ObfuscationData();
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      MappingField mappingField = obfuscationEnvironment.getObfField(paramMappingField);
      if (mappingField.getDesc() == null && paramMappingField.getDesc() != null)
        mappingField = mappingField.transform(obfuscationEnvironment.remapDescriptor(paramMappingField.getDesc())); 
      obfuscationData.put(obfuscationEnvironment.getType(), mappingField);
    } 
    return obfuscationData;
  }
  
  public ObfuscationData getObfClass(TypeHandle paramTypeHandle) {
    return getObfClass(paramTypeHandle.getName());
  }
  
  public ObfuscationData getObfClass(String paramString) {
    ObfuscationData obfuscationData = new ObfuscationData(paramString);
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      String str = obfuscationEnvironment.getObfClass(paramString);
      obfuscationData.put(obfuscationEnvironment.getType(), str);
    } 
    return obfuscationData;
  }
  
  private static ObfuscationData applyParents(ObfuscationData paramObfuscationData1, ObfuscationData paramObfuscationData2) {
    for (ObfuscationType obfuscationType : paramObfuscationData2) {
      String str = (String)paramObfuscationData1.get(obfuscationType);
      Object object = paramObfuscationData2.get(obfuscationType);
      paramObfuscationData2.put(obfuscationType, MemberInfo.fromMapping((IMapping)object).move(str).asMapping());
    } 
    return paramObfuscationData2;
  }
}
