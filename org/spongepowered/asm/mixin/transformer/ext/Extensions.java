package org.spongepowered.asm.mixin.transformer.ext;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgsClassGenerator;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;

public final class Extensions {
  private final MixinTransformer transformer;
  
  private final List extensions = new ArrayList();
  
  private final Map extensionMap = new HashMap<Object, Object>();
  
  private final List generators = new ArrayList();
  
  private final List generatorsView = Collections.unmodifiableList(this.generators);
  
  private final Map generatorMap = new HashMap<Object, Object>();
  
  private List activeExtensions = Collections.emptyList();
  
  public Extensions(MixinTransformer paramMixinTransformer) {
    this.transformer = paramMixinTransformer;
  }
  
  public MixinTransformer getTransformer() {
    return this.transformer;
  }
  
  public void add(IExtension paramIExtension) {
    this.extensions.add(paramIExtension);
    this.extensionMap.put(paramIExtension.getClass(), paramIExtension);
  }
  
  public List getExtensions() {
    return Collections.unmodifiableList(this.extensions);
  }
  
  public List getActiveExtensions() {
    return this.activeExtensions;
  }
  
  public IExtension getExtension(Class paramClass) {
    return (IExtension)lookup(paramClass, this.extensionMap, this.extensions);
  }
  
  public void select(MixinEnvironment paramMixinEnvironment) {
    ImmutableList.Builder builder = ImmutableList.builder();
    for (IExtension iExtension : this.extensions) {
      if (iExtension.checkActive(paramMixinEnvironment))
        builder.add(iExtension); 
    } 
    this.activeExtensions = (List)builder.build();
  }
  
  public void preApply(ITargetClassContext paramITargetClassContext) {
    for (IExtension iExtension : this.activeExtensions)
      iExtension.preApply(paramITargetClassContext); 
  }
  
  public void postApply(ITargetClassContext paramITargetClassContext) {
    for (IExtension iExtension : this.activeExtensions)
      iExtension.postApply(paramITargetClassContext); 
  }
  
  public void export(MixinEnvironment paramMixinEnvironment, String paramString, boolean paramBoolean, byte[] paramArrayOfbyte) {
    for (IExtension iExtension : this.activeExtensions)
      iExtension.export(paramMixinEnvironment, paramString, paramBoolean, paramArrayOfbyte); 
  }
  
  public void add(IClassGenerator paramIClassGenerator) {
    this.generators.add(paramIClassGenerator);
    this.generatorMap.put(paramIClassGenerator.getClass(), paramIClassGenerator);
  }
  
  public List getGenerators() {
    return this.generatorsView;
  }
  
  public IClassGenerator getGenerator(Class<ArgsClassGenerator> paramClass) {
    paramClass = ArgsClassGenerator.class;
    return (IClassGenerator)lookup(paramClass, this.generatorMap, this.generators);
  }
  
  private static Object lookup(Class paramClass, Map paramMap, List paramList) {
    Object object = paramMap.get(paramClass);
    for (Object object1 : paramList) {
      if (paramClass.isAssignableFrom(object1.getClass())) {
        object = object1;
        break;
      } 
    } 
    throw new IllegalArgumentException("Extension for <" + paramClass.getName() + "> could not be found");
  }
}
