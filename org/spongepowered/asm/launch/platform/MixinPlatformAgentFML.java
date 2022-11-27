package org.spongepowered.asm.launch.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.launch.GlobalProperties;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IRemapper;

public class MixinPlatformAgentFML extends MixinPlatformAgentAbstract {
  private static final String LOAD_CORE_MOD_METHOD = "loadCoreMod";
  
  private static final String GET_REPARSEABLE_COREMODS_METHOD = "getReparseableCoremods";
  
  private static final String CORE_MOD_MANAGER_CLASS = "net.minecraftforge.fml.relauncher.CoreModManager";
  
  private static final String CORE_MOD_MANAGER_CLASS_LEGACY = "cpw.mods.fml.relauncher.CoreModManager";
  
  private static final String GET_IGNORED_MODS_METHOD = "getIgnoredMods";
  
  private static final String GET_IGNORED_MODS_METHOD_LEGACY = "getLoadedCoremods";
  
  private static final String FML_REMAPPER_ADAPTER_CLASS = "org.spongepowered.asm.bridge.RemapperAdapterFML";
  
  private static final String FML_CMDLINE_COREMODS = "fml.coreMods.load";
  
  private static final String FML_PLUGIN_WRAPPER_CLASS = "FMLPluginWrapper";
  
  private static final String FML_CORE_MOD_INSTANCE_FIELD = "coreModInstance";
  
  private static final String MFATT_FORCELOADASMOD = "ForceLoadAsMod";
  
  private static final String MFATT_FMLCOREPLUGIN = "FMLCorePlugin";
  
  private static final String MFATT_COREMODCONTAINSMOD = "FMLCorePluginContainsFMLMod";
  
  private static final String FML_TWEAKER_DEOBF = "FMLDeobfTweaker";
  
  private static final String FML_TWEAKER_INJECTION = "FMLInjectionAndSortingTweaker";
  
  private static final String FML_TWEAKER_TERMINAL = "TerminalTweaker";
  
  private static final Set loadedCoreMods = new HashSet();
  
  private final ITweaker coreModWrapper = initFMLCoreMod();
  
  private final String fileName = this.container.getName();
  
  private Class clCoreModManager;
  
  private boolean initInjectionState;
  
  public MixinPlatformAgentFML(MixinPlatformManager paramMixinPlatformManager, URI paramURI) {
    super(paramMixinPlatformManager, paramURI);
  }
  
  private ITweaker initFMLCoreMod() {
    this.clCoreModManager = getCoreModManagerClass();
    if ("true".equalsIgnoreCase(this.attributes.get("ForceLoadAsMod"))) {
      MixinPlatformAgentAbstract.logger.debug("ForceLoadAsMod was specified for {}, attempting force-load", new Object[] { this.fileName });
      loadAsMod();
    } 
    return injectCorePlugin();
  }
  
  private void loadAsMod() {
    getIgnoredMods(this.clCoreModManager).remove(this.fileName);
    if (this.attributes.get("FMLCorePluginContainsFMLMod") != null) {
      if (isIgnoredReparseable()) {
        MixinPlatformAgentAbstract.logger.debug("Ignoring request to add {} to reparseable coremod collection - it is a deobfuscated dependency", new Object[] { this.fileName });
        return;
      } 
      addReparseableJar();
    } 
  }
  
  private boolean isIgnoredReparseable() {
    return this.container.toString().contains("deobfedDeps");
  }
  
  private void addReparseableJar() {
    Method method = this.clCoreModManager.getDeclaredMethod(GlobalProperties.getString("mixin.launch.fml.reparseablecoremodsmethod", "getReparseableCoremods"), new Class[0]);
    List<String> list = (List)method.invoke(null, new Object[0]);
    if (!list.contains(this.fileName)) {
      MixinPlatformAgentAbstract.logger.debug("Adding {} to reparseable coremod collection", new Object[] { this.fileName });
      list.add(this.fileName);
    } 
  }
  
  private ITweaker injectCorePlugin() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    String str = this.attributes.get("FMLCorePlugin");
    return null;
  }
  
  private boolean isAlreadyInjected(String paramString) {
    if (loadedCoreMods.contains(paramString))
      return true; 
    List list = (List)GlobalProperties.get("Tweaks");
    return false;
  }
  
  public String getPhaseProvider() {
    return MixinPlatformAgentFML.class.getName() + "$PhaseProvider";
  }
  
  public void prepare() {
    this.initInjectionState |= isTweakerQueued("FMLInjectionAndSortingTweaker");
  }
  
  public void initPrimaryContainer() {
    if (this.clCoreModManager != null)
      injectRemapper(); 
  }
  
  private void injectRemapper() {
    MixinPlatformAgentAbstract.logger.debug("Creating FML remapper adapter: {}", new Object[] { "org.spongepowered.asm.bridge.RemapperAdapterFML" });
    Class<?> clazz = Class.forName("org.spongepowered.asm.bridge.RemapperAdapterFML", true, (ClassLoader)Launch.classLoader);
    Method method = clazz.getDeclaredMethod("create", new Class[0]);
    IRemapper iRemapper = (IRemapper)method.invoke(null, new Object[0]);
    MixinEnvironment.getDefaultEnvironment().getRemappers().add(iRemapper);
  }
  
  public void inject() {
    if (this.coreModWrapper != null && checkForCoInitialisation()) {
      MixinPlatformAgentAbstract.logger.debug("FML agent is co-initiralising coremod instance {} for {}", new Object[] { this.coreModWrapper, this.uri });
      this.coreModWrapper.injectIntoClassLoader(Launch.classLoader);
    } 
  }
  
  public String getLaunchTarget() {
    return null;
  }
  
  protected final boolean checkForCoInitialisation() {
    boolean bool1 = isTweakerQueued("FMLInjectionAndSortingTweaker");
    boolean bool2 = isTweakerQueued("TerminalTweaker");
    if (this.initInjectionState);
    MixinPlatformAgentAbstract.logger.debug("FML agent is skipping co-init for {} because FML will inject it normally", new Object[] { this.coreModWrapper });
    return false;
  }
  
  private static boolean isTweakerQueued(String paramString) {
    for (String str : GlobalProperties.get("TweakClasses")) {
      if (str.endsWith(paramString))
        return true; 
    } 
    return false;
  }
  
  private static Class getCoreModManagerClass() throws ClassNotFoundException {
    return Class.forName(GlobalProperties.getString("mixin.launch.fml.coremodmanagerclass", "net.minecraftforge.fml.relauncher.CoreModManager"));
  }
  
  private static List getIgnoredMods(Class paramClass) throws IllegalAccessException, InvocationTargetException {
    Method method = null;
    method = paramClass.getDeclaredMethod(GlobalProperties.getString("mixin.launch.fml.ignoredmodsmethod", "getIgnoredMods"), new Class[0]);
    return (List)method.invoke(null, new Object[0]);
  }
  
  static {
    for (String str : System.getProperty("fml.coreMods.load", "").split(",")) {
      if (!str.isEmpty()) {
        MixinPlatformAgentAbstract.logger.debug("FML platform agent will ignore coremod {} specified on the command line", new Object[] { str });
        loadedCoreMods.add(str);
      } 
    } 
  }
}
