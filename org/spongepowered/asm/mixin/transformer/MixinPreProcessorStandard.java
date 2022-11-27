package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Strings;
import java.util.Iterator;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.perf.Profiler;

class MixinPreProcessorStandard {
  private static final Logger logger = LogManager.getLogger("mixin");
  
  protected final MixinInfo mixin;
  
  protected final MixinInfo$MixinClassNode classNode;
  
  protected final MixinEnvironment env;
  
  protected final Profiler profiler = MixinEnvironment.getProfiler();
  
  private final boolean verboseLogging;
  
  private final boolean strictUnique;
  
  private boolean prepared;
  
  private boolean attached;
  
  MixinPreProcessorStandard(MixinInfo paramMixinInfo, MixinInfo$MixinClassNode paramMixinInfo$MixinClassNode) {
    this.mixin = paramMixinInfo;
    this.classNode = paramMixinInfo$MixinClassNode;
    this.env = paramMixinInfo.getParent().getEnvironment();
    this.verboseLogging = this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
    this.strictUnique = this.env.getOption(MixinEnvironment.Option.DEBUG_UNIQUE);
  }
  
  final MixinPreProcessorStandard prepare() {
    if (this.prepared)
      return this; 
    this.prepared = true;
    Profiler.Section section = this.profiler.begin("prepare");
    for (MixinInfo$MixinMethodNode mixinInfo$MixinMethodNode : this.classNode.mixinMethods) {
      ClassInfo$Method classInfo$Method = this.mixin.getClassInfo().findMethod(mixinInfo$MixinMethodNode);
      prepareMethod(mixinInfo$MixinMethodNode, classInfo$Method);
    } 
    for (FieldNode fieldNode : this.classNode.fields)
      prepareField(fieldNode); 
    section.end();
    return this;
  }
  
  protected void prepareMethod(MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, ClassInfo$Method paramClassInfo$Method) {
    prepareShadow(paramMixinInfo$MixinMethodNode, paramClassInfo$Method);
    prepareSoftImplements(paramMixinInfo$MixinMethodNode, paramClassInfo$Method);
  }
  
  protected void prepareShadow(MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, ClassInfo$Method paramClassInfo$Method) {
    AnnotationNode annotationNode = Annotations.getVisible(paramMixinInfo$MixinMethodNode, Shadow.class);
  }
  
  protected void prepareSoftImplements(MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, ClassInfo$Method paramClassInfo$Method) {
    for (InterfaceInfo interfaceInfo : this.mixin.getSoftImplements()) {
      if (interfaceInfo.renameMethod(paramMixinInfo$MixinMethodNode))
        paramClassInfo$Method.renameTo(paramMixinInfo$MixinMethodNode.name); 
    } 
  }
  
  protected void prepareField(FieldNode paramFieldNode) {}
  
  final MixinPreProcessorStandard conform(TargetClassContext paramTargetClassContext) {
    return conform(paramTargetClassContext.getClassInfo());
  }
  
  final MixinPreProcessorStandard conform(ClassInfo paramClassInfo) {
    Profiler.Section section = this.profiler.begin("conform");
    for (MixinInfo$MixinMethodNode mixinInfo$MixinMethodNode : this.classNode.mixinMethods) {
      if (mixinInfo$MixinMethodNode.isInjector()) {
        ClassInfo$Method classInfo$Method = this.mixin.getClassInfo().findMethod(mixinInfo$MixinMethodNode, 10);
        conformInjector(paramClassInfo, mixinInfo$MixinMethodNode, classInfo$Method);
      } 
    } 
    section.end();
    return this;
  }
  
  private void conformInjector(ClassInfo paramClassInfo, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, ClassInfo$Method paramClassInfo$Method) {
    MethodMapper methodMapper = paramClassInfo.getMethodMapper();
    methodMapper.remapHandlerMethod(this.mixin, paramMixinInfo$MixinMethodNode, paramClassInfo$Method);
  }
  
