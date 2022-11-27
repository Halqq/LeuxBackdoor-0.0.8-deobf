package org.spongepowered.tools.obfuscation;

import com.google.common.base.Strings;
import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.refmap.IReferenceMapper;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.mirror.FieldHandle;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public class AnnotatedMixinElementHandlerAccessor extends AnnotatedMixinElementHandler implements IMixinContext {
  public AnnotatedMixinElementHandlerAccessor(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, AnnotatedMixin paramAnnotatedMixin) {
    super(paramIMixinAnnotationProcessor, paramAnnotatedMixin);
  }
  
  public ReferenceMapper getReferenceMapper() {
    return null;
  }
  
  public String getClassRef() {
    return this.mixin.getClassRef();
  }
  
  public String getTargetClassRef() {
    throw new UnsupportedOperationException("Target class not available at compile time");
  }
  
  public IMixinInfo getMixin() {
    throw new UnsupportedOperationException("MixinInfo not available at compile time");
  }
  
  public Extensions getExtensions() {
    throw new UnsupportedOperationException("Mixin Extensions not available at compile time");
  }
  
  public boolean getOption(MixinEnvironment.Option paramOption) {
    throw new UnsupportedOperationException("Options not available at compile time");
  }
  
  public int getPriority() {
    throw new UnsupportedOperationException("Priority not available at compile time");
  }
  
  public Target getTargetMethod(MethodNode paramMethodNode) {
    throw new UnsupportedOperationException("Target not available at compile time");
  }
  
  public void registerAccessor(AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor) {
    if (paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getAccessorType() == null) {
      paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, "Unsupported accessor type");
      return;
    } 
    String str = getAccessorTargetName(paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor);
    paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, "Cannot inflect accessor target name");
  }
  
  private void registerAccessorForTarget(AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor, TypeHandle paramTypeHandle) {
    FieldHandle fieldHandle = paramTypeHandle.findField(paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getTargetName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getTargetTypeName(), false);
    if (!paramTypeHandle.isImaginary()) {
      paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, "Could not locate @Accessor target " + paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor + " in target " + paramTypeHandle);
      return;
    } 
    fieldHandle = new FieldHandle(paramTypeHandle.getName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getTargetName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getDesc());
    if (!paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.shouldRemap())
      return; 
    ObfuscationData obfuscationData = this.obf.getDataProvider().getObfField(fieldHandle.asMapping(false).move(paramTypeHandle.getName()));
    if (obfuscationData.isEmpty()) {
      String str = this.mixin.isMultiTarget() ? (" in target " + paramTypeHandle) : "";
      paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + str + " for @Accessor target " + paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor);
      return;
    } 
    obfuscationData = AnnotatedMixinElementHandler.stripOwnerData(obfuscationData);
    this.obf.getReferenceManager().addFieldMapping(this.mixin.getClassRef(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getTargetName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getContext(), obfuscationData);
  }
  
  private void registerInvokerForTarget(AnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker, TypeHandle paramTypeHandle) {
    MethodHandle methodHandle = paramTypeHandle.findMethod(paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.getTargetName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.getTargetTypeName(), false);
    if (!paramTypeHandle.isImaginary()) {
      paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, "Could not locate @Invoker target " + paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker + " in target " + paramTypeHandle);
      return;
    } 
    methodHandle = new MethodHandle(paramTypeHandle, paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.getTargetName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.getDesc());
    if (!paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.shouldRemap())
      return; 
    ObfuscationData obfuscationData = this.obf.getDataProvider().getObfMethod(methodHandle.asMapping(false).move(paramTypeHandle.getName()));
    if (obfuscationData.isEmpty()) {
      String str = this.mixin.isMultiTarget() ? (" in target " + paramTypeHandle) : "";
      paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, "Unable to locate obfuscation mapping" + str + " for @Accessor target " + paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker);
      return;
    } 
    obfuscationData = AnnotatedMixinElementHandler.stripOwnerData(obfuscationData);
    this.obf.getReferenceManager().addMethodMapping(this.mixin.getClassRef(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.getTargetName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker.getContext(), obfuscationData);
  }
  
  private String getAccessorTargetName(AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor) {
    String str = paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getAnnotationValue();
    return Strings.isNullOrEmpty(str) ? inflectAccessorTarget(paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor) : str;
  }
  
  private String inflectAccessorTarget(AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor) {
    return AccessorInfo.inflectTarget(paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getSimpleName(), paramAnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor.getAccessorType(), "", this, false);
  }
  
  public IReferenceMapper getReferenceMapper() {
    return (IReferenceMapper)getReferenceMapper();
  }
}
