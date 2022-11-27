package org.spongepowered.asm.mixin.transformer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.transformer.throwables.MixinTransformerError;
import org.spongepowered.asm.transformers.MixinClassWriter;
import org.spongepowered.asm.transformers.TreeTransformer;
import org.spongepowered.asm.util.Bytecode;

class MixinPostProcessor extends TreeTransformer implements MixinConfig$IListener {
  private final Set syntheticInnerClasses = new HashSet();
  
  private final Map accessorMixins = new HashMap<Object, Object>();
  
  private final Set loadable = new HashSet();
  
  public void onInit(MixinInfo paramMixinInfo) {
    for (String str : paramMixinInfo.getSyntheticInnerClasses())
      registerSyntheticInner(str.replace('/', '.')); 
  }
  
  public void onPrepare(MixinInfo paramMixinInfo) {
    String str = paramMixinInfo.getClassName();
    if (paramMixinInfo.isLoadable())
      registerLoadable(str); 
    if (paramMixinInfo.isAccessor())
      registerAccessor(paramMixinInfo); 
  }
  
  void registerSyntheticInner(String paramString) {
    this.syntheticInnerClasses.add(paramString);
  }
  
  void registerLoadable(String paramString) {
    this.loadable.add(paramString);
  }
  
  void registerAccessor(MixinInfo paramMixinInfo) {
    registerLoadable(paramMixinInfo.getClassName());
    this.accessorMixins.put(paramMixinInfo.getClassName(), paramMixinInfo);
  }
  
  boolean canTransform(String paramString) {
    return (this.syntheticInnerClasses.contains(paramString) || this.loadable.contains(paramString));
  }
  
  public String getName() {
    return getClass().getName();
  }
  
  public boolean isDelegationExcluded() {
    return true;
  }
  
  public byte[] transformClassBytes(String paramString1, String paramString2, byte[] paramArrayOfbyte) {
    if (this.syntheticInnerClasses.contains(paramString2))
      return processSyntheticInner(paramArrayOfbyte); 
    if (this.accessorMixins.containsKey(paramString2)) {
      MixinInfo mixinInfo = (MixinInfo)this.accessorMixins.get(paramString2);
      return processAccessor(paramArrayOfbyte, mixinInfo);
    } 
    return paramArrayOfbyte;
  }
  
  private byte[] processSyntheticInner(byte[] paramArrayOfbyte) {
    ClassReader classReader = new ClassReader(paramArrayOfbyte);
    MixinClassWriter mixinClassWriter = new MixinClassWriter(classReader, 0);
    MixinPostProcessor$1 mixinPostProcessor$1 = new MixinPostProcessor$1(this, 327680, (ClassVisitor)mixinClassWriter);
    classReader.accept(mixinPostProcessor$1, 8);
    return mixinClassWriter.toByteArray();
  }
  
  private byte[] processAccessor(byte[] paramArrayOfbyte, MixinInfo paramMixinInfo) {
    if (!MixinEnvironment.getCompatibilityLevel().isAtLeast(MixinEnvironment.CompatibilityLevel.JAVA_8))
      return paramArrayOfbyte; 
    boolean bool = false;
    MixinInfo$MixinClassNode mixinInfo$MixinClassNode = paramMixinInfo.getClassNode(0);
    ClassInfo classInfo = paramMixinInfo.getTargets().get(0);
    for (MixinInfo$MixinMethodNode mixinInfo$MixinMethodNode : mixinInfo$MixinClassNode.mixinMethods) {
      if (!Bytecode.hasFlag(mixinInfo$MixinMethodNode, 8))
        continue; 
      AnnotationNode annotationNode1 = mixinInfo$MixinMethodNode.getVisibleAnnotation(Accessor.class);
      AnnotationNode annotationNode2 = mixinInfo$MixinMethodNode.getVisibleAnnotation(Invoker.class);
      ClassInfo$Method classInfo$Method = getAccessorMethod(paramMixinInfo, mixinInfo$MixinMethodNode, classInfo);
      createProxy(mixinInfo$MixinMethodNode, classInfo, classInfo$Method);
      bool = true;
    } 
    return paramArrayOfbyte;
  }
  
  private static ClassInfo$Method getAccessorMethod(MixinInfo paramMixinInfo, MethodNode paramMethodNode, ClassInfo paramClassInfo) throws MixinTransformerError {
    ClassInfo$Method classInfo$Method = paramMixinInfo.getClassInfo().findMethod(paramMethodNode, 10);
    if (!classInfo$Method.isRenamed())
      throw new MixinTransformerError("Unexpected state: " + paramMixinInfo + " loaded before " + paramClassInfo + " was conformed"); 
    return classInfo$Method;
  }
  
  private static void createProxy(MethodNode paramMethodNode, ClassInfo paramClassInfo, ClassInfo$Method paramClassInfo$Method) {
    paramMethodNode.instructions.clear();
    Type[] arrayOfType = Type.getArgumentTypes(paramMethodNode.desc);
    Type type = Type.getReturnType(paramMethodNode.desc);
    Bytecode.loadArgs(arrayOfType, paramMethodNode.instructions, 0);
    paramMethodNode.instructions.add((AbstractInsnNode)new MethodInsnNode(184, paramClassInfo.getName(), paramClassInfo$Method.getName(), paramMethodNode.desc, false));
    paramMethodNode.instructions.add((AbstractInsnNode)new InsnNode(type.getOpcode(172)));
    paramMethodNode.maxStack = Bytecode.getFirstNonArgLocalIndex(arrayOfType, false);
    paramMethodNode.maxLocals = 0;
  }
}
