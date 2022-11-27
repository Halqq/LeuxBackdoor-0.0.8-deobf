package org.spongepowered.asm.mixin.transformer.debug;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import java.io.File;
import java.util.Map;
import java.util.jar.Manifest;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import org.spongepowered.asm.mixin.transformer.ext.IDecompiler;

public class RuntimeDecompiler extends IFernflowerLogger implements IDecompiler, IResultSaver {
  private static final Level[] SEVERITY_LEVELS = new Level[] { Level.TRACE, Level.INFO, Level.WARN, Level.ERROR };
  
  private final Map options = (Map)ImmutableMap.builder().put("din", "0").put("rbr", "0").put("dgs", "1").put("asc", "1").put("den", "1").put("hdc", "1").put("ind", "    ").build();
  
  private final File outputPath;
  
  protected final Logger logger = LogManager.getLogger("fernflower");
  
  public RuntimeDecompiler(File paramFile) {
    this.outputPath = paramFile;
    if (this.outputPath.exists())
      FileUtils.deleteDirectory(this.outputPath); 
  }
  
  public void decompile(File paramFile) {
    Fernflower fernflower = new Fernflower(new RuntimeDecompiler$1(this), this, this.options, this);
    fernflower.getStructContext().addSpace(paramFile, true);
    fernflower.decompileContext();
  }
  
  public void saveFolder(String paramString) {}
  
  public void saveClassFile(String paramString1, String paramString2, String paramString3, String paramString4, int[] paramArrayOfint) {
    File file = new File(this.outputPath, paramString2 + ".java");
    file.getParentFile().mkdirs();
    this.logger.info("Writing {}", new Object[] { file.getAbsolutePath() });
    Files.write(paramString4, file, Charsets.UTF_8);
  }
  
  public void startReadingClass(String paramString) {
    this.logger.info("Decompiling {}", new Object[] { paramString });
  }
  
  public void writeMessage(String paramString, IFernflowerLogger.Severity paramSeverity) {
    this.logger.log(SEVERITY_LEVELS[paramSeverity.ordinal()], paramString);
  }
  
  public void writeMessage(String paramString, Throwable paramThrowable) {
    this.logger.warn("{} {}: {}", new Object[] { paramString, paramThrowable.getClass().getSimpleName(), paramThrowable.getMessage() });
  }
  
  public void writeMessage(String paramString, IFernflowerLogger.Severity paramSeverity, Throwable paramThrowable) {
    this.logger.log(SEVERITY_LEVELS[paramSeverity.ordinal()], paramString, paramThrowable);
  }
  
  public void copyFile(String paramString1, String paramString2, String paramString3) {}
  
  public void createArchive(String paramString1, String paramString2, Manifest paramManifest) {}
  
  public void saveDirEntry(String paramString1, String paramString2, String paramString3) {}
  
  public void copyEntry(String paramString1, String paramString2, String paramString3, String paramString4) {}
  
  public void saveClassEntry(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {}
  
  public void closeArchive(String paramString1, String paramString2) {}
}
