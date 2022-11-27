package org.spongepowered.asm.lib.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.AnnotationVisitor;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.TypePath;

public class CheckMethodAdapter extends MethodVisitor {
  public int version;
  
  private int access;
  
  private boolean startCode;
  
  private boolean endCode;
  
  private boolean endMethod;
  
  private int insnCount;
  
  private final Map labels;
  
  private Set usedLabels;
  
  private int expandedFrames;
  
  private int compressedFrames;
  
  private int lastFrame = -1;
  
  private List handlers;
  
  private static final int[] TYPE;
  
  private static Field labelStatusField;
  
  public CheckMethodAdapter(MethodVisitor paramMethodVisitor) {
    this(paramMethodVisitor, new HashMap<Object, Object>());
  }
  
  public CheckMethodAdapter(MethodVisitor paramMethodVisitor, Map paramMap) {
    this(327680, paramMethodVisitor, paramMap);
    if (getClass() != CheckMethodAdapter.class)
      throw new IllegalStateException(); 
  }
  
  protected CheckMethodAdapter(int paramInt, MethodVisitor paramMethodVisitor, Map paramMap) {
    super(paramInt, paramMethodVisitor);
    this.labels = paramMap;
    this.usedLabels = new HashSet();
    this.handlers = new ArrayList();
  }
  
  public CheckMethodAdapter(int paramInt, String paramString1, String paramString2, MethodVisitor paramMethodVisitor, Map paramMap) {
    this((MethodVisitor)new CheckMethodAdapter$1(327680, paramInt, paramString1, paramString2, null, null, paramMethodVisitor), paramMap);
    this.access = paramInt;
  }
  
  public void visitParameter(String paramString, int paramInt) {
    checkUnqualifiedName(this.version, paramString, "name");
    CheckClassAdapter.checkAccess(paramInt, 36880);
    super.visitParameter(paramString, paramInt);
  }
  
