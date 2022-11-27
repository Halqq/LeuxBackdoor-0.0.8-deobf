package org.spongepowered.tools.obfuscation.mapping.mcp;

import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.obfuscation.mapping.mcp.MappingFieldSrg;

class MappingProviderSrg$1 implements LineProcessor {
  final BiMap val$packageMap;
  
  final BiMap val$classMap;
  
  final BiMap val$fieldMap;
  
  final BiMap val$methodMap;
  
  final File val$input;
  
  final MappingProviderSrg this$0;
  
  public String getResult() {
    return null;
  }
  
  public boolean processLine(String paramString) throws IOException {
    if (Strings.isNullOrEmpty(paramString) || paramString.startsWith("#"))
      return true; 
    String str = paramString.substring(0, 2);
    String[] arrayOfString = paramString.substring(4).split(" ");
    if (str.equals("PK")) {
      packageMap.forcePut(arrayOfString[0], arrayOfString[1]);
    } else if (str.equals("CL")) {
      classMap.forcePut(arrayOfString[0], arrayOfString[1]);
    } else if (str.equals("FD")) {
      fieldMap.forcePut((new MappingFieldSrg(arrayOfString[0])).copy(), (new MappingFieldSrg(arrayOfString[1])).copy());
    } else if (str.equals("MD")) {
      methodMap.forcePut(new MappingMethod(arrayOfString[0], arrayOfString[1]), new MappingMethod(arrayOfString[2], arrayOfString[3]));
    } else {
      throw new MixinException("Invalid SRG file: " + input);
    } 
    return true;
  }
  
  public Object getResult() {
    return getResult();
  }
}
