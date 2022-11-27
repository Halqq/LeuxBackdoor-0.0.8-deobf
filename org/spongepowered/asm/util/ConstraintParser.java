package org.spongepowered.asm.util;

import org.spongepowered.asm.lib.tree.AnnotationNode;

public final class ConstraintParser {
  public static ConstraintParser$Constraint parse(String paramString) {
    if (paramString.length() == 0)
      return ConstraintParser$Constraint.NONE; 
    String[] arrayOfString = paramString.replaceAll("\\s", "").toUpperCase().split(";");
    ConstraintParser$Constraint constraintParser$Constraint = null;
    for (String str : arrayOfString) {
      ConstraintParser$Constraint constraintParser$Constraint1 = new ConstraintParser$Constraint(str);
      constraintParser$Constraint = constraintParser$Constraint1;
    } 
  }
  
  public static ConstraintParser$Constraint parse(AnnotationNode paramAnnotationNode) {
    String str = (String)Annotations.getValue(paramAnnotationNode, "constraints", "");
    return parse(str);
  }
}
