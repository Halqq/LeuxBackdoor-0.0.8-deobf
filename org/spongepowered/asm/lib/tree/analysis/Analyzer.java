package org.spongepowered.asm.lib.tree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LookupSwitchInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TableSwitchInsnNode;
import org.spongepowered.asm.lib.tree.TryCatchBlockNode;

public class Analyzer implements Opcodes {
  private final Interpreter interpreter;
  
  private int n;
  
  private InsnList insns;
  
  private List[] handlers;
  
  private Frame[] frames;
  
  private Subroutine[] subroutines;
  
  private boolean[] queued;
  
  private int[] queue;
  
  private int top;
  
  public Analyzer(Interpreter paramInterpreter) {
    this.interpreter = paramInterpreter;
  }
  
  public Frame[] analyze(String paramString, MethodNode paramMethodNode) throws AnalyzerException {
    if ((paramMethodNode.access & 0x500) != 0) {
      this.frames = new Frame[0];
      return this.frames;
    } 
    this.n = paramMethodNode.instructions.size();
    this.insns = paramMethodNode.instructions;
    this.handlers = new List[this.n];
    this.frames = new Frame[this.n];
    this.subroutines = new Subroutine[this.n];
    this.queued = new boolean[this.n];
    this.queue = new int[this.n];
    this.top = 0;
    for (byte b1 = 0; b1 < paramMethodNode.tryCatchBlocks.size(); b1++) {
      TryCatchBlockNode tryCatchBlockNode = paramMethodNode.tryCatchBlocks.get(b1);
      int j = this.insns.indexOf((AbstractInsnNode)tryCatchBlockNode.start);
      int k = this.insns.indexOf((AbstractInsnNode)tryCatchBlockNode.end);
      for (int m = j; m < k; m++) {
        List<TryCatchBlockNode> list = this.handlers[m];
        list = new ArrayList();
        this.handlers[m] = list;
        list.add(tryCatchBlockNode);
      } 
    } 
    Subroutine subroutine = new Subroutine(null, paramMethodNode.maxLocals, null);
    ArrayList<JumpInsnNode> arrayList = new ArrayList();
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    findSubroutine(0, subroutine, arrayList);
    while (!arrayList.isEmpty()) {
      JumpInsnNode jumpInsnNode = arrayList.remove(0);
      Subroutine subroutine1 = (Subroutine)hashMap.get(jumpInsnNode.label);
      subroutine1 = new Subroutine(jumpInsnNode.label, paramMethodNode.maxLocals, jumpInsnNode);
      hashMap.put(jumpInsnNode.label, subroutine1);
      findSubroutine(this.insns.indexOf((AbstractInsnNode)jumpInsnNode.label), subroutine1, arrayList);
    } 
    for (byte b2 = 0; b2 < this.n; b2++) {
      if (this.subroutines[b2] != null && (this.subroutines[b2]).start == null)
        this.subroutines[b2] = null; 
    } 
    Frame frame1 = newFrame(paramMethodNode.maxLocals, paramMethodNode.maxStack);
    Frame frame2 = newFrame(paramMethodNode.maxLocals, paramMethodNode.maxStack);
    frame1.setReturn(this.interpreter.newValue(Type.getReturnType(paramMethodNode.desc)));
    Type[] arrayOfType = Type.getArgumentTypes(paramMethodNode.desc);
    byte b3 = 0;
    if ((paramMethodNode.access & 0x8) == 0) {
      Type type = Type.getObjectType(paramString);
      frame1.setLocal(b3++, this.interpreter.newValue(type));
    } 
    int i;
    for (i = 0; i < arrayOfType.length; i++) {
      frame1.setLocal(b3++, this.interpreter.newValue(arrayOfType[i]));
      if (arrayOfType[i].getSize() == 2)
        frame1.setLocal(b3++, this.interpreter.newValue(null)); 
    } 
    while (b3 < paramMethodNode.maxLocals)
      frame1.setLocal(b3++, this.interpreter.newValue(null)); 
    merge(0, frame1, null);
    init(paramString, paramMethodNode);
    while (this.top > 0) {
      i = this.queue[--this.top];
      Frame frame = this.frames[i];
      Subroutine subroutine1 = this.subroutines[i];
      this.queued[i] = false;
      AbstractInsnNode abstractInsnNode = null;
      abstractInsnNode = paramMethodNode.instructions.get(i);
      int j = abstractInsnNode.getOpcode();
      int k = abstractInsnNode.getType();
      if (k == 8 || k == 15 || k == 14) {
        merge(i + 1, frame, subroutine1);
        newControlFlowEdge(i, i + 1);
      } else {
        frame1.init(frame).execute(abstractInsnNode, this.interpreter);
      } 
      List<TryCatchBlockNode> list = this.handlers[i];
      for (byte b = 0; b < list.size(); b++) {
        Type type;
        TryCatchBlockNode tryCatchBlockNode = list.get(b);
        if (tryCatchBlockNode.type == null) {
          type = Type.getObjectType("java/lang/Throwable");
        } else {
          type = Type.getObjectType(tryCatchBlockNode.type);
        } 
        int m = this.insns.indexOf((AbstractInsnNode)tryCatchBlockNode.handler);
        if (newControlFlowExceptionEdge(i, tryCatchBlockNode)) {
          frame2.init(frame);
          frame2.clearStack();
          frame2.push(this.interpreter.newValue(type));
          merge(m, frame2, subroutine1);
        } 
      } 
    } 
    return this.frames;
  }
  
