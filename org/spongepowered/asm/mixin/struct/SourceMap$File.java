package org.spongepowered.asm.mixin.struct;

import java.util.ListIterator;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.MethodNode;

public class SourceMap$File {
  public final int id;
  
  public final int lineOffset;
  
  public final int size;
  
  public final String sourceFileName;
  
  public final String sourceFilePath;
  
  public SourceMap$File(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    this(paramInt1, paramInt2, paramInt3, paramString, null);
  }
  
  public SourceMap$File(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2) {
    this.id = paramInt1;
    this.lineOffset = paramInt2;
    this.size = paramInt3;
    this.sourceFileName = paramString1;
    this.sourceFilePath = paramString2;
  }
  
  public void applyOffset(ClassNode paramClassNode) {
    for (MethodNode methodNode : paramClassNode.methods)
      applyOffset(methodNode); 
  }
  
  public void applyOffset(MethodNode paramMethodNode) {
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof LineNumberNode)
        ((LineNumberNode)abstractInsnNode).line += this.lineOffset - 1; 
    } 
  }
  
  void appendFile(StringBuilder paramStringBuilder) {
    if (this.sourceFilePath != null) {
      paramStringBuilder.append("+ ").append(this.id).append(" ").append(this.sourceFileName).append("\n");
      paramStringBuilder.append(this.sourceFilePath).append("\n");
    } else {
      paramStringBuilder.append(this.id).append(" ").append(this.sourceFileName).append("\n");
    } 
  }
  
  public void appendLines(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("1#").append(this.id).append(",").append(this.size).append(":").append(this.lineOffset).append("\n");
  }
}
