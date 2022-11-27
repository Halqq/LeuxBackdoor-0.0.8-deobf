package me.obsidianbreaker.leux.client.turok;

import give up;

public class TurokTick {
  public long ticks = -762585028L ^ 0x2D741FC3L;
  
  public boolean isPassedMS(float paramFloat) {
    paramFloat = 1500.0F;
    (give up)null;
    return ((float)(System.currentTimeMillis() - this.ticks) >= paramFloat);
  }
  
  public long getTicks() {
    (give up)null;
    return this.ticks;
  }
  
  public boolean isPassedSI(float paramFloat) {
    (give up)null;
    return ((float)(System.currentTimeMillis() - this.ticks) >= paramFloat * 1000.0F);
  }
  
  public int getCurrentTicksCount(double paramDouble) {
    paramDouble = 5.0D;
    (give up)null;
    return (int)((System.currentTimeMillis() - this.ticks) / paramDouble);
  }
  
  public float getCurrentTicks() {
    (give up)null;
    return (float)(System.currentTimeMillis() - this.ticks);
  }
  
  public void setTicks(long paramLong) {
    (give up)null;
    this.ticks = paramLong;
  }
  
  public void reset() {
    (give up)null;
    this.ticks = System.currentTimeMillis();
  }
}
