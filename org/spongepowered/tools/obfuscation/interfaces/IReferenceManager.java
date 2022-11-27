package org.spongepowered.tools.obfuscation.interfaces;

import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.tools.obfuscation.ObfuscationData;

public interface IReferenceManager {
  void setAllowConflicts(boolean paramBoolean);
  
  boolean getAllowConflicts();
  
  void write();
  
  ReferenceMapper getMapper();
  
  void addMethodMapping(String paramString1, String paramString2, ObfuscationData paramObfuscationData);
  
  void addMethodMapping(String paramString1, String paramString2, MemberInfo paramMemberInfo, ObfuscationData paramObfuscationData);
  
  void addFieldMapping(String paramString1, String paramString2, MemberInfo paramMemberInfo, ObfuscationData paramObfuscationData);
  
  void addClassMapping(String paramString1, String paramString2, ObfuscationData paramObfuscationData);
}
