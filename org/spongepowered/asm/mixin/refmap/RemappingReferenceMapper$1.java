package org.spongepowered.asm.mixin.refmap;

import com.google.common.base.Strings;
import com.google.common.io.LineProcessor;
import java.io.IOException;
import java.util.Map;

final class RemappingReferenceMapper$1 implements LineProcessor {
  final Map val$map;
  
  public Object getResult() {
    return null;
  }
  
  public boolean processLine(String paramString) throws IOException {
    if (Strings.isNullOrEmpty(paramString) || paramString.startsWith("#"))
      return true; 
    boolean bool1 = false;
    boolean bool2 = false;
    bool2 = paramString.startsWith("MD: ") ? true : (paramString.startsWith("FD: ") ? true : false);
    return true;
  }
}
