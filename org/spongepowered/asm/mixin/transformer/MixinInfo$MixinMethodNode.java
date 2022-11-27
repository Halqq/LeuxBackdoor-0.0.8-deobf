package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.injection.Surrogate;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

class MixinInfo$MixinMethodNode extends MethodNode {
  private final String originalName;
  
  final MixinInfo this$0;
  
  public MixinInfo$MixinMethodNode(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    super(327680, paramInt, paramString1, paramString2, paramString3, paramArrayOfString);
    this.originalName = paramString1;
  }
  
  public String toString() {
    return String.format("%s%s", new Object[] { this.originalName, this.desc });
  }
  
  public String getOriginalName() {
    return this.originalName;
  }
  
  public boolean isInjector() {
    return (getInjectorAnnotation() != null || isSurrogate());
  }
  
  public boolean isSurrogate() {
    return (getVisibleAnnotation(Surrogate.class) != null);
  }
  
  public boolean isSynthetic() {
    return Bytecode.hasFlag(this, 4096);
  }
  
  public AnnotationNode getVisibleAnnotation(Class paramClass) {
    return Annotations.getVisible(this, paramClass);
  }
  
  public AnnotationNode getInjectorAnnotation() {
    return InjectionInfo.getInjectorAnnotation(MixinInfo.this, this);
  }
  
  public IMixinInfo getOwner() {
    return MixinInfo.this;
  }
}
