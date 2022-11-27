package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

enum MixinTransformer$ErrorPhase$1 {
  IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler paramIMixinErrorHandler, String paramString, InvalidMixinException paramInvalidMixinException, IMixinInfo paramIMixinInfo, IMixinErrorHandler.ErrorAction paramErrorAction) {
    return paramIMixinErrorHandler.onPrepareError(paramIMixinInfo.getConfig(), (Throwable)paramInvalidMixinException, paramIMixinInfo, paramErrorAction);
  }
  
  protected String getContext(IMixinInfo paramIMixinInfo, String paramString) {
    return String.format("preparing %s in %s", new Object[] { paramIMixinInfo.getName(), paramString });
  }
}
