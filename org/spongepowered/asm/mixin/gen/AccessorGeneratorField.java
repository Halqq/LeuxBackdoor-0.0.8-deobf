package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.FieldNode;

public abstract class AccessorGeneratorField extends AccessorGenerator {
  protected final FieldNode targetField;
  
  protected final Type targetType;
  
  protected final boolean isInstanceField;
  
  public AccessorGeneratorField(AccessorInfo paramAccessorInfo) {
    super(paramAccessorInfo);
    this.targetField = paramAccessorInfo.getTargetField();
    this.targetType = paramAccessorInfo.getTargetFieldType();
    this.isInstanceField = ((this.targetField.access & 0x8) == 0);
  }
}
