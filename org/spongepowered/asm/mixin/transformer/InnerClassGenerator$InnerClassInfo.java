package org.spongepowered.asm.mixin.transformer;

import java.io.IOException;
import org.spongepowered.asm.lib.commons.Remapper;
import org.spongepowered.asm.service.MixinService;

class InnerClassGenerator$InnerClassInfo extends Remapper {
  private final String name;
  
  private final String originalName;
  
  private final MixinInfo owner;
  
  private final MixinTargetContext target;
  
  private final String ownerName;
  
  private final String targetName;
  
  InnerClassGenerator$InnerClassInfo(String paramString1, String paramString2, MixinInfo paramMixinInfo, MixinTargetContext paramMixinTargetContext) {
    this.name = paramString1;
    this.originalName = paramString2;
    this.owner = paramMixinInfo;
    this.ownerName = paramMixinInfo.getClassRef();
    this.target = paramMixinTargetContext;
    this.targetName = paramMixinTargetContext.getTargetClassRef();
  }
  
  String getName() {
    return this.name;
  }
  
  String getOriginalName() {
    return this.originalName;
  }
  
  MixinInfo getOwner() {
    return this.owner;
  }
  
  MixinTargetContext getTarget() {
    return this.target;
  }
  
  String getOwnerName() {
    return this.ownerName;
  }
  
  String getTargetName() {
    return this.targetName;
  }
  
  byte[] getClassBytes() throws ClassNotFoundException, IOException {
    return MixinService.getService().getBytecodeProvider().getClassBytes(this.originalName, true);
  }
  
  public String mapMethodName(String paramString1, String paramString2, String paramString3) {
    if (this.ownerName.equalsIgnoreCase(paramString1)) {
      ClassInfo$Method classInfo$Method = this.owner.getClassInfo().findMethod(paramString2, paramString3, 10);
      return classInfo$Method.getName();
    } 
    return super.mapMethodName(paramString1, paramString2, paramString3);
  }
  
  public String map(String paramString) {
    return this.originalName.equals(paramString) ? this.name : (this.ownerName.equals(paramString) ? this.targetName : paramString);
  }
  
  public String toString() {
    return this.name;
  }
}
