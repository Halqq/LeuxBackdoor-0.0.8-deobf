package org.spongepowered.asm.mixin.injection.code;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.struct.Target;

public class InjectorTarget {
  private final ISliceContext context;
  
  private final Map cache = new HashMap<Object, Object>();
  
  private final Target target;
  
  public InjectorTarget(ISliceContext paramISliceContext, Target paramTarget) {
    this.context = paramISliceContext;
    this.target = paramTarget;
  }
  
  public Target getTarget() {
    return this.target;
  }
  
  public MethodNode getMethod() {
    return this.target.method;
  }
  
  public InsnList getSlice(String paramString) {
    ReadOnlyInsnList readOnlyInsnList = (ReadOnlyInsnList)this.cache.get(paramString);
    MethodSlice methodSlice = this.context.getSlice(paramString);
    readOnlyInsnList = methodSlice.getSlice(this.target.method);
    this.cache.put(paramString, readOnlyInsnList);
    return readOnlyInsnList;
  }
  
  public InsnList getSlice(InjectionPoint paramInjectionPoint) {
    return getSlice(paramInjectionPoint.getSlice());
  }
  
  public void dispose() {
    for (ReadOnlyInsnList readOnlyInsnList : this.cache.values())
      readOnlyInsnList.dispose(); 
    this.cache.clear();
  }
}