  MixinTargetContext createContextFor(TargetClassContext paramTargetClassContext) {
    MixinTargetContext mixinTargetContext = new MixinTargetContext(this.mixin, this.classNode, paramTargetClassContext);
    conform(paramTargetClassContext);
    attach(mixinTargetContext);
    return mixinTargetContext;
  }
  
  final MixinPreProcessorStandard attach(MixinTargetContext paramMixinTargetContext) {
    if (this.attached)
      throw new IllegalStateException("Preprocessor was already attached"); 
    this.attached = true;
    Profiler.Section section1 = this.profiler.begin("attach");
    Profiler.Section section2 = this.profiler.begin("methods");
    attachMethods(paramMixinTargetContext);
    section2 = section2.next("fields");
    attachFields(paramMixinTargetContext);
    section2 = section2.next("transform");
    transform(paramMixinTargetContext);
    section2.end();
    section1.end();
    return this;
  }
  
  protected void attachMethods(MixinTargetContext paramMixinTargetContext) {
    Iterator<MixinInfo$MixinMethodNode> iterator = this.classNode.mixinMethods.iterator();
    while (iterator.hasNext()) {
      MixinInfo$MixinMethodNode mixinInfo$MixinMethodNode = iterator.next();
      if (!validateMethod(paramMixinTargetContext, mixinInfo$MixinMethodNode)) {
        iterator.remove();
        continue;
      } 
      if (attachInjectorMethod(paramMixinTargetContext, mixinInfo$MixinMethodNode)) {
        paramMixinTargetContext.addMixinMethod(mixinInfo$MixinMethodNode);
        continue;
      } 
      if (attachAccessorMethod(paramMixinTargetContext, mixinInfo$MixinMethodNode)) {
        iterator.remove();
        continue;
      } 
      if (attachShadowMethod(paramMixinTargetContext, mixinInfo$MixinMethodNode)) {
        paramMixinTargetContext.addShadowMethod(mixinInfo$MixinMethodNode);
        iterator.remove();
        continue;
      } 
      if (attachOverwriteMethod(paramMixinTargetContext, mixinInfo$MixinMethodNode)) {
        paramMixinTargetContext.addMixinMethod(mixinInfo$MixinMethodNode);
        continue;
      } 
      if (attachUniqueMethod(paramMixinTargetContext, mixinInfo$MixinMethodNode)) {
        iterator.remove();
        continue;
      } 
      attachMethod(paramMixinTargetContext, mixinInfo$MixinMethodNode);
      paramMixinTargetContext.addMixinMethod(mixinInfo$MixinMethodNode);
    } 
  }
  
