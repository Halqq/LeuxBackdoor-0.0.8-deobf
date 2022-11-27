package org.spongepowered.asm.lib;

public class ClassWriter extends ClassVisitor {
  public static final int COMPUTE_MAXS = 1;
  
  public static final int COMPUTE_FRAMES = 2;
  
  static final int ACC_SYNTHETIC_ATTRIBUTE = 262144;
  
  static final int TO_ACC_SYNTHETIC = 64;
  
  static final int NOARG_INSN = 0;
  
  static final int SBYTE_INSN = 1;
  
  static final int SHORT_INSN = 2;
  
  static final int VAR_INSN = 3;
  
  static final int IMPLVAR_INSN = 4;
  
  static final int TYPE_INSN = 5;
  
  static final int FIELDORMETH_INSN = 6;
  
  static final int ITFMETH_INSN = 7;
  
  static final int INDYMETH_INSN = 8;
  
  static final int LABEL_INSN = 9;
  
  static final int LABELW_INSN = 10;
  
  static final int LDC_INSN = 11;
  
  static final int LDCW_INSN = 12;
  
  static final int IINC_INSN = 13;
  
  static final int TABL_INSN = 14;
  
  static final int LOOK_INSN = 15;
  
  static final int MANA_INSN = 16;
  
  static final int WIDE_INSN = 17;
  
  static final int ASM_LABEL_INSN = 18;
  
  static final int F_INSERT = 256;
  
  static final byte[] TYPE;
  
  static final int CLASS = 7;
  
  static final int FIELD = 9;
  
  static final int METH = 10;
  
  static final int IMETH = 11;
  
  static final int STR = 8;
  
  static final int INT = 3;
  
  static final int FLOAT = 4;
  
  static final int LONG = 5;
  
  static final int DOUBLE = 6;
  
  static final int NAME_TYPE = 12;
  
  static final int UTF8 = 1;
  
  static final int MTYPE = 16;
  
  static final int HANDLE = 15;
  
  static final int INDY = 18;
  
  static final int HANDLE_BASE = 20;
  
  static final int TYPE_NORMAL = 30;
  
  static final int TYPE_UNINIT = 31;
  
  static final int TYPE_MERGED = 32;
  
  static final int BSM = 33;
  
  ClassReader cr;
  
  int version;
  
  int index = 1;
  
  final ByteVector pool = new ByteVector();
  
  Item[] items = new Item[256];
  
  int threshold = (int)(0.75D * this.items.length);
  
  final Item key = new Item();
  
  final Item key2 = new Item();
  
  final Item key3 = new Item();
  
  final Item key4 = new Item();
  
  Item[] typeTable;
  
  private short typeCount;
  
  private int access;
  
  private int name;
  
  String thisName;
  
  private int signature;
  
  private int superName;
  
  private int interfaceCount;
  
  private int[] interfaces;
  
  private int sourceFile;
  
  private ByteVector sourceDebug;
  
  private int enclosingMethodOwner;
  
  private int enclosingMethod;
  
  private AnnotationWriter anns;
  
  private AnnotationWriter ianns;
  
  private AnnotationWriter tanns;
  
  private AnnotationWriter itanns;
  
  private Attribute attrs;
  
  private int innerClassesCount;
  
  private ByteVector innerClasses;
  
  int bootstrapMethodsCount;
  
  ByteVector bootstrapMethods;
  
  FieldWriter firstField;
  
  FieldWriter lastField;
  
  MethodWriter firstMethod;
  
  MethodWriter lastMethod;
  
  private int compute;
  
  boolean hasAsmInsns;
  
  public ClassWriter(int paramInt) {
    super(327680);
    this.compute = ((paramInt & 0x2) != 0) ? 0 : (((paramInt & 0x1) != 0) ? 2 : 3);
  }
  
  public ClassWriter(ClassReader paramClassReader, int paramInt) {
    this(paramInt);
    paramClassReader.copyPool(this);
    this.cr = paramClassReader;
  }
  
  public final void visit(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    paramInt2 = 1;
    this.version = paramInt1;
    this.access = paramInt2;
    this.name = newClass(paramString1);
    this.thisName = paramString1;
    this.signature = newUTF8(paramString2);
  }
  
  public final void visitSource(String paramString1, String paramString2) {
    this.sourceFile = newUTF8(paramString1);
    this.sourceDebug = (new ByteVector()).encodeUTF8(paramString2, 0, 2147483647);
  }
  
  public final void visitOuterClass(String paramString1, String paramString2, String paramString3) {
    this.enclosingMethodOwner = newClass(paramString1);
    this.enclosingMethod = newNameType(paramString2, paramString3);
  }
  
