package org.spongepowered.tools.obfuscation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.refmap.ReferenceMapper;
import org.spongepowered.asm.obfuscation.mapping.IMapping;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IReferenceManager;

public class ReferenceManager implements IReferenceManager {
  private final IMixinAnnotationProcessor ap;
  
  private final String outRefMapFileName;
  
  private final List environments;
  
  private final ReferenceMapper refMapper = new ReferenceMapper();
  
  private boolean allowConflicts;
  
  public ReferenceManager(IMixinAnnotationProcessor paramIMixinAnnotationProcessor, List paramList) {
    this.ap = paramIMixinAnnotationProcessor;
    this.environments = paramList;
    this.outRefMapFileName = this.ap.getOption("outRefMapFile");
  }
  
  public boolean getAllowConflicts() {
    return this.allowConflicts;
  }
  
  public void setAllowConflicts(boolean paramBoolean) {
    this.allowConflicts = paramBoolean;
  }
  
  public void write() {
    if (this.outRefMapFileName == null)
      return; 
    PrintWriter printWriter = null;
    printWriter = newWriter(this.outRefMapFileName, "refmap");
    this.refMapper.write(printWriter);
    printWriter.close();
  }
  
  private PrintWriter newWriter(String paramString1, String paramString2) throws IOException {
    paramString2 = "refmap";
    if (paramString1.matches("^.*[\\\\/:].*$")) {
      File file = new File(paramString1);
      file.getParentFile().mkdirs();
      this.ap.printMessage(Diagnostic.Kind.NOTE, "Writing " + paramString2 + " to " + file.getAbsolutePath());
      return new PrintWriter(file);
    } 
    FileObject fileObject = this.ap.getProcessingEnvironment().getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", paramString1, new javax.lang.model.element.Element[0]);
    this.ap.printMessage(Diagnostic.Kind.NOTE, "Writing " + paramString2 + " to " + (new File(fileObject.toUri())).getAbsolutePath());
    return new PrintWriter(fileObject.openWriter());
  }
  
  public ReferenceMapper getMapper() {
    return this.refMapper;
  }
  
  public void addMethodMapping(String paramString1, String paramString2, ObfuscationData paramObfuscationData) {
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      MappingMethod mappingMethod = (MappingMethod)paramObfuscationData.get(obfuscationEnvironment.getType());
      MemberInfo memberInfo = new MemberInfo((IMapping)mappingMethod);
      addMapping(obfuscationEnvironment.getType(), paramString1, paramString2, memberInfo.toString());
    } 
  }
  
  public void addMethodMapping(String paramString1, String paramString2, MemberInfo paramMemberInfo, ObfuscationData paramObfuscationData) {
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      MappingMethod mappingMethod = (MappingMethod)paramObfuscationData.get(obfuscationEnvironment.getType());
      MemberInfo memberInfo = paramMemberInfo.remapUsing(mappingMethod, true);
      addMapping(obfuscationEnvironment.getType(), paramString1, paramString2, memberInfo.toString());
    } 
  }
  
  public void addFieldMapping(String paramString1, String paramString2, MemberInfo paramMemberInfo, ObfuscationData paramObfuscationData) {
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      MappingField mappingField = (MappingField)paramObfuscationData.get(obfuscationEnvironment.getType());
      MemberInfo memberInfo = MemberInfo.fromMapping((IMapping)mappingField.transform(obfuscationEnvironment.remapDescriptor(paramMemberInfo.desc)));
      addMapping(obfuscationEnvironment.getType(), paramString1, paramString2, memberInfo.toString());
    } 
  }
  
  public void addClassMapping(String paramString1, String paramString2, ObfuscationData paramObfuscationData) {
    for (ObfuscationEnvironment obfuscationEnvironment : this.environments) {
      String str = (String)paramObfuscationData.get(obfuscationEnvironment.getType());
      addMapping(obfuscationEnvironment.getType(), paramString1, paramString2, str);
    } 
  }
  
  protected void addMapping(ObfuscationType paramObfuscationType, String paramString1, String paramString2, String paramString3) {
    String str = this.refMapper.addMapping(paramObfuscationType.getKey(), paramString1, paramString2, paramString3);
    if (paramObfuscationType.isDefault())
      this.refMapper.addMapping(null, paramString1, paramString2, paramString3); 
    if (!this.allowConflicts)
      if (!str.equals(paramString3))
        throw new ReferenceManager$ReferenceConflictException(str, paramString3);  
  }
}
