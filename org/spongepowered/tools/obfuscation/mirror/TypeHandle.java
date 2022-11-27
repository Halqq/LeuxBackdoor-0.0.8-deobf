package org.spongepowered.tools.obfuscation.mirror;

import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.mirror.mapping.ResolvableMappingMethod;

public class TypeHandle {
  private final String name;
  
  private final PackageElement pkg;
  
  private final TypeElement element;
  
  private TypeReference reference;
  
  public TypeHandle(PackageElement paramPackageElement, String paramString) {
    this.name = paramString.replace('.', '/');
    this.pkg = paramPackageElement;
    this.element = null;
  }
  
  public TypeHandle(TypeElement paramTypeElement) {
    this.pkg = TypeUtils.getPackage(paramTypeElement);
    this.name = TypeUtils.getInternalName(paramTypeElement);
    this.element = paramTypeElement;
  }
  
  public TypeHandle(DeclaredType paramDeclaredType) {
    this((TypeElement)paramDeclaredType.asElement());
  }
  
  public final String toString() {
    return this.name.replace('/', '.');
  }
  
  public final String getName() {
    return this.name;
  }
  
  public final PackageElement getPackage() {
    return this.pkg;
  }
  
  public final TypeElement getElement() {
    return this.element;
  }
  
  protected TypeElement getTargetElement() {
    return this.element;
  }
  
  public AnnotationHandle getAnnotation(Class paramClass) {
    return AnnotationHandle.of(getTargetElement(), paramClass);
  }
  
  public final List getEnclosedElements() {
    return getEnclosedElements(getTargetElement());
  }
  
  public List getEnclosedElements(ElementKind... paramVarArgs) {
    return getEnclosedElements(getTargetElement(), paramVarArgs);
  }
  
  public TypeMirror getType() {
    return (getTargetElement() != null) ? getTargetElement().asType() : null;
  }
  
  public TypeHandle getSuperclass() {
    TypeElement typeElement = getTargetElement();
    return null;
  }
  
  public List getInterfaces() {
    if (getTargetElement() == null)
      return Collections.emptyList(); 
    ImmutableList.Builder builder = ImmutableList.builder();
    for (TypeMirror typeMirror : getTargetElement().getInterfaces())
      builder.add(new TypeHandle((DeclaredType)typeMirror)); 
    return (List)builder.build();
  }
  
  public boolean isPublic() {
    return (getTargetElement() != null && getTargetElement().getModifiers().contains(Modifier.PUBLIC));
  }
  
  public boolean isImaginary() {
    return (getTargetElement() == null);
  }
  
  public boolean isSimulated() {
    return false;
  }
  
  public final TypeReference getReference() {
    if (this.reference == null)
      this.reference = new TypeReference(this); 
    return this.reference;
  }
  
  public MappingMethod getMappingMethod(String paramString1, String paramString2) {
    return (MappingMethod)new ResolvableMappingMethod(this, paramString1, paramString2);
  }
  
  public String findDescriptor(MemberInfo paramMemberInfo) {
    String str = paramMemberInfo.desc;
    for (ExecutableElement executableElement : getEnclosedElements(new ElementKind[] { ElementKind.METHOD })) {
      if (executableElement.getSimpleName().toString().equals(paramMemberInfo.name)) {
        str = TypeUtils.getDescriptor(executableElement);
        break;
      } 
    } 
    return str;
  }
  
  public final FieldHandle findField(VariableElement paramVariableElement) {
    return findField(paramVariableElement, true);
  }
  
  public final FieldHandle findField(VariableElement paramVariableElement, boolean paramBoolean) {
    paramBoolean = true;
    return findField(paramVariableElement.getSimpleName().toString(), TypeUtils.getTypeName(paramVariableElement.asType()), paramBoolean);
  }
  
  public final FieldHandle findField(String paramString1, String paramString2) {
    return findField(paramString1, paramString2, true);
  }
  
  public FieldHandle findField(String paramString1, String paramString2, boolean paramBoolean) {
    String str = TypeUtils.stripGenerics(paramString2);
    for (VariableElement variableElement : getEnclosedElements(new ElementKind[] { ElementKind.FIELD })) {
      if (compareElement(variableElement, paramString1, paramString2, paramBoolean))
        return new FieldHandle(getTargetElement(), variableElement); 
      if (compareElement(variableElement, paramString1, str, paramBoolean))
        return new FieldHandle(getTargetElement(), variableElement, true); 
    } 
    return null;
  }
  
  public final MethodHandle findMethod(ExecutableElement paramExecutableElement) {
    return findMethod(paramExecutableElement, true);
  }
  
  public final MethodHandle findMethod(ExecutableElement paramExecutableElement, boolean paramBoolean) {
    paramBoolean = true;
    return findMethod(paramExecutableElement.getSimpleName().toString(), TypeUtils.getJavaSignature(paramExecutableElement), paramBoolean);
  }
  
  public final MethodHandle findMethod(String paramString1, String paramString2) {
    return findMethod(paramString1, paramString2, true);
  }
  
  public MethodHandle findMethod(String paramString1, String paramString2, boolean paramBoolean) {
    String str = TypeUtils.stripGenerics(paramString2);
    return findMethod(this, paramString1, paramString2, str, paramBoolean);
  }
  
  protected static MethodHandle findMethod(TypeHandle paramTypeHandle, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    for (ExecutableElement executableElement : getEnclosedElements(paramTypeHandle.getTargetElement(), new ElementKind[] { ElementKind.CONSTRUCTOR, ElementKind.METHOD })) {
      if (compareElement(executableElement, paramString1, paramString2, paramBoolean) || compareElement(executableElement, paramString1, paramString3, paramBoolean))
        return new MethodHandle(paramTypeHandle, executableElement); 
    } 
    return null;
  }
  
  protected static boolean compareElement(Element paramElement, String paramString1, String paramString2, boolean paramBoolean) {
    String str1 = paramElement.getSimpleName().toString();
    String str2 = TypeUtils.getJavaSignature(paramElement);
    String str3 = TypeUtils.stripGenerics(str2);
  }
  
  protected static List getEnclosedElements(TypeElement paramTypeElement, ElementKind... paramVarArgs) {
    return (paramVarArgs.length < 1) ? getEnclosedElements(paramTypeElement) : Collections.emptyList();
  }
  
  protected static List getEnclosedElements(TypeElement paramTypeElement) {}
}
