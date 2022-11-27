package org.spongepowered.tools.obfuscation.mirror;

import com.google.common.base.Strings;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;

public class FieldHandle extends MemberHandle {
  private final VariableElement element;
  
  private final boolean rawType;
  
  public FieldHandle(TypeElement paramTypeElement, VariableElement paramVariableElement) {
    this(TypeUtils.getInternalName(paramTypeElement), paramVariableElement);
  }
  
  public FieldHandle(String paramString, VariableElement paramVariableElement) {
    this(paramString, paramVariableElement, false);
  }
  
  public FieldHandle(TypeElement paramTypeElement, VariableElement paramVariableElement, boolean paramBoolean) {
    this(TypeUtils.getInternalName(paramTypeElement), paramVariableElement, paramBoolean);
  }
  
  public FieldHandle(String paramString, VariableElement paramVariableElement, boolean paramBoolean) {
    this(paramString, paramVariableElement, paramBoolean, TypeUtils.getName(paramVariableElement), TypeUtils.getInternalName(paramVariableElement));
  }
  
  public FieldHandle(String paramString1, String paramString2, String paramString3) {
    this(paramString1, (VariableElement)null, false, paramString2, paramString3);
  }
  
  private FieldHandle(String paramString1, VariableElement paramVariableElement, boolean paramBoolean, String paramString2, String paramString3) {
    super(paramString1, paramString2, paramString3);
    this.element = paramVariableElement;
    this.rawType = paramBoolean;
  }
  
  public boolean isImaginary() {
    return (this.element == null);
  }
  
  public VariableElement getElement() {
    return this.element;
  }
  
  public Visibility getVisibility() {
    return TypeUtils.getVisibility(this.element);
  }
  
  public boolean isRawType() {
    return this.rawType;
  }
  
  public MappingField asMapping(boolean paramBoolean) {}
  
  public String toString() {
    String str1 = (getOwner() != null) ? ("L" + getOwner() + ";") : "";
    String str2 = Strings.nullToEmpty(getName());
    String str3 = Strings.nullToEmpty(getDesc());
    return String.format("%s%s:%s", new Object[] { str1, str2, str3 });
  }
  
  public IMapping asMapping(boolean paramBoolean) {
    return (IMapping)asMapping(paramBoolean);
  }
}
