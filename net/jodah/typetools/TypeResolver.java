package net.jodah.typetools;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import sun.misc.Unsafe;

public final class TypeResolver {
  private static final Map<Class<?>, Reference<Map<TypeVariable<?>, Type>>> TYPE_VARIABLE_CACHE = Collections.synchronizedMap(new WeakHashMap<Class<?>, Reference<Map<TypeVariable<?>, Type>>>());
  
  private static volatile boolean CACHE_ENABLED = true;
  
  private static boolean RESOLVES_LAMBDAS;
  
  private static Method GET_CONSTANT_POOL;
  
  private static Method GET_CONSTANT_POOL_SIZE;
  
  private static Method GET_CONSTANT_POOL_METHOD_AT;
  
  private static final Map<String, Method> OBJECT_METHODS = new HashMap<String, Method>();
  
  private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPERS;
  
  private static final Double JAVA_VERSION = Double.valueOf(Double.parseDouble(System.getProperty("java.specification.version", "0")));
  
  static {
    try {
      Unsafe unsafe = AccessController.<Unsafe>doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
            public Unsafe run() throws Exception {
              Field f = Unsafe.class.getDeclaredField("theUnsafe");
              f.setAccessible(true);
              return (Unsafe)f.get(null);
            }
          });
      GET_CONSTANT_POOL = Class.class.getDeclaredMethod("getConstantPool", new Class[0]);
      String constantPoolName = (JAVA_VERSION.doubleValue() < 9.0D) ? "sun.reflect.ConstantPool" : "jdk.internal.reflect.ConstantPool";
      Class<?> constantPoolClass = Class.forName(constantPoolName);
      GET_CONSTANT_POOL_SIZE = constantPoolClass.getDeclaredMethod("getSize", new Class[0]);
      GET_CONSTANT_POOL_METHOD_AT = constantPoolClass.getDeclaredMethod("getMethodAt", new Class[] { int.class });
      Field overrideField = AccessibleObject.class.getDeclaredField("override");
      long overrideFieldOffset = unsafe.objectFieldOffset(overrideField);
      unsafe.putBoolean(GET_CONSTANT_POOL, overrideFieldOffset, true);
      unsafe.putBoolean(GET_CONSTANT_POOL_SIZE, overrideFieldOffset, true);
      unsafe.putBoolean(GET_CONSTANT_POOL_METHOD_AT, overrideFieldOffset, true);
      Object constantPool = GET_CONSTANT_POOL.invoke(Object.class, new Object[0]);
      GET_CONSTANT_POOL_SIZE.invoke(constantPool, new Object[0]);
      for (Method method : Object.class.getDeclaredMethods())
        OBJECT_METHODS.put(method.getName(), method); 
      RESOLVES_LAMBDAS = true;
    } catch (Exception exception) {}
    Map<Class<?>, Class<?>> types = new HashMap<Class<?>, Class<?>>();
    types.put(boolean.class, Boolean.class);
    types.put(byte.class, Byte.class);
    types.put(char.class, Character.class);
    types.put(double.class, Double.class);
    types.put(float.class, Float.class);
    types.put(int.class, Integer.class);
    types.put(long.class, Long.class);
    types.put(short.class, Short.class);
    types.put(void.class, Void.class);
    PRIMITIVE_WRAPPERS = Collections.unmodifiableMap(types);
  }
  
  public static final class Unknown {}
  
  public static void enableCache() {
    CACHE_ENABLED = true;
  }
  
  public static void disableCache() {
    TYPE_VARIABLE_CACHE.clear();
    CACHE_ENABLED = false;
  }
  
  public static <T, S extends T> Class<?> resolveRawArgument(Class<T> type, Class<S> subType) {
    return resolveRawArgument(resolveGenericType(type, subType), subType);
  }
  
  public static Class<?> resolveRawArgument(Type genericType, Class<?> subType) {
    Class<?>[] arguments = resolveRawArguments(genericType, subType);
    if (arguments == null)
      return Unknown.class; 
    if (arguments.length != 1)
      throw new IllegalArgumentException("Expected 1 argument for generic type " + genericType + " but found " + arguments.length); 
    return arguments[0];
  }
  
  public static <T, S extends T> Class<?>[] resolveRawArguments(Class<T> type, Class<S> subType) {
    return resolveRawArguments(resolveGenericType(type, subType), subType);
  }
  
  public static Class<?>[] resolveRawArguments(Type genericType, Class<?> subType) {
    Class<?>[] result = null;
    Class<?> functionalInterface = null;
    if (RESOLVES_LAMBDAS && subType.isSynthetic()) {
      Class<?> fi = (genericType instanceof ParameterizedType && ((ParameterizedType)genericType).getRawType() instanceof Class) ? (Class)((ParameterizedType)genericType).getRawType() : ((genericType instanceof Class) ? (Class)genericType : null);
      if (fi != null && fi.isInterface())
        functionalInterface = fi; 
    } 
    if (genericType instanceof ParameterizedType) {
      ParameterizedType paramType = (ParameterizedType)genericType;
      Type[] arguments = paramType.getActualTypeArguments();
      result = new Class[arguments.length];
      for (int i = 0; i < arguments.length; i++)
        result[i] = resolveRawClass(arguments[i], subType, functionalInterface); 
    } else if (genericType instanceof TypeVariable) {
      result = new Class[1];
      result[0] = resolveRawClass(genericType, subType, functionalInterface);
    } else if (genericType instanceof Class) {
      TypeVariable[] arrayOfTypeVariable = ((Class)genericType).getTypeParameters();
      result = new Class[arrayOfTypeVariable.length];
      for (int i = 0; i < arrayOfTypeVariable.length; i++)
        result[i] = resolveRawClass(arrayOfTypeVariable[i], subType, functionalInterface); 
    } 
    return result;
  }
  
  public static Type resolveGenericType(Class<?> type, Type subType) {
    Class<?> rawType;
    if (subType instanceof ParameterizedType) {
      rawType = (Class)((ParameterizedType)subType).getRawType();
    } else {
      rawType = (Class)subType;
    } 
    if (type.equals(rawType))
      return subType; 
    if (type.isInterface())
      for (Type superInterface : rawType.getGenericInterfaces()) {
        Type type1;
        if (superInterface != null && !superInterface.equals(Object.class) && (
          type1 = resolveGenericType(type, superInterface)) != null)
          return type1; 
      }  
    Type superClass = rawType.getGenericSuperclass();
    Type result;
    if (superClass != null && !superClass.equals(Object.class) && (
      result = resolveGenericType(type, superClass)) != null)
      return result; 
    return null;
  }
  
  public static Class<?> resolveRawClass(Type genericType, Class<?> subType) {
    return resolveRawClass(genericType, subType, null);
  }
  
  private static Class<?> resolveRawClass(Type genericType, Class<?> subType, Class<?> functionalInterface) {
    if (genericType instanceof Class)
      return (Class)genericType; 
    if (genericType instanceof ParameterizedType)
      return resolveRawClass(((ParameterizedType)genericType).getRawType(), subType, functionalInterface); 
    if (genericType instanceof GenericArrayType) {
      GenericArrayType arrayType = (GenericArrayType)genericType;
      Class<?> component = resolveRawClass(arrayType.getGenericComponentType(), subType, functionalInterface);
      return Array.newInstance(component, 0).getClass();
    } 
    if (genericType instanceof TypeVariable) {
      TypeVariable<?> variable = (TypeVariable)genericType;
      genericType = getTypeVariableMap(subType, functionalInterface).get(variable);
      genericType = (genericType == null) ? resolveBound(variable) : resolveRawClass(genericType, subType, functionalInterface);
    } 
    return (genericType instanceof Class) ? (Class)genericType : Unknown.class;
  }
  
  private static Map<TypeVariable<?>, Type> getTypeVariableMap(Class<?> targetType, Class<?> functionalInterface) {
    Reference<Map<TypeVariable<?>, Type>> ref = TYPE_VARIABLE_CACHE.get(targetType);
    Map<TypeVariable<?>, Type> map = (ref != null) ? ref.get() : null;
    if (map == null) {
      map = new HashMap<TypeVariable<?>, Type>();
      if (functionalInterface != null)
        populateLambdaArgs(functionalInterface, targetType, map); 
      populateSuperTypeArgs(targetType.getGenericInterfaces(), map, (functionalInterface != null));
      Type genericType = targetType.getGenericSuperclass();
      Class<?> type = targetType.getSuperclass();
      while (type != null && !Object.class.equals(type)) {
        if (genericType instanceof ParameterizedType)
          populateTypeArgs((ParameterizedType)genericType, map, false); 
        populateSuperTypeArgs(type.getGenericInterfaces(), map, false);
        genericType = type.getGenericSuperclass();
        type = type.getSuperclass();
      } 
      type = targetType;
      while (type.isMemberClass()) {
        genericType = type.getGenericSuperclass();
        if (genericType instanceof ParameterizedType)
          populateTypeArgs((ParameterizedType)genericType, map, (functionalInterface != null)); 
        type = type.getEnclosingClass();
      } 
      if (CACHE_ENABLED)
        TYPE_VARIABLE_CACHE.put(targetType, new WeakReference<Map<TypeVariable<?>, Type>>(map)); 
    } 
    return map;
  }
  
  private static void populateSuperTypeArgs(Type[] types, Map<TypeVariable<?>, Type> map, boolean depthFirst) {
    for (Type type : types) {
      if (type instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType)type;
        if (!depthFirst)
          populateTypeArgs(parameterizedType, map, depthFirst); 
        Type rawType = parameterizedType.getRawType();
        if (rawType instanceof Class)
          populateSuperTypeArgs(((Class)rawType).getGenericInterfaces(), map, depthFirst); 
        if (depthFirst)
          populateTypeArgs(parameterizedType, map, depthFirst); 
      } else if (type instanceof Class) {
        populateSuperTypeArgs(((Class)type).getGenericInterfaces(), map, depthFirst);
      } 
    } 
  }
  
  private static void populateTypeArgs(ParameterizedType type, Map<TypeVariable<?>, Type> map, boolean depthFirst) {
    if (type.getRawType() instanceof Class) {
      TypeVariable[] arrayOfTypeVariable = ((Class)type.getRawType()).getTypeParameters();
      Type[] typeArguments = type.getActualTypeArguments();
      if (type.getOwnerType() != null) {
        Type owner = type.getOwnerType();
        if (owner instanceof ParameterizedType)
          populateTypeArgs((ParameterizedType)owner, map, depthFirst); 
      } 
      for (int i = 0; i < typeArguments.length; i++) {
        TypeVariable<?> variable = arrayOfTypeVariable[i];
        Type typeArgument = typeArguments[i];
        if (typeArgument instanceof Class) {
          map.put(variable, typeArgument);
          continue;
        } 
        if (typeArgument instanceof GenericArrayType) {
          map.put(variable, typeArgument);
          continue;
        } 
        if (typeArgument instanceof ParameterizedType) {
          map.put(variable, typeArgument);
          continue;
        } 
        if (typeArgument instanceof TypeVariable) {
          TypeVariable<?> typeVariableArgument = (TypeVariable)typeArgument;
          if (depthFirst) {
            Type existingType = map.get(variable);
            if (existingType != null) {
              map.put(typeVariableArgument, existingType);
              continue;
            } 
          } 
          Type resolvedType = map.get(typeVariableArgument);
          if (resolvedType == null)
            resolvedType = resolveBound(typeVariableArgument); 
          map.put(variable, resolvedType);
        } 
        continue;
      } 
    } 
  }
  
  public static Type resolveBound(TypeVariable<?> typeVariable) {
    Type[] bounds = typeVariable.getBounds();
    if (bounds.length == 0)
      return Unknown.class; 
    Type bound = bounds[0];
    if (bound instanceof TypeVariable)
      bound = resolveBound((TypeVariable)bound); 
    return (bound == Object.class) ? Unknown.class : bound;
  }
  
  private static void populateLambdaArgs(Class<?> functionalInterface, Class<?> lambdaType, Map<TypeVariable<?>, Type> map) {
    if (RESOLVES_LAMBDAS)
      for (Method m : functionalInterface.getMethods()) {
        if (!isDefaultMethod(m) && !Modifier.isStatic(m.getModifiers()) && !m.isBridge()) {
          Method objectMethod = OBJECT_METHODS.get(m.getName());
          if (objectMethod == null || !Arrays.equals((Object[])m.getTypeParameters(), (Object[])objectMethod.getTypeParameters())) {
            Type returnTypeVar = m.getGenericReturnType();
            Type[] paramTypeVars = m.getGenericParameterTypes();
            Member member = getMemberRef(lambdaType);
            if (member == null)
              return; 
            if (returnTypeVar instanceof TypeVariable) {
              Class<?> returnType = (member instanceof Method) ? ((Method)member).getReturnType() : ((Constructor)member).getDeclaringClass();
              returnType = wrapPrimitives(returnType);
              if (!returnType.equals(Void.class))
                map.put((TypeVariable)returnTypeVar, returnType); 
            } 
            Class<?>[] arguments = (member instanceof Method) ? ((Method)member).getParameterTypes() : ((Constructor)member).getParameterTypes();
            int paramOffset = 0;
            if (paramTypeVars.length > 0 && paramTypeVars[0] instanceof TypeVariable && paramTypeVars.length == arguments.length + 1) {
              Class<?> instanceType = member.getDeclaringClass();
              map.put((TypeVariable)paramTypeVars[0], instanceType);
              paramOffset = 1;
            } 
            int argOffset = 0;
            if (paramTypeVars.length < arguments.length)
              argOffset = arguments.length - paramTypeVars.length; 
            for (int i = 0; i + argOffset < arguments.length; i++) {
              if (paramTypeVars[i] instanceof TypeVariable)
                map.put((TypeVariable)paramTypeVars[i + paramOffset], wrapPrimitives(arguments[i + argOffset])); 
            } 
            return;
          } 
        } 
      }  
  }
  
  private static boolean isDefaultMethod(Method m) {
    return (JAVA_VERSION.doubleValue() >= 1.8D && m.isDefault());
  }
  
  private static Member getMemberRef(Class<?> type) {
    Object constantPool;
    try {
      constantPool = GET_CONSTANT_POOL.invoke(type, new Object[0]);
    } catch (Exception ignore) {
      return null;
    } 
    Member result = null;
    for (int i = getConstantPoolSize(constantPool) - 1; i >= 0; i--) {
      Member member = getConstantPoolMethodAt(constantPool, i);
      if (member != null && (!(member instanceof Constructor) || 
        
        !member.getDeclaringClass().getName().equals("java.lang.invoke.SerializedLambda")) && 
        !member.getDeclaringClass().isAssignableFrom(type)) {
        result = member;
        if (!(member instanceof Method) || !isAutoBoxingMethod((Method)member))
          break; 
      } 
    } 
    return result;
  }
  
  private static boolean isAutoBoxingMethod(Method method) {
    Class<?>[] parameters = method.getParameterTypes();
    return (method.getName().equals("valueOf") && parameters.length == 1 && parameters[0].isPrimitive() && 
      wrapPrimitives(parameters[0]).equals(method.getDeclaringClass()));
  }
  
  private static Class<?> wrapPrimitives(Class<?> clazz) {
    return clazz.isPrimitive() ? PRIMITIVE_WRAPPERS.get(clazz) : clazz;
  }
  
  private static int getConstantPoolSize(Object constantPool) {
    try {
      return ((Integer)GET_CONSTANT_POOL_SIZE.invoke(constantPool, new Object[0])).intValue();
    } catch (Exception ignore) {
      return 0;
    } 
  }
  
  private static Member getConstantPoolMethodAt(Object constantPool, int i) {
    try {
      return (Member)GET_CONSTANT_POOL_METHOD_AT.invoke(constantPool, new Object[] { Integer.valueOf(i) });
    } catch (Exception ignore) {
      return null;
    } 
  }
}
