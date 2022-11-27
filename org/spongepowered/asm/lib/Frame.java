package org.spongepowered.asm.lib;

class Frame {
  static final int DIM = -268435456;
  
  static final int ARRAY_OF = 268435456;
  
  static final int ELEMENT_OF = -268435456;
  
  static final int KIND = 251658240;
  
  static final int TOP_IF_LONG_OR_DOUBLE = 8388608;
  
  static final int VALUE = 8388607;
  
  static final int BASE_KIND = 267386880;
  
  static final int BASE_VALUE = 1048575;
  
  static final int BASE = 16777216;
  
  static final int OBJECT = 24117248;
  
  static final int UNINITIALIZED = 25165824;
  
  private static final int LOCAL = 33554432;
  
  private static final int STACK = 50331648;
  
  static final int TOP = 16777216;
  
  static final int BOOLEAN = 16777225;
  
  static final int BYTE = 16777226;
  
  static final int CHAR = 16777227;
  
  static final int SHORT = 16777228;
  
  static final int INTEGER = 16777217;
  
  static final int FLOAT = 16777218;
  
  static final int DOUBLE = 16777219;
  
  static final int LONG = 16777220;
  
  static final int NULL = 16777221;
  
  static final int UNINITIALIZED_THIS = 16777222;
  
  static final int[] SIZE;
  
  Label owner;
  
  int[] inputLocals;
  
  int[] inputStack;
  
  private int[] outputLocals;
  
  private int[] outputStack;
  
  int outputStackTop;
  
  private int initializationCount;
  
  private int[] initializations;
  
