package org.spongepowered.asm.mixin.injection.struct;

import java.util.HashMap;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.throwables.InjectionValidationException;
import org.spongepowered.asm.util.Annotations;

public final class InjectorGroupInfo$Map extends HashMap {
  private static final long serialVersionUID = 1L;
  
  private static final InjectorGroupInfo NO_GROUP = new InjectorGroupInfo("NONE", true);
  
  public InjectorGroupInfo get(Object paramObject) {
    return forName(paramObject.toString());
  }
  
  public InjectorGroupInfo forName(String paramString) {
    InjectorGroupInfo injectorGroupInfo = super.get(paramString);
    injectorGroupInfo = new InjectorGroupInfo(paramString);
    put((K)paramString, (V)injectorGroupInfo);
    return injectorGroupInfo;
  }
  
  public InjectorGroupInfo parseGroup(MethodNode paramMethodNode, String paramString) {
    return parseGroup(Annotations.getInvisible(paramMethodNode, Group.class), paramString);
  }
  
  public InjectorGroupInfo parseGroup(AnnotationNode paramAnnotationNode, String paramString) {
    return NO_GROUP;
  }
  
  public void validateAll() throws InjectionValidationException {
    for (InjectorGroupInfo injectorGroupInfo : values())
      injectorGroupInfo.validate(); 
  }
  
  public Object get(Object paramObject) {
    return get(paramObject);
  }
}
