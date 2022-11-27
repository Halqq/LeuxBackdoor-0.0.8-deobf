package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.util.Bytecode;

@AtCode("FIELD")
public class BeforeFieldAccess extends BeforeInvoke {
  private static final String ARRAY_GET = "get";
  
  private static final String ARRAY_SET = "set";
  
  private static final String ARRAY_LENGTH = "length";
  
  public static final int ARRAY_SEARCH_FUZZ_DEFAULT = 8;
  
  private final int opcode;
  
  private final int arrOpcode;
  
  private final int fuzzFactor;
  
  public BeforeFieldAccess(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
    this.opcode = paramInjectionPointData.getOpcode(-1, new int[] { 180, 181, 178, 179, -1 });
    String str = paramInjectionPointData.get("array", "");
    this.arrOpcode = "get".equalsIgnoreCase(str) ? 46 : ("set".equalsIgnoreCase(str) ? 79 : ("length".equalsIgnoreCase(str) ? 190 : 0));
    this.fuzzFactor = Math.min(Math.max(paramInjectionPointData.get("fuzz", 8), 1), 32);
  }
  
  public int getFuzzFactor() {
    return this.fuzzFactor;
  }
  
  public int getArrayOpcode() {
    return this.arrOpcode;
  }
  
  private int getArrayOpcode(String paramString) {
    return (this.arrOpcode != 190) ? Type.getType(paramString).getElementType().getOpcode(this.arrOpcode) : this.arrOpcode;
  }
  
  protected boolean matchesInsn(AbstractInsnNode paramAbstractInsnNode) {
    return (paramAbstractInsnNode instanceof FieldInsnNode && (((FieldInsnNode)paramAbstractInsnNode).getOpcode() == this.opcode || this.opcode == -1)) ? ((this.arrOpcode == 0) ? true : ((paramAbstractInsnNode.getOpcode() != 178 && paramAbstractInsnNode.getOpcode() != 180) ? false : ((Type.getType(((FieldInsnNode)paramAbstractInsnNode).desc).getSort() == 9)))) : false;
  }
  
  protected boolean addInsn(InsnList paramInsnList, Collection paramCollection, AbstractInsnNode paramAbstractInsnNode) {
    if (this.arrOpcode > 0) {
      FieldInsnNode fieldInsnNode = (FieldInsnNode)paramAbstractInsnNode;
      int i = getArrayOpcode(fieldInsnNode.desc);
      log("{} > > > > searching for array access opcode {} fuzz={}", new Object[] { this.className, Bytecode.getOpcodeName(i), Integer.valueOf(this.fuzzFactor) });
      if (findArrayNode(paramInsnList, fieldInsnNode, i, this.fuzzFactor) == null) {
        log("{} > > > > > failed to locate matching insn", new Object[] { this.className });
        return false;
      } 
    } 
    log("{} > > > > > adding matching insn", new Object[] { this.className });
    return super.addInsn(paramInsnList, paramCollection, paramAbstractInsnNode);
  }
  
  public static AbstractInsnNode findArrayNode(InsnList paramInsnList, FieldInsnNode paramFieldInsnNode, int paramInt1, int paramInt2) {
    byte b = 0;
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator(paramInsnList.indexOf((AbstractInsnNode)paramFieldInsnNode) + 1);
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      if (abstractInsnNode.getOpcode() == paramInt1)
        return abstractInsnNode; 
      if (abstractInsnNode.getOpcode() == 190)
        return null; 
      if (abstractInsnNode instanceof FieldInsnNode) {
        FieldInsnNode fieldInsnNode = (FieldInsnNode)abstractInsnNode;
        if (fieldInsnNode.desc.equals(paramFieldInsnNode.desc) && fieldInsnNode.name.equals(paramFieldInsnNode.name) && fieldInsnNode.owner.equals(paramFieldInsnNode.owner))
          return null; 
      } 
      if (b++ > paramInt2)
        return null; 
    } 
    return null;
  }
}
