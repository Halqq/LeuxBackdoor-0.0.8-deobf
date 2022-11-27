package org.spongepowered.asm.launch;

import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class MixinTweaker implements ITweaker {
  public MixinTweaker() {
    MixinBootstrap.start();
  }
  
  public final void acceptOptions(List paramList, File paramFile1, File paramFile2, String paramString) {
    MixinBootstrap.doInit(paramList);
  }
  
  public final void injectIntoClassLoader(LaunchClassLoader paramLaunchClassLoader) {
    MixinBootstrap.inject();
  }
  
  public String getLaunchTarget() {
    return MixinBootstrap.getPlatform().getLaunchTarget();
  }
  
  public String[] getLaunchArguments() {
    return new String[0];
  }
}
