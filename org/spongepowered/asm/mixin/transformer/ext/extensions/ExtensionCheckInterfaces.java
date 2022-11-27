package org.spongepowered.asm.mixin.transformer.ext.extensions;

import com.google.common.base.Charsets;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;
import org.spongepowered.asm.util.Constants;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class ExtensionCheckInterfaces implements IExtension {
  private static final String AUDIT_DIR = "audit";
  
  private static final String IMPL_REPORT_FILENAME = "mixin_implementation_report";
  
  private static final String IMPL_REPORT_CSV_FILENAME = "mixin_implementation_report.csv";
  
  private static final String IMPL_REPORT_TXT_FILENAME = "mixin_implementation_report.txt";
  
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private final File csv;
  
  private final File report;
  
  private final Multimap interfaceMethods = (Multimap)HashMultimap.create();
  
  private boolean strict;
  
  public ExtensionCheckInterfaces() {
    File file = new File(Constants.DEBUG_OUTPUT_DIR, "audit");
    file.mkdirs();
    this.csv = new File(file, "mixin_implementation_report.csv");
    this.report = new File(file, "mixin_implementation_report.txt");
    Files.write("Class,Method,Signature,Interface\n", this.csv, Charsets.ISO_8859_1);
    String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    Files.write("Mixin Implementation Report generated on " + str + "\n", this.report, Charsets.ISO_8859_1);
  }
  
  public boolean checkActive(MixinEnvironment paramMixinEnvironment) {
    this.strict = paramMixinEnvironment.getOption(MixinEnvironment.Option.CHECK_IMPLEMENTS_STRICT);
    return paramMixinEnvironment.getOption(MixinEnvironment.Option.CHECK_IMPLEMENTS);
  }
  
  public void preApply(ITargetClassContext paramITargetClassContext) {
    ClassInfo classInfo = paramITargetClassContext.getClassInfo();
    for (ClassInfo.Method method : classInfo.getInterfaceMethods(false))
      this.interfaceMethods.put(classInfo, method); 
  }
  
  public void postApply(ITargetClassContext paramITargetClassContext) {
    ClassInfo classInfo = paramITargetClassContext.getClassInfo();
    if (classInfo.isAbstract() && !this.strict) {
      logger.info("{} is skipping abstract target {}", new Object[] { getClass().getSimpleName(), paramITargetClassContext });
      return;
    } 
    String str = classInfo.getName().replace('/', '.');
    byte b = 0;
    PrettyPrinter prettyPrinter = new PrettyPrinter();
    prettyPrinter.add("Class: %s", new Object[] { str }).hr();
    prettyPrinter.add("%-32s %-47s  %s", new Object[] { "Return Type", "Missing Method", "From Interface" }).hr();
    Set set = classInfo.getInterfaceMethods(true);
    HashSet hashSet = new HashSet(classInfo.getSuperClass().getInterfaceMethods(true));
    hashSet.addAll(this.interfaceMethods.removeAll(classInfo));
    for (ClassInfo.Method method1 : set) {
      ClassInfo.Method method2 = classInfo.findMethodInHierarchy(method1.getName(), method1.getDesc(), ClassInfo.SearchType.ALL_CLASSES, ClassInfo.Traversal.ALL);
      if (!method2.isAbstract() || hashSet.contains(method1))
        continue; 
      SignaturePrinter signaturePrinter = (new SignaturePrinter(method1.getName(), method1.getDesc())).setModifiers("");
      String str1 = method1.getOwner().getName().replace('/', '.');
      b++;
      prettyPrinter.add("%-32s%s", new Object[] { signaturePrinter.getReturnType(), signaturePrinter });
      prettyPrinter.add("%-80s  %s", new Object[] { "", str1 });
      appendToCSVReport(str, method1, str1);
    } 
  }
  
  public void export(MixinEnvironment paramMixinEnvironment, String paramString, boolean paramBoolean, byte[] paramArrayOfbyte) {}
  
  private void appendToCSVReport(String paramString1, ClassInfo.Method paramMethod, String paramString2) {
    Files.append(String.format("%s,%s,%s,%s\n", new Object[] { paramString1, paramMethod.getName(), paramMethod.getDesc(), paramString2 }), this.csv, Charsets.ISO_8859_1);
  }
  
  private void appendToTextReport(PrettyPrinter paramPrettyPrinter) {
    FileOutputStream fileOutputStream = null;
    fileOutputStream = new FileOutputStream(this.report, true);
    PrintStream printStream = new PrintStream(fileOutputStream);
    printStream.print("\n");
    paramPrettyPrinter.print(printStream);
    IOUtils.closeQuietly(fileOutputStream);
  }
}
