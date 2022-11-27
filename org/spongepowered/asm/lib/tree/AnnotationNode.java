package org.spongepowered.asm.lib.tree;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.lib.AnnotationVisitor;

public class AnnotationNode extends AnnotationVisitor {
  public String desc;
  
  public List values;
  
  public AnnotationNode(String paramString) {
    this(327680, paramString);
    if (getClass() != AnnotationNode.class)
      throw new IllegalStateException(); 
  }
  
  public AnnotationNode(int paramInt, String paramString) {
    super(paramInt);
    this.desc = paramString;
  }
  
  AnnotationNode(List paramList) {
    super(327680);
    this.values = paramList;
  }
  
  public void visit(String paramString, Object paramObject) {
    if (this.values == null)
      this.values = new ArrayList((this.desc != null) ? 2 : 1); 
    if (this.desc != null)
      this.values.add(paramString); 
    if (paramObject instanceof byte[]) {
      byte[] arrayOfByte = (byte[])paramObject;
      ArrayList<Byte> arrayList = new ArrayList(arrayOfByte.length);
      for (byte b : arrayOfByte)
        arrayList.add(Byte.valueOf(b)); 
      this.values.add(arrayList);
    } else if (paramObject instanceof boolean[]) {
      boolean[] arrayOfBoolean = (boolean[])paramObject;
      ArrayList<Boolean> arrayList = new ArrayList(arrayOfBoolean.length);
      for (boolean bool : arrayOfBoolean)
        arrayList.add(Boolean.valueOf(bool)); 
      this.values.add(arrayList);
    } else if (paramObject instanceof short[]) {
      short[] arrayOfShort = (short[])paramObject;
      ArrayList<Short> arrayList = new ArrayList(arrayOfShort.length);
      for (short s : arrayOfShort)
        arrayList.add(Short.valueOf(s)); 
      this.values.add(arrayList);
    } else if (paramObject instanceof char[]) {
      char[] arrayOfChar = (char[])paramObject;
      ArrayList<Character> arrayList = new ArrayList(arrayOfChar.length);
      for (char c : arrayOfChar)
        arrayList.add(Character.valueOf(c)); 
      this.values.add(arrayList);
    } else if (paramObject instanceof int[]) {
      int[] arrayOfInt = (int[])paramObject;
      ArrayList<Integer> arrayList = new ArrayList(arrayOfInt.length);
      for (int i : arrayOfInt)
        arrayList.add(Integer.valueOf(i)); 
      this.values.add(arrayList);
    } else if (paramObject instanceof long[]) {
      long[] arrayOfLong = (long[])paramObject;
      ArrayList<Long> arrayList = new ArrayList(arrayOfLong.length);
      for (long l : arrayOfLong)
        arrayList.add(Long.valueOf(l)); 
      this.values.add(arrayList);
    } else if (paramObject instanceof float[]) {
      float[] arrayOfFloat = (float[])paramObject;
      ArrayList<Float> arrayList = new ArrayList(arrayOfFloat.length);
      for (float f : arrayOfFloat)
        arrayList.add(Float.valueOf(f)); 
      this.values.add(arrayList);
    } else if (paramObject instanceof double[]) {
      double[] arrayOfDouble = (double[])paramObject;
      ArrayList<Double> arrayList = new ArrayList(arrayOfDouble.length);
      for (double d : arrayOfDouble)
        arrayList.add(Double.valueOf(d)); 
      this.values.add(arrayList);
    } else {
      this.values.add(paramObject);
    } 
  }
  
  public void visitEnum(String paramString1, String paramString2, String paramString3) {
    if (this.values == null)
      this.values = new ArrayList((this.desc != null) ? 2 : 1); 
    if (this.desc != null)
      this.values.add(paramString1); 
    this.values.add(new String[] { paramString2, paramString3 });
  }
  
  public AnnotationVisitor visitAnnotation(String paramString1, String paramString2) {
    if (this.values == null)
      this.values = new ArrayList((this.desc != null) ? 2 : 1); 
    if (this.desc != null)
      this.values.add(paramString1); 
    AnnotationNode annotationNode = new AnnotationNode(paramString2);
    this.values.add(annotationNode);
    return annotationNode;
  }
  
  public AnnotationVisitor visitArray(String paramString) {
    if (this.values == null)
      this.values = new ArrayList((this.desc != null) ? 2 : 1); 
    if (this.desc != null)
      this.values.add(paramString); 
    ArrayList arrayList = new ArrayList();
    this.values.add(arrayList);
    return new AnnotationNode(arrayList);
  }
  
  public void visitEnd() {}
  
  public void check(int paramInt) {}
  
  public void accept(AnnotationVisitor paramAnnotationVisitor) {
    if (this.values != null)
      for (byte b = 0; b < this.values.size(); b += 2) {
        String str = this.values.get(b);
        Object object = this.values.get(b + 1);
        accept(paramAnnotationVisitor, str, object);
      }  
    paramAnnotationVisitor.visitEnd();
  }
  
  static void accept(AnnotationVisitor paramAnnotationVisitor, String paramString, Object paramObject) {
    if (paramObject instanceof String[]) {
      String[] arrayOfString = (String[])paramObject;
      paramAnnotationVisitor.visitEnum(paramString, arrayOfString[0], arrayOfString[1]);
    } else if (paramObject instanceof AnnotationNode) {
      AnnotationNode annotationNode = (AnnotationNode)paramObject;
      annotationNode.accept(paramAnnotationVisitor.visitAnnotation(paramString, annotationNode.desc));
    } else if (paramObject instanceof List) {
      AnnotationVisitor annotationVisitor = paramAnnotationVisitor.visitArray(paramString);
      List list = (List)paramObject;
      for (byte b = 0; b < list.size(); b++)
        accept(annotationVisitor, null, list.get(b)); 
      annotationVisitor.visitEnd();
    } else {
      paramAnnotationVisitor.visit(paramString, paramObject);
    } 
  }
}
