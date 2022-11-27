package org.spongepowered.asm.mixin.transformer.ext;

import org.spongepowered.asm.mixin.MixinEnvironment;

public interface IExtension {
  boolean checkActive(MixinEnvironment paramMixinEnvironment);
  
  void preApply(ITargetClassContext paramITargetClassContext);
  
  void postApply(ITargetClassContext paramITargetClassContext);
  
  void export(MixinEnvironment paramMixinEnvironment, String paramString, boolean paramBoolean, byte[] paramArrayOfbyte);
}
