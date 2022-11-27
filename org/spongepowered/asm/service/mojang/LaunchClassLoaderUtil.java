package org.spongepowered.asm.service.mojang;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.launchwrapper.LaunchClassLoader;

final class LaunchClassLoaderUtil {
  private static final String CACHED_CLASSES_FIELD = "cachedClasses";
  
  private static final String INVALID_CLASSES_FIELD = "invalidClasses";
  
  private static final String CLASS_LOADER_EXCEPTIONS_FIELD = "classLoaderExceptions";
  
  private static final String TRANSFORMER_EXCEPTIONS_FIELD = "transformerExceptions";
  
  private final LaunchClassLoader classLoader;
  
  private final Map cachedClasses;
  
  private final Set invalidClasses;
  
  private final Set classLoaderExceptions;
  
  private final Set transformerExceptions;
  
  LaunchClassLoaderUtil(LaunchClassLoader paramLaunchClassLoader) {
    this.classLoader = paramLaunchClassLoader;
    this.cachedClasses = (Map)getField(paramLaunchClassLoader, "cachedClasses");
    this.invalidClasses = (Set)getField(paramLaunchClassLoader, "invalidClasses");
    this.classLoaderExceptions = (Set)getField(paramLaunchClassLoader, "classLoaderExceptions");
    this.transformerExceptions = (Set)getField(paramLaunchClassLoader, "transformerExceptions");
  }
  
  LaunchClassLoader getClassLoader() {
    return this.classLoader;
  }
  
  boolean isClassLoaded(String paramString) {
    return this.cachedClasses.containsKey(paramString);
  }
  
  boolean isClassExcluded(String paramString1, String paramString2) {
    for (String str : getClassLoaderExceptions()) {
      if (paramString2.startsWith(str) || paramString1.startsWith(str))
        return true; 
    } 
    for (String str : getTransformerExceptions()) {
      if (paramString2.startsWith(str) || paramString1.startsWith(str))
        return true; 
    } 
    return false;
  }
  
  void registerInvalidClass(String paramString) {
    if (this.invalidClasses != null)
      this.invalidClasses.add(paramString); 
  }
  
  Set getClassLoaderExceptions() {
    return (this.classLoaderExceptions != null) ? this.classLoaderExceptions : Collections.emptySet();
  }
  
  Set getTransformerExceptions() {
    return (this.transformerExceptions != null) ? this.transformerExceptions : Collections.emptySet();
  }
  
  private static Object getField(LaunchClassLoader paramLaunchClassLoader, String paramString) {
    Field field = LaunchClassLoader.class.getDeclaredField(paramString);
    field.setAccessible(true);
    return field.get(paramLaunchClassLoader);
  }
}
