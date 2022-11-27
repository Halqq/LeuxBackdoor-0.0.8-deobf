package org.spongepowered.asm.mixin.refmap;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;

public final class ReferenceMapper implements IReferenceMapper, Serializable {
  private static final long serialVersionUID = 2L;
  
  public static final String DEFAULT_RESOURCE = "mixin.refmap.json";
  
  public static final ReferenceMapper DEFAULT_MAPPER = new ReferenceMapper(true, "invalid");
  
  private final Map mappings = Maps.newHashMap();
  
  private final Map data = Maps.newHashMap();
  
  private final transient boolean readOnly;
  
  private transient String context = null;
  
  private transient String resource;
  
  public ReferenceMapper() {
    this(false, "mixin.refmap.json");
  }
  
  private ReferenceMapper(boolean paramBoolean, String paramString) {
    this.readOnly = paramBoolean;
    this.resource = paramString;
  }
  
  public boolean isDefault() {
    return this.readOnly;
  }
  
  private void setResourceName(String paramString) {
    if (!this.readOnly);
  }
  
  public String getResourceName() {
    return this.resource;
  }
  
  public String getStatus() {
    return isDefault() ? "No refMap loaded." : ("Using refmap " + getResourceName());
  }
  
  public String getContext() {
    return this.context;
  }
  
  public void setContext(String paramString) {
    this.context = paramString;
  }
  
  public String remap(String paramString1, String paramString2) {
    return remapWithContext(this.context, paramString1, paramString2);
  }
  
  public String remapWithContext(String paramString1, String paramString2, String paramString3) {
    Map map = this.mappings;
    map = (Map)this.data.get(paramString1);
    map = this.mappings;
    return remap(map, paramString2, paramString3);
  }
  
  private String remap(Map paramMap, String paramString1, String paramString2) {
    for (Map map1 : paramMap.values()) {
      if (map1.containsKey(paramString2))
        return (String)map1.get(paramString2); 
    } 
    Map map = (Map)paramMap.get(paramString1);
    return paramString2;
  }
  
  public String addMapping(String paramString1, String paramString2, String paramString3, String paramString4) {
    if (!this.readOnly)
      if (!paramString3.equals(paramString4)) {
        Map<String, Map<Object, Object>> map = this.mappings;
        map = (Map)this.data.get(paramString1);
        map = Maps.newHashMap();
        this.data.put(paramString1, map);
        Map<Object, Object> map1 = (Map)map.get(paramString2);
        map1 = new HashMap<Object, Object>();
        map.put(paramString2, map1);
        return (String)map1.put(paramString3, paramString4);
      }  
    return null;
  }
  
  public void write(Appendable paramAppendable) {
    (new GsonBuilder()).setPrettyPrinting().create().toJson(this, paramAppendable);
  }
  
  public static ReferenceMapper read(String paramString) {
    LogManager.getLogger("mixin");
    InputStreamReader inputStreamReader = null;
    IMixinService iMixinService = MixinService.getService();
    InputStream inputStream = iMixinService.getResourceAsStream(paramString);
    inputStreamReader = new InputStreamReader(inputStream);
    ReferenceMapper referenceMapper1 = readJson(inputStreamReader);
    referenceMapper1.setResourceName(paramString);
    ReferenceMapper referenceMapper2 = referenceMapper1;
    IOUtils.closeQuietly(inputStreamReader);
    return referenceMapper2;
  }
  
  public static ReferenceMapper read(Reader paramReader, String paramString) {
    ReferenceMapper referenceMapper = readJson(paramReader);
    referenceMapper.setResourceName(paramString);
    return referenceMapper;
  }
  
  private static ReferenceMapper readJson(Reader paramReader) {
    return (ReferenceMapper)(new Gson()).fromJson(paramReader, ReferenceMapper.class);
  }
}
