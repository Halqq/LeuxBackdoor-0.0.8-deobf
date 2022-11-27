package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.analysis.Analyzer;
import org.spongepowered.asm.lib.tree.analysis.BasicVerifier;
import org.spongepowered.asm.lib.tree.analysis.Interpreter;

class CheckMethodAdapter$1 extends MethodNode {
  final MethodVisitor val$cmv;
  
  CheckMethodAdapter$1(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    super(paramInt1, paramInt2, paramString1, paramString2, paramString3, paramArrayOfString);
  }
  
  public void visitEnd() {
    Analyzer analyzer = new Analyzer((Interpreter)new BasicVerifier());
    analyzer.analyze("dummy", this);
    accept(cmv);
  }
}
