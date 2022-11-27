package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;

public class Config {
  private final String name;
  
  private final MixinConfig config;
  
  public Config(MixinConfig paramMixinConfig) {
    this.name = paramMixinConfig.getName();
    this.config = paramMixinConfig;
  }
  
  public String getName() {
    return this.name;
  }
  
  MixinConfig get() {
    return this.config;
  }
  
  public boolean isVisited() {
    return this.config.isVisited();
  }
  
  public IMixinConfig getConfig() {
    return this.config;
  }
  
  public MixinEnvironment getEnvironment() {
    return this.config.getEnvironment();
  }
  
  public String toString() {
    return this.config.toString();
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof Config && this.name.equals(((Config)paramObject).name));
  }
  
  public int hashCode() {
    return this.name.hashCode();
  }
  
  @Deprecated
  @Deprecated
  public static Config create(String paramString, MixinEnvironment paramMixinEnvironment) {
    return MixinConfig.create(paramString, paramMixinEnvironment);
  }
  
  public static Config create(String paramString) {
    return MixinConfig.create(paramString, MixinEnvironment.getDefaultEnvironment());
  }
}
