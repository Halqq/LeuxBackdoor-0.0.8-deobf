package org.spongepowered.tools.agent;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.mixin.MixinEnvironment;

class MixinAgentClassLoader extends ClassLoader {
  private static final Logger logger = LogManager.getLogger("mixin.agent");
  
  private Map mixins = new HashMap<Object, Object>();
  
  private Map targets = new HashMap<Object, Object>();
  
  void addMixinClass(String paramString) {
    logger.debug("Mixin class {} added to class loader", new Object[] { paramString });
    byte[] arrayOfByte = materialise(paramString);
    Class<?> clazz = defineClass(paramString, arrayOfByte, 0, arrayOfByte.length);
    clazz.newInstance();
    this.mixins.put(clazz, arrayOfByte);
  }
  
  void addTargetClass(String paramString, byte[] paramArrayOfbyte) {
    this.targets.put(paramString, paramArrayOfbyte);
  }
  
  byte[] getFakeMixinBytecode(Class paramClass) {
    return (byte[])this.mixins.get(paramClass);
  }
  
  byte[] getOriginalTargetBytecode(String paramString) {
    return (byte[])this.targets.get(paramString);
  }
  
  private byte[] materialise(String paramString) {
    ClassWriter classWriter = new ClassWriter(3);
    classWriter.visit(MixinEnvironment.getCompatibilityLevel().classVersion(), 1, paramString.replace('.', '/'), null, Type.getInternalName(Object.class), null);
    MethodVisitor methodVisitor = classWriter.visitMethod(1, "<init>", "()V", null, null);
    methodVisitor.visitCode();
    methodVisitor.visitVarInsn(25, 0);
    methodVisitor.visitMethodInsn(183, Type.getInternalName(Object.class), "<init>", "()V", false);
    methodVisitor.visitInsn(177);
    methodVisitor.visitMaxs(1, 1);
    methodVisitor.visitEnd();
    classWriter.visitEnd();
    return classWriter.toByteArray();
  }
}
