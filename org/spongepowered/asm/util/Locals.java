package org.spongepowered.asm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.tree.analysis.Analyzer;
import org.spongepowered.asm.lib.tree.analysis.BasicValue;
import org.spongepowered.asm.lib.tree.analysis.Frame;
import org.spongepowered.asm.lib.tree.analysis.Interpreter;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.util.asm.MixinVerifier;
import org.spongepowered.asm.util.throwables.LVTGeneratorException;

public final class Locals {
  private static final Map calculatedLocalVariables = new HashMap<Object, Object>();
  
  public static void loadLocals(Type[] paramArrayOfType, InsnList paramInsnList, int paramInt1, int paramInt2) {
    while (paramInt1 < paramArrayOfType.length) {
      if (paramArrayOfType[paramInt1] != null) {
        paramInsnList.add((AbstractInsnNode)new VarInsnNode(paramArrayOfType[paramInt1].getOpcode(21), paramInt1));
        paramInt2--;
      } 
      paramInt1++;
    } 
  }
  
  public static LocalVariableNode[] getLocalsAt(ClassNode paramClassNode, MethodNode paramMethodNode, AbstractInsnNode paramAbstractInsnNode) {
    byte b = 0;
    while (true) {
      3;
      if (paramAbstractInsnNode instanceof LabelNode || paramAbstractInsnNode instanceof org.spongepowered.asm.lib.tree.LineNumberNode) {
        paramAbstractInsnNode = nextNode(paramMethodNode.instructions, paramAbstractInsnNode);
        continue;
      } 
      ClassInfo classInfo = ClassInfo.forName(paramClassNode.name);
      throw new LVTGeneratorException("Could not load class metadata for " + paramClassNode.name + " generating LVT for " + paramMethodNode.name);
    } 
  }
  
  public static LocalVariableNode getLocalVariableAt(ClassNode paramClassNode, MethodNode paramMethodNode, AbstractInsnNode paramAbstractInsnNode, int paramInt) {
    return getLocalVariableAt(paramClassNode, paramMethodNode, paramMethodNode.instructions.indexOf(paramAbstractInsnNode), paramInt);
  }
  
  private static LocalVariableNode getLocalVariableAt(ClassNode paramClassNode, MethodNode paramMethodNode, int paramInt1, int paramInt2) {
    LocalVariableNode localVariableNode1 = null;
    LocalVariableNode localVariableNode2 = null;
    for (LocalVariableNode localVariableNode : getLocalVariableTable(paramClassNode, paramMethodNode)) {
      if (localVariableNode.index != paramInt2)
        continue; 
      if (isOpcodeInRange(paramMethodNode.instructions, localVariableNode, paramInt1)) {
        localVariableNode1 = localVariableNode;
        continue;
      } 
      localVariableNode2 = localVariableNode;
    } 
    if (!paramMethodNode.localVariables.isEmpty())
      for (LocalVariableNode localVariableNode : getGeneratedLocalVariableTable(paramClassNode, paramMethodNode)) {
        if (localVariableNode.index == paramInt2 && isOpcodeInRange(paramMethodNode.instructions, localVariableNode, paramInt1))
          localVariableNode1 = localVariableNode; 
      }  
  }
  
  private static boolean isOpcodeInRange(InsnList paramInsnList, LocalVariableNode paramLocalVariableNode, int paramInt) {
    return (paramInsnList.indexOf((AbstractInsnNode)paramLocalVariableNode.start) < paramInt && paramInsnList.indexOf((AbstractInsnNode)paramLocalVariableNode.end) > paramInt);
  }
  
  public static List getLocalVariableTable(ClassNode paramClassNode, MethodNode paramMethodNode) {
    return paramMethodNode.localVariables.isEmpty() ? getGeneratedLocalVariableTable(paramClassNode, paramMethodNode) : paramMethodNode.localVariables;
  }
  
  public static List getGeneratedLocalVariableTable(ClassNode paramClassNode, MethodNode paramMethodNode) {
    String str = String.format("%s.%s%s", new Object[] { paramClassNode.name, paramMethodNode.name, paramMethodNode.desc });
    return (List)calculatedLocalVariables.get(str);
  }
  
  public static List generateLocalVariableTable(ClassNode paramClassNode, MethodNode paramMethodNode) {
    ArrayList<Type> arrayList = null;
    if (paramClassNode.interfaces != null) {
      arrayList = new ArrayList();
      for (String str : paramClassNode.interfaces)
        arrayList.add(Type.getObjectType(str)); 
    } 
    Type type = null;
    if (paramClassNode.superName != null)
      type = Type.getObjectType(paramClassNode.superName); 
    Analyzer analyzer = new Analyzer((Interpreter)new MixinVerifier(Type.getObjectType(paramClassNode.name), type, arrayList, false));
    analyzer.analyze(paramClassNode.name, paramMethodNode);
    Frame[] arrayOfFrame = analyzer.getFrames();
    int i = paramMethodNode.instructions.size();
    ArrayList<LocalVariableNode> arrayList1 = new ArrayList();
    LocalVariableNode[] arrayOfLocalVariableNode = new LocalVariableNode[paramMethodNode.maxLocals];
    BasicValue[] arrayOfBasicValue = new BasicValue[paramMethodNode.maxLocals];
    LabelNode[] arrayOfLabelNode = new LabelNode[i];
    String[] arrayOfString = new String[paramMethodNode.maxLocals];
    for (byte b = 0; b < i; b++)
      Frame frame = arrayOfFrame[b]; 
    LabelNode labelNode = null;
    int j;
    for (j = 0; j < arrayOfLocalVariableNode.length; j++) {
      if (arrayOfLocalVariableNode[j] != null) {
        labelNode = new LabelNode();
        paramMethodNode.instructions.add((AbstractInsnNode)labelNode);
        (arrayOfLocalVariableNode[j]).end = labelNode;
        arrayList1.add(arrayOfLocalVariableNode[j]);
      } 
    } 
    for (j = i - 1;; j--) {
      if (arrayOfLabelNode[j] != null)
        paramMethodNode.instructions.insert(paramMethodNode.instructions.get(j), (AbstractInsnNode)arrayOfLabelNode[j]); 
    } 
  }
  
  private static AbstractInsnNode nextNode(InsnList paramInsnList, AbstractInsnNode paramAbstractInsnNode) {
    int i = paramInsnList.indexOf(paramAbstractInsnNode) + 1;
    return (i < paramInsnList.size()) ? paramInsnList.get(i) : paramAbstractInsnNode;
  }
}
