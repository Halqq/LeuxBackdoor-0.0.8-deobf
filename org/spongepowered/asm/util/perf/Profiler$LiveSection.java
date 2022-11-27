package org.spongepowered.asm.util.perf;

import java.util.Arrays;

class Profiler$LiveSection extends Profiler$Section {
  private int cursor = 0;
  
  private long[] times = new long[0];
  
  private long start = 0L;
  
  private long time;
  
  private long markedTime;
  
  private int count;
  
  private int markedCount;
  
  final Profiler this$0;
  
  Profiler$LiveSection(String paramString, int paramInt) {
    super(paramProfiler, paramString);
    this.cursor = paramInt;
  }
  
  Profiler$Section start() {
    this.start = System.currentTimeMillis();
    return this;
  }
  
  protected Profiler$Section stop() {
    if (this.start > 0L)
      this.time += System.currentTimeMillis() - this.start; 
    this.start = 0L;
    this.count++;
    return this;
  }
  
  public Profiler$Section end() {
    stop();
    if (!this.invalidated)
      Profiler.this.end(this); 
    return this;
  }
  
  void mark() {
    if (this.cursor >= this.times.length)
      this.times = Arrays.copyOf(this.times, this.cursor + 4); 
    this.times[this.cursor] = this.time;
    this.markedTime += this.time;
    this.markedCount += this.count;
    this.time = 0L;
    this.count = 0;
    this.cursor++;
  }
  
  public long getTime() {
    return this.time;
  }
  
  public long getTotalTime() {
    return this.time + this.markedTime;
  }
  
  public double getSeconds() {
    return this.time * 0.001D;
  }
  
  public double getTotalSeconds() {
    return (this.time + this.markedTime) * 0.001D;
  }
  
  public long[] getTimes() {
    long[] arrayOfLong = new long[this.cursor + 1];
    System.arraycopy(this.times, 0, arrayOfLong, 0, Math.min(this.times.length, this.cursor));
    arrayOfLong[this.cursor] = this.time;
    return arrayOfLong;
  }
  
  public int getCount() {
    return this.count;
  }
  
  public int getTotalCount() {
    return this.count + this.markedCount;
  }
  
  public double getAverageTime() {
    return (this.count > 0) ? (this.time / this.count) : 0.0D;
  }
  
  public double getTotalAverageTime() {
    return (this.count > 0) ? ((this.time + this.markedTime) / (this.count + this.markedCount)) : 0.0D;
  }
}
