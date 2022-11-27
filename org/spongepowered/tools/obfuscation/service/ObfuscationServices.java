package org.spongepowered.tools.obfuscation.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.ObfuscationType;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;

public final class ObfuscationServices {
  private static ObfuscationServices instance;
  
  private final ServiceLoader serviceLoader = ServiceLoader.load(IObfuscationService.class, getClass().getClassLoader());
  
  private final Set services = new HashSet();
  
  public static ObfuscationServices getInstance() {
    if (instance == null)
      instance = new ObfuscationServices(); 
    return instance;
  }
  
  public void initProviders(IMixinAnnotationProcessor paramIMixinAnnotationProcessor) {
    for (IObfuscationService iObfuscationService : this.serviceLoader) {
      if (!this.services.contains(iObfuscationService)) {
        this.services.add(iObfuscationService);
        String str = iObfuscationService.getClass().getSimpleName();
        Collection collection = iObfuscationService.getObfuscationTypes();
        for (ObfuscationTypeDescriptor obfuscationTypeDescriptor : collection) {
          ObfuscationType obfuscationType = ObfuscationType.create(obfuscationTypeDescriptor, paramIMixinAnnotationProcessor);
          paramIMixinAnnotationProcessor.printMessage(Diagnostic.Kind.NOTE, str + " supports type: \"" + obfuscationType + "\"");
        } 
      } 
    } 
  }
  
  public Set getSupportedOptions() {
    HashSet hashSet = new HashSet();
    for (IObfuscationService iObfuscationService : this.serviceLoader) {
      Set set = iObfuscationService.getSupportedOptions();
      hashSet.addAll(set);
    } 
    return hashSet;
  }
  
  public IObfuscationService getService(Class paramClass) {
    for (IObfuscationService iObfuscationService : this.serviceLoader) {
      if (paramClass.getName().equals(iObfuscationService.getClass().getName()))
        return iObfuscationService; 
    } 
    return null;
  }
}
