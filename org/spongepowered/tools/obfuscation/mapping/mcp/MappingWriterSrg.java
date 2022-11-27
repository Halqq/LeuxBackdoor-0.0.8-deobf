package org.spongepowered.tools.obfuscation.mapping.mcp;

import java.io.PrintWriter;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;
import org.spongepowered.tools.obfuscation.mapping.common.MappingWriter;

public class MappingWriterSrg extends MappingWriter {
  public MappingWriterSrg(Messager paramMessager, Filer paramFiler) {
    super(paramMessager, paramFiler);
  }
  
  public void write(String paramString, ObfuscationType paramObfuscationType, IMappingConsumer.MappingSet paramMappingSet1, IMappingConsumer.MappingSet paramMappingSet2) {}
  
  protected void writeFieldMappings(PrintWriter paramPrintWriter, IMappingConsumer.MappingSet paramMappingSet) {
    for (IMappingConsumer.MappingSet.Pair pair : paramMappingSet)
      paramPrintWriter.println(formatFieldMapping(pair)); 
  }
  
  protected void writeMethodMappings(PrintWriter paramPrintWriter, IMappingConsumer.MappingSet paramMappingSet) {
    for (IMappingConsumer.MappingSet.Pair pair : paramMappingSet)
      paramPrintWriter.println(formatMethodMapping(pair)); 
  }
  
  protected String formatFieldMapping(IMappingConsumer.MappingSet.Pair paramPair) {
    return String.format("FD: %s/%s %s/%s", new Object[] { ((MappingField)paramPair.from).getOwner(), ((MappingField)paramPair.from).getName(), ((MappingField)paramPair.to).getOwner(), ((MappingField)paramPair.to).getName() });
  }
  
  protected String formatMethodMapping(IMappingConsumer.MappingSet.Pair paramPair) {
    return String.format("MD: %s %s %s %s", new Object[] { ((MappingMethod)paramPair.from).getName(), ((MappingMethod)paramPair.from).getDesc(), ((MappingMethod)paramPair.to).getName(), ((MappingMethod)paramPair.to).getDesc() });
  }
}
