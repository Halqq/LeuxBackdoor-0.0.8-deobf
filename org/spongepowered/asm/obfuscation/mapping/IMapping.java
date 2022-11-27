package org.spongepowered.asm.obfuscation.mapping;

public interface IMapping {
  IMapping$Type getType();
  
  Object move(String paramString);
  
  Object remap(String paramString);
  
  Object transform(String paramString);
  
  Object copy();
  
  String getName();
  
  String getSimpleName();
  
  String getOwner();
  
  String getDesc();
  
  Object getSuper();
  
  String serialise();
}
