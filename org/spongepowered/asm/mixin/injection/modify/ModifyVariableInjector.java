package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collection;
import java.util.List;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class ModifyVariableInjector extends Injector {
  private final LocalVariableDiscriminator discriminator;
  
  public ModifyVariableInjector(InjectionInfo paramInjectionInfo, LocalVariableDiscriminator paramLocalVariableDiscriminator) {
    super(paramInjectionInfo);
    this.discriminator = paramLocalVariableDiscriminator;
  }
  
  protected boolean findTargetNodes(MethodNode paramMethodNode, InjectionPoint paramInjectionPoint, InsnList paramInsnList, Collection paramCollection) {
    if (paramInjectionPoint instanceof ModifyVariableInjector$ContextualInjectionPoint) {
      Target target = this.info.getContext().getTargetMethod(paramMethodNode);
      return ((ModifyVariableInjector$ContextualInjectionPoint)paramInjectionPoint).find(target, paramCollection);
    } 
    return paramInjectionPoint.find(paramMethodNode.desc, paramInsnList, paramCollection);
  }
  
  protected void sanityCheck(Target paramTarget, List paramList) {
    super.sanityCheck(paramTarget, paramList);
    if (paramTarget.isStatic != this.isStatic)
      throw new InvalidInjectionException(this.info, "'static' of variable modifier method does not match target in " + this); 
    int i = this.discriminator.getOrdinal();
    if (i < -1)
      throw new InvalidInjectionException(this.info, "Invalid ordinal " + i + " specified in " + this); 
    if (this.discriminator.getIndex() == 0 && !this.isStatic)
      throw new InvalidInjectionException(this.info, "Invalid index 0 specified in non-static variable modifier " + this); 
  }
  
  protected void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    if (paramInjectionNode.isReplaced())
      throw new InvalidInjectionException(this.info, "Variable modifier target for " + this + " was removed by another injector"); 
    ModifyVariableInjector$Context modifyVariableInjector$Context = new ModifyVariableInjector$Context(this.returnType, this.discriminator.isArgsOnly(), paramTarget, paramInjectionNode.getCurrentTarget());
    if (this.discriminator.printLVT())
      printLocals(modifyVariableInjector$Context); 
    int i = this.discriminator.findLocal(modifyVariableInjector$Context);
    if (i > -1)
      inject(modifyVariableInjector$Context, i); 
    paramTarget.insns.insertBefore(modifyVariableInjector$Context.node, modifyVariableInjector$Context.insns);
    paramTarget.addToStack(this.isStatic ? 1 : 2);
  }
  
  private void printLocals(ModifyVariableInjector$Context paramModifyVariableInjector$Context) {
    SignaturePrinter signaturePrinter = new SignaturePrinter(this.methodNode.name, this.returnType, this.methodArgs, new String[] { "var" });
    signaturePrinter.setModifiers(this.methodNode);
    (new PrettyPrinter()).kvWidth(20).kv("Target Class", this.classNode.name.replace('/', '.')).kv("Target Method", paramModifyVariableInjector$Context.target.method.name).kv("Callback Name", this.methodNode.name).kv("Capture Type", SignaturePrinter.getTypeName(this.returnType, false)).kv("Instruction", "%s %s", new Object[] { paramModifyVariableInjector$Context.node.getClass().getSimpleName(), Bytecode.getOpcodeName(paramModifyVariableInjector$Context.node.getOpcode()) }).hr().kv("Match mode", this.discriminator.isImplicit(paramModifyVariableInjector$Context) ? "IMPLICIT (match single)" : "EXPLICIT (match by criteria)").kv("Match ordinal", (this.discriminator.getOrdinal() < 0) ? "any" : Integer.valueOf(this.discriminator.getOrdinal())).kv("Match index", (this.discriminator.getIndex() < paramModifyVariableInjector$Context.baseArgIndex) ? "any" : Integer.valueOf(this.discriminator.getIndex())).kv("Match name(s)", this.discriminator.hasNames() ? this.discriminator.getNames() : "any").kv("Args only", Boolean.valueOf(this.discriminator.isArgsOnly())).hr().add(paramModifyVariableInjector$Context).print(System.err);
  }
  
  private void inject(ModifyVariableInjector$Context paramModifyVariableInjector$Context, int paramInt) {
    if (!this.isStatic)
      paramModifyVariableInjector$Context.insns.add((AbstractInsnNode)new VarInsnNode(25, 0)); 
    paramModifyVariableInjector$Context.insns.add((AbstractInsnNode)new VarInsnNode(this.returnType.getOpcode(21), paramInt));
    invokeHandler(paramModifyVariableInjector$Context.insns);
    paramModifyVariableInjector$Context.insns.add((AbstractInsnNode)new VarInsnNode(this.returnType.getOpcode(54), paramInt));
  }
}
