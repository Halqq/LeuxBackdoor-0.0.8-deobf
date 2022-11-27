package org.spongepowered.asm.mixin;

import org.spongepowered.asm.mixin.extensibility.IEnvironmentTokenProvider;

class MixinEnvironment$TokenProviderWrapper implements Comparable {
  private static int nextOrder = 0;
  
  private final int priority;
  
  private final int order;
  
  private final IEnvironmentTokenProvider provider;
  
  private final MixinEnvironment environment;
  
  public MixinEnvironment$TokenProviderWrapper(IEnvironmentTokenProvider paramIEnvironmentTokenProvider, MixinEnvironment paramMixinEnvironment) {
    this.provider = paramIEnvironmentTokenProvider;
    this.environment = paramMixinEnvironment;
    this.order = nextOrder++;
    this.priority = paramIEnvironmentTokenProvider.getPriority();
  }
  
  public int compareTo(MixinEnvironment$TokenProviderWrapper paramMixinEnvironment$TokenProviderWrapper) {
    return 0;
  }
  
  public IEnvironmentTokenProvider getProvider() {
    return this.provider;
  }
  
  Integer getToken(String paramString) {
    return this.provider.getToken(paramString, this.environment);
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((MixinEnvironment$TokenProviderWrapper)paramObject);
  }
}
