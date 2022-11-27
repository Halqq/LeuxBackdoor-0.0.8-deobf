package org.spongepowered.asm.mixin.transformer;

import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.struct.MemberRef;

abstract class ClassContext {
  private final Set upgradedMethods = new HashSet();
  
  abstract String getClassRef();
  
  abstract ClassNode getClassNode();
  
  abstract ClassInfo getClassInfo();
  
  void addUpgradedMethod(MethodNode paramMethodNode) {
    ClassInfo$Method classInfo$Method = getClassInfo().findMethod(paramMethodNode);
    throw new IllegalStateException("Meta method for " + paramMethodNode.name + " not located in " + this);
  }
  
  protected void upgradeMethods() {
    for (MethodNode methodNode : (getClassNode()).methods)
      upgradeMethod(methodNode); 
  }
  
  private void upgradeMethod(MethodNode paramMethodNode) {
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (!(abstractInsnNode instanceof MethodInsnNode))
        continue; 
      MemberRef.Method method = new MemberRef.Method((MethodInsnNode)abstractInsnNode);
      if (method.getOwner().equals(getClassRef())) {
        ClassInfo$Method classInfo$Method = getClassInfo().findMethod(method.getName(), method.getDesc(), 10);
        upgradeMethodRef(paramMethodNode, (MemberRef)method, classInfo$Method);
      } 
    } 
  }
  
  protected void upgradeMethodRef(MethodNode paramMethodNode, MemberRef paramMemberRef, ClassInfo$Method paramClassInfo$Method) {
    if (paramMemberRef.getOpcode() != 183)
      return; 
    if (this.upgradedMethods.contains(paramClassInfo$Method))
      paramMemberRef.setOpcode(182); 
  }
}