  public final AnnotationVisitor visitAnnotation(String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    byteVector.putShort(newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
    annotationWriter.next = this.anns;
    this.anns = annotationWriter;
    return annotationWriter;
  }
  
  public final AnnotationVisitor visitTypeAnnotation(int paramInt, TypePath paramTypePath, String paramString, boolean paramBoolean) {
    ByteVector byteVector = new ByteVector();
    AnnotationWriter.putTarget(paramInt, paramTypePath, byteVector);
    byteVector.putShort(newUTF8(paramString)).putShort(0);
    AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, byteVector.length - 2);
    annotationWriter.next = this.tanns;
    this.tanns = annotationWriter;
    return annotationWriter;
  }
  
  public final void visitAttribute(Attribute paramAttribute) {
    paramAttribute.next = this.attrs;
    this.attrs = paramAttribute;
  }
  
  public final void visitInnerClass(String paramString1, String paramString2, String paramString3, int paramInt) {
    if (this.innerClasses == null)
      this.innerClasses = new ByteVector(); 
    Item item = newClassItem(paramString1);
    if (item.intVal == 0) {
      this.innerClassesCount++;
      this.innerClasses.putShort(item.index);
    } 
  }
  
  public final FieldVisitor visitField(int paramInt, String paramString1, String paramString2, String paramString3, Object paramObject) {
    return new FieldWriter(this, paramInt, paramString1, paramString2, paramString3, paramObject);
  }
  
  public final MethodVisitor visitMethod(int paramInt, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString) {
    paramString2 = "()V";
    paramString1 = "<init>";
    paramInt = 1;
    return new MethodWriter(this, paramInt, paramString1, paramString2, paramString3, paramArrayOfString, this.compute);
  }
  
  public final void visitEnd() {}
  
  public byte[] toByteArray() {
    if (this.index > 65535)
      throw new RuntimeException("Class file too large!"); 
    int i = 24 + 2 * this.interfaceCount;
    byte b = 0;
    for (FieldWriter fieldWriter = this.firstField;; fieldWriter = (FieldWriter)fieldWriter.fv) {
      b++;
      i += fieldWriter.getSize();
    } 
  }
  
  Item newConstItem(Object paramObject) {
    if (paramObject instanceof Integer) {
      int i = ((Integer)paramObject).intValue();
      return newInteger(i);
    } 
    if (paramObject instanceof Byte) {
      int i = ((Byte)paramObject).intValue();
      return newInteger(i);
    } 
    if (paramObject instanceof Character) {
      char c = ((Character)paramObject).charValue();
      return newInteger(c);
    } 
    if (paramObject instanceof Short) {
      int i = ((Short)paramObject).intValue();
      return newInteger(i);
    } 
    if (paramObject instanceof Boolean) {
      boolean bool = ((Boolean)paramObject).booleanValue() ? true : false;
      return newInteger(bool);
    } 
    if (paramObject instanceof Float) {
      float f = ((Float)paramObject).floatValue();
      return newFloat(f);
    } 
    if (paramObject instanceof Long) {
      long l = ((Long)paramObject).longValue();
      return newLong(l);
    } 
    if (paramObject instanceof Double) {
      double d = ((Double)paramObject).doubleValue();
      return newDouble(d);
    } 
    if (paramObject instanceof String)
      return newString((String)paramObject); 
    if (paramObject instanceof Type) {
      Type type = (Type)paramObject;
      int i = type.getSort();
      return (i == 10) ? newClassItem(type.getInternalName()) : ((i == 11) ? newMethodTypeItem(type.getDescriptor()) : newClassItem(type.getDescriptor()));
    } 
    if (paramObject instanceof Handle) {
      Handle handle = (Handle)paramObject;
      return newHandleItem(handle.tag, handle.owner, handle.name, handle.desc, handle.itf);
    } 
    throw new IllegalArgumentException("value " + paramObject);
  }
  
  public int newConst(Object paramObject) {
    return (newConstItem(paramObject)).index;
  }
  
  public int newUTF8(String paramString) {
    this.key.set(1, paramString, null, null);
    Item item = get(this.key);
    this.pool.putByte(1).putUTF8(paramString);
    item = new Item(this.index++, this.key);
    put(item);
    return item.index;
  }
  
  Item newClassItem(String paramString) {
    this.key2.set(7, paramString, null, null);
    Item item = get(this.key2);
    this.pool.put12(7, newUTF8(paramString));
    item = new Item(this.index++, this.key2);
    put(item);
    return item;
  }
  
  public int newClass(String paramString) {
    return (newClassItem(paramString)).index;
  }
  
