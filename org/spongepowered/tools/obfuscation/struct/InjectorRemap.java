package org.spongepowered.tools.obfuscation.struct;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;

public class InjectorRemap {
  private final boolean remap;
  
  private Message message;
  
  private int remappedCount;
  
  public InjectorRemap(boolean paramBoolean) {
    this.remap = paramBoolean;
  }
  
  public boolean shouldRemap() {
    return this.remap;
  }
  
  public void notifyRemapped() {
    this.remappedCount++;
    clearMessage();
  }
  
  public void addMessage(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationHandle paramAnnotationHandle) {
    this.message = new Message(paramKind, paramCharSequence, paramElement, paramAnnotationHandle);
  }
  
  public void clearMessage() {
    this.message = null;
  }
  
  public void dispatchPendingMessages(Messager paramMessager) {
    if (this.remappedCount == 0 && this.message != null)
      this.message.sendTo(paramMessager); 
  }
}
