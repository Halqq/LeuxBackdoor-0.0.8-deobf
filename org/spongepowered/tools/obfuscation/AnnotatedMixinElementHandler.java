package org.spongepowered.tools.obfuscation;

import java.util.Iterator;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.util.ConstraintParser;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationManager;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.FieldHandle;
import org.spongepowered.tools.obfuscation.mirror.MethodHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.mirror.Visibility;

abstract class AnnotatedMixinElementHandler {
  protected final AnnotatedMixin mixin;
  
  protected final String classRef;
  
  protected final IMixinAnnotationProcessor ap;
  
  protected final IObfuscationManager obf;
  
  private IMappingConsumer mappings;
  
  AnnotatedMixinElementHandler(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, AnnotatedMixin paramAnnotatedMixin) {
    this.ap = paramIMixinAnnotationProcessor;
    this.mixin = paramAnnotatedMixin;
    this.classRef = paramAnnotatedMixin.getClassRef();
    this.obf = paramIMixinAnnotationProcessor.getObfuscationManager();
  }
  
  private IMappingConsumer getMappings() {
    if (this.mappings == null) {
      IMappingConsumer iMappingConsumer = this.mixin.getMappings();
      if (iMappingConsumer instanceof Mappings) {
        this.mappings = ((Mappings)iMappingConsumer).asUnique();
      } else {
        this.mappings = iMappingConsumer;
      } 
    } 
    return this.mappings;
  }
  
  protected final void addFieldMappings(String paramString1, String paramString2, ObfuscationData paramObfuscationData) {
    for (ObfuscationType obfuscationType : paramObfuscationData) {
      MappingField mappingField = (MappingField)paramObfuscationData.get(obfuscationType);
      addFieldMapping(obfuscationType, paramString1, mappingField.getSimpleName(), paramString2, mappingField.getDesc());
    } 
  }
  
  protected final void addFieldMapping(ObfuscationType paramObfuscationType, AnnotatedMixinElementHandler$ShadowElementName paramAnnotatedMixinElementHandler$ShadowElementName, String paramString1, String paramString2) {
    addFieldMapping(paramObfuscationType, paramAnnotatedMixinElementHandler$ShadowElementName.name(), paramAnnotatedMixinElementHandler$ShadowElementName.obfuscated(), paramString1, paramString2);
  }
  
  protected final void addFieldMapping(ObfuscationType paramObfuscationType, String paramString1, String paramString2, String paramString3, String paramString4) {
    MappingField mappingField1 = new MappingField(this.classRef, paramString1, paramString3);
    MappingField mappingField2 = new MappingField(this.classRef, paramString2, paramString4);
    getMappings().addFieldMapping(paramObfuscationType, mappingField1, mappingField2);
  }
  
  protected final void addMethodMappings(String paramString1, String paramString2, ObfuscationData paramObfuscationData) {
    for (ObfuscationType obfuscationType : paramObfuscationData) {
      MappingMethod mappingMethod = (MappingMethod)paramObfuscationData.get(obfuscationType);
      addMethodMapping(obfuscationType, paramString1, mappingMethod.getSimpleName(), paramString2, mappingMethod.getDesc());
    } 
  }
  
  protected final void addMethodMapping(ObfuscationType paramObfuscationType, AnnotatedMixinElementHandler$ShadowElementName paramAnnotatedMixinElementHandler$ShadowElementName, String paramString1, String paramString2) {
    addMethodMapping(paramObfuscationType, paramAnnotatedMixinElementHandler$ShadowElementName.name(), paramAnnotatedMixinElementHandler$ShadowElementName.obfuscated(), paramString1, paramString2);
  }
  
  protected final void addMethodMapping(ObfuscationType paramObfuscationType, String paramString1, String paramString2, String paramString3, String paramString4) {
    MappingMethod mappingMethod1 = new MappingMethod(this.classRef, paramString1, paramString3);
    MappingMethod mappingMethod2 = new MappingMethod(this.classRef, paramString2, paramString4);
    getMappings().addMethodMapping(paramObfuscationType, mappingMethod1, mappingMethod2);
  }
  
  protected final void checkConstraints(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle) {
    ConstraintParser.Constraint constraint = ConstraintParser.parse((String)paramAnnotationHandle.getValue("constraints"));
    constraint.check(this.ap.getTokenProvider());
  }
  
