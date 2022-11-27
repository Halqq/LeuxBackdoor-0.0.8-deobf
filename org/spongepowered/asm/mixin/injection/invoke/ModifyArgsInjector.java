package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.invoke.arg.ArgsClassGenerator;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;

public class ModifyArgsInjector extends InvokeInjector {
  private final ArgsClassGenerator argsClassGenerator;
  
  public ModifyArgsInjector(InjectionInfo paramInjectionInfo) {
    super(paramInjectionInfo, "@ModifyArgs");
    this.argsClassGenerator = (ArgsClassGenerator)paramInjectionInfo.getContext().getExtensions().getGenerator(ArgsClassGenerator.class);
  }
  
  protected void checkTarget(Target paramTarget) {
    checkTargetModifiers(paramTarget, false);
  }
  
  protected void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    checkTargetForNode(paramTarget, paramInjectionNode);
    super.inject(paramTarget, paramInjectionNode);
  }
  
  protected void injectAtInvoke(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    MethodInsnNode methodInsnNode = (MethodInsnNode)paramInjectionNode.getCurrentTarget();
    Type[] arrayOfType = Type.getArgumentTypes(methodInsnNode.desc);
    if (arrayOfType.length == 0)
      throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " targets a method invocation " + methodInsnNode.name + methodInsnNode.desc + " with no arguments!"); 
    String str = this.argsClassGenerator.getClassRef(methodInsnNode.desc);
    boolean bool = verifyTarget(paramTarget);
    InsnList insnList = new InsnList();
    paramTarget.addToStack(1);
    packArgs(insnList, str, methodInsnNode);
    paramTarget.addToStack(Bytecode.getArgsSize(paramTarget.arguments));
    Bytecode.loadArgs(paramTarget.arguments, insnList, paramTarget.isStatic ? 0 : 1);
    invokeHandler(insnList);
    unpackArgs(insnList, str, arrayOfType);
    paramTarget.insns.insertBefore((AbstractInsnNode)methodInsnNode, insnList);
  }
  
  private boolean verifyTarget(Target paramTarget) {
    String str = String.format("(L%s;)V", new Object[] { ArgsClassGenerator.ARGS_REF });
    if (!this.methodNode.desc.equals(str)) {
      String str1 = Bytecode.changeDescriptorReturnType(paramTarget.method.desc, "V");
      String str2 = String.format("(L%s;%s", new Object[] { ArgsClassGenerator.ARGS_REF, str1.substring(1) });
      if (this.methodNode.desc.equals(str2))
        return true; 
      throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " has an invalid signature " + this.methodNode.desc + ", expected " + str + " or " + str2);
    } 
    return false;
  }
  
  private void packArgs(InsnList paramInsnList, String paramString, MethodInsnNode paramMethodInsnNode) {
    String str = Bytecode.changeDescriptorReturnType(paramMethodInsnNode.desc, "L" + paramString + ";");
    paramInsnList.add((AbstractInsnNode)new MethodInsnNode(184, paramString, "of", str, false));
    paramInsnList.add((AbstractInsnNode)new InsnNode(89));
    if (!this.isStatic) {
      paramInsnList.add((AbstractInsnNode)new VarInsnNode(25, 0));
      paramInsnList.add((AbstractInsnNode)new InsnNode(95));
    } 
  }
  
  private void unpackArgs(InsnList paramInsnList, String paramString, Type[] paramArrayOfType) {
    for (byte b = 0; b < paramArrayOfType.length; b++) {
      if (b < paramArrayOfType.length - 1)
        paramInsnList.add((AbstractInsnNode)new InsnNode(89)); 
      paramInsnList.add((AbstractInsnNode)new MethodInsnNode(182, paramString, "$" + b, "()" + paramArrayOfType[b].getDescriptor(), false));
      if (b < paramArrayOfType.length - 1)
        if (paramArrayOfType[b].getSize() == 1) {
          paramInsnList.add((AbstractInsnNode)new InsnNode(95));
        } else {
          paramInsnList.add((AbstractInsnNode)new InsnNode(93));
          paramInsnList.add((AbstractInsnNode)new InsnNode(88));
        }  
    } 
  }
}
