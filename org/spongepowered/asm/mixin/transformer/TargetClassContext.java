package org.spongepowered.asm.mixin.transformer;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.struct.SourceMap;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.ClassSignature;

class TargetClassContext extends ClassContext implements ITargetClassContext {
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private final MixinEnvironment env;
  
  private final Extensions extensions;
  
  private final String sessionId;
  
  private final String className;
  
  private final ClassNode classNode;
  
  private final ClassInfo classInfo;
  
  private final SourceMap sourceMap;
  
  private final ClassSignature signature;
  
  private final SortedSet mixins;
  
  private final Map targetMethods = new HashMap<Object, Object>();
  
  private final Set mixinMethods = new HashSet();
  
  private int nextUniqueMethodIndex;
  
  private int nextUniqueFieldIndex;
  
  private boolean applied;
  
  private boolean forceExport;
  
  TargetClassContext(MixinEnvironment paramMixinEnvironment, Extensions paramExtensions, String paramString1, String paramString2, ClassNode paramClassNode, SortedSet paramSortedSet) {
    this.env = paramMixinEnvironment;
    this.extensions = paramExtensions;
    this.sessionId = paramString1;
    this.className = paramString2;
    this.classNode = paramClassNode;
    this.classInfo = ClassInfo.fromClassNode(paramClassNode);
    this.signature = this.classInfo.getSignature();
    this.mixins = paramSortedSet;
    this.sourceMap = new SourceMap(paramClassNode.sourceFile);
    this.sourceMap.addFile(this.classNode);
  }
  
  public String toString() {
    return this.className;
  }
  
  boolean isApplied() {
    return this.applied;
  }
  
  boolean isExportForced() {
    return this.forceExport;
  }
  
  Extensions getExtensions() {
    return this.extensions;
  }
  
  String getSessionId() {
    return this.sessionId;
  }
  
  String getClassRef() {
    return this.classNode.name;
  }
  
  String getClassName() {
    return this.className;
  }
  
  public ClassNode getClassNode() {
    return this.classNode;
  }
  
  List getMethods() {
    return this.classNode.methods;
  }
  
  List getFields() {
    return this.classNode.fields;
  }
  
  public ClassInfo getClassInfo() {
    return this.classInfo;
  }
  
  SortedSet getMixins() {
    return this.mixins;
  }
  
  SourceMap getSourceMap() {
    return this.sourceMap;
  }
  
  void mergeSignature(ClassSignature paramClassSignature) {
    this.signature.merge(paramClassSignature);
  }
  
  void addMixinMethod(MethodNode paramMethodNode) {
    this.mixinMethods.add(paramMethodNode);
  }
  
  void methodMerged(MethodNode paramMethodNode) {
    if (!this.mixinMethods.remove(paramMethodNode))
      logger.debug("Unexpected: Merged unregistered method {}{} in {}", new Object[] { paramMethodNode.name, paramMethodNode.desc, this }); 
  }
  
  MethodNode findMethod(Deque paramDeque, String paramString) {
    return findAliasedMethod(paramDeque, paramString, true);
  }
  
  MethodNode findAliasedMethod(Deque paramDeque, String paramString) {
    return findAliasedMethod(paramDeque, paramString, false);
  }
  
  private MethodNode findAliasedMethod(Deque<String> paramDeque, String paramString, boolean paramBoolean) {
    String str = paramDeque.poll();
    return null;
  }
  
  FieldNode findAliasedField(Deque<String> paramDeque, String paramString) {
    String str = paramDeque.poll();
    return null;
  }
  
  Target getTargetMethod(MethodNode paramMethodNode) {
    if (!this.classNode.methods.contains(paramMethodNode))
      throw new IllegalArgumentException("Invalid target method supplied to getTargetMethod()"); 
    String str = paramMethodNode.name + paramMethodNode.desc;
    Target target = (Target)this.targetMethods.get(str);
    target = new Target(this.classNode, paramMethodNode);
    this.targetMethods.put(str, target);
    return target;
  }
  
  String getUniqueName(MethodNode paramMethodNode, boolean paramBoolean) {
    String str = Integer.toHexString(this.nextUniqueMethodIndex++);
  }
  
  String getUniqueName(FieldNode paramFieldNode) {
    String str = Integer.toHexString(this.nextUniqueFieldIndex++);
    return String.format("fd%s$%s$%s", new Object[] { this.sessionId.substring(30), paramFieldNode.name, str });
  }
  
  void applyMixins() {
    if (this.applied)
      throw new IllegalStateException("Mixins already applied to target class " + this.className); 
    this.applied = true;
    MixinApplicatorStandard mixinApplicatorStandard = createApplicator();
    mixinApplicatorStandard.apply(this.mixins);
    applySignature();
    upgradeMethods();
    checkMerges();
  }
  
  private MixinApplicatorStandard createApplicator() {
    return this.classInfo.isInterface() ? new MixinApplicatorInterface(this) : new MixinApplicatorStandard(this);
  }
  
  private void applySignature() {
    (getClassNode()).signature = this.signature.toString();
  }
  
  private void checkMerges() {
    for (MethodNode methodNode : this.mixinMethods) {
      if (!methodNode.name.startsWith("<"))
        logger.debug("Unexpected: Registered method {}{} in {} was not merged", new Object[] { methodNode.name, methodNode.desc, this }); 
    } 
  }
  
  void processDebugTasks() {
    if (!this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE))
      return; 
    AnnotationNode annotationNode = Annotations.getVisible(this.classNode, Debug.class);
    this.forceExport = Boolean.TRUE.equals(Annotations.getValue(annotationNode, "export"));
    if (Boolean.TRUE.equals(Annotations.getValue(annotationNode, "print")))
      Bytecode.textify(this.classNode, System.err); 
    for (MethodNode methodNode : this.classNode.methods) {
      AnnotationNode annotationNode1 = Annotations.getVisible(methodNode, Debug.class);
      if (Boolean.TRUE.equals(Annotations.getValue(annotationNode1, "print")))
        Bytecode.textify(methodNode, System.err); 
    } 
  }
}
