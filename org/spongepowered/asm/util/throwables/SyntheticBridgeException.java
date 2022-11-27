package org.spongepowered.asm.util.throwables;

import java.util.ListIterator;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.mixin.transformer.ClassInfo;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.PrettyPrinter;

public class SyntheticBridgeException extends MixinException {
  private static final long serialVersionUID = 1L;
  
  private final SyntheticBridgeException$Problem problem;
  
  private final String name;
  
  private final String desc;
  
  private final int index;
  
  private final AbstractInsnNode a;
  
  private final AbstractInsnNode b;
  
  public SyntheticBridgeException(SyntheticBridgeException$Problem paramSyntheticBridgeException$Problem, String paramString1, String paramString2, int paramInt, AbstractInsnNode paramAbstractInsnNode1, AbstractInsnNode paramAbstractInsnNode2) {
    super(paramSyntheticBridgeException$Problem.getMessage(paramString1, paramString2, paramInt, paramAbstractInsnNode1, paramAbstractInsnNode2));
    this.problem = paramSyntheticBridgeException$Problem;
    this.name = paramString1;
    this.desc = paramString2;
    this.index = paramInt;
    this.a = paramAbstractInsnNode1;
    this.b = paramAbstractInsnNode2;
  }
  
  public void printAnalysis(IMixinContext paramIMixinContext, MethodNode paramMethodNode1, MethodNode paramMethodNode2) {
    PrettyPrinter prettyPrinter = new PrettyPrinter();
    prettyPrinter.addWrapped(100, getMessage(), new Object[0]).hr();
    prettyPrinter.add().kv("Method", this.name + this.desc).kv("Problem Type", this.problem).add().hr();
    String str = (String)Annotations.getValue(Annotations.getVisible(paramMethodNode1, MixinMerged.class), "mixin");
  }
  
  private PrettyPrinter printMethod(PrettyPrinter paramPrettyPrinter, MethodNode paramMethodNode) {
    byte b = 0;
    ListIterator<AbstractInsnNode> listIterator = paramMethodNode.instructions.iterator();
    while (listIterator.hasNext()) {
      paramPrettyPrinter.kv((b == this.index) ? ">>>>" : "", Bytecode.describeNode(listIterator.next()));
      b++;
    } 
    return paramPrettyPrinter.add();
  }
  
