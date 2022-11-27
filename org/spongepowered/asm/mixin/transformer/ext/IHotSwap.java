package org.spongepowered.asm.mixin.transformer.ext;

public interface IHotSwap {
  void registerMixinClass(String paramString);
  
  void registerTargetClass(String paramString, byte[] paramArrayOfbyte);
}
