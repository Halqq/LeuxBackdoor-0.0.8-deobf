package org.spongepowered.asm.mixin.injection.callback;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.InsnNode;
import org.spongepowered.asm.lib.tree.JumpInsnNode;
import org.spongepowered.asm.lib.tree.LabelNode;
import org.spongepowered.asm.lib.tree.LdcInsnNode;
import org.spongepowered.asm.lib.tree.LocalVariableNode;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.lib.tree.VarInsnNode;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.Surrogate;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.points.BeforeReturn;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;
import org.spongepowered.asm.util.Locals;
import org.spongepowered.asm.util.PrettyPrinter;
import org.spongepowered.asm.util.SignaturePrinter;

public class CallbackInjector extends Injector {
  private final boolean cancellable;
  
  private final LocalCapture localCapture;
  
  private final String identifier;
  
  private final Map ids = new HashMap<Object, Object>();
  
  private int totalInjections = 0;
  
  private int callbackInfoVar = -1;
  
  private String lastId;
  
  private String lastDesc;
  
  private Target lastTarget;
  
  private String callbackInfoClass;
  
  public CallbackInjector(InjectionInfo paramInjectionInfo, boolean paramBoolean, LocalCapture paramLocalCapture, String paramString) {
    super(paramInjectionInfo);
    this.cancellable = paramBoolean;
    this.localCapture = paramLocalCapture;
    this.identifier = paramString;
  }
  
  protected void sanityCheck(Target paramTarget, List paramList) {
    super.sanityCheck(paramTarget, paramList);
    if (paramTarget.isStatic != this.isStatic)
      throw new InvalidInjectionException(this.info, "'static' modifier of callback method does not match target in " + this); 
    if ("<init>".equals(paramTarget.method.name))
      for (InjectionPoint injectionPoint : paramList) {
        if (!injectionPoint.getClass().equals(BeforeReturn.class))
          throw new InvalidInjectionException(this.info, "Found injection point type " + injectionPoint.getClass().getSimpleName() + " targetting a ctor in " + this + ". Only RETURN allowed for a ctor target"); 
      }  
  }
  
  protected void addTargetNode(Target paramTarget, List<InjectionNodes.InjectionNode> paramList, AbstractInsnNode paramAbstractInsnNode, Set paramSet) {
    InjectionNodes.InjectionNode injectionNode = paramTarget.addInjectionNode(paramAbstractInsnNode);
    for (InjectionPoint injectionPoint : paramSet) {
      String str1 = injectionPoint.getId();
      if (Strings.isNullOrEmpty(str1))
        continue; 
      String str2 = (String)this.ids.get(Integer.valueOf(injectionNode.getId()));
      if (!str2.equals(str1)) {
        Injector.logger.warn("Conflicting id for {} insn in {}, found id {} on {}, previously defined as {}", new Object[] { Bytecode.getOpcodeName(paramAbstractInsnNode), paramTarget.toString(), str1, this.info, str2 });
        break;
      } 
      this.ids.put(Integer.valueOf(injectionNode.getId()), str1);
    } 
    paramList.add(injectionNode);
    this.totalInjections++;
  }
  