  private PrettyPrinter printProblem(PrettyPrinter paramPrettyPrinter, IMixinContext paramIMixinContext, MethodNode paramMethodNode1, MethodNode paramMethodNode2) {
    ListIterator<AbstractInsnNode> listIterator1;
    ListIterator<AbstractInsnNode> listIterator2;
    Type[] arrayOfType1;
    Type[] arrayOfType2;
    byte b1;
    Type type2;
    Type type3;
    MethodInsnNode methodInsnNode1;
    MethodInsnNode methodInsnNode2;
    Type[] arrayOfType3;
    Type[] arrayOfType4;
    Type type4;
    Type type5;
    byte b2;
    Type type1 = Type.getObjectType(paramIMixinContext.getTargetClassRef());
    paramPrettyPrinter.add("Analysis").add();
    switch (SyntheticBridgeException$1.$SwitchMap$org$spongepowered$asm$util$throwables$SyntheticBridgeException$Problem[this.problem.ordinal()]) {
      case 1:
        paramPrettyPrinter.add("The bridge methods are not compatible because they contain incompatible opcodes");
        paramPrettyPrinter.add("at index " + this.index + ":").add();
        paramPrettyPrinter.kv("Existing opcode: %s", Bytecode.getOpcodeName(this.a));
        paramPrettyPrinter.kv("Incoming opcode: %s", Bytecode.getOpcodeName(this.b)).add();
        paramPrettyPrinter.add("This implies that the bridge methods are from different interfaces. This problem");
        paramPrettyPrinter.add("may not be resolvable without changing the base interfaces.").add();
        break;
      case 2:
        paramPrettyPrinter.add("The bridge methods are not compatible because they contain different variables at");
        paramPrettyPrinter.add("opcode index " + this.index + ".").add();
        listIterator1 = paramMethodNode1.instructions.iterator();
        listIterator2 = paramMethodNode2.instructions.iterator();
        arrayOfType1 = Type.getArgumentTypes(paramMethodNode1.desc);
        arrayOfType2 = Type.getArgumentTypes(paramMethodNode2.desc);
        for (b1 = 0; listIterator1.hasNext() && listIterator2.hasNext(); b1++) {
          AbstractInsnNode abstractInsnNode1 = listIterator1.next();
          AbstractInsnNode abstractInsnNode2 = listIterator2.next();
          if (abstractInsnNode1 instanceof VarInsnNode && abstractInsnNode2 instanceof VarInsnNode) {
            VarInsnNode varInsnNode1 = (VarInsnNode)abstractInsnNode1;
            VarInsnNode varInsnNode2 = (VarInsnNode)abstractInsnNode2;
            Type type6 = (varInsnNode1.var > 0) ? arrayOfType1[varInsnNode1.var - 1] : type1;
            Type type7 = (varInsnNode2.var > 0) ? arrayOfType2[varInsnNode2.var - 1] : type1;
            paramPrettyPrinter.kv("Target " + b1, "%8s %-2d %s", new Object[] { Bytecode.getOpcodeName((AbstractInsnNode)varInsnNode1), Integer.valueOf(varInsnNode1.var), type6 });
            paramPrettyPrinter.kv("Incoming " + b1, "%8s %-2d %s", new Object[] { Bytecode.getOpcodeName((AbstractInsnNode)varInsnNode2), Integer.valueOf(varInsnNode2.var), type7 });
            if (type6.equals(type7)) {
              paramPrettyPrinter.kv("", "Types match: %s", new Object[] { type6 });
            } else if (type6.getSort() != type7.getSort()) {
              paramPrettyPrinter.kv("", "Types are incompatible");
            } else if (type6.getSort() == 10) {
              ClassInfo classInfo = ClassInfo.getCommonSuperClassOrInterface(type6, type7);
              paramPrettyPrinter.kv("", "Common supertype: %s", new Object[] { classInfo });
            } 
            paramPrettyPrinter.add();
          } 
        } 
        paramPrettyPrinter.add("Since this probably means that the methods come from different interfaces, you");
        paramPrettyPrinter.add("may have a \"multiple inheritance\" problem, it may not be possible to implement");
        paramPrettyPrinter.add("both root interfaces");
        break;
      case 3:
        paramPrettyPrinter.add("Incompatible CHECKCAST encountered at opcode " + this.index + ", this could indicate that the bridge");
        paramPrettyPrinter.add("is casting down for contravariant generic types. It may be possible to coalesce the");
        paramPrettyPrinter.add("bridges by adjusting the types in the target method.").add();
        type2 = Type.getObjectType(((TypeInsnNode)this.a).desc);
        type3 = Type.getObjectType(((TypeInsnNode)this.b).desc);
        paramPrettyPrinter.kv("Target type", type2);
        paramPrettyPrinter.kv("Incoming type", type3);
        paramPrettyPrinter.kv("Common supertype", ClassInfo.getCommonSuperClassOrInterface(type2, type3)).add();
        break;
      case 4:
        paramPrettyPrinter.add("Incompatible invocation targets in synthetic bridge. This is extremely unusual");
        paramPrettyPrinter.add("and implies that a remapping transformer has incorrectly remapped a method. This");
        paramPrettyPrinter.add("is an unrecoverable error.");
        break;
      case 5:
        methodInsnNode1 = (MethodInsnNode)this.a;
        methodInsnNode2 = (MethodInsnNode)this.b;
        arrayOfType3 = Type.getArgumentTypes(methodInsnNode1.desc);
        arrayOfType4 = Type.getArgumentTypes(methodInsnNode2.desc);
        if (arrayOfType3.length != arrayOfType4.length) {
          int i = (Type.getArgumentTypes(paramMethodNode1.desc)).length;
          String str = (arrayOfType3.length == i) ? "The TARGET" : ((arrayOfType4.length == i) ? " The INCOMING" : "NEITHER");
          paramPrettyPrinter.add("Mismatched invocation descriptors in synthetic bridge implies that a remapping");
          paramPrettyPrinter.add("transformer has incorrectly coalesced a bridge method with a conflicting name.");
          paramPrettyPrinter.add("Overlapping bridge methods should always have the same number of arguments, yet");
          paramPrettyPrinter.add("the target method has %d arguments, the incoming method has %d. This is an", new Object[] { Integer.valueOf(arrayOfType3.length), Integer.valueOf(arrayOfType4.length) });
          paramPrettyPrinter.add("unrecoverable error. %s method has the expected arg count of %d", new Object[] { str, Integer.valueOf(i) });
          break;
        } 
        type4 = Type.getReturnType(methodInsnNode1.desc);
        type5 = Type.getReturnType(methodInsnNode2.desc);
        paramPrettyPrinter.add("Incompatible invocation descriptors in synthetic bridge implies that generified");
        paramPrettyPrinter.add("types are incompatible over one or more generic superclasses or interfaces. It may");
        paramPrettyPrinter.add("be possible to adjust the generic types on implemented members to rectify this");
        paramPrettyPrinter.add("problem by coalescing the appropriate generic types.").add();
        printTypeComparison(paramPrettyPrinter, "return type", type4, type5);
        for (b2 = 0; b2 < arrayOfType3.length; b2++)
          printTypeComparison(paramPrettyPrinter, "arg " + b2, arrayOfType3[b2], arrayOfType4[b2]); 
        break;
      case 6:
        paramPrettyPrinter.add("Mismatched bridge method length implies the bridge methods are incompatible");
        paramPrettyPrinter.add("and may originate from different superinterfaces. This is an unrecoverable");
        paramPrettyPrinter.add("error.").add();
        break;
    } 
    return paramPrettyPrinter;
  }
  
  private PrettyPrinter printTypeComparison(PrettyPrinter paramPrettyPrinter, String paramString, Type paramType1, Type paramType2) {
    paramPrettyPrinter.kv("Target " + paramString, "%s", new Object[] { paramType1 });
    paramPrettyPrinter.kv("Incoming " + paramString, "%s", new Object[] { paramType2 });
    if (paramType1.equals(paramType2)) {
      paramPrettyPrinter.kv("Analysis", "Types match: %s", new Object[] { paramType1 });
    } else if (paramType1.getSort() != paramType2.getSort()) {
      paramPrettyPrinter.kv("Analysis", "Types are incompatible");
    } else if (paramType1.getSort() == 10) {
      ClassInfo classInfo = ClassInfo.getCommonSuperClassOrInterface(paramType1, paramType2);
      paramPrettyPrinter.kv("Analysis", "Common supertype: L%s;", new Object[] { classInfo });
    } 
    return paramPrettyPrinter.add();
  }
}
