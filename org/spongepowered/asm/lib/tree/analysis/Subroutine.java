package org.spongepowered.asm.lib.tree.analysis;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;

class Subroutine {
  LabelNode start;
  
  boolean[] access;
  
  List callers;
  
  private Subroutine() {}
  
  Subroutine(LabelNode paramLabelNode, int paramInt, JumpInsnNode paramJumpInsnNode) {
    this.start = paramLabelNode;
    this.access = new boolean[paramInt];
    this.callers = new ArrayList();
    this.callers.add(paramJumpInsnNode);
  }
  
  public Subroutine copy() {
    Subroutine subroutine = new Subroutine();
    subroutine.start = this.start;
    subroutine.access = new boolean[this.access.length];
    System.arraycopy(this.access, 0, subroutine.access, 0, this.access.length);
    subroutine.callers = new ArrayList(this.callers);
    return subroutine;
  }
  
  public boolean merge(Subroutine paramSubroutine) throws AnalyzerException {
    boolean bool = false;
    byte b;
    for (b = 0; b < this.access.length; b++) {
      if (paramSubroutine.access[b] && !this.access[b]) {
        this.access[b] = true;
        bool = true;
      } 
    } 
    if (paramSubroutine.start == this.start)
      for (b = 0; b < paramSubroutine.callers.size(); b++) {
        JumpInsnNode jumpInsnNode = paramSubroutine.callers.get(b);
        if (!this.callers.contains(jumpInsnNode)) {
          this.callers.add(jumpInsnNode);
          bool = true;
        } 
      }  
    return bool;
  }
}
