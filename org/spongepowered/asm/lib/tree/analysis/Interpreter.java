package org.spongepowered.asm.lib.tree.analysis;

import java.util.List;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;

public abstract class Interpreter {
  protected final int api;
  
  protected Interpreter(int paramInt) {
    this.api = paramInt;
  }
  
  public abstract Value newValue(Type paramType);
  
  public abstract Value newOperation(AbstractInsnNode paramAbstractInsnNode) throws AnalyzerException;
  
  public abstract Value copyOperation(AbstractInsnNode paramAbstractInsnNode, Value paramValue) throws AnalyzerException;
  
  public abstract Value unaryOperation(AbstractInsnNode paramAbstractInsnNode, Value paramValue) throws AnalyzerException;
  
  public abstract Value binaryOperation(AbstractInsnNode paramAbstractInsnNode, Value paramValue1, Value paramValue2) throws AnalyzerException;
  
  public abstract Value ternaryOperation(AbstractInsnNode paramAbstractInsnNode, Value paramValue1, Value paramValue2, Value paramValue3) throws AnalyzerException;
  
  public abstract Value naryOperation(AbstractInsnNode paramAbstractInsnNode, List paramList) throws AnalyzerException;
  
  public abstract void returnOperation(AbstractInsnNode paramAbstractInsnNode, Value paramValue1, Value paramValue2) throws AnalyzerException;
  
  public abstract Value merge(Value paramValue1, Value paramValue2);
}
