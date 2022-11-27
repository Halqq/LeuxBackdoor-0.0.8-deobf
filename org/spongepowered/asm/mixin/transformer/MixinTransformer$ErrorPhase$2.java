package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

enum MixinTransformer$ErrorPhase$2 {
  IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler paramIMixinErrorHandler, String paramString, InvalidMixinException paramInvalidMixinException, IMixinInfo paramIMixinInfo, IMixinErrorHandler.ErrorAction paramErrorAction) {
    return paramIMixinErrorHandler.onApplyError(paramString, (Throwable)paramInvalidMixinException, paramIMixinInfo, paramErrorAction);
  }
  
  protected String getContext(IMixinInfo paramIMixinInfo, String paramString) {
    return String.format("%s -> %s", new Object[] { paramIMixinInfo, paramString });
  }
}
