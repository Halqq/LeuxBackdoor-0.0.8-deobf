package org.spongepowered.asm.mixin.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FrameNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.util.Annotations;

public class ClassInfo$Method extends ClassInfo$Member {
  private final List frames;
  
  private boolean isAccessor;
  
  final ClassInfo this$0;
  
  public ClassInfo$Method(ClassInfo$Member paramClassInfo$Member) {
    super(paramClassInfo$Member);
    this.frames = (paramClassInfo$Member instanceof ClassInfo$Method) ? ((ClassInfo$Method)paramClassInfo$Member).frames : null;
  }
  
  public ClassInfo$Method(MethodNode paramMethodNode) {
    this(paramMethodNode, false);
    setUnique((Annotations.getVisible(paramMethodNode, Unique.class) != null));
    this.isAccessor = (Annotations.getSingleVisible(paramMethodNode, new Class[] { Accessor.class, Invoker.class }) != null);
  }
  
  public ClassInfo$Method(MethodNode paramMethodNode, boolean paramBoolean) {
    super(ClassInfo$Member$Type.METHOD, paramMethodNode.name, paramMethodNode.desc, paramMethodNode.access, paramBoolean);
    this.frames = gatherFrames(paramMethodNode);
    setUnique((Annotations.getVisible(paramMethodNode, Unique.class) != null));
    this.isAccessor = (Annotations.getSingleVisible(paramMethodNode, new Class[] { Accessor.class, Invoker.class }) != null);
  }
  
  public ClassInfo$Method(String paramString1, String paramString2) {
    super(ClassInfo$Member$Type.METHOD, paramString1, paramString2, 1, false);
    this.frames = null;
  }
  
  public ClassInfo$Method(String paramString1, String paramString2, int paramInt) {
    super(ClassInfo$Member$Type.METHOD, paramString1, paramString2, paramInt, false);
    this.frames = null;
  }
  
  public ClassInfo$Method(String paramString1, String paramString2, int paramInt, boolean paramBoolean) {
    super(ClassInfo$Member$Type.METHOD, paramString1, paramString2, paramInt, paramBoolean);
    this.frames = null;
  }
  
  private List gatherFrames(MethodNode paramMethodNode) {
    ArrayList<ClassInfo$FrameData> arrayList = new ArrayList();
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof FrameNode)
        arrayList.add(new ClassInfo$FrameData(paramMethodNode.instructions.indexOf(abstractInsnNode), (FrameNode)abstractInsnNode)); 
    } 
    return arrayList;
  }
  
  public List getFrames() {
    return this.frames;
  }
  
  public ClassInfo getOwner() {
    return ClassInfo.this;
  }
  
  public boolean isAccessor() {
    return this.isAccessor;
  }
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof ClassInfo$Method) ? false : super.equals(paramObject);
  }
  
  public String toString() {
    return super.toString();
  }
  
  public int hashCode() {
    return super.hashCode();
  }
  
  public boolean equals(String paramString1, String paramString2) {
    return super.equals(paramString1, paramString2);
  }
  
  public String remapTo(String paramString) {
    return super.remapTo(paramString);
  }
  
  public String renameTo(String paramString) {
    return super.renameTo(paramString);
  }
  
  public int getAccess() {
    return super.getAccess();
  }
  
  public ClassInfo getImplementor() {
    return super.getImplementor();
  }
  
  public boolean matchesFlags(int paramInt) {
    return super.matchesFlags(paramInt);
  }
  
  public void setDecoratedFinal(boolean paramBoolean1, boolean paramBoolean2) {
    super.setDecoratedFinal(paramBoolean1, paramBoolean2);
  }
  
  public boolean isDecoratedMutable() {
    return super.isDecoratedMutable();
  }
  
  public boolean isDecoratedFinal() {
    return super.isDecoratedFinal();
  }
  
  public void setUnique(boolean paramBoolean) {
    super.setUnique(paramBoolean);
  }
  
  public boolean isUnique() {
    return super.isUnique();
  }
  
  public boolean isSynthetic() {
    return super.isSynthetic();
  }
  
  public boolean isFinal() {
    return super.isFinal();
  }
  
  public boolean isAbstract() {
    return super.isAbstract();
  }
  
  public boolean isStatic() {
    return super.isStatic();
  }
  
  public boolean isPrivate() {
    return super.isPrivate();
  }
  
  public boolean isRemapped() {
    return super.isRemapped();
  }
  
  public boolean isRenamed() {
    return super.isRenamed();
  }
  
  public boolean isInjected() {
    return super.isInjected();
  }
  
  public String getDesc() {
    return super.getDesc();
  }
  
  public String getOriginalDesc() {
    return super.getOriginalDesc();
  }
  
  public String getName() {
    return super.getName();
  }
  
  public String getOriginalName() {
    return super.getOriginalName();
  }
}
