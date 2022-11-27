package org.spongepowered.asm.obfuscation;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.mixin.extensibility.IRemapper;

public class RemapperChain implements IRemapper {
  private final List remappers = new ArrayList();
  
  public String toString() {
    return String.format("RemapperChain[%d]", new Object[] { Integer.valueOf(this.remappers.size()) });
  }
  
  public RemapperChain add(IRemapper paramIRemapper) {
    this.remappers.add(paramIRemapper);
    return this;
  }
  
  public String mapMethodName(String paramString1, String paramString2, String paramString3) {
    for (IRemapper iRemapper : this.remappers) {
      String str = iRemapper.mapMethodName(paramString1, paramString2, paramString3);
      if (!str.equals(paramString2))
        paramString2 = str; 
    } 
    return paramString2;
  }
  
  public String mapFieldName(String paramString1, String paramString2, String paramString3) {
    for (IRemapper iRemapper : this.remappers) {
      String str = iRemapper.mapFieldName(paramString1, paramString2, paramString3);
      if (!str.equals(paramString2))
        paramString2 = str; 
    } 
    return paramString2;
  }
  
  public String map(String paramString) {
    for (IRemapper iRemapper : this.remappers) {
      String str = iRemapper.map(paramString);
      if (!str.equals(paramString))
        paramString = str; 
    } 
    return paramString;
  }
  
  public String unmap(String paramString) {
    for (IRemapper iRemapper : this.remappers) {
      String str = iRemapper.unmap(paramString);
      if (!str.equals(paramString))
        paramString = str; 
    } 
    return paramString;
  }
  
  public String mapDesc(String paramString) {
    for (IRemapper iRemapper : this.remappers) {
      String str = iRemapper.mapDesc(paramString);
      if (!str.equals(paramString))
        paramString = str; 
    } 
    return paramString;
  }
  
  public String unmapDesc(String paramString) {
    for (IRemapper iRemapper : this.remappers) {
      String str = iRemapper.unmapDesc(paramString);
      if (!str.equals(paramString))
        paramString = str; 
    } 
    return paramString;
  }
}
