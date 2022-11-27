package org.spongepowered.asm.lib.tree.analysis;

import java.util.ArrayList;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.IincInsnNode;
import org.spongepowered.asm.lib.tree.InvokeDynamicInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MultiANewArrayInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;

public class Frame {
  private Value returnValue;
  
  private Value[] values;
  
  private int locals;
  
  private int top;
  
  public Frame(int paramInt1, int paramInt2) {
    this.values = new Value[paramInt1 + paramInt2];
    this.locals = paramInt1;
  }
  
  public Frame(Frame paramFrame) {
    this(paramFrame.locals, paramFrame.values.length - paramFrame.locals);
    init(paramFrame);
  }
  
  public Frame init(Frame paramFrame) {
    this.returnValue = paramFrame.returnValue;
    System.arraycopy(paramFrame.values, 0, this.values, 0, this.values.length);
    this.top = paramFrame.top;
    return this;
  }
  
  public void setReturn(Value paramValue) {
    this.returnValue = paramValue;
  }
  
  public int getLocals() {
    return this.locals;
  }
  
  public int getMaxStackSize() {
    return this.values.length - this.locals;
  }
  
  public Value getLocal(int paramInt) throws IndexOutOfBoundsException {
    if (paramInt >= this.locals)
      throw new IndexOutOfBoundsException("Trying to access an inexistant local variable"); 
    return this.values[paramInt];
  }
  
  public void setLocal(int paramInt, Value paramValue) throws IndexOutOfBoundsException {
    if (paramInt >= this.locals)
      throw new IndexOutOfBoundsException("Trying to access an inexistant local variable " + paramInt); 
    this.values[paramInt] = paramValue;
  }
  
  public int getStackSize() {
    return this.top;
  }
  
  public Value getStack(int paramInt) throws IndexOutOfBoundsException {
    paramInt = 0;
    return this.values[paramInt + this.locals];
  }
  
  public void clearStack() {
    this.top = 0;
  }
  
  public Value pop() throws IndexOutOfBoundsException {
    if (this.top == 0)
      throw new IndexOutOfBoundsException("Cannot pop operand off an empty stack."); 
    return this.values[--this.top + this.locals];
  }
  
  public void push(Value paramValue) throws IndexOutOfBoundsException {
    if (this.top + this.locals >= this.values.length)
      throw new IndexOutOfBoundsException("Insufficient maximum stack size."); 
    this.values[this.top++ + this.locals] = paramValue;
  }
  
