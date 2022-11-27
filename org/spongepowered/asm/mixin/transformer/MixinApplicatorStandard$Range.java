package org.spongepowered.asm.mixin.transformer;

class MixinApplicatorStandard$Range {
  final int start;
  
  final int end;
  
  final int marker;
  
  final MixinApplicatorStandard this$0;
  
  MixinApplicatorStandard$Range(int paramInt1, int paramInt2, int paramInt3) {
    this.start = paramInt1;
    this.end = paramInt2;
    this.marker = paramInt3;
  }
  
  boolean isValid() {
    return (this.start != 0 && this.end != 0 && this.end >= this.start);
  }
  
  boolean contains(int paramInt) {
    return (paramInt >= this.start && paramInt <= this.end);
  }
  
  boolean excludes(int paramInt) {
    return (paramInt < this.start || paramInt > this.end);
  }
  
  public String toString() {
    return String.format("Range[%d-%d,%d,valid=%s)", new Object[] { Integer.valueOf(this.start), Integer.valueOf(this.end), Integer.valueOf(this.marker), Boolean.valueOf(isValid()) });
  }
}
