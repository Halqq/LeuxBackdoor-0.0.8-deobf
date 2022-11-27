package org.spongepowered.asm.mixin;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.mixin.extensibility.IEnvironmentTokenProvider;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.obfuscation.RemapperChain;
import org.spongepowered.asm.service.ILegacyClassTransformer;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.ITransformer;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.ITokenProvider;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.perf.Profiler;

public final class MixinEnvironment implements ITokenProvider {
  private static final Set excludeTransformers = Sets.newHashSet((Object[])new String[] { "net.minecraftforge.fml.common.asm.transformers.EventSubscriptionTransformer", "cpw.mods.fml.common.asm.transformers.EventSubscriptionTransformer", "net.minecraftforge.fml.common.asm.transformers.TerminalTransformer", "cpw.mods.fml.common.asm.transformers.TerminalTransformer" });
  
  private static MixinEnvironment currentEnvironment;
  
  private static MixinEnvironment$Phase currentPhase = MixinEnvironment$Phase.NOT_INITIALISED;
  
  private static MixinEnvironment$CompatibilityLevel compatibility = (MixinEnvironment$CompatibilityLevel)MixinEnvironment$Option.DEFAULT_COMPATIBILITY_LEVEL.getEnumValue(MixinEnvironment$CompatibilityLevel.JAVA_6);
  
  private static boolean showHeader = true;
  
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private static final Profiler profiler = new Profiler();
  
  private final IMixinService service = MixinService.getService();
  
  private final MixinEnvironment$Phase phase;
  
  private final String configsKey;
  
  private final boolean[] options;
  
  private final Set tokenProviderClasses = new HashSet();
  
  private final List tokenProviders = new ArrayList();
  
  private final Map internalTokens = new HashMap<Object, Object>();
  
  private final RemapperChain remappers = new RemapperChain();
  
  private MixinEnvironment$Side side;
  
  private List transformers;
  
  private String obfuscationContext = null;
  
  MixinEnvironment(MixinEnvironment$Phase paramMixinEnvironment$Phase) {
    this.phase = paramMixinEnvironment$Phase;
    this.configsKey = "mixin.configs." + this.phase.name.toLowerCase();
    String str = getVersion();
    if (!"0.7.4".equals(str))
      throw new MixinException("Environment conflict, mismatched versions or you didn't call MixinBootstrap.init()"); 
    this.service.checkEnv(this);
    this.options = new boolean[(MixinEnvironment$Option.values()).length];
    for (MixinEnvironment$Option mixinEnvironment$Option : MixinEnvironment$Option.values())
      this.options[mixinEnvironment$Option.ordinal()] = mixinEnvironment$Option.getBooleanValue(); 
    if (showHeader) {
      showHeader = false;
      printHeader(str);
    } 
  }
  
  private void printHeader(Object paramObject) {
    String str1 = getCodeSource();
    String str2 = this.service.getName();
    MixinEnvironment$Side mixinEnvironment$Side = getSide();
    logger.info("SpongePowered MIXIN Subsystem Version={} Source={} Service={} Env={}", new Object[] { paramObject, str1, str2, mixinEnvironment$Side });
    boolean bool = getOption(MixinEnvironment$Option.DEBUG_VERBOSE);
    if (getOption(MixinEnvironment$Option.DEBUG_EXPORT) || getOption(MixinEnvironment$Option.DEBUG_PROFILER))
      PrettyPrinter prettyPrinter = new PrettyPrinter(32); 
  }
  
  private String getCodeSource() {
    return getClass().getProtectionDomain().getCodeSource().getLocation().toString();
  }
  
  public MixinEnvironment$Phase getPhase() {
    return this.phase;
  }
  
  @Deprecated
  @Deprecated
  public List getMixinConfigs() {
    List list = (List)GlobalProperties.get(this.configsKey);
    list = new ArrayList();
    GlobalProperties.put(this.configsKey, list);
    return list;
  }
  
  @Deprecated
  @Deprecated
  public MixinEnvironment addConfiguration(String paramString) {
    logger.warn("MixinEnvironment::addConfiguration is deprecated and will be removed. Use Mixins::addConfiguration instead!");
    Mixins.addConfiguration(paramString, this);
    return this;
  }
  
  void registerConfig(String paramString) {
    List<String> list = getMixinConfigs();
    if (!list.contains(paramString))
      list.add(paramString); 
  }
  
  @Deprecated
  @Deprecated
  public MixinEnvironment registerErrorHandlerClass(String paramString) {
    Mixins.registerErrorHandlerClass(paramString);
    return this;
  }
  
