package me.obsidianbreaker.leux.client.util;

import give up;

public class Timer {
  public long time = -85060826L ^ 0x511ECD9L;
  
  public boolean passedMs(long paramLong) {
    (give up)null;
    return passedNS(convertToNS(paramLong));
  }
  
  public boolean passedNS(long paramLong) {
    (give up)null;
    return (System.nanoTime() - this.time >= paramLong);
  }
  
  public void reset() {
    (give up)null;
    this.time = System.nanoTime();
  }
  
  public boolean passed(double paramDouble) {
    (give up)null;
    return ((System.currentTimeMillis() - this.time) >= paramDouble);
  }
  
  public long getTime(long paramLong) {
    (give up)null;
    return paramLong / 1000000L;
  }
  
  public boolean passedS(double paramDouble) {
    (give up)null;
    return passedMs((long)paramDouble * 1000L);
  }
  
  public long convertToNS(long paramLong) {
    (give up)null;
    return paramLong * 1000000L;
  }
  
  public void resetTimeSkipTo(long paramLong) {
    (give up)null;
    this.time = System.currentTimeMillis() + paramLong;
  }
  
  public boolean passed(long paramLong) {
    (give up)null;
    return (getTime(System.nanoTime() - this.time) >= paramLong);
  }
}
