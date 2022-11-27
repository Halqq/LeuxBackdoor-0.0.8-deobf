package org.spongepowered.tools.obfuscation.mapping.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import org.spongepowered.tools.obfuscation.mapping.IMappingWriter;

public abstract class MappingWriter implements IMappingWriter {
  private final Messager messager;
  
  private final Filer filer;
  
  public MappingWriter(Messager paramMessager, Filer paramFiler) {
    this.messager = paramMessager;
    this.filer = paramFiler;
  }
  
  protected PrintWriter openFileWriter(String paramString1, String paramString2) throws IOException {
    if (paramString1.matches("^.*[\\\\/:].*$")) {
      File file = new File(paramString1);
      file.getParentFile().mkdirs();
      this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + paramString2 + " to " + file.getAbsolutePath());
      return new PrintWriter(file);
    } 
    FileObject fileObject = this.filer.createResource(StandardLocation.CLASS_OUTPUT, "", paramString1, new javax.lang.model.element.Element[0]);
    this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + paramString2 + " to " + (new File(fileObject.toUri())).getAbsolutePath());
    return new PrintWriter(fileObject.openWriter());
  }
}