  public MixinEnvironment registerTokenProviderClass(String paramString) {
    if (!this.tokenProviderClasses.contains(paramString)) {
      Class<IEnvironmentTokenProvider> clazz = this.service.getClassProvider().findClass(paramString, true);
      IEnvironmentTokenProvider iEnvironmentTokenProvider = clazz.newInstance();
      registerTokenProvider(iEnvironmentTokenProvider);
    } 
    return this;
  }
  
  public MixinEnvironment registerTokenProvider(IEnvironmentTokenProvider paramIEnvironmentTokenProvider) {
    if (!this.tokenProviderClasses.contains(paramIEnvironmentTokenProvider.getClass().getName())) {
      String str = paramIEnvironmentTokenProvider.getClass().getName();
      MixinEnvironment$TokenProviderWrapper mixinEnvironment$TokenProviderWrapper = new MixinEnvironment$TokenProviderWrapper(paramIEnvironmentTokenProvider, this);
      logger.info("Adding new token provider {} to {}", new Object[] { str, this });
      this.tokenProviders.add(mixinEnvironment$TokenProviderWrapper);
      this.tokenProviderClasses.add(str);
      Collections.sort(this.tokenProviders);
    } 
    return this;
  }
  
  public Integer getToken(String paramString) {
    paramString = paramString.toUpperCase();
    Iterator<MixinEnvironment$TokenProviderWrapper> iterator = this.tokenProviders.iterator();
    if (iterator.hasNext()) {
      MixinEnvironment$TokenProviderWrapper mixinEnvironment$TokenProviderWrapper = iterator.next();
      return mixinEnvironment$TokenProviderWrapper.getToken(paramString);
    } 
    return (Integer)this.internalTokens.get(paramString);
  }
  
  @Deprecated
  @Deprecated
  public Set getErrorHandlerClasses() {
    return Mixins.getErrorHandlerClasses();
  }
  
  public Object getActiveTransformer() {
    return GlobalProperties.get("mixin.transformer");
  }
  
  public void setActiveTransformer(ITransformer paramITransformer) {
    GlobalProperties.put("mixin.transformer", paramITransformer);
  }
  
  public MixinEnvironment setSide(MixinEnvironment$Side paramMixinEnvironment$Side) {
    if (getSide() == MixinEnvironment$Side.UNKNOWN && paramMixinEnvironment$Side != MixinEnvironment$Side.UNKNOWN)
      this.side = paramMixinEnvironment$Side; 
    return this;
  }
  
  public MixinEnvironment$Side getSide() {
    if (this.side == null)
      for (MixinEnvironment$Side mixinEnvironment$Side : MixinEnvironment$Side.values()) {
        if (mixinEnvironment$Side.detect()) {
          this.side = mixinEnvironment$Side;
          break;
        } 
      }  
    return (this.side != null) ? this.side : MixinEnvironment$Side.UNKNOWN;
  }
  
  public String getVersion() {
    return (String)GlobalProperties.get("mixin.initialised");
  }
  
  public boolean getOption(MixinEnvironment$Option paramMixinEnvironment$Option) {
    return this.options[paramMixinEnvironment$Option.ordinal()];
  }
  
  public void setOption(MixinEnvironment$Option paramMixinEnvironment$Option, boolean paramBoolean) {
    this.options[paramMixinEnvironment$Option.ordinal()] = paramBoolean;
  }
  
  public String getOptionValue(MixinEnvironment$Option paramMixinEnvironment$Option) {
    return paramMixinEnvironment$Option.getStringValue();
  }
  
  public Enum getOption(MixinEnvironment$Option paramMixinEnvironment$Option, Enum paramEnum) {
    return paramMixinEnvironment$Option.getEnumValue(paramEnum);
  }
  
  public void setObfuscationContext(String paramString) {
    paramString = "searge";
    this.obfuscationContext = paramString;
  }
  
  public String getObfuscationContext() {
    return this.obfuscationContext;
  }
  
  public String getRefmapObfuscationContext() {
    return MixinEnvironment$Option.OBFUSCATION_TYPE.getStringValue();
  }
  
  public RemapperChain getRemappers() {
    return this.remappers;
  }
  
  public void audit() {
    Object object = getActiveTransformer();
    if (object instanceof MixinTransformer) {
      MixinTransformer mixinTransformer = (MixinTransformer)object;
      mixinTransformer.audit(this);
    } 
  }
  
  public List getTransformers() {
    if (this.transformers == null)
      buildTransformerDelegationList(); 
    return Collections.unmodifiableList(this.transformers);
  }
  
  public void addTransformerExclusion(String paramString) {
    excludeTransformers.add(paramString);
    this.transformers = null;
  }
  
