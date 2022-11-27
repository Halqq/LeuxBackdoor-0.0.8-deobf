package org.spongepowered.asm.mixin;

public enum MixinEnvironment$CompatibilityLevel {
  JAVA_6(6, 50, false),
  JAVA_7(7, 51, false),
  JAVA_8(8, 52, true),
  JAVA_9(9, 53, true);
  
  private static final int CLASS_V1_9 = 53;
  
  private final int ver;
  
  private final int classVersion;
  
  private final boolean supportsMethodsInInterfaces;
  
  private MixinEnvironment$CompatibilityLevel maxCompatibleLevel;
  
  private static final MixinEnvironment$CompatibilityLevel[] $VALUES = new MixinEnvironment$CompatibilityLevel[] { JAVA_6, JAVA_7, JAVA_8, JAVA_9 };
  
  MixinEnvironment$CompatibilityLevel(int paramInt1, int paramInt2, boolean paramBoolean) {
    this.ver = paramInt1;
    this.classVersion = paramInt2;
    this.supportsMethodsInInterfaces = paramBoolean;
  }
  
  private void setMaxCompatibleLevel(MixinEnvironment$CompatibilityLevel paramMixinEnvironment$CompatibilityLevel) {
    this.maxCompatibleLevel = paramMixinEnvironment$CompatibilityLevel;
  }
  
  boolean isSupported() {
    return true;
  }
  
  public int classVersion() {
    return this.classVersion;
  }
  
  public boolean supportsMethodsInInterfaces() {
    return this.supportsMethodsInInterfaces;
  }
  
  public boolean isAtLeast(MixinEnvironment$CompatibilityLevel paramMixinEnvironment$CompatibilityLevel) {
    return (this.ver >= paramMixinEnvironment$CompatibilityLevel.ver);
  }
  
  public boolean canElevateTo(MixinEnvironment$CompatibilityLevel paramMixinEnvironment$CompatibilityLevel) {
    return (this.maxCompatibleLevel == null) ? true : ((paramMixinEnvironment$CompatibilityLevel.ver <= this.maxCompatibleLevel.ver));
  }
  
  public boolean canSupport(MixinEnvironment$CompatibilityLevel paramMixinEnvironment$CompatibilityLevel) {
    return true;
  }
}