  public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
    checkEndMethod();
    checkDesc(paramString, false);
    return new CheckAnnotationAdapter(super.visitAnnotation(paramString, paramBoolean));
  }
  
  public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    checkEndMethod();
    int i = paramInt >>> 24;
    if (i != 1 && i != 18 && i != 20 && i != 21 && i != 22 && i != 23)
      throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i)); 
    CheckClassAdapter.checkTypeRefAndPath(paramInt, paramTypePath);
    checkDesc(paramString, false);
    return new CheckAnnotationAdapter(super.visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean));
  }
  
  public AnnotationVisitor visitAnnotationDefault() {
    checkEndMethod();
    return new CheckAnnotationAdapter(super.visitAnnotationDefault(), false);
  }
  
  public AnnotationVisitor visitParameterAnnotation(int paramInt, String paramString, boolean paramBoolean) {
    checkEndMethod();
    checkDesc(paramString, false);
    return new CheckAnnotationAdapter(super.visitParameterAnnotation(paramInt, paramString, paramBoolean));
  }
  
  public void visitAttribute(Attribute paramAttribute) {
    checkEndMethod();
    throw new IllegalArgumentException("Invalid attribute (must not be null)");
  }
  
  public void visitCode() {
    if ((this.access & 0x400) != 0)
      throw new RuntimeException("Abstract methods cannot have code"); 
    this.startCode = true;
    super.visitCode();
  }
  
  public void visitFrame(int paramInt1, int paramInt2, Object[] paramArrayOfObject1, int paramInt3, Object[] paramArrayOfObject2) {
    int i;
    int j;
    if (this.insnCount == this.lastFrame)
      throw new IllegalStateException("At most one frame can be visited at a given code location."); 
    this.lastFrame = this.insnCount;
    switch (paramInt1) {
      case -1:
      case 0:
        i = Integer.MAX_VALUE;
        j = Integer.MAX_VALUE;
        break;
      case 3:
        i = 0;
        j = 0;
        break;
      case 4:
        i = 0;
        j = 1;
        break;
      case 1:
      case 2:
        i = 3;
        j = 0;
        break;
      default:
        throw new IllegalArgumentException("Invalid frame type " + paramInt1);
    } 
    if (paramInt2 > i)
      throw new IllegalArgumentException("Invalid nLocal=" + paramInt2 + " for frame type " + paramInt1); 
    if (paramInt3 > j)
      throw new IllegalArgumentException("Invalid nStack=" + paramInt3 + " for frame type " + paramInt1); 
    if (paramInt1 != 2) {
      if (paramArrayOfObject1.length < paramInt2)
        throw new IllegalArgumentException("Array local[] is shorter than nLocal"); 
      for (byte b1 = 0; b1 < paramInt2; b1++)
        checkFrameValue(paramArrayOfObject1[b1]); 
    } 
    if (paramArrayOfObject2.length < paramInt3)
      throw new IllegalArgumentException("Array stack[] is shorter than nStack"); 
    for (byte b = 0; b < paramInt3; b++)
      checkFrameValue(paramArrayOfObject2[b]); 
    if (paramInt1 == -1) {
      this.expandedFrames++;
    } else {
      this.compressedFrames++;
    } 
    if (this.expandedFrames > 0 && this.compressedFrames > 0)
      throw new RuntimeException("Expanded and compressed frames must not be mixed."); 
    super.visitFrame(paramInt1, paramInt2, paramArrayOfObject1, paramInt3, paramArrayOfObject2);
  }
  
  public void visitInsn(int paramInt) {
    checkStartCode();
    checkEndCode();
    checkOpcode(paramInt, 0);
    super.visitInsn(paramInt);
    this.insnCount++;
  }
  
  public void visitIntInsn(int paramInt1, int paramInt2) {
    checkStartCode();
    checkEndCode();
    checkOpcode(paramInt1, 1);
    switch (paramInt1) {
      case 16:
        checkSignedByte(paramInt2, "Invalid operand");
        break;
      case 17:
        checkSignedShort(paramInt2, "Invalid operand");
        break;
      default:
        if (paramInt2 < 4 || paramInt2 > 11)
          throw new IllegalArgumentException("Invalid operand (must be an array type code T_...): " + paramInt2); 
        break;
    } 
    super.visitIntInsn(paramInt1, paramInt2);
    this.insnCount++;
  }
  
  public void visitVarInsn(int paramInt1, int paramInt2) {
    checkStartCode();
    checkEndCode();
    checkOpcode(paramInt1, 2);
    checkUnsignedShort(paramInt2, "Invalid variable index");
    super.visitVarInsn(paramInt1, paramInt2);
    this.insnCount++;
  }
  
  public void visitTypeInsn(int paramInt, String paramString) {
    checkStartCode();
    checkEndCode();
    checkOpcode(paramInt, 3);
    checkInternalName(paramString, "type");
    if (paramInt == 187 && paramString.charAt(0) == '[')
      throw new IllegalArgumentException("NEW cannot be used to create arrays: " + paramString); 
    super.visitTypeInsn(paramInt, paramString);
    this.insnCount++;
  }
  
  public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
    checkStartCode();
    checkEndCode();
    checkOpcode(paramInt, 4);
    checkInternalName(paramString1, "owner");
    checkUnqualifiedName(this.version, paramString2, "name");
    checkDesc(paramString3, false);
    super.visitFieldInsn(paramInt, paramString1, paramString2, paramString3);
    this.insnCount++;
  }
  
  @Deprecated
  @Deprecated
  public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
    if (this.api >= 327680) {
      super.visitMethodInsn(paramInt, paramString1, paramString2, paramString3);
      return;
    } 
    doVisitMethodInsn(paramInt, paramString1, paramString2, paramString3, (paramInt == 185));
  }
  
  public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    if (this.api < 327680) {
      super.visitMethodInsn(paramInt, paramString1, paramString2, paramString3, paramBoolean);
      return;
    } 
    doVisitMethodInsn(paramInt, paramString1, paramString2, paramString3, paramBoolean);
  }
  
  private void doVisitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    checkStartCode();
    checkEndCode();
    checkOpcode(paramInt, 5);
    if (paramInt != 183 || !"<init>".equals(paramString2))
      checkMethodIdentifier(this.version, paramString2, "name"); 
    checkInternalName(paramString1, "owner");
    checkMethodDesc(paramString3);
    if (paramInt == 182)
      throw new IllegalArgumentException("INVOKEVIRTUAL can't be used with interfaces"); 
    if (paramInt == 185)
      throw new IllegalArgumentException("INVOKEINTERFACE can't be used with classes"); 
    if (paramInt == 183)
      if ((this.version & 0xFFFF) < 52)
        throw new IllegalArgumentException("INVOKESPECIAL can't be used with interfaces prior to Java 8");  
    if (this.mv != null)
      this.mv.visitMethodInsn(paramInt, paramString1, paramString2, paramString3, paramBoolean); 
    this.insnCount++;
  }
  
  public void visitInvokeDynamicInsn(String paramString1, String paramString2, Handle paramHandle, Object... paramVarArgs) {
    checkStartCode();
    checkEndCode();
    checkMethodIdentifier(this.version, paramString1, "name");
    checkMethodDesc(paramString2);
    if (paramHandle.getTag() != 6 && paramHandle.getTag() != 8)
      throw new IllegalArgumentException("invalid handle tag " + paramHandle.getTag()); 
    for (byte b = 0; b < paramVarArgs.length; b++)
      checkLDCConstant(paramVarArgs[b]); 
    super.visitInvokeDynamicInsn(paramString1, paramString2, paramHandle, paramVarArgs);
    this.insnCount++;
  }
  
  public void visitJumpInsn(int paramInt, Label paramLabel) {
    checkStartCode();
    checkEndCode();
    checkOpcode(paramInt, 6);
    checkLabel(paramLabel, false, "label");
    checkNonDebugLabel(paramLabel);
    super.visitJumpInsn(paramInt, paramLabel);
    this.usedLabels.add(paramLabel);
    this.insnCount++;
  }
  
  public void visitLabel(Label paramLabel) {
    checkStartCode();
    checkEndCode();
    checkLabel(paramLabel, false, "label");
    if (this.labels.get(paramLabel) != null)
      throw new IllegalArgumentException("Already visited label"); 
    this.labels.put(paramLabel, Integer.valueOf(this.insnCount));
    super.visitLabel(paramLabel);
  }
  
  public void visitLdcInsn(Object paramObject) {
    checkStartCode();
    checkEndCode();
    checkLDCConstant(paramObject);
    super.visitLdcInsn(paramObject);
    this.insnCount++;
  }
  
  public void visitIincInsn(int paramInt1, int paramInt2) {
    checkStartCode();
    checkEndCode();
    checkUnsignedShort(paramInt1, "Invalid variable index");
    checkSignedShort(paramInt2, "Invalid increment");
    super.visitIincInsn(paramInt1, paramInt2);
    this.insnCount++;
  }
  
  public void visitTableSwitchInsn(int paramInt1, int paramInt2, Label paramLabel, Label... paramVarArgs) {
    checkStartCode();
    checkEndCode();
    if (paramInt2 < paramInt1)
      throw new IllegalArgumentException("Max = " + paramInt2 + " must be greater than or equal to min = " + paramInt1); 
    checkLabel(paramLabel, false, "default label");
    checkNonDebugLabel(paramLabel);
    if (paramVarArgs.length != paramInt2 - paramInt1 + 1)
      throw new IllegalArgumentException("There must be max - min + 1 labels"); 
    byte b;
    for (b = 0; b < paramVarArgs.length; b++) {
      checkLabel(paramVarArgs[b], false, "label at index " + b);
      checkNonDebugLabel(paramVarArgs[b]);
    } 
    super.visitTableSwitchInsn(paramInt1, paramInt2, paramLabel, paramVarArgs);
    for (b = 0; b < paramVarArgs.length; b++)
      this.usedLabels.add(paramVarArgs[b]); 
    this.insnCount++;
  }
  
  public void visitLookupSwitchInsn(Label paramLabel, int[] paramArrayOfint, Label[] paramArrayOfLabel) {
    checkEndCode();
    checkStartCode();
    checkLabel(paramLabel, false, "default label");
    checkNonDebugLabel(paramLabel);
    if (paramArrayOfint.length != paramArrayOfLabel.length)
      throw new IllegalArgumentException("There must be the same number of keys and labels"); 
    byte b;
    for (b = 0; b < paramArrayOfLabel.length; b++) {
      checkLabel(paramArrayOfLabel[b], false, "label at index " + b);
      checkNonDebugLabel(paramArrayOfLabel[b]);
    } 
    super.visitLookupSwitchInsn(paramLabel, paramArrayOfint, paramArrayOfLabel);
    this.usedLabels.add(paramLabel);
    for (b = 0; b < paramArrayOfLabel.length; b++)
      this.usedLabels.add(paramArrayOfLabel[b]); 
    this.insnCount++;
  }
  
  public void visitMultiANewArrayInsn(String paramString, int paramInt) {
    checkStartCode();
    checkEndCode();
    checkDesc(paramString, false);
    if (paramString.charAt(0) != '[')
      throw new IllegalArgumentException("Invalid descriptor (must be an array type descriptor): " + paramString); 
    if (paramInt < 1)
      throw new IllegalArgumentException("Invalid dimensions (must be greater than 0): " + paramInt); 
    if (paramInt > paramString.lastIndexOf('[') + 1)
      throw new IllegalArgumentException("Invalid dimensions (must not be greater than dims(desc)): " + paramInt); 
    super.visitMultiANewArrayInsn(paramString, paramInt);
    this.insnCount++;
  }
  
  public AnnotationVisitor visitInsnAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    checkStartCode();
    checkEndCode();
    int i = paramInt >>> 24;
    if (i != 67 && i != 68 && i != 69 && i != 70 && i != 71 && i != 72 && i != 73 && i != 74 && i != 75)
      throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i)); 
    CheckClassAdapter.checkTypeRefAndPath(paramInt, paramTypePath);
    checkDesc(paramString, false);
    return new CheckAnnotationAdapter(super.visitInsnAnnotation(paramInt, paramTypePath, paramString, paramBoolean));
  }
  
  public void visitTryCatchBlock(Label paramLabel1, Label paramLabel2, Label paramLabel3, String paramString) {
    checkStartCode();
    checkEndCode();
    checkLabel(paramLabel1, false, "start label");
    checkLabel(paramLabel2, false, "end label");
    checkLabel(paramLabel3, false, "handler label");
    checkNonDebugLabel(paramLabel1);
    checkNonDebugLabel(paramLabel2);
    checkNonDebugLabel(paramLabel3);
    if (this.labels.get(paramLabel1) != null || this.labels.get(paramLabel2) != null || this.labels.get(paramLabel3) != null)
      throw new IllegalStateException("Try catch blocks must be visited before their labels"); 
    checkInternalName(paramString, "type");
    super.visitTryCatchBlock(paramLabel1, paramLabel2, paramLabel3, paramString);
    this.handlers.add(paramLabel1);
    this.handlers.add(paramLabel2);
  }
  
  public AnnotationVisitor visitTryCatchAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    checkStartCode();
    checkEndCode();
    int i = paramInt >>> 24;
    if (i != 66)
      throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i)); 
    CheckClassAdapter.checkTypeRefAndPath(paramInt, paramTypePath);
    checkDesc(paramString, false);
    return new CheckAnnotationAdapter(super.visitTryCatchAnnotation(paramInt, paramTypePath, paramString, paramBoolean));
  }
  
  public void visitLocalVariable(String paramString1, String paramString2, String paramString3, Label paramLabel1, Label paramLabel2, int paramInt) {
    checkStartCode();
    checkEndCode();
    checkUnqualifiedName(this.version, paramString1, "name");
    checkDesc(paramString2, false);
    checkLabel(paramLabel1, true, "start label");
    checkLabel(paramLabel2, true, "end label");
    checkUnsignedShort(paramInt, "Invalid variable index");
    int i = ((Integer)this.labels.get(paramLabel1)).intValue();
    int j = ((Integer)this.labels.get(paramLabel2)).intValue();
    if (j < i)
      throw new IllegalArgumentException("Invalid start and end labels (end must be greater than start)"); 
    super.visitLocalVariable(paramString1, paramString2, paramString3, paramLabel1, paramLabel2, paramInt);
  }
  
  public AnnotationVisitor visitLocalVariableAnnotation(int paramInt, TypePath paramTypePath, Label[] paramArrayOfLabel1, Label[] paramArrayOfLabel2, int[] paramArrayOfint, String paramString, boolean paramBoolean) {
    checkStartCode();
    checkEndCode();
    int i = paramInt >>> 24;
    if (i != 64 && i != 65)
      throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i)); 
    CheckClassAdapter.checkTypeRefAndPath(paramInt, paramTypePath);
    checkDesc(paramString, false);
    if (paramArrayOfLabel2.length != paramArrayOfLabel1.length || paramArrayOfint.length != paramArrayOfLabel1.length)
      throw new IllegalArgumentException("Invalid start, end and index arrays (must be non null and of identical length"); 
    for (byte b = 0; b < paramArrayOfLabel1.length; b++) {
      checkLabel(paramArrayOfLabel1[b], true, "start label");
      checkLabel(paramArrayOfLabel2[b], true, "end label");
      checkUnsignedShort(paramArrayOfint[b], "Invalid variable index");
      int j = ((Integer)this.labels.get(paramArrayOfLabel1[b])).intValue();
      int k = ((Integer)this.labels.get(paramArrayOfLabel2[b])).intValue();
      if (k < j)
        throw new IllegalArgumentException("Invalid start and end labels (end must be greater than start)"); 
    } 
    return super.visitLocalVariableAnnotation(paramInt, paramTypePath, paramArrayOfLabel1, paramArrayOfLabel2, paramArrayOfint, paramString, paramBoolean);
  }
  
  public void visitLineNumber(int paramInt, Label paramLabel) {
    checkStartCode();
    checkEndCode();
    checkUnsignedShort(paramInt, "Invalid line number");
    checkLabel(paramLabel, true, "start label");
    super.visitLineNumber(paramInt, paramLabel);
  }
  
  public void visitMaxs(int paramInt1, int paramInt2) {
    checkStartCode();
    checkEndCode();
    this.endCode = true;
    for (Label label : this.usedLabels) {
      if (this.labels.get(label) == null)
        throw new IllegalStateException("Undefined label used"); 
    } 
    byte b = 0;
    if (b < this.handlers.size()) {
      Integer integer1 = (Integer)this.labels.get(this.handlers.get(b++));
      Integer integer2 = (Integer)this.labels.get(this.handlers.get(b++));
      throw new IllegalStateException("Undefined try catch block labels");
    } 
    checkUnsignedShort(paramInt1, "Invalid max stack");
    checkUnsignedShort(paramInt2, "Invalid max locals");
    super.visitMaxs(paramInt1, paramInt2);
  }
  
  public void visitEnd() {
    checkEndMethod();
    this.endMethod = true;
    super.visitEnd();
  }
  
  void checkStartCode() {
    if (!this.startCode)
      throw new IllegalStateException("Cannot visit instructions before visitCode has been called."); 
  }
  
  void checkEndCode() {
    if (this.endCode)
      throw new IllegalStateException("Cannot visit instructions after visitMaxs has been called."); 
  }
  
  void checkEndMethod() {
    if (this.endMethod)
      throw new IllegalStateException("Cannot visit elements after visitEnd has been called."); 
  }
  
  void checkFrameValue(Object paramObject) {
    if (paramObject == Opcodes.TOP || paramObject == Opcodes.INTEGER || paramObject == Opcodes.FLOAT || paramObject == Opcodes.LONG || paramObject == Opcodes.DOUBLE || paramObject == Opcodes.NULL || paramObject == Opcodes.UNINITIALIZED_THIS)
      return; 
    if (paramObject instanceof String) {
      checkInternalName((String)paramObject, "Invalid stack frame value");
      return;
    } 
    if (!(paramObject instanceof Label))
      throw new IllegalArgumentException("Invalid stack frame value: " + paramObject); 
    this.usedLabels.add((Label)paramObject);
  }
  
  static void checkOpcode(int paramInt1, int paramInt2) {
    if (paramInt1 > 199 || TYPE[paramInt1] != paramInt2)
      throw new IllegalArgumentException("Invalid opcode: " + paramInt1); 
  }
  
  static void checkSignedByte(int paramInt, String paramString) {
    paramString = "Invalid operand";
    if (paramInt < -128 || paramInt > 127)
      throw new IllegalArgumentException(paramString + " (must be a signed byte): " + paramInt); 
  }
  
  static void checkSignedShort(int paramInt, String paramString) {
    if (paramInt < -32768 || paramInt > 32767)
      throw new IllegalArgumentException(paramString + " (must be a signed short): " + paramInt); 
  }
  
  static void checkUnsignedShort(int paramInt, String paramString) {
    if (paramInt > 65535)
      throw new IllegalArgumentException(paramString + " (must be an unsigned short): " + paramInt); 
  }
  
  static void checkConstant(Object paramObject) {
    if (!(paramObject instanceof Integer) && !(paramObject instanceof Float) && !(paramObject instanceof Long) && !(paramObject instanceof Double) && !(paramObject instanceof String))
      throw new IllegalArgumentException("Invalid constant: " + paramObject); 
  }
  
  void checkLDCConstant(Object paramObject) {
    if (paramObject instanceof Type) {
      int i = ((Type)paramObject).getSort();
      if (i != 10 && i != 9 && i != 11)
        throw new IllegalArgumentException("Illegal LDC constant value"); 
      if (i != 11 && (this.version & 0xFFFF) < 49)
        throw new IllegalArgumentException("ldc of a constant class requires at least version 1.5"); 
      if (i == 11 && (this.version & 0xFFFF) < 51)
        throw new IllegalArgumentException("ldc of a method type requires at least version 1.7"); 
    } else if (paramObject instanceof Handle) {
      if ((this.version & 0xFFFF) < 51)
        throw new IllegalArgumentException("ldc of a handle requires at least version 1.7"); 
      int i = ((Handle)paramObject).getTag();
      if (i < 1 || i > 9)
        throw new IllegalArgumentException("invalid handle tag " + i); 
    } else {
      checkConstant(paramObject);
    } 
  }
  
  static void checkUnqualifiedName(int paramInt, String paramString1, String paramString2) {
    if ((paramInt & 0xFFFF) < 49) {
      checkIdentifier(paramString1, paramString2);
    } else {
      for (byte b = 0; b < paramString1.length(); b++) {
        if (".;[/".indexOf(paramString1.charAt(b)) != -1)
          throw new IllegalArgumentException("Invalid " + paramString2 + " (must be a valid unqualified name): " + paramString1); 
      } 
    } 
  }
  
  static void checkIdentifier(String paramString1, String paramString2) {
    checkIdentifier(paramString1, 0, -1, paramString2);
  }
  
  static void checkIdentifier(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    if ((paramInt2 == -1) ? (paramString1.length() <= paramInt1) : (paramInt2 <= paramInt1))
      throw new IllegalArgumentException("Invalid " + paramString2 + " (must not be null or empty)"); 
    if (!Character.isJavaIdentifierStart(paramString1.charAt(paramInt1)))
      throw new IllegalArgumentException("Invalid " + paramString2 + " (must be a valid Java identifier): " + paramString1); 
    int i = (paramInt2 == -1) ? paramString1.length() : paramInt2;
    for (int j = paramInt1 + 1; j < i; j++) {
      if (!Character.isJavaIdentifierPart(paramString1.charAt(j)))
        throw new IllegalArgumentException("Invalid " + paramString2 + " (must be a valid Java identifier): " + paramString1); 
    } 
  }
  
  static void checkMethodIdentifier(int paramInt, String paramString1, String paramString2) {
    if (paramString1.length() == 0)
      throw new IllegalArgumentException("Invalid " + paramString2 + " (must not be null or empty)"); 
    if ((paramInt & 0xFFFF) >= 49) {
      for (byte b1 = 0; b1 < paramString1.length(); b1++) {
        if (".;[/<>".indexOf(paramString1.charAt(b1)) != -1)
          throw new IllegalArgumentException("Invalid " + paramString2 + " (must be a valid unqualified name): " + paramString1); 
      } 
      return;
    } 
    if (!Character.isJavaIdentifierStart(paramString1.charAt(0)))
      throw new IllegalArgumentException("Invalid " + paramString2 + " (must be a '<init>', '<clinit>' or a valid Java identifier): " + paramString1); 
    for (byte b = 1; b < paramString1.length(); b++) {
      if (!Character.isJavaIdentifierPart(paramString1.charAt(b)))
        throw new IllegalArgumentException("Invalid " + paramString2 + " (must be '<init>' or '<clinit>' or a valid Java identifier): " + paramString1); 
    } 
  }
  
  static void checkInternalName(String paramString1, String paramString2) {
    if (paramString1.length() == 0)
      throw new IllegalArgumentException("Invalid " + paramString2 + " (must not be null or empty)"); 
    if (paramString1.charAt(0) == '[') {
      checkDesc(paramString1, false);
    } else {
      checkInternalName(paramString1, 0, -1, paramString2);
    } 
  }
  
  static void checkInternalName(String paramString1, int paramInt1, int paramInt2, String paramString2) {
    int k;
    int i = (paramInt2 == -1) ? paramString1.length() : paramInt2;
    int j = paramInt1;
    do {
      k = paramString1.indexOf('/', j + 1);
      if (k == -1 || k > i)
        k = i; 
      checkIdentifier(paramString1, j, k, (String)null);
      j = k + 1;
    } while (k != i);
  }
  
  static void checkDesc(String paramString, boolean paramBoolean) {
    paramBoolean = false;
    int i = checkDesc(paramString, 0, paramBoolean);
    if (i != paramString.length())
      throw new IllegalArgumentException("Invalid descriptor: " + paramString); 
  }
  
  static int checkDesc(String paramString, int paramInt, boolean paramBoolean) {
    int i;
    if (paramInt >= paramString.length())
      throw new IllegalArgumentException("Invalid type descriptor (must not be null or empty)"); 
    switch (paramString.charAt(paramInt)) {
      case 'V':
        return paramInt + 1;
      case 'B':
      case 'C':
      case 'D':
      case 'F':
      case 'I':
      case 'J':
      case 'S':
      case 'Z':
        return paramInt + 1;
      case '[':
        for (i = paramInt + 1; i < paramString.length() && paramString.charAt(i) == '['; i++);
        if (i < paramString.length())
          return checkDesc(paramString, i, false); 
        throw new IllegalArgumentException("Invalid descriptor: " + paramString);
      case 'L':
        i = paramString.indexOf(';', paramInt);
        if (i == -1 || i - paramInt < 2)
          throw new IllegalArgumentException("Invalid descriptor: " + paramString); 
        checkInternalName(paramString, paramInt + 1, i, (String)null);
        return i + 1;
    } 
    throw new IllegalArgumentException("Invalid descriptor: " + paramString);
  }
  
  static void checkMethodDesc(String paramString) {
    if (paramString.length() == 0)
      throw new IllegalArgumentException("Invalid method descriptor (must not be null or empty)"); 
    if (paramString.charAt(0) != '(' || paramString.length() < 3)
      throw new IllegalArgumentException("Invalid descriptor: " + paramString); 
    int i = 1;
    if (paramString.charAt(i) != ')')
      do {
        if (paramString.charAt(i) == 'V')
          throw new IllegalArgumentException("Invalid descriptor: " + paramString); 
        i = checkDesc(paramString, i, false);
      } while (i < paramString.length() && paramString.charAt(i) != ')'); 
    i = checkDesc(paramString, i + 1, true);
    if (i != paramString.length())
      throw new IllegalArgumentException("Invalid descriptor: " + paramString); 
  }
  
  void checkLabel(Label paramLabel, boolean paramBoolean, String paramString) {
    throw new IllegalArgumentException("Invalid " + paramString + " (must not be null)");
  }
  
  private static void checkNonDebugLabel(Label paramLabel) {
    Field field = getLabelStatusField();
    boolean bool = false;
  }
  
  private static Field getLabelStatusField() {
    if (labelStatusField == null) {
      labelStatusField = getLabelField("a");
      if (labelStatusField == null)
        labelStatusField = getLabelField("status"); 
    } 
    return labelStatusField;
  }
  
  private static Field getLabelField(String paramString) {
    Field field = Label.class.getDeclaredField(paramString);
    field.setAccessible(true);
    return field;
  }
  
  static {
    String str = "BBBBBBBBBBBBBBBBCCIAADDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBDDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBJBBBBBBBBBBBBBBBBBBBBHHHHHHHHHHHHHHHHDKLBBBBBBFFFFGGGGAECEBBEEBBAMHHAA";
    TYPE = new int[str.length()];
    for (byte b = 0; b < TYPE.length; b++)
      TYPE[b] = str.charAt(b) - 65 - 1; 
  }
}
