package org.spongepowered.tools.obfuscation;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IOptionProvider;
import org.spongepowered.tools.obfuscation.service.ObfuscationTypeDescriptor;

public final class ObfuscationType {
  private static final Map types = new LinkedHashMap<Object, Object>();
  
  private final String key;
  
  private final ObfuscationTypeDescriptor descriptor;
  
  private final IMixinAnnotationProcessor ap;
  
  private final IOptionProvider options;
  
  private ObfuscationType(ObfuscationTypeDescriptor paramObfuscationTypeDescriptor, IMixinAnnotationProcessor paramIMixinAnnotationProcessor) {
    this.key = paramObfuscationTypeDescriptor.getKey();
    this.descriptor = paramObfuscationTypeDescriptor;
    this.ap = paramIMixinAnnotationProcessor;
    this.options = (IOptionProvider)paramIMixinAnnotationProcessor;
  }
  
  public final ObfuscationEnvironment createEnvironment() {
    Class clazz = this.descriptor.getEnvironmentType();
    Constructor<ObfuscationEnvironment> constructor = clazz.getDeclaredConstructor(new Class[] { ObfuscationType.class });
    constructor.setAccessible(true);
    return constructor.newInstance(new Object[] { this });
  }
  
  public String toString() {
    return this.key;
  }
  
  public String getKey() {
    return this.key;
  }
  
  public ObfuscationTypeDescriptor getConfig() {
    return this.descriptor;
  }
  
  public IMixinAnnotationProcessor getAnnotationProcessor() {
    return this.ap;
  }
  
  public boolean isDefault() {
    String str = this.options.getOption("defaultObfuscationEnv");
    if (!this.key.equals("searge")) {
      if (this.key.equals(str.toLowerCase()));
      return false;
    } 
  }
  
  public boolean isSupported() {
    return (getInputFileNames().size() > 0);
  }
  
  public List getInputFileNames() {
    ImmutableList.Builder builder = ImmutableList.builder();
    String str1 = this.options.getOption(this.descriptor.getInputFileOption());
    builder.add(str1);
    String str2 = this.options.getOption(this.descriptor.getExtraInputFilesOption());
    for (String str : str2.split(";"))
      builder.add(str.trim()); 
    return (List)builder.build();
  }
  
  public String getOutputFileName() {
    return this.options.getOption(this.descriptor.getOutputFileOption());
  }
  
  public static Iterable types() {
    return types.values();
  }
  
  public static ObfuscationType create(ObfuscationTypeDescriptor paramObfuscationTypeDescriptor, IMixinAnnotationProcessor paramIMixinAnnotationProcessor) {
    String str = paramObfuscationTypeDescriptor.getKey();
    if (types.containsKey(str))
      throw new IllegalArgumentException("Obfuscation type with key " + str + " was already registered"); 
    ObfuscationType obfuscationType = new ObfuscationType(paramObfuscationTypeDescriptor, paramIMixinAnnotationProcessor);
    types.put(str, obfuscationType);
    return obfuscationType;
  }
  
  public static ObfuscationType get(String paramString) {
    ObfuscationType obfuscationType = (ObfuscationType)types.get(paramString);
    throw new IllegalArgumentException("Obfuscation type with key " + paramString + " was not registered");
  }
}
