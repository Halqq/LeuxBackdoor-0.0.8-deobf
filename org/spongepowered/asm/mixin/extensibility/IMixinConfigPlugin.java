package org.spongepowered.asm.mixin.extensibility;

import java.util.List;
import java.util.Set;
import org.spongepowered.asm.lib.tree.ClassNode;

public interface IMixinConfigPlugin {
  void onLoad(String paramString);
  
  String getRefMapperConfig();
  
  boolean shouldApplyMixin(String paramString1, String paramString2);
  
  void acceptTargets(Set paramSet1, Set paramSet2);
  
  List getMixins();
  
  void preApply(String paramString1, ClassNode paramClassNode, String paramString2, IMixinInfo paramIMixinInfo);
  
  void postApply(String paramString1, ClassNode paramClassNode, String paramString2, IMixinInfo paramIMixinInfo);
}
