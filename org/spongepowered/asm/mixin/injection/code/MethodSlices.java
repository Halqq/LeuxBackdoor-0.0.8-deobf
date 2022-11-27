package org.spongepowered.asm.mixin.injection.code;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.throwables.InvalidSliceException;
import org.spongepowered.asm.util.Annotations;

public final class MethodSlices {
  private final InjectionInfo info;
  
  private final Map slices = new HashMap<Object, Object>(4);
  
  private MethodSlices(InjectionInfo paramInjectionInfo) {
    this.info = paramInjectionInfo;
  }
  
  private void add(MethodSlice paramMethodSlice) {
    String str = this.info.getSliceId(paramMethodSlice.getId());
    if (this.slices.containsKey(str))
      throw new InvalidSliceException(this.info, paramMethodSlice + " has a duplicate id, '" + str + "' was already defined"); 
    this.slices.put(str, paramMethodSlice);
  }
  
  public MethodSlice get(String paramString) {
    return (MethodSlice)this.slices.get(paramString);
  }
  
  public String toString() {
    return String.format("MethodSlices%s", new Object[] { this.slices.keySet() });
  }
  
  public static MethodSlices parse(InjectionInfo paramInjectionInfo) {
    MethodSlices methodSlices = new MethodSlices(paramInjectionInfo);
    AnnotationNode annotationNode = paramInjectionInfo.getAnnotation();
    for (AnnotationNode annotationNode1 : Annotations.getValue(annotationNode, "slice", true)) {
      MethodSlice methodSlice = MethodSlice.parse((ISliceContext)paramInjectionInfo, annotationNode1);
      methodSlices.add(methodSlice);
    } 
    return methodSlices;
  }
}
