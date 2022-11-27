package org.spongepowered.asm.mixin.injection.modify;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.util.Annotations;

public class LocalVariableDiscriminator {
  private final boolean argsOnly;
  
  private final int ordinal;
  
  private final int index;
  
  private final Set names;
  
  private final boolean print;
  
  public LocalVariableDiscriminator(boolean paramBoolean1, int paramInt1, int paramInt2, Set<?> paramSet, boolean paramBoolean2) {
    this.argsOnly = paramBoolean1;
    this.ordinal = paramInt1;
    this.index = paramInt2;
    this.names = Collections.unmodifiableSet(paramSet);
    this.print = paramBoolean2;
  }
  
  public boolean isArgsOnly() {
    return this.argsOnly;
  }
  
  public int getOrdinal() {
    return this.ordinal;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public Set getNames() {
    return this.names;
  }
  
  public boolean hasNames() {
    return !this.names.isEmpty();
  }
  
  public boolean printLVT() {
    return this.print;
  }
  
  protected boolean isImplicit(LocalVariableDiscriminator$Context paramLocalVariableDiscriminator$Context) {
    return (this.ordinal < 0 && this.index < paramLocalVariableDiscriminator$Context.baseArgIndex && this.names.isEmpty());
  }
  
  public int findLocal(Type paramType, boolean paramBoolean, Target paramTarget, AbstractInsnNode paramAbstractInsnNode) {
    return findLocal(new LocalVariableDiscriminator$Context(paramType, paramBoolean, paramTarget, paramAbstractInsnNode));
  }
  
  public int findLocal(LocalVariableDiscriminator$Context paramLocalVariableDiscriminator$Context) {
    return isImplicit(paramLocalVariableDiscriminator$Context) ? findImplicitLocal(paramLocalVariableDiscriminator$Context) : findExplicitLocal(paramLocalVariableDiscriminator$Context);
  }
  
  private int findImplicitLocal(LocalVariableDiscriminator$Context paramLocalVariableDiscriminator$Context) {
    int i = 0;
    byte b = 0;
    for (int j = paramLocalVariableDiscriminator$Context.baseArgIndex; j < paramLocalVariableDiscriminator$Context.locals.length; j++) {
      LocalVariableDiscriminator$Context$Local localVariableDiscriminator$Context$Local = paramLocalVariableDiscriminator$Context.locals[j];
      if (localVariableDiscriminator$Context$Local.type.equals(paramLocalVariableDiscriminator$Context.returnType)) {
        b++;
        i = j;
      } 
    } 
    true;
    throw new InvalidImplicitDiscriminatorException("Found " + b + " candidate variables but exactly 1 is required.");
  }
  
  private int findExplicitLocal(LocalVariableDiscriminator$Context paramLocalVariableDiscriminator$Context) {
    for (int i = paramLocalVariableDiscriminator$Context.baseArgIndex; i < paramLocalVariableDiscriminator$Context.locals.length; i++) {
      LocalVariableDiscriminator$Context$Local localVariableDiscriminator$Context$Local = paramLocalVariableDiscriminator$Context.locals[i];
      if (localVariableDiscriminator$Context$Local.type.equals(paramLocalVariableDiscriminator$Context.returnType))
        if (this.ordinal > -1) {
          if (this.ordinal == localVariableDiscriminator$Context$Local.ord)
            return i; 
        } else if (this.index >= paramLocalVariableDiscriminator$Context.baseArgIndex) {
          if (this.index == i)
            return i; 
        } else if (this.names.contains(localVariableDiscriminator$Context$Local.name)) {
          return i;
        }  
    } 
    return -1;
  }
  
  public static LocalVariableDiscriminator parse(AnnotationNode paramAnnotationNode) {
    boolean bool1 = ((Boolean)Annotations.getValue(paramAnnotationNode, "argsOnly", Boolean.FALSE)).booleanValue();
    int i = ((Integer)Annotations.getValue(paramAnnotationNode, "ordinal", Integer.valueOf(-1))).intValue();
    int j = ((Integer)Annotations.getValue(paramAnnotationNode, "index", Integer.valueOf(-1))).intValue();
    boolean bool2 = ((Boolean)Annotations.getValue(paramAnnotationNode, "print", Boolean.FALSE)).booleanValue();
    HashSet hashSet = new HashSet();
    List list = (List)Annotations.getValue(paramAnnotationNode, "name", null);
    hashSet.addAll(list);
    return new LocalVariableDiscriminator(bool1, i, j, hashSet, bool2);
  }
}
