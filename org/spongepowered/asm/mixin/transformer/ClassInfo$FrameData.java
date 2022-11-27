package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.FrameNode;

public class ClassInfo$FrameData {
  private static final String[] FRAMETYPES = new String[] { "NEW", "FULL", "APPEND", "CHOP", "SAME", "SAME1" };
  
  public final int index;
  
  public final int type;
  
  public final int locals;
  
  ClassInfo$FrameData(int paramInt1, int paramInt2, int paramInt3) {
    this.index = paramInt1;
    this.type = paramInt2;
    this.locals = paramInt3;
  }
  
  ClassInfo$FrameData(int paramInt, FrameNode paramFrameNode) {
    this.index = paramInt;
    this.type = paramFrameNode.type;
    this.locals = (paramFrameNode.local != null) ? paramFrameNode.local.size() : 0;
  }
  
  public String toString() {
    return String.format("FrameData[index=%d, type=%s, locals=%d]", new Object[] { Integer.valueOf(this.index), FRAMETYPES[this.type + 1], Integer.valueOf(this.locals) });
  }
}
