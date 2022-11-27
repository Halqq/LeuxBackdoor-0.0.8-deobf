package org.spongepowered.asm.mixin.transformer;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinInitialisationError;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.asm.mixin.refmap.RemappingReferenceMapper;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.VersionNumber;

final class MixinConfig implements Comparable, IMixinConfig {
  private static int configOrder = 0;
  
  private static final Set globalMixinList = new HashSet();
  
  private final Logger logger = LogManager.getLogger("mixin");
  
  private final transient Map mixinMapping = new HashMap<Object, Object>();
  
  private final transient Set unhandledTargets = new HashSet();
  
  private final transient List mixins = new ArrayList();
  
  private transient Config handle;
  
  @SerializedName("target")
  private String selector;
  
  @SerializedName("minVersion")
  private String version;
  
  @SerializedName("compatibilityLevel")
  private String compatibility;
  
  @SerializedName("required")
  private boolean required;
  
  @SerializedName("priority")
  private int priority = 1000;
  
  @SerializedName("mixinPriority")
  private int mixinPriority = 1000;
  
  @SerializedName("package")
  private String mixinPackage;
  
  @SerializedName("mixins")
  private List mixinClasses;
  
  @SerializedName("client")
  private List mixinClassesClient;
  
  @SerializedName("server")
  private List mixinClassesServer;
  
  @SerializedName("setSourceFile")
  private boolean setSourceFile = false;
  
  @SerializedName("refmap")
  private String refMapperConfig;
  
  @SerializedName("verbose")
  private boolean verboseLogging;
  
  private final transient int order = configOrder++;
  
  private final transient List listeners = new ArrayList();
  
  private transient IMixinService service;
  
  private transient MixinEnvironment env;
  
  private transient String name;
  
  @SerializedName("plugin")
  private String pluginClassName;
  
  @SerializedName("injectors")
  private MixinConfig$InjectorOptions injectorOptions = new MixinConfig$InjectorOptions();
  
  @SerializedName("overwrites")
  private MixinConfig$OverwriteOptions overwriteOptions = new MixinConfig$OverwriteOptions();
  
  private transient IMixinConfigPlugin plugin;
  
  private transient IReferenceMapper refMapper;
  
  private transient boolean prepared = false;
  
  private transient boolean visited = false;
  
  private boolean onLoad(IMixinService paramIMixinService, String paramString, MixinEnvironment paramMixinEnvironment) {
    this.service = paramIMixinService;
    this.name = paramString;
    this.env = parseSelector(this.selector, paramMixinEnvironment);
    this.required &= !this.env.getOption(MixinEnvironment.Option.IGNORE_REQUIRED) ? 1 : 0;
    initCompatibilityLevel();
    initInjectionPoints();
    return checkVersion();
  }
  
