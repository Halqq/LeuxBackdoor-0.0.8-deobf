package org.spongepowered.asm.mixin;

public enum MixinEnvironment$Option {
  DEBUG_ALL("debug"),
  DEBUG_EXPORT(DEBUG_ALL, "export"),
  DEBUG_EXPORT_FILTER(DEBUG_EXPORT, "filter", false),
  DEBUG_EXPORT_DECOMPILE(DEBUG_EXPORT, MixinEnvironment$Option$Inherit.ALLOW_OVERRIDE, "decompile"),
  DEBUG_EXPORT_DECOMPILE_THREADED(DEBUG_EXPORT_DECOMPILE, MixinEnvironment$Option$Inherit.ALLOW_OVERRIDE, "async"),
  DEBUG_VERIFY(DEBUG_ALL, "verify"),
  DEBUG_VERBOSE(DEBUG_ALL, "verbose"),
  DEBUG_INJECTORS(DEBUG_ALL, "countInjections"),
  DEBUG_STRICT(DEBUG_ALL, MixinEnvironment$Option$Inherit.INDEPENDENT, "strict"),
  DEBUG_UNIQUE(DEBUG_STRICT, "unique"),
  DEBUG_TARGETS(DEBUG_STRICT, "targets"),
  DEBUG_PROFILER(DEBUG_ALL, MixinEnvironment$Option$Inherit.ALLOW_OVERRIDE, "profiler"),
  DUMP_TARGET_ON_FAILURE("dumpTargetOnFailure"),
  CHECK_ALL("checks"),
  CHECK_IMPLEMENTS(CHECK_ALL, "interfaces"),
  CHECK_IMPLEMENTS_STRICT(CHECK_IMPLEMENTS, MixinEnvironment$Option$Inherit.ALLOW_OVERRIDE, "strict"),
  IGNORE_CONSTRAINTS("ignoreConstraints"),
  HOT_SWAP("hotSwap"),
  ENVIRONMENT(MixinEnvironment$Option$Inherit.ALWAYS_FALSE, "env"),
  OBFUSCATION_TYPE(ENVIRONMENT, MixinEnvironment$Option$Inherit.ALWAYS_FALSE, "obf"),
  DISABLE_REFMAP(ENVIRONMENT, MixinEnvironment$Option$Inherit.INDEPENDENT, "disableRefMap"),
  REFMAP_REMAP(ENVIRONMENT, MixinEnvironment$Option$Inherit.INDEPENDENT, "remapRefMap"),
  REFMAP_REMAP_RESOURCE(ENVIRONMENT, MixinEnvironment$Option$Inherit.INDEPENDENT, "refMapRemappingFile", ""),
  REFMAP_REMAP_SOURCE_ENV(ENVIRONMENT, MixinEnvironment$Option$Inherit.INDEPENDENT, "refMapRemappingEnv", "searge"),
  IGNORE_REQUIRED(ENVIRONMENT, MixinEnvironment$Option$Inherit.INDEPENDENT, "ignoreRequired"),
  DEFAULT_COMPATIBILITY_LEVEL(ENVIRONMENT, MixinEnvironment$Option$Inherit.INDEPENDENT, "compatLevel"),
  SHIFT_BY_VIOLATION_BEHAVIOUR(ENVIRONMENT, MixinEnvironment$Option$Inherit.INDEPENDENT, "shiftByViolation", "warn"),
  INITIALISER_INJECTION_MODE("initialiserInjectionMode", "default");
  
  private static final String PREFIX = "mixin";
  
  final MixinEnvironment$Option parent;
  
  final MixinEnvironment$Option$Inherit inheritance;
  
  final String property;
  
  final String defaultValue;
  
  final boolean isFlag;
  
  final int depth;
  
  private static final MixinEnvironment$Option[] $VALUES = new MixinEnvironment$Option[] { 
      DEBUG_ALL, DEBUG_EXPORT, DEBUG_EXPORT_FILTER, DEBUG_EXPORT_DECOMPILE, DEBUG_EXPORT_DECOMPILE_THREADED, DEBUG_VERIFY, DEBUG_VERBOSE, DEBUG_INJECTORS, DEBUG_STRICT, DEBUG_UNIQUE, 
      DEBUG_TARGETS, DEBUG_PROFILER, DUMP_TARGET_ON_FAILURE, CHECK_ALL, CHECK_IMPLEMENTS, CHECK_IMPLEMENTS_STRICT, IGNORE_CONSTRAINTS, HOT_SWAP, ENVIRONMENT, OBFUSCATION_TYPE, 
      DISABLE_REFMAP, REFMAP_REMAP, REFMAP_REMAP_RESOURCE, REFMAP_REMAP_SOURCE_ENV, IGNORE_REQUIRED, DEFAULT_COMPATIBILITY_LEVEL, SHIFT_BY_VIOLATION_BEHAVIOUR, INITIALISER_INJECTION_MODE };
  
  MixinEnvironment$Option(MixinEnvironment$Option paramMixinEnvironment$Option, MixinEnvironment$Option$Inherit paramMixinEnvironment$Option$Inherit, String paramString1, boolean paramBoolean, String paramString2) {
    this.parent = paramMixinEnvironment$Option;
    this.inheritance = paramMixinEnvironment$Option$Inherit;
  }
  
  MixinEnvironment$Option getParent() {
    return this.parent;
  }
  
  String getProperty() {
    return this.property;
  }
  
  public String toString() {
    return this.isFlag ? String.valueOf(getBooleanValue()) : getStringValue();
  }
  
  private boolean getLocalBooleanValue(boolean paramBoolean) {
    return Boolean.parseBoolean(System.getProperty(this.property, Boolean.toString(paramBoolean)));
  }
  
  private boolean getInheritedBooleanValue() {
    return (this.parent != null && this.parent.getBooleanValue());
  }
  
  final boolean getBooleanValue() {
    if (this.inheritance == MixinEnvironment$Option$Inherit.ALWAYS_FALSE)
      return false; 
    boolean bool = getLocalBooleanValue(false);
    if (this.inheritance == MixinEnvironment$Option$Inherit.INDEPENDENT)
      return bool; 
    boolean bool1 = getInheritedBooleanValue() ? true : false;
    return (this.inheritance == MixinEnvironment$Option$Inherit.INHERIT) ? bool1 : getLocalBooleanValue(bool1);
  }
  
  final String getStringValue() {
    return (this.parent == null || this.parent.getBooleanValue()) ? System.getProperty(this.property, this.defaultValue) : this.defaultValue;
  }
  
  Enum getEnumValue(Enum paramEnum) {
    String str = System.getProperty(this.property, paramEnum.name());
    return (Enum)Enum.valueOf(paramEnum.getClass(), str.toUpperCase());
  }
}