  public void execute(AbstractInsnNode paramAbstractInsnNode, Interpreter paramInterpreter) throws AnalyzerException {
    Value value1;
    Value value2;
    int i;
    Value value3;
    String str;
    int j;
    Value value4;
    ArrayList<Value> arrayList;
    int k;
    switch (paramAbstractInsnNode.getOpcode()) {
      case 0:
        return;
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
        push(paramInterpreter.newOperation(paramAbstractInsnNode));
      case 21:
      case 22:
      case 23:
      case 24:
      case 25:
        push(paramInterpreter.copyOperation(paramAbstractInsnNode, getLocal(((VarInsnNode)paramAbstractInsnNode).var)));
      case 46:
      case 47:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
        value1 = pop();
        value2 = pop();
        push(paramInterpreter.binaryOperation(paramAbstractInsnNode, value2, value1));
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
        value2 = paramInterpreter.copyOperation(paramAbstractInsnNode, pop());
        i = ((VarInsnNode)paramAbstractInsnNode).var;
        setLocal(i, value2);
        if (value2.getSize() == 2)
          setLocal(i + 1, paramInterpreter.newValue(null)); 
        value3 = getLocal(i - 1);
        if (value3.getSize() == 2)
          setLocal(i - 1, paramInterpreter.newValue(null)); 
      case 79:
      case 80:
      case 81:
      case 82:
      case 83:
      case 84:
      case 85:
      case 86:
        value4 = pop();
        value1 = pop();
        value2 = pop();
        paramInterpreter.ternaryOperation(paramAbstractInsnNode, value2, value1, value4);
      case 87:
        if (pop().getSize() == 2)
          throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of POP"); 
      case 88:
        if (pop().getSize() == 1 && pop().getSize() != 1)
          throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of POP2"); 
      case 89:
        value2 = pop();
        if (value2.getSize() != 1)
          throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP"); 
        push(value2);
        push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
      case 90:
        value2 = pop();
        value1 = pop();
        if (value2.getSize() != 1 || value1.getSize() != 1)
          throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP_X1"); 
        push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
        push(value1);
        push(value2);
      case 91:
        value2 = pop();
        if (value2.getSize() == 1) {
          value1 = pop();
          if (value1.getSize() == 1) {
            value4 = pop();
            if (value4.getSize() == 1) {
              push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
              push(value4);
              push(value1);
              push(value2);
            } else {
              throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP_X2");
            } 
          } else {
            push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
            push(value1);
            push(value2);
          } 
        } else {
          throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP_X2");
        } 
      case 92:
        value2 = pop();
        if (value2.getSize() == 1) {
          value1 = pop();
          if (value1.getSize() == 1) {
            push(value1);
            push(value2);
            push(paramInterpreter.copyOperation(paramAbstractInsnNode, value1));
            push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
          } else {
            throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP2");
          } 
        } else {
          push(value2);
          push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
        } 
      case 93:
        value2 = pop();
        if (value2.getSize() == 1) {
          value1 = pop();
          if (value1.getSize() == 1) {
            value4 = pop();
            if (value4.getSize() == 1) {
              push(paramInterpreter.copyOperation(paramAbstractInsnNode, value1));
              push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
              push(value4);
              push(value1);
              push(value2);
            } else {
              throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP2_X1");
            } 
          } else {
            throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP2_X1");
          } 
        } else {
          value1 = pop();
          if (value1.getSize() == 1) {
            push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
            push(value1);
            push(value2);
          } else {
            throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP2_X1");
          } 
        } 
      case 94:
        value2 = pop();
        if (value2.getSize() == 1) {
          value1 = pop();
          if (value1.getSize() == 1) {
            value4 = pop();
            if (value4.getSize() == 1) {
              Value value = pop();
              if (value.getSize() == 1) {
                push(paramInterpreter.copyOperation(paramAbstractInsnNode, value1));
                push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
                push(value);
                push(value4);
                push(value1);
                push(value2);
              } else {
                throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP2_X2");
              } 
            } else {
              push(paramInterpreter.copyOperation(paramAbstractInsnNode, value1));
              push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
              push(value4);
              push(value1);
              push(value2);
            } 
          } else {
            throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP2_X2");
          } 
        } else {
          value1 = pop();
          if (value1.getSize() == 1) {
            value4 = pop();
            if (value4.getSize() == 1) {
              push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
              push(value4);
              push(value1);
              push(value2);
            } else {
              throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of DUP2_X2");
            } 
          } else {
            push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
            push(value1);
            push(value2);
          } 
        } 
      case 95:
        value1 = pop();
        value2 = pop();
        if (value2.getSize() != 1 || value1.getSize() != 1)
          throw new AnalyzerException(paramAbstractInsnNode, "Illegal use of SWAP"); 
        push(paramInterpreter.copyOperation(paramAbstractInsnNode, value1));
        push(paramInterpreter.copyOperation(paramAbstractInsnNode, value2));
      case 96:
      case 97:
      case 98:
      case 99:
      case 100:
      case 101:
      case 102:
      case 103:
      case 104:
      case 105:
      case 106:
      case 107:
      case 108:
      case 109:
      case 110:
      case 111:
      case 112:
      case 113:
      case 114:
      case 115:
        value1 = pop();
        value2 = pop();
        push(paramInterpreter.binaryOperation(paramAbstractInsnNode, value2, value1));
      case 116:
      case 117:
      case 118:
      case 119:
        push(paramInterpreter.unaryOperation(paramAbstractInsnNode, pop()));
      case 120:
      case 121:
      case 122:
      case 123:
      case 124:
      case 125:
      case 126:
      case 127:
      case 128:
      case 129:
      case 130:
      case 131:
        value1 = pop();
        value2 = pop();
        push(paramInterpreter.binaryOperation(paramAbstractInsnNode, value2, value1));
      case 132:
        i = ((IincInsnNode)paramAbstractInsnNode).var;
        setLocal(i, paramInterpreter.unaryOperation(paramAbstractInsnNode, getLocal(i)));
      case 133:
      case 134:
      case 135:
      case 136:
      case 137:
      case 138:
      case 139:
      case 140:
      case 141:
      case 142:
      case 143:
      case 144:
      case 145:
      case 146:
      case 147:
        push(paramInterpreter.unaryOperation(paramAbstractInsnNode, pop()));
      case 148:
      case 149:
      case 150:
      case 151:
      case 152:
        value1 = pop();
        value2 = pop();
        push(paramInterpreter.binaryOperation(paramAbstractInsnNode, value2, value1));
      case 153:
      case 154:
      case 155:
      case 156:
      case 157:
      case 158:
        paramInterpreter.unaryOperation(paramAbstractInsnNode, pop());
      case 159:
      case 160:
      case 161:
      case 162:
      case 163:
      case 164:
      case 165:
      case 166:
        value1 = pop();
        value2 = pop();
        paramInterpreter.binaryOperation(paramAbstractInsnNode, value2, value1);
      case 167:
        return;
      case 168:
        push(paramInterpreter.newOperation(paramAbstractInsnNode));
      case 169:
        return;
      case 170:
      case 171:
        paramInterpreter.unaryOperation(paramAbstractInsnNode, pop());
      case 172:
      case 173:
      case 174:
      case 175:
      case 176:
        value2 = pop();
        paramInterpreter.unaryOperation(paramAbstractInsnNode, value2);
        paramInterpreter.returnOperation(paramAbstractInsnNode, value2, this.returnValue);
      case 177:
        if (this.returnValue != null)
          throw new AnalyzerException(paramAbstractInsnNode, "Incompatible return type"); 
      case 178:
        push(paramInterpreter.newOperation(paramAbstractInsnNode));
      case 179:
        paramInterpreter.unaryOperation(paramAbstractInsnNode, pop());
      case 180:
        push(paramInterpreter.unaryOperation(paramAbstractInsnNode, pop()));
      case 181:
        value1 = pop();
        value2 = pop();
        paramInterpreter.binaryOperation(paramAbstractInsnNode, value2, value1);
      case 182:
      case 183:
      case 184:
      case 185:
        arrayList = new ArrayList();
        str = ((MethodInsnNode)paramAbstractInsnNode).desc;
        for (k = (Type.getArgumentTypes(str)).length;; k--)
          arrayList.add(0, pop()); 
      case 186:
        arrayList = new ArrayList<Value>();
        str = ((InvokeDynamicInsnNode)paramAbstractInsnNode).desc;
        for (k = (Type.getArgumentTypes(str)).length;; k--)
          arrayList.add(0, pop()); 
      case 187:
        push(paramInterpreter.newOperation(paramAbstractInsnNode));
      case 188:
      case 189:
      case 190:
        push(paramInterpreter.unaryOperation(paramAbstractInsnNode, pop()));
      case 191:
        paramInterpreter.unaryOperation(paramAbstractInsnNode, pop());
      case 192:
      case 193:
        push(paramInterpreter.unaryOperation(paramAbstractInsnNode, pop()));
      case 194:
      case 195:
        paramInterpreter.unaryOperation(paramAbstractInsnNode, pop());
      case 197:
        arrayList = new ArrayList<Value>();
        for (j = ((MultiANewArrayInsnNode)paramAbstractInsnNode).dims;; j--)
          arrayList.add(0, pop()); 
      case 198:
      case 199:
        paramInterpreter.unaryOperation(paramAbstractInsnNode, pop());
    } 
    throw new RuntimeException("Illegal opcode " + paramAbstractInsnNode.getOpcode());
  }
  
  public boolean merge(Frame paramFrame, Interpreter paramInterpreter) throws AnalyzerException {
    if (this.top != paramFrame.top)
      throw new AnalyzerException(null, "Incompatible stack heights"); 
    boolean bool = false;
    for (byte b = 0; b < this.locals + this.top; b++) {
      Value value = paramInterpreter.merge(this.values[b], paramFrame.values[b]);
      if (!value.equals(this.values[b])) {
        this.values[b] = value;
        bool = true;
      } 
    } 
    return bool;
  }
  
  public boolean merge(Frame paramFrame, boolean[] paramArrayOfboolean) {
    boolean bool = false;
    for (byte b = 0; b < this.locals; b++) {
      if (!paramArrayOfboolean[b] && !this.values[b].equals(paramFrame.values[b])) {
        this.values[b] = paramFrame.values[b];
        bool = true;
      } 
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    byte b;
    for (b = 0; b < getLocals(); b++)
      stringBuilder.append(getLocal(b)); 
    stringBuilder.append(' ');
    for (b = 0; b < getStackSize(); b++)
      stringBuilder.append(getStack(b).toString()); 
    return stringBuilder.toString();
  }
}
