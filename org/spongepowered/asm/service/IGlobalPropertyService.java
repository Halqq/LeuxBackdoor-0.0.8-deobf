package org.spongepowered.asm.service;

public interface IGlobalPropertyService {
  Object getProperty(String paramString);
  
  void setProperty(String paramString, Object paramObject);
  
  Object getProperty(String paramString, Object paramObject);
  
  String getPropertyString(String paramString1, String paramString2);
}
