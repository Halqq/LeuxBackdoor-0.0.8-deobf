package org.spongepowered.asm.mixin.transformer.debug;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RuntimeDecompilerAsync extends RuntimeDecompiler implements Runnable, Thread.UncaughtExceptionHandler {
  private final BlockingQueue queue = new LinkedBlockingQueue();
  
  private final Thread thread = new Thread(this, "Decompiler thread");
  
  private boolean run = true;
  
  public RuntimeDecompilerAsync(File paramFile) {
    super(paramFile);
    this.thread.setDaemon(true);
    this.thread.setPriority(1);
    this.thread.setUncaughtExceptionHandler(this);
    this.thread.start();
  }
  
  public void decompile(File paramFile) {
    if (this.run) {
      this.queue.offer(paramFile);
    } else {
      super.decompile(paramFile);
    } 
  }
  
  public void run() {
    while (this.run) {
      File file = this.queue.take();
      super.decompile(file);
    } 
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
    this.logger.error("Async decompiler encountered an error and will terminate. Further decompile requests will be handled synchronously. {} {}", new Object[] { paramThrowable.getClass().getName(), paramThrowable.getMessage() });
    flush();
  }
  
  private void flush() {
    this.run = false;
    File file;
    while ((file = this.queue.poll()) != null)
      decompile(file); 
  }
}
