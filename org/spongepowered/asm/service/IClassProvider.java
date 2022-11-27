package org.spongepowered.asm.service;

import java.net.URL;

public interface IClassProvider {
  URL[] getClassPath();
  
  Class findClass(String paramString) throws ClassNotFoundException;
  
  Class findClass(String paramString, boolean paramBoolean) throws ClassNotFoundException {
    // Byte code:
    //   0: ldc 1
    //   2: istore_2
    //   3: ldc 1
    //   5: istore_2
  }
  
  Class findAgentClass(String paramString, boolean paramBoolean) throws ClassNotFoundException;
}
