package org.spongepowered.asm.lib.util;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.lib.Attribute;
import org.spongepowered.asm.lib.Handle;
import org.spongepowered.asm.lib.Label;
import org.spongepowered.asm.lib.TypePath;

public class ASMifier extends Printer {
  protected final String name;
  
  protected final int id;
  
  protected Map labelNames;
  
  private static final int ACCESS_CLASS = 262144;
  
  private static final int ACCESS_FIELD = 524288;
  
  private static final int ACCESS_INNER = 1048576;
  
  public ASMifier() {
    this(327680, "cw", 0);
    if (getClass() != ASMifier.class)
      throw new IllegalStateException(); 
  }
  
  protected ASMifier(int paramInt1, String paramString, int paramInt2) {
    super(paramInt1);
    this.name = paramString;
    this.id = paramInt2;
  }
  
  public static void main(String[] paramArrayOfString) throws Exception {
    boolean bool1 = false;
    byte b = 2;
    boolean bool2 = true;
    if (paramArrayOfString.length < 1 || paramArrayOfString.length > 2)
      bool2 = false; 
    System.err.println("Prints the ASM code to generate the given class.");
    System.err.println("Usage: ASMifier [-debug] <fully qualified class name or class file name>");
  }
  
  public void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    String str;
    int i = paramString1.lastIndexOf('/');
    if (i == -1) {
      str = paramString1;
    } else {
      this.text.add("package asm." + paramString1.substring(0, i).replace('/', '.') + ";\n");
      str = paramString1.substring(i + 1);
    } 
    this.text.add("import java.util.*;\n");
    this.text.add("import org.objectweb.asm.*;\n");
    this.text.add("public class " + str + "Dump implements Opcodes {\n\n");
    this.text.add("public static byte[] dump () throws Exception {\n\n");
    this.text.add("ClassWriter cw = new ClassWriter(0);\n");
    this.text.add("FieldVisitor fv;\n");
    this.text.add("MethodVisitor mv;\n");
    this.text.add("AnnotationVisitor av0;\n\n");
    this.buf.setLength(0);
    this.buf.append("cw.visit(");
    switch (paramInt1) {
      case 196653:
        this.buf.append("V1_1");
        break;
      case 46:
        this.buf.append("V1_2");
        break;
      case 47:
        this.buf.append("V1_3");
        break;
      case 48:
        this.buf.append("V1_4");
        break;
      case 49:
        this.buf.append("V1_5");
        break;
      case 50:
        this.buf.append("V1_6");
        break;
      case 51:
        this.buf.append("V1_7");
        break;
      default:
        this.buf.append(paramInt1);
        break;
    } 
    this.buf.append(", ");
    appendAccess(paramInt2 | 0x40000);
    this.buf.append(", ");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(", ");
    if (paramArrayOfString.length > 0) {
      this.buf.append("new String[] {");
      for (byte b = 0; b < paramArrayOfString.length; b++) {
        this.buf.append(" ");
        appendConstant(paramArrayOfString[b]);
      } 
      this.buf.append(" }");
    } else {
      this.buf.append("null");
    } 
    this.buf.append(");\n\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitSource(String paramString1, String paramString2) {
    this.buf.setLength(0);
    this.buf.append("cw.visitSource(");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(");\n\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitOuterClass(String paramString1, String paramString2, String paramString3) {
    this.buf.setLength(0);
    this.buf.append("cw.visitOuterClass(");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(");\n\n");
    this.text.add(this.buf.toString());
  }
  
  public ASMifier visitClassAnnotation(String paramString, boolean paramBoolean) {
    return visitAnnotation(paramString, paramBoolean);
  }
  
  public ASMifier visitClassTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public void visitClassAttribute(Attribute paramAttribute) {
    visitAttribute(paramAttribute);
  }
  
  public void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt) {
    this.buf.setLength(0);
    this.buf.append("cw.visitInnerClass(");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(", ");
    appendAccess(paramInt | 0x100000);
    this.buf.append(");\n\n");
    this.text.add(this.buf.toString());
  }
  
