package org.spongepowered.tools.obfuscation.interfaces;

import java.util.Collection;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;

public interface IObfuscationEnvironment {
  MappingMethod getObfMethod(MemberInfo paramMemberInfo);
  
  MappingMethod getObfMethod(MappingMethod paramMappingMethod);
  
  MappingMethod getObfMethod(MappingMethod paramMappingMethod, boolean paramBoolean);
  
  MappingField getObfField(MemberInfo paramMemberInfo);
  
  MappingField getObfField(MappingField paramMappingField);
  
  MappingField getObfField(MappingField paramMappingField, boolean paramBoolean);
  
  String getObfClass(String paramString);
  
  MemberInfo remapDescriptor(MemberInfo paramMemberInfo);
  
  String remapDescriptor(String paramString);
  
  void writeMappings(Collection paramCollection);
}
