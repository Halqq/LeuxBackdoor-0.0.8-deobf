package org.spongepowered.asm.lib;

class MethodWriter extends MethodVisitor {
  static final int ACC_CONSTRUCTOR = 524288;
  
  static final int SAME_FRAME = 0;
  
  static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
  
  static final int RESERVED = 128;
  
  static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
  
  static final int CHOP_FRAME = 248;
  
  static final int SAME_FRAME_EXTENDED = 251;
  
  static final int APPEND_FRAME = 252;
  
  static final int FULL_FRAME = 255;
  
  static final int FRAMES = 0;
  
  static final int INSERTED_FRAMES = 1;
  
  static final int MAXS = 2;
  
  static final int NOTHING = 3;
  
  final ClassWriter cw;
  
  private int access;
  
  private final int name;
  
  private final int desc;
  
  private final String descriptor;
  
  String signature;
  
  int classReaderOffset;
  
  int classReaderLength;
  
  int exceptionCount;
  
  int[] exceptions;
  
  private ByteVector annd;
  
  private AnnotationWriter anns;
  
  private AnnotationWriter ianns;
  
  private AnnotationWriter tanns;
  
  private AnnotationWriter itanns;
  
  private AnnotationWriter[] panns;
  
  private AnnotationWriter[] ipanns;
  
  private int synthetics;
  
  private Attribute attrs;
  
  private ByteVector code = new ByteVector();
  
  private int maxStack;
  
  private int maxLocals;
  
  private int currentLocals;
  
  private int frameCount;
  
  private ByteVector stackMap;
  
  private int previousFrameOffset;
  
  private int[] previousFrame;
  
  private int[] frame;
  
  private int handlerCount;
  
  private Handler firstHandler;
  
  private Handler lastHandler;
  
  private int methodParametersCount;
  
  private ByteVector methodParameters;
  
  private int localVarCount;
  
  private ByteVector localVar;
  
  private int localVarTypeCount;
  
  private ByteVector localVarType;
  
  private int lineNumberCount;
  
  private ByteVector lineNumber;
  
  private int lastCodeOffset;
  
  private AnnotationWriter ctanns;
  
  private AnnotationWriter ictanns;
  
  private Attribute cattrs;
  
  private int subroutines;
  
  private final int compute;
  
  private Label labels;
  
  private Label previousBlock;
  
  private Label currentBlock;
  
  private int stackSize;
  
  private int maxStackSize;
  
  MethodWriter(ClassWriter paramClassWriter, int paramInt1, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, int paramInt2) {
    super(327680);
    if (paramClassWriter.firstMethod == null) {
      paramClassWriter.firstMethod = this;
    } else {
      paramClassWriter.lastMethod.mv = this;
    } 
    paramClassWriter.lastMethod = this;
    this.cw = paramClassWriter;
    this.access = paramInt1;
    if ("<init>".equals(paramString1))
      this.access |= 0x80000; 
    this.name = paramClassWriter.newUTF8(paramString1);
    this.desc = paramClassWriter.newUTF8(paramString2);
    this.descriptor = paramString2;
    this.signature = paramString3;
    if (paramArrayOfString.length > 0) {
      this.exceptionCount = paramArrayOfString.length;
      this.exceptions = new int[this.exceptionCount];
      for (byte b = 0; b < this.exceptionCount; b++)
        this.exceptions[b] = paramClassWriter.newClass(paramArrayOfString[b]); 
    } 
    this.compute = paramInt2;
    if (paramInt2 != 3) {
      int i = Type.getArgumentsAndReturnSizes(this.descriptor) >> 2;
      if ((paramInt1 & 0x8) != 0)
        i--; 
      this.maxLocals = i;
      this.currentLocals = i;
      this.labels = new Label();
      this.labels.status |= 0x8;
      visitLabel(this.labels);
    } 
  }
  
  public void visitParameter(String paramString, int paramInt) {
    if (this.methodParameters == null)
      this.methodParameters = new ByteVector(); 
    this.methodParametersCount++;
  }
  
  public AnnotationVisitor visitAnnotationDefault() {
    this.annd = new ByteVector();
    return new AnnotationWriter(this.cw, false, this.annd, null, 0);
  }
  
