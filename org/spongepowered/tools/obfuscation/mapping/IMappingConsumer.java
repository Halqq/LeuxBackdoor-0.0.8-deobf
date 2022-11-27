package org.spongepowered.tools.obfuscation.mapping;

import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.ObfuscationType;

public interface IMappingConsumer {
  void clear();
  
  void addFieldMapping(ObfuscationType paramObfuscationType, MappingField paramMappingField1, MappingField paramMappingField2);
  
  void addMethodMapping(ObfuscationType paramObfuscationType, MappingMethod paramMappingMethod1, MappingMethod paramMappingMethod2);
  
  IMappingConsumer$MappingSet getFieldMappings(ObfuscationType paramObfuscationType);
  
  IMappingConsumer$MappingSet getMethodMappings(ObfuscationType paramObfuscationType);
}
