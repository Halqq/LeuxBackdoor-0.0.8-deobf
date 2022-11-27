package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Strings;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.callback.CallbackInjector;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.util.Annotations;

public class CallbackInjectionInfo extends InjectionInfo {
  protected CallbackInjectionInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, AnnotationNode paramAnnotationNode) {
    super(paramMixinTargetContext, paramMethodNode, paramAnnotationNode);
  }
  
  protected Injector parseInjector(AnnotationNode paramAnnotationNode) {
    boolean bool = ((Boolean)Annotations.getValue(paramAnnotationNode, "cancellable", Boolean.FALSE)).booleanValue();
    LocalCapture localCapture = (LocalCapture)Annotations.getValue(paramAnnotationNode, "locals", LocalCapture.class, (Enum)LocalCapture.NO_CAPTURE);
    String str = (String)Annotations.getValue(paramAnnotationNode, "id", "");
    return (Injector)new CallbackInjector(this, bool, localCapture, str);
  }
  
  public String getSliceId(String paramString) {
    return Strings.nullToEmpty(paramString);
  }
}
