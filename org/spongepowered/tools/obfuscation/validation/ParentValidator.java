package org.spongepowered.tools.obfuscation.validation;

import java.util.Collection;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import org.spongepowered.tools.obfuscation.MixinValidator;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IMixinValidator;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

public class ParentValidator extends MixinValidator {
  public ParentValidator(IMixinAnnotationProcessor paramIMixinAnnotationProcessor) {
    super(paramIMixinAnnotationProcessor, IMixinValidator.ValidationPass.EARLY);
  }
  
  public boolean validate(TypeElement paramTypeElement, AnnotationHandle paramAnnotationHandle, Collection paramCollection) {
    if (paramTypeElement.getEnclosingElement().getKind() != ElementKind.PACKAGE && !paramTypeElement.getModifiers().contains(Modifier.STATIC))
      error("Inner class mixin must be declared static", paramTypeElement); 
    return true;
  }
}
