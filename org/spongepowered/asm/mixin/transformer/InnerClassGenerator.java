package org.spongepowered.asm.mixin.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.mixin.transformer.ext.IClassGenerator;
import org.spongepowered.asm.transformers.MixinClassWriter;

final class InnerClassGenerator implements IClassGenerator {
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private final Map innerClassNames = new HashMap<Object, Object>();
  
  private final Map innerClasses = new HashMap<Object, Object>();
  
  public String registerInnerClass(MixinInfo paramMixinInfo, String paramString, MixinTargetContext paramMixinTargetContext) {
    String str1 = String.format("%s%s", new Object[] { paramString, paramMixinTargetContext });
    String str2 = (String)this.innerClassNames.get(str1);
    str2 = getUniqueReference(paramString, paramMixinTargetContext);
    this.innerClassNames.put(str1, str2);
    this.innerClasses.put(str2, new InnerClassGenerator$InnerClassInfo(str2, paramString, paramMixinInfo, paramMixinTargetContext));
    logger.debug("Inner class {} in {} on {} gets unique name {}", new Object[] { paramString, paramMixinInfo.getClassRef(), paramMixinTargetContext.getTargetClassRef(), str2 });
    return str2;
  }
  
  public byte[] generate(String paramString) {
    String str = paramString.replace('.', '/');
    InnerClassGenerator$InnerClassInfo innerClassGenerator$InnerClassInfo = (InnerClassGenerator$InnerClassInfo)this.innerClasses.get(str);
    return generate(innerClassGenerator$InnerClassInfo);
  }
  
  private byte[] generate(InnerClassGenerator$InnerClassInfo paramInnerClassGenerator$InnerClassInfo) {
    logger.debug("Generating mapped inner class {} (originally {})", new Object[] { paramInnerClassGenerator$InnerClassInfo.getName(), paramInnerClassGenerator$InnerClassInfo.getOriginalName() });
    ClassReader classReader = new ClassReader(paramInnerClassGenerator$InnerClassInfo.getClassBytes());
    MixinClassWriter mixinClassWriter = new MixinClassWriter(classReader, 0);
    classReader.accept((ClassVisitor)new InnerClassGenerator$InnerClassAdapter((ClassVisitor)mixinClassWriter, paramInnerClassGenerator$InnerClassInfo), 8);
    return mixinClassWriter.toByteArray();
  }
  
  private static String getUniqueReference(String paramString, MixinTargetContext paramMixinTargetContext) {
    String str = paramString.substring(paramString.lastIndexOf('$') + 1);
    if (str.matches("^[0-9]+$"))
      str = "Anonymous"; 
    return String.format("%s$%s$%s", new Object[] { paramMixinTargetContext.getTargetClassRef(), str, UUID.randomUUID().toString().replace("-", "") });
  }
}
