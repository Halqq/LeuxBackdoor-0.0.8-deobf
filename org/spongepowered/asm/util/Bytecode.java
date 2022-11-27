package org.spongepowered.asm.util;

import com.google.common.base.Joiner;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LineNumberNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.lib.util.CheckClassAdapter;
import org.spongepowered.asm.lib.util.TraceClassVisitor;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.util.throwables.SyntheticBridgeException;

public final class Bytecode {
  public static final int[] CONSTANTS_INT = new int[] { 2, 3, 4, 5, 6, 7, 8 };
  
  public static final int[] CONSTANTS_FLOAT = new int[] { 11, 12, 13 };
  
  public static final int[] CONSTANTS_DOUBLE = new int[] { 14, 15 };
  
  public static final int[] CONSTANTS_LONG = new int[] { 9, 10 };
  
  public static final int[] CONSTANTS_ALL = new int[] { 
      1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
      11, 12, 13, 14, 15, 16, 17, 18 };
  
  private static final Object[] CONSTANTS_VALUES = new Object[] { 
      null, Integer.valueOf(-1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Long.valueOf(0L), Long.valueOf(1L), 
      Float.valueOf(0.0F), Float.valueOf(1.0F), Float.valueOf(2.0F), Double.valueOf(0.0D), Double.valueOf(1.0D) };
  
  private static final String[] CONSTANTS_TYPES = new String[] { 
      null, "I", "I", "I", "I", "I", "I", "I", "J", "J", 
      "F", "F", "F", "D", "D", "I", "I" };
  
  private static final String[] BOXING_TYPES = new String[] { 
      null, "java/lang/Boolean", "java/lang/Character", "java/lang/Byte", "java/lang/Short", "java/lang/Integer", "java/lang/Float", "java/lang/Long", "java/lang/Double", null, 
      null, null };
  
  private static final String[] UNBOXING_METHODS = new String[] { 
      null, "booleanValue", "charValue", "byteValue", "shortValue", "intValue", "floatValue", "longValue", "doubleValue", null, 
      null, null };
  
  private static final Class[] MERGEABLE_MIXIN_ANNOTATIONS = new Class[] { Overwrite.class, Intrinsic.class, Final.class, Debug.class };
  
  private static Pattern mergeableAnnotationPattern = getMergeableAnnotationPattern();
  
  private static final Logger logger = LogManager.getLogger("mixin");
  
  public static MethodNode findMethod(ClassNode paramClassNode, String paramString1, String paramString2) {
    for (MethodNode methodNode : paramClassNode.methods) {
      if (methodNode.name.equals(paramString1) && methodNode.desc.equals(paramString2))
        return methodNode; 
    } 
    return null;
  }
  
  public static AbstractInsnNode findInsn(MethodNode paramMethodNode, int paramInt) {
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode.getOpcode() == paramInt)
        return abstractInsnNode; 
    } 
    return null;
  }
  
  public static MethodInsnNode findSuperInit(MethodNode paramMethodNode, String paramString) {
    if (!"<init>".equals(paramMethodNode.name))
      return null; 
    byte b = 0;
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof TypeInsnNode && abstractInsnNode.getOpcode() == 187) {
        b++;
        continue;
      } 
      if (abstractInsnNode instanceof MethodInsnNode && abstractInsnNode.getOpcode() == 183) {
        MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
        if ("<init>".equals(methodInsnNode.name))
          if (methodInsnNode.owner.equals(paramString))
            return methodInsnNode;  
      } 
    } 
    return null;
  }
  
  public static void textify(ClassNode paramClassNode, OutputStream paramOutputStream) {
    paramClassNode.accept((ClassVisitor)new TraceClassVisitor(new PrintWriter(paramOutputStream)));
  }
  
  public static void textify(MethodNode paramMethodNode, OutputStream paramOutputStream) {
    TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(paramOutputStream));
    MethodVisitor methodVisitor = traceClassVisitor.visitMethod(paramMethodNode.access, paramMethodNode.name, paramMethodNode.desc, paramMethodNode.signature, (String[])paramMethodNode.exceptions.toArray((Object[])new String[0]));
    paramMethodNode.accept(methodVisitor);
    traceClassVisitor.visitEnd();
  }
  
  public static void dumpClass(ClassNode paramClassNode) {
    ClassWriter classWriter = new ClassWriter(3);
    paramClassNode.accept((ClassVisitor)classWriter);
    dumpClass(classWriter.toByteArray());
  }
  
  public static void dumpClass(byte[] paramArrayOfbyte) {
    ClassReader classReader = new ClassReader(paramArrayOfbyte);
    CheckClassAdapter.verify(classReader, true, new PrintWriter(System.out));
  }
  
  public static void printMethodWithOpcodeIndices(MethodNode paramMethodNode) {
    System.err.printf("%s%s\n", new Object[] { paramMethodNode.name, paramMethodNode.desc });
    byte b = 0;
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      System.err.printf("[%4d] %s\n", new Object[] { Integer.valueOf(b++), describeNode(listIterator.next()) });
    } 
  }
  
  public static void printMethod(MethodNode paramMethodNode) {
    System.err.printf("%s%s\n", new Object[] { paramMethodNode.name, paramMethodNode.desc });
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      System.err.print("  ");
      printNode(listIterator.next());
    } 
  }
  
  public static void printNode(AbstractInsnNode paramAbstractInsnNode) {
    System.err.printf("%s\n", new Object[] { describeNode(paramAbstractInsnNode) });
  }
  
  public static String describeNode(AbstractInsnNode paramAbstractInsnNode) {
    return String.format("   %-14s ", new Object[] { "null" });
  }
  
  public static String getOpcodeName(AbstractInsnNode paramAbstractInsnNode) {}
  
  public static String getOpcodeName(int paramInt) {
    return getOpcodeName(paramInt, "UNINITIALIZED_THIS", 1);
  }
  
  private static String getOpcodeName(int paramInt1, String paramString, int paramInt2) {
    if (paramInt1 >= paramInt2) {
      boolean bool = false;
      for (Field field : Opcodes.class.getDeclaredFields()) {
        if (field.getName().equals(paramString)) {
          bool = true;
          if (field.getType() == int.class && field.getInt(null) == paramInt1)
            return field.getName(); 
        } 
      } 
    } 
  }
  
  public static boolean methodHasLineNumbers(MethodNode paramMethodNode) {
    ListIterator listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      if (listIterator.next() instanceof LineNumberNode)
        return true; 
    } 
    return false;
  }
  
  public static boolean methodIsStatic(MethodNode paramMethodNode) {
    return ((paramMethodNode.access & 0x8) == 8);
  }
  
  public static boolean fieldIsStatic(FieldNode paramFieldNode) {
    return ((paramFieldNode.access & 0x8) == 8);
  }
  
  public static int getFirstNonArgLocalIndex(MethodNode paramMethodNode) {
    return getFirstNonArgLocalIndex(Type.getArgumentTypes(paramMethodNode.desc), ((paramMethodNode.access & 0x8) == 0));
  }
  
  public static int getFirstNonArgLocalIndex(Type[] paramArrayOfType, boolean paramBoolean) {
    paramBoolean = false;
  }
  
  public static int getArgsSize(Type[] paramArrayOfType) {
    int i = 0;
    for (Type type : paramArrayOfType)
      i += type.getSize(); 
    return i;
  }
  
  public static void loadArgs(Type[] paramArrayOfType, InsnList paramInsnList, int paramInt) {
    loadArgs(paramArrayOfType, paramInsnList, paramInt, -1);
  }
  
  public static void loadArgs(Type[] paramArrayOfType, InsnList paramInsnList, int paramInt1, int paramInt2) {
    paramInt2 = -1;
    loadArgs(paramArrayOfType, paramInsnList, paramInt1, paramInt2, null);
  }
  
  public static void loadArgs(Type[] paramArrayOfType1, InsnList paramInsnList, int paramInt1, int paramInt2, Type[] paramArrayOfType2) {
    paramInt2 = -1;
    int i = paramInt1;
    byte b = 0;
    for (Type type : paramArrayOfType1) {
      paramInsnList.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), i));
      if (b < paramArrayOfType2.length && paramArrayOfType2[b] != null)
        paramInsnList.add((AbstractInsnNode)new TypeInsnNode(192, paramArrayOfType2[b].getInternalName())); 
      i += type.getSize();
      if (paramInt2 >= paramInt1 && i >= paramInt2)
        return; 
      b++;
    } 
  }
  
  public static Map cloneLabels(InsnList paramInsnList) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator();
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode instanceof LabelNode)
        hashMap.put(abstractInsnNode, new LabelNode(((LabelNode)abstractInsnNode).getLabel())); 
    } 
    return hashMap;
  }
  
  public static String generateDescriptor(Object paramObject, Object... paramVarArgs) {
    StringBuilder stringBuilder = (new StringBuilder()).append('(');
    for (Object object : paramVarArgs)
      stringBuilder.append(toDescriptor(object)); 
  }
  
  private static String toDescriptor(Object paramObject) {
    if (paramObject instanceof String)
      return (String)paramObject; 
    if (paramObject instanceof Type)
      return paramObject.toString(); 
    if (paramObject instanceof Class)
      return Type.getDescriptor((Class)paramObject); 
  }
  
  public static String getDescriptor(Type[] paramArrayOfType) {
    return "(" + Joiner.on("").join((Object[])paramArrayOfType) + ")";
  }
  
  public static String getDescriptor(Type[] paramArrayOfType, Type paramType) {
    return getDescriptor(paramArrayOfType) + paramType.toString();
  }
  
  public static String changeDescriptorReturnType(String paramString1, String paramString2) {
    return null;
  }
  
  public static String getSimpleName(Class paramClass) {
    return paramClass.getSimpleName();
  }
  
  public static String getSimpleName(AnnotationNode paramAnnotationNode) {
    return getSimpleName(paramAnnotationNode.desc);
  }
  
  public static String getSimpleName(String paramString) {
    int i = Math.max(paramString.lastIndexOf('/'), 0);
    return paramString.substring(i + 1).replace(";", "");
  }
  
  public static boolean isConstant(AbstractInsnNode paramAbstractInsnNode) {
    return false;
  }
  
  public static Object getConstant(AbstractInsnNode paramAbstractInsnNode) {
    return null;
  }
  
  public static Type getConstantType(AbstractInsnNode paramAbstractInsnNode) {
    return null;
  }
  
  public static boolean hasFlag(ClassNode paramClassNode, int paramInt) {
    return ((paramClassNode.access & paramInt) == paramInt);
  }
  
  public static boolean hasFlag(MethodNode paramMethodNode, int paramInt) {
    return ((paramMethodNode.access & paramInt) == paramInt);
  }
  
  public static boolean hasFlag(FieldNode paramFieldNode, int paramInt) {
    return ((paramFieldNode.access & paramInt) == paramInt);
  }
  
  public static boolean compareFlags(MethodNode paramMethodNode1, MethodNode paramMethodNode2, int paramInt) {
    paramInt = 8;
    return (hasFlag(paramMethodNode1, paramInt) == hasFlag(paramMethodNode2, paramInt));
  }
  
  public static boolean compareFlags(FieldNode paramFieldNode1, FieldNode paramFieldNode2, int paramInt) {
    paramInt = 8;
    return (hasFlag(paramFieldNode1, paramInt) == hasFlag(paramFieldNode2, paramInt));
  }
  
  public static Bytecode$Visibility getVisibility(MethodNode paramMethodNode) {
    return getVisibility(paramMethodNode.access & 0x7);
  }
  
  public static Bytecode$Visibility getVisibility(FieldNode paramFieldNode) {
    return getVisibility(paramFieldNode.access & 0x7);
  }
  
  private static Bytecode$Visibility getVisibility(int paramInt) {
    return ((paramInt & 0x4) != 0) ? Bytecode$Visibility.PROTECTED : (((paramInt & 0x2) != 0) ? Bytecode$Visibility.PRIVATE : (((paramInt & 0x1) != 0) ? Bytecode$Visibility.PUBLIC : Bytecode$Visibility.PACKAGE));
  }
  
  public static void setVisibility(MethodNode paramMethodNode, Bytecode$Visibility paramBytecode$Visibility) {
    int j = paramBytecode$Visibility.access;
    int i = paramMethodNode.access;
    paramMethodNode.access = i & 0xFFFFFFF8 | j & 0x7;
  }
  
  public static void setVisibility(FieldNode paramFieldNode, Bytecode$Visibility paramBytecode$Visibility) {
    int j = paramBytecode$Visibility.access;
    int i = paramFieldNode.access;
    paramFieldNode.access = i & 0xFFFFFFF8 | j & 0x7;
  }
  
  public static void setVisibility(MethodNode paramMethodNode, int paramInt) {
    int j = paramInt;
    int i = paramMethodNode.access;
    paramMethodNode.access = i & 0xFFFFFFF8 | j & 0x7;
  }
  
  public static void setVisibility(FieldNode paramFieldNode, int paramInt) {
    int j = paramInt;
    int i = paramFieldNode.access;
    paramFieldNode.access = i & 0xFFFFFFF8 | j & 0x7;
  }
  
  public static int getMaxLineNumber(ClassNode paramClassNode, int paramInt1, int paramInt2) {
    paramInt2 = 50;
    paramInt1 = 500;
    int i = 0;
    for (MethodNode methodNode : paramClassNode.methods) {
      ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
      while (listIterator.hasNext()) {
        AbstractInsnNode abstractInsnNode = listIterator.next();
        if (abstractInsnNode instanceof LineNumberNode)
          i = Math.max(i, ((LineNumberNode)abstractInsnNode).line); 
      } 
    } 
    return Math.max(paramInt1, i + paramInt2);
  }
  
  public static String getBoxingType(Type paramType) {}
  
  public static String getUnboxingMethod(Type paramType) {}
  
  public static void mergeAnnotations(ClassNode paramClassNode1, ClassNode paramClassNode2) {
    paramClassNode2.visibleAnnotations = mergeAnnotations(paramClassNode1.visibleAnnotations, paramClassNode2.visibleAnnotations, "class", paramClassNode1.name);
    paramClassNode2.invisibleAnnotations = mergeAnnotations(paramClassNode1.invisibleAnnotations, paramClassNode2.invisibleAnnotations, "class", paramClassNode1.name);
  }
  
  public static void mergeAnnotations(MethodNode paramMethodNode1, MethodNode paramMethodNode2) {
    paramMethodNode2.visibleAnnotations = mergeAnnotations(paramMethodNode1.visibleAnnotations, paramMethodNode2.visibleAnnotations, "method", paramMethodNode1.name);
    paramMethodNode2.invisibleAnnotations = mergeAnnotations(paramMethodNode1.invisibleAnnotations, paramMethodNode2.invisibleAnnotations, "method", paramMethodNode1.name);
  }
  
  public static void mergeAnnotations(FieldNode paramFieldNode1, FieldNode paramFieldNode2) {
    paramFieldNode2.visibleAnnotations = mergeAnnotations(paramFieldNode1.visibleAnnotations, paramFieldNode2.visibleAnnotations, "field", paramFieldNode1.name);
    paramFieldNode2.invisibleAnnotations = mergeAnnotations(paramFieldNode1.invisibleAnnotations, paramFieldNode2.invisibleAnnotations, "field", paramFieldNode1.name);
  }
  
  private static List mergeAnnotations(List paramList1, List paramList2, String paramString1, String paramString2) {
    return paramList2;
  }
  
  private static boolean isMergeableAnnotation(AnnotationNode paramAnnotationNode) {
    return paramAnnotationNode.desc.startsWith("L" + Constants.MIXIN_PACKAGE_REF) ? mergeableAnnotationPattern.matcher(paramAnnotationNode.desc).matches() : true;
  }
  
  private static Pattern getMergeableAnnotationPattern() {
    StringBuilder stringBuilder = new StringBuilder("^L(");
    for (byte b = 0; b < MERGEABLE_MIXIN_ANNOTATIONS.length; b++)
      stringBuilder.append(MERGEABLE_MIXIN_ANNOTATIONS[b].getName().replace('.', '/')); 
    return Pattern.compile(stringBuilder.append(");$").toString());
  }
  
  public static void compareBridgeMethods(MethodNode paramMethodNode1, MethodNode paramMethodNode2) {
    ListIterator<AbstractInsnNode> listIterator1 = paramMethodNode1.instructions.iterator();
    ListIterator<AbstractInsnNode> listIterator2 = paramMethodNode2.instructions.iterator();
    byte b;
    for (b = 0; listIterator1.hasNext() && listIterator2.hasNext(); b++) {
      AbstractInsnNode abstractInsnNode1 = listIterator1.next();
      AbstractInsnNode abstractInsnNode2 = listIterator2.next();
      if (!(abstractInsnNode1 instanceof LabelNode))
        if (abstractInsnNode1 instanceof MethodInsnNode) {
          MethodInsnNode methodInsnNode1 = (MethodInsnNode)abstractInsnNode1;
          MethodInsnNode methodInsnNode2 = (MethodInsnNode)abstractInsnNode2;
          if (!methodInsnNode1.name.equals(methodInsnNode2.name))
            throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_INVOKE_NAME, paramMethodNode1.name, paramMethodNode1.desc, b, abstractInsnNode1, abstractInsnNode2); 
          if (!methodInsnNode1.desc.equals(methodInsnNode2.desc))
            throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_INVOKE_DESC, paramMethodNode1.name, paramMethodNode1.desc, b, abstractInsnNode1, abstractInsnNode2); 
        } else {
          if (abstractInsnNode1.getOpcode() != abstractInsnNode2.getOpcode())
            throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_INSN, paramMethodNode1.name, paramMethodNode1.desc, b, abstractInsnNode1, abstractInsnNode2); 
          if (abstractInsnNode1 instanceof VarInsnNode) {
            VarInsnNode varInsnNode1 = (VarInsnNode)abstractInsnNode1;
            VarInsnNode varInsnNode2 = (VarInsnNode)abstractInsnNode2;
            if (varInsnNode1.var != varInsnNode2.var)
              throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_LOAD, paramMethodNode1.name, paramMethodNode1.desc, b, abstractInsnNode1, abstractInsnNode2); 
          } else if (abstractInsnNode1 instanceof TypeInsnNode) {
            TypeInsnNode typeInsnNode1 = (TypeInsnNode)abstractInsnNode1;
            TypeInsnNode typeInsnNode2 = (TypeInsnNode)abstractInsnNode2;
            if (typeInsnNode1.getOpcode() == 192 && !typeInsnNode1.desc.equals(typeInsnNode2.desc))
              throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_CAST, paramMethodNode1.name, paramMethodNode1.desc, b, abstractInsnNode1, abstractInsnNode2); 
          } 
        }  
    } 
    if (listIterator1.hasNext() || listIterator2.hasNext())
      throw new SyntheticBridgeException(SyntheticBridgeException.Problem.BAD_LENGTH, paramMethodNode1.name, paramMethodNode1.desc, b, null, null); 
  }
}
