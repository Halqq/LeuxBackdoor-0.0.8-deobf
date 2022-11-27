package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;

class Mappings implements IMappingConsumer {
  private final Map fieldMappings = new HashMap<Object, Object>();
  
  private final Map methodMappings = new HashMap<Object, Object>();
  
  private Mappings$UniqueMappings unique;
  
  public Mappings() {
    init();
  }
  
  private void init() {
    for (ObfuscationType obfuscationType : ObfuscationType.types()) {
      this.fieldMappings.put(obfuscationType, new IMappingConsumer.MappingSet());
      this.methodMappings.put(obfuscationType, new IMappingConsumer.MappingSet());
    } 
  }
  
  public IMappingConsumer asUnique() {
    if (this.unique == null)
      this.unique = new Mappings$UniqueMappings(this); 
    return this.unique;
  }
  
  public IMappingConsumer.MappingSet getFieldMappings(ObfuscationType paramObfuscationType) {
    IMappingConsumer.MappingSet mappingSet = (IMappingConsumer.MappingSet)this.fieldMappings.get(paramObfuscationType);
  }
  
  public IMappingConsumer.MappingSet getMethodMappings(ObfuscationType paramObfuscationType) {
    IMappingConsumer.MappingSet mappingSet = (IMappingConsumer.MappingSet)this.methodMappings.get(paramObfuscationType);
  }
  
  public void clear() {
    this.fieldMappings.clear();
    this.methodMappings.clear();
    if (this.unique != null)
      this.unique.clearMaps(); 
    init();
  }
  
  public void addFieldMapping(ObfuscationType paramObfuscationType, MappingField paramMappingField1, MappingField paramMappingField2) {
    IMappingConsumer.MappingSet mappingSet = (IMappingConsumer.MappingSet)this.fieldMappings.get(paramObfuscationType);
    mappingSet = new IMappingConsumer.MappingSet();
    this.fieldMappings.put(paramObfuscationType, mappingSet);
    mappingSet.add(new IMappingConsumer.MappingSet.Pair((IMapping)paramMappingField1, (IMapping)paramMappingField2));
  }
  
  public void addMethodMapping(ObfuscationType paramObfuscationType, MappingMethod paramMappingMethod1, MappingMethod paramMappingMethod2) {
    IMappingConsumer.MappingSet mappingSet = (IMappingConsumer.MappingSet)this.methodMappings.get(paramObfuscationType);
    mappingSet = new IMappingConsumer.MappingSet();
    this.methodMappings.put(paramObfuscationType, mappingSet);
    mappingSet.add(new IMappingConsumer.MappingSet.Pair((IMapping)paramMappingMethod1, (IMapping)paramMappingMethod2));
  }
}
