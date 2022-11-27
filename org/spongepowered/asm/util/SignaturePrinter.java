package org.spongepowered.asm.util;

import com.google.common.base.Strings;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;

public class SignaturePrinter {
  private final String name;
  
  private final Type returnType;
  
  private final Type[] argTypes;
  
  private final String[] argNames;
  
  private String modifiers = "private void";
  
  private boolean fullyQualified;
  
  public SignaturePrinter(MethodNode paramMethodNode) {
    this(paramMethodNode.name, Type.VOID_TYPE, Type.getArgumentTypes(paramMethodNode.desc));
    setModifiers(paramMethodNode);
  }
  
  public SignaturePrinter(MethodNode paramMethodNode, String[] paramArrayOfString) {
    this(paramMethodNode.name, Type.VOID_TYPE, Type.getArgumentTypes(paramMethodNode.desc), paramArrayOfString);
    setModifiers(paramMethodNode);
  }
  
  public SignaturePrinter(MemberInfo paramMemberInfo) {
    this(paramMemberInfo.name, paramMemberInfo.desc);
  }
  
  public SignaturePrinter(String paramString1, String paramString2) {
    this(paramString1, Type.getReturnType(paramString2), Type.getArgumentTypes(paramString2));
  }
  
  public SignaturePrinter(String paramString, Type paramType, Type[] paramArrayOfType) {
    this.name = paramString;
    this.returnType = paramType;
    this.argTypes = new Type[paramArrayOfType.length];
    this.argNames = new String[paramArrayOfType.length];
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < paramArrayOfType.length) {
      if (paramArrayOfType[b1] != null) {
        this.argTypes[b1] = paramArrayOfType[b1];
        this.argNames[b1] = "var" + b2++;
      } 
      b1++;
    } 
  }
  
  public SignaturePrinter(String paramString, Type paramType, LocalVariableNode[] paramArrayOfLocalVariableNode) {
    this.name = paramString;
    this.returnType = paramType;
    this.argTypes = new Type[paramArrayOfLocalVariableNode.length];
    this.argNames = new String[paramArrayOfLocalVariableNode.length];
    for (byte b = 0; b < paramArrayOfLocalVariableNode.length; b++) {
      if (paramArrayOfLocalVariableNode[b] != null) {
        this.argTypes[b] = Type.getType((paramArrayOfLocalVariableNode[b]).desc);
        this.argNames[b] = (paramArrayOfLocalVariableNode[b]).name;
      } 
    } 
  }
  
  public SignaturePrinter(String paramString, Type paramType, Type[] paramArrayOfType, String[] paramArrayOfString) {
    this.name = paramString;
    this.returnType = paramType;
    this.argTypes = paramArrayOfType;
    this.argNames = paramArrayOfString;
    if (this.argTypes.length > this.argNames.length)
      throw new IllegalArgumentException(String.format("Types array length must not exceed names array length! (names=%d, types=%d)", new Object[] { Integer.valueOf(this.argNames.length), Integer.valueOf(this.argTypes.length) })); 
  }
  
  public String getFormattedArgs() {
    return appendArgs(new StringBuilder(), true, true).toString();
  }
  
  public String getReturnType() {
    return getTypeName(this.returnType, false, this.fullyQualified);
  }
  
  public void setModifiers(MethodNode paramMethodNode) {
    String str = getTypeName(Type.getReturnType(paramMethodNode.desc), false, this.fullyQualified);
    if ((paramMethodNode.access & 0x1) != 0) {
      setModifiers("public " + str);
    } else if ((paramMethodNode.access & 0x4) != 0) {
      setModifiers("protected " + str);
    } else if ((paramMethodNode.access & 0x2) != 0) {
      setModifiers("private " + str);
    } else {
      setModifiers(str);
    } 
  }
  
  public SignaturePrinter setModifiers(String paramString) {
    this.modifiers = paramString.replace("${returnType}", getReturnType());
    return this;
  }
  
  public SignaturePrinter setFullyQualified(boolean paramBoolean) {
    paramBoolean = true;
    this.fullyQualified = paramBoolean;
    return this;
  }
  
  public boolean isFullyQualified() {
    return this.fullyQualified;
  }
  
  public String toString() {
    return appendArgs((new StringBuilder()).append(this.modifiers).append(" ").append(this.name), false, true).toString();
  }
  
  public String toDescriptor() {
    StringBuilder stringBuilder = appendArgs(new StringBuilder(), true, false);
    return stringBuilder.append(getTypeName(this.returnType, false, this.fullyQualified)).toString();
  }
  
  private StringBuilder appendArgs(StringBuilder paramStringBuilder, boolean paramBoolean1, boolean paramBoolean2) {
    paramStringBuilder.append('(');
    for (byte b = 0; b < this.argTypes.length; b++) {
      if (this.argTypes[b] != null);
    } 
    return paramStringBuilder.append(")");
  }
  
  private StringBuilder appendType(StringBuilder paramStringBuilder, Type paramType, String paramString) {
    switch (paramType.getSort()) {
      case 9:
        return appendArraySuffix(appendType(paramStringBuilder, paramType.getElementType(), paramString), paramType);
      case 10:
        return appendType(paramStringBuilder, paramType.getClassName(), paramString);
    } 
    paramStringBuilder.append(getTypeName(paramType, false, this.fullyQualified));
    paramStringBuilder.append(' ').append(paramString);
    return paramStringBuilder;
  }
  
  private StringBuilder appendType(StringBuilder paramStringBuilder, String paramString1, String paramString2) {
    if (!this.fullyQualified)
      paramString1 = paramString1.substring(paramString1.lastIndexOf('.') + 1); 
    paramStringBuilder.append(paramString1);
    if (paramString1.endsWith("CallbackInfoReturnable"))
      paramStringBuilder.append('<').append(getTypeName(this.returnType, true, this.fullyQualified)).append('>'); 
    paramStringBuilder.append(' ').append(paramString2);
    return paramStringBuilder;
  }
  
  public static String getTypeName(Type paramType, boolean paramBoolean) {
    paramBoolean = false;
    return getTypeName(paramType, paramBoolean, false);
  }
  
  public static String getTypeName(Type paramType, boolean paramBoolean1, boolean paramBoolean2) {
    switch (paramType.getSort()) {
      case 0:
      
      case 1:
      
      case 2:
      
      case 3:
      
      case 4:
      
      case 5:
      
      case 6:
      
      case 7:
      
      case 8:
      
      case 9:
        return getTypeName(paramType.getElementType(), paramBoolean1, paramBoolean2) + arraySuffix(paramType);
      case 10:
        null = paramType.getClassName();
        return null.substring(null.lastIndexOf('.') + 1);
    } 
    return "Object";
  }
  
  private static String arraySuffix(Type paramType) {
    return Strings.repeat("[]", paramType.getDimensions());
  }
  
  private static StringBuilder appendArraySuffix(StringBuilder paramStringBuilder, Type paramType) {
    for (byte b = 0; b < paramType.getDimensions(); b++)
      paramStringBuilder.append("[]"); 
    return paramStringBuilder;
  }
}
