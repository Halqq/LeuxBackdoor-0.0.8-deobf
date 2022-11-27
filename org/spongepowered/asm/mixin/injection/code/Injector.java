package org.spongepowered.asm.mixin.injection.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.util.Bytecode;

public abstract class Injector {
  protected static final Logger logger = LogManager.getLogger("mixin");
  
  protected InjectionInfo info;
  
  protected final ClassNode classNode;
  
  protected final MethodNode methodNode;
  
  protected final Type[] methodArgs;
  
  protected final Type returnType;
  
  protected final boolean isStatic;
  
  public Injector(InjectionInfo paramInjectionInfo) {
    this(paramInjectionInfo.getClassNode(), paramInjectionInfo.getMethod());
    this.info = paramInjectionInfo;
  }
  
  private Injector(ClassNode paramClassNode, MethodNode paramMethodNode) {
    this.classNode = paramClassNode;
    this.methodNode = paramMethodNode;
    this.methodArgs = Type.getArgumentTypes(this.methodNode.desc);
    this.returnType = Type.getReturnType(this.methodNode.desc);
    this.isStatic = Bytecode.methodIsStatic(this.methodNode);
  }
  
  public String toString() {
    return String.format("%s::%s", new Object[] { this.classNode.name, this.methodNode.name });
  }
  
  public final List find(InjectorTarget paramInjectorTarget, List paramList) {
    sanityCheck(paramInjectorTarget.getTarget(), paramList);
    ArrayList arrayList = new ArrayList();
    for (Injector$TargetNode injector$TargetNode : findTargetNodes(paramInjectorTarget, paramList))
      addTargetNode(paramInjectorTarget.getTarget(), arrayList, injector$TargetNode.insn, injector$TargetNode.nominators); 
    return arrayList;
  }
  
  protected void addTargetNode(Target paramTarget, List<InjectionNodes.InjectionNode> paramList, AbstractInsnNode paramAbstractInsnNode, Set paramSet) {
    paramList.add(paramTarget.addInjectionNode(paramAbstractInsnNode));
  }
  
  public final void inject(Target paramTarget, List paramList) {
    for (InjectionNodes.InjectionNode injectionNode : paramList) {
      if (injectionNode.isRemoved()) {
        if (this.info.getContext().getOption(MixinEnvironment.Option.DEBUG_VERBOSE))
          logger.warn("Target node for {} was removed by a previous injector in {}", new Object[] { this.info, paramTarget }); 
        continue;
      } 
      inject(paramTarget, injectionNode);
    } 
    for (InjectionNodes.InjectionNode injectionNode : paramList)
      postInject(paramTarget, injectionNode); 
  }
  
  private Collection findTargetNodes(InjectorTarget paramInjectorTarget, List paramList) {
    MethodNode methodNode = paramInjectorTarget.getMethod();
    TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();
    ArrayList arrayList = new ArrayList(32);
    for (InjectionPoint injectionPoint : paramList) {
      arrayList.clear();
      if (findTargetNodes(methodNode, injectionPoint, paramInjectorTarget.getSlice(injectionPoint), arrayList))
        for (AbstractInsnNode abstractInsnNode : arrayList) {
          Integer integer = Integer.valueOf(methodNode.instructions.indexOf(abstractInsnNode));
          Injector$TargetNode injector$TargetNode = (Injector$TargetNode)treeMap.get(integer);
          injector$TargetNode = new Injector$TargetNode(abstractInsnNode);
          treeMap.put(integer, injector$TargetNode);
          injector$TargetNode.nominators.add(injectionPoint);
        }  
    } 
    return treeMap.values();
  }
  
  protected boolean findTargetNodes(MethodNode paramMethodNode, InjectionPoint paramInjectionPoint, InsnList paramInsnList, Collection paramCollection) {
    return paramInjectionPoint.find(paramMethodNode.desc, paramInsnList, paramCollection);
  }
  
  protected void sanityCheck(Target paramTarget, List paramList) {
    if (paramTarget.classNode != this.classNode)
      throw new InvalidInjectionException(this.info, "Target class does not match injector class in " + this); 
  }
  
  protected abstract void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode);
  
  protected void postInject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {}
  
  protected AbstractInsnNode invokeHandler(InsnList paramInsnList) {
    return invokeHandler(paramInsnList, this.methodNode);
  }
  
  protected AbstractInsnNode invokeHandler(InsnList paramInsnList, MethodNode paramMethodNode) {
    boolean bool = ((paramMethodNode.access & 0x2) != 0) ? true : false;
    char c = this.isStatic ? '¸' : '¶';
    MethodInsnNode methodInsnNode = new MethodInsnNode(c, this.classNode.name, paramMethodNode.name, paramMethodNode.desc, false);
    paramInsnList.add((AbstractInsnNode)methodInsnNode);
    this.info.addCallbackInvocation(paramMethodNode);
    return (AbstractInsnNode)methodInsnNode;
  }
  
  protected void throwException(InsnList paramInsnList, String paramString1, String paramString2) {
    paramInsnList.add((AbstractInsnNode)new TypeInsnNode(187, paramString1));
    paramInsnList.add((AbstractInsnNode)new InsnNode(89));
    paramInsnList.add((AbstractInsnNode)new LdcInsnNode(paramString2));
    paramInsnList.add((AbstractInsnNode)new MethodInsnNode(183, paramString1, "<init>", "(Ljava/lang/String;)V", false));
    paramInsnList.add((AbstractInsnNode)new InsnNode(191));
  }
  
  public static boolean canCoerce(Type paramType1, Type paramType2) {
    return (paramType1.getSort() == 10 && paramType2.getSort() == 10) ? canCoerce(ClassInfo.forType(paramType1), ClassInfo.forType(paramType2)) : canCoerce(paramType1.getDescriptor(), paramType2.getDescriptor());
  }
  
  public static boolean canCoerce(String paramString1, String paramString2) {
    return (paramString1.length() > 1 || paramString2.length() > 1) ? false : canCoerce(paramString1.charAt(0), paramString2.charAt(0));
  }
  
  public static boolean canCoerce(char paramChar1, char paramChar2) {
    return (paramChar2 == 'I' && "IBSCZ".indexOf(paramChar1) > -1);
  }
  
  private static boolean canCoerce(ClassInfo paramClassInfo1, ClassInfo paramClassInfo2) {
    return (paramClassInfo2 == paramClassInfo1 || paramClassInfo2.hasSuperClass(paramClassInfo1));
  }
}
