package org.spongepowered.asm.mixin.injection.modify;

import java.util.HashMap;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.Locals;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class LocalVariableDiscriminator$Context implements PrettyPrinter.IPrettyPrintable {
  final Target target;
  
  final Type returnType;
  
  final AbstractInsnNode node;
  
  final int baseArgIndex;
  
  final LocalVariableDiscriminator$Context$Local[] locals;
  
  private final boolean isStatic;
  
  public LocalVariableDiscriminator$Context(Type paramType, boolean paramBoolean, Target paramTarget, AbstractInsnNode paramAbstractInsnNode) {
    this.isStatic = Bytecode.methodIsStatic(paramTarget.method);
    this.returnType = paramType;
    this.target = paramTarget;
    this.node = paramAbstractInsnNode;
    this.baseArgIndex = this.isStatic ? 0 : 1;
    this.locals = initLocals(paramTarget, paramBoolean, paramAbstractInsnNode);
    initOrdinals();
  }
  
  private LocalVariableDiscriminator$Context$Local[] initLocals(Target paramTarget, boolean paramBoolean, AbstractInsnNode paramAbstractInsnNode) {
    LocalVariableNode[] arrayOfLocalVariableNode = Locals.getLocalsAt(paramTarget.classNode, paramTarget.method, paramAbstractInsnNode);
    LocalVariableDiscriminator$Context$Local[] arrayOfLocalVariableDiscriminator$Context$Local = new LocalVariableDiscriminator$Context$Local[arrayOfLocalVariableNode.length];
    for (byte b = 0; b < arrayOfLocalVariableNode.length; b++) {
      if (arrayOfLocalVariableNode[b] != null)
        arrayOfLocalVariableDiscriminator$Context$Local[b] = new LocalVariableDiscriminator$Context$Local(this, (arrayOfLocalVariableNode[b]).name, Type.getType((arrayOfLocalVariableNode[b]).desc)); 
    } 
    return arrayOfLocalVariableDiscriminator$Context$Local;
  }
  
  private void initOrdinals() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (byte b = 0; b < this.locals.length; b++) {
      Integer integer = Integer.valueOf(0);
      if (this.locals[b] != null)
        integer = (Integer)hashMap.get((this.locals[b]).type); 
    } 
  }
  
  public void print(PrettyPrinter paramPrettyPrinter) {
    paramPrettyPrinter.add("%5s  %7s  %30s  %-50s  %s", new Object[] { "INDEX", "ORDINAL", "TYPE", "NAME", "CANDIDATE" });
    for (int i = this.baseArgIndex; i < this.locals.length; i++) {
      LocalVariableDiscriminator$Context$Local localVariableDiscriminator$Context$Local = this.locals[i];
      Type type = localVariableDiscriminator$Context$Local.type;
      String str1 = localVariableDiscriminator$Context$Local.name;
      int j = localVariableDiscriminator$Context$Local.ord;
      String str2 = this.returnType.equals(type) ? "YES" : "-";
      paramPrettyPrinter.add("[%3d]    [%3d]  %30s  %-50s  %s", new Object[] { Integer.valueOf(i), Integer.valueOf(j), SignaturePrinter.getTypeName(type, false), str1, str2 });
    } 
  }
}
