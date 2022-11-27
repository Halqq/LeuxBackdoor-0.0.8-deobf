package org.spongepowered.asm.util;

import java.util.HashSet;
import java.util.Set;
import org.spongepowered.asm.lib.signature.SignatureWriter;

class ClassSignature$SignatureRemapper extends SignatureWriter {
  private final Set localTypeVars = new HashSet();
  
  final ClassSignature this$0;
  
  public void visitFormalTypeParameter(String paramString) {
    this.localTypeVars.add(paramString);
    super.visitFormalTypeParameter(paramString);
  }
  
  public void visitTypeVariable(String paramString) {
    if (!this.localTypeVars.contains(paramString)) {
      ClassSignature$TypeVar classSignature$TypeVar = ClassSignature.this.getTypeVar(paramString);
      super.visitTypeVariable(classSignature$TypeVar.toString());
      return;
    } 
    super.visitTypeVariable(paramString);
  }
}
