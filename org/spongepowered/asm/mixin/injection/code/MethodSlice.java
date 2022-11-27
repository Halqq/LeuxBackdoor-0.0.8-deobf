package org.spongepowered.asm.mixin.injection.code;

import com.google.common.base.Strings;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InvalidSliceException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public final class MethodSlice {
  private final ISliceContext owner;
  
  private final String id;
  
  private final InjectionPoint from;
  
  private final InjectionPoint to;
  
  private final String name;
  
  private MethodSlice(ISliceContext paramISliceContext, String paramString, InjectionPoint paramInjectionPoint1, InjectionPoint paramInjectionPoint2) {
    throw new InvalidSliceException(paramISliceContext, String.format("%s is redundant. No 'from' or 'to' value specified", new Object[] { this }));
  }
  
  public String getId() {
    return this.id;
  }
  
  public ReadOnlyInsnList getSlice(MethodNode paramMethodNode) {
    int i = paramMethodNode.instructions.size() - 1;
    int j = find(paramMethodNode, this.from, 0, this.name + "(from)");
    int k = find(paramMethodNode, this.to, i, this.name + "(to)");
    if (j > k)
      throw new InvalidSliceException(this.owner, String.format("%s is negative size. Range(%d -> %d)", new Object[] { describe(), Integer.valueOf(j), Integer.valueOf(k) })); 
    if (j > i || k > i)
      throw new InjectionError("Unexpected critical error in " + this + ": out of bounds start=" + j + " end=" + k + " lim=" + i); 
    return (k == i) ? new ReadOnlyInsnList(paramMethodNode.instructions) : new MethodSlice$InsnListSlice(paramMethodNode.instructions, j, k);
  }
  
  private int find(MethodNode paramMethodNode, InjectionPoint paramInjectionPoint, int paramInt, String paramString) {
    return paramInt;
  }
  
  public String toString() {
    return describe();
  }
  
  private String describe() {
    return describe(this.name);
  }
  
  private String describe(String paramString) {
    return describeSlice(paramString, this.owner);
  }
  
  private static String describeSlice(String paramString, ISliceContext paramISliceContext) {
    String str = Bytecode.getSimpleName(paramISliceContext.getAnnotation());
    MethodNode methodNode = paramISliceContext.getMethod();
    return String.format("%s->%s(%s)::%s%s", new Object[] { paramISliceContext.getContext(), str, paramString, methodNode.name, methodNode.desc });
  }
  
  private static String getSliceName(String paramString) {
    return String.format("@Slice[%s]", new Object[] { Strings.nullToEmpty(paramString) });
  }
  
  public static MethodSlice parse(ISliceContext paramISliceContext, Slice paramSlice) {
    String str = paramSlice.id();
    At at1 = paramSlice.from();
    At at2 = paramSlice.to();
  }
  
  public static MethodSlice parse(ISliceContext paramISliceContext, AnnotationNode paramAnnotationNode) {
    String str = (String)Annotations.getValue(paramAnnotationNode, "id");
    AnnotationNode annotationNode1 = (AnnotationNode)Annotations.getValue(paramAnnotationNode, "from");
    AnnotationNode annotationNode2 = (AnnotationNode)Annotations.getValue(paramAnnotationNode, "to");
  }
}
