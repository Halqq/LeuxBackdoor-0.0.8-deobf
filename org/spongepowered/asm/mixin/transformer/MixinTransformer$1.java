package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;

class MixinTransformer$1 implements MixinConfig$IListener {
  final IHotSwap val$hotSwapper;
  
  final MixinTransformer this$0;
  
  public void onPrepare(MixinInfo paramMixinInfo) {
    hotSwapper.registerMixinClass(paramMixinInfo.getClassName());
  }
  
  public void onInit(MixinInfo paramMixinInfo) {}
}
