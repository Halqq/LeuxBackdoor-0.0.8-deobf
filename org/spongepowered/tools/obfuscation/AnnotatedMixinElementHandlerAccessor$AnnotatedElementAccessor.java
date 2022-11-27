package org.spongepowered.tools.obfuscation;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.spongepowered.asm.mixin.gen.AccessorInfo;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;

class AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor extends AnnotatedMixinElementHandler$AnnotatedElement {
  private final boolean shouldRemap;
  
  private final TypeMirror returnType;
  
  private String targetName;
  
  public AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    super(paramExecutableElement, paramAnnotationHandle);
    this.shouldRemap = paramBoolean;
    this.returnType = ((ExecutableElement)getElement()).getReturnType();
  }
  
  public boolean shouldRemap() {
    return this.shouldRemap;
  }
  
  public String getAnnotationValue() {
    return (String)getAnnotation().getValue();
  }
  
  public TypeMirror getTargetType() {
    switch (AnnotatedMixinElementHandlerAccessor$1.$SwitchMap$org$spongepowered$asm$mixin$gen$AccessorInfo$AccessorType[getAccessorType().ordinal()]) {
      case 1:
        return this.returnType;
      case 2:
        return ((VariableElement)((ExecutableElement)getElement()).getParameters().get(0)).asType();
    } 
    return null;
  }
  
  public String getTargetTypeName() {
    return TypeUtils.getTypeName(getTargetType());
  }
  
  public String getAccessorDesc() {
    return TypeUtils.getInternalName(getTargetType());
  }
  
  public MemberInfo getContext() {
    return new MemberInfo(getTargetName(), null, getAccessorDesc());
  }
  
  public AccessorInfo.AccessorType getAccessorType() {
    return (this.returnType.getKind() == TypeKind.VOID) ? AccessorInfo.AccessorType.FIELD_SETTER : AccessorInfo.AccessorType.FIELD_GETTER;
  }
  
  public void setTargetName(String paramString) {
    this.targetName = paramString;
  }
  
  public String getTargetName() {
    return this.targetName;
  }
  
  public String toString() {
    return (this.targetName != null) ? this.targetName : "<invalid>";
  }
}
