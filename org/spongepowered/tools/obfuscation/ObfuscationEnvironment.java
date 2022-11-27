package org.spongepowered.tools.obfuscation;

import java.io.File;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.util.ObfuscationUtil;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationEnvironment;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;
import org.spongepowered.tools.obfuscation.mapping.IMappingProvider;
import org.spongepowered.tools.obfuscation.mapping.IMappingWriter;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;

public abstract class ObfuscationEnvironment implements IObfuscationEnvironment {
  protected final ObfuscationType type;
  
  protected final IMappingProvider mappingProvider;
  
  protected final IMappingWriter mappingWriter;
  
  protected final ObfuscationEnvironment$RemapperProxy remapper = new ObfuscationEnvironment$RemapperProxy(this);
  
  protected final IMixinAnnotationProcessor ap;
  
  protected final String outFileName;
  
  protected final List inFileNames;
  
  private boolean initDone;
  
  protected ObfuscationEnvironment(ObfuscationType paramObfuscationType) {
    this.type = paramObfuscationType;
    this.ap = paramObfuscationType.getAnnotationProcessor();
    this.inFileNames = paramObfuscationType.getInputFileNames();
    this.outFileName = paramObfuscationType.getOutputFileName();
    this.mappingProvider = getMappingProvider((Messager)this.ap, this.ap.getProcessingEnvironment().getFiler());
    this.mappingWriter = getMappingWriter((Messager)this.ap, this.ap.getProcessingEnvironment().getFiler());
  }
  
  public String toString() {
    return this.type.toString();
  }
  
  protected abstract IMappingProvider getMappingProvider(Messager paramMessager, Filer paramFiler);
  
  protected abstract IMappingWriter getMappingWriter(Messager paramMessager, Filer paramFiler);
  
  private boolean initMappings() {
    if (!this.initDone) {
      this.initDone = true;
      if (this.inFileNames == null) {
        this.ap.printMessage(Diagnostic.Kind.ERROR, "The " + this.type.getConfig().getInputFileOption() + " argument was not supplied, obfuscation processing will not occur");
        return false;
      } 
      byte b = 0;
      for (String str : this.inFileNames) {
        File file = new File(str);
        if (file.isFile()) {
          this.ap.printMessage(Diagnostic.Kind.NOTE, "Loading " + this.type + " mappings from " + file.getAbsolutePath());
          this.mappingProvider.read(file);
          b++;
        } 
      } 
      true;
      this.ap.printMessage(Diagnostic.Kind.ERROR, "No valid input files for " + this.type + " could be read, processing may not be sucessful.");
      this.mappingProvider.clear();
    } 
    return !this.mappingProvider.isEmpty();
  }
  
  public ObfuscationType getType() {
    return this.type;
  }
  
  public MappingMethod getObfMethod(MemberInfo paramMemberInfo) {
    MappingMethod mappingMethod = getObfMethod(paramMemberInfo.asMethodMapping());
    if (!paramMemberInfo.isFullyQualified())
      return mappingMethod; 
    TypeHandle typeHandle = this.ap.getTypeProvider().getTypeHandle(paramMemberInfo.owner);
    if (typeHandle.isImaginary())
      return null; 
    TypeMirror typeMirror = typeHandle.getElement().getSuperclass();
    if (typeMirror.getKind() != TypeKind.DECLARED)
      return null; 
    String str = ((TypeElement)((DeclaredType)typeMirror).asElement()).getQualifiedName().toString();
    return getObfMethod(new MemberInfo(paramMemberInfo.name, str.replace('.', '/'), paramMemberInfo.desc, paramMemberInfo.matchAll));
  }
  
  public MappingMethod getObfMethod(MappingMethod paramMappingMethod) {
    return getObfMethod(paramMappingMethod, true);
  }
  
  public MappingMethod getObfMethod(MappingMethod paramMappingMethod, boolean paramBoolean) {
    paramBoolean = true;
    if (initMappings()) {
      boolean bool = true;
      MappingMethod mappingMethod1 = null;
      for (MappingMethod mappingMethod2 = paramMappingMethod;; mappingMethod2 = mappingMethod2.getSuper())
        mappingMethod1 = this.mappingProvider.getMethodMapping(mappingMethod2); 
    } 
    return null;
  }
  
  public MemberInfo remapDescriptor(MemberInfo paramMemberInfo) {
    boolean bool = false;
    String str1 = paramMemberInfo.owner;
    String str2 = this.remapper.map(str1);
    str1 = str2;
    bool = true;
    str2 = paramMemberInfo.desc;
    String str3 = ObfuscationUtil.mapDescriptor(paramMemberInfo.desc, this.remapper);
    if (!str3.equals(paramMemberInfo.desc)) {
      str2 = str3;
      bool = true;
    } 
    return null;
  }
  
  public String remapDescriptor(String paramString) {
    return ObfuscationUtil.mapDescriptor(paramString, this.remapper);
  }
  
  public MappingField getObfField(MemberInfo paramMemberInfo) {
    return getObfField(paramMemberInfo.asFieldMapping(), true);
  }
  
  public MappingField getObfField(MappingField paramMappingField) {
    return getObfField(paramMappingField, true);
  }
  
  public MappingField getObfField(MappingField paramMappingField, boolean paramBoolean) {
    paramBoolean = true;
    if (!initMappings())
      return null; 
    MappingField mappingField = this.mappingProvider.getFieldMapping(paramMappingField);
    return null;
  }
  
  public String getObfClass(String paramString) {
    return !initMappings() ? null : this.mappingProvider.getClassMapping(paramString);
  }
  
  public void writeMappings(Collection paramCollection) {
    IMappingConsumer.MappingSet mappingSet1 = new IMappingConsumer.MappingSet();
    IMappingConsumer.MappingSet mappingSet2 = new IMappingConsumer.MappingSet();
    for (IMappingConsumer iMappingConsumer : paramCollection) {
      mappingSet1.addAll((Collection)iMappingConsumer.getFieldMappings(this.type));
      mappingSet2.addAll((Collection)iMappingConsumer.getMethodMappings(this.type));
    } 
    this.mappingWriter.write(this.outFileName, this.type, mappingSet1, mappingSet2);
  }
}
