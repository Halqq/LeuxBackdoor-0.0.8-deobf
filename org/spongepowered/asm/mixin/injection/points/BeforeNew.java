package org.spongepowered.asm.mixin.injection.points;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;

@AtCode("NEW")
public class BeforeNew extends InjectionPoint {
  private final String target;
  
  private final String desc;
  
  private final int ordinal;
  
  public BeforeNew(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
    this.ordinal = paramInjectionPointData.getOrdinal();
    String str = Strings.emptyToNull(paramInjectionPointData.get("class", paramInjectionPointData.get("target", "")).replace('.', '/'));
    MemberInfo memberInfo = MemberInfo.parseAndValidate(str, paramInjectionPointData.getContext());
    this.target = memberInfo.toCtorType();
    this.desc = memberInfo.toCtorDesc();
  }
  
  public boolean hasDescriptor() {
    return (this.desc != null);
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection<TypeInsnNode> paramCollection) {
    boolean bool = false;
    byte b = 0;
    ArrayList arrayList = new ArrayList();
    Collection<AbstractInsnNode> collection = (this.desc != null) ? arrayList : paramCollection;
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof TypeInsnNode && abstractInsnNode.getOpcode() == 187 && matchesOwner((TypeInsnNode)abstractInsnNode)) {
        if (this.ordinal == -1 || this.ordinal == b) {
          collection.add(abstractInsnNode);
          bool = (this.desc == null) ? true : false;
        } 
        b++;
      } 
    } 
    if (this.desc != null)
      for (TypeInsnNode typeInsnNode : arrayList) {
        if (findCtor(paramInsnList, typeInsnNode)) {
          paramCollection.add(typeInsnNode);
          bool = true;
        } 
      }  
    return bool;
  }
  
  protected boolean findCtor(InsnList paramInsnList, TypeInsnNode paramTypeInsnNode) {
    int i = paramInsnList.indexOf((AbstractInsnNode)paramTypeInsnNode);
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator(i);
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof MethodInsnNode && abstractInsnNode.getOpcode() == 183) {
        MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
        if ("<init>".equals(methodInsnNode.name) && methodInsnNode.owner.equals(paramTypeInsnNode.desc) && methodInsnNode.desc.equals(this.desc))
          return true; 
      } 
    } 
    return false;
  }
  
  private boolean matchesOwner(TypeInsnNode paramTypeInsnNode) {
    return (this.target == null || this.target.equals(paramTypeInsnNode.desc));
  }
}