  private void initCompatibilityLevel() {
    if (this.compatibility == null)
      return; 
    MixinEnvironment.CompatibilityLevel compatibilityLevel1 = MixinEnvironment.CompatibilityLevel.valueOf(this.compatibility.trim().toUpperCase());
    MixinEnvironment.CompatibilityLevel compatibilityLevel2 = MixinEnvironment.getCompatibilityLevel();
    if (compatibilityLevel1 == compatibilityLevel2)
      return; 
    if (compatibilityLevel2.isAtLeast(compatibilityLevel1) && !compatibilityLevel2.canSupport(compatibilityLevel1))
      throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + compatibilityLevel1 + " which is too old"); 
    if (!compatibilityLevel2.canElevateTo(compatibilityLevel1))
      throw new MixinInitialisationError("Mixin config " + this.name + " requires compatibility level " + compatibilityLevel1 + " which is prohibited by " + compatibilityLevel2); 
    MixinEnvironment.setCompatibilityLevel(compatibilityLevel1);
  }
  
  private MixinEnvironment parseSelector(String paramString, MixinEnvironment paramMixinEnvironment) {
    String[] arrayOfString = paramString.split("[&\\| ]");
    for (String str : arrayOfString) {
      str = str.trim();
      Pattern pattern = Pattern.compile("^@env(?:ironment)?\\(([A-Z]+)\\)$");
      Matcher matcher = pattern.matcher(str);
      if (matcher.matches())
        return MixinEnvironment.getEnvironment(MixinEnvironment.Phase.forName(matcher.group(1))); 
    } 
    MixinEnvironment.Phase phase = MixinEnvironment.Phase.forName(paramString);
    return MixinEnvironment.getEnvironment(phase);
  }
  
  private void initInjectionPoints() {
    if (this.injectorOptions.injectionPoints == null)
      return; 
    for (String str : this.injectorOptions.injectionPoints) {
      Class<?> clazz = this.service.getClassProvider().findClass(str, true);
      if (InjectionPoint.class.isAssignableFrom(clazz)) {
        InjectionPoint.register(clazz);
        continue;
      } 
      this.logger.error("Unable to register injection point {} for {}, class must extend InjectionPoint", new Object[] { clazz, this });
    } 
  }
  
  private boolean checkVersion() throws MixinInitialisationError {
    if (this.version == null)
      this.logger.error("Mixin config {} does not specify \"minVersion\" property", new Object[] { this.name }); 
    VersionNumber versionNumber1 = VersionNumber.parse(this.version);
    VersionNumber versionNumber2 = VersionNumber.parse(this.env.getVersion());
    if (versionNumber1.compareTo(versionNumber2) > 0) {
      this.logger.warn("Mixin config {} requires mixin subsystem version {} but {} was found. The mixin config will not be applied.", new Object[] { this.name, versionNumber1, versionNumber2 });
      if (this.required)
        throw new MixinInitialisationError("Required mixin config " + this.name + " requires mixin subsystem version " + versionNumber1); 
      return false;
    } 
    return true;
  }
  
  void addListener(MixinConfig$IListener paramMixinConfig$IListener) {
    this.listeners.add(paramMixinConfig$IListener);
  }
  
  void onSelect() {
    if (this.pluginClassName != null) {
      Class<IMixinConfigPlugin> clazz = this.service.getClassProvider().findClass(this.pluginClassName, true);
      this.plugin = clazz.newInstance();
      if (this.plugin != null)
        this.plugin.onLoad(this.mixinPackage); 
    } 
    if (!this.mixinPackage.endsWith("."))
      this.mixinPackage += "."; 
    boolean bool = false;
    if (this.refMapperConfig == null) {
      if (this.plugin != null)
        this.refMapperConfig = this.plugin.getRefMapperConfig(); 
      if (this.refMapperConfig == null) {
        bool = true;
        this.refMapperConfig = "mixin.refmap.json";
      } 
    } 
    this.refMapper = (IReferenceMapper)ReferenceMapper.read(this.refMapperConfig);
    this.verboseLogging |= this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
    if (this.refMapper.isDefault() && !this.env.getOption(MixinEnvironment.Option.DISABLE_REFMAP))
      this.logger.warn("Reference map '{}' for {} could not be read. If this is a development environment you can ignore this message", new Object[] { this.refMapperConfig, this }); 
    if (this.env.getOption(MixinEnvironment.Option.REFMAP_REMAP))
      this.refMapper = RemappingReferenceMapper.of(this.env, this.refMapper); 
  }
  
  void prepare() {
    if (this.prepared)
      return; 
    this.prepared = true;
    prepareMixins(this.mixinClasses, false);
    switch (MixinConfig$1.$SwitchMap$org$spongepowered$asm$mixin$MixinEnvironment$Side[this.env.getSide().ordinal()]) {
      case 1:
        prepareMixins(this.mixinClassesClient, false);
        return;
      case 2:
        prepareMixins(this.mixinClassesServer, false);
        return;
    } 
    this.logger.warn("Mixin environment was unable to detect the current side, sided mixins will not be applied");
  }
  
  void postInitialise() {
    if (this.plugin != null) {
      List list = this.plugin.getMixins();
      prepareMixins(list, true);
    } 
    for (MixinInfo mixinInfo : this.mixins) {
      mixinInfo.validate();
      for (MixinConfig$IListener mixinConfig$IListener : this.listeners)
        mixinConfig$IListener.onInit(mixinInfo); 
    } 
  }
  
  private void removeMixin(MixinInfo paramMixinInfo) {
    for (List list : this.mixinMapping.values()) {
      Iterator<MixinInfo> iterator = list.iterator();
      while (iterator.hasNext()) {
        if (paramMixinInfo == iterator.next())
          iterator.remove(); 
      } 
    } 
  }
  
  private void prepareMixins(List paramList, boolean paramBoolean) {}
  
  void postApply(String paramString, ClassNode paramClassNode) {
    this.unhandledTargets.remove(paramString);
  }
  
  public Config getHandle() {
    if (this.handle == null)
      this.handle = new Config(this); 
    return this.handle;
  }
  
  public boolean isRequired() {
    return this.required;
  }
  
  public MixinEnvironment getEnvironment() {
    return this.env;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getMixinPackage() {
    return this.mixinPackage;
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public int getDefaultMixinPriority() {
    return this.mixinPriority;
  }
  
  public int getDefaultRequiredInjections() {
    return this.injectorOptions.defaultRequireValue;
  }
  
  public String getDefaultInjectorGroup() {
    String str = this.injectorOptions.defaultGroup;
    return !str.isEmpty() ? str : "default";
  }
  
  public boolean conformOverwriteVisibility() {
    return this.overwriteOptions.conformAccessModifiers;
  }
  
  public boolean requireOverwriteAnnotations() {
    return this.overwriteOptions.requireOverwriteAnnotations;
  }
  
  public int getMaxShiftByValue() {
    return Math.min(Math.max(this.injectorOptions.maxShiftBy, 0), 0);
  }
  
  public boolean select(MixinEnvironment paramMixinEnvironment) {
    this.visited = true;
    return (this.env == paramMixinEnvironment);
  }
  
  boolean isVisited() {
    return this.visited;
  }
  
  int getDeclaredMixinCount() {
    return getCollectionSize(new Collection[] { this.mixinClasses, this.mixinClassesClient, this.mixinClassesServer });
  }
  
  int getMixinCount() {
    return this.mixins.size();
  }
  
  public List getClasses() {
    return Collections.unmodifiableList(this.mixinClasses);
  }
  
  public boolean shouldSetSourceFile() {
    return this.setSourceFile;
  }
  
  public IReferenceMapper getReferenceMapper() {
    if (this.env.getOption(MixinEnvironment.Option.DISABLE_REFMAP))
      return (IReferenceMapper)ReferenceMapper.DEFAULT_MAPPER; 
    this.refMapper.setContext(this.env.getRefmapObfuscationContext());
    return this.refMapper;
  }
  
  String remapClassName(String paramString1, String paramString2) {
    return getReferenceMapper().remap(paramString1, paramString2);
  }
  
  public IMixinConfigPlugin getPlugin() {
    return this.plugin;
  }
  
  public Set getTargets() {
    return Collections.unmodifiableSet(this.mixinMapping.keySet());
  }
  
  public Set getUnhandledTargets() {
    return Collections.unmodifiableSet(this.unhandledTargets);
  }
  
  public Level getLoggingLevel() {
    return this.verboseLogging ? Level.INFO : Level.DEBUG;
  }
  
  public boolean packageMatch(String paramString) {
    return paramString.startsWith(this.mixinPackage);
  }
  
  public boolean hasMixinsFor(String paramString) {
    return this.mixinMapping.containsKey(paramString);
  }
  
  public List getMixinsFor(String paramString) {
    return mixinsFor(paramString);
  }
  
  private List mixinsFor(String paramString) {
    List list = (List)this.mixinMapping.get(paramString);
    list = new ArrayList();
    this.mixinMapping.put(paramString, list);
    return list;
  }
  
  public List reloadMixin(String paramString, byte[] paramArrayOfbyte) {
    for (MixinInfo mixinInfo : this.mixins) {
      if (mixinInfo.getClassName().equals(paramString)) {
        mixinInfo.reloadMixin(paramArrayOfbyte);
        return mixinInfo.getTargetClasses();
      } 
    } 
    return Collections.emptyList();
  }
  
  public String toString() {
    return this.name;
  }
  
  public int compareTo(MixinConfig paramMixinConfig) {
    return 0;
  }
  
  static Config create(String paramString, MixinEnvironment paramMixinEnvironment) {
    IMixinService iMixinService = MixinService.getService();
    MixinConfig mixinConfig = (MixinConfig)(new Gson()).fromJson(new InputStreamReader(iMixinService.getResourceAsStream(paramString)), MixinConfig.class);
    return mixinConfig.onLoad(iMixinService, paramString, paramMixinEnvironment) ? mixinConfig.getHandle() : null;
  }
  
  private static int getCollectionSize(Collection... paramVarArgs) {
    int i = 0;
    for (Collection collection : paramVarArgs)
      i += collection.size(); 
    return i;
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((MixinConfig)paramObject);
  }
}
