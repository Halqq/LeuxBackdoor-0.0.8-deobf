package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;

public class AccessorGeneratorFieldGetter extends AccessorGeneratorField {
  public AccessorGeneratorFieldGetter(AccessorInfo paramAccessorInfo) {
    super(paramAccessorInfo);
  }
  
  public MethodNode generate() {
    MethodNode methodNode = createMethod(this.targetType.getSize(), this.targetType.getSize());
    if (this.isInstanceField)
      methodNode.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0)); 
    char c = this.isInstanceField ? '´' : '²';
    methodNode.instructions.add((AbstractInsnNode)new FieldInsnNode(c, (this.info.getClassNode()).name, this.targetField.name, this.targetField.desc));
    methodNode.instructions.add((AbstractInsnNode)new InsnNode(this.targetType.getOpcode(172)));
    return methodNode;
  }
}
