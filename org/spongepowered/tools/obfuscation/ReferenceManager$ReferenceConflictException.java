package org.spongepowered.tools.obfuscation;

public class ReferenceManager$ReferenceConflictException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  private final String oldReference;
  
  private final String newReference;
  
  public ReferenceManager$ReferenceConflictException(String paramString1, String paramString2) {
    this.oldReference = paramString1;
    this.newReference = paramString2;
  }
  
  public String getOld() {
    return this.oldReference;
  }
  
  public String getNew() {
    return this.newReference;
  }
}
