package org.spongepowered.tools.obfuscation.mapping;

import com.google.common.base.Objects;
import org.spongepowered.asm.obfuscation.mapping.IMapping;

public class IMappingConsumer$MappingSet$Pair {
  public final IMapping from;
  
  public final IMapping to;
  
  public IMappingConsumer$MappingSet$Pair(IMapping paramIMapping1, IMapping paramIMapping2) {
    this.from = paramIMapping1;
    this.to = paramIMapping2;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof IMappingConsumer$MappingSet$Pair))
      return false; 
    IMappingConsumer$MappingSet$Pair iMappingConsumer$MappingSet$Pair = (IMappingConsumer$MappingSet$Pair)paramObject;
    return (Objects.equal(this.from, iMappingConsumer$MappingSet$Pair.from) && Objects.equal(this.to, iMappingConsumer$MappingSet$Pair.to));
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { this.from, this.to });
  }
  
  public String toString() {
    return String.format("%s -> %s", new Object[] { this.from, this.to });
  }
}
