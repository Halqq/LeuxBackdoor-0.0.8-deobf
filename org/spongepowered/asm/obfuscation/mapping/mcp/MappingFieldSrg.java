package org.spongepowered.asm.obfuscation.mapping.mcp;

import org.spongepowered.asm.obfuscation.mapping.common.MappingField;

public class MappingFieldSrg extends MappingField {
  private final String srg;
  
  public MappingFieldSrg(String paramString) {
    super(getOwnerFromSrg(paramString), getNameFromSrg(paramString), null);
    this.srg = paramString;
  }
  
  public MappingFieldSrg(MappingField paramMappingField) {
    super(paramMappingField.getOwner(), paramMappingField.getName(), null);
    this.srg = paramMappingField.getOwner() + "/" + paramMappingField.getName();
  }
  
  public String serialise() {
    return this.srg;
  }
  
  private static String getNameFromSrg(String paramString) {
    return null;
  }
  
  private static String getOwnerFromSrg(String paramString) {
    return null;
  }
}
