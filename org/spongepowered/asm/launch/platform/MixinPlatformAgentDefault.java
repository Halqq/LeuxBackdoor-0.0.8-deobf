package org.spongepowered.asm.launch.platform;

import java.net.URI;

public class MixinPlatformAgentDefault extends MixinPlatformAgentAbstract {
  public MixinPlatformAgentDefault(MixinPlatformManager paramMixinPlatformManager, URI paramURI) {
    super(paramMixinPlatformManager, paramURI);
  }
  
  public void prepare() {
    String str1 = this.attributes.get("MixinCompatibilityLevel");
    this.manager.setCompatibilityLevel(str1);
    String str2 = this.attributes.get("MixinConfigs");
    for (String str : str2.split(","))
      this.manager.addConfig(str.trim()); 
    String str3 = this.attributes.get("MixinTokenProviders");
    for (String str : str3.split(","))
      this.manager.addTokenProvider(str.trim()); 
  }
  
  public void initPrimaryContainer() {}
  
  public void inject() {}
  
  public String getLaunchTarget() {
    return this.attributes.get("Main-Class");
  }
}
