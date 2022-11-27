package org.spongepowered.asm.lib.tree.analysis;

import org.spongepowered.asm.lib.tree.AbstractInsnNode;

public class AnalyzerException extends Exception {
  public final AbstractInsnNode node;
  
  public AnalyzerException(AbstractInsnNode paramAbstractInsnNode, String paramString) {
    super(paramString);
    this.node = paramAbstractInsnNode;
  }
  
  public AnalyzerException(AbstractInsnNode paramAbstractInsnNode, String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
    this.node = paramAbstractInsnNode;
  }
  
  public AnalyzerException(AbstractInsnNode paramAbstractInsnNode, String paramString, Object paramObject, Value paramValue) {}
}
