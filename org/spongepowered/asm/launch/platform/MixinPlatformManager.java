package org.spongepowered.asm.launch.platform;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.service.MixinService;

public class MixinPlatformManager {
  private static final String DEFAULT_MAIN_CLASS = "net.minecraft.client.main.Main";
  
  private static final String MIXIN_TWEAKER_CLASS = "org.spongepowered.asm.launch.MixinTweaker";
  
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private final Map containers = new LinkedHashMap<Object, Object>();
  
  private MixinContainer primaryContainer;
  
  private boolean prepared = false;
  
  private boolean injected;
  
  public void init() {
    logger.debug("Initialising Mixin Platform Manager");
    URI uRI = null;
    uRI = getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
    logger.debug("Mixin platform: primary container is {}", new Object[] { uRI });
    this.primaryContainer = addContainer(uRI);
    scanClasspath();
  }
  
  public Collection getPhaseProviderClasses() {
    Collection<?> collection = this.primaryContainer.getPhaseProviders();
    return Collections.unmodifiableCollection(collection);
  }
  
  public final MixinContainer addContainer(URI paramURI) {
    return (MixinContainer)this.containers.get(paramURI);
  }
  
  public final void prepare(List paramList) {
    this.prepared = true;
    for (MixinContainer mixinContainer : this.containers.values())
      mixinContainer.prepare(); 
    parseArgs(paramList);
  }
  
  private void parseArgs(List paramList) {
    boolean bool = false;
    for (String str : paramList)
      bool = "--mixin".equals(str); 
  }
  
  public final void inject() {
    if (this.injected)
      return; 
    this.injected = true;
    if (this.primaryContainer != null)
      this.primaryContainer.initPrimaryContainer(); 
    scanClasspath();
    logger.debug("inject() running with {} agents", new Object[] { Integer.valueOf(this.containers.size()) });
    for (MixinContainer mixinContainer : this.containers.values())
      mixinContainer.inject(); 
  }
  
  private void scanClasspath() {
    URL[] arrayOfURL = MixinService.getService().getClassProvider().getClassPath();
    for (URL uRL : arrayOfURL) {
      URI uRI = uRL.toURI();
      if (!this.containers.containsKey(uRI)) {
        logger.debug("Scanning {} for mixin tweaker", new Object[] { uRI });
        if ("file".equals(uRI.getScheme()) && (new File(uRI)).exists()) {
          MainAttributes mainAttributes = MainAttributes.of(uRI);
          String str = mainAttributes.get("TweakClass");
          if ("org.spongepowered.asm.launch.MixinTweaker".equals(str)) {
            logger.debug("{} contains a mixin tweaker, adding agents", new Object[] { uRI });
            addContainer(uRI);
          } 
        } 
      } 
    } 
  }
  
  public String getLaunchTarget() {
    Iterator<MixinContainer> iterator = this.containers.values().iterator();
    if (iterator.hasNext()) {
      MixinContainer mixinContainer = iterator.next();
      return mixinContainer.getLaunchTarget();
    } 
    return "net.minecraft.client.main.Main";
  }
  
  final void setCompatibilityLevel(String paramString) {
    MixinEnvironment.CompatibilityLevel compatibilityLevel = MixinEnvironment.CompatibilityLevel.valueOf(paramString.toUpperCase());
    logger.debug("Setting mixin compatibility level: {}", new Object[] { compatibilityLevel });
    MixinEnvironment.setCompatibilityLevel(compatibilityLevel);
  }
  
  final void addConfig(String paramString) {
    if (paramString.endsWith(".json")) {
      logger.debug("Registering mixin config: {}", new Object[] { paramString });
      Mixins.addConfiguration(paramString);
    } else if (paramString.contains(".json@")) {
      int i = paramString.indexOf(".json@");
      String str = paramString.substring(i + 6);
      paramString = paramString.substring(0, i + 5);
      MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(str);
      logger.warn("Setting config phase via manifest is deprecated: {}. Specify target in config instead", new Object[] { paramString });
      logger.debug("Registering mixin config: {}", new Object[] { paramString });
      MixinEnvironment.getEnvironment(phase).addConfiguration(paramString);
    } 
  }
  
  final void addTokenProvider(String paramString) {
    if (paramString.contains("@")) {
      String[] arrayOfString = paramString.split("@", 2);
      MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(arrayOfString[1]);
      logger.debug("Registering token provider class: {}", new Object[] { arrayOfString[0] });
      MixinEnvironment.getEnvironment(phase).registerTokenProviderClass(arrayOfString[0]);
      return;
    } 
    MixinEnvironment.getDefaultEnvironment().registerTokenProviderClass(paramString);
  }
}
