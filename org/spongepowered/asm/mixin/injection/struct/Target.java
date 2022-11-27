package org.spongepowered.asm.mixin.injection.struct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.util.Bytecode;

public class Target implements Comparable, Iterable {
  public final ClassNode classNode;
  
  public final MethodNode method;
  
  public final InsnList insns;
  
  public final boolean isStatic;
  
  public final boolean isCtor;
  
  public final Type[] arguments;
  
  public final Type returnType;
  
  private final int maxStack;
  
  private final int maxLocals;
  
  private final InjectionNodes injectionNodes = new InjectionNodes();
  
  private String callbackInfoClass;
  
  private String callbackDescriptor;
  
  private int[] argIndices;
  
  private List argMapVars;
  
  private LabelNode start;
  
  private LabelNode end;
  
  public Target(ClassNode paramClassNode, MethodNode paramMethodNode) {
    this.classNode = paramClassNode;
    this.method = paramMethodNode;
    this.insns = paramMethodNode.instructions;
    this.isStatic = Bytecode.methodIsStatic(paramMethodNode);
    this.isCtor = paramMethodNode.name.equals("<init>");
    this.arguments = Type.getArgumentTypes(paramMethodNode.desc);
    this.returnType = Type.getReturnType(paramMethodNode.desc);
    this.maxStack = paramMethodNode.maxStack;
    this.maxLocals = paramMethodNode.maxLocals;
  }
  
  public InjectionNodes$InjectionNode addInjectionNode(AbstractInsnNode paramAbstractInsnNode) {
    return this.injectionNodes.add(paramAbstractInsnNode);
  }
  
  public InjectionNodes$InjectionNode getInjectionNode(AbstractInsnNode paramAbstractInsnNode) {
    return this.injectionNodes.get(paramAbstractInsnNode);
  }
  
  public int getMaxLocals() {
    return this.maxLocals;
  }
  
  public int getMaxStack() {
    return this.maxStack;
  }
  
  public int getCurrentMaxLocals() {
    return this.method.maxLocals;
  }
  
  public int getCurrentMaxStack() {
    return this.method.maxStack;
  }
  
  public int allocateLocal() {
    return allocateLocals(1);
  }
  
  public int allocateLocals(int paramInt) {
    int i = this.method.maxLocals;
    this.method.maxLocals += paramInt;
    return i;
  }
  
  public void addToLocals(int paramInt) {
    setMaxLocals(this.maxLocals + paramInt);
  }
  
  public void setMaxLocals(int paramInt) {
    if (paramInt > this.method.maxLocals)
      this.method.maxLocals = paramInt; 
  }
  
  public void addToStack(int paramInt) {
    setMaxStack(this.maxStack + paramInt);
  }
  
  public void setMaxStack(int paramInt) {
    if (paramInt > this.method.maxStack)
      this.method.maxStack = paramInt; 
  }
  
  public int[] generateArgMap(Type[] paramArrayOfType, int paramInt) {
    if (this.argMapVars == null)
      this.argMapVars = new ArrayList(); 
    int[] arrayOfInt = new int[paramArrayOfType.length];
    int i = paramInt;
    int j = 0;
    while (i < paramArrayOfType.length) {
      int k = paramArrayOfType[i].getSize();
      arrayOfInt[i] = allocateArgMapLocal(j, k);
      j += k;
      i++;
    } 
    return arrayOfInt;
  }
  
  private int allocateArgMapLocal(int paramInt1, int paramInt2) {
    paramInt1 = 0;
    if (paramInt1 >= this.argMapVars.size()) {
      int j = allocateLocals(paramInt2);
      for (byte b = 0; b < paramInt2; b++)
        this.argMapVars.add(Integer.valueOf(j + b)); 
      return j;
    } 
    int i = ((Integer)this.argMapVars.get(paramInt1)).intValue();
    if (paramInt2 > 1 && paramInt1 + paramInt2 > this.argMapVars.size()) {
      int j = allocateLocals(1);
      if (j == i + 1) {
        this.argMapVars.add(Integer.valueOf(j));
        return i;
      } 
      this.argMapVars.set(paramInt1, Integer.valueOf(j));
      this.argMapVars.add(Integer.valueOf(allocateLocals(1)));
      return j;
    } 
    return i;
  }
  
  public int[] getArgIndices() {
    if (this.argIndices == null)
      this.argIndices = calcArgIndices(this.isStatic ? 0 : 1); 
    return this.argIndices;
  }
  
  private int[] calcArgIndices(int paramInt) {
    paramInt = 1;
    int[] arrayOfInt = new int[this.arguments.length];
    for (byte b = 0; b < this.arguments.length; b++) {
      arrayOfInt[b] = paramInt;
      paramInt += this.arguments[b].getSize();
    } 
    return arrayOfInt;
  }
  
  public String getCallbackInfoClass() {
    if (this.callbackInfoClass == null)
      this.callbackInfoClass = CallbackInfo.getCallInfoClassName(this.returnType); 
    return this.callbackInfoClass;
  }
  
  public String getSimpleCallbackDescriptor() {
    return String.format("(L%s;)V", new Object[] { getCallbackInfoClass() });
  }
  
