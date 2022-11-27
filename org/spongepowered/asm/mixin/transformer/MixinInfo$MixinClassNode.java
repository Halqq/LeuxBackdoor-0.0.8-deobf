package org.spongepowered.asm.mixin.transformer;

import java.util.List;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.tree.ClassNode;

class MixinInfo$MixinClassNode extends ClassNode {
  public final List mixinMethods = this.methods;
  
  final MixinInfo this$0;
  
  public MixinInfo$MixinClassNode(MixinInfo paramMixinInfo1, MixinInfo paramMixinInfo2) {
    this(327680);
  }
  
  public MixinInfo$MixinClassNode(int paramInt) {
    super(paramInt);
  }
  
  public MixinInfo getMixin() {
    return MixinInfo.this;
  }
  
  public MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    MixinInfo$MixinMethodNode mixinInfo$MixinMethodNode = new MixinInfo$MixinMethodNode(MixinInfo.this, paramInt, paramString1, paramString2, paramString3, paramArrayOfString);
    this.methods.add(mixinInfo$MixinMethodNode);
    return (MethodVisitor)mixinInfo$MixinMethodNode;
  }
}
