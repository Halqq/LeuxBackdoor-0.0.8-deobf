package org.spongepowered.asm.mixin.transformer.ext.extensions;

import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.util.CheckClassAdapter;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;
import org.spongepowered.asm.transformers.MixinClassWriter;

public class ExtensionCheckClass implements IExtension {
  public boolean checkActive(MixinEnvironment paramMixinEnvironment) {
    return paramMixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_VERIFY);
  }
  
  public void preApply(ITargetClassContext paramITargetClassContext) {}
  
  public void postApply(ITargetClassContext paramITargetClassContext) {
    paramITargetClassContext.getClassNode().accept((ClassVisitor)new CheckClassAdapter((ClassVisitor)new MixinClassWriter(2)));
  }
  
  public void export(MixinEnvironment paramMixinEnvironment, String paramString, boolean paramBoolean, byte[] paramArrayOfbyte) {}
}
