package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;

class Mappings$UniqueMappings implements IMappingConsumer {
  private final IMappingConsumer mappings;
  
  private final Map fields = new HashMap<Object, Object>();
  
  private final Map methods = new HashMap<Object, Object>();
  
  public Mappings$UniqueMappings(IMappingConsumer paramIMappingConsumer) {
    this.mappings = paramIMappingConsumer;
  }
  
  public void clear() {
    clearMaps();
    this.mappings.clear();
  }
  
  protected void clearMaps() {
    this.fields.clear();
    this.methods.clear();
  }
  
  public void addFieldMapping(ObfuscationType paramObfuscationType, MappingField paramMappingField1, MappingField paramMappingField2) {
    if (!checkForExistingMapping(paramObfuscationType, (IMapping)paramMappingField1, (IMapping)paramMappingField2, this.fields))
      this.mappings.addFieldMapping(paramObfuscationType, paramMappingField1, paramMappingField2); 
  }
  
  public void addMethodMapping(ObfuscationType paramObfuscationType, MappingMethod paramMappingMethod1, MappingMethod paramMappingMethod2) {
    if (!checkForExistingMapping(paramObfuscationType, (IMapping)paramMappingMethod1, (IMapping)paramMappingMethod2, this.methods))
      this.mappings.addMethodMapping(paramObfuscationType, paramMappingMethod1, paramMappingMethod2); 
  }
  
  private boolean checkForExistingMapping(ObfuscationType paramObfuscationType, IMapping paramIMapping1, IMapping paramIMapping2, Map<ObfuscationType, Map<Object, Object>> paramMap) throws Mappings$MappingConflictException {
    Map<Object, Object> map = (Map)paramMap.get(paramObfuscationType);
    map = new HashMap<Object, Object>();
    paramMap.put(paramObfuscationType, map);
    IMapping iMapping = (IMapping)map.get(paramIMapping1);
    if (iMapping.equals(paramIMapping2))
      return true; 
    throw new Mappings$MappingConflictException(iMapping, paramIMapping2);
  }
  
  public IMappingConsumer.MappingSet getFieldMappings(ObfuscationType paramObfuscationType) {
    return this.mappings.getFieldMappings(paramObfuscationType);
  }
  
  public IMappingConsumer.MappingSet getMethodMappings(ObfuscationType paramObfuscationType) {
    return this.mappings.getMethodMappings(paramObfuscationType);
  }
}
