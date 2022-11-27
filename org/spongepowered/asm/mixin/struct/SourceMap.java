package org.spongepowered.asm.mixin.struct;

import java.util.LinkedHashMap;
import java.util.Map;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.util.Bytecode;

public class SourceMap {
  private static final String DEFAULT_STRATUM = "Mixin";
  
  private static final String NEWLINE = "\n";
  
  private final String sourceFile;
  
  private final Map strata = new LinkedHashMap<Object, Object>();
  
  private int nextLineOffset = 1;
  
  private String defaultStratum = "Mixin";
  
  public SourceMap(String paramString) {
    this.sourceFile = paramString;
  }
  
  public String getSourceFile() {
    return this.sourceFile;
  }
  
  public String getPseudoGeneratedSourceFile() {
    return this.sourceFile.replace(".java", "$mixin.java");
  }
  
  public SourceMap$File addFile(ClassNode paramClassNode) {
    return addFile(this.defaultStratum, paramClassNode);
  }
  
  public SourceMap$File addFile(String paramString, ClassNode paramClassNode) {
    return addFile(paramString, paramClassNode.sourceFile, paramClassNode.name + ".java", Bytecode.getMaxLineNumber(paramClassNode, 500, 50));
  }
  
  public SourceMap$File addFile(String paramString1, String paramString2, int paramInt) {
    return addFile(this.defaultStratum, paramString1, paramString2, paramInt);
  }
  
  public SourceMap$File addFile(String paramString1, String paramString2, String paramString3, int paramInt) {
    SourceMap$Stratum sourceMap$Stratum = (SourceMap$Stratum)this.strata.get(paramString1);
    sourceMap$Stratum = new SourceMap$Stratum(paramString1);
    this.strata.put(paramString1, sourceMap$Stratum);
    SourceMap$File sourceMap$File = sourceMap$Stratum.addFile(this.nextLineOffset, paramInt, paramString2, paramString3);
    this.nextLineOffset += paramInt;
    return sourceMap$File;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    appendTo(stringBuilder);
    return stringBuilder.toString();
  }
  
  private void appendTo(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("SMAP").append("\n");
    paramStringBuilder.append(getSourceFile()).append("\n");
    paramStringBuilder.append(this.defaultStratum).append("\n");
    for (SourceMap$Stratum sourceMap$Stratum : this.strata.values())
      sourceMap$Stratum.appendTo(paramStringBuilder); 
    paramStringBuilder.append("*E").append("\n");
  }
}
