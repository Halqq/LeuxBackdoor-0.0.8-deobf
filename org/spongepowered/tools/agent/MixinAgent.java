package org.spongepowered.tools.agent;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.transformer.MixinTransformer;
import org.spongepowered.asm.mixin.transformer.ext.IHotSwap;

public class MixinAgent implements IHotSwap {
  public static final byte[] ERROR_BYTECODE = new byte[] { 1 };
  
  static final MixinAgentClassLoader classLoader = new MixinAgentClassLoader();
  
  static final Logger logger = LogManager.getLogger("mixin.agent");
  
  static Instrumentation instrumentation = null;
  
  private static List agents = new ArrayList();
  
  final MixinTransformer classTransformer;
  
  public MixinAgent(MixinTransformer paramMixinTransformer) {
    this.classTransformer = paramMixinTransformer;
    agents.add(this);
    if (instrumentation != null)
      initTransformer(); 
  }
  
  private void initTransformer() {
    instrumentation.addTransformer(new MixinAgent$Transformer(this), true);
  }
  
  public void registerMixinClass(String paramString) {
    classLoader.addMixinClass(paramString);
  }
  
  public void registerTargetClass(String paramString, byte[] paramArrayOfbyte) {
    classLoader.addTargetClass(paramString, paramArrayOfbyte);
  }
  
  public static void init(Instrumentation paramInstrumentation) {
    instrumentation = paramInstrumentation;
    if (!instrumentation.isRedefineClassesSupported())
      logger.error("The instrumentation doesn't support re-definition of classes"); 
    for (MixinAgent mixinAgent : agents)
      mixinAgent.initTransformer(); 
  }
  
  public static void premain(String paramString, Instrumentation paramInstrumentation) {
    System.setProperty("mixin.hotSwap", "true");
    init(paramInstrumentation);
  }
  
  public static void agentmain(String paramString, Instrumentation paramInstrumentation) {
    init(paramInstrumentation);
  }
}
