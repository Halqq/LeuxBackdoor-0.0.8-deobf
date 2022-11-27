package me.obsidianbreaker.leux.client.util;

import give up;
import me.obsidianbreaker.leux.client.Leux;

public class BurrowUtil extends RuntimeException {
  public BurrowUtil(String paramString) {
    super(paramString);
    setStackTrace(new StackTraceElement[0]);
  }
  
  public synchronized Throwable fillInStackTrace() {
    (give up)null;
    return this;
  }
  
  public String toString() {
    (give up)null;
    return "" + Leux.get_version();
  }
}
