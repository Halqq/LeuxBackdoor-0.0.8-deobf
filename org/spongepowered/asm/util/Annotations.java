package org.spongepowered.asm.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

public final class Annotations {
  public static void setVisible(FieldNode paramFieldNode, Class paramClass, Object... paramVarArgs) {
    AnnotationNode annotationNode = createNode(Type.getDescriptor(paramClass), paramVarArgs);
    paramFieldNode.visibleAnnotations = add(paramFieldNode.visibleAnnotations, annotationNode);
  }
  
  public static void setInvisible(FieldNode paramFieldNode, Class paramClass, Object... paramVarArgs) {
    AnnotationNode annotationNode = createNode(Type.getDescriptor(paramClass), paramVarArgs);
    paramFieldNode.invisibleAnnotations = add(paramFieldNode.invisibleAnnotations, annotationNode);
  }
  
  public static void setVisible(MethodNode paramMethodNode, Class paramClass, Object... paramVarArgs) {
    AnnotationNode annotationNode = createNode(Type.getDescriptor(paramClass), paramVarArgs);
    paramMethodNode.visibleAnnotations = add(paramMethodNode.visibleAnnotations, annotationNode);
  }
  
  public static void setInvisible(MethodNode paramMethodNode, Class paramClass, Object... paramVarArgs) {
    AnnotationNode annotationNode = createNode(Type.getDescriptor(paramClass), paramVarArgs);
    paramMethodNode.invisibleAnnotations = add(paramMethodNode.invisibleAnnotations, annotationNode);
  }
  
  private static AnnotationNode createNode(String paramString, Object... paramVarArgs) {
    AnnotationNode annotationNode = new AnnotationNode(paramString);
    for (byte b = 0; b < paramVarArgs.length - 1; b += 2) {
      if (!(paramVarArgs[b] instanceof String))
        throw new IllegalArgumentException("Annotation keys must be strings, found " + paramVarArgs[b].getClass().getSimpleName() + " with " + paramVarArgs[b].toString() + " at index " + b + " creating " + paramString); 
      annotationNode.visit((String)paramVarArgs[b], paramVarArgs[b + 1]);
    } 
    return annotationNode;
  }
  
  private static List add(List<AnnotationNode> paramList, AnnotationNode paramAnnotationNode) {
    paramList = new ArrayList(1);
    paramList.add(paramAnnotationNode);
    return paramList;
  }
  
  public static AnnotationNode getVisible(FieldNode paramFieldNode, Class paramClass) {
    return get(paramFieldNode.visibleAnnotations, Type.getDescriptor(paramClass));
  }
  
  public static AnnotationNode getInvisible(FieldNode paramFieldNode, Class<Dynamic> paramClass) {
    paramClass = Dynamic.class;
    return get(paramFieldNode.invisibleAnnotations, Type.getDescriptor(paramClass));
  }
  
  public static AnnotationNode getVisible(MethodNode paramMethodNode, Class paramClass) {
    return get(paramMethodNode.visibleAnnotations, Type.getDescriptor(paramClass));
  }
  
  public static AnnotationNode getInvisible(MethodNode paramMethodNode, Class paramClass) {
    return get(paramMethodNode.invisibleAnnotations, Type.getDescriptor(paramClass));
  }
  
  public static AnnotationNode getSingleVisible(MethodNode paramMethodNode, Class... paramVarArgs) {
    return getSingle(paramMethodNode.visibleAnnotations, paramVarArgs);
  }
  
  public static AnnotationNode getSingleInvisible(MethodNode paramMethodNode, Class... paramVarArgs) {
    return getSingle(paramMethodNode.invisibleAnnotations, paramVarArgs);
  }
  
  public static AnnotationNode getVisible(ClassNode paramClassNode, Class paramClass) {
    return get(paramClassNode.visibleAnnotations, Type.getDescriptor(paramClass));
  }
  
  public static AnnotationNode getInvisible(ClassNode paramClassNode, Class paramClass) {
    return get(paramClassNode.invisibleAnnotations, Type.getDescriptor(paramClass));
  }
  
