package org.spongepowered.tools.obfuscation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Messager;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IMixinValidator;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationManager;
import org.spongepowered.tools.obfuscation.interfaces.ITypeHandleProvider;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeUtils;
import org.spongepowered.tools.obfuscation.struct.InjectorRemap;

class AnnotatedMixin {
  private final AnnotationHandle annotation;
  
  private final Messager messager;
  
  private final ITypeHandleProvider typeProvider;
  
  private final IObfuscationManager obf;
  
  private final IMappingConsumer mappings;
  
  private final TypeElement mixin;
  
  private final List methods;
  
  private final TypeHandle handle;
  
  private final List targets = new ArrayList();
  
  private final TypeHandle primaryTarget;
  
  private final String classRef;
  
  private final boolean remap;
  
  private final boolean virtual;
  
  private final AnnotatedMixinElementHandlerOverwrite overwrites;
  
  private final AnnotatedMixinElementHandlerShadow shadows;
  
  private final AnnotatedMixinElementHandlerInjector injectors;
  
  private final AnnotatedMixinElementHandlerAccessor accessors;
  
  private final AnnotatedMixinElementHandlerSoftImplements softImplements;
  
  private boolean validated = false;
  
  public AnnotatedMixin(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, TypeElement paramTypeElement) {
    this.typeProvider = paramIMixinAnnotationProcessor.getTypeProvider();
    this.obf = paramIMixinAnnotationProcessor.getObfuscationManager();
    this.mappings = this.obf.createMappingConsumer();
    this.messager = (Messager)paramIMixinAnnotationProcessor;
    this.mixin = paramTypeElement;
    this.handle = new TypeHandle(paramTypeElement);
    this.methods = new ArrayList(this.handle.getEnclosedElements(new ElementKind[] { ElementKind.METHOD }));
    this.virtual = this.handle.getAnnotation(Pseudo.class).exists();
    this.annotation = this.handle.getAnnotation(Mixin.class);
    this.classRef = TypeUtils.getInternalName(paramTypeElement);
    this.primaryTarget = initTargets();
    this.remap = (this.annotation.getBoolean("remap", true) && this.targets.size() > 0);
    this.overwrites = new AnnotatedMixinElementHandlerOverwrite(paramIMixinAnnotationProcessor, this);
    this.shadows = new AnnotatedMixinElementHandlerShadow(paramIMixinAnnotationProcessor, this);
    this.injectors = new AnnotatedMixinElementHandlerInjector(paramIMixinAnnotationProcessor, this);
    this.accessors = new AnnotatedMixinElementHandlerAccessor(paramIMixinAnnotationProcessor, this);
    this.softImplements = new AnnotatedMixinElementHandlerSoftImplements(paramIMixinAnnotationProcessor, this);
  }
  
  AnnotatedMixin runValidators(IMixinValidator.ValidationPass paramValidationPass, Collection paramCollection) {
    for (IMixinValidator iMixinValidator : paramCollection) {
      if (!iMixinValidator.validate(paramValidationPass, this.mixin, this.annotation, this.targets))
        break; 
    } 
    if (paramValidationPass == IMixinValidator.ValidationPass.FINAL && !this.validated) {
      this.validated = true;
      runFinalValidation();
    } 
    return this;
  }
  
  private TypeHandle initTargets() {
    TypeHandle typeHandle = null;
    for (TypeMirror typeMirror : this.annotation.getList()) {
      TypeHandle typeHandle1 = new TypeHandle((DeclaredType)typeMirror);
      if (this.targets.contains(typeHandle1))
        continue; 
      addTarget(typeHandle1);
      typeHandle = typeHandle1;
    } 
    for (String str : this.annotation.getList("targets")) {
      TypeHandle typeHandle1 = this.typeProvider.getTypeHandle(str);
      if (this.targets.contains(typeHandle1))
        continue; 
      if (this.virtual) {
        typeHandle1 = this.typeProvider.getSimulatedHandle(str, this.mixin.asType());
      } else {
        printMessage(Diagnostic.Kind.ERROR, "Mixin target " + str + " could not be found", this);
        return null;
      } 
      addSoftTarget(typeHandle1, str);
      typeHandle = typeHandle1;
    } 
    printMessage(Diagnostic.Kind.ERROR, "Mixin has no targets", this);
    return typeHandle;
  }
  
  private void printMessage(Diagnostic.Kind paramKind, CharSequence paramCharSequence, AnnotatedMixin paramAnnotatedMixin) {
    this.messager.printMessage(paramKind, paramCharSequence, this.mixin, this.annotation.asMirror());
  }
  
