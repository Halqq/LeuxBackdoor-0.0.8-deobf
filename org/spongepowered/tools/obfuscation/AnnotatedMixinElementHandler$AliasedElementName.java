package org.spongepowered.tools.obfuscation;

import java.util.List;
import javax.lang.model.element.Element;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

class AnnotatedMixinElementHandler$AliasedElementName {
  protected final String originalName;
  
  private final List aliases;
  
  private boolean caseSensitive;
  
  public AnnotatedMixinElementHandler$AliasedElementName(Element paramElement, AnnotationHandle paramAnnotationHandle) {
    this.originalName = paramElement.getSimpleName().toString();
    this.aliases = paramAnnotationHandle.getList("aliases");
  }
  
  public AnnotatedMixinElementHandler$AliasedElementName setCaseSensitive(boolean paramBoolean) {
    this.caseSensitive = paramBoolean;
    return this;
  }
  
  public boolean isCaseSensitive() {
    return this.caseSensitive;
  }
  
  public boolean hasAliases() {
    return (this.aliases.size() > 0);
  }
  
  public List getAliases() {
    return this.aliases;
  }
  
  public String elementName() {
    return this.originalName;
  }
  
  public String baseName() {
    return this.originalName;
  }
  
  public boolean hasPrefix() {
    return false;
  }
}