  Item newMethodTypeItem(String paramString) {
    this.key2.set(16, paramString, null, null);
    Item item = get(this.key2);
    this.pool.put12(16, newUTF8(paramString));
    item = new Item(this.index++, this.key2);
    put(item);
    return item;
  }
  
  public int newMethodType(String paramString) {
    return (newMethodTypeItem(paramString)).index;
  }
  
  Item newHandleItem(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    this.key4.set(20 + paramInt, paramString1, paramString2, paramString3);
    Item item = get(this.key4);
    if (paramInt <= 4) {
      put112(15, paramInt, newField(paramString1, paramString2, paramString3));
    } else {
      put112(15, paramInt, newMethod(paramString1, paramString2, paramString3, paramBoolean));
    } 
    item = new Item(this.index++, this.key4);
    put(item);
    return item;
  }
  
  @Deprecated
  @Deprecated
  public int newHandle(int paramInt, String paramString1, String paramString2, String paramString3) {
    return newHandle(paramInt, paramString1, paramString2, paramString3, (paramInt == 9));
  }
  
  public int newHandle(int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    return (newHandleItem(paramInt, paramString1, paramString2, paramString3, paramBoolean)).index;
  }
  
  Item newInvokeDynamicItem(String paramString1, String paramString2, Handle paramHandle, Object... paramVarArgs) {
    ByteVector byteVector = this.bootstrapMethods;
    byteVector = this.bootstrapMethods = new ByteVector();
    int i = byteVector.length;
    int j = paramHandle.hashCode();
    byteVector.putShort(newHandle(paramHandle.tag, paramHandle.owner, paramHandle.name, paramHandle.desc, paramHandle.isInterface()));
    int k = paramVarArgs.length;
    byteVector.putShort(k);
    for (byte b = 0; b < k; b++) {
      Object object = paramVarArgs[b];
      j ^= object.hashCode();
      byteVector.putShort(newConst(object));
    } 
    byte[] arrayOfByte = byteVector.data;
    int m = 2 + k << 1;
    j &= Integer.MAX_VALUE;
    Item item = this.items[j % this.items.length];
    label40: while (true) {
      if (item.type != 33 || item.hashCode != j) {
        item = item.next;
        continue;
      } 
      int n = item.intVal;
      for (byte b1 = 0; b1 < m; b1++) {
        if (arrayOfByte[i + b1] != arrayOfByte[n + b1]) {
          item = item.next;
          continue label40;
        } 
      } 
      n = item.index;
      byteVector.length = i;
      this.key3.set(paramString1, paramString2, n);
      item = get(this.key3);
      put122(18, n, newNameType(paramString1, paramString2));
      item = new Item(this.index++, this.key3);
      put(item);
      return item;
    } 
  }
  
  public int newInvokeDynamic(String paramString1, String paramString2, Handle paramHandle, Object... paramVarArgs) {
    return (newInvokeDynamicItem(paramString1, paramString2, paramHandle, paramVarArgs)).index;
  }
  
  Item newFieldItem(String paramString1, String paramString2, String paramString3) {
    this.key3.set(9, paramString1, paramString2, paramString3);
    Item item = get(this.key3);
    put122(9, newClass(paramString1), newNameType(paramString2, paramString3));
    item = new Item(this.index++, this.key3);
    put(item);
    return item;
  }
  
  public int newField(String paramString1, String paramString2, String paramString3) {
    return (newFieldItem(paramString1, paramString2, paramString3)).index;
  }
  
