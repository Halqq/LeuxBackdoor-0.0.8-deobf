package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.extensibility.IMixinConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTargetAlreadyLoadedException;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.perf.Profiler;

class MixinInfo implements Comparable, IMixinInfo {
  private static final IMixinService classLoaderUtil = MixinService.getService();
  
  static int mixinOrder = 0;
  
  private final transient Logger logger = LogManager.getLogger("mixin");
  
  private final transient Profiler profiler = MixinEnvironment.getProfiler();
  
  private final transient MixinConfig parent;
  
  private final String name;
  
  private final String className;
  
  private final int priority;
  
  private final boolean virtual;
  
  private final List targetClasses;
  
  private final List targetClassNames;
  
  private final transient int order = mixinOrder++;
  
  private final transient IMixinService service;
  
  private final transient IMixinConfigPlugin plugin;
  
  private final transient MixinEnvironment.Phase phase;
  
  private final transient ClassInfo info;
  
  private final transient MixinInfo$SubType type;
  
  private final transient boolean strict;
  
  private transient MixinInfo$State pendingState;
  
  private transient MixinInfo$State state;
  
  MixinInfo(IMixinService paramIMixinService, MixinConfig paramMixinConfig, String paramString, boolean paramBoolean1, IMixinConfigPlugin paramIMixinConfigPlugin, boolean paramBoolean2) {
    this.service = paramIMixinService;
    this.parent = paramMixinConfig;
    this.name = paramString;
    this.className = paramMixinConfig.getMixinPackage() + paramString;
    this.plugin = paramIMixinConfigPlugin;
    this.phase = paramMixinConfig.getEnvironment().getPhase();
    this.strict = paramMixinConfig.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_TARGETS);
    byte[] arrayOfByte = loadMixinClass(this.className, paramBoolean1);
    this.pendingState = new MixinInfo$State(this, arrayOfByte);
    this.info = this.pendingState.getClassInfo();
    this.type = MixinInfo$SubType.getTypeFor(this);
    if (!this.type.isLoadable())
      classLoaderUtil.registerInvalidClass(this.className); 
    this.priority = readPriority(this.pendingState.getClassNode());
    this.virtual = readPseudo(this.pendingState.getClassNode());
    this.targetClasses = readTargetClasses(this.pendingState.getClassNode(), paramBoolean2);
    this.targetClassNames = Collections.unmodifiableList(Lists.transform(this.targetClasses, Functions.toStringFunction()));
  }
  
  void validate() {
    if (this.pendingState == null)
      throw new IllegalStateException("No pending validation state for " + this); 
    this.pendingState.validate(this.type, this.targetClasses);
    this.state = this.pendingState;
    this.pendingState = null;
  }
  
  protected List readTargetClasses(MixinInfo$MixinClassNode paramMixinInfo$MixinClassNode, boolean paramBoolean) {
    return Collections.emptyList();
  }
  
  private void readTargets(Collection<ClassInfo> paramCollection1, Collection paramCollection2, boolean paramBoolean1, boolean paramBoolean2) {
    for (String str1 : paramCollection2) {
      String str2 = str1.replace('/', '.');
      if (classLoaderUtil.isClassLoaded(str2) && !isReloading()) {
        String str = String.format("Critical problem: %s target %s was already transformed.", new Object[] { this, str2 });
        if (this.parent.isRequired())
          throw new MixinTargetAlreadyLoadedException(this, str, str2); 
        this.logger.error(str);
      } 
      if (shouldApplyMixin(paramBoolean1, str2)) {
        ClassInfo classInfo = getTarget(str2, paramBoolean2);
        if (!paramCollection1.contains(classInfo)) {
          paramCollection1.add(classInfo);
          classInfo.addMixin(this);
        } 
      } 
    } 
  }
  
  private boolean shouldApplyMixin(boolean paramBoolean, String paramString) {
    Profiler.Section section = this.profiler.begin("plugin");
    if (this.plugin != null) {
      if (this.plugin.shouldApplyMixin(paramString, this.className));
      boolean bool = false;
      section.end();
      return bool;
    } 
  }
  
  private ClassInfo getTarget(String paramString, boolean paramBoolean) throws InvalidMixinException {
    ClassInfo classInfo = ClassInfo.forName(paramString);
    if (isVirtual()) {
      this.logger.debug("Skipping virtual target {} for {}", new Object[] { paramString, this });
    } else {
      handleTargetError(String.format("@Mixin target %s was not found %s", new Object[] { paramString, this }));
    } 
    return null;
  }
  
  private void handleTargetError(String paramString) {
    if (this.strict) {
      this.logger.error(paramString);
      throw new InvalidMixinException(this, paramString);
    } 
    this.logger.warn(paramString);
  }
  
  protected int readPriority(ClassNode paramClassNode) {
    return this.parent.getDefaultMixinPriority();
  }
  
  protected boolean readPseudo(ClassNode paramClassNode) {
    return (Annotations.getInvisible(paramClassNode, Pseudo.class) != null);
  }
  
  private boolean isReloading() {
    return this.pendingState instanceof MixinInfo$Reloaded;
  }
  
  private MixinInfo$State getState() {
    return (this.state != null) ? this.state : this.pendingState;
  }
  
  ClassInfo getClassInfo() {
    return this.info;
  }
  
  public IMixinConfig getConfig() {
    return this.parent;
  }
  
  MixinConfig getParent() {
    return this.parent;
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getClassName() {
    return this.className;
  }
  
  public String getClassRef() {
    return getClassInfo().getName();
  }
  
  public byte[] getClassBytes() {
    return getState().getClassBytes();
  }
  
  public boolean isDetachedSuper() {
    return getState().isDetachedSuper();
  }
  
  public boolean isUnique() {
    return getState().isUnique();
  }
  
  public boolean isVirtual() {
    return this.virtual;
  }
  
  public boolean isAccessor() {
    return this.type instanceof MixinInfo$SubType$Accessor;
  }
  
  public boolean isLoadable() {
    return this.type.isLoadable();
  }
  
  public Level getLoggingLevel() {
    return this.parent.getLoggingLevel();
  }
  
  public MixinEnvironment.Phase getPhase() {
    return this.phase;
  }
  
  public MixinInfo$MixinClassNode getClassNode(int paramInt) {
    return getState().createClassNode(paramInt);
  }
  
  public List getTargetClasses() {
    return this.targetClassNames;
  }
  
  List getSoftImplements() {
    return Collections.unmodifiableList(getState().getSoftImplements());
  }
  
  Set getSyntheticInnerClasses() {
    return Collections.unmodifiableSet(getState().getSyntheticInnerClasses());
  }
  
  Set getInnerClasses() {
    return Collections.unmodifiableSet(getState().getInnerClasses());
  }
  
  List getTargets() {
    return Collections.unmodifiableList(this.targetClasses);
  }
  
  Set getInterfaces() {
    return getState().getInterfaces();
  }
  
  MixinTargetContext createContextFor(TargetClassContext paramTargetClassContext) {
    MixinInfo$MixinClassNode mixinInfo$MixinClassNode = getClassNode(8);
    Profiler.Section section = this.profiler.begin("pre");
    MixinTargetContext mixinTargetContext = this.type.createPreProcessor(mixinInfo$MixinClassNode).prepare().createContextFor(paramTargetClassContext);
    section.end();
    return mixinTargetContext;
  }
  
  private byte[] loadMixinClass(String paramString, boolean paramBoolean) throws ClassNotFoundException {
    paramBoolean = true;
    null = null;
    return this.service.getBytecodeProvider().getClassBytes(paramString, paramBoolean);
  }
  
  void reloadMixin(byte[] paramArrayOfbyte) {
    if (this.pendingState != null)
      throw new IllegalStateException("Cannot reload mixin while it is initialising"); 
    this.pendingState = new MixinInfo$Reloaded(this, this.state, paramArrayOfbyte);
    validate();
  }
  
  public int compareTo(MixinInfo paramMixinInfo) {
    return 0;
  }
  
  public void preApply(String paramString, ClassNode paramClassNode) {
    if (this.plugin != null) {
      Profiler.Section section = this.profiler.begin("plugin");
      this.plugin.preApply(paramString, paramClassNode, this.className, this);
      section.end();
    } 
  }
  
  public void postApply(String paramString, ClassNode paramClassNode) {
    if (this.plugin != null) {
      Profiler.Section section = this.profiler.begin("plugin");
      this.plugin.postApply(paramString, paramClassNode, this.className, this);
      section.end();
    } 
    this.parent.postApply(paramString, paramClassNode);
  }
  
  public String toString() {
    return String.format("%s:%s", new Object[] { this.parent.getName(), this.name });
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((MixinInfo)paramObject);
  }
  
  public ClassNode getClassNode(int paramInt) {
    return getClassNode(paramInt);
  }
}
