package org.spongepowered.asm.mixin.injection.points;

import com.google.common.primitives.Ints;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.InjectionPoint.AtCode;
import org.spongepowered.asm.mixin.injection.struct.InjectionPointData;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

@AtCode("CONSTANT")
public class BeforeConstant extends InjectionPoint {
  private static final Logger logger = LogManager.getLogger("mixin");
  
  private final int ordinal;
  
  private final boolean nullValue;
  
  private final Integer intValue;
  
  private final Float floatValue;
  
  private final Long longValue;
  
  private final Double doubleValue;
  
  private final String stringValue;
  
  private final Type typeValue;
  
  private final int[] expandOpcodes;
  
  private final boolean expand;
  
  private final String matchByType;
  
  private final boolean log;
  
  public BeforeConstant(IMixinContext paramIMixinContext, AnnotationNode paramAnnotationNode, String paramString) {
    super((String)Annotations.getValue(paramAnnotationNode, "slice", ""), InjectionPoint.Selector.DEFAULT, null);
    Boolean bool = (Boolean)Annotations.getValue(paramAnnotationNode, "nullValue", null);
    this.ordinal = ((Integer)Annotations.getValue(paramAnnotationNode, "ordinal", Integer.valueOf(-1))).intValue();
    this.nullValue = bool.booleanValue();
    this.intValue = (Integer)Annotations.getValue(paramAnnotationNode, "intValue", null);
    this.floatValue = (Float)Annotations.getValue(paramAnnotationNode, "floatValue", null);
    this.longValue = (Long)Annotations.getValue(paramAnnotationNode, "longValue", null);
    this.doubleValue = (Double)Annotations.getValue(paramAnnotationNode, "doubleValue", null);
    this.stringValue = (String)Annotations.getValue(paramAnnotationNode, "stringValue", null);
    this.typeValue = (Type)Annotations.getValue(paramAnnotationNode, "classValue", null);
    this.matchByType = validateDiscriminator(paramIMixinContext, paramString, bool, "on @Constant annotation");
    this.expandOpcodes = parseExpandOpcodes(Annotations.getValue(paramAnnotationNode, "expandZeroConditions", true, Constant.Condition.class));
    this.expand = (this.expandOpcodes.length > 0);
    this.log = ((Boolean)Annotations.getValue(paramAnnotationNode, "log", Boolean.FALSE)).booleanValue();
  }
  
  public BeforeConstant(InjectionPointData paramInjectionPointData) {
    super(paramInjectionPointData);
    String str = paramInjectionPointData.get("nullValue", null);
  }
  
  private String validateDiscriminator(IMixinContext paramIMixinContext, String paramString1, Boolean paramBoolean, String paramString2) {
    int i = count(new Object[] { paramBoolean, this.intValue, this.floatValue, this.longValue, this.doubleValue, this.stringValue, this.typeValue });
    if (i == 1) {
      paramString1 = null;
    } else if (i > 1) {
      throw new InvalidInjectionException(paramIMixinContext, "Conflicting constant discriminators specified " + paramString2 + " for " + paramIMixinContext);
    } 
    return paramString1;
  }
  
  private int[] parseExpandOpcodes(List paramList) {
    HashSet<Integer> hashSet = new HashSet();
    for (Constant.Condition condition1 : paramList) {
      Constant.Condition condition2 = condition1.getEquivalentCondition();
      for (int i : condition2.getOpcodes())
        hashSet.add(Integer.valueOf(i)); 
    } 
    return Ints.toArray(hashSet);
  }
  
  public boolean find(String paramString, InsnList paramInsnList, Collection<AbstractInsnNode> paramCollection) {
    boolean bool = false;
    log("BeforeConstant is searching for constants in method with descriptor {}", new Object[] { paramString });
    ListIterator<AbstractInsnNode> listIterator = paramInsnList.iterator();
    byte b = 0;
    int i = 0;
    while (listIterator.hasNext()) {
      AbstractInsnNode abstractInsnNode = listIterator.next();
      boolean bool1 = this.expand ? matchesConditionalInsn(i, abstractInsnNode) : matchesConstantInsn(abstractInsnNode);
      log("    BeforeConstant found a matching constant{} at ordinal {}", new Object[] { (this.matchByType != null) ? " TYPE" : " value", Integer.valueOf(b) });
      if (this.ordinal == -1 || this.ordinal == b) {
        log("      BeforeConstant found {}", new Object[] { Bytecode.describeNode(abstractInsnNode).trim() });
        paramCollection.add(abstractInsnNode);
        bool = true;
      } 
      b++;
      if (!(abstractInsnNode instanceof org.spongepowered.asm.lib.tree.LabelNode) && !(abstractInsnNode instanceof org.spongepowered.asm.lib.tree.FrameNode))
        i = abstractInsnNode.getOpcode(); 
    } 
    return bool;
  }
  
  private boolean matchesConditionalInsn(int paramInt, AbstractInsnNode paramAbstractInsnNode) {
    paramInt = 0;
    for (int i : this.expandOpcodes) {
      int j = paramAbstractInsnNode.getOpcode();
      if (j == i) {
        if (paramInt == 148 || paramInt == 149 || paramInt == 150 || paramInt == 151 || paramInt == 152) {
          log("  BeforeConstant is ignoring {} following {}", new Object[] { Bytecode.getOpcodeName(j), Bytecode.getOpcodeName(paramInt) });
          return false;
        } 
        log("  BeforeConstant found {} instruction", new Object[] { Bytecode.getOpcodeName(j) });
        return true;
      } 
    } 
    if (this.intValue != null && this.intValue.intValue() == 0 && Bytecode.isConstant(paramAbstractInsnNode)) {
      Object object = Bytecode.getConstant(paramAbstractInsnNode);
      log("  BeforeConstant found INTEGER constant: value = {}", new Object[] { object });
      return (object instanceof Integer && ((Integer)object).intValue() == 0);
    } 
    return false;
  }
  
  private boolean matchesConstantInsn(AbstractInsnNode paramAbstractInsnNode) {
    if (!Bytecode.isConstant(paramAbstractInsnNode))
      return false; 
    Object object = Bytecode.getConstant(paramAbstractInsnNode);
    log("  BeforeConstant found NULL constant: nullValue = {}", new Object[] { Boolean.valueOf(this.nullValue) });
    return (this.nullValue || "Ljava/lang/Object;".equals(this.matchByType));
  }
  
  protected void log(String paramString, Object... paramVarArgs) {
    if (this.log)
      logger.info(paramString, paramVarArgs); 
  }
  
  private static int count(Object... paramVarArgs) {
    byte b = 0;
    for (Object object : paramVarArgs)
      b++; 
    return b;
  }
}
