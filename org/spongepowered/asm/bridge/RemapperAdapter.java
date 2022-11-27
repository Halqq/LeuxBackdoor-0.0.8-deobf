package org.spongepowered.asm.bridge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.commons.Remapper;
import org.spongepowered.asm.mixin.extensibility.IRemapper;
import org.spongepowered.asm.util.ObfuscationUtil;

public abstract class RemapperAdapter implements IRemapper, ObfuscationUtil.IClassRemapper {
  protected final Logger logger = LogManager.getLogger("mixin");
  
  protected final Remapper remapper;
  
  public RemapperAdapter(Remapper paramRemapper) {
    this.remapper = paramRemapper;
  }
  
  public String toString() {
    return getClass().getSimpleName();
  }
  
  public String mapMethodName(String paramString1, String paramString2, String paramString3) {
    this.logger.debug("{} is remapping method {}{} for {}", new Object[] { this, paramString2, paramString3, paramString1 });
    String str1 = this.remapper.mapMethodName(paramString1, paramString2, paramString3);
    if (!str1.equals(paramString2))
      return str1; 
    String str2 = unmap(paramString1);
    String str3 = unmapDesc(paramString3);
    this.logger.debug("{} is remapping obfuscated method {}{} for {}", new Object[] { this, paramString2, str3, str2 });
    return this.remapper.mapMethodName(str2, paramString2, str3);
  }
  
  public String mapFieldName(String paramString1, String paramString2, String paramString3) {
    this.logger.debug("{} is remapping field {}{} for {}", new Object[] { this, paramString2, paramString3, paramString1 });
    String str1 = this.remapper.mapFieldName(paramString1, paramString2, paramString3);
    if (!str1.equals(paramString2))
      return str1; 
    String str2 = unmap(paramString1);
    String str3 = unmapDesc(paramString3);
    this.logger.debug("{} is remapping obfuscated field {}{} for {}", new Object[] { this, paramString2, str3, str2 });
    return this.remapper.mapFieldName(str2, paramString2, str3);
  }
  
  public String map(String paramString) {
    this.logger.debug("{} is remapping class {}", new Object[] { this, paramString });
    return this.remapper.map(paramString);
  }
  
  public String unmap(String paramString) {
    return paramString;
  }
  
  public String mapDesc(String paramString) {
    return this.remapper.mapDesc(paramString);
  }
  
  public String unmapDesc(String paramString) {
    return ObfuscationUtil.unmapDescriptor(paramString, this);
  }
}
