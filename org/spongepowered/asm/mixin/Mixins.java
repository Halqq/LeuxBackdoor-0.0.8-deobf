package org.spongepowered.asm.mixin;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.mixin.transformer.Config;

public final class Mixins {
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private static final String CONFIGS_KEY = "mixin.configs.queue";
  
  private static final Set errorHandlers = new LinkedHashSet();
  
  public static void addConfigurations(String... paramVarArgs) {
    MixinEnvironment mixinEnvironment = MixinEnvironment.getDefaultEnvironment();
    for (String str : paramVarArgs)
      createConfiguration(str, mixinEnvironment); 
  }
  
  public static void addConfiguration(String paramString) {
    createConfiguration(paramString, MixinEnvironment.getDefaultEnvironment());
  }
  
  @Deprecated
  @Deprecated
  static void addConfiguration(String paramString, MixinEnvironment paramMixinEnvironment) {
    createConfiguration(paramString, paramMixinEnvironment);
  }
  
  private static void createConfiguration(String paramString, MixinEnvironment paramMixinEnvironment) {
    Config config = null;
    config = Config.create(paramString, paramMixinEnvironment);
    registerConfiguration(config);
  }
  
  private static void registerConfiguration(Config paramConfig) {}
  
  public static int getUnvisitedCount() {
    byte b = 0;
    for (Config config : getConfigs()) {
      if (!config.isVisited())
        b++; 
    } 
    return b;
  }
  
  public static Set getConfigs() {
    Set set = (Set)GlobalProperties.get("mixin.configs.queue");
    set = new LinkedHashSet();
    GlobalProperties.put("mixin.configs.queue", set);
    return set;
  }
  
  public static void registerErrorHandlerClass(String paramString) {
    errorHandlers.add(paramString);
  }
  
  public static Set getErrorHandlerClasses() {
    return Collections.unmodifiableSet(errorHandlers);
  }
}
