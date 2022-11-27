package org.spongepowered.asm.util;

import com.google.common.base.Strings;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrettyPrinter {
  private final PrettyPrinter$HorizontalRule horizontalRule = new PrettyPrinter$HorizontalRule(this, new char[] { '*' });
  
  private final List lines = new ArrayList();
  
  private PrettyPrinter$Table table;
  
  private boolean recalcWidth = false;
  
  protected int width = 100;
  
  protected int wrapWidth = 80;
  
  protected int kvKeyWidth = 10;
  
  protected String kvFormat = makeKvFormat(this.kvKeyWidth);
  
  public PrettyPrinter() {
    this(100);
  }
  
  public PrettyPrinter(int paramInt) {
    this.width = paramInt;
  }
  
  public PrettyPrinter wrapTo(int paramInt) {
    this.wrapWidth = paramInt;
    return this;
  }
  
  public int wrapTo() {
    return this.wrapWidth;
  }
  
  public PrettyPrinter table() {
    this.table = new PrettyPrinter$Table();
    return this;
  }
  
  public PrettyPrinter table(String... paramVarArgs) {
    this.table = new PrettyPrinter$Table();
    for (String str : paramVarArgs)
      this.table.addColumn(str); 
    return this;
  }
  
  public PrettyPrinter table(Object... paramVarArgs) {
    this.table = new PrettyPrinter$Table();
    PrettyPrinter$Column prettyPrinter$Column = null;
    for (Object object : paramVarArgs) {
      if (object instanceof String) {
        prettyPrinter$Column = this.table.addColumn((String)object);
      } else if (object instanceof Integer) {
        int i = ((Integer)object).intValue();
        prettyPrinter$Column.setWidth(i);
      } else if (object instanceof PrettyPrinter$Alignment) {
        prettyPrinter$Column.setAlignment((PrettyPrinter$Alignment)object);
      } else {
        prettyPrinter$Column = this.table.addColumn(object.toString());
      } 
    } 
    return this;
  }
  
  public PrettyPrinter spacing(int paramInt) {
    if (this.table == null)
      this.table = new PrettyPrinter$Table(); 
    this.table.setColSpacing(paramInt);
    return this;
  }
  
  public PrettyPrinter th() {
    return th(false);
  }
  
  private PrettyPrinter th(boolean paramBoolean) {
    if (this.table == null)
      this.table = new PrettyPrinter$Table(); 
    if (this.table.addHeader) {
      this.table.headerAdded();
      addLine(this.table);
    } 
    return this;
  }
  
  public PrettyPrinter tr(Object... paramVarArgs) {
    th(true);
    addLine(this.table.addRow(paramVarArgs));
    this.recalcWidth = true;
    return this;
  }
  
  public PrettyPrinter add() {
    addLine("");
    return this;
  }
  
  public PrettyPrinter add(String paramString) {
    addLine(paramString);
    this.width = Math.max(this.width, paramString.length());
    return this;
  }
  
  public PrettyPrinter add(String paramString, Object... paramVarArgs) {
    String str = String.format(paramString, paramVarArgs);
    addLine(str);
    this.width = Math.max(this.width, str.length());
    return this;
  }
  
  public PrettyPrinter add(Object[] paramArrayOfObject) {
    return add(paramArrayOfObject, "%s");
  }
  
  public PrettyPrinter add(Object[] paramArrayOfObject, String paramString) {
    for (Object object : paramArrayOfObject) {
      add(paramString, new Object[] { object });
    } 
    return this;
  }
  
  public PrettyPrinter addIndexed(Object[] paramArrayOfObject) {
    int i = String.valueOf(paramArrayOfObject.length - 1).length();
    String str = "[%" + i + "d] %s";
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      add(str, new Object[] { Integer.valueOf(b), paramArrayOfObject[b] });
    } 
    return this;
  }
  
  public PrettyPrinter addWithIndices(Collection paramCollection) {
    return addIndexed(paramCollection.toArray());
  }
  
  public PrettyPrinter add(PrettyPrinter$IPrettyPrintable paramPrettyPrinter$IPrettyPrintable) {
    paramPrettyPrinter$IPrettyPrintable.print(this);
    return this;
  }
  
  public PrettyPrinter add(Throwable paramThrowable) {
    return add(paramThrowable, 4);
  }
  
  public PrettyPrinter add(Throwable paramThrowable, int paramInt) {
    while (true) {
      add("%s: %s", new Object[] { paramThrowable.getClass().getName(), paramThrowable.getMessage() });
      add(paramThrowable.getStackTrace(), paramInt);
      paramThrowable = paramThrowable.getCause();
    } 
  }
  
  public PrettyPrinter add(StackTraceElement[] paramArrayOfStackTraceElement, int paramInt) {
    String str = Strings.repeat(" ", paramInt);
    for (StackTraceElement stackTraceElement : paramArrayOfStackTraceElement) {
      add("%s%s", new Object[] { str, stackTraceElement });
    } 
    return this;
  }
  
  public PrettyPrinter add(Object paramObject) {
    return add(paramObject, 0);
  }
  
  public PrettyPrinter add(Object paramObject, int paramInt) {
    paramInt = 0;
    String str = Strings.repeat(" ", paramInt);
    return append(paramObject, paramInt, str);
  }
  
  private PrettyPrinter append(Object paramObject, int paramInt, String paramString) {
    paramInt = 0;
    if (paramObject instanceof String)
      return add("%s%s", new Object[] { paramString, paramObject }); 
    if (paramObject instanceof Iterable) {
      for (Object object : paramObject)
        append(object, paramInt, paramString); 
      return this;
    } 
    if (paramObject instanceof Map) {
      kvWidth(paramInt);
      return add((Map)paramObject);
    } 
    return (paramObject instanceof PrettyPrinter$IPrettyPrintable) ? add((PrettyPrinter$IPrettyPrintable)paramObject) : ((paramObject instanceof Throwable) ? add((Throwable)paramObject, paramInt) : (paramObject.getClass().isArray() ? add((Object[])paramObject, paramInt + "%s") : add("%s%s", new Object[] { paramString, paramObject })));
  }
  
  public PrettyPrinter addWrapped(String paramString, Object... paramVarArgs) {
    return addWrapped(this.wrapWidth, paramString, paramVarArgs);
  }
  
  public PrettyPrinter addWrapped(int paramInt, String paramString, Object... paramVarArgs) {
    String str1 = "";
    String str2 = String.format(paramString, paramVarArgs).replace("\t", "    ");
    Matcher matcher = Pattern.compile("^(\\s+)(.*)$").matcher(str2);
    if (matcher.matches())
      str1 = matcher.group(1); 
    for (String str : getWrapped(paramInt, str2, str1))
      addLine(str); 
    return this;
  }
  
  private List getWrapped(int paramInt, String paramString1, String paramString2) {
    paramString2 = "";
    ArrayList<String> arrayList = new ArrayList();
    while (paramString1.length() > paramInt) {
      int i = paramString1.lastIndexOf(' ', paramInt);
      if (i < 10)
        i = paramInt; 
      String str = paramString1.substring(0, i);
      arrayList.add(str);
      paramString1 = paramString2 + paramString1.substring(i + 1);
    } 
    if (paramString1.length() > 0)
      arrayList.add(paramString1); 
    return arrayList;
  }
  
  public PrettyPrinter kv(String paramString1, String paramString2, Object... paramVarArgs) {
    return kv(paramString1, String.format(paramString2, paramVarArgs));
  }
  
  public PrettyPrinter kv(String paramString, Object paramObject) {
    addLine(new PrettyPrinter$KeyValue(this, paramString, paramObject));
    return kvWidth(paramString.length());
  }
  
  public PrettyPrinter kvWidth(int paramInt) {
    if (paramInt > this.kvKeyWidth) {
      this.kvKeyWidth = paramInt;
      this.kvFormat = makeKvFormat(paramInt);
    } 
    this.recalcWidth = true;
    return this;
  }
  
  public PrettyPrinter add(Map paramMap) {
    for (Map.Entry entry : paramMap.entrySet()) {
      String str = (entry.getKey() == null) ? "null" : entry.getKey().toString();
      kv(str, entry.getValue());
    } 
    return this;
  }
  
  public PrettyPrinter hr() {
    return hr('*');
  }
  
  public PrettyPrinter hr(char paramChar) {
    paramChar = '*';
    addLine(new PrettyPrinter$HorizontalRule(this, new char[] { paramChar }));
    return this;
  }
  
  public PrettyPrinter centre() {
    if (!this.lines.isEmpty()) {
      Object object = this.lines.get(this.lines.size() - 1);
      if (object instanceof String)
        addLine(new PrettyPrinter$CentredText(this, this.lines.remove(this.lines.size() - 1))); 
    } 
    return this;
  }
  
  private void addLine(Object paramObject) {}
  
  public PrettyPrinter trace() {
    return trace(getDefaultLoggerName());
  }
  
  public PrettyPrinter trace(Level paramLevel) {
    return trace(getDefaultLoggerName(), paramLevel);
  }
  
  public PrettyPrinter trace(String paramString) {
    return trace(System.err, LogManager.getLogger(paramString));
  }
  
  public PrettyPrinter trace(String paramString, Level paramLevel) {
    return trace(System.err, LogManager.getLogger(paramString), paramLevel);
  }
  
  public PrettyPrinter trace(Logger paramLogger) {
    return trace(System.err, paramLogger);
  }
  
  public PrettyPrinter trace(Logger paramLogger, Level paramLevel) {
    return trace(System.err, paramLogger, paramLevel);
  }
  
  public PrettyPrinter trace(PrintStream paramPrintStream) {
    return trace(paramPrintStream, getDefaultLoggerName());
  }
  
  public PrettyPrinter trace(PrintStream paramPrintStream, Level paramLevel) {
    return trace(paramPrintStream, getDefaultLoggerName(), paramLevel);
  }
  
  public PrettyPrinter trace(PrintStream paramPrintStream, String paramString) {
    return trace(paramPrintStream, LogManager.getLogger(paramString));
  }
  
  public PrettyPrinter trace(PrintStream paramPrintStream, String paramString, Level paramLevel) {
    return trace(paramPrintStream, LogManager.getLogger(paramString), paramLevel);
  }
  
  public PrettyPrinter trace(PrintStream paramPrintStream, Logger paramLogger) {
    return trace(paramPrintStream, paramLogger, Level.DEBUG);
  }
  
  public PrettyPrinter trace(PrintStream paramPrintStream, Logger paramLogger, Level paramLevel) {
    log(paramLogger, paramLevel);
    print(paramPrintStream);
    return this;
  }
  
  public PrettyPrinter print() {
    return print(System.err);
  }
  
  public PrettyPrinter print(PrintStream paramPrintStream) {
    updateWidth();
    printSpecial(paramPrintStream, this.horizontalRule);
    for (PrettyPrinter$ISpecialEntry prettyPrinter$ISpecialEntry : this.lines) {
      if (prettyPrinter$ISpecialEntry instanceof PrettyPrinter$ISpecialEntry) {
        printSpecial(paramPrintStream, prettyPrinter$ISpecialEntry);
        continue;
      } 
      printString(paramPrintStream, prettyPrinter$ISpecialEntry.toString());
    } 
    printSpecial(paramPrintStream, this.horizontalRule);
    return this;
  }
  
  private void printSpecial(PrintStream paramPrintStream, PrettyPrinter$ISpecialEntry paramPrettyPrinter$ISpecialEntry) {
    paramPrintStream.printf("/*%s*/\n", new Object[] { paramPrettyPrinter$ISpecialEntry.toString() });
  }
  
  private void printString(PrintStream paramPrintStream, String paramString) {
    paramPrintStream.printf("/* %-" + this.width + "s */\n", new Object[] { paramString });
  }
  
  public PrettyPrinter log(Logger paramLogger) {
    return log(paramLogger, Level.INFO);
  }
  
  public PrettyPrinter log(Logger paramLogger, Level paramLevel) {
    updateWidth();
    logSpecial(paramLogger, paramLevel, this.horizontalRule);
    for (PrettyPrinter$ISpecialEntry prettyPrinter$ISpecialEntry : this.lines) {
      if (prettyPrinter$ISpecialEntry instanceof PrettyPrinter$ISpecialEntry) {
        logSpecial(paramLogger, paramLevel, prettyPrinter$ISpecialEntry);
        continue;
      } 
      logString(paramLogger, paramLevel, prettyPrinter$ISpecialEntry.toString());
    } 
    logSpecial(paramLogger, paramLevel, this.horizontalRule);
    return this;
  }
  
  private void logSpecial(Logger paramLogger, Level paramLevel, PrettyPrinter$ISpecialEntry paramPrettyPrinter$ISpecialEntry) {
    paramLogger.log(paramLevel, "/*{}*/", new Object[] { paramPrettyPrinter$ISpecialEntry.toString() });
  }
  
  private void logString(Logger paramLogger, Level paramLevel, String paramString) {
    paramLogger.log(paramLevel, String.format("/* %-" + this.width + "s */", new Object[] { paramString }));
  }
  
  private void updateWidth() {
    if (this.recalcWidth) {
      this.recalcWidth = false;
      for (PrettyPrinter$IVariableWidthEntry prettyPrinter$IVariableWidthEntry : this.lines) {
        if (prettyPrinter$IVariableWidthEntry instanceof PrettyPrinter$IVariableWidthEntry)
          this.width = Math.min(4096, Math.max(this.width, ((PrettyPrinter$IVariableWidthEntry)prettyPrinter$IVariableWidthEntry).getWidth())); 
      } 
    } 
  }
  
  private static String makeKvFormat(int paramInt) {
    return String.format("%%%ds : %%s", new Object[] { Integer.valueOf(paramInt) });
  }
  
  private static String getDefaultLoggerName() {
    String str = (new Throwable()).getStackTrace()[2].getClassName();
    int i = str.lastIndexOf('.');
    return (i == -1) ? str : str.substring(i + 1);
  }
  
  public static void dumpStack() {
    (new PrettyPrinter()).add(new Exception("Stack trace")).print(System.err);
  }
  
  public static void print(Throwable paramThrowable) {
    (new PrettyPrinter()).add(paramThrowable).print(System.err);
  }
}
