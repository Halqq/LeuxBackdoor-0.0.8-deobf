package org.spongepowered.asm.transformers;

import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.service.ILegacyClassTransformer;

public abstract class TreeTransformer implements ILegacyClassTransformer {
  private ClassReader classReader;
  
  private ClassNode classNode;
  
  protected final ClassNode readClass(byte[] paramArrayOfbyte) {
    return readClass(paramArrayOfbyte, true);
  }
  
  protected final ClassNode readClass(byte[] paramArrayOfbyte, boolean paramBoolean) {
    paramBoolean = true;
    ClassReader classReader = new ClassReader(paramArrayOfbyte);
    this.classReader = classReader;
    ClassNode classNode = new ClassNode();
    classReader.accept((ClassVisitor)classNode, 8);
    return classNode;
  }
  
  protected final byte[] writeClass(ClassNode paramClassNode) {
    if (this.classReader != null && this.classNode == paramClassNode) {
      this.classNode = null;
      MixinClassWriter mixinClassWriter1 = new MixinClassWriter(this.classReader, 3);
      this.classReader = null;
      paramClassNode.accept((ClassVisitor)mixinClassWriter1);
      return mixinClassWriter1.toByteArray();
    } 
    this.classNode = null;
    MixinClassWriter mixinClassWriter = new MixinClassWriter(3);
    paramClassNode.accept((ClassVisitor)mixinClassWriter);
    return mixinClassWriter.toByteArray();
  }
}