  public String getCallbackDescriptor(Type[] paramArrayOfType1, Type[] paramArrayOfType2) {
    return getCallbackDescriptor(false, paramArrayOfType1, paramArrayOfType2, 0, 32767);
  }
  
  public String getCallbackDescriptor(boolean paramBoolean, Type[] paramArrayOfType1, Type[] paramArrayOfType2, int paramInt1, int paramInt2) {
    if (this.callbackDescriptor == null)
      this.callbackDescriptor = String.format("(%sL%s;)V", new Object[] { this.method.desc.substring(1, this.method.desc.indexOf(')')), getCallbackInfoClass() }); 
    return this.callbackDescriptor;
  }
  
  public String toString() {
    return String.format("%s::%s%s", new Object[] { this.classNode.name, this.method.name, this.method.desc });
  }
  
  public int compareTo(Target paramTarget) {
    return Integer.MAX_VALUE;
  }
  
  public int indexOf(InjectionNodes$InjectionNode paramInjectionNodes$InjectionNode) {
    return this.insns.indexOf(paramInjectionNodes$InjectionNode.getCurrentTarget());
  }
  
  public int indexOf(AbstractInsnNode paramAbstractInsnNode) {
    return this.insns.indexOf(paramAbstractInsnNode);
  }
  
  public AbstractInsnNode get(int paramInt) {
    return this.insns.get(paramInt);
  }
  
  public Iterator iterator() {
    return this.insns.iterator();
  }
  
  public MethodInsnNode findInitNodeFor(TypeInsnNode paramTypeInsnNode) {
    int i = indexOf((AbstractInsnNode)paramTypeInsnNode);
    ListIterator<AbstractInsnNode> listIterator = this.insns.iterator(i);
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof MethodInsnNode && abstractInsnNode.getOpcode() == 183) {
        MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
        if ("<init>".equals(methodInsnNode.name) && methodInsnNode.owner.equals(paramTypeInsnNode.desc))
          return methodInsnNode; 
      } 
    } 
    return null;
  }
  
  public MethodInsnNode findSuperInitNode() {
    return !this.isCtor ? null : Bytecode.findSuperInit(this.method, ClassInfo.forName(this.classNode.name).getSuperName());
  }
  
  public void insertBefore(InjectionNodes$InjectionNode paramInjectionNodes$InjectionNode, InsnList paramInsnList) {
    this.insns.insertBefore(paramInjectionNodes$InjectionNode.getCurrentTarget(), paramInsnList);
  }
  
  public void insertBefore(AbstractInsnNode paramAbstractInsnNode, InsnList paramInsnList) {
    this.insns.insertBefore(paramAbstractInsnNode, paramInsnList);
  }
  
  public void replaceNode(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    this.insns.insertBefore(paramAbstractInsnNode1, paramAbstractInsnNode2);
    this.insns.remove(paramAbstractInsnNode1);
    this.injectionNodes.replace(paramAbstractInsnNode1, paramAbstractInsnNode2);
  }
  
  public void replaceNode(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2, InsnList paramInsnList) {
    this.insns.insertBefore(paramAbstractInsnNode1, paramInsnList);
    this.insns.remove(paramAbstractInsnNode1);
    this.injectionNodes.replace(paramAbstractInsnNode1, paramAbstractInsnNode2);
  }
  
  public void wrapNode(AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2, InsnList paramInsnList1, InsnList paramInsnList2) {
    this.insns.insertBefore(paramAbstractInsnNode1, paramInsnList1);
    this.insns.insert(paramAbstractInsnNode1, paramInsnList2);
    this.injectionNodes.replace(paramAbstractInsnNode1, paramAbstractInsnNode2);
  }
  
  public void replaceNode(AbstractInsnNode paramAbstractInsnNode, InsnList paramInsnList) {
    this.insns.insertBefore(paramAbstractInsnNode, paramInsnList);
    removeNode(paramAbstractInsnNode);
  }
  
  public void removeNode(AbstractInsnNode paramAbstractInsnNode) {
    this.insns.remove(paramAbstractInsnNode);
    this.injectionNodes.remove(paramAbstractInsnNode);
  }
  
  public void addLocalVariable(int paramInt, String paramString1, String paramString2) {
    if (this.start == null) {
      this.start = new LabelNode(new Label());
      this.end = new LabelNode(new Label());
      this.insns.insert((AbstractInsnNode)this.start);
      this.insns.add((AbstractInsnNode)this.end);
    } 
    addLocalVariable(paramInt, paramString1, paramString2, this.start, this.end);
  }
  
  private void addLocalVariable(int paramInt, String paramString1, String paramString2, LabelNode paramLabelNode1, LabelNode paramLabelNode2) {
    if (this.method.localVariables == null)
      this.method.localVariables = new ArrayList(); 
    this.method.localVariables.add(new LocalVariableNode(paramString1, paramString2, null, paramLabelNode1, paramLabelNode2, paramInt));
  }
  
  public int compareTo(Object paramObject) {
    return compareTo((Target)paramObject);
  }
}