  private void findSubroutine(int paramInt, Subroutine paramSubroutine, List<AbstractInsnNode> paramList) throws AnalyzerException {
    while (true) {
      if (paramInt >= this.n)
        throw new AnalyzerException(null, "Execution can fall off end of the code"); 
      if (this.subroutines[paramInt] != null)
        return; 
      this.subroutines[paramInt] = paramSubroutine.copy();
      AbstractInsnNode abstractInsnNode = this.insns.get(paramInt);
      if (abstractInsnNode instanceof JumpInsnNode) {
        if (abstractInsnNode.getOpcode() == 168) {
          paramList.add(abstractInsnNode);
        } else {
          JumpInsnNode jumpInsnNode = (JumpInsnNode)abstractInsnNode;
          findSubroutine(this.insns.indexOf((AbstractInsnNode)jumpInsnNode.label), paramSubroutine, paramList);
        } 
      } else {
        if (abstractInsnNode instanceof TableSwitchInsnNode) {
          TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode)abstractInsnNode;
          findSubroutine(this.insns.indexOf((AbstractInsnNode)tableSwitchInsnNode.dflt), paramSubroutine, paramList);
          for (int i = tableSwitchInsnNode.labels.size() - 1;; i--) {
            LabelNode labelNode = tableSwitchInsnNode.labels.get(i);
            findSubroutine(this.insns.indexOf((AbstractInsnNode)labelNode), paramSubroutine, paramList);
          } 
          break;
        } 
        if (abstractInsnNode instanceof LookupSwitchInsnNode) {
          LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode)abstractInsnNode;
          findSubroutine(this.insns.indexOf((AbstractInsnNode)lookupSwitchInsnNode.dflt), paramSubroutine, paramList);
          for (int i = lookupSwitchInsnNode.labels.size() - 1;; i--) {
            LabelNode labelNode = lookupSwitchInsnNode.labels.get(i);
            findSubroutine(this.insns.indexOf((AbstractInsnNode)labelNode), paramSubroutine, paramList);
          } 
          break;
        } 
      } 
      List<TryCatchBlockNode> list = this.handlers[paramInt];
      for (byte b = 0; b < list.size(); b++) {
        TryCatchBlockNode tryCatchBlockNode = list.get(b);
        findSubroutine(this.insns.indexOf((AbstractInsnNode)tryCatchBlockNode.handler), paramSubroutine, paramList);
      } 
      switch (abstractInsnNode.getOpcode()) {
        case 167:
        case 169:
        case 170:
        case 171:
        case 172:
        case 173:
        case 174:
        case 175:
        case 176:
        case 177:
        case 191:
          return;
      } 
      paramInt++;
    } 
  }
  
  public Frame[] getFrames() {
    return this.frames;
  }
  
  public List getHandlers(int paramInt) {
    return this.handlers[paramInt];
  }
  
  protected void init(String paramString, MethodNode paramMethodNode) throws AnalyzerException {}
  
  protected Frame newFrame(int paramInt1, int paramInt2) {
    return new Frame(paramInt1, paramInt2);
  }
  
  protected Frame newFrame(Frame paramFrame) {
    return new Frame(paramFrame);
  }
  
  protected void newControlFlowEdge(int paramInt1, int paramInt2) {}
  
  protected boolean newControlFlowExceptionEdge(int paramInt1, int paramInt2) {
    return true;
  }
  
  protected boolean newControlFlowExceptionEdge(int paramInt, TryCatchBlockNode paramTryCatchBlockNode) {
    return newControlFlowExceptionEdge(paramInt, this.insns.indexOf((AbstractInsnNode)paramTryCatchBlockNode.handler));
  }
  
  private void merge(int paramInt, Frame paramFrame, Subroutine paramSubroutine) throws AnalyzerException {
    Frame frame = this.frames[paramInt];
    Subroutine subroutine = this.subroutines[paramInt];
    this.frames[paramInt] = newFrame(paramFrame);
    boolean bool = true;
    this.subroutines[paramInt] = paramSubroutine.copy();
    bool = true;
    if (!this.queued[paramInt]) {
      this.queued[paramInt] = true;
      this.queue[this.top++] = paramInt;
    } 
  }
  
  private void merge(int paramInt, Frame paramFrame1, Frame paramFrame2, Subroutine paramSubroutine, boolean[] paramArrayOfboolean) throws AnalyzerException {
    Frame frame = this.frames[paramInt];
    Subroutine subroutine = this.subroutines[paramInt];
    paramFrame2.merge(paramFrame1, paramArrayOfboolean);
    this.frames[paramInt] = newFrame(paramFrame2);
    boolean bool = true;
    bool |= subroutine.merge(paramSubroutine);
    if (!this.queued[paramInt]) {
      this.queued[paramInt] = true;
      this.queue[this.top++] = paramInt;
    } 
  }
}
