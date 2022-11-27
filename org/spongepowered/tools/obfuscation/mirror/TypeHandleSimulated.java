package org.spongepowered.tools.obfuscation.mirror;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.util.SignaturePrinter;

public class TypeHandleSimulated extends TypeHandle {
  private final TypeElement simulatedType;
  
  public TypeHandleSimulated(String paramString, TypeMirror paramTypeMirror) {
    this(TypeUtils.getPackage(paramTypeMirror), paramString, paramTypeMirror);
  }
  
  public TypeHandleSimulated(PackageElement paramPackageElement, String paramString, TypeMirror paramTypeMirror) {
    super(paramPackageElement, paramString);
    this.simulatedType = (TypeElement)((DeclaredType)paramTypeMirror).asElement();
  }
  
  protected TypeElement getTargetElement() {
    return this.simulatedType;
  }
  
  public boolean isPublic() {
    return true;
  }
  
  public boolean isImaginary() {
    return false;
  }
  
  public boolean isSimulated() {
    return true;
  }
  
  public AnnotationHandle getAnnotation(Class paramClass) {
    return null;
  }
  
  public TypeHandle getSuperclass() {
    return null;
  }
  
  public String findDescriptor(MemberInfo paramMemberInfo) {}
  
  public FieldHandle findField(String paramString1, String paramString2, boolean paramBoolean) {
    return new FieldHandle(null, paramString1, paramString2);
  }
  
  public MethodHandle findMethod(String paramString1, String paramString2, boolean paramBoolean) {
    return new MethodHandle(null, paramString1, paramString2);
  }
  
  public MappingMethod getMappingMethod(String paramString1, String paramString2) {
    String str1 = (new SignaturePrinter(paramString1, paramString2)).setFullyQualified(true).toDescriptor();
    String str2 = TypeUtils.stripGenerics(str1);
    MethodHandle methodHandle = findMethodRecursive(this, paramString1, str1, str2, true);
  }
  
  private static MethodHandle findMethodRecursive(TypeHandle paramTypeHandle, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    TypeElement typeElement = paramTypeHandle.getTargetElement();
    return null;
  }
  
  private static MethodHandle findMethodRecursive(TypeMirror paramTypeMirror, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    if (!(paramTypeMirror instanceof DeclaredType))
      return null; 
    TypeElement typeElement = (TypeElement)((DeclaredType)paramTypeMirror).asElement();
    return findMethodRecursive(new TypeHandle(typeElement), paramString1, paramString2, paramString3, paramBoolean);
  }
}
