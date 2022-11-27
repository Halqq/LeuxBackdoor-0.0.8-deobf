package org.spongepowered.asm.mixin.gen;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

public enum AccessorInfo$AccessorType {
  FIELD_GETTER((Set)ImmutableSet.of("get", "is")),
  FIELD_SETTER((Set)ImmutableSet.of("set")),
  METHOD_PROXY((Set)ImmutableSet.of("call", "invoke"));
  
  private final Set expectedPrefixes;
  
  private static final AccessorInfo$AccessorType[] $VALUES = new AccessorInfo$AccessorType[] { FIELD_GETTER, FIELD_SETTER, METHOD_PROXY };
  
  AccessorInfo$AccessorType(Set paramSet) {
    this.expectedPrefixes = paramSet;
  }
  
  public boolean isExpectedPrefix(String paramString) {
    return this.expectedPrefixes.contains(paramString);
  }
  
  public String getExpectedPrefixes() {
    return this.expectedPrefixes.toString();
  }
  
  abstract AccessorGenerator getGenerator(AccessorInfo paramAccessorInfo);
}
