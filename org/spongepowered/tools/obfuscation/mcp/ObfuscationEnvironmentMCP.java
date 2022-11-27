package org.spongepowered.tools.obfuscation.mcp;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.tools.obfuscation.ObfuscationEnvironment;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.mapping.IMappingProvider;
import org.spongepowered.tools.obfuscation.mapping.IMappingWriter;
import org.spongepowered.tools.obfuscation.mapping.mcp.MappingProviderSrg;
import org.spongepowered.tools.obfuscation.mapping.mcp.MappingWriterSrg;

public class ObfuscationEnvironmentMCP extends ObfuscationEnvironment {
  protected ObfuscationEnvironmentMCP(ObfuscationType paramObfuscationType) {
    super(paramObfuscationType);
  }
  
  protected IMappingProvider getMappingProvider(Messager paramMessager, Filer paramFiler) {
    return (IMappingProvider)new MappingProviderSrg(paramMessager, paramFiler);
  }
  
  protected IMappingWriter getMappingWriter(Messager paramMessager, Filer paramFiler) {
    return (IMappingWriter)new MappingWriterSrg(paramMessager, paramFiler);
  }
}
