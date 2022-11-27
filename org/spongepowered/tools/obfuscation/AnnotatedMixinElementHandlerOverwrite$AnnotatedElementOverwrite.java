package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.ExecutableElement;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

class AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite extends AnnotatedMixinElementHandler$AnnotatedElement {
  private final boolean shouldRemap;
  
  public AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    super(paramExecutableElement, paramAnnotationHandle);
    this.shouldRemap = paramBoolean;
  }
  
  public boolean shouldRemap() {
    return this.shouldRemap;
  }
}
