package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;

class MixinEnvironment$MixinLogger {
  static MixinEnvironment$MixinLogger$MixinAppender appender = new MixinEnvironment$MixinLogger$MixinAppender("MixinLogger", null, null);
  
  public MixinEnvironment$MixinLogger() {
    Logger logger = (Logger)LogManager.getLogger("FML");
    appender.start();
    logger.addAppender((Appender)appender);
  }
}
