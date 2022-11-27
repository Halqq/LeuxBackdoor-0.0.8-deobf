package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.util.Counter;

public class MethodMapper {
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private static final List classes = new ArrayList();
  
  private static final Map methods = new HashMap<Object, Object>();
  
  private final ClassInfo info;
  
  public MethodMapper(MixinEnvironment paramMixinEnvironment, ClassInfo paramClassInfo) {
    this.info = paramClassInfo;
  }
  
  public ClassInfo getClassInfo() {
    return this.info;
  }
  
  public void remapHandlerMethod(MixinInfo paramMixinInfo, MethodNode paramMethodNode, ClassInfo$Method paramClassInfo$Method) {
    if (!(paramMethodNode instanceof MixinInfo$MixinMethodNode) || !((MixinInfo$MixinMethodNode)paramMethodNode).isInjector())
      return; 
    if (paramClassInfo$Method.isUnique())
      logger.warn("Redundant @Unique on injector method {} in {}. Injectors are implicitly unique", new Object[] { paramClassInfo$Method, paramMixinInfo }); 
    if (paramClassInfo$Method.isRenamed()) {
      paramMethodNode.name = paramClassInfo$Method.getName();
      return;
    } 
    String str = getHandlerName((MixinInfo$MixinMethodNode)paramMethodNode);
    paramMethodNode.name = paramClassInfo$Method.renameTo(str);
  }
  
  public String getHandlerName(MixinInfo$MixinMethodNode paramMixinInfo$MixinMethodNode) {
    String str1 = InjectionInfo.getInjectorPrefix(paramMixinInfo$MixinMethodNode.getInjectorAnnotation());
    String str2 = getClassUID(paramMixinInfo$MixinMethodNode.getOwner().getClassRef());
    String str3 = getMethodUID(paramMixinInfo$MixinMethodNode.name, paramMixinInfo$MixinMethodNode.desc, !paramMixinInfo$MixinMethodNode.isSurrogate());
    return String.format("%s$%s$%s%s", new Object[] { str1, paramMixinInfo$MixinMethodNode.name, str2, str3 });
  }
  
  private static String getClassUID(String paramString) {
    int i = classes.indexOf(paramString);
    i = classes.size();
    classes.add(paramString);
    return finagle(i);
  }
  
  private static String getMethodUID(String paramString1, String paramString2, boolean paramBoolean) {
    paramBoolean = false;
    String str = String.format("%s%s", new Object[] { paramString1, paramString2 });
    Counter counter = (Counter)methods.get(str);
    counter = new Counter();
    methods.put(str, counter);
    return String.format("%03x", new Object[] { Integer.valueOf(counter.value) });
  }
  
  private static String finagle(int paramInt) {
    String str = Integer.toHexString(paramInt);
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b = 0; b < str.length(); b++) {
      char c = str.charAt(b);
      stringBuilder.append(c = (char)(c + ((c < ':') ? 49 : 10)));
    } 
    return Strings.padStart(stringBuilder.toString(), 3, 'z');
  }
}