  protected void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    LocalVariableNode[] arrayOfLocalVariableNode = null;
    if (this.localCapture.isCaptureLocals() || this.localCapture.isPrintLocals())
      arrayOfLocalVariableNode = Locals.getLocalsAt(this.classNode, paramTarget.method, paramInjectionNode.getCurrentTarget()); 
    inject(new CallbackInjector$Callback(this, this.methodNode, paramTarget, paramInjectionNode, arrayOfLocalVariableNode, this.localCapture.isCaptureLocals()));
  }
  
  private void inject(CallbackInjector$Callback paramCallbackInjector$Callback) {
    if (this.localCapture.isPrintLocals()) {
      printLocals(paramCallbackInjector$Callback);
      this.info.addCallbackInvocation(this.methodNode);
      return;
    } 
    MethodNode methodNode = this.methodNode;
    if (!paramCallbackInjector$Callback.checkDescriptor(this.methodNode.desc)) {
      if (this.info.getTargets().size() > 1)
        return; 
      if (paramCallbackInjector$Callback.canCaptureLocals) {
        MethodNode methodNode1 = Bytecode.findMethod(this.classNode, this.methodNode.name, paramCallbackInjector$Callback.getDescriptor());
        if (Annotations.getVisible(methodNode1, Surrogate.class) != null) {
          methodNode = methodNode1;
        } else {
          String str = generateBadLVTMessage(paramCallbackInjector$Callback);
          switch (CallbackInjector$1.$SwitchMap$org$spongepowered$asm$mixin$injection$callback$LocalCapture[this.localCapture.ordinal()]) {
            case 1:
              Injector.logger.error("Injection error: {}", new Object[] { str });
              methodNode = generateErrorMethod(paramCallbackInjector$Callback, "org/spongepowered/asm/mixin/injection/throwables/InjectionError", str);
              break;
            case 2:
              Injector.logger.warn("Injection warning: {}", new Object[] { str });
              return;
            default:
              Injector.logger.error("Critical injection failure: {}", new Object[] { str });
              throw new InjectionError(str);
          } 
        } 
      } else {
        String str = this.methodNode.desc.replace("Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;", "Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;");
        if (paramCallbackInjector$Callback.checkDescriptor(str))
          throw new InvalidInjectionException(this.info, "Invalid descriptor on " + this.info + "! CallbackInfoReturnable is required!"); 
        MethodNode methodNode1 = Bytecode.findMethod(this.classNode, this.methodNode.name, paramCallbackInjector$Callback.getDescriptor());
        if (Annotations.getVisible(methodNode1, Surrogate.class) != null) {
          methodNode = methodNode1;
        } else {
          throw new InvalidInjectionException(this.info, "Invalid descriptor on " + this.info + "! Expected " + paramCallbackInjector$Callback.getDescriptor() + " but found " + this.methodNode.desc);
        } 
      } 
    } 
    dupReturnValue(paramCallbackInjector$Callback);
    if (this.cancellable || this.totalInjections > 1)
      createCallbackInfo(paramCallbackInjector$Callback, true); 
    invokeCallback(paramCallbackInjector$Callback, methodNode);
    injectCancellationCode(paramCallbackInjector$Callback);
    paramCallbackInjector$Callback.inject();
    this.info.notifyInjected(paramCallbackInjector$Callback.target);
  }
  
  private String generateBadLVTMessage(CallbackInjector$Callback paramCallbackInjector$Callback) {
    int i = paramCallbackInjector$Callback.target.indexOf(paramCallbackInjector$Callback.node);
    List list1 = summariseLocals(this.methodNode.desc, paramCallbackInjector$Callback.target.arguments.length + 1);
    List list2 = summariseLocals(paramCallbackInjector$Callback.getDescriptorWithAllLocals(), paramCallbackInjector$Callback.frameSize);
    return String.format("LVT in %s has incompatible changes at opcode %d in callback %s.\nExpected: %s\n   Found: %s", new Object[] { paramCallbackInjector$Callback.target, Integer.valueOf(i), this, list1, list2 });
  }
  
  private MethodNode generateErrorMethod(CallbackInjector$Callback paramCallbackInjector$Callback, String paramString1, String paramString2) {
    paramString1 = "org/spongepowered/asm/mixin/injection/throwables/InjectionError";
    MethodNode methodNode = this.info.addMethod(this.methodNode.access, this.methodNode.name + "$missing", paramCallbackInjector$Callback.getDescriptor());
    methodNode.maxLocals = Bytecode.getFirstNonArgLocalIndex(Type.getArgumentTypes(paramCallbackInjector$Callback.getDescriptor()), !this.isStatic);
    methodNode.maxStack = 3;
    InsnList insnList = methodNode.instructions;
    insnList.add((AbstractInsnNode)new TypeInsnNode(187, paramString1));
    insnList.add((AbstractInsnNode)new InsnNode(89));
    insnList.add((AbstractInsnNode)new LdcInsnNode(paramString2));
    insnList.add((AbstractInsnNode)new MethodInsnNode(183, paramString1, "<init>", "(Ljava/lang/String;)V", false));
    insnList.add((AbstractInsnNode)new InsnNode(191));
    return methodNode;
  }
  
  private void printLocals(CallbackInjector$Callback paramCallbackInjector$Callback) {
    Type[] arrayOfType = Type.getArgumentTypes(paramCallbackInjector$Callback.getDescriptorWithAllLocals());
    SignaturePrinter signaturePrinter1 = new SignaturePrinter(paramCallbackInjector$Callback.target.method, paramCallbackInjector$Callback.argNames);
    SignaturePrinter signaturePrinter2 = new SignaturePrinter(this.methodNode.name, paramCallbackInjector$Callback.target.returnType, arrayOfType, paramCallbackInjector$Callback.argNames);
    signaturePrinter2.setModifiers(this.methodNode);
    PrettyPrinter prettyPrinter = new PrettyPrinter();
    prettyPrinter.kv("Target Class", this.classNode.name.replace('/', '.'));
    prettyPrinter.kv("Target Method", signaturePrinter1);
    prettyPrinter.kv("Target Max LOCALS", Integer.valueOf(paramCallbackInjector$Callback.target.getMaxLocals()));
    prettyPrinter.kv("Initial Frame Size", Integer.valueOf(paramCallbackInjector$Callback.frameSize));
    prettyPrinter.kv("Callback Name", this.methodNode.name);
    prettyPrinter.kv("Instruction", "%s %s", new Object[] { paramCallbackInjector$Callback.node.getClass().getSimpleName(), Bytecode.getOpcodeName(paramCallbackInjector$Callback.node.getCurrentTarget().getOpcode()) });
    prettyPrinter.hr();
    if (paramCallbackInjector$Callback.locals.length > paramCallbackInjector$Callback.frameSize) {
      prettyPrinter.add("  %s  %20s  %s", new Object[] { "LOCAL", "TYPE", "NAME" });
      for (byte b = 0; b < paramCallbackInjector$Callback.locals.length; b++) {
        String str = (b == paramCallbackInjector$Callback.frameSize) ? ">" : " ";
        if (paramCallbackInjector$Callback.locals[b] != null) {
          prettyPrinter.add("%s [%3d]  %20s  %-50s %s", new Object[] { str, Integer.valueOf(b), SignaturePrinter.getTypeName(paramCallbackInjector$Callback.localTypes[b], false), meltSnowman(b, (paramCallbackInjector$Callback.locals[b]).name), (b >= paramCallbackInjector$Callback.frameSize) ? "<capture>" : "" });
        } else {
          boolean bool = false;
          (new Object[3])[0] = str;
          (new Object[3])[1] = Integer.valueOf(b);
        } 
      } 
      prettyPrinter.hr();
    } 
    prettyPrinter.add().add("/**").add(" * Expected callback signature").add(" * /");
    prettyPrinter.add("%s {", new Object[] { signaturePrinter2 });
    prettyPrinter.add("    // Method body").add("}").add().print(System.err);
  }
  
  private void createCallbackInfo(CallbackInjector$Callback paramCallbackInjector$Callback, boolean paramBoolean) {
    if (paramCallbackInjector$Callback.target != this.lastTarget) {
      this.lastId = null;
      this.lastDesc = null;
    } 
    this.lastTarget = paramCallbackInjector$Callback.target;
    String str1 = getIdentifier(paramCallbackInjector$Callback);
    String str2 = paramCallbackInjector$Callback.getCallbackInfoConstructorDescriptor();
    if (str1.equals(this.lastId) && str2.equals(this.lastDesc) && !paramCallbackInjector$Callback.isAtReturn && !this.cancellable)
      return; 
    instanceCallbackInfo(paramCallbackInjector$Callback, str1, str2, paramBoolean);
  }
  
  private void loadOrCreateCallbackInfo(CallbackInjector$Callback paramCallbackInjector$Callback) {
    if (this.cancellable || this.totalInjections > 1) {
      paramCallbackInjector$Callback.add((AbstractInsnNode)new VarInsnNode(25, this.callbackInfoVar), false, true);
    } else {
      createCallbackInfo(paramCallbackInjector$Callback, false);
    } 
  }
  
  private void dupReturnValue(CallbackInjector$Callback paramCallbackInjector$Callback) {
    if (!paramCallbackInjector$Callback.isAtReturn)
      return; 
    paramCallbackInjector$Callback.add((AbstractInsnNode)new InsnNode(89));
    paramCallbackInjector$Callback.add((AbstractInsnNode)new VarInsnNode(paramCallbackInjector$Callback.target.returnType.getOpcode(54), paramCallbackInjector$Callback.marshalVar()));
  }
  
  protected void instanceCallbackInfo(CallbackInjector$Callback paramCallbackInjector$Callback, String paramString1, String paramString2, boolean paramBoolean) {
    this.lastId = paramString1;
    this.lastDesc = paramString2;
    this.callbackInfoVar = paramCallbackInjector$Callback.marshalVar();
    this.callbackInfoClass = paramCallbackInjector$Callback.target.getCallbackInfoClass();
    boolean bool = (this.totalInjections > 1 && !paramCallbackInjector$Callback.isAtReturn && !this.cancellable) ? true : false;
  }
  
  private void invokeCallback(CallbackInjector$Callback paramCallbackInjector$Callback, MethodNode paramMethodNode) {
    if (!this.isStatic)
      paramCallbackInjector$Callback.add((AbstractInsnNode)new VarInsnNode(25, 0), false, true); 
    if (paramCallbackInjector$Callback.captureArgs())
      Bytecode.loadArgs(paramCallbackInjector$Callback.target.arguments, paramCallbackInjector$Callback, this.isStatic ? 0 : 1, -1); 
    loadOrCreateCallbackInfo(paramCallbackInjector$Callback);
    if (paramCallbackInjector$Callback.canCaptureLocals)
      Locals.loadLocals(paramCallbackInjector$Callback.localTypes, paramCallbackInjector$Callback, paramCallbackInjector$Callback.frameSize, paramCallbackInjector$Callback.extraArgs); 
    invokeHandler(paramCallbackInjector$Callback, paramMethodNode);
  }
  
  private String getIdentifier(CallbackInjector$Callback paramCallbackInjector$Callback) {
    String str1 = Strings.isNullOrEmpty(this.identifier) ? paramCallbackInjector$Callback.target.method.name : this.identifier;
    String str2 = (String)this.ids.get(Integer.valueOf(paramCallbackInjector$Callback.node.getId()));
    return str1 + (Strings.isNullOrEmpty(str2) ? "" : (":" + str2));
  }
  
  protected void injectCancellationCode(CallbackInjector$Callback paramCallbackInjector$Callback) {
    if (!this.cancellable)
      return; 
    paramCallbackInjector$Callback.add((AbstractInsnNode)new VarInsnNode(25, this.callbackInfoVar));
    paramCallbackInjector$Callback.add((AbstractInsnNode)new MethodInsnNode(182, this.callbackInfoClass, "isCancelled", "()Z", false));
    LabelNode labelNode = new LabelNode();
    paramCallbackInjector$Callback.add((AbstractInsnNode)new JumpInsnNode(153, labelNode));
    injectReturnCode(paramCallbackInjector$Callback);
    paramCallbackInjector$Callback.add((AbstractInsnNode)labelNode);
  }
  
  protected void injectReturnCode(CallbackInjector$Callback paramCallbackInjector$Callback) {
    if (paramCallbackInjector$Callback.target.returnType.equals(Type.VOID_TYPE)) {
      paramCallbackInjector$Callback.add((AbstractInsnNode)new InsnNode(177));
    } else {
      paramCallbackInjector$Callback.add((AbstractInsnNode)new VarInsnNode(25, paramCallbackInjector$Callback.marshalVar()));
      String str1 = CallbackInfoReturnable.getReturnAccessor(paramCallbackInjector$Callback.target.returnType);
      String str2 = CallbackInfoReturnable.getReturnDescriptor(paramCallbackInjector$Callback.target.returnType);
      paramCallbackInjector$Callback.add((AbstractInsnNode)new MethodInsnNode(182, this.callbackInfoClass, str1, str2, false));
      if (paramCallbackInjector$Callback.target.returnType.getSort() == 10)
        paramCallbackInjector$Callback.add((AbstractInsnNode)new TypeInsnNode(192, paramCallbackInjector$Callback.target.returnType.getInternalName())); 
      paramCallbackInjector$Callback.add((AbstractInsnNode)new InsnNode(paramCallbackInjector$Callback.target.returnType.getOpcode(172)));
    } 
  }
  
  protected boolean isStatic() {
    return this.isStatic;
  }
  
  private static List summariseLocals(String paramString, int paramInt) {
    return summariseLocals(Type.getArgumentTypes(paramString), paramInt);
  }
  
  private static List summariseLocals(Type[] paramArrayOfType, int paramInt) {
    ArrayList<String> arrayList = new ArrayList();
    while (paramInt < paramArrayOfType.length) {
      if (paramArrayOfType[paramInt] != null)
        arrayList.add(paramArrayOfType[paramInt].toString()); 
      paramInt++;
    } 
    return arrayList;
  }
  
  static String meltSnowman(int paramInt, String paramString) {
    paramInt = 0;
    return ('☃' == paramString.charAt(0)) ? ("var" + paramInt) : paramString;
  }
}
