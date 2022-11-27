package org.spongepowered.asm.mixin.injection.invoke;

import java.util.Arrays;
import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;

public class ModifyArgInjector extends InvokeInjector {
  private final int index;
  
  private final boolean singleArgMode;
  
  public ModifyArgInjector(InjectionInfo paramInjectionInfo, int paramInt) {
    super(paramInjectionInfo, "@ModifyArg");
    this.index = paramInt;
    this.singleArgMode = (this.methodArgs.length == 1);
  }
  
  protected void sanityCheck(Target paramTarget, List paramList) {
    super.sanityCheck(paramTarget, paramList);
    if (this.singleArgMode && !this.methodArgs[0].equals(this.returnType))
      throw new InvalidInjectionException(this.info, "@ModifyArg return type on " + this + " must match the parameter type. ARG=" + this.methodArgs[0] + " RETURN=" + this.returnType); 
  }
  
  protected void checkTarget(Target paramTarget) {
    if (!this.isStatic && paramTarget.isStatic)
      throw new InvalidInjectionException(this.info, "non-static callback method " + this + " targets a static method which is not supported"); 
  }
  
  protected void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    checkTargetForNode(paramTarget, paramInjectionNode);
    super.inject(paramTarget, paramInjectionNode);
  }
  
  protected void injectAtInvoke(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    MethodInsnNode methodInsnNode = (MethodInsnNode)paramInjectionNode.getCurrentTarget();
    Type[] arrayOfType = Type.getArgumentTypes(methodInsnNode.desc);
    int i = findArgIndex(paramTarget, arrayOfType);
    InsnList insnList = new InsnList();
    int j = 0;
    if (this.singleArgMode) {
      j = injectSingleArgHandler(paramTarget, arrayOfType, i, insnList);
    } else {
      j = injectMultiArgHandler(paramTarget, arrayOfType, i, insnList);
    } 
    paramTarget.insns.insertBefore((AbstractInsnNode)methodInsnNode, insnList);
    paramTarget.addToLocals(j);
    paramTarget.addToStack(2 - j - 1);
  }
  
  private int injectSingleArgHandler(Target paramTarget, Type[] paramArrayOfType, int paramInt, InsnList paramInsnList) {
    int[] arrayOfInt = storeArgs(paramTarget, paramArrayOfType, paramInsnList, paramInt);
    invokeHandlerWithArgs(paramArrayOfType, paramInsnList, arrayOfInt, paramInt, paramInt + 1);
    pushArgs(paramArrayOfType, paramInsnList, arrayOfInt, paramInt + 1, paramArrayOfType.length);
    return arrayOfInt[arrayOfInt.length - 1] - paramTarget.getMaxLocals() + paramArrayOfType[paramArrayOfType.length - 1].getSize();
  }
  
  private int injectMultiArgHandler(Target paramTarget, Type[] paramArrayOfType, int paramInt, InsnList paramInsnList) {
    if (!Arrays.equals((Object[])paramArrayOfType, (Object[])this.methodArgs))
      throw new InvalidInjectionException(this.info, "@ModifyArg method " + this + " targets a method with an invalid signature " + Bytecode.getDescriptor(paramArrayOfType) + ", expected " + Bytecode.getDescriptor(this.methodArgs)); 
    int[] arrayOfInt = storeArgs(paramTarget, paramArrayOfType, paramInsnList, 0);
    pushArgs(paramArrayOfType, paramInsnList, arrayOfInt, 0, paramInt);
    invokeHandlerWithArgs(paramArrayOfType, paramInsnList, arrayOfInt, 0, paramArrayOfType.length);
    pushArgs(paramArrayOfType, paramInsnList, arrayOfInt, paramInt + 1, paramArrayOfType.length);
    return arrayOfInt[arrayOfInt.length - 1] - paramTarget.getMaxLocals() + paramArrayOfType[paramArrayOfType.length - 1].getSize();
  }
  
  protected int findArgIndex(Target paramTarget, Type[] paramArrayOfType) {
    if (this.index > -1) {
      if (this.index >= paramArrayOfType.length || !paramArrayOfType[this.index].equals(this.returnType))
        throw new InvalidInjectionException(this.info, "Specified index " + this.index + " for @ModifyArg is invalid for args " + Bytecode.getDescriptor(paramArrayOfType) + ", expected " + this.returnType + " on " + this); 
      return this.index;
    } 
    byte b = -1;
    for (byte b1 = 0; b1 < paramArrayOfType.length; b1++) {
      if (paramArrayOfType[b1].equals(this.returnType)) {
        -1;
        b = b1;
      } 
    } 
    -1;
    throw new InvalidInjectionException(this.info, "Could not find arg matching type " + this.returnType + " for @ModifyArg target " + paramTarget + " in " + this);
  }
}
