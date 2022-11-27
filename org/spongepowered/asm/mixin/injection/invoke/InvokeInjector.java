package org.spongepowered.asm.mixin.injection.invoke;

import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;

public abstract class InvokeInjector extends Injector {
  protected final String annotationType;
  
  public InvokeInjector(InjectionInfo paramInjectionInfo, String paramString) {
    super(paramInjectionInfo);
    this.annotationType = paramString;
  }
  
  protected void sanityCheck(Target paramTarget, List paramList) {
    super.sanityCheck(paramTarget, paramList);
    checkTarget(paramTarget);
  }
  
  protected void checkTarget(Target paramTarget) {
    checkTargetModifiers(paramTarget, true);
  }
  
  protected final void checkTargetModifiers(Target paramTarget, boolean paramBoolean) {
    paramBoolean = true;
    if (paramTarget.isStatic != this.isStatic)
      throw new InvalidInjectionException(this.info, "'static' modifier of handler method does not match target in " + this); 
    if (!this.isStatic && paramTarget.isStatic)
      throw new InvalidInjectionException(this.info, "non-static callback method " + this + " targets a static method which is not supported"); 
  }
  
  protected void checkTargetForNode(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    if (paramTarget.isCtor) {
      MethodInsnNode methodInsnNode = paramTarget.findSuperInitNode();
      int i = paramTarget.indexOf((AbstractInsnNode)methodInsnNode);
      int j = paramTarget.indexOf(paramInjectionNode.getCurrentTarget());
      if (j <= i) {
        if (!this.isStatic)
          throw new InvalidInjectionException(this.info, "Pre-super " + this.annotationType + " invocation must be static in " + this); 
        return;
      } 
    } 
    checkTargetModifiers(paramTarget, true);
  }
  
  protected void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    if (!(paramInjectionNode.getCurrentTarget() instanceof MethodInsnNode))
      throw new InvalidInjectionException(this.info, this.annotationType + " annotation on is targetting a non-method insn in " + paramTarget + " in " + this); 
    injectAtInvoke(paramTarget, paramInjectionNode);
  }
  
  protected abstract void injectAtInvoke(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode);
  
  protected AbstractInsnNode invokeHandlerWithArgs(Type[] paramArrayOfType, InsnList paramInsnList, int[] paramArrayOfint) {
    return invokeHandlerWithArgs(paramArrayOfType, paramInsnList, paramArrayOfint, 0, paramArrayOfType.length);
  }
  
  protected AbstractInsnNode invokeHandlerWithArgs(Type[] paramArrayOfType, InsnList paramInsnList, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    paramInt1 = 0;
    if (!this.isStatic)
      paramInsnList.add((AbstractInsnNode)new VarInsnNode(25, 0)); 
    pushArgs(paramArrayOfType, paramInsnList, paramArrayOfint, paramInt1, paramInt2);
    return invokeHandler(paramInsnList);
  }
  
  protected int[] storeArgs(Target paramTarget, Type[] paramArrayOfType, InsnList paramInsnList, int paramInt) {
    int[] arrayOfInt = paramTarget.generateArgMap(paramArrayOfType, paramInt);
    storeArgs(paramArrayOfType, paramInsnList, arrayOfInt, paramInt, paramArrayOfType.length);
    return arrayOfInt;
  }
  
  protected void storeArgs(Type[] paramArrayOfType, InsnList paramInsnList, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    for (int i = paramInt2 - 1; i >= paramInt1; i--)
      paramInsnList.add((AbstractInsnNode)new VarInsnNode(paramArrayOfType[i].getOpcode(54), paramArrayOfint[i])); 
  }
  
  protected void pushArgs(Type[] paramArrayOfType, InsnList paramInsnList, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    paramInt1 = 0;
    for (int i = paramInt1; i < paramInt2; i++)
      paramInsnList.add((AbstractInsnNode)new VarInsnNode(paramArrayOfType[i].getOpcode(21), paramArrayOfint[i])); 
  }
}
