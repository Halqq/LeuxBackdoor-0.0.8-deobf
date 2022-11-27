package org.spongepowered.asm.mixin.injection.struct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.injection.throwables.InjectionValidationException;

public class InjectorGroupInfo {
  private final String name;
  
  private final List members = new ArrayList();
  
  private final boolean isDefault;
  
  private int minCallbackCount = -1;
  
  private int maxCallbackCount = Integer.MAX_VALUE;
  
  public InjectorGroupInfo(String paramString) {
    this(paramString, false);
  }
  
  InjectorGroupInfo(String paramString, boolean paramBoolean) {
    this.name = paramString;
    this.isDefault = paramBoolean;
  }
  
  public String toString() {
    return String.format("@Group(name=%s, min=%d, max=%d)", new Object[] { getName(), Integer.valueOf(getMinRequired()), Integer.valueOf(getMaxAllowed()) });
  }
  
  public boolean isDefault() {
    return this.isDefault;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getMinRequired() {
    return Math.max(this.minCallbackCount, 1);
  }
  
  public int getMaxAllowed() {
    return Math.min(this.maxCallbackCount, 2147483647);
  }
  
  public Collection getMembers() {
    return Collections.unmodifiableCollection(this.members);
  }
  
  public void setMinRequired(int paramInt) {
    if (paramInt < 1)
      throw new IllegalArgumentException("Cannot set zero or negative value for injector group min count. Attempted to set min=" + paramInt + " on " + this); 
    if (this.minCallbackCount > 0 && this.minCallbackCount != paramInt)
      LogManager.getLogger("mixin").warn("Conflicting min value '{}' on @Group({}), previously specified {}", new Object[] { Integer.valueOf(paramInt), this.name, Integer.valueOf(this.minCallbackCount) }); 
    this.minCallbackCount = Math.max(this.minCallbackCount, paramInt);
  }
  
  public void setMaxAllowed(int paramInt) {
    if (paramInt < 1)
      throw new IllegalArgumentException("Cannot set zero or negative value for injector group max count. Attempted to set max=" + paramInt + " on " + this); 
    if (this.maxCallbackCount < Integer.MAX_VALUE && this.maxCallbackCount != paramInt)
      LogManager.getLogger("mixin").warn("Conflicting max value '{}' on @Group({}), previously specified {}", new Object[] { Integer.valueOf(paramInt), this.name, Integer.valueOf(this.maxCallbackCount) }); 
    this.maxCallbackCount = Math.min(this.maxCallbackCount, paramInt);
  }
  
  public InjectorGroupInfo add(InjectionInfo paramInjectionInfo) {
    this.members.add(paramInjectionInfo);
    return this;
  }
  
  public InjectorGroupInfo validate() throws InjectionValidationException {
    if (this.members.size() == 0)
      return this; 
    int i = 0;
    for (InjectionInfo injectionInfo : this.members)
      i += injectionInfo.getInjectedCallbackCount(); 
    int j = getMinRequired();
    int k = getMaxAllowed();
    if (i < j)
      throw new InjectionValidationException(this, String.format("expected %d invocation(s) but only %d succeeded", new Object[] { Integer.valueOf(j), Integer.valueOf(i) })); 
    if (i > k)
      throw new InjectionValidationException(this, String.format("maximum of %d invocation(s) allowed but %d succeeded", new Object[] { Integer.valueOf(k), Integer.valueOf(i) })); 
    return this;
  }
}
