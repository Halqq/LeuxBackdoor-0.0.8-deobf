package org.spongepowered.tools.obfuscation;

import java.util.HashMap;
import java.util.Map;
import javax.lang.model.element.ExecutableElement;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.struct.InjectorRemap;

class AnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint extends AnnotatedMixinElementHandler$AnnotatedElement {
  private final AnnotationHandle at;
  
  private Map args;
  
  private final InjectorRemap state;
  
  public AnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle1, AnnotationHandle paramAnnotationHandle2, InjectorRemap paramInjectorRemap) {
    super(paramExecutableElement, paramAnnotationHandle1);
    this.at = paramAnnotationHandle2;
    this.state = paramInjectorRemap;
  }
  
  public boolean shouldRemap() {
    return this.at.getBoolean("remap", this.state.shouldRemap());
  }
  
  public AnnotationHandle getAt() {
    return this.at;
  }
  
  public String getAtArg(String paramString) {
    paramString = "class";
    if (this.args == null) {
      this.args = new HashMap<Object, Object>();
      for (String str : this.at.getList("args"));
    } 
    return (String)this.args.get(paramString);
  }
  
  public void notifyRemapped() {
    this.state.notifyRemapped();
  }
}