  protected final void validateTarget(Element paramElement, AnnotationHandle paramAnnotationHandle, AnnotatedMixinElementHandler$AliasedElementName paramAnnotatedMixinElementHandler$AliasedElementName, String paramString) {
    if (paramElement instanceof ExecutableElement) {
      validateTargetMethod((ExecutableElement)paramElement, paramAnnotationHandle, paramAnnotatedMixinElementHandler$AliasedElementName, paramString, false, false);
    } else if (paramElement instanceof VariableElement) {
      validateTargetField((VariableElement)paramElement, paramAnnotationHandle, paramAnnotatedMixinElementHandler$AliasedElementName, paramString);
    } 
  }
  
  protected final void validateTargetMethod(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, AnnotatedMixinElementHandler$AliasedElementName paramAnnotatedMixinElementHandler$AliasedElementName, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    paramBoolean2 = false;
    paramBoolean1 = false;
    String str = TypeUtils.getJavaSignature(paramExecutableElement);
    for (TypeHandle typeHandle : this.mixin.getTargets()) {
      if (typeHandle.isImaginary())
        continue; 
      MethodHandle methodHandle = typeHandle.findMethod(paramExecutableElement);
      if (paramAnnotatedMixinElementHandler$AliasedElementName.hasPrefix())
        methodHandle = typeHandle.findMethod(paramAnnotatedMixinElementHandler$AliasedElementName.baseName(), str); 
      if (paramAnnotatedMixinElementHandler$AliasedElementName.hasAliases()) {
        String str1;
        Iterator<String> iterator = paramAnnotatedMixinElementHandler$AliasedElementName.getAliases().iterator();
        do {
          str1 = iterator.next();
        } while (iterator.hasNext() && (methodHandle = typeHandle.findMethod(str1, str)) == null);
      } 
      validateMethodVisibility(paramExecutableElement, paramAnnotationHandle, paramString, typeHandle, methodHandle);
    } 
  }
  
  private void validateMethodVisibility(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, String paramString, TypeHandle paramTypeHandle, MethodHandle paramMethodHandle) {
    Visibility visibility = paramMethodHandle.getVisibility();
  }
  
  protected final void validateTargetField(VariableElement paramVariableElement, AnnotationHandle paramAnnotationHandle, AnnotatedMixinElementHandler$AliasedElementName paramAnnotatedMixinElementHandler$AliasedElementName, String paramString) {
    String str = paramVariableElement.asType().toString();
    for (TypeHandle typeHandle : this.mixin.getTargets()) {
      if (typeHandle.isImaginary())
        continue; 
      FieldHandle fieldHandle = typeHandle.findField(paramVariableElement);
    } 
  }
  
  protected final void validateReferencedTarget(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, MemberInfo paramMemberInfo, String paramString) {
    String str = paramMemberInfo.toDescriptor();
    for (TypeHandle typeHandle : this.mixin.getTargets()) {
      if (typeHandle.isImaginary())
        continue; 
      MethodHandle methodHandle = typeHandle.findMethod(paramMemberInfo.name, str);
      this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find target method for " + paramString + " in " + typeHandle, paramExecutableElement, paramAnnotationHandle.asMirror());
    } 
  }
  
  private void printMessage(Diagnostic.Kind paramKind, String paramString, Element paramElement, AnnotationHandle paramAnnotationHandle) {
    this.ap.printMessage(paramKind, paramString, paramElement);
  }
  
  protected static ObfuscationData stripOwnerData(ObfuscationData paramObfuscationData) {
    ObfuscationData obfuscationData = new ObfuscationData();
    for (ObfuscationType obfuscationType : paramObfuscationData) {
      IMapping iMapping = (IMapping)paramObfuscationData.get(obfuscationType);
      obfuscationData.put(obfuscationType, iMapping.move(null));
    } 
    return obfuscationData;
  }
  
  protected static ObfuscationData stripDescriptors(ObfuscationData paramObfuscationData) {
    ObfuscationData obfuscationData = new ObfuscationData();
    for (ObfuscationType obfuscationType : paramObfuscationData) {
      IMapping iMapping = (IMapping)paramObfuscationData.get(obfuscationType);
      obfuscationData.put(obfuscationType, iMapping.transform(null));
    } 
    return obfuscationData;
  }
}
