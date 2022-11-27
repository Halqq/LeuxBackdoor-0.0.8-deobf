package org.spongepowered.asm.service;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MixinService {
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private static MixinService instance;
  
  private ServiceLoader bootstrapServiceLoader;
  
  private final Set bootedServices = new HashSet();
  
  private ServiceLoader serviceLoader;
  
  private IMixinService service = null;
  
  private MixinService() {
    runBootServices();
  }
  
  private void runBootServices() {
    this.bootstrapServiceLoader = ServiceLoader.load(IMixinServiceBootstrap.class, getClass().getClassLoader());
    for (IMixinServiceBootstrap iMixinServiceBootstrap : this.bootstrapServiceLoader) {
      iMixinServiceBootstrap.bootstrap();
      this.bootedServices.add(iMixinServiceBootstrap.getServiceClassName());
    } 
  }
  
  private static MixinService getInstance() {
    if (instance == null)
      instance = new MixinService(); 
    return instance;
  }
  
  public static void boot() {
    getInstance();
  }
  
  public static IMixinService getService() {
    return getInstance().getServiceInstance();
  }
  
  private synchronized IMixinService getServiceInstance() {
    if (this.service == null) {
      this.service = initService();
      if (this.service == null)
        throw new ServiceNotAvailableError("No mixin host service is available"); 
    } 
    return this.service;
  }
  
  private IMixinService initService() {
    this.serviceLoader = ServiceLoader.load(IMixinService.class, getClass().getClassLoader());
    for (IMixinService iMixinService : this.serviceLoader) {
      if (this.bootedServices.contains(iMixinService.getClass().getName()))
        logger.debug("MixinService [{}] was successfully booted in {}", new Object[] { iMixinService.getName(), getClass().getClassLoader() }); 
      if (iMixinService.isValid())
        return iMixinService; 
    } 
    return null;
  }
}
