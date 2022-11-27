package org.spongepowered.asm.lib.tree.analysis;

import java.util.List;
import org.spongepowered.asm.lib.Type;

public class SimpleVerifier extends BasicVerifier {
  private final Type currentClass;
  
  private final Type currentSuperClass;
  
  private final List currentClassInterfaces;
  
  private final boolean isInterface;
  
  private ClassLoader loader = getClass().getClassLoader();
  
  public SimpleVerifier() {
    this(null, null, false);
  }
  
  public SimpleVerifier(Type paramType1, Type paramType2, boolean paramBoolean) {
    this(paramType1, paramType2, null, paramBoolean);
  }
  
  public SimpleVerifier(Type paramType1, Type paramType2, List paramList, boolean paramBoolean) {
    this(327680, paramType1, paramType2, paramList, paramBoolean);
  }
  
  protected SimpleVerifier(int paramInt, Type paramType1, Type paramType2, List paramList, boolean paramBoolean) {
    super(paramInt);
    this.currentClass = paramType1;
    this.currentSuperClass = paramType2;
    this.currentClassInterfaces = paramList;
    this.isInterface = paramBoolean;
  }
  
  public void setClassLoader(ClassLoader paramClassLoader) {
    this.loader = paramClassLoader;
  }
  
  public BasicValue newValue(Type paramType) {
    return BasicValue.UNINITIALIZED_VALUE;
  }
  
  protected boolean isArrayValue(BasicValue paramBasicValue) {
    Type type = paramBasicValue.getType();
    return ("Lnull;".equals(type.getDescriptor()) || type.getSort() == 9);
  }
  
  protected BasicValue getElementValue(BasicValue paramBasicValue) throws AnalyzerException {
    Type type = paramBasicValue.getType();
    if (type.getSort() == 9)
      return newValue(Type.getType(type.getDescriptor().substring(1))); 
    if ("Lnull;".equals(type.getDescriptor()))
      return paramBasicValue; 
    throw new Error("Internal error");
  }
  
  protected boolean isSubTypeOf(BasicValue paramBasicValue1, BasicValue paramBasicValue2) {
    Type type1 = paramBasicValue2.getType();
    Type type2 = paramBasicValue1.getType();
    switch (type1.getSort()) {
      case 5:
      case 6:
      case 7:
      case 8:
        return type2.equals(type1);
      case 9:
      case 10:
        return "Lnull;".equals(type2.getDescriptor()) ? true : ((type2.getSort() == 10 || type2.getSort() == 9) ? isAssignableFrom(type1, type2) : false);
    } 
    throw new Error("Internal error");
  }
  
  public BasicValue merge(BasicValue paramBasicValue1, BasicValue paramBasicValue2) {
    if (!paramBasicValue1.equals(paramBasicValue2)) {
      Type type1 = paramBasicValue1.getType();
      Type type2 = paramBasicValue2.getType();
      if (type1.getSort() == 10 || type1.getSort() == 9)
        if (type2.getSort() == 10 || type2.getSort() == 9) {
          if ("Lnull;".equals(type1.getDescriptor()))
            return paramBasicValue2; 
          if ("Lnull;".equals(type2.getDescriptor()))
            return paramBasicValue1; 
          if (isAssignableFrom(type1, type2))
            return paramBasicValue1; 
          if (isAssignableFrom(type2, type1))
            return paramBasicValue2; 
          while (true) {
            if (isInterface(type1))
              return BasicValue.REFERENCE_VALUE; 
            type1 = getSuperClass(type1);
            if (isAssignableFrom(type1, type2))
              return newValue(type1); 
          } 
        }  
      return BasicValue.UNINITIALIZED_VALUE;
    } 
    return paramBasicValue1;
  }
  
  protected boolean isInterface(Type paramType) {
    return (this.currentClass != null && paramType.equals(this.currentClass)) ? this.isInterface : getClass(paramType).isInterface();
  }
  
  protected Type getSuperClass(Type paramType) {
    if (this.currentClass != null && paramType.equals(this.currentClass))
      return this.currentSuperClass; 
    Class clazz = getClass(paramType).getSuperclass();
  }
  
  protected boolean isAssignableFrom(Type paramType1, Type paramType2) {
    if (paramType1.equals(paramType2))
      return true; 
    if (this.currentClass != null && paramType1.equals(this.currentClass))
      return (getSuperClass(paramType2) == null) ? false : (this.isInterface ? ((paramType2.getSort() == 10 || paramType2.getSort() == 9)) : isAssignableFrom(paramType1, getSuperClass(paramType2))); 
    if (this.currentClass != null && paramType2.equals(this.currentClass)) {
      if (isAssignableFrom(paramType1, this.currentSuperClass))
        return true; 
      if (this.currentClassInterfaces != null)
        for (byte b = 0; b < this.currentClassInterfaces.size(); b++) {
          Type type = this.currentClassInterfaces.get(b);
          if (isAssignableFrom(paramType1, type))
            return true; 
        }  
      return false;
    } 
    Class<Object> clazz = getClass(paramType1);
    if (clazz.isInterface())
      clazz = Object.class; 
    return clazz.isAssignableFrom(getClass(paramType2));
  }
  
  protected Class getClass(Type paramType) {
    return (paramType.getSort() == 9) ? Class.forName(paramType.getDescriptor().replace('/', '.'), false, this.loader) : Class.forName(paramType.getClassName(), false, this.loader);
  }
  
  public Value merge(Value paramValue1, Value paramValue2) {
    return merge((BasicValue)paramValue1, (BasicValue)paramValue2);
  }
  
  public Value newValue(Type paramType) {
    return newValue(paramType);
  }
}