  private void buildTransformerDelegationList() {
    logger.debug("Rebuilding transformer delegation list:");
    this.transformers = new ArrayList();
    for (ITransformer iTransformer : this.service.getTransformers()) {
      if (!(iTransformer instanceof ILegacyClassTransformer))
        continue; 
      ILegacyClassTransformer iLegacyClassTransformer = (ILegacyClassTransformer)iTransformer;
      String str = iLegacyClassTransformer.getName();
      boolean bool = true;
      for (String str1 : excludeTransformers) {
        if (str.contains(str1)) {
          bool = false;
          break;
        } 
      } 
      if (!iLegacyClassTransformer.isDelegationExcluded()) {
        logger.debug("  Adding:    {}", new Object[] { str });
        this.transformers.add(iLegacyClassTransformer);
        continue;
      } 
      logger.debug("  Excluding: {}", new Object[] { str });
    } 
    logger.debug("Transformer delegation list created with {} entries", new Object[] { Integer.valueOf(this.transformers.size()) });
  }
  
  public String toString() {
    return String.format("%s[%s]", new Object[] { getClass().getSimpleName(), this.phase });
  }
  
  private static MixinEnvironment$Phase getCurrentPhase() {
    if (currentPhase == MixinEnvironment$Phase.NOT_INITIALISED)
      init(MixinEnvironment$Phase.PREINIT); 
    return currentPhase;
  }
  
  public static void init(MixinEnvironment$Phase paramMixinEnvironment$Phase) {
    if (currentPhase == MixinEnvironment$Phase.NOT_INITIALISED) {
      currentPhase = paramMixinEnvironment$Phase;
      MixinEnvironment mixinEnvironment = getEnvironment(paramMixinEnvironment$Phase);
      getProfiler().setActive(mixinEnvironment.getOption(MixinEnvironment$Option.DEBUG_PROFILER));
      new MixinEnvironment$MixinLogger();
    } 
  }
  
  public static MixinEnvironment getEnvironment(MixinEnvironment$Phase paramMixinEnvironment$Phase) {
    return MixinEnvironment$Phase.DEFAULT.getEnvironment();
  }
  
  public static MixinEnvironment getDefaultEnvironment() {
    return getEnvironment(MixinEnvironment$Phase.DEFAULT);
  }
  
  public static MixinEnvironment getCurrentEnvironment() {
    if (currentEnvironment == null)
      currentEnvironment = getEnvironment(getCurrentPhase()); 
    return currentEnvironment;
  }
  
  public static MixinEnvironment$CompatibilityLevel getCompatibilityLevel() {
    return compatibility;
  }
  
  @Deprecated
  @Deprecated
  public static void setCompatibilityLevel(MixinEnvironment$CompatibilityLevel paramMixinEnvironment$CompatibilityLevel) throws IllegalArgumentException {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if (!"org.spongepowered.asm.mixin.transformer.MixinConfig".equals(arrayOfStackTraceElement[2].getClassName()))
      logger.warn("MixinEnvironment::setCompatibilityLevel is deprecated and will be removed. Set level via config instead!"); 
    if (paramMixinEnvironment$CompatibilityLevel != compatibility && paramMixinEnvironment$CompatibilityLevel.isAtLeast(compatibility)) {
      if (!paramMixinEnvironment$CompatibilityLevel.isSupported())
        throw new IllegalArgumentException("The requested compatibility level " + paramMixinEnvironment$CompatibilityLevel + " could not be set. Level is not supported"); 
      compatibility = paramMixinEnvironment$CompatibilityLevel;
      logger.info("Compatibility level set to {}", new Object[] { paramMixinEnvironment$CompatibilityLevel });
    } 
  }
  
  public static Profiler getProfiler() {
    return profiler;
  }
  
  static void gotoPhase(MixinEnvironment$Phase paramMixinEnvironment$Phase) {
    if (paramMixinEnvironment$Phase.ordinal < 0)
      throw new IllegalArgumentException("Cannot go to the specified phase, phase is null or invalid"); 
    if (paramMixinEnvironment$Phase.ordinal > (getCurrentPhase()).ordinal)
      MixinService.getService().beginPhase(); 
    if (paramMixinEnvironment$Phase == MixinEnvironment$Phase.DEFAULT) {
      Logger logger = (Logger)LogManager.getLogger("FML");
      logger.removeAppender((Appender)MixinEnvironment$MixinLogger.appender);
    } 
    currentPhase = paramMixinEnvironment$Phase;
    currentEnvironment = getEnvironment(getCurrentPhase());
  }
}
