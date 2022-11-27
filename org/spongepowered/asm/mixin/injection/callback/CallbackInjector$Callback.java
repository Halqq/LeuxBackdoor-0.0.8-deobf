package org.spongepowered.asm.mixin.injection.callback;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.util.Annotations;

class CallbackInjector$Callback extends InsnList {
  private final MethodNode handler;
  
  private final AbstractInsnNode head;
  
  final Target target;
  
  final InjectionNodes.InjectionNode node;
  
  final LocalVariableNode[] locals;
  
  final Type[] localTypes;
  
  final int frameSize;
  
  final int extraArgs;
  
  final boolean canCaptureLocals;
  
  final boolean isAtReturn;
  
  final String desc;
  
  final String descl;
  
  final String[] argNames;
  
  int ctor;
  
  int invoke;
  
  private int marshalVar = -1;
  
  private boolean captureArgs = true;
  
  final CallbackInjector this$0;
  
  CallbackInjector$Callback(MethodNode paramMethodNode, Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode, LocalVariableNode[] paramArrayOfLocalVariableNode, boolean paramBoolean) {
    this.handler = paramMethodNode;
    this.target = paramTarget;
    this.head = paramTarget.insns.getFirst();
    this.node = paramInjectionNode;
    this.locals = paramArrayOfLocalVariableNode;
  }
  
  private boolean isValueReturnOpcode(int paramInt) {
    return (paramInt >= 172 && paramInt < 177);
  }
  
  String getDescriptor() {
    return this.canCaptureLocals ? this.descl : this.desc;
  }
  
  String getDescriptorWithAllLocals() {
    return this.target.getCallbackDescriptor(true, this.localTypes, this.target.arguments, this.frameSize, 32767);
  }
  
  String getCallbackInfoConstructorDescriptor() {
    return this.isAtReturn ? CallbackInfo.getConstructorDescriptor(this.target.returnType) : CallbackInfo.getConstructorDescriptor();
  }
  
  void add(AbstractInsnNode paramAbstractInsnNode, boolean paramBoolean1, boolean paramBoolean2) {
    add(paramAbstractInsnNode, paramBoolean1, paramBoolean2, false);
  }
  
  void add(AbstractInsnNode paramAbstractInsnNode, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
    paramBoolean3 = false;
    this.target.insns.insertBefore(this.head, paramAbstractInsnNode);
  }
  
  void inject() {
    this.target.insertBefore(this.node, this);
    this.target.addToStack(Math.max(this.invoke, this.ctor));
  }
  
  boolean checkDescriptor(String paramString) {
    if (getDescriptor().equals(paramString))
      return true; 
    if (this.target.getSimpleCallbackDescriptor().equals(paramString) && !this.canCaptureLocals) {
      this.captureArgs = false;
      return true;
    } 
    Type[] arrayOfType1 = Type.getArgumentTypes(paramString);
    Type[] arrayOfType2 = Type.getArgumentTypes(this.descl);
    if (arrayOfType1.length != arrayOfType2.length)
      return false; 
    for (byte b = 0; b < arrayOfType2.length; b++) {
      Type type = arrayOfType1[b];
      if (!type.equals(arrayOfType2[b])) {
        if (type.getSort() == 9)
          return false; 
        if (Annotations.getInvisibleParameter(this.handler, Coerce.class, b) == null)
          return false; 
        if (!Injector.canCoerce(arrayOfType1[b], arrayOfType2[b]))
          return false; 
      } 
    } 
    return true;
  }
  
  boolean captureArgs() {
    return this.captureArgs;
  }
  
  int marshalVar() {
    if (this.marshalVar < 0)
      this.marshalVar = this.target.allocateLocal(); 
    return this.marshalVar;
  }
}
