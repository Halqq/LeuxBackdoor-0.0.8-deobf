package org.spongepowered.asm.mixin.struct;

import java.util.LinkedHashMap;
import java.util.Map;

class SourceMap$Stratum {
  private static final String STRATUM_MARK = "*S";
  
  private static final String FILE_MARK = "*F";
  
  private static final String LINES_MARK = "*L";
  
  public final String name;
  
  private final Map files = new LinkedHashMap<Object, Object>();
  
  public SourceMap$Stratum(String paramString) {
    this.name = paramString;
  }
  
  public SourceMap$File addFile(int paramInt1, int paramInt2, String paramString1, String paramString2) {
    SourceMap$File sourceMap$File = (SourceMap$File)this.files.get(paramString2);
    sourceMap$File = new SourceMap$File(this.files.size() + 1, paramInt1, paramInt2, paramString1, paramString2);
    this.files.put(paramString2, sourceMap$File);
    return sourceMap$File;
  }
  
  void appendTo(StringBuilder paramStringBuilder) {
    paramStringBuilder.append("*S").append(" ").append(this.name).append("\n");
    paramStringBuilder.append("*F").append("\n");
    for (SourceMap$File sourceMap$File : this.files.values())
      sourceMap$File.appendFile(paramStringBuilder); 
    paramStringBuilder.append("*L").append("\n");
    for (SourceMap$File sourceMap$File : this.files.values())
      sourceMap$File.appendLines(paramStringBuilder); 
  }
}