  private void addSoftTarget(TypeHandle paramTypeHandle, String paramString) {
    ObfuscationData obfuscationData = this.obf.getDataProvider().getObfClass(paramTypeHandle);
    if (!obfuscationData.isEmpty())
      this.obf.getReferenceManager().addClassMapping(this.classRef, paramString, obfuscationData); 
    addTarget(paramTypeHandle);
  }
  
  private void addTarget(TypeHandle paramTypeHandle) {
    this.targets.add(paramTypeHandle);
  }
  
  public String toString() {
    return this.mixin.getSimpleName().toString();
  }
  
  public AnnotationHandle getAnnotation() {
    return this.annotation;
  }
  
  public TypeElement getMixin() {
    return this.mixin;
  }
  
  public TypeHandle getHandle() {
    return this.handle;
  }
  
  public String getClassRef() {
    return this.classRef;
  }
  
  public boolean isInterface() {
    return (this.mixin.getKind() == ElementKind.INTERFACE);
  }
  
  @Deprecated
  @Deprecated
  public TypeHandle getPrimaryTarget() {
    return this.primaryTarget;
  }
  
  public List getTargets() {
    return this.targets;
  }
  
  public boolean isMultiTarget() {
    return (this.targets.size() > 1);
  }
  
  public boolean remap() {
    return this.remap;
  }
  
  public IMappingConsumer getMappings() {
    return this.mappings;
  }
  
  private void runFinalValidation() {
    for (ExecutableElement executableElement : this.methods)
      this.overwrites.registerMerge(executableElement); 
  }
  
  public void registerOverwrite(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    this.methods.remove(paramExecutableElement);
    this.overwrites.registerOverwrite(new AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite(paramExecutableElement, paramAnnotationHandle, paramBoolean));
  }
  
  public void registerShadow(VariableElement paramVariableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    this.shadows.getClass();
    this.shadows.registerShadow(new AnnotatedMixinElementHandlerShadow$AnnotatedElementShadowField(this.shadows, paramVariableElement, paramAnnotationHandle, paramBoolean));
  }
  
  public void registerShadow(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    this.methods.remove(paramExecutableElement);
    this.shadows.getClass();
    this.shadows.registerShadow(new AnnotatedMixinElementHandlerShadow$AnnotatedElementShadowMethod(this.shadows, paramExecutableElement, paramAnnotationHandle, paramBoolean));
  }
  
  public void registerInjector(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, InjectorRemap paramInjectorRemap) {
    this.methods.remove(paramExecutableElement);
    this.injectors.registerInjector(new AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector(paramExecutableElement, paramAnnotationHandle, paramInjectorRemap));
    List list1 = paramAnnotationHandle.getAnnotationList("at");
    for (AnnotationHandle annotationHandle : list1)
      registerInjectionPoint(paramExecutableElement, paramAnnotationHandle, annotationHandle, paramInjectorRemap, "@At(%s)"); 
    List list2 = paramAnnotationHandle.getAnnotationList("slice");
    for (AnnotationHandle annotationHandle1 : list2) {
      String str = (String)annotationHandle1.getValue("id", "");
      AnnotationHandle annotationHandle2 = annotationHandle1.getAnnotation("from");
      registerInjectionPoint(paramExecutableElement, paramAnnotationHandle, annotationHandle2, paramInjectorRemap, "@Slice[" + str + "](from=@At(%s))");
      AnnotationHandle annotationHandle3 = annotationHandle1.getAnnotation("to");
      registerInjectionPoint(paramExecutableElement, paramAnnotationHandle, annotationHandle3, paramInjectorRemap, "@Slice[" + str + "](to=@At(%s))");
    } 
  }
  
  public void registerInjectionPoint(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle1, AnnotationHandle paramAnnotationHandle2, InjectorRemap paramInjectorRemap, String paramString) {
    this.injectors.registerInjectionPoint(new AnnotatedMixinElementHandlerInjector$AnnotatedElementInjectionPoint(paramExecutableElement, paramAnnotationHandle1, paramAnnotationHandle2, paramInjectorRemap), paramString);
  }
  
  public void registerAccessor(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    this.methods.remove(paramExecutableElement);
    this.accessors.registerAccessor(new AnnotatedMixinElementHandlerAccessor$AnnotatedElementAccessor(paramExecutableElement, paramAnnotationHandle, paramBoolean));
  }
  
  public void registerInvoker(ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle, boolean paramBoolean) {
    this.methods.remove(paramExecutableElement);
    this.accessors.registerAccessor(new AnnotatedMixinElementHandlerAccessor$AnnotatedElementInvoker(paramExecutableElement, paramAnnotationHandle, paramBoolean));
  }
  
  public void registerSoftImplements(AnnotationHandle paramAnnotationHandle) {
    this.softImplements.process(paramAnnotationHandle);
  }
}
