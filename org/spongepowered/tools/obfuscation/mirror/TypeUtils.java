package org.spongepowered.tools.obfuscation.mirror;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import org.spongepowered.asm.util.SignaturePrinter;

public abstract class TypeUtils {
  private static final int MAX_GENERIC_RECURSION_DEPTH = 5;
  
  private static final String OBJECT_SIG = "java.lang.Object";
  
  private static final String OBJECT_REF = "java/lang/Object";
  
  public static PackageElement getPackage(TypeMirror paramTypeMirror) {
    return !(paramTypeMirror instanceof DeclaredType) ? null : getPackage((TypeElement)((DeclaredType)paramTypeMirror).asElement());
  }
  
  public static PackageElement getPackage(TypeElement paramTypeElement) {
    Element element = paramTypeElement.getEnclosingElement();
    while (true) {
      if (!(element instanceof PackageElement)) {
        element = element.getEnclosingElement();
        continue;
      } 
      return (PackageElement)element;
    } 
  }
  
  public static String getElementType(Element paramElement) {
    return (paramElement instanceof TypeElement) ? "TypeElement" : ((paramElement instanceof ExecutableElement) ? "ExecutableElement" : ((paramElement instanceof VariableElement) ? "VariableElement" : ((paramElement instanceof PackageElement) ? "PackageElement" : ((paramElement instanceof javax.lang.model.element.TypeParameterElement) ? "TypeParameterElement" : paramElement.getClass().getSimpleName()))));
  }
  
  public static String stripGenerics(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < paramString.length()) {
      char c = paramString.charAt(b1);
      if (c == '<')
        b2++; 
      stringBuilder.append(c);
      b1++;
    } 
    return stringBuilder.toString();
  }
  
  public static String getName(VariableElement paramVariableElement) {}
  
  public static String getName(ExecutableElement paramExecutableElement) {}
  
  public static String getJavaSignature(Element paramElement) {
    if (paramElement instanceof ExecutableElement) {
      ExecutableElement executableElement = (ExecutableElement)paramElement;
      StringBuilder stringBuilder = (new StringBuilder()).append("(");
      boolean bool = false;
      for (VariableElement variableElement : executableElement.getParameters()) {
        stringBuilder.append(getTypeName(variableElement.asType()));
        bool = true;
      } 
      stringBuilder.append(')').append(getTypeName(executableElement.getReturnType()));
      return stringBuilder.toString();
    } 
    return getTypeName(paramElement.asType());
  }
  
  public static String getJavaSignature(String paramString) {
    return (new SignaturePrinter("", paramString)).setFullyQualified(true).toDescriptor();
  }
  
  public static String getTypeName(TypeMirror paramTypeMirror) {
    switch (TypeUtils$1.$SwitchMap$javax$lang$model$type$TypeKind[paramTypeMirror.getKind().ordinal()]) {
      case 1:
        return getTypeName(((ArrayType)paramTypeMirror).getComponentType()) + "[]";
      case 2:
        return getTypeName((DeclaredType)paramTypeMirror);
      case 3:
        return getTypeName(getUpperBound(paramTypeMirror));
      case 4:
        return "java.lang.Object";
    } 
    return paramTypeMirror.toString();
  }
  
  public static String getTypeName(DeclaredType paramDeclaredType) {
    return "java.lang.Object";
  }
  
  public static String getDescriptor(Element paramElement) {
    return (paramElement instanceof ExecutableElement) ? getDescriptor((ExecutableElement)paramElement) : ((paramElement instanceof VariableElement) ? getInternalName((VariableElement)paramElement) : getInternalName(paramElement.asType()));
  }
  
  public static String getDescriptor(ExecutableElement paramExecutableElement) {
    return null;
  }
  
  public static String getInternalName(VariableElement paramVariableElement) {
    return null;
  }
  
  public static String getInternalName(TypeMirror paramTypeMirror) {
    switch (TypeUtils$1.$SwitchMap$javax$lang$model$type$TypeKind[paramTypeMirror.getKind().ordinal()]) {
      case 1:
        return "[" + getInternalName(((ArrayType)paramTypeMirror).getComponentType());
      case 2:
        return "L" + getInternalName((DeclaredType)paramTypeMirror) + ";";
      case 3:
        return "L" + getInternalName(getUpperBound(paramTypeMirror)) + ";";
      case 5:
        return "Z";
      case 6:
        return "B";
      case 7:
        return "C";
      case 8:
        return "D";
      case 9:
        return "F";
      case 10:
        return "I";
      case 11:
        return "J";
      case 12:
        return "S";
      case 13:
        return "V";
      case 4:
        return "Ljava/lang/Object;";
    } 
    throw new IllegalArgumentException("Unable to parse type symbol " + paramTypeMirror + " with " + paramTypeMirror.getKind() + " to equivalent bytecode type");
  }
  
  public static String getInternalName(DeclaredType paramDeclaredType) {
    return "java/lang/Object";
  }
  
  public static String getInternalName(TypeElement paramTypeElement) {
    return null;
  }
  
  private static DeclaredType getUpperBound(TypeMirror paramTypeMirror) {
    return getUpperBound0(paramTypeMirror, 5);
  }
  
  private static DeclaredType getUpperBound0(TypeMirror paramTypeMirror, int paramInt) {
    paramInt = 5;
    throw new IllegalStateException("Generic symbol \"" + paramTypeMirror + "\" is too complex, exceeded " + '\005' + " iterations attempting to determine upper bound");
  }
  
  public static boolean isAssignable(ProcessingEnvironment paramProcessingEnvironment, TypeMirror paramTypeMirror1, TypeMirror paramTypeMirror2) {
    boolean bool = paramProcessingEnvironment.getTypeUtils().isAssignable(paramTypeMirror1, paramTypeMirror2);
    if (paramTypeMirror1 instanceof DeclaredType && paramTypeMirror2 instanceof DeclaredType) {
      TypeMirror typeMirror1 = toRawType(paramProcessingEnvironment, (DeclaredType)paramTypeMirror1);
      TypeMirror typeMirror2 = toRawType(paramProcessingEnvironment, (DeclaredType)paramTypeMirror2);
      return paramProcessingEnvironment.getTypeUtils().isAssignable(typeMirror1, typeMirror2);
    } 
    return bool;
  }
  
  private static TypeMirror toRawType(ProcessingEnvironment paramProcessingEnvironment, DeclaredType paramDeclaredType) {
    return paramProcessingEnvironment.getElementUtils().getTypeElement(((TypeElement)paramDeclaredType.asElement()).getQualifiedName()).asType();
  }
  
  public static Visibility getVisibility(Element paramElement) {
    return null;
  }
}
