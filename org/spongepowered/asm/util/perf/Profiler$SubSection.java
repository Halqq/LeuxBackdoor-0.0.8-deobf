package org.spongepowered.asm.util.perf;

class Profiler$SubSection extends Profiler$LiveSection {
  private final String baseName;
  
  private final Profiler$Section root;
  
  final Profiler this$0;
  
  Profiler$SubSection(String paramString1, int paramInt, String paramString2, Profiler$Section paramProfiler$Section) {
    super(paramProfiler, paramString1, paramInt);
    this.baseName = paramString2;
    this.root = paramProfiler$Section;
  }
  
  Profiler$Section invalidate() {
    this.root.invalidate();
    return super.invalidate();
  }
  
  public String getBaseName() {
    return this.baseName;
  }
  
  public void setInfo(String paramString) {
    this.root.setInfo(paramString);
    super.setInfo(paramString);
  }
  
  Profiler$Section getDelegate() {
    return this.root;
  }
  
  Profiler$Section start() {
    this.root.start();
    return super.start();
  }
  
  public Profiler$Section end() {
    this.root.stop();
    return super.end();
  }
  
  public Profiler$Section next(String paramString) {
    stop();
    return this.root.next(paramString);
  }
}