  Item newMethodItem(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {}
  
  public int newMethod(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    return (newMethodItem(paramString1, paramString2, paramString3, paramBoolean)).index;
  }
  
  Item newInteger(int paramInt) {
    this.key.set(paramInt);
    Item item = get(this.key);
    this.pool.putByte(3).putInt(paramInt);
    item = new Item(this.index++, this.key);
    put(item);
    return item;
  }
  
  Item newFloat(float paramFloat) {
    this.key.set(paramFloat);
    Item item = get(this.key);
    this.pool.putByte(4).putInt(this.key.intVal);
    item = new Item(this.index++, this.key);
    put(item);
    return item;
  }
  
  Item newLong(long paramLong) {
    this.key.set(paramLong);
    Item item = get(this.key);
    this.pool.putByte(5).putLong(paramLong);
    item = new Item(this.index, this.key);
    this.index += 2;
    put(item);
    return item;
  }
  
  Item newDouble(double paramDouble) {
    this.key.set(paramDouble);
    Item item = get(this.key);
    this.pool.putByte(6).putLong(this.key.longVal);
    item = new Item(this.index, this.key);
    this.index += 2;
    put(item);
    return item;
  }
  
  private Item newString(String paramString) {
    this.key2.set(8, paramString, null, null);
    Item item = get(this.key2);
    this.pool.put12(8, newUTF8(paramString));
    item = new Item(this.index++, this.key2);
    put(item);
    return item;
  }
  
  public int newNameType(String paramString1, String paramString2) {
    return (newNameTypeItem(paramString1, paramString2)).index;
  }
  
  Item newNameTypeItem(String paramString1, String paramString2) {
    this.key2.set(12, paramString1, paramString2, null);
    Item item = get(this.key2);
    put122(12, newUTF8(paramString1), newUTF8(paramString2));
    item = new Item(this.index++, this.key2);
    put(item);
    return item;
  }
  
  int addType(String paramString) {
    this.key.set(30, paramString, null, null);
    Item item = get(this.key);
    item = addType(this.key);
    return item.index;
  }
  
  int addUninitializedType(String paramString, int paramInt) {
    this.key.type = 31;
    this.key.intVal = paramInt;
    this.key.strVal1 = paramString;
    this.key.hashCode = Integer.MAX_VALUE & 31 + paramString.hashCode() + paramInt;
    Item item = get(this.key);
    item = addType(this.key);
    return item.index;
  }
  
  private Item addType(Item paramItem) {
    this.typeCount = (short)(this.typeCount + 1);
    Item item = new Item(this.typeCount, this.key);
    put(item);
    if (this.typeTable == null)
      this.typeTable = new Item[16]; 
    if (this.typeCount == this.typeTable.length) {
      Item[] arrayOfItem = new Item[2 * this.typeTable.length];
      System.arraycopy(this.typeTable, 0, arrayOfItem, 0, this.typeTable.length);
      this.typeTable = arrayOfItem;
    } 
    this.typeTable[this.typeCount] = item;
    return item;
  }
  
  int getMergedType(int paramInt1, int paramInt2) {
    this.key2.type = 32;
    this.key2.longVal = paramInt1 | paramInt2 << 32L;
    this.key2.hashCode = Integer.MAX_VALUE & 32 + paramInt1 + paramInt2;
    Item item = get(this.key2);
    String str1 = (this.typeTable[paramInt1]).strVal1;
    String str2 = (this.typeTable[paramInt2]).strVal1;
    this.key2.intVal = addType(getCommonSuperClass(str1, str2));
    item = new Item(0, this.key2);
    put(item);
    return item.intVal;
  }
  
  protected String getCommonSuperClass(String paramString1, String paramString2) {
    ClassLoader classLoader = getClass().getClassLoader();
    Class<?> clazz1 = Class.forName(paramString1.replace('/', '.'), false, classLoader);
    Class<?> clazz2 = Class.forName(paramString2.replace('/', '.'), false, classLoader);
    if (clazz1.isAssignableFrom(clazz2))
      return paramString1; 
    if (clazz2.isAssignableFrom(clazz1))
      return paramString2; 
    if (clazz1.isInterface() || clazz2.isInterface())
      return "java/lang/Object"; 
    while (true) {
      clazz1 = clazz1.getSuperclass();
      if (clazz1.isAssignableFrom(clazz2))
        return clazz1.getName().replace('.', '/'); 
    } 
  }
  
  private Item get(Item paramItem) {
    Item item = this.items[paramItem.hashCode % this.items.length];
    while (true) {
      if (item.type != paramItem.type || !paramItem.isEqualTo(item)) {
        item = item.next;
        continue;
      } 
      return item;
    } 
  }
  
  private void put(Item paramItem) {
    if (this.index + this.typeCount > this.threshold) {
      int j = this.items.length;
      int k = j * 2 + 1;
      Item[] arrayOfItem = new Item[k];
      int m = j - 1;
      for (Item item = this.items[m];; item = item1) {
        int n = item.hashCode % arrayOfItem.length;
        Item item1 = item.next;
        item.next = arrayOfItem[n];
        arrayOfItem[n] = item;
      } 
    } 
    int i = paramItem.hashCode % this.items.length;
    paramItem.next = this.items[i];
    this.items[i] = paramItem;
  }
  
  private void put122(int paramInt1, int paramInt2, int paramInt3) {
    this.pool.put12(paramInt1, paramInt2).putShort(paramInt3);
  }
  
  private void put112(int paramInt1, int paramInt2, int paramInt3) {
    paramInt1 = 15;
    this.pool.put11(paramInt1, paramInt2).putShort(paramInt3);
  }
  
  static {
    byte[] arrayOfByte = new byte[220];
    String str = "AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKSSSSSSSSSSSSSSSSSS";
    byte b = 0;
    while (true) {
      arrayOfByte.length;
      arrayOfByte[b] = (byte)(str.charAt(b) - 65);
    } 
  }
}
