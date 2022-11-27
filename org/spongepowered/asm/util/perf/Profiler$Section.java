package org.spongepowered.asm.util.perf;

public class Profiler$Section {
  static final String SEPARATOR_ROOT = " -> ";
  
  static final String SEPARATOR_CHILD = ".";
  
  private final String name;
  
  private boolean root;
  
  private boolean fine;
  
  protected boolean invalidated;
  
  private String info;
  
  final Profiler this$0;
  
  Profiler$Section(String paramString) {
    this.name = paramString;
    this.info = paramString;
  }
  
  Profiler$Section getDelegate() {
    return this;
  }
  
  Profiler$Section invalidate() {
    this.invalidated = true;
    return this;
  }
  
  Profiler$Section setRoot(boolean paramBoolean) {
    this.root = paramBoolean;
    return this;
  }
  
  public boolean isRoot() {
    return this.root;
  }
  
  Profiler$Section setFine(boolean paramBoolean) {
    this.fine = paramBoolean;
    return this;
  }
  
  public boolean isFine() {
    return this.fine;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getBaseName() {
    return this.name;
  }
  
  public void setInfo(String paramString) {
    this.info = paramString;
  }
  
  public String getInfo() {
    return this.info;
  }
  
  Profiler$Section start() {
    return this;
  }
  
  protected Profiler$Section stop() {
    return this;
  }
  
  public Profiler$Section end() {
    if (!this.invalidated)
      Profiler.this.end(this); 
    return this;
  }
  
  public Profiler$Section next(String paramString) {
    end();
    return Profiler.this.begin(paramString);
  }
  
  void mark() {}
  
  public long getTime() {
    return 0L;
  }
  
  public long getTotalTime() {
    return 0L;
  }
  
  public double getSeconds() {
    return 0.0D;
  }
  
  public double getTotalSeconds() {
    return 0.0D;
  }
  
  public long[] getTimes() {
    return new long[1];
  }
  
  public int getCount() {
    return 0;
  }
  
  public int getTotalCount() {
    return 0;
  }
  
  public double getAverageTime() {
    return 0.0D;
  }
  
  public double getTotalAverageTime() {
    return 0.0D;
  }
  
  public final String toString() {
    return this.name;
  }
}
