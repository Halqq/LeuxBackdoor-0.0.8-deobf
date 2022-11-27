package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

class MixinEnvironment$MixinLogger$MixinAppender extends AbstractAppender {
  protected MixinEnvironment$MixinLogger$MixinAppender(String paramString, Filter paramFilter, Layout paramLayout) {
    super(paramString, paramFilter, paramLayout);
  }
  
  public void append(LogEvent paramLogEvent) {
    if (paramLogEvent.getLevel() == Level.DEBUG && "Validating minecraft".equals(paramLogEvent.getMessage().getFormat()))
      MixinEnvironment.gotoPhase(MixinEnvironment$Phase.INIT); 
  }
}
