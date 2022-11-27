package org.spongepowered.asm.lib.util;

import java.util.Map;

public interface ASMifiable {
  void asmify(StringBuffer paramStringBuffer, String paramString, Map paramMap) {
    // Byte code:
    //   0: ldc 'attr'
    //   2: astore_2
    //   3: ldc 'attr'
    //   5: astore_2
  }
}
