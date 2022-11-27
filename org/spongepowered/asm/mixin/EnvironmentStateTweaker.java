package org.spongepowered.asm.mixin;

import java.io.File;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;

public class EnvironmentStateTweaker implements ITweaker {
  public void acceptOptions(List paramList, File paramFile1, File paramFile2, String paramString) {}
  
  public void injectIntoClassLoader(LaunchClassLoader paramLaunchClassLoader) {
    MixinBootstrap.getPlatform().inject();
  }
  
  public String getLaunchTarget() {
    return "";
  }
  
  public String[] getLaunchArguments() {
    MixinEnvironment.gotoPhase(MixinEnvironment$Phase.DEFAULT);
    return new String[0];
  }
}
