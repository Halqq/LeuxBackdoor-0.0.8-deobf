package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;

public class ModifyConstantInjector extends RedirectInjector {
  private static final int OPCODE_OFFSET = 6;
  
  public ModifyConstantInjector(InjectionInfo paramInjectionInfo) {
    super(paramInjectionInfo, "@ModifyConstant");
  }
  
  protected void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    if (!preInject(paramInjectionNode))
      return; 
    if (paramInjectionNode.isReplaced())
      throw new UnsupportedOperationException("Target failure for " + this.info); 
    AbstractInsnNode abstractInsnNode = paramInjectionNode.getCurrentTarget();
    if (abstractInsnNode instanceof JumpInsnNode) {
      checkTargetModifiers(paramTarget, false);
      injectExpandedConstantModifier(paramTarget, (JumpInsnNode)abstractInsnNode);
      return;
    } 
    if (Bytecode.isConstant(abstractInsnNode)) {
      checkTargetModifiers(paramTarget, false);
      injectConstantModifier(paramTarget, abstractInsnNode);
      return;
    } 
    throw new InvalidInjectionException(this.info, this.annotationType + " annotation is targetting an invalid insn in " + paramTarget + " in " + this);
  }
  
  private void injectExpandedConstantModifier(Target paramTarget, JumpInsnNode paramJumpInsnNode) {
    int i = paramJumpInsnNode.getOpcode();
    if (i < 155 || i > 158)
      throw new InvalidInjectionException(this.info, this.annotationType + " annotation selected an invalid opcode " + Bytecode.getOpcodeName(i) + " in " + paramTarget + " in " + this); 
    InsnList insnList = new InsnList();
    insnList.add((AbstractInsnNode)new InsnNode(3));
    AbstractInsnNode abstractInsnNode = invokeConstantHandler(Type.getType("I"), paramTarget, insnList, insnList);
    insnList.add((AbstractInsnNode)new JumpInsnNode(i + 6, paramJumpInsnNode.label));
    paramTarget.replaceNode((AbstractInsnNode)paramJumpInsnNode, abstractInsnNode, insnList);
    paramTarget.addToStack(1);
  }
  
  private void injectConstantModifier(Target paramTarget, AbstractInsnNode paramAbstractInsnNode) {
    Type type = Bytecode.getConstantType(paramAbstractInsnNode);
    InsnList insnList1 = new InsnList();
    InsnList insnList2 = new InsnList();
    AbstractInsnNode abstractInsnNode = invokeConstantHandler(type, paramTarget, insnList1, insnList2);
    paramTarget.wrapNode(paramAbstractInsnNode, abstractInsnNode, insnList1, insnList2);
  }
  
  private AbstractInsnNode invokeConstantHandler(Type paramType, Target paramTarget, InsnList paramInsnList1, InsnList paramInsnList2) {
    String str = Bytecode.generateDescriptor(paramType, new Object[] { paramType });
    boolean bool = checkDescriptor(str, paramTarget, "getter");
    if (!this.isStatic) {
      paramInsnList1.insert((AbstractInsnNode)new VarInsnNode(25, 0));
      paramTarget.addToStack(1);
    } 
    pushArgs(paramTarget.arguments, paramInsnList2, paramTarget.getArgIndices(), 0, paramTarget.arguments.length);
    paramTarget.addToStack(Bytecode.getArgsSize(paramTarget.arguments));
    return invokeHandler(paramInsnList2);
  }
}
