package org.spongepowered.tools.obfuscation;

import javax.annotation.processing.Messager;
import javax.lang.model.element.ExecutableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

class AnnotatedMixinElementHandlerInjector extends AnnotatedMixinElementHandler {
  AnnotatedMixinElementHandlerInjector(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, AnnotatedMixin paramAnnotatedMixin) {
    super(paramIMixinAnnotationProcessor, paramAnnotatedMixin);
  }
  
  public void registerInjector(AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector) {
    if (this.mixin.isInterface())
      this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getElement()); 
    for (String str : paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getAnnotation().getList("method")) {
      MemberInfo memberInfo = MemberInfo.parse(str);
      if (memberInfo.name == null)
        continue; 
      memberInfo.validate();
      if (memberInfo.desc != null)
        validateReferencedTarget((ExecutableElement)paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getElement(), paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getAnnotation(), memberInfo, paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.toString()); 
      if (!paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.shouldRemap())
        continue; 
      for (TypeHandle typeHandle : this.mixin.getTargets()) {
        if (!registerInjector(paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector, str, memberInfo, typeHandle))
          break; 
      } 
    } 
  }
  
  private boolean registerInjector(AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector, String paramString, MemberInfo paramMemberInfo, TypeHandle paramTypeHandle) {
    String str = paramTypeHandle.findDescriptor(paramMemberInfo);
    Diagnostic.Kind kind = this.mixin.isMultiTarget() ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
    if (paramTypeHandle.isSimulated()) {
      paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.printMessage((Messager)this.ap, Diagnostic.Kind.NOTE, paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector + " target '" + paramString + "' in @Pseudo mixin will not be obfuscated");
    } else if (paramTypeHandle.isImaginary()) {
      paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.printMessage((Messager)this.ap, kind, paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector + " target requires method signature because enclosing type information for " + paramTypeHandle + " is unavailable");
    } else if (!paramMemberInfo.isInitialiser()) {
      paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.printMessage((Messager)this.ap, kind, "Unable to determine signature for " + paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjector + " target method");
    } 
    return true;
  }
  
  public void registerInjectionPoint(AnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint, String paramString) {
    if (this.mixin.isInterface())
      this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint.getElement()); 
    if (!paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint.shouldRemap())
      return; 
    String str1 = InjectionPointData.parseType((String)paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint.getAt().getValue("value"));
    String str2 = (String)paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint.getAt().getValue("target");
    if ("NEW".equals(str1)) {
      remapNewTarget(String.format(paramString, new Object[] { str1 + ".<target>" }), str2, paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint);
      remapNewTarget(String.format(paramString, new Object[] { str1 + ".args[class]" }), paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint.getAtArg("class"), paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint);
    } else {
      remapReference(String.format(paramString, new Object[] { str1 + ".<target>" }), str2, paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint);
    } 
  }
  
  protected final void remapNewTarget(String paramString1, String paramString2, AnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint) {}
  
  protected final void remapReference(String paramString1, String paramString2, AnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint paramAnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint) {}
}
