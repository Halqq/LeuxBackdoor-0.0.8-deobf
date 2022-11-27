package org.spongepowered.asm.mixin.injection.invoke;

class RedirectInjector$Meta {
  public static final String KEY = "redirector";
  
  final int priority;
  
  final boolean isFinal;
  
  final String name;
  
  final String desc;
  
  final RedirectInjector this$0;
  
  public RedirectInjector$Meta(int paramInt, boolean paramBoolean, String paramString1, String paramString2) {
    this.priority = paramInt;
    this.isFinal = paramBoolean;
    this.name = paramString1;
    this.desc = paramString2;
  }
  
  RedirectInjector getOwner() {
    return RedirectInjector.this;
  }
}