  public ASMifier visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject) {
    this.buf.setLength(0);
    this.buf.append("{\n");
    this.buf.append("fv = cw.visitField(");
    appendAccess(paramInt | 0x80000);
    this.buf.append(", ");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(", ");
    appendConstant(paramObject);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("fv", 0);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public ASMifier visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    this.buf.setLength(0);
    this.buf.append("{\n");
    this.buf.append("mv = cw.visitMethod(");
    appendAccess(paramInt);
    this.buf.append(", ");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(", ");
    if (paramArrayOfString.length > 0) {
      this.buf.append("new String[] {");
      for (byte b = 0; b < paramArrayOfString.length; b++) {
        this.buf.append(" ");
        appendConstant(paramArrayOfString[b]);
      } 
      this.buf.append(" }");
    } else {
      this.buf.append("null");
    } 
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("mv", 0);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public void visitClassEnd() {
    this.text.add("cw.visitEnd();\n\n");
    this.text.add("return cw.toByteArray();\n");
    this.text.add("}\n");
    this.text.add("}\n");
  }
  
  public void visit(String paramString, Object paramObject) {
    this.buf.setLength(0);
    this.buf.append("av").append(this.id).append(".visit(");
    appendConstant(this.buf, paramString);
    this.buf.append(", ");
    appendConstant(this.buf, paramObject);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitEnum(String paramString1, String paramString2, String paramString3) {
    this.buf.setLength(0);
    this.buf.append("av").append(this.id).append(".visitEnum(");
    appendConstant(this.buf, paramString1);
    this.buf.append(", ");
    appendConstant(this.buf, paramString2);
    this.buf.append(", ");
    appendConstant(this.buf, paramString3);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public ASMifier visitAnnotation(String paramString1, String paramString2) {
    this.buf.setLength(0);
    this.buf.append("{\n");
    this.buf.append("AnnotationVisitor av").append(this.id + 1).append(" = av");
    this.buf.append(this.id).append(".visitAnnotation(");
    appendConstant(this.buf, paramString1);
    this.buf.append(", ");
    appendConstant(this.buf, paramString2);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("av", this.id + 1);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public ASMifier visitArray(String paramString) {
    this.buf.setLength(0);
    this.buf.append("{\n");
    this.buf.append("AnnotationVisitor av").append(this.id + 1).append(" = av");
    this.buf.append(this.id).append(".visitArray(");
    appendConstant(this.buf, paramString);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("av", this.id + 1);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public void visitAnnotationEnd() {
    this.buf.setLength(0);
    this.buf.append("av").append(this.id).append(".visitEnd();\n");
    this.text.add(this.buf.toString());
  }
  
  public ASMifier visitFieldAnnotation(String paramString, boolean paramBoolean) {
    return visitAnnotation(paramString, paramBoolean);
  }
  
  public ASMifier visitFieldTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public void visitFieldAttribute(Attribute paramAttribute) {
    visitAttribute(paramAttribute);
  }
  
  public void visitFieldEnd() {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitEnd();\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitParameter(String paramString, int paramInt) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitParameter(");
    appendString(this.buf, paramString);
    this.buf.append(", ");
    appendAccess(paramInt);
    this.text.add(this.buf.append(");\n").toString());
  }
  
  public ASMifier visitAnnotationDefault() {
    this.buf.setLength(0);
    this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitAnnotationDefault();\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("av", 0);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public ASMifier visitMethodAnnotation(String paramString, boolean paramBoolean) {
    return visitAnnotation(paramString, paramBoolean);
  }
  
  public ASMifier visitMethodTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public ASMifier visitParameterAnnotation(int paramInt, String paramString, boolean paramBoolean) {
    this.buf.setLength(0);
    this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitParameterAnnotation(").append(paramInt).append(", ");
    appendConstant(paramString);
    this.buf.append(", ").append(paramBoolean).append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("av", 0);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public void visitMethodAttribute(Attribute paramAttribute) {
    visitAttribute(paramAttribute);
  }
  
  public void visitCode() {
    this.text.add(this.name + ".visitCode();\n");
  }
  
  public void visitFrame(int paramInt1, int paramInt2, Object[] paramArrayOfObject1, int paramInt3, Object[] paramArrayOfObject2) {
    this.buf.setLength(0);
    switch (paramInt1) {
      case -1:
      case 0:
        declareFrameTypes(paramInt2, paramArrayOfObject1);
        declareFrameTypes(paramInt3, paramArrayOfObject2);
        if (paramInt1 == -1) {
          this.buf.append(this.name).append(".visitFrame(Opcodes.F_NEW, ");
        } else {
          this.buf.append(this.name).append(".visitFrame(Opcodes.F_FULL, ");
        } 
        this.buf.append(paramInt2).append(", new Object[] {");
        appendFrameTypes(paramInt2, paramArrayOfObject1);
        this.buf.append("}, ").append(paramInt3).append(", new Object[] {");
        appendFrameTypes(paramInt3, paramArrayOfObject2);
        this.buf.append('}');
        break;
      case 1:
        declareFrameTypes(paramInt2, paramArrayOfObject1);
        this.buf.append(this.name).append(".visitFrame(Opcodes.F_APPEND,").append(paramInt2).append(", new Object[] {");
        appendFrameTypes(paramInt2, paramArrayOfObject1);
        this.buf.append("}, 0, null");
        break;
      case 2:
        this.buf.append(this.name).append(".visitFrame(Opcodes.F_CHOP,").append(paramInt2).append(", null, 0, null");
        break;
      case 3:
        this.buf.append(this.name).append(".visitFrame(Opcodes.F_SAME, 0, null, 0, null");
        break;
      case 4:
        declareFrameTypes(1, paramArrayOfObject2);
        this.buf.append(this.name).append(".visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {");
        appendFrameTypes(1, paramArrayOfObject2);
        this.buf.append('}');
        break;
    } 
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitInsn(int paramInt) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitInsn(").append(OPCODES[paramInt]).append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitIntInsn(int paramInt1, int paramInt2) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitIntInsn(").append(OPCODES[paramInt1]).append(", ").append((paramInt1 == 188) ? TYPES[paramInt2] : Integer.toString(paramInt2)).append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitVarInsn(int paramInt1, int paramInt2) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitVarInsn(").append(OPCODES[paramInt1]).append(", ").append(paramInt2).append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitTypeInsn(int paramInt, String paramString) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitTypeInsn(").append(OPCODES[paramInt]).append(", ");
    appendConstant(paramString);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitFieldInsn(").append(OPCODES[paramInt]).append(", ");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
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
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitMethodInsn(").append(OPCODES[paramInt]).append(", ");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(", ");
  }
  
  public void visitInvokeDynamicInsn(String paramString1, String paramString2, Handle paramHandle, Object... paramVarArgs) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitInvokeDynamicInsn(");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramHandle);
    this.buf.append(", new Object[]{");
    for (byte b = 0; b < paramVarArgs.length; b++) {
      appendConstant(paramVarArgs[b]);
      if (b != paramVarArgs.length - 1)
        this.buf.append(", "); 
    } 
    this.buf.append("});\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitJumpInsn(int paramInt, Label paramLabel) {
    this.buf.setLength(0);
    declareLabel(paramLabel);
    this.buf.append(this.name).append(".visitJumpInsn(").append(OPCODES[paramInt]).append(", ");
    appendLabel(paramLabel);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitLabel(Label paramLabel) {
    this.buf.setLength(0);
    declareLabel(paramLabel);
    this.buf.append(this.name).append(".visitLabel(");
    appendLabel(paramLabel);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitLdcInsn(Object paramObject) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitLdcInsn(");
    appendConstant(paramObject);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitIincInsn(int paramInt1, int paramInt2) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitIincInsn(").append(paramInt1).append(", ").append(paramInt2).append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitTableSwitchInsn(int paramInt1, int paramInt2, Label paramLabel, Label... paramVarArgs) {
    this.buf.setLength(0);
    byte b;
    for (b = 0; b < paramVarArgs.length; b++)
      declareLabel(paramVarArgs[b]); 
    declareLabel(paramLabel);
    this.buf.append(this.name).append(".visitTableSwitchInsn(").append(paramInt1).append(", ").append(paramInt2).append(", ");
    appendLabel(paramLabel);
    this.buf.append(", new Label[] {");
    for (b = 0; b < paramVarArgs.length; b++) {
      this.buf.append(" ");
      appendLabel(paramVarArgs[b]);
    } 
    this.buf.append(" });\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitLookupSwitchInsn(Label paramLabel, int[] paramArrayOfint, Label[] paramArrayOfLabel) {
    this.buf.setLength(0);
    byte b;
    for (b = 0; b < paramArrayOfLabel.length; b++)
      declareLabel(paramArrayOfLabel[b]); 
    declareLabel(paramLabel);
    this.buf.append(this.name).append(".visitLookupSwitchInsn(");
    appendLabel(paramLabel);
    this.buf.append(", new int[] {");
    for (b = 0; b < paramArrayOfint.length; b++)
      this.buf.append(" ").append(paramArrayOfint[b]); 
    this.buf.append(" }, new Label[] {");
    for (b = 0; b < paramArrayOfLabel.length; b++) {
      this.buf.append(" ");
      appendLabel(paramArrayOfLabel[b]);
    } 
    this.buf.append(" });\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitMultiANewArrayInsn(String paramString, int paramInt) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitMultiANewArrayInsn(");
    appendConstant(paramString);
    this.buf.append(", ").append(paramInt).append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public ASMifier visitInsnAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitTypeAnnotation("visitInsnAnnotation", paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public void visitTryCatchBlock(Label paramLabel1, Label paramLabel2, Label paramLabel3, String paramString) {
    this.buf.setLength(0);
    declareLabel(paramLabel1);
    declareLabel(paramLabel2);
    declareLabel(paramLabel3);
    this.buf.append(this.name).append(".visitTryCatchBlock(");
    appendLabel(paramLabel1);
    this.buf.append(", ");
    appendLabel(paramLabel2);
    this.buf.append(", ");
    appendLabel(paramLabel3);
    this.buf.append(", ");
    appendConstant(paramString);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public ASMifier visitTryCatchAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitTypeAnnotation("visitTryCatchAnnotation", paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public void visitLocalVariable(String paramString1, String paramString2, String paramString3, Label paramLabel1, Label paramLabel2, int paramInt) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitLocalVariable(");
    appendConstant(paramString1);
    this.buf.append(", ");
    appendConstant(paramString2);
    this.buf.append(", ");
    appendConstant(paramString3);
    this.buf.append(", ");
    appendLabel(paramLabel1);
    this.buf.append(", ");
    appendLabel(paramLabel2);
    this.buf.append(", ").append(paramInt).append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public Printer visitLocalVariableAnnotation(int paramInt, TypePath paramTypePath, Label[] paramArrayOfLabel1, Label[] paramArrayOfLabel2, int[] paramArrayOfint, String paramString, boolean paramBoolean) {
    this.buf.setLength(0);
    this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitLocalVariableAnnotation(");
    this.buf.append(paramInt);
    this.buf.append(", null, ");
    this.buf.append("new Label[] {");
    byte b;
    for (b = 0; b < paramArrayOfLabel1.length; b++) {
      this.buf.append(" ");
      appendLabel(paramArrayOfLabel1[b]);
    } 
    this.buf.append(" }, new Label[] {");
    for (b = 0; b < paramArrayOfLabel2.length; b++) {
      this.buf.append(" ");
      appendLabel(paramArrayOfLabel2[b]);
    } 
    this.buf.append(" }, new int[] {");
    for (b = 0; b < paramArrayOfint.length; b++)
      this.buf.append(" ").append(paramArrayOfint[b]); 
    this.buf.append(" }, ");
    appendConstant(paramString);
    this.buf.append(", ").append(paramBoolean).append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("av", 0);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public void visitLineNumber(int paramInt, Label paramLabel) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitLineNumber(").append(paramInt).append(", ");
    appendLabel(paramLabel);
    this.buf.append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitMaxs(int paramInt1, int paramInt2) {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitMaxs(").append(paramInt1).append(", ").append(paramInt2).append(");\n");
    this.text.add(this.buf.toString());
  }
  
  public void visitMethodEnd() {
    this.buf.setLength(0);
    this.buf.append(this.name).append(".visitEnd();\n");
    this.text.add(this.buf.toString());
  }
  
  public ASMifier visitAnnotation(String paramString, boolean paramBoolean) {
    this.buf.setLength(0);
    this.buf.append("{\n").append("av0 = ").append(this.name).append(".visitAnnotation(");
    appendConstant(paramString);
    this.buf.append(", ").append(paramBoolean).append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("av", 0);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public ASMifier visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitTypeAnnotation("visitTypeAnnotation", paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public ASMifier visitTypeAnnotation(String paramString1, int paramInt, TypePath paramTypePath, String paramString2, boolean paramBoolean) {
    this.buf.setLength(0);
    this.buf.append("{\n").append("av0 = ").append(this.name).append(".").append(paramString1).append("(");
    this.buf.append(paramInt);
    this.buf.append(", null, ");
    appendConstant(paramString2);
    this.buf.append(", ").append(paramBoolean).append(");\n");
    this.text.add(this.buf.toString());
    ASMifier aSMifier = createASMifier("av", 0);
    this.text.add(aSMifier.getText());
    this.text.add("}\n");
    return aSMifier;
  }
  
  public void visitAttribute(Attribute paramAttribute) {
    this.buf.setLength(0);
    this.buf.append("// ATTRIBUTE ").append(paramAttribute.type).append('\n');
    if (paramAttribute instanceof ASMifiable) {
      if (this.labelNames == null)
        this.labelNames = new HashMap<Object, Object>(); 
      this.buf.append("{\n");
      ((ASMifiable)paramAttribute).asmify(this.buf, "attr", this.labelNames);
      this.buf.append(this.name).append(".visitAttribute(attr);\n");
      this.buf.append("}\n");
    } 
    this.text.add(this.buf.toString());
  }
  
  protected ASMifier createASMifier(String paramString, int paramInt) {
    return new ASMifier(327680, paramString, paramInt);
  }
  
  void appendAccess(int paramInt) {
    boolean bool = true;
    if ((paramInt & 0x1) != 0) {
      this.buf.append("ACC_PUBLIC");
      bool = false;
    } 
    if ((paramInt & 0x2) != 0) {
      this.buf.append("ACC_PRIVATE");
      bool = false;
    } 
    if ((paramInt & 0x4) != 0) {
      this.buf.append("ACC_PROTECTED");
      bool = false;
    } 
    if ((paramInt & 0x10) != 0) {
      this.buf.append("ACC_FINAL");
      bool = false;
    } 
    if ((paramInt & 0x8) != 0) {
      this.buf.append("ACC_STATIC");
      bool = false;
    } 
    if ((paramInt & 0x20) != 0) {
      if ((paramInt & 0x40000) == 0) {
        this.buf.append("ACC_SYNCHRONIZED");
      } else {
        this.buf.append("ACC_SUPER");
      } 
      bool = false;
    } 
    if ((paramInt & 0x40) != 0 && (paramInt & 0x80000) != 0) {
      this.buf.append("ACC_VOLATILE");
      bool = false;
    } 
    if ((paramInt & 0x40) != 0 && (paramInt & 0x40000) == 0 && (paramInt & 0x80000) == 0) {
      this.buf.append("ACC_BRIDGE");
      bool = false;
    } 
    if ((paramInt & 0x80) != 0 && (paramInt & 0x40000) == 0 && (paramInt & 0x80000) == 0) {
      this.buf.append("ACC_VARARGS");
      bool = false;
    } 
    if ((paramInt & 0x80) != 0 && (paramInt & 0x80000) != 0) {
      this.buf.append("ACC_TRANSIENT");
      bool = false;
    } 
    if ((paramInt & 0x100) != 0 && (paramInt & 0x40000) == 0 && (paramInt & 0x80000) == 0) {
      this.buf.append("ACC_NATIVE");
      bool = false;
    } 
    if ((paramInt & 0x4000) != 0 && ((paramInt & 0x40000) != 0 || (paramInt & 0x80000) != 0 || (paramInt & 0x100000) != 0)) {
      this.buf.append("ACC_ENUM");
      bool = false;
    } 
    if ((paramInt & 0x2000) != 0 && ((paramInt & 0x40000) != 0 || (paramInt & 0x100000) != 0)) {
      this.buf.append("ACC_ANNOTATION");
      bool = false;
    } 
    if ((paramInt & 0x400) != 0) {
      this.buf.append("ACC_ABSTRACT");
      bool = false;
    } 
    if ((paramInt & 0x200) != 0) {
      this.buf.append("ACC_INTERFACE");
      bool = false;
    } 
    if ((paramInt & 0x800) != 0) {
      this.buf.append("ACC_STRICT");
      bool = false;
    } 
    if ((paramInt & 0x1000) != 0) {
      this.buf.append("ACC_SYNTHETIC");
      bool = false;
    } 
    if ((paramInt & 0x20000) != 0) {
      this.buf.append("ACC_DEPRECATED");
      bool = false;
    } 
    if ((paramInt & 0x8000) != 0) {
      this.buf.append("ACC_MANDATED");
      bool = false;
    } 
    this.buf.append('0');
  }
  
  protected void appendConstant(Object paramObject) {
    appendConstant(this.buf, paramObject);
  }
  
  static void appendConstant(StringBuffer paramStringBuffer, Object paramObject) {
    paramStringBuffer.append("null");
  }
  
  private void declareFrameTypes(int paramInt, Object[] paramArrayOfObject) {
    for (byte b = 0; b < paramInt; b++) {
      if (paramArrayOfObject[b] instanceof Label)
        declareLabel((Label)paramArrayOfObject[b]); 
    } 
  }
  
  private void appendFrameTypes(int paramInt, Object[] paramArrayOfObject) {
    for (byte b = 0; b < paramInt; b++) {
      if (paramArrayOfObject[b] instanceof String) {
        appendConstant(paramArrayOfObject[b]);
      } else if (paramArrayOfObject[b] instanceof Integer) {
        switch (((Integer)paramArrayOfObject[b]).intValue()) {
          case 0:
            this.buf.append("Opcodes.TOP");
            break;
          case 1:
            this.buf.append("Opcodes.INTEGER");
            break;
          case 2:
            this.buf.append("Opcodes.FLOAT");
            break;
          case 3:
            this.buf.append("Opcodes.DOUBLE");
            break;
          case 4:
            this.buf.append("Opcodes.LONG");
            break;
          case 5:
            this.buf.append("Opcodes.NULL");
            break;
          case 6:
            this.buf.append("Opcodes.UNINITIALIZED_THIS");
            break;
        } 
      } else {
        appendLabel((Label)paramArrayOfObject[b]);
      } 
    } 
  }
  
  protected void declareLabel(Label paramLabel) {
    if (this.labelNames == null)
      this.labelNames = new HashMap<Object, Object>(); 
    String str = (String)this.labelNames.get(paramLabel);
    str = "l" + this.labelNames.size();
    this.labelNames.put(paramLabel, str);
    this.buf.append("Label ").append(str).append(" = new Label();\n");
  }
  
  protected void appendLabel(Label paramLabel) {
    this.buf.append((String)this.labelNames.get(paramLabel));
  }
  
  public Printer visitTryCatchAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitTryCatchAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public Printer visitInsnAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitInsnAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public Printer visitParameterAnnotation(int paramInt, String paramString, boolean paramBoolean) {
    return visitParameterAnnotation(paramInt, paramString, paramBoolean);
  }
  
  public Printer visitMethodTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitMethodTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public Printer visitMethodAnnotation(String paramString, boolean paramBoolean) {
    return visitMethodAnnotation(paramString, paramBoolean);
  }
  
  public Printer visitAnnotationDefault() {
    return visitAnnotationDefault();
  }
  
  public Printer visitFieldTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitFieldTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public Printer visitFieldAnnotation(String paramString, boolean paramBoolean) {
    return visitFieldAnnotation(paramString, paramBoolean);
  }
  
  public Printer visitArray(String paramString) {
    return visitArray(paramString);
  }
  
  public Printer visitAnnotation(String paramString1, String paramString2) {
    return visitAnnotation(paramString1, paramString2);
  }
  
  public Printer visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    return visitMethod(paramInt, paramString1, paramString2, paramString3, paramArrayOfString);
  }
  
  public Printer visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject) {
    return visitField(paramInt, paramString1, paramString2, paramString3, paramObject);
  }
  
  public Printer visitClassTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    return visitClassTypeAnnotation(paramInt, paramTypePath, paramString, paramBoolean);
  }
  
  public Printer visitClassAnnotation(String paramString, boolean paramBoolean) {
    return visitClassAnnotation(paramString, paramBoolean);
  }
}
