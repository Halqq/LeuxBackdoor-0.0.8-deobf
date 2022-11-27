package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.util.Bytecode;

public class AccessorGeneratorMethodProxy extends AccessorGenerator {
  private final MethodNode targetMethod;
  
  private final Type[] argTypes;
  
  private final Type returnType;
  
  private final boolean isInstanceMethod;
  
  public AccessorGeneratorMethodProxy(AccessorInfo paramAccessorInfo) {
    super(paramAccessorInfo);
    this.targetMethod = paramAccessorInfo.getTargetMethod();
    this.argTypes = paramAccessorInfo.getArgTypes();
    this.returnType = paramAccessorInfo.getReturnType();
    this.isInstanceMethod = !Bytecode.hasFlag(this.targetMethod, 8);
  }
  
  public MethodNode generate() {
    int i = Bytecode.getArgsSize(this.argTypes) + this.returnType.getSize() + (this.isInstanceMethod ? 1 : 0);
    MethodNode methodNode = createMethod(i, i);
    if (this.isInstanceMethod)
      methodNode.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0)); 
    Bytecode.loadArgs(this.argTypes, methodNode.instructions, this.isInstanceMethod ? 1 : 0);
    boolean bool = Bytecode.hasFlag(this.targetMethod, 2);
    char c = this.isInstanceMethod ? '·' : '¸';
    methodNode.instructions.add((AbstractInsnNode)new MethodInsnNode(c, (this.info.getClassNode()).name, this.targetMethod.name, this.targetMethod.desc, false));
    methodNode.instructions.add((AbstractInsnNode)new InsnNode(this.returnType.getOpcode(172)));
    return methodNode;
  }
}
