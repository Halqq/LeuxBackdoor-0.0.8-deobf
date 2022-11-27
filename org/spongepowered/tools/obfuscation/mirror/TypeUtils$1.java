package org.spongepowered.tools.obfuscation.mirror;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;

class TypeUtils$1 {
  static final int[] $SwitchMap$javax$lang$model$type$TypeKind;
  
  static final int[] $SwitchMap$javax$lang$model$element$Modifier = new int[(Modifier.values()).length];
  
  static {
    $SwitchMap$javax$lang$model$element$Modifier[Modifier.PUBLIC.ordinal()] = 1;
    $SwitchMap$javax$lang$model$element$Modifier[Modifier.PROTECTED.ordinal()] = 2;
    $SwitchMap$javax$lang$model$element$Modifier[Modifier.PRIVATE.ordinal()] = 3;
    $SwitchMap$javax$lang$model$type$TypeKind = new int[(TypeKind.values()).length];
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.ARRAY.ordinal()] = 1;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DECLARED.ordinal()] = 2;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.TYPEVAR.ordinal()] = 3;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.ERROR.ordinal()] = 4;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BOOLEAN.ordinal()] = 5;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BYTE.ordinal()] = 6;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.CHAR.ordinal()] = 7;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DOUBLE.ordinal()] = 8;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.FLOAT.ordinal()] = 9;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INT.ordinal()] = 10;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.LONG.ordinal()] = 11;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.SHORT.ordinal()] = 12;
    $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.VOID.ordinal()] = 13;
  }
}
