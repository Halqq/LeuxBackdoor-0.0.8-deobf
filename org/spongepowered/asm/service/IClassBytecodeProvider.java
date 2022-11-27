package org.spongepowered.asm.service;

import java.io.IOException;
import org.spongepowered.asm.lib.tree.ClassNode;

public interface IClassBytecodeProvider {
  byte[] getClassBytes(String paramString1, String paramString2) throws IOException;
  
  byte[] getClassBytes(String paramString, boolean paramBoolean) throws ClassNotFoundException, IOException {
    // Byte code:
    //   0: ldc 1
    //   2: istore_2
    //   3: ldc 1
    //   5: istore_2
  }
  
  ClassNode getClassNode(String paramString) throws ClassNotFoundException, IOException;
}
