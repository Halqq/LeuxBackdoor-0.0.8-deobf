package org.spongepowered.asm.mixin.injection.callback;

import org.spongepowered.asm.lib.Type;

public class CallbackInfoReturnable extends CallbackInfo {
  private Object returnValue = null;
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, Object paramObject) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, byte paramByte) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, char paramChar) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, double paramDouble) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, float paramFloat) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, int paramInt) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, long paramLong) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean, short paramShort) {
    super(paramString, paramBoolean);
  }
  
  public CallbackInfoReturnable(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    super(paramString, paramBoolean1);
  }
  
  public void setReturnValue(Object paramObject) throws CancellationException {
    cancel();
    this.returnValue = paramObject;
  }
  
  public Object getReturnValue() {
    return this.returnValue;
  }
  
  public byte getReturnValueB() {
    return (this.returnValue == null) ? 0 : ((Byte)this.returnValue).byteValue();
  }
  
  public char getReturnValueC() {
    return (this.returnValue == null) ? Character.MIN_VALUE : ((Character)this.returnValue).charValue();
  }
  
  public double getReturnValueD() {
    return (this.returnValue == null) ? 0.0D : ((Double)this.returnValue).doubleValue();
  }
  
  public float getReturnValueF() {
    return (this.returnValue == null) ? 0.0F : ((Float)this.returnValue).floatValue();
  }
  
  public int getReturnValueI() {
    return (this.returnValue == null) ? 0 : ((Integer)this.returnValue).intValue();
  }
  
  public long getReturnValueJ() {
    return (this.returnValue == null) ? 0L : ((Long)this.returnValue).longValue();
  }
  
  public short getReturnValueS() {
    return (this.returnValue == null) ? 0 : ((Short)this.returnValue).shortValue();
  }
  
  public boolean getReturnValueZ() {
    return (this.returnValue == null) ? false : ((Boolean)this.returnValue).booleanValue();
  }
  
  static String getReturnAccessor(Type paramType) {
    return (paramType.getSort() == 10 || paramType.getSort() == 9) ? "getReturnValue" : String.format("getReturnValue%s", new Object[] { paramType.getDescriptor() });
  }
  
  static String getReturnDescriptor(Type paramType) {
    return (paramType.getSort() == 10 || paramType.getSort() == 9) ? String.format("()%s", new Object[] { "Ljava/lang/Object;" }) : String.format("()%s", new Object[] { paramType.getDescriptor() });
  }
}
