package org.spongepowered.asm.service;

import java.io.InputStream;
import java.util.Collection;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.util.ReEntranceLock;

public interface IMixinService {
  String getName();
  
  boolean isValid();
  
  void prepare();
  
  MixinEnvironment.Phase getInitialPhase();
  
  void init();
  
  void beginPhase();
  
  void checkEnv(Object paramObject);
  
  ReEntranceLock getReEntranceLock();
  
  IClassProvider getClassProvider();
  
  IClassBytecodeProvider getBytecodeProvider();
  
  Collection getPlatformAgents();
  
  InputStream getResourceAsStream(String paramString);
  
  void registerInvalidClass(String paramString);
  
  boolean isClassLoaded(String paramString);
  
  Collection getTransformers();
  
  String getSideName();
}