  final void set(ClassWriter paramClassWriter, int paramInt1, Object[] paramArrayOfObject1, int paramInt2, Object[] paramArrayOfObject2) {
    int i = convert(paramClassWriter, paramInt1, paramArrayOfObject1, this.inputLocals);
    while (i < paramArrayOfObject1.length)
      this.inputLocals[i++] = 16777216; 
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramInt2; b2++) {
      if (paramArrayOfObject2[b2] == Opcodes.LONG || paramArrayOfObject2[b2] == Opcodes.DOUBLE)
        b1++; 
    } 
    this.inputStack = new int[paramInt2 + b1];
    convert(paramClassWriter, paramInt2, paramArrayOfObject2, this.inputStack);
    this.outputStackTop = 0;
    this.initializationCount = 0;
  }
  
  private static int convert(ClassWriter paramClassWriter, int paramInt, Object[] paramArrayOfObject, int[] paramArrayOfint) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < paramInt; b2++) {
      if (paramArrayOfObject[b2] instanceof Integer) {
        paramArrayOfint[b1++] = 0x1000000 | ((Integer)paramArrayOfObject[b2]).intValue();
        if (paramArrayOfObject[b2] == Opcodes.LONG || paramArrayOfObject[b2] == Opcodes.DOUBLE)
          paramArrayOfint[b1++] = 16777216; 
      } else if (paramArrayOfObject[b2] instanceof String) {
        paramArrayOfint[b1++] = type(paramClassWriter, Type.getObjectType((String)paramArrayOfObject[b2]).getDescriptor());
      } else {
        paramArrayOfint[b1++] = 0x1800000 | paramClassWriter.addUninitializedType("", ((Label)paramArrayOfObject[b2]).position);
      } 
    } 
    return b1;
  }
  
  final void set(Frame paramFrame) {
    this.inputLocals = paramFrame.inputLocals;
    this.inputStack = paramFrame.inputStack;
    this.outputLocals = paramFrame.outputLocals;
    this.outputStack = paramFrame.outputStack;
    this.outputStackTop = paramFrame.outputStackTop;
    this.initializationCount = paramFrame.initializationCount;
    this.initializations = paramFrame.initializations;
  }
  
  private int get(int paramInt) {
    if (this.outputLocals == null || paramInt >= this.outputLocals.length)
      return 0x2000000 | paramInt; 
    null = this.outputLocals[paramInt];
    return this.outputLocals[paramInt] = 0x2000000 | paramInt;
  }
  
  private void set(int paramInt1, int paramInt2) {
    if (this.outputLocals == null)
      this.outputLocals = new int[10]; 
    int i = this.outputLocals.length;
    if (paramInt1 >= i) {
      int[] arrayOfInt = new int[Math.max(paramInt1 + 1, 2 * i)];
      System.arraycopy(this.outputLocals, 0, arrayOfInt, 0, i);
      this.outputLocals = arrayOfInt;
    } 
    this.outputLocals[paramInt1] = paramInt2;
  }
  
  private void push(int paramInt) {
    if (this.outputStack == null)
      this.outputStack = new int[10]; 
    int i = this.outputStack.length;
    if (this.outputStackTop >= i) {
      int[] arrayOfInt = new int[Math.max(this.outputStackTop + 1, 2 * i)];
      System.arraycopy(this.outputStack, 0, arrayOfInt, 0, i);
      this.outputStack = arrayOfInt;
    } 
    this.outputStack[this.outputStackTop++] = paramInt;
    int j = this.owner.inputStackTop + this.outputStackTop;
    if (j > this.owner.outputStackMax)
      this.owner.outputStackMax = j; 
  }
  
  private void push(ClassWriter paramClassWriter, String paramString) {
    int i = type(paramClassWriter, paramString);
    push(i);
    if (i == 16777220 || i == 16777219)
      push(16777216); 
  }
  
  private static int type(ClassWriter paramClassWriter, String paramString) {
    byte b = (paramString.charAt(0) == '(') ? (paramString.indexOf(')') + 1) : 0;
    switch (paramString.charAt(b)) {
      case 'V':
        return 0;
      case 'B':
      case 'C':
      case 'I':
      case 'S':
      case 'Z':
        return 16777217;
      case 'F':
        return 16777218;
      case 'J':
        return 16777220;
      case 'D':
        return 16777219;
      case 'L':
        str = paramString.substring(b + 1, paramString.length() - 1);
        return 0x1700000 | paramClassWriter.addType(str);
    } 
    int i;
    for (i = b + 1; paramString.charAt(i) == '['; i++);
    switch (paramString.charAt(i)) {
      case 'Z':
        j = 16777225;
        return i - b << 28 | j;
      case 'C':
        j = 16777227;
        return i - b << 28 | j;
      case 'B':
        j = 16777226;
        return i - b << 28 | j;
      case 'S':
        j = 16777228;
        return i - b << 28 | j;
      case 'I':
        j = 16777217;
        return i - b << 28 | j;
      case 'F':
        j = 16777218;
        return i - b << 28 | j;
      case 'J':
        j = 16777220;
        return i - b << 28 | j;
      case 'D':
        j = 16777219;
        return i - b << 28 | j;
    } 
    String str = paramString.substring(i + 1, paramString.length() - 1);
    int j = 0x1700000 | paramClassWriter.addType(str);
    return i - b << 28 | j;
  }
  
  private int pop() {
    return (this.outputStackTop > 0) ? this.outputStack[--this.outputStackTop] : (0x3000000 | ---this.owner.inputStackTop);
  }
  
  private void pop(int paramInt) {
    if (this.outputStackTop >= paramInt) {
      this.outputStackTop -= paramInt;
    } else {
      this.owner.inputStackTop -= paramInt - this.outputStackTop;
      this.outputStackTop = 0;
    } 
  }
  
  private void pop(String paramString) {
    char c = paramString.charAt(0);
    if (c == '(') {
      pop((Type.getArgumentsAndReturnSizes(paramString) >> 2) - 1);
    } else if (c == 'J' || c == 'D') {
      pop(2);
    } else {
      pop(1);
    } 
  }
  
  private void init(int paramInt) {
    if (this.initializations == null)
      this.initializations = new int[2]; 
    int i = this.initializations.length;
    if (this.initializationCount >= i) {
      int[] arrayOfInt = new int[Math.max(this.initializationCount + 1, 2 * i)];
      System.arraycopy(this.initializations, 0, arrayOfInt, 0, i);
      this.initializations = arrayOfInt;
    } 
    this.initializations[this.initializationCount++] = paramInt;
  }
  
  private int init(ClassWriter paramClassWriter, int paramInt) {
    int i;
    if (paramInt == 16777222) {
      i = 0x1700000 | paramClassWriter.addType(paramClassWriter.thisName);
    } else if ((paramInt & 0xFFF00000) == 25165824) {
      String str = (paramClassWriter.typeTable[paramInt & 0xFFFFF]).strVal1;
      i = 0x1700000 | paramClassWriter.addType(str);
    } else {
      return paramInt;
    } 
    for (byte b = 0; b < this.initializationCount; b++) {
      int j = this.initializations[b];
      int k = j & 0xF0000000;
      int m = j & 0xF000000;
      if (m == 33554432) {
        j = k + this.inputLocals[j & 0x7FFFFF];
      } else if (m == 50331648) {
        j = k + this.inputStack[this.inputStack.length - (j & 0x7FFFFF)];
      } 
      if (paramInt == j)
        return i; 
    } 
    return paramInt;
  }
  
  final void initInputFrame(ClassWriter paramClassWriter, int paramInt1, Type[] paramArrayOfType, int paramInt2) {
    this.inputLocals = new int[paramInt2];
    this.inputStack = new int[0];
    byte b1 = 0;
    if ((paramInt1 & 0x8) == 0)
      if ((paramInt1 & 0x80000) == 0) {
        this.inputLocals[b1++] = 0x1700000 | paramClassWriter.addType(paramClassWriter.thisName);
      } else {
        this.inputLocals[b1++] = 16777222;
      }  
    for (byte b2 = 0; b2 < paramArrayOfType.length; b2++) {
      int i = type(paramClassWriter, paramArrayOfType[b2].getDescriptor());
      this.inputLocals[b1++] = i;
      if (i == 16777220 || i == 16777219)
        this.inputLocals[b1++] = 16777216; 
    } 
    while (b1 < paramInt2)
      this.inputLocals[b1++] = 16777216; 
  }
  
  void execute(int paramInt1, int paramInt2, ClassWriter paramClassWriter, Item paramItem) {
    int i;
    int j;
    int k;
    int m;
    String str;
    switch (paramInt1) {
      case 0:
      case 116:
      case 117:
      case 118:
      case 119:
      case 145:
      case 146:
      case 147:
      case 167:
      case 177:
        return;
      case 1:
        push(16777221);
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 16:
      case 17:
      case 21:
        push(16777217);
      case 9:
      case 10:
      case 22:
        push(16777220);
        push(16777216);
      case 11:
      case 12:
      case 13:
      case 23:
        push(16777218);
      case 14:
      case 15:
      case 24:
        push(16777219);
        push(16777216);
      case 18:
        switch (paramItem.type) {
          case 3:
            push(16777217);
          case 5:
            push(16777220);
            push(16777216);
          case 4:
            push(16777218);
          case 6:
            push(16777219);
            push(16777216);
          case 7:
            push(0x1700000 | paramClassWriter.addType("java/lang/Class"));
          case 8:
            push(0x1700000 | paramClassWriter.addType("java/lang/String"));
          case 16:
            push(0x1700000 | paramClassWriter.addType("java/lang/invoke/MethodType"));
        } 
        push(0x1700000 | paramClassWriter.addType("java/lang/invoke/MethodHandle"));
      case 25:
        push(get(paramInt2));
      case 46:
      case 51:
      case 52:
      case 53:
        pop(2);
        push(16777217);
      case 47:
      case 143:
        pop(2);
        push(16777220);
        push(16777216);
      case 48:
        pop(2);
        push(16777218);
      case 49:
      case 138:
        pop(2);
        push(16777219);
        push(16777216);
      case 50:
        pop(1);
        i = pop();
        push(-268435456 + i);
      case 54:
      case 56:
      case 58:
        i = pop();
        set(paramInt2, i);
        j = get(paramInt2 - 1);
        if (j == 16777220 || j == 16777219) {
          set(paramInt2 - 1, 16777216);
        } else if ((j & 0xF000000) != 16777216) {
          set(paramInt2 - 1, j | 0x800000);
        } 
      case 55:
      case 57:
        pop(1);
        i = pop();
        set(paramInt2, i);
        set(paramInt2 + 1, 16777216);
        j = get(paramInt2 - 1);
        if (j == 16777220 || j == 16777219) {
          set(paramInt2 - 1, 16777216);
        } else if ((j & 0xF000000) != 16777216) {
          set(paramInt2 - 1, j | 0x800000);
        } 
      case 79:
      case 81:
      case 83:
      case 84:
      case 85:
      case 86:
        pop(3);
      case 80:
      case 82:
        pop(4);
      case 87:
      case 153:
      case 154:
      case 155:
      case 156:
      case 157:
      case 158:
      case 170:
      case 171:
      case 172:
      case 174:
      case 176:
      case 191:
      case 194:
      case 195:
      case 198:
      case 199:
        pop(1);
      case 88:
      case 159:
      case 160:
      case 161:
      case 162:
      case 163:
      case 164:
      case 165:
      case 166:
      case 173:
      case 175:
        pop(2);
      case 89:
        i = pop();
        push(i);
        push(i);
      case 90:
        i = pop();
        j = pop();
        push(i);
        push(j);
        push(i);
      case 91:
        i = pop();
        j = pop();
        k = pop();
        push(i);
        push(k);
        push(j);
        push(i);
      case 92:
        i = pop();
        j = pop();
        push(j);
        push(i);
        push(j);
        push(i);
      case 93:
        i = pop();
        j = pop();
        k = pop();
        push(j);
        push(i);
        push(k);
        push(j);
        push(i);
      case 94:
        i = pop();
        j = pop();
        k = pop();
        m = pop();
        push(j);
        push(i);
        push(m);
        push(k);
        push(j);
        push(i);
      case 95:
        i = pop();
        j = pop();
        push(i);
        push(j);
      case 96:
      case 100:
      case 104:
      case 108:
      case 112:
      case 120:
      case 122:
      case 124:
      case 126:
      case 128:
      case 130:
      case 136:
      case 142:
      case 149:
      case 150:
        pop(2);
        push(16777217);
      case 97:
      case 101:
      case 105:
      case 109:
      case 113:
      case 127:
      case 129:
      case 131:
        pop(4);
        push(16777220);
        push(16777216);
      case 98:
      case 102:
      case 106:
      case 110:
      case 114:
      case 137:
      case 144:
        pop(2);
        push(16777218);
      case 99:
      case 103:
      case 107:
      case 111:
      case 115:
        pop(4);
        push(16777219);
        push(16777216);
      case 121:
      case 123:
      case 125:
        pop(3);
        push(16777220);
        push(16777216);
      case 132:
        set(paramInt2, 16777217);
      case 133:
      case 140:
        pop(1);
        push(16777220);
        push(16777216);
      case 134:
        pop(1);
        push(16777218);
      case 135:
      case 141:
        pop(1);
        push(16777219);
        push(16777216);
      case 139:
      case 190:
      case 193:
        pop(1);
        push(16777217);
      case 148:
      case 151:
      case 152:
        pop(4);
        push(16777217);
      case 168:
      case 169:
        throw new RuntimeException("JSR/RET are not supported with computeFrames option");
      case 178:
        push(paramClassWriter, paramItem.strVal3);
      case 179:
        pop(paramItem.strVal3);
      case 180:
        pop(1);
        push(paramClassWriter, paramItem.strVal3);
      case 181:
        pop(paramItem.strVal3);
        pop();
      case 182:
      case 183:
      case 184:
      case 185:
        pop(paramItem.strVal3);
        if (paramInt1 != 184) {
          i = pop();
          if (paramInt1 == 183 && paramItem.strVal2.charAt(0) == '<')
            init(i); 
        } 
        push(paramClassWriter, paramItem.strVal3);
      case 186:
        pop(paramItem.strVal2);
        push(paramClassWriter, paramItem.strVal2);
      case 187:
        push(0x1800000 | paramClassWriter.addUninitializedType(paramItem.strVal1, paramInt2));
      case 188:
        pop();
        switch (paramInt2) {
          case 4:
            push(285212681);
          case 5:
            push(285212683);
          case 8:
            push(285212682);
          case 9:
            push(285212684);
          case 10:
            push(285212673);
          case 6:
            push(285212674);
          case 7:
            push(285212675);
        } 
        push(285212676);
      case 189:
        str = paramItem.strVal1;
        pop();
        if (str.charAt(0) == '[') {
          push(paramClassWriter, '[' + str);
        } else {
          push(0x11700000 | paramClassWriter.addType(str));
        } 
      case 192:
        str = paramItem.strVal1;
        pop();
        if (str.charAt(0) == '[') {
          push(paramClassWriter, str);
        } else {
          push(0x1700000 | paramClassWriter.addType(str));
        } 
    } 
    pop(paramInt2);
    push(paramClassWriter, paramItem.strVal1);
  }
  
  final boolean merge(ClassWriter paramClassWriter, Frame paramFrame, int paramInt) {
    boolean bool = false;
    int i = this.inputLocals.length;
    int j = this.inputStack.length;
    if (paramFrame.inputLocals == null) {
      paramFrame.inputLocals = new int[i];
      bool = true;
    } 
    byte b;
    for (b = 0; b < i; b++) {
      int k;
      if (this.outputLocals != null && b < this.outputLocals.length) {
        int m = this.outputLocals[b];
        k = this.inputLocals[b];
      } else {
        k = this.inputLocals[b];
      } 
      if (this.initializations != null)
        k = init(paramClassWriter, k); 
      bool |= merge(paramClassWriter, k, paramFrame.inputLocals, b);
    } 
    for (b = 0; b < i; b++) {
      int k = this.inputLocals[b];
      bool |= merge(paramClassWriter, k, paramFrame.inputLocals, b);
    } 
    if (paramFrame.inputStack == null) {
      paramFrame.inputStack = new int[1];
      bool = true;
    } 
    bool |= merge(paramClassWriter, paramInt, paramFrame.inputStack, 0);
    return bool;
  }
  
  private static boolean merge(ClassWriter paramClassWriter, int paramInt1, int[] paramArrayOfint, int paramInt2) {
    int i = paramArrayOfint[paramInt2];
    if (i == paramInt1)
      return false; 
    if ((paramInt1 & 0xFFFFFFF) == 16777221) {
      if (i == 16777221)
        return false; 
      paramInt1 = 16777221;
    } 
    paramArrayOfint[paramInt2] = paramInt1;
    return true;
  }
  
  static {
    int[] arrayOfInt = new int[202];
    String str = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE";
    byte b = 0;
    while (true) {
      arrayOfInt.length;
      arrayOfInt[b] = str.charAt(b) - 69;
    } 
  }
}
