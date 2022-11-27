package org.spongepowered.asm.mixin.transformer;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.service.ILegacyClassTransformer;

public final class Proxy implements IClassTransformer, ILegacyClassTransformer {
  private static List proxies = new ArrayList();
  
  private static MixinTransformer transformer = new MixinTransformer();
  
  private boolean isActive = true;
  
  public Proxy() {
    for (Proxy proxy : proxies)
      proxy.isActive = false; 
    proxies.add(this);
    LogManager.getLogger("mixin").debug("Adding new mixin transformer proxy #{}", new Object[] { Integer.valueOf(proxies.size()) });
  }
  
  public byte[] transform(String paramString1, String paramString2, byte[] paramArrayOfbyte) {
    return this.isActive ? transformer.transformClassBytes(paramString1, paramString2, paramArrayOfbyte) : paramArrayOfbyte;
  }
  
  public String getName() {
    return getClass().getName();
  }
  
  public boolean isDelegationExcluded() {
    return true;
  }
  
  public byte[] transformClassBytes(String paramString1, String paramString2, byte[] paramArrayOfbyte) {
    return this.isActive ? transformer.transformClassBytes(paramString1, paramString2, paramArrayOfbyte) : paramArrayOfbyte;
  }
}
