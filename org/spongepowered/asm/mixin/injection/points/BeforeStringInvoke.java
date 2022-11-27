package org.spongepowered.asm.mixin.injection.points;

import java.util.Collection;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;

@AtCode("INVOKE_STRING")
public class BeforeStringInvoke extends BeforeInvoke {
  private static final String STRING_VOID_SIG = "(Ljava/lang/String;)V";
  
  private final String ldcValue;
  
  private boolean foundLdc;
  
  public BeforeStringInvoke(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
    this.ldcValue = paramInjectionPointData.get("ldc", null);
    if (this.ldcValue == null)
      throw new IllegalArgumentException(getClass().getSimpleName() + " requires named argument \"ldc\" to specify the desired target"); 
    if (!"(Ljava/lang/String;)V".equals(this.target.desc))
      throw new IllegalArgumentException(getClass().getSimpleName() + " requires target method with with signature " + "(Ljava/lang/String;)V"); 
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection paramCollection) {
    this.foundLdc = false;
    return super.find(paramString, paramInsnList, paramCollection);
  }
  
  protected void inspectInsn(String paramString, InsnList paramInsnList, AbstractInsnNode paramAbstractInsnNode) {
    if (paramAbstractInsnNode instanceof LdcInsnNode) {
      LdcInsnNode ldcInsnNode = (LdcInsnNode)paramAbstractInsnNode;
      if (ldcInsnNode.cst instanceof String && this.ldcValue.equals(ldcInsnNode.cst)) {
        log("{} > found a matching LDC with value {}", new Object[] { this.className, ldcInsnNode.cst });
        this.foundLdc = true;
        return;
      } 
    } 
    this.foundLdc = false;
  }
  
  protected boolean matchesInsn(MemberInfo paramMemberInfo, int paramInt) {
    log("{} > > found LDC \"{}\" = {}", new Object[] { this.className, this.ldcValue, Boolean.valueOf(this.foundLdc) });
    return (this.foundLdc && super.matchesInsn(paramMemberInfo, paramInt));
  }
}