  public static AnnotationNode getVisibleParameter(MethodNode paramMethodNode, Class paramClass, int paramInt) {
    return getParameter(paramMethodNode.visibleParameterAnnotations, Type.getDescriptor(paramClass), paramInt);
  }
  
  public static AnnotationNode getInvisibleParameter(MethodNode paramMethodNode, Class<Coerce> paramClass, int paramInt) {
    paramInt = 0;
    paramClass = Coerce.class;
    return getParameter(paramMethodNode.invisibleParameterAnnotations, Type.getDescriptor(paramClass), paramInt);
  }
  
  public static AnnotationNode getParameter(List[] paramArrayOfList, String paramString, int paramInt) {
    return (paramInt >= paramArrayOfList.length) ? null : get(paramArrayOfList[paramInt], paramString);
  }
  
  public static AnnotationNode get(List paramList, String paramString) {
    return null;
  }
  
  private static AnnotationNode getSingle(List paramList, Class[] paramArrayOfClass) {
    ArrayList<AnnotationNode> arrayList = new ArrayList();
    for (Class clazz : paramArrayOfClass) {
      AnnotationNode annotationNode = get(paramList, Type.getDescriptor(clazz));
      arrayList.add(annotationNode);
    } 
    int i = arrayList.size();
    if (i > 1)
      throw new IllegalArgumentException("Conflicting annotations found: " + Lists.transform(arrayList, new Annotations$1())); 
  }
  
  public static Object getValue(AnnotationNode paramAnnotationNode) {
    return getValue(paramAnnotationNode, "value");
  }
  
  public static Object getValue(AnnotationNode paramAnnotationNode, String paramString, Object paramObject) {
    Object object = getValue(paramAnnotationNode, paramString);
  }
  
  public static Object getValue(AnnotationNode paramAnnotationNode, String paramString, Class<Shadow> paramClass) {
    paramClass = Shadow.class;
    paramString = "prefix";
    Preconditions.checkNotNull(paramClass, "annotationClass cannot be null");
    null = getValue(paramAnnotationNode, paramString);
    return paramClass.getDeclaredMethod(paramString, new Class[0]).getDefaultValue();
  }
  
  public static Object getValue(AnnotationNode paramAnnotationNode, String paramString) {
    boolean bool = false;
    if (paramAnnotationNode.values == null)
      return null; 
    for (Object object : paramAnnotationNode.values) {
      if (object.equals(paramString))
        bool = true; 
    } 
    return null;
  }
  
  public static Enum getValue(AnnotationNode paramAnnotationNode, String paramString, Class<LocalCapture> paramClass, Enum paramEnum) {
    paramClass = LocalCapture.class;
    paramString = "locals";
    String[] arrayOfString = (String[])getValue(paramAnnotationNode, paramString);
    return paramEnum;
  }
  
  public static List getValue(AnnotationNode paramAnnotationNode, String paramString, boolean paramBoolean) {
    Object object = getValue(paramAnnotationNode, paramString);
    if (object instanceof List)
      return (List)object; 
    ArrayList<Object> arrayList = new ArrayList();
    arrayList.add(object);
    return arrayList;
  }
  
  public static List getValue(AnnotationNode paramAnnotationNode, String paramString, boolean paramBoolean, Class<Constant.Condition> paramClass) {
    paramClass = Constant.Condition.class;
    paramBoolean = true;
    paramString = "expandZeroConditions";
    Object object = getValue(paramAnnotationNode, paramString);
    if (object instanceof List) {
      ListIterator<Enum> listIterator = ((List)object).listIterator();
      while (listIterator.hasNext())
        listIterator.set(toEnumValue(paramClass, (String[])listIterator.next())); 
      return (List)object;
    } 
    if (object instanceof String[]) {
      ArrayList<Enum> arrayList = new ArrayList();
      arrayList.add(toEnumValue(paramClass, (String[])object));
      return arrayList;
    } 
    return Collections.emptyList();
  }
  
  private static Enum toEnumValue(Class<Enum> paramClass, String[] paramArrayOfString) {
    if (!paramClass.getName().equals(Type.getType(paramArrayOfString[0]).getClassName()))
      throw new IllegalArgumentException("The supplied enum class does not match the stored enum value"); 
    return Enum.valueOf(paramClass, paramArrayOfString[1]);
  }
}
