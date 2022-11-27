package org.spongepowered.asm.launch;

import java.util.ServiceLoader;
import org.spongepowered.asm.service.IGlobalPropertyService;

public final class GlobalProperties {
  private static IGlobalPropertyService service;
  
  private static IGlobalPropertyService getService() {
    if (service == null) {
      ServiceLoader<IGlobalPropertyService> serviceLoader = ServiceLoader.load(IGlobalPropertyService.class, GlobalProperties.class.getClassLoader());
      service = serviceLoader.iterator().next();
    } 
    return service;
  }
  
  public static Object get(String paramString) {
    return getService().getProperty(paramString);
  }
  
  public static void put(String paramString, Object paramObject) {
    getService().setProperty(paramString, paramObject);
  }
  
  public static Object get(String paramString, Object paramObject) {
    return getService().getProperty(paramString, paramObject);
  }
  
  public static String getString(String paramString1, String paramString2) {
    return getService().getPropertyString(paramString1, paramString2);
  }
}
