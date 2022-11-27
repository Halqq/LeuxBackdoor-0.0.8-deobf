package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.Target;

@AtCode("LOAD")
public class BeforeLoadLocal extends ModifyVariableInjector$ContextualInjectionPoint {
  private final Type returnType;
  
  private final LocalVariableDiscriminator discriminator;
  
  private final int opcode;
  
  private final int ordinal;
  
  private boolean opcodeAfter;
  
  protected BeforeLoadLocal(InjectionPointData paramInjectionPointData) {
    this(paramInjectionPointData, 21, false);
  }
  
  protected BeforeLoadLocal(InjectionPointData paramInjectionPointData, int paramInt, boolean paramBoolean) {
    super(paramInjectionPointData.getContext());
    this.returnType = paramInjectionPointData.getMethodReturnType();
    this.discriminator = paramInjectionPointData.getLocalVariableDiscriminator();
    this.opcode = paramInjectionPointData.getOpcode(this.returnType.getOpcode(paramInt));
    this.ordinal = paramInjectionPointData.getOrdinal();
    this.opcodeAfter = paramBoolean;
  }
  
  boolean find(Target paramTarget, Collection paramCollection) {
    BeforeLoadLocal$SearchState beforeLoadLocal$SearchState = new BeforeLoadLocal$SearchState(this.ordinal, this.discriminator.printLVT());
    ListIterator<AbstractInsnNode> listIterator = paramTarget.method.instructions.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (beforeLoadLocal$SearchState.isPendingCheck()) {
        int i = this.discriminator.findLocal(this.returnType, this.discriminator.isArgsOnly(), paramTarget, abstractInsnNode);
        beforeLoadLocal$SearchState.check(paramCollection, abstractInsnNode, i);
        continue;
      } 
      if (abstractInsnNode instanceof VarInsnNode && abstractInsnNode.getOpcode() == this.opcode && (this.ordinal == -1 || !beforeLoadLocal$SearchState.success())) {
        beforeLoadLocal$SearchState.register((VarInsnNode)abstractInsnNode);
        if (this.opcodeAfter) {
          beforeLoadLocal$SearchState.setPendingCheck();
          continue;
        } 
        int i = this.discriminator.findLocal(this.returnType, this.discriminator.isArgsOnly(), paramTarget, abstractInsnNode);
        beforeLoadLocal$SearchState.check(paramCollection, abstractInsnNode, i);
      } 
    } 
    return beforeLoadLocal$SearchState.success();
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection paramCollection) {
    return super.find(paramString, paramInsnList, paramCollection);
  }
}
