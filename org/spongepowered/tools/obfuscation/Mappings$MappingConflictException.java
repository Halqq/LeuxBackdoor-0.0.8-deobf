package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.obfuscation.mapping.IMapping;

public class Mappings$MappingConflictException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  private final IMapping oldMapping;
  
  private final IMapping newMapping;
  
  public Mappings$MappingConflictException(IMapping paramIMapping1, IMapping paramIMapping2) {
    this.oldMapping = paramIMapping1;
    this.newMapping = paramIMapping2;
  }
  
  public IMapping getOld() {
    return this.oldMapping;
  }
  
  public IMapping getNew() {
    return this.newMapping;
  }
}