  protected boolean validateMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    return true;
  }
  
  protected boolean attachInjectorMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    return paramMixinInfo$MixinMethodNode.isInjector();
  }
  
  protected boolean attachAccessorMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    return (attachAccessorMethod(paramMixinTargetContext, paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod.ACCESSOR) || attachAccessorMethod(paramMixinTargetContext, paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod.INVOKER));
  }
  
  protected boolean attachAccessorMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod paramMixinPreProcessorStandard$SpecialMethod) {
    AnnotationNode annotationNode = paramMixinInfo$MixinMethodNode.getVisibleAnnotation(paramMixinPreProcessorStandard$SpecialMethod.annotation);
    return false;
  }
  
  protected boolean attachShadowMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    return attachSpecialMethod(paramMixinTargetContext, paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod.SHADOW);
  }
  
  protected boolean attachOverwriteMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    return attachSpecialMethod(paramMixinTargetContext, paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod.OVERWRITE);
  }
  
  protected boolean attachSpecialMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod paramMixinPreProcessorStandard$SpecialMethod) {
    AnnotationNode annotationNode = paramMixinInfo$MixinMethodNode.getVisibleAnnotation(paramMixinPreProcessorStandard$SpecialMethod.annotation);
    return false;
  }
  
  private void conformVisibility(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod paramMixinPreProcessorStandard$SpecialMethod, MethodNode paramMethodNode) {
    Bytecode.Visibility visibility1 = Bytecode.getVisibility(paramMethodNode);
    Bytecode.Visibility visibility2 = Bytecode.getVisibility(paramMixinInfo$MixinMethodNode);
    if (visibility2.ordinal() >= visibility1.ordinal()) {
      if (visibility1 == Bytecode.Visibility.PRIVATE && visibility2.ordinal() > Bytecode.Visibility.PRIVATE.ordinal())
        paramMixinTargetContext.getTarget().addUpgradedMethod(paramMethodNode); 
      return;
    } 
    String str = String.format("%s %s method %s in %s cannot reduce visibiliy of %s target method", new Object[] { visibility2, paramMixinPreProcessorStandard$SpecialMethod, paramMixinInfo$MixinMethodNode.name, this.mixin, visibility1 });
    if (paramMixinPreProcessorStandard$SpecialMethod.isOverwrite && !this.mixin.getParent().conformOverwriteVisibility())
      throw new InvalidMixinException(this.mixin, str); 
    if (visibility2 == Bytecode.Visibility.PRIVATE) {
      if (paramMixinPreProcessorStandard$SpecialMethod.isOverwrite)
        logger.warn("Static binding violation: {}, visibility will be upgraded.", new Object[] { str }); 
      paramMixinTargetContext.addUpgradedMethod(paramMixinInfo$MixinMethodNode);
      Bytecode.setVisibility(paramMixinInfo$MixinMethodNode, visibility1);
    } 
  }
  
  protected ClassInfo$Method getSpecialMethod(MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod paramMixinPreProcessorStandard$SpecialMethod) {
    ClassInfo$Method classInfo$Method = this.mixin.getClassInfo().findMethod(paramMixinInfo$MixinMethodNode, 10);
    checkMethodNotUnique(classInfo$Method, paramMixinPreProcessorStandard$SpecialMethod);
    return classInfo$Method;
  }
  
  protected void checkMethodNotUnique(ClassInfo$Method paramClassInfo$Method, MixinPreProcessorStandard$SpecialMethod paramMixinPreProcessorStandard$SpecialMethod) {
    if (paramClassInfo$Method.isUnique())
      throw new InvalidMixinException(this.mixin, String.format("%s method %s in %s cannot be @Unique", new Object[] { paramMixinPreProcessorStandard$SpecialMethod, paramClassInfo$Method.getName(), this.mixin })); 
  }
  
  protected void checkMixinNotUnique(MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode, MixinPreProcessorStandard$SpecialMethod paramMixinPreProcessorStandard$SpecialMethod) {
    if (this.mixin.isUnique())
      throw new InvalidMixinException(this.mixin, String.format("%s method %s found in a @Unique mixin %s", new Object[] { paramMixinPreProcessorStandard$SpecialMethod, paramMixinInfo$MixinMethodNode.name, this.mixin })); 
  }
  
  protected boolean attachUniqueMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    ClassInfo$Method classInfo$Method = this.mixin.getClassInfo().findMethod(paramMixinInfo$MixinMethodNode, 10);
    if (!classInfo$Method.isUnique() && !this.mixin.isUnique() && !classInfo$Method.isSynthetic())
      return false; 
    if (classInfo$Method.isSynthetic()) {
      paramMixinTargetContext.transformDescriptor(paramMixinInfo$MixinMethodNode);
      classInfo$Method.remapTo(paramMixinInfo$MixinMethodNode.desc);
    } 
    MethodNode methodNode = paramMixinTargetContext.findMethod(paramMixinInfo$MixinMethodNode, null);
    return false;
  }
  
  protected void attachMethod(MixinTargetContext paramMixinTargetContext, MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    ClassInfo$Method classInfo$Method = this.mixin.getClassInfo().findMethod(paramMixinInfo$MixinMethodNode);
  }
  
  protected void attachFields(MixinTargetContext paramMixinTargetContext) {
    for (FieldNode fieldNode : this.classNode.fields)
      AnnotationNode annotationNode = Annotations.getVisible(fieldNode, Shadow.class); 
  }
  
  protected boolean validateField(MixinTargetContext paramMixinTargetContext, FieldNode paramFieldNode, AnnotationNode paramAnnotationNode) {
    if (Bytecode.hasFlag(paramFieldNode, 8) && !Bytecode.hasFlag(paramFieldNode, 2) && !Bytecode.hasFlag(paramFieldNode, 4096))
      throw new InvalidMixinException(paramMixinTargetContext, String.format("Mixin %s contains non-private static field %s:%s", new Object[] { paramMixinTargetContext, paramFieldNode.name, paramFieldNode.desc })); 
    String str = (String)Annotations.getValue(paramAnnotationNode, "prefix", Shadow.class);
    if (paramFieldNode.name.startsWith(str))
      throw new InvalidMixinException(paramMixinTargetContext, String.format("@Shadow field %s.%s has a shadow prefix. This is not allowed.", new Object[] { paramMixinTargetContext, paramFieldNode.name })); 
    if ("super$".equals(paramFieldNode.name)) {
      if (paramFieldNode.access != 2)
        throw new InvalidMixinException(this.mixin, String.format("Imaginary super field %s.%s must be private and non-final", new Object[] { paramMixinTargetContext, paramFieldNode.name })); 
      if (!paramFieldNode.desc.equals("L" + this.mixin.getClassRef() + ";"))
        throw new InvalidMixinException(this.mixin, String.format("Imaginary super field %s.%s must have the same type as the parent mixin (%s)", new Object[] { paramMixinTargetContext, paramFieldNode.name, this.mixin.getClassName() })); 
      return false;
    } 
    return true;
  }
  
  protected void transform(MixinTargetContext paramMixinTargetContext) {
    for (MethodNode methodNode : this.classNode.methods) {
      ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
      while (listIterator.hasNext()) {
        AbstractInsnNode abstractInsnNode = listIterator.next();
        if (abstractInsnNode instanceof MethodInsnNode) {
          transformMethod((MethodInsnNode)abstractInsnNode);
          continue;
        } 
        if (abstractInsnNode instanceof FieldInsnNode)
          transformField((FieldInsnNode)abstractInsnNode); 
      } 
    } 
  }
  
  protected void transformMethod(MethodInsnNode paramMethodInsnNode) {
    Profiler.Section section = this.profiler.begin("meta");
    ClassInfo classInfo = ClassInfo.forName(paramMethodInsnNode.owner);
    throw new RuntimeException(new ClassNotFoundException(paramMethodInsnNode.owner.replace('/', '.')));
  }
  
  protected void transformField(FieldInsnNode paramFieldInsnNode) {
    Profiler.Section section = this.profiler.begin("meta");
    ClassInfo classInfo = ClassInfo.forName(paramFieldInsnNode.owner);
    throw new RuntimeException(new ClassNotFoundException(paramFieldInsnNode.owner.replace('/', '.')));
  }
  
  protected static String getDynamicInfo(MethodNode paramMethodNode) {
    return getDynamicInfo("Method", Annotations.getInvisible(paramMethodNode, Dynamic.class));
  }
  
  protected static String getDynamicInfo(FieldNode paramFieldNode) {
    return getDynamicInfo("Field", Annotations.getInvisible(paramFieldNode, Dynamic.class));
  }
  
  private static String getDynamicInfo(String paramString, AnnotationNode paramAnnotationNode) {
    String str = Strings.nullToEmpty((String)Annotations.getValue(paramAnnotationNode));
    Type type = (Type)Annotations.getValue(paramAnnotationNode, "mixin");
    str = String.format("{%s} %s", new Object[] { type.getClassName(), str }).trim();
    return (str.length() > 0) ? String.format(" %s is @Dynamic(%s)", new Object[] { paramString, str }) : "";
  }
}
