package org.spongepowered.asm.mixin.refmap;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;

public final class RemappingReferenceMapper implements IReferenceMapper {
  private static final String DEFAULT_RESOURCE_PATH_PROPERTY = "net.minecraftforge.gradle.GradleStart.srg.srg-mcp";
  
  private static final String DEFAULT_MAPPING_ENV = "searge";
  
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private static final Map srgs = new HashMap<Object, Object>();
  
  private final IReferenceMapper refMap;
  
  private final Map mappings;
  
  private final Map cache = new HashMap<Object, Object>();
  
  private RemappingReferenceMapper(MixinEnvironment paramMixinEnvironment, IReferenceMapper paramIReferenceMapper) {
    this.refMap = paramIReferenceMapper;
    this.refMap.setContext(getMappingEnv(paramMixinEnvironment));
    String str = getResource(paramMixinEnvironment);
    this.mappings = loadSrgs(str);
    logger.info("Remapping refMap {} using {}", new Object[] { paramIReferenceMapper.getResourceName(), str });
  }
  
  public boolean isDefault() {
    return this.refMap.isDefault();
  }
  
  public String getResourceName() {
    return this.refMap.getResourceName();
  }
  
  public String getStatus() {
    return this.refMap.getStatus();
  }
  
  public String getContext() {
    return this.refMap.getContext();
  }
  
  public void setContext(String paramString) {}
  
  public String remap(String paramString1, String paramString2) {
    Map<String, String> map = getCache(paramString1);
    String str = (String)map.get(paramString2);
    str = this.refMap.remap(paramString1, paramString2);
    for (Map.Entry entry : this.mappings.entrySet())
      str = str.replace((CharSequence)entry.getKey(), (CharSequence)entry.getValue()); 
    map.put(paramString2, str);
    return str;
  }
  
  private Map getCache(String paramString) {
    Map<Object, Object> map = (Map)this.cache.get(paramString);
    map = new HashMap<Object, Object>();
    this.cache.put(paramString, map);
    return map;
  }
  
  public String remapWithContext(String paramString1, String paramString2, String paramString3) {
    return this.refMap.remapWithContext(paramString1, paramString2, paramString3);
  }
  
  private static Map loadSrgs(String paramString) {
    if (srgs.containsKey(paramString))
      return (Map)srgs.get(paramString); 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    srgs.put(paramString, hashMap);
    File file = new File(paramString);
    if (!file.isFile())
      return hashMap; 
    Files.readLines(file, Charsets.UTF_8, new RemappingReferenceMapper$1(hashMap));
    return hashMap;
  }
  
  public static IReferenceMapper of(MixinEnvironment paramMixinEnvironment, IReferenceMapper paramIReferenceMapper) {
    return (!paramIReferenceMapper.isDefault() && hasData(paramMixinEnvironment)) ? new RemappingReferenceMapper(paramMixinEnvironment, paramIReferenceMapper) : paramIReferenceMapper;
  }
  
  private static boolean hasData(MixinEnvironment paramMixinEnvironment) {
    String str = getResource(paramMixinEnvironment);
    return (new File(str)).exists();
  }
  
  private static String getResource(MixinEnvironment paramMixinEnvironment) {
    String str = paramMixinEnvironment.getOptionValue(MixinEnvironment.Option.REFMAP_REMAP_RESOURCE);
    return Strings.isNullOrEmpty(str) ? System.getProperty("net.minecraftforge.gradle.GradleStart.srg.srg-mcp") : str;
  }
  
  private static String getMappingEnv(MixinEnvironment paramMixinEnvironment) {
    String str = paramMixinEnvironment.getOptionValue(MixinEnvironment.Option.REFMAP_REMAP_SOURCE_ENV);
    return Strings.isNullOrEmpty(str) ? "searge" : str;
  }
}
