package org.spongepowered.tools.obfuscation.interfaces;

import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.ObfuscationData;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public interface IObfuscationDataProvider {
  ObfuscationData getObfEntryRecursive(MemberInfo paramMemberInfo);
  
  ObfuscationData getObfEntry(MemberInfo paramMemberInfo);
  
  ObfuscationData getObfEntry(IMapping paramIMapping);
  
  ObfuscationData getObfMethodRecursive(MemberInfo paramMemberInfo);
  
  ObfuscationData getObfMethod(MemberInfo paramMemberInfo);
  
  ObfuscationData getRemappedMethod(MemberInfo paramMemberInfo);
  
  ObfuscationData getObfMethod(MappingMethod paramMappingMethod);
  
  ObfuscationData getRemappedMethod(MappingMethod paramMappingMethod);
  
  ObfuscationData getObfFieldRecursive(MemberInfo paramMemberInfo);
  
  ObfuscationData getObfField(MemberInfo paramMemberInfo);
  
  ObfuscationData getObfField(MappingField paramMappingField);
  
  ObfuscationData getObfClass(TypeHandle paramTypeHandle);
  
  ObfuscationData getObfClass(String paramString);
}
