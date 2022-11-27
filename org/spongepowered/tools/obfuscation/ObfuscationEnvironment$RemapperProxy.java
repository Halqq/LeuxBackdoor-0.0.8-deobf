package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.util.ObfuscationUtil;

final class ObfuscationEnvironment$RemapperProxy implements ObfuscationUtil.IClassRemapper {
  final ObfuscationEnvironment this$0;
  
  public String map(String paramString) {
    return (ObfuscationEnvironment.this.mappingProvider == null) ? null : ObfuscationEnvironment.this.mappingProvider.getClassMapping(paramString);
  }
  
  public String unmap(String paramString) {
    return (ObfuscationEnvironment.this.mappingProvider == null) ? null : ObfuscationEnvironment.this.mappingProvider.getClassMapping(paramString);
  }
}
