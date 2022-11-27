package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.Element;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationDataProvider;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

abstract class AnnotatedMixinElementHandlerShadow$AnnotatedElementShadow extends AnnotatedMixinElementHandler$AnnotatedElement {
  private final boolean shouldRemap;
  
  private final AnnotatedMixinElementHandler$ShadowElementName name;
  
  private final IMapping.Type type;
  
  protected AnnotatedMixinElementHandlerShadow$AnnotatedElementShadow(Element paramElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean, IMapping.Type paramType) {
    super(paramElement, paramAnnotationHandle);
    this.shouldRemap = paramBoolean;
    this.name = new AnnotatedMixinElementHandler$ShadowElementName(paramElement, paramAnnotationHandle);
    this.type = paramType;
  }
  
  public boolean shouldRemap() {
    return this.shouldRemap;
  }
  
  public AnnotatedMixinElementHandler$ShadowElementName getName() {
    return this.name;
  }
  
  public IMapping.Type getElementType() {
    return this.type;
  }
  
  public String toString() {
    return getElementType().name().toLowerCase();
  }
  
  public AnnotatedMixinElementHandler$ShadowElementName setObfuscatedName(IMapping paramIMapping) {
    return setObfuscatedName(paramIMapping.getSimpleName());
  }
  
  public AnnotatedMixinElementHandler$ShadowElementName setObfuscatedName(String paramString) {
    return getName().setObfuscatedName(paramString);
  }
  
  public ObfuscationData getObfuscationData(IObfuscationDataProvider paramIObfuscationDataProvider, TypeHandle paramTypeHandle) {
    return paramIObfuscationDataProvider.getObfEntry(getMapping(paramTypeHandle, getName().toString(), getDesc()));
  }
  
  public abstract IMapping getMapping(TypeHandle paramTypeHandle, String paramString1, String paramString2);
  
  public abstract void addMapping(ObfuscationType paramObfuscationType, IMapping paramIMapping);
}
