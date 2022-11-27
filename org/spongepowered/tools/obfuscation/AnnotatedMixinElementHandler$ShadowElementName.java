package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.Element;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

class AnnotatedMixinElementHandler$ShadowElementName extends AnnotatedMixinElementHandler$AliasedElementName {
  private final boolean hasPrefix;
  
  private final String prefix;
  
  private final String baseName;
  
  private String obfuscated;
  
  AnnotatedMixinElementHandler$ShadowElementName(Element paramElement, AnnotationHandle paramAnnotationHandle) {
    super(paramElement, paramAnnotationHandle);
    this.prefix = (String)paramAnnotationHandle.getValue("prefix", "shadow$");
    boolean bool = false;
    String str = this.originalName;
    if (str.startsWith(this.prefix)) {
      bool = true;
      str = str.substring(this.prefix.length());
    } 
    this.hasPrefix = bool;
    this.obfuscated = this.baseName = str;
  }
  
  public String toString() {
    return this.baseName;
  }
  
  public String baseName() {
    return this.baseName;
  }
  
  public AnnotatedMixinElementHandler$ShadowElementName setObfuscatedName(IMapping paramIMapping) {
    this.obfuscated = paramIMapping.getName();
    return this;
  }
  
  public AnnotatedMixinElementHandler$ShadowElementName setObfuscatedName(String paramString) {
    this.obfuscated = paramString;
    return this;
  }
  
  public boolean hasPrefix() {
    return this.hasPrefix;
  }
  
  public String prefix() {
    return this.hasPrefix ? this.prefix : "";
  }
  
  public String name() {
    return prefix(this.baseName);
  }
  
  public String obfuscated() {
    return prefix(this.obfuscated);
  }
  
  public String prefix(String paramString) {
    return this.hasPrefix ? (this.prefix + paramString) : paramString;
  }
}
