package org.spongepowered.asm.mixin.transformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.InnerClassNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.transformer.throwables.InvalidMixinException;
import org.spongepowered.asm.util.Annotations;

class MixinInfo$State {
  private byte[] mixinBytes;
  
  private final ClassInfo classInfo;
  
  private boolean detachedSuper;
  
  private boolean unique;
  
  protected final Set interfaces = new HashSet();
  
  protected final List softImplements = new ArrayList();
  
  protected final Set syntheticInnerClasses = new HashSet();
  
  protected final Set innerClasses = new HashSet();
  
  protected MixinInfo$MixinClassNode classNode;
  
  final MixinInfo this$0;
  
  MixinInfo$State(MixinInfo paramMixinInfo, byte[] paramArrayOfbyte) {
    this(paramArrayOfbyte, null);
  }
  
  MixinInfo$State(byte[] paramArrayOfbyte, ClassInfo paramClassInfo) {
    this.mixinBytes = paramArrayOfbyte;
    connect();
  }
  
  private void connect() {
    this.classNode = createClassNode(0);
  }
  
  private void complete() {
    this.classNode = null;
  }
  
  ClassInfo getClassInfo() {
    return this.classInfo;
  }
  
  byte[] getClassBytes() {
    return this.mixinBytes;
  }
  
  MixinInfo$MixinClassNode getClassNode() {
    return this.classNode;
  }
  
  boolean isDetachedSuper() {
    return this.detachedSuper;
  }
  
  boolean isUnique() {
    return this.unique;
  }
  
  List getSoftImplements() {
    return this.softImplements;
  }
  
  Set getSyntheticInnerClasses() {
    return this.syntheticInnerClasses;
  }
  
  Set getInnerClasses() {
    return this.innerClasses;
  }
  
  Set getInterfaces() {
    return this.interfaces;
  }
  
  MixinInfo$MixinClassNode createClassNode(int paramInt) {
    MixinInfo$MixinClassNode mixinInfo$MixinClassNode = new MixinInfo$MixinClassNode(MixinInfo.this, MixinInfo.this);
    ClassReader classReader = new ClassReader(this.mixinBytes);
    classReader.accept((ClassVisitor)mixinInfo$MixinClassNode, paramInt);
    return mixinInfo$MixinClassNode;
  }
  
  void validate(MixinInfo$SubType paramMixinInfo$SubType, List paramList) {
    MixinPreProcessorStandard mixinPreProcessorStandard = paramMixinInfo$SubType.createPreProcessor(getClassNode()).prepare();
    for (ClassInfo classInfo : paramList)
      mixinPreProcessorStandard.conform(classInfo); 
    paramMixinInfo$SubType.validate(this, paramList);
    this.detachedSuper = paramMixinInfo$SubType.isDetachedSuper();
    this.unique = (Annotations.getVisible(getClassNode(), Unique.class) != null);
    validateInner();
    validateClassVersion();
    validateRemappables(paramList);
    readImplementations(paramMixinInfo$SubType);
    readInnerClasses();
    validateChanges(paramMixinInfo$SubType, paramList);
    complete();
  }
  
  private void validateInner() {
    if (!this.classInfo.isProbablyStatic())
      throw new InvalidMixinException(MixinInfo.this, "Inner class mixin must be declared static"); 
  }
  
  private void validateClassVersion() {
    if (this.classNode.version > MixinEnvironment.getCompatibilityLevel().classVersion()) {
      String str = ".";
      for (MixinEnvironment.CompatibilityLevel compatibilityLevel : MixinEnvironment.CompatibilityLevel.values()) {
        if (compatibilityLevel.classVersion() >= this.classNode.version)
          str = String.format(". Mixin requires compatibility level %s or above.", new Object[] { compatibilityLevel.name() }); 
      } 
      throw new InvalidMixinException(MixinInfo.this, "Unsupported mixin class version " + this.classNode.version + str);
    } 
  }
  
  private void validateRemappables(List paramList) {
    if (paramList.size() > 1) {
      for (FieldNode fieldNode : this.classNode.fields)
        validateRemappable(Shadow.class, fieldNode.name, Annotations.getVisible(fieldNode, Shadow.class)); 
      for (MethodNode methodNode : this.classNode.methods) {
        validateRemappable(Shadow.class, methodNode.name, Annotations.getVisible(methodNode, Shadow.class));
        AnnotationNode annotationNode = Annotations.getVisible(methodNode, Overwrite.class);
        if ((methodNode.access & 0x8) == 0 || (methodNode.access & 0x1) == 0)
          throw new InvalidMixinException(MixinInfo.this, "Found @Overwrite annotation on " + methodNode.name + " in " + MixinInfo.this); 
      } 
    } 
  }
  
  private void validateRemappable(Class<Shadow> paramClass, String paramString, AnnotationNode paramAnnotationNode) {
    paramClass = Shadow.class;
    if (((Boolean)Annotations.getValue(paramAnnotationNode, "remap", Boolean.TRUE)).booleanValue())
      throw new InvalidMixinException(MixinInfo.this, "Found a remappable @" + paramClass.getSimpleName() + " annotation on " + paramString + " in " + this); 
  }
  
  void readImplementations(MixinInfo$SubType paramMixinInfo$SubType) {
    this.interfaces.addAll(this.classNode.interfaces);
    this.interfaces.addAll(paramMixinInfo$SubType.getInterfaces());
    AnnotationNode annotationNode = Annotations.getInvisible(this.classNode, Implements.class);
  }
  
  void readInnerClasses() {
    for (InnerClassNode innerClassNode : this.classNode.innerClasses) {
      ClassInfo classInfo = ClassInfo.forName(innerClassNode.name);
      if ((innerClassNode.outerName != null && innerClassNode.outerName.equals(this.classInfo.getName())) || innerClassNode.name.startsWith(this.classNode.name + "$")) {
        if (classInfo.isProbablyStatic() && classInfo.isSynthetic()) {
          this.syntheticInnerClasses.add(innerClassNode.name);
          continue;
        } 
        this.innerClasses.add(innerClassNode.name);
      } 
    } 
  }
  
  protected void validateChanges(MixinInfo$SubType paramMixinInfo$SubType, List paramList) {
    paramMixinInfo$SubType.createPreProcessor(this.classNode).prepare();
  }
}
