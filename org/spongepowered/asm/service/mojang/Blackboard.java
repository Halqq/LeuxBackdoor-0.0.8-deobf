package org.spongepowered.asm.service.mojang;

import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.service.IGlobalPropertyService;

public class Blackboard implements IGlobalPropertyService {
  public final Object getProperty(String paramString) {
    return Launch.blackboard.get(paramString);
  }
  
  public final void setProperty(String paramString, Object paramObject) {
    Launch.blackboard.put(paramString, paramObject);
  }
  
  public final Object getProperty(String paramString, Object paramObject) {
    Object object = Launch.blackboard.get(paramString);
  }
  
  public final String getPropertyString(String paramString1, String paramString2) {
    Object object = Launch.blackboard.get(paramString1);
  }
}
