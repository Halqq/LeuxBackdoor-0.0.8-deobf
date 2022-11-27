package org.spongepowered.tools.obfuscation.interfaces;

import java.util.Collection;
import javax.lang.model.element.TypeElement;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

public interface IMixinValidator {
  boolean validate(IMixinValidator$ValidationPass paramIMixinValidator$ValidationPass, TypeElement paramTypeElement, AnnotationHandle paramAnnotationHandle, Collection paramCollection);
}
