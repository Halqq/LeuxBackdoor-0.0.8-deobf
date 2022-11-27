package org.spongepowered.tools.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Iterator;
import java.util.List;
import org.spongepowered.asm.service.IMixinService;
import org.spongepowered.asm.service.MixinService;

class MixinAgent$Transformer implements ClassFileTransformer {
  final MixinAgent this$0;
  
  public byte[] transform(ClassLoader paramClassLoader, String paramString, Class paramClass, ProtectionDomain paramProtectionDomain, byte[] paramArrayOfbyte) throws IllegalClassFormatException {
    return null;
  }
  
  private List reloadMixin(String paramString, byte[] paramArrayOfbyte) {
    MixinAgent.logger.info("Redefining mixin {}", new Object[] { paramString });
    return MixinAgent.this.classTransformer.reload(paramString.replace('/', '.'), paramArrayOfbyte);
  }
  
  private boolean reApplyMixins(List paramList) {
    IMixinService iMixinService = MixinService.getService();
    Iterator<String> iterator = paramList.iterator();
    if (iterator.hasNext()) {
      String str1 = iterator.next();
      String str2 = str1.replace('/', '.');
      MixinAgent.logger.debug("Re-transforming target class {}", new Object[] { str1 });
      Class clazz = iMixinService.getClassProvider().findClass(str2);
      byte[] arrayOfByte = MixinAgent.classLoader.getOriginalTargetBytecode(str2);
      MixinAgent.logger.error("Target class {} bytecode is not registered", new Object[] { str2 });
      return false;
    } 
    return true;
  }
}
