package org.spongepowered.asm.service.mojang;

import javax.annotation.Resource;
import net.minecraft.launchwrapper.IClassTransformer;
import org.spongepowered.asm.service.ILegacyClassTransformer;

class LegacyTransformerHandle implements ILegacyClassTransformer {
  private final IClassTransformer transformer;
  
  LegacyTransformerHandle(IClassTransformer paramIClassTransformer) {
    this.transformer = paramIClassTransformer;
  }
  
  public String getName() {
    return this.transformer.getClass().getName();
  }
  
  public boolean isDelegationExcluded() {
    return (this.transformer.getClass().getAnnotation(Resource.class) != null);
  }
  
  public byte[] transformClassBytes(String paramString1, String paramString2, byte[] paramArrayOfbyte) {
    return this.transformer.transform(paramString1, paramString2, paramArrayOfbyte);
  }
}
