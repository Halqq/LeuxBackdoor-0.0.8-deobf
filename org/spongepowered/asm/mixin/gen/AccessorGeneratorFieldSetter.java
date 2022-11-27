package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;

public class AccessorGeneratorFieldSetter extends AccessorGeneratorField {
  public AccessorGeneratorFieldSetter(AccessorInfo paramAccessorInfo) {
    super(paramAccessorInfo);
  }
  
  public MethodNode generate() {
    byte b = this.isInstanceField ? 1 : 0;
    int i = b + this.targetType.getSize();
    int j = b + this.targetType.getSize();
    MethodNode methodNode = createMethod(i, j);
    if (this.isInstanceField)
      methodNode.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0)); 
    methodNode.instructions.add((AbstractInsnNode)new VarInsnNode(this.targetType.getOpcode(21), b));
    char c = this.isInstanceField ? 'µ' : '³';
    methodNode.instructions.add((AbstractInsnNode)new FieldInsnNode(c, (this.info.getClassNode()).name, this.targetField.name, this.targetField.desc));
    methodNode.instructions.add((AbstractInsnNode)new InsnNode(177));
    return methodNode;
  }
}