  public AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    byteVector.putShort(this.cw.newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, 2);
    annotationWriter.next = this.anns;
    this.anns = annotationWriter;
    return annotationWriter;
  }
  
  public AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    AnnotationWriter.putTarget(paramInt, paramTypePath, byteVector);
    byteVector.putShort(this.cw.newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
    annotationWriter.next = this.tanns;
    this.tanns = annotationWriter;
    return annotationWriter;
  }
  
  public AnnotationVisitor visitParameterAnnotation(int paramInt, String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    if ("Ljava/lang/Synthetic;".equals(paramString)) {
      this.synthetics = Math.max(this.synthetics, paramInt + 1);
      return new AnnotationWriter(this.cw, false, byteVector, null, 0);
    } 
    byteVector.putShort(this.cw.newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, 2);
    if (this.panns == null)
      this.panns = new AnnotationWriter[(Type.getArgumentTypes(this.descriptor)).length]; 
    annotationWriter.next = this.panns[paramInt];
    this.panns[paramInt] = annotationWriter;
    return annotationWriter;
  }
  
  public void visitAttribute(Attribute paramAttribute) {
    if (paramAttribute.isCodeAttribute()) {
      paramAttribute.next = this.cattrs;
      this.cattrs = paramAttribute;
    } else {
      paramAttribute.next = this.attrs;
      this.attrs = paramAttribute;
    } 
  }
  
  public void visitCode() {}
  
  public void visitFrame(int paramInt1, int paramInt2, Object[] paramArrayOfObject1, int paramInt3, Object[] paramArrayOfObject2) {
    if (this.compute == 0)
      return; 
    if (this.compute == 1) {
      if (this.currentBlock.frame == null) {
        this.currentBlock.frame = new CurrentFrame();
        this.currentBlock.frame.owner = this.currentBlock;
        this.currentBlock.frame.initInputFrame(this.cw, this.access, Type.getArgumentTypes(this.descriptor), paramInt2);
        visitImplicitFirstFrame();
      } else {
        if (paramInt1 == -1)
          this.currentBlock.frame.set(this.cw, paramInt2, paramArrayOfObject1, paramInt3, paramArrayOfObject2); 
        visitFrame(this.currentBlock.frame);
      } 
    } else if (paramInt1 == -1) {
      if (this.previousFrame == null)
        visitImplicitFirstFrame(); 
      this.currentLocals = paramInt2;
      int i = startFrame(this.code.length, paramInt2, paramInt3);
      byte b;
      for (b = 0; b < paramInt2; b++) {
        if (paramArrayOfObject1[b] instanceof String) {
          this.frame[i++] = 0x1700000 | this.cw.addType((String)paramArrayOfObject1[b]);
        } else if (paramArrayOfObject1[b] instanceof Integer) {
          this.frame[i++] = ((Integer)paramArrayOfObject1[b]).intValue();
        } else {
          this.frame[i++] = 0x1800000 | this.cw.addUninitializedType("", ((Label)paramArrayOfObject1[b]).position);
        } 
      } 
      for (b = 0; b < paramInt3; b++) {
        if (paramArrayOfObject2[b] instanceof String) {
          this.frame[i++] = 0x1700000 | this.cw.addType((String)paramArrayOfObject2[b]);
        } else if (paramArrayOfObject2[b] instanceof Integer) {
          this.frame[i++] = ((Integer)paramArrayOfObject2[b]).intValue();
        } else {
          this.frame[i++] = 0x1800000 | this.cw.addUninitializedType("", ((Label)paramArrayOfObject2[b]).position);
        } 
      } 
      endFrame();
    } else {
      if (this.stackMap == null) {
        byte b;
        this.stackMap = new ByteVector();
        int i = this.code.length;
        switch (paramInt1) {
          case 0:
            this.currentLocals = paramInt2;
            this.stackMap.putByte(255).putShort(i).putShort(paramInt2);
            for (b = 0; b < paramInt2; b++)
              writeFrameType(paramArrayOfObject1[b]); 
            this.stackMap.putShort(paramInt3);
            for (b = 0; b < paramInt3; b++)
              writeFrameType(paramArrayOfObject2[b]); 
            break;
          case 1:
            this.currentLocals += paramInt2;
            this.stackMap.putByte(251 + paramInt2).putShort(i);
            for (b = 0; b < paramInt2; b++)
              writeFrameType(paramArrayOfObject1[b]); 
            break;
          case 2:
            this.currentLocals -= paramInt2;
            this.stackMap.putByte(251 - paramInt2).putShort(i);
            break;
          case 3:
            if (i < 64) {
              this.stackMap.putByte(i);
              break;
            } 
            this.stackMap.putByte(251).putShort(i);
            break;
          case 4:
            if (i < 64) {
              this.stackMap.putByte(64 + i);
            } else {
              this.stackMap.putByte(247).putShort(i);
            } 
            writeFrameType(paramArrayOfObject2[0]);
            break;
        } 
      } else {
        int i = this.code.length - this.previousFrameOffset - 1;
        if (paramInt1 == 3)
          return; 
        throw new IllegalStateException();
      } 
      this.previousFrameOffset = this.code.length;
      this.frameCount++;
    } 
    this.maxStack = Math.max(this.maxStack, paramInt3);
    this.maxLocals = Math.max(this.maxLocals, this.currentLocals);
  }
  
  public void visitInsn(int paramInt) {
    this.lastCodeOffset = this.code.length;
    this.code.putByte(paramInt);
    if (this.currentBlock != null) {
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(paramInt, 0, null, null);
      } else {
        int i = this.stackSize + Frame.SIZE[paramInt];
        if (i > this.maxStackSize)
          this.maxStackSize = i; 
        this.stackSize = i;
      } 
      if ((paramInt >= 172 && paramInt <= 177) || paramInt == 191)
        noSuccessor(); 
    } 
  }
  
  public void visitIntInsn(int paramInt1, int paramInt2) {
    this.lastCodeOffset = this.code.length;
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(paramInt1, paramInt2, null, null);
      } else if (paramInt1 != 188) {
        int i = this.stackSize + 1;
        if (i > this.maxStackSize)
          this.maxStackSize = i; 
        this.stackSize = i;
      }  
    if (paramInt1 == 17) {
      this.code.put12(paramInt1, paramInt2);
    } else {
      this.code.put11(paramInt1, paramInt2);
    } 
  }
  
  public void visitVarInsn(int paramInt1, int paramInt2) {
    this.lastCodeOffset = this.code.length;
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(paramInt1, paramInt2, null, null);
      } else if (paramInt1 == 169) {
        this.currentBlock.status |= 0x100;
        this.currentBlock.inputStackTop = this.stackSize;
        noSuccessor();
      } else {
        int i = this.stackSize + Frame.SIZE[paramInt1];
        if (i > this.maxStackSize)
          this.maxStackSize = i; 
        this.stackSize = i;
      }  
    if (this.compute != 3) {
      int i;
      if (paramInt1 == 22 || paramInt1 == 24 || paramInt1 == 55 || paramInt1 == 57) {
        i = paramInt2 + 2;
      } else {
        i = paramInt2 + 1;
      } 
      if (i > this.maxLocals)
        this.maxLocals = i; 
    } 
    if (paramInt2 < 4 && paramInt1 != 169) {
      int i;
      if (paramInt1 < 54) {
        i = 26 + (paramInt1 - 21 << 2) + paramInt2;
      } else {
        i = 59 + (paramInt1 - 54 << 2) + paramInt2;
      } 
      this.code.putByte(i);
    } else if (paramInt2 >= 256) {
      this.code.putByte(196).put12(paramInt1, paramInt2);
    } else {
      this.code.put11(paramInt1, paramInt2);
    } 
    if (paramInt1 >= 54 && this.compute == 0 && this.handlerCount > 0)
      visitLabel(new Label()); 
  }
  
  public void visitTypeInsn(int paramInt, String paramString) {
    this.lastCodeOffset = this.code.length;
    Item item = this.cw.newClassItem(paramString);
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(paramInt, this.code.length, this.cw, item);
      } else if (paramInt == 187) {
        int i = this.stackSize + 1;
        if (i > this.maxStackSize)
          this.maxStackSize = i; 
        this.stackSize = i;
      }  
    this.code.put12(paramInt, item.index);
  }
  
  public void visitFieldInsn(int paramInt, String paramString1, String paramString2, String paramString3) {
    this.lastCodeOffset = this.code.length;
    Item item = this.cw.newFieldItem(paramString1, paramString2, paramString3);
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(paramInt, 0, this.cw, item);
      } else {
        int i;
        char c = paramString3.charAt(0);
        switch (paramInt) {
          case 178:
            i = this.stackSize + ((c == 'D' || c == 'J') ? 2 : 1);
            break;
          case 179:
            i = this.stackSize + ((c == 'D' || c == 'J') ? -2 : -1);
            break;
          case 180:
            i = this.stackSize + ((c == 'D' || c == 'J') ? 1 : 0);
            break;
          default:
            i = this.stackSize + ((c == 'D' || c == 'J') ? -3 : -2);
            break;
        } 
        if (i > this.maxStackSize)
          this.maxStackSize = i; 
        this.stackSize = i;
      }  
    this.code.put12(paramInt, item.index);
  }
  
  public void visitMethodInsn(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    this.lastCodeOffset = this.code.length;
    Item item = this.cw.newMethodItem(paramString1, paramString2, paramString3, paramBoolean);
    int i = item.intVal;
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(paramInt, 0, this.cw, item);
      } else {
        int j;
        i = Type.getArgumentsAndReturnSizes(paramString3);
        item.intVal = i;
        if (paramInt == 184) {
          j = this.stackSize - (i >> 2) + (i & 0x3) + 1;
        } else {
          j = this.stackSize - (i >> 2) + (i & 0x3);
        } 
        if (j > this.maxStackSize)
          this.maxStackSize = j; 
        this.stackSize = j;
      }  
    if (paramInt == 185) {
      i = Type.getArgumentsAndReturnSizes(paramString3);
      item.intVal = i;
      this.code.put12(185, item.index).put11(i >> 2, 0);
    } else {
      this.code.put12(paramInt, item.index);
    } 
  }
  
  public void visitInvokeDynamicInsn(String paramString1, String paramString2, Handle paramHandle, Object... paramVarArgs) {
    this.lastCodeOffset = this.code.length;
    Item item = this.cw.newInvokeDynamicItem(paramString1, paramString2, paramHandle, paramVarArgs);
    int i = item.intVal;
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(186, 0, this.cw, item);
      } else {
        i = Type.getArgumentsAndReturnSizes(paramString2);
        item.intVal = i;
        int j = this.stackSize - (i >> 2) + (i & 0x3) + 1;
        if (j > this.maxStackSize)
          this.maxStackSize = j; 
        this.stackSize = j;
      }  
    this.code.put12(186, item.index);
    this.code.putShort(0);
  }
  
  public void visitJumpInsn(int paramInt, Label paramLabel) {
    boolean bool = (paramInt >= 200) ? true : false;
    paramInt = paramInt;
    this.lastCodeOffset = this.code.length;
    Label label = null;
    if (this.currentBlock != null)
      if (this.compute == 0) {
        this.currentBlock.frame.execute(paramInt, 0, null, null);
        (paramLabel.getFirst()).status |= 0x10;
        addSuccessor(0, paramLabel);
        if (paramInt != 167)
          label = new Label(); 
      } else if (this.compute == 1) {
        this.currentBlock.frame.execute(paramInt, 0, null, null);
      } else if (paramInt == 168) {
        if ((paramLabel.status & 0x200) == 0) {
          paramLabel.status |= 0x200;
          this.subroutines++;
        } 
        this.currentBlock.status |= 0x80;
        addSuccessor(this.stackSize + 1, paramLabel);
        label = new Label();
      } else {
        this.stackSize += Frame.SIZE[paramInt];
        addSuccessor(this.stackSize, paramLabel);
      }  
    if ((paramLabel.status & 0x2) != 0 && paramLabel.position - this.code.length < -32768) {
      if (paramInt == 167) {
        this.code.putByte(200);
      } else if (paramInt == 168) {
        this.code.putByte(201);
      } else {
        label.status |= 0x10;
        this.code.putByte((paramInt <= 166) ? ((paramInt + 1 ^ 0x1) - 1) : (paramInt ^ 0x1));
        this.code.putShort(8);
        this.code.putByte(200);
      } 
      paramLabel.put(this, this.code, this.code.length - 1, true);
    } else {
      this.code.putByte(paramInt);
      paramLabel.put(this, this.code, this.code.length - 1, false);
    } 
    if (this.currentBlock != null) {
      visitLabel(label);
      if (paramInt == 167)
        noSuccessor(); 
    } 
  }
  
  public void visitLabel(Label paramLabel) {
    this.cw.hasAsmInsns |= paramLabel.resolve(this, this.code.length, this.code.data);
    if ((paramLabel.status & 0x1) != 0)
      return; 
    if (this.compute == 0) {
      if (this.currentBlock != null) {
        if (paramLabel.position == this.currentBlock.position) {
          this.currentBlock.status |= paramLabel.status & 0x10;
          paramLabel.frame = this.currentBlock.frame;
          return;
        } 
        addSuccessor(0, paramLabel);
      } 
      this.currentBlock = paramLabel;
      if (paramLabel.frame == null) {
        paramLabel.frame = new Frame();
        paramLabel.frame.owner = paramLabel;
      } 
      if (this.previousBlock != null) {
        if (paramLabel.position == this.previousBlock.position) {
          this.previousBlock.status |= paramLabel.status & 0x10;
          paramLabel.frame = this.previousBlock.frame;
          this.currentBlock = this.previousBlock;
          return;
        } 
        this.previousBlock.successor = paramLabel;
      } 
      this.previousBlock = paramLabel;
    } else if (this.compute == 1) {
      if (this.currentBlock == null) {
        this.currentBlock = paramLabel;
      } else {
        this.currentBlock.frame.owner = paramLabel;
      } 
    } else if (this.compute == 2) {
      if (this.currentBlock != null) {
        this.currentBlock.outputStackMax = this.maxStackSize;
        addSuccessor(this.stackSize, paramLabel);
      } 
      this.currentBlock = paramLabel;
      this.stackSize = 0;
      this.maxStackSize = 0;
      if (this.previousBlock != null)
        this.previousBlock.successor = paramLabel; 
      this.previousBlock = paramLabel;
    } 
  }
  
  public void visitLdcInsn(Object paramObject) {
    this.lastCodeOffset = this.code.length;
    Item item = this.cw.newConstItem(paramObject);
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(18, 0, this.cw, item);
      } else {
        int j;
        if (item.type == 5 || item.type == 6) {
          j = this.stackSize + 2;
        } else {
          j = this.stackSize + 1;
        } 
        if (j > this.maxStackSize)
          this.maxStackSize = j; 
        this.stackSize = j;
      }  
    int i = item.index;
    if (item.type == 5 || item.type == 6) {
      this.code.put12(20, i);
    } else if (i >= 256) {
      this.code.put12(19, i);
    } else {
      this.code.put11(18, i);
    } 
  }
  
  public void visitIincInsn(int paramInt1, int paramInt2) {
    this.lastCodeOffset = this.code.length;
    if (this.currentBlock != null && (this.compute == 0 || this.compute == 1))
      this.currentBlock.frame.execute(132, paramInt1, null, null); 
    if (this.compute != 3) {
      int i = paramInt1 + 1;
      if (i > this.maxLocals)
        this.maxLocals = i; 
    } 
    if (paramInt1 > 255 || paramInt2 > 127 || paramInt2 < -128) {
      this.code.putByte(196).put12(132, paramInt1).putShort(paramInt2);
    } else {
      this.code.putByte(132).put11(paramInt1, paramInt2);
    } 
  }
  
  public void visitTableSwitchInsn(int paramInt1, int paramInt2, Label paramLabel, Label... paramVarArgs) {
    this.lastCodeOffset = this.code.length;
    int i = this.code.length;
    this.code.putByte(170);
    this.code.putByteArray(null, 0, (4 - this.code.length % 4) % 4);
    paramLabel.put(this, this.code, i, true);
    this.code.putInt(paramInt1).putInt(paramInt2);
    for (byte b = 0; b < paramVarArgs.length; b++)
      paramVarArgs[b].put(this, this.code, i, true); 
    visitSwitchInsn(paramLabel, paramVarArgs);
  }
  
  public void visitLookupSwitchInsn(Label paramLabel, int[] paramArrayOfint, Label[] paramArrayOfLabel) {
    this.lastCodeOffset = this.code.length;
    int i = this.code.length;
    this.code.putByte(171);
    this.code.putByteArray(null, 0, (4 - this.code.length % 4) % 4);
    paramLabel.put(this, this.code, i, true);
    this.code.putInt(paramArrayOfLabel.length);
    for (byte b = 0; b < paramArrayOfLabel.length; b++) {
      this.code.putInt(paramArrayOfint[b]);
      paramArrayOfLabel[b].put(this, this.code, i, true);
    } 
    visitSwitchInsn(paramLabel, paramArrayOfLabel);
  }
  
  private void visitSwitchInsn(Label paramLabel, Label[] paramArrayOfLabel) {
    if (this.currentBlock != null) {
      if (this.compute == 0) {
        this.currentBlock.frame.execute(171, 0, null, null);
        addSuccessor(0, paramLabel);
        (paramLabel.getFirst()).status |= 0x10;
        for (byte b = 0; b < paramArrayOfLabel.length; b++) {
          addSuccessor(0, paramArrayOfLabel[b]);
          (paramArrayOfLabel[b].getFirst()).status |= 0x10;
        } 
      } else {
        this.stackSize--;
        addSuccessor(this.stackSize, paramLabel);
        for (byte b = 0; b < paramArrayOfLabel.length; b++)
          addSuccessor(this.stackSize, paramArrayOfLabel[b]); 
      } 
      noSuccessor();
    } 
  }
  
  public void visitMultiANewArrayInsn(String paramString, int paramInt) {
    this.lastCodeOffset = this.code.length;
    Item item = this.cw.newClassItem(paramString);
    if (this.currentBlock != null)
      if (this.compute == 0 || this.compute == 1) {
        this.currentBlock.frame.execute(197, paramInt, this.cw, item);
      } else {
        this.stackSize += 1 - paramInt;
      }  
    this.code.put12(197, item.index).putByte(paramInt);
  }
  
  public AnnotationVisitor visitInsnAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    paramInt = paramInt & 0xFF0000FF | this.lastCodeOffset << 8;
    AnnotationWriter.putTarget(paramInt, paramTypePath, byteVector);
    byteVector.putShort(this.cw.newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
    annotationWriter.next = this.ctanns;
    this.ctanns = annotationWriter;
    return annotationWriter;
  }
  
  public void visitTryCatchBlock(Label paramLabel1, Label paramLabel2, Label paramLabel3, String paramString) {
    this.handlerCount++;
    Handler handler = new Handler();
    handler.start = paramLabel1;
    handler.end = paramLabel2;
    handler.handler = paramLabel3;
    handler.desc = paramString;
  }
  
  public AnnotationVisitor visitTryCatchAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    AnnotationWriter.putTarget(paramInt, paramTypePath, byteVector);
    byteVector.putShort(this.cw.newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
    annotationWriter.next = this.ctanns;
    this.ctanns = annotationWriter;
    return annotationWriter;
  }
  
  public void visitLocalVariable(String paramString1, String paramString2, String paramString3, Label paramLabel1, Label paramLabel2, int paramInt) {
    if (this.localVarType == null)
      this.localVarType = new ByteVector(); 
    this.localVarTypeCount++;
    this.localVarType.putShort(paramLabel1.position).putShort(paramLabel2.position - paramLabel1.position).putShort(this.cw.newUTF8(paramString1)).putShort(this.cw.newUTF8(paramString3)).putShort(paramInt);
    if (this.localVar == null)
      this.localVar = new ByteVector(); 
    this.localVarCount++;
    this.localVar.putShort(paramLabel1.position).putShort(paramLabel2.position - paramLabel1.position).putShort(this.cw.newUTF8(paramString1)).putShort(this.cw.newUTF8(paramString2)).putShort(paramInt);
    if (this.compute != 3) {
      char c = paramString2.charAt(0);
      int i = paramInt + ((c == 'J' || c == 'D') ? 2 : 1);
      if (i > this.maxLocals)
        this.maxLocals = i; 
    } 
  }
  
  public AnnotationVisitor visitLocalVariableAnnotation(int paramInt, TypePath paramTypePath, Label[] paramArrayOfLabel1, Label[] paramArrayOfLabel2, int[] paramArrayOfint, String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    byteVector.putByte(paramInt >>> 24).putShort(paramArrayOfLabel1.length);
    for (byte b = 0; b < paramArrayOfLabel1.length; b++)
      byteVector.putShort((paramArrayOfLabel1[b]).position).putShort((paramArrayOfLabel2[b]).position - (paramArrayOfLabel1[b]).position).putShort(paramArrayOfint[b]); 
    byteVector.putByte(0);
    byteVector.putShort(this.cw.newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this.cw, true, byteVector, byteVector, byteVector.length - 2);
    annotationWriter.next = this.ctanns;
    this.ctanns = annotationWriter;
    return annotationWriter;
  }
  
  public void visitLineNumber(int paramInt, Label paramLabel) {
    if (this.lineNumber == null)
      this.lineNumber = new ByteVector(); 
    this.lineNumberCount++;
    this.lineNumber.putShort(paramLabel.position);
    this.lineNumber.putShort(paramInt);
  }
  
  public void visitMaxs(int paramInt1, int paramInt2) {
    if (this.compute == 0)
      for (Handler handler = this.firstHandler;; handler = handler.next) {
        Label label1 = handler.start.getFirst();
        Label label2 = handler.handler.getFirst();
        Label label3 = handler.end.getFirst();
        String str = (handler.desc == null) ? "java/lang/Throwable" : handler.desc;
        int i = 0x1700000 | this.cw.addType(str);
        label2.status |= 0x10;
        while (label1 != label3) {
          Edge edge = new Edge();
          edge.info = i;
          edge.successor = label2;
          edge.next = label1.successors;
          label1.successors = edge;
          label1 = label1.successor;
        } 
      }  
    if (this.compute == 2)
      for (Handler handler = this.firstHandler;; handler = handler.next) {
        Label label1 = handler.start;
        Label label2 = handler.handler;
        Label label3 = handler.end;
        while (label1 != label3) {
          Edge edge = new Edge();
          edge.info = Integer.MAX_VALUE;
          edge.successor = label2;
          if ((label1.status & 0x80) == 0) {
            edge.next = label1.successors;
            label1.successors = edge;
          } else {
            edge.next = label1.successors.next.next;
            label1.successors.next.next = edge;
          } 
          label1 = label1.successor;
        } 
      }  
    this.maxStack = paramInt1;
    this.maxLocals = paramInt2;
  }
  
  public void visitEnd() {}
  
  private void addSuccessor(int paramInt, Label paramLabel) {
    Edge edge = new Edge();
    edge.info = paramInt;
    edge.successor = paramLabel;
    edge.next = this.currentBlock.successors;
    this.currentBlock.successors = edge;
  }
  
  private void noSuccessor() {
    if (this.compute == 0) {
      Label label = new Label();
      label.frame = new Frame();
      label.frame.owner = label;
      label.resolve(this, this.code.length, this.code.data);
      this.previousBlock.successor = label;
      this.previousBlock = label;
    } else {
      this.currentBlock.outputStackMax = this.maxStackSize;
    } 
    if (this.compute != 1)
      this.currentBlock = null; 
  }
  
  private void visitFrame(Frame paramFrame) {
    byte b1 = 0;
    int i = 0;
    byte b2 = 0;
    int[] arrayOfInt1 = paramFrame.inputLocals;
    int[] arrayOfInt2 = paramFrame.inputStack;
    byte b3;
    for (b3 = 0; b3 < arrayOfInt1.length; b3++) {
      int k = arrayOfInt1[b3];
      if (k == 16777216) {
        b1++;
      } else {
        i += b1 + 1;
        b1 = 0;
      } 
      if (k == 16777220 || k == 16777219)
        b3++; 
    } 
    for (b3 = 0; b3 < arrayOfInt2.length; b3++) {
      int k = arrayOfInt2[b3];
      b2++;
      if (k == 16777220 || k == 16777219)
        b3++; 
    } 
    int j = startFrame(paramFrame.owner.position, i, b2);
    b3 = 0;
    for (b3 = 0; b3 < arrayOfInt2.length; b3++) {
      int k = arrayOfInt2[b3];
      this.frame[j++] = k;
      if (k == 16777220 || k == 16777219)
        b3++; 
    } 
    endFrame();
  }
  
  private void visitImplicitFirstFrame() {
    int i = startFrame(0, this.descriptor.length() + 1, 0);
    if ((this.access & 0x8) == 0)
      if ((this.access & 0x80000) == 0) {
        this.frame[i++] = 0x1700000 | this.cw.addType(this.cw.thisName);
      } else {
        this.frame[i++] = 6;
      }  
    byte b = 1;
    while (true) {
      byte b1 = b;
      switch (this.descriptor.charAt(b++)) {
        case 'B':
        case 'C':
        case 'I':
        case 'S':
        case 'Z':
          this.frame[i++] = 1;
          continue;
        case 'F':
          this.frame[i++] = 2;
          continue;
        case 'J':
          this.frame[i++] = 4;
          continue;
        case 'D':
          this.frame[i++] = 3;
          continue;
        case '[':
          while (this.descriptor.charAt(b) == '[')
            b++; 
          if (this.descriptor.charAt(b) == 'L')
            while (this.descriptor.charAt(++b) != ';')
              b++;  
          this.frame[i++] = 0x1700000 | this.cw.addType(this.descriptor.substring(b1, ++b));
          continue;
        case 'L':
          while (this.descriptor.charAt(b) != ';')
            b++; 
          this.frame[i++] = 0x1700000 | this.cw.addType(this.descriptor.substring(b1 + 1, b++));
          continue;
      } 
      this.frame[1] = i - 3;
      endFrame();
      return;
    } 
  }
  
  private int startFrame(int paramInt1, int paramInt2, int paramInt3) {
    int i = 3 + paramInt2 + paramInt3;
    if (this.frame == null || this.frame.length < i)
      this.frame = new int[i]; 
    this.frame[0] = paramInt1;
    this.frame[1] = paramInt2;
    this.frame[2] = paramInt3;
    return 3;
  }
  
  private void endFrame() {
    if (this.previousFrame != null) {
      if (this.stackMap == null)
        this.stackMap = new ByteVector(); 
      writeFrame();
      this.frameCount++;
    } 
    this.previousFrame = this.frame;
    this.frame = null;
  }
  
  private void writeFrame() {
    int n;
    int i = this.frame[1];
    int j = this.frame[2];
    if ((this.cw.version & 0xFFFF) < 50) {
      this.stackMap.putShort(this.frame[0]).putShort(i);
      writeFrameTypes(3, 3 + i);
      this.stackMap.putShort(j);
      writeFrameTypes(3 + i, 3 + i + j);
      return;
    } 
    int k = this.previousFrame[1];
    char c = 'ÿ';
    int m = 0;
    if (this.frameCount == 0) {
      n = this.frame[0];
    } else {
      n = this.frame[0] - this.previousFrame[0] - 1;
    } 
    m = i - k;
    switch (m) {
      case -3:
      case -2:
      case -1:
        c = 'ø';
        k = i;
        break;
      case 0:
        c = (n < 64) ? Character.MIN_VALUE : 'û';
        break;
      case 1:
      case 2:
      case 3:
        c = 'ü';
        break;
    } 
    'ÿ';
    this.stackMap.putByte(255).putShort(n).putShort(i);
    writeFrameTypes(3, 3 + i);
    this.stackMap.putShort(j);
    writeFrameTypes(3 + i, 3 + i + j);
  }
  
  private void writeFrameTypes(int paramInt1, int paramInt2) {
    for (int i = paramInt1; i < paramInt2; i++) {
      int j = this.frame[i];
      int k = j & 0xF0000000;
      int m = j & 0xFFFFF;
      switch (j & 0xFF00000) {
        case 24117248:
          this.stackMap.putByte(7).putShort(this.cw.newClass((this.cw.typeTable[m]).strVal1));
          break;
        case 25165824:
          this.stackMap.putByte(8).putShort((this.cw.typeTable[m]).intVal);
          break;
        default:
          this.stackMap.putByte(m);
          break;
      } 
    } 
  }
  
  private void writeFrameType(Object paramObject) {
    if (paramObject instanceof String) {
      this.stackMap.putByte(7).putShort(this.cw.newClass((String)paramObject));
    } else if (paramObject instanceof Integer) {
      this.stackMap.putByte(((Integer)paramObject).intValue());
    } else {
      this.stackMap.putByte(8).putShort(((Label)paramObject).position);
    } 
  }
  
  final int getSize() {
    if (this.classReaderOffset != 0)
      return 6 + this.classReaderLength; 
    int i = 8;
    if (this.code.length > 0) {
      if (this.code.length > 65535)
        throw new RuntimeException("Method code too large!"); 
      this.cw.newUTF8("Code");
      i += 18 + this.code.length + 8 * this.handlerCount;
      if (this.localVar != null) {
        this.cw.newUTF8("LocalVariableTable");
        i += 8 + this.localVar.length;
      } 
      if (this.localVarType != null) {
        this.cw.newUTF8("LocalVariableTypeTable");
        i += 8 + this.localVarType.length;
      } 
      if (this.lineNumber != null) {
        this.cw.newUTF8("LineNumberTable");
        i += 8 + this.lineNumber.length;
      } 
      if (this.stackMap != null) {
        boolean bool = ((this.cw.version & 0xFFFF) >= 50) ? true : false;
        this.cw.newUTF8("StackMap");
        i += 8 + this.stackMap.length;
      } 
      if (this.ctanns != null) {
        this.cw.newUTF8("RuntimeVisibleTypeAnnotations");
        i += 8 + this.ctanns.getSize();
      } 
      if (this.ictanns != null) {
        this.cw.newUTF8("RuntimeInvisibleTypeAnnotations");
        i += 8 + this.ictanns.getSize();
      } 
      if (this.cattrs != null)
        i += this.cattrs.getSize(this.cw, this.code.data, this.code.length, this.maxStack, this.maxLocals); 
    } 
    if (this.exceptionCount > 0) {
      this.cw.newUTF8("Exceptions");
      i += 8 + 2 * this.exceptionCount;
    } 
    if ((this.access & 0x1000) != 0 && ((this.cw.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0)) {
      this.cw.newUTF8("Synthetic");
      i += 6;
    } 
    if ((this.access & 0x20000) != 0) {
      this.cw.newUTF8("Deprecated");
      i += 6;
    } 
    if (this.signature != null) {
      this.cw.newUTF8("Signature");
      this.cw.newUTF8(this.signature);
      i += 8;
    } 
    if (this.methodParameters != null) {
      this.cw.newUTF8("MethodParameters");
      i += 7 + this.methodParameters.length;
    } 
    if (this.annd != null) {
      this.cw.newUTF8("AnnotationDefault");
      i += 6 + this.annd.length;
    } 
    if (this.anns != null) {
      this.cw.newUTF8("RuntimeVisibleAnnotations");
      i += 8 + this.anns.getSize();
    } 
    if (this.ianns != null) {
      this.cw.newUTF8("RuntimeInvisibleAnnotations");
      i += 8 + this.ianns.getSize();
    } 
    if (this.tanns != null) {
      this.cw.newUTF8("RuntimeVisibleTypeAnnotations");
      i += 8 + this.tanns.getSize();
    } 
    if (this.itanns != null) {
      this.cw.newUTF8("RuntimeInvisibleTypeAnnotations");
      i += 8 + this.itanns.getSize();
    } 
    if (this.panns != null) {
      this.cw.newUTF8("RuntimeVisibleParameterAnnotations");
      i += 7 + 2 * (this.panns.length - this.synthetics);
      for (int j = this.panns.length - 1; j >= this.synthetics; j--)
        i += (this.panns[j] == null) ? 0 : this.panns[j].getSize(); 
    } 
    if (this.ipanns != null) {
      this.cw.newUTF8("RuntimeInvisibleParameterAnnotations");
      i += 7 + 2 * (this.ipanns.length - this.synthetics);
      for (int j = this.ipanns.length - 1; j >= this.synthetics; j--)
        i += (this.ipanns[j] == null) ? 0 : this.ipanns[j].getSize(); 
    } 
    if (this.attrs != null)
      i += this.attrs.getSize(this.cw, null, 0, -1, -1); 
    return i;
  }
  
  final void put(ByteVector paramByteVector) {
    64;
    int i = 0xE0000 | (this.access & 0x40000) / 64;
    paramByteVector.putShort(this.access & (i ^ 0xFFFFFFFF)).putShort(this.name).putShort(this.desc);
    if (this.classReaderOffset != 0) {
      paramByteVector.putByteArray(this.cw.cr.b, this.classReaderOffset, this.classReaderLength);
      return;
    } 
    int j = 0;
    if (this.code.length > 0)
      j++; 
    if (this.exceptionCount > 0)
      j++; 
    if ((this.access & 0x1000) != 0 && ((this.cw.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0))
      j++; 
    if ((this.access & 0x20000) != 0)
      j++; 
    if (this.signature != null)
      j++; 
    if (this.methodParameters != null)
      j++; 
    if (this.annd != null)
      j++; 
    if (this.anns != null)
      j++; 
    if (this.ianns != null)
      j++; 
    if (this.tanns != null)
      j++; 
    if (this.itanns != null)
      j++; 
    if (this.panns != null)
      j++; 
    if (this.ipanns != null)
      j++; 
    if (this.attrs != null)
      j += this.attrs.getCount(); 
    paramByteVector.putShort(j);
    if (this.code.length > 0) {
      int k = 12 + this.code.length + 8 * this.handlerCount;
      if (this.localVar != null)
        k += 8 + this.localVar.length; 
      if (this.localVarType != null)
        k += 8 + this.localVarType.length; 
      if (this.lineNumber != null)
        k += 8 + this.lineNumber.length; 
      if (this.stackMap != null)
        k += 8 + this.stackMap.length; 
      if (this.ctanns != null)
        k += 8 + this.ctanns.getSize(); 
      if (this.ictanns != null)
        k += 8 + this.ictanns.getSize(); 
      if (this.cattrs != null)
        k += this.cattrs.getSize(this.cw, this.code.data, this.code.length, this.maxStack, this.maxLocals); 
      paramByteVector.putShort(this.cw.newUTF8("Code")).putInt(k);
      paramByteVector.putShort(this.maxStack).putShort(this.maxLocals);
      paramByteVector.putInt(this.code.length).putByteArray(this.code.data, 0, this.code.length);
      paramByteVector.putShort(this.handlerCount);
      if (this.handlerCount > 0)
        for (Handler handler = this.firstHandler;; handler = handler.next)
          paramByteVector.putShort(handler.start.position).putShort(handler.end.position).putShort(handler.handler.position).putShort(handler.type);  
      j = 0;
      if (this.localVar != null)
        j++; 
      if (this.localVarType != null)
        j++; 
      if (this.lineNumber != null)
        j++; 
      if (this.stackMap != null)
        j++; 
      if (this.ctanns != null)
        j++; 
      if (this.ictanns != null)
        j++; 
      if (this.cattrs != null)
        j += this.cattrs.getCount(); 
      paramByteVector.putShort(j);
      if (this.localVar != null) {
        paramByteVector.putShort(this.cw.newUTF8("LocalVariableTable"));
        paramByteVector.putInt(this.localVar.length + 2).putShort(this.localVarCount);
        paramByteVector.putByteArray(this.localVar.data, 0, this.localVar.length);
      } 
      if (this.localVarType != null) {
        paramByteVector.putShort(this.cw.newUTF8("LocalVariableTypeTable"));
        paramByteVector.putInt(this.localVarType.length + 2).putShort(this.localVarTypeCount);
        paramByteVector.putByteArray(this.localVarType.data, 0, this.localVarType.length);
      } 
      if (this.lineNumber != null) {
        paramByteVector.putShort(this.cw.newUTF8("LineNumberTable"));
        paramByteVector.putInt(this.lineNumber.length + 2).putShort(this.lineNumberCount);
        paramByteVector.putByteArray(this.lineNumber.data, 0, this.lineNumber.length);
      } 
      if (this.stackMap != null)
        boolean bool = ((this.cw.version & 0xFFFF) >= 50) ? true : false; 
      if (this.ctanns != null) {
        paramByteVector.putShort(this.cw.newUTF8("RuntimeVisibleTypeAnnotations"));
        this.ctanns.put(paramByteVector);
      } 
      if (this.ictanns != null) {
        paramByteVector.putShort(this.cw.newUTF8("RuntimeInvisibleTypeAnnotations"));
        this.ictanns.put(paramByteVector);
      } 
      if (this.cattrs != null)
        this.cattrs.put(this.cw, this.code.data, this.code.length, this.maxLocals, this.maxStack, paramByteVector); 
    } 
    if (this.exceptionCount > 0) {
      paramByteVector.putShort(this.cw.newUTF8("Exceptions")).putInt(2 * this.exceptionCount + 2);
      paramByteVector.putShort(this.exceptionCount);
      for (byte b = 0; b < this.exceptionCount; b++)
        paramByteVector.putShort(this.exceptions[b]); 
    } 
    if ((this.access & 0x1000) != 0 && ((this.cw.version & 0xFFFF) < 49 || (this.access & 0x40000) != 0))
      paramByteVector.putShort(this.cw.newUTF8("Synthetic")).putInt(0); 
    if ((this.access & 0x20000) != 0)
      paramByteVector.putShort(this.cw.newUTF8("Deprecated")).putInt(0); 
    if (this.signature != null)
      paramByteVector.putShort(this.cw.newUTF8("Signature")).putInt(2).putShort(this.cw.newUTF8(this.signature)); 
    if (this.methodParameters != null) {
      paramByteVector.putShort(this.cw.newUTF8("MethodParameters"));
      paramByteVector.putInt(this.methodParameters.length + 1).putByte(this.methodParametersCount);
      paramByteVector.putByteArray(this.methodParameters.data, 0, this.methodParameters.length);
    } 
    if (this.annd != null) {
      paramByteVector.putShort(this.cw.newUTF8("AnnotationDefault"));
      paramByteVector.putInt(this.annd.length);
      paramByteVector.putByteArray(this.annd.data, 0, this.annd.length);
    } 
    if (this.anns != null) {
      paramByteVector.putShort(this.cw.newUTF8("RuntimeVisibleAnnotations"));
      this.anns.put(paramByteVector);
    } 
    if (this.ianns != null) {
      paramByteVector.putShort(this.cw.newUTF8("RuntimeInvisibleAnnotations"));
      this.ianns.put(paramByteVector);
    } 
    if (this.tanns != null) {
      paramByteVector.putShort(this.cw.newUTF8("RuntimeVisibleTypeAnnotations"));
      this.tanns.put(paramByteVector);
    } 
    if (this.itanns != null) {
      paramByteVector.putShort(this.cw.newUTF8("RuntimeInvisibleTypeAnnotations"));
      this.itanns.put(paramByteVector);
    } 
    if (this.panns != null) {
      paramByteVector.putShort(this.cw.newUTF8("RuntimeVisibleParameterAnnotations"));
      AnnotationWriter.put(this.panns, this.synthetics, paramByteVector);
    } 
    if (this.ipanns != null) {
      paramByteVector.putShort(this.cw.newUTF8("RuntimeInvisibleParameterAnnotations"));
      AnnotationWriter.put(this.ipanns, this.synthetics, paramByteVector);
    } 
    if (this.attrs != null)
      this.attrs.put(this.cw, null, 0, -1, -1, paramByteVector); 
  }
}
