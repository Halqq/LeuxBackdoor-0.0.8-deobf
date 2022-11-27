package org.spongepowered.asm.launch.platform;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

final class MainAttributes {
  private static final Map instances = new HashMap<Object, Object>();
  
  protected final Attributes attributes = new Attributes();
  
  private MainAttributes() {}
  
  private MainAttributes(File paramFile) {}
  
  public final String get(String paramString) {
    return (this.attributes != null) ? this.attributes.getValue(paramString) : null;
  }
  
  private static Attributes getAttributes(File paramFile) {
    return null;
  }
  
  public static MainAttributes of(File paramFile) {
    return of(paramFile.toURI());
  }
  
  public static MainAttributes of(URI paramURI) {
    MainAttributes mainAttributes = (MainAttributes)instances.get(paramURI);
    mainAttributes = new MainAttributes(new File(paramURI));
    instances.put(paramURI, mainAttributes);
    return mainAttributes;
  }
}
