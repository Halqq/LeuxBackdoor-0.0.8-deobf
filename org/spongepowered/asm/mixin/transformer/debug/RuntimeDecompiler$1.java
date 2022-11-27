package org.spongepowered.asm.mixin.transformer.debug;

import java.io.File;
import java.io.IOException;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;
import org.jetbrains.java.decompiler.util.InterpreterUtil;

class RuntimeDecompiler$1 implements IBytecodeProvider {
  private byte[] byteCode;
  
  final RuntimeDecompiler this$0;
  
  public byte[] getBytecode(String paramString1, String paramString2) throws IOException {
    if (this.byteCode == null)
      this.byteCode = InterpreterUtil.getBytes(new File(paramString1)); 
    return this.byteCode;
  }
}
