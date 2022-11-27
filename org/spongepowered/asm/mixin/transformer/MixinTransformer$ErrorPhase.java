package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinErrorHandler;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;

enum MixinTransformer$ErrorPhase {
  PREPARE, APPLY;
  
  private final String text = name().toLowerCase();
  
  private static final MixinTransformer$ErrorPhase[] $VALUES = new MixinTransformer$ErrorPhase[] { PREPARE, APPLY };
  
  abstract IMixinErrorHandler.ErrorAction onError(IMixinErrorHandler paramIMixinErrorHandler, String paramString, InvalidMixinException paramInvalidMixinException, IMixinInfo paramIMixinInfo, IMixinErrorHandler.ErrorAction paramErrorAction);
  
  protected abstract String getContext(IMixinInfo paramIMixinInfo, String paramString);
  
  public String getLogMessage(String paramString, InvalidMixinException paramInvalidMixinException, IMixinInfo paramIMixinInfo) {
    return String.format("Mixin %s failed %s: %s %s", new Object[] { this.text, getContext(paramIMixinInfo, paramString), paramInvalidMixinException.getClass().getName(), paramInvalidMixinException.getMessage() });
  }
  
  public String getErrorMessage(IMixinInfo paramIMixinInfo, IMixinConfig paramIMixinConfig, MixinEnvironment.Phase paramPhase) {
    return String.format("Mixin [%s] from phase [%s] in config [%s] FAILED during %s", new Object[] { paramIMixinInfo, paramPhase, paramIMixinConfig, name() });
  }
}
