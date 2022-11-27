package org.spongepowered.asm.util;

import com.google.common.base.Function;
import org.spongepowered.asm.lib.tree.AnnotationNode;

final class Annotations$1 implements Function {
  public String apply(AnnotationNode paramAnnotationNode) {
    return paramAnnotationNode.desc;
  }
  
  public Object apply(Object paramObject) {
    return apply((AnnotationNode)paramObject);
  }
}
