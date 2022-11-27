package org.spongepowered.asm.mixin.injection.invoke;

import com.google.common.base.Joiner;
import com.google.common.collect.ObjectArrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AbstractInsnNode;
import org.spongepowered.asm.lib.tree.FieldInsnNode;
import org.spongepowered.asm.lib.tree.InsnList;
import org.spongepowered.asm.lib.tree.MethodInsnNode;
import org.spongepowered.asm.lib.tree.TypeInsnNode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.points.BeforeFieldAccess;
import org.spongepowered.asm.mixin.injection.points.BeforeNew;
import org.spongepowered.asm.mixin.injection.struct.InjectionInfo;
import org.spongepowered.asm.mixin.injection.struct.InjectionNodes;
import org.spongepowered.asm.mixin.injection.struct.Target;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public class RedirectInjector extends InvokeInjector {
  private static final String KEY_NOMINATORS = "nominators";
  
  private static final String KEY_WILD = "wildcard";
  
  private static final String KEY_FUZZ = "fuzz";
  
  private static final String KEY_OPCODE = "opcode";
  
  protected RedirectInjector$Meta meta;
  
  private Map ctorRedirectors = new HashMap<Object, Object>();
  
  public RedirectInjector(InjectionInfo paramInjectionInfo) {
    this(paramInjectionInfo, "@Redirect");
  }
  
  protected RedirectInjector(InjectionInfo paramInjectionInfo, String paramString) {
    super(paramInjectionInfo, paramString);
    int i = paramInjectionInfo.getContext().getPriority();
    boolean bool = (Annotations.getVisible(this.methodNode, Final.class) != null) ? true : false;
    this.meta = new RedirectInjector$Meta(this, i, bool, this.info.toString(), this.methodNode.desc);
  }
  
  protected void checkTarget(Target paramTarget) {}
  
  protected void addTargetNode(Target paramTarget, List<InjectionNodes.InjectionNode> paramList, AbstractInsnNode paramAbstractInsnNode, Set paramSet) {
    InjectionNodes.InjectionNode injectionNode1 = paramTarget.getInjectionNode(paramAbstractInsnNode);
    RedirectInjector$ConstructorRedirectData redirectInjector$ConstructorRedirectData = null;
    int i = 8;
    int j = 0;
    RedirectInjector$Meta redirectInjector$Meta = (RedirectInjector$Meta)injectionNode1.getDecoration("redirector");
    if (redirectInjector$Meta.getOwner() != this) {
      if (redirectInjector$Meta.priority >= this.meta.priority) {
        Injector.logger.warn("{} conflict. Skipping {} with priority {}, already redirected by {} with priority {}", new Object[] { this.annotationType, this.info, Integer.valueOf(this.meta.priority), redirectInjector$Meta.name, Integer.valueOf(redirectInjector$Meta.priority) });
        return;
      } 
      if (redirectInjector$Meta.isFinal)
        throw new InvalidInjectionException(this.info, this.annotationType + " conflict: " + this + " failed because target was already remapped by " + redirectInjector$Meta.name); 
    } 
    for (InjectionPoint injectionPoint : paramSet) {
      if (injectionPoint instanceof BeforeNew && !((BeforeNew)injectionPoint).hasDescriptor()) {
        redirectInjector$ConstructorRedirectData = getCtorRedirect((BeforeNew)injectionPoint);
        continue;
      } 
      if (injectionPoint instanceof BeforeFieldAccess) {
        BeforeFieldAccess beforeFieldAccess = (BeforeFieldAccess)injectionPoint;
        i = beforeFieldAccess.getFuzzFactor();
        j = beforeFieldAccess.getArrayOpcode();
      } 
    } 
    InjectionNodes.InjectionNode injectionNode2 = paramTarget.addInjectionNode(paramAbstractInsnNode);
    injectionNode2.decorate("redirector", this.meta);
    injectionNode2.decorate("nominators", paramSet);
    if (paramAbstractInsnNode instanceof TypeInsnNode && paramAbstractInsnNode.getOpcode() == 187) {
    
    } else {
      injectionNode2.decorate("fuzz", Integer.valueOf(i));
      injectionNode2.decorate("opcode", Integer.valueOf(j));
    } 
    paramList.add(injectionNode2);
  }
  
  private RedirectInjector$ConstructorRedirectData getCtorRedirect(BeforeNew paramBeforeNew) {
    RedirectInjector$ConstructorRedirectData redirectInjector$ConstructorRedirectData = (RedirectInjector$ConstructorRedirectData)this.ctorRedirectors.get(paramBeforeNew);
    redirectInjector$ConstructorRedirectData = new RedirectInjector$ConstructorRedirectData(this);
    this.ctorRedirectors.put(paramBeforeNew, redirectInjector$ConstructorRedirectData);
    return redirectInjector$ConstructorRedirectData;
  }
  
  protected void inject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    if (!preInject(paramInjectionNode))
      return; 
    if (paramInjectionNode.isReplaced())
      throw new UnsupportedOperationException("Redirector target failure for " + this.info); 
    if (paramInjectionNode.getCurrentTarget() instanceof MethodInsnNode) {
      checkTargetForNode(paramTarget, paramInjectionNode);
      injectAtInvoke(paramTarget, paramInjectionNode);
      return;
    } 
    if (paramInjectionNode.getCurrentTarget() instanceof FieldInsnNode) {
      checkTargetForNode(paramTarget, paramInjectionNode);
      injectAtFieldAccess(paramTarget, paramInjectionNode);
      return;
    } 
    if (paramInjectionNode.getCurrentTarget() instanceof TypeInsnNode && paramInjectionNode.getCurrentTarget().getOpcode() == 187) {
      if (!this.isStatic && paramTarget.isStatic)
        throw new InvalidInjectionException(this.info, "non-static callback method " + this + " has a static target which is not supported"); 
      injectAtConstructor(paramTarget, paramInjectionNode);
      return;
    } 
    throw new InvalidInjectionException(this.info, this.annotationType + " annotation on is targetting an invalid insn in " + paramTarget + " in " + this);
  }
  
  protected boolean preInject(InjectionNodes.InjectionNode paramInjectionNode) {
    RedirectInjector$Meta redirectInjector$Meta = (RedirectInjector$Meta)paramInjectionNode.getDecoration("redirector");
    if (redirectInjector$Meta.getOwner() != this) {
      Injector.logger.warn("{} conflict. Skipping {} with priority {}, already redirected by {} with priority {}", new Object[] { this.annotationType, this.info, Integer.valueOf(this.meta.priority), redirectInjector$Meta.name, Integer.valueOf(redirectInjector$Meta.priority) });
      return false;
    } 
    return true;
  }
  
  protected void postInject(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    super.postInject(paramTarget, paramInjectionNode);
    if (paramInjectionNode.getOriginalTarget() instanceof TypeInsnNode && paramInjectionNode.getOriginalTarget().getOpcode() == 187) {
      RedirectInjector$ConstructorRedirectData redirectInjector$ConstructorRedirectData = (RedirectInjector$ConstructorRedirectData)paramInjectionNode.getDecoration("ctor");
      boolean bool = ((Boolean)paramInjectionNode.getDecoration("wildcard")).booleanValue();
      if (redirectInjector$ConstructorRedirectData.injected == 0)
        throw new InvalidInjectionException(this.info, this.annotationType + " ctor invocation was not found in " + paramTarget); 
    } 
  }
  
  protected void injectAtInvoke(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    MethodInsnNode methodInsnNode = (MethodInsnNode)paramInjectionNode.getCurrentTarget();
    boolean bool1 = (methodInsnNode.getOpcode() == 184) ? true : false;
    Type type1 = Type.getType("L" + methodInsnNode.owner + ";");
    Type type2 = Type.getReturnType(methodInsnNode.desc);
    Type[] arrayOfType1 = Type.getArgumentTypes(methodInsnNode.desc);
    Type[] arrayOfType2 = (Type[])ObjectArrays.concat(type1, (Object[])arrayOfType1);
    boolean bool2 = false;
    String str = Bytecode.getDescriptor(arrayOfType2, type2);
    if (!str.equals(this.methodNode.desc)) {
      String str1 = Bytecode.getDescriptor((Type[])ObjectArrays.concat((Object[])arrayOfType2, (Object[])paramTarget.arguments, Type.class), type2);
      if (str1.equals(this.methodNode.desc)) {
        bool2 = true;
      } else {
        throw new InvalidInjectionException(this.info, this.annotationType + " handler method " + this + " has an invalid signature, expected " + str + " found " + this.methodNode.desc);
      } 
    } 
    InsnList insnList = new InsnList();
    int i = Bytecode.getArgsSize(arrayOfType2) + 1;
    boolean bool3 = true;
    int[] arrayOfInt = storeArgs(paramTarget, arrayOfType2, insnList, 0);
    AbstractInsnNode abstractInsnNode = invokeHandlerWithArgs(this.methodArgs, insnList, arrayOfInt);
    paramTarget.replaceNode((AbstractInsnNode)methodInsnNode, abstractInsnNode, insnList);
    paramTarget.addToLocals(i);
    paramTarget.addToStack(bool3);
  }
  
  private void injectAtFieldAccess(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    FieldInsnNode fieldInsnNode = (FieldInsnNode)paramInjectionNode.getCurrentTarget();
    int i = fieldInsnNode.getOpcode();
    Type type1 = Type.getType("L" + fieldInsnNode.owner + ";");
    Type type2 = Type.getType(fieldInsnNode.desc);
    boolean bool1 = (type2.getSort() == 9) ? type2.getDimensions() : false;
    boolean bool2 = (this.returnType.getSort() == 9) ? this.returnType.getDimensions() : false;
    injectAtScalarField(paramTarget, fieldInsnNode, i, type1, type2);
  }
  
  private void injectAtArrayField(Target paramTarget, FieldInsnNode paramFieldInsnNode, int paramInt1, Type paramType1, Type paramType2, int paramInt2, int paramInt3) {
    Type type = paramType2.getElementType();
    if (paramInt1 != 178 && paramInt1 != 180)
      throw new InvalidInjectionException(this.info, "Unspported opcode " + Bytecode.getOpcodeName(paramInt1) + " for array access " + this.info); 
    if (this.returnType.getSort() != 0) {
      if (paramInt3 != 190)
        paramInt3 = type.getOpcode(46); 
      AbstractInsnNode abstractInsnNode = BeforeFieldAccess.findArrayNode(paramTarget.insns, paramFieldInsnNode, paramInt3, paramInt2);
      injectAtGetArray(paramTarget, paramFieldInsnNode, abstractInsnNode, paramType1, paramType2);
    } else {
      AbstractInsnNode abstractInsnNode = BeforeFieldAccess.findArrayNode(paramTarget.insns, paramFieldInsnNode, type.getOpcode(79), paramInt2);
      injectAtSetArray(paramTarget, paramFieldInsnNode, abstractInsnNode, paramType1, paramType2);
    } 
  }
  
  private void injectAtGetArray(Target paramTarget, FieldInsnNode paramFieldInsnNode, AbstractInsnNode paramAbstractInsnNode, Type paramType1, Type paramType2) {
    String str = getGetArrayHandlerDescriptor(paramAbstractInsnNode, this.returnType, paramType2);
    boolean bool = checkDescriptor(str, paramTarget, "array getter");
    injectArrayRedirect(paramTarget, paramFieldInsnNode, paramAbstractInsnNode, bool, "array getter");
  }
  
  private void injectAtSetArray(Target paramTarget, FieldInsnNode paramFieldInsnNode, AbstractInsnNode paramAbstractInsnNode, Type paramType1, Type paramType2) {
    String str = Bytecode.generateDescriptor(null, (Object[])getArrayArgs(paramType2, 1, new Type[] { paramType2.getElementType() }));
    boolean bool = checkDescriptor(str, paramTarget, "array setter");
    injectArrayRedirect(paramTarget, paramFieldInsnNode, paramAbstractInsnNode, bool, "array setter");
  }
  
  public void injectArrayRedirect(Target paramTarget, FieldInsnNode paramFieldInsnNode, AbstractInsnNode paramAbstractInsnNode, boolean paramBoolean, String paramString) {
    String str = "";
    throw new InvalidInjectionException(this.info, "Array element " + this.annotationType + " on " + this + " could not locate a matching " + paramString + " instruction in " + paramTarget + ". " + str);
  }
  
  public void injectAtScalarField(Target paramTarget, FieldInsnNode paramFieldInsnNode, int paramInt, Type paramType1, Type paramType2) {
    AbstractInsnNode abstractInsnNode = null;
    InsnList insnList = new InsnList();
    if (paramInt == 178 || paramInt == 180) {
      abstractInsnNode = injectAtGetField(insnList, paramTarget, paramFieldInsnNode, (paramInt == 178), paramType1, paramType2);
    } else if (paramInt == 179 || paramInt == 181) {
      abstractInsnNode = injectAtPutField(insnList, paramTarget, paramFieldInsnNode, (paramInt == 179), paramType1, paramType2);
    } else {
      throw new InvalidInjectionException(this.info, "Unspported opcode " + Bytecode.getOpcodeName(paramInt) + " for " + this.info);
    } 
    paramTarget.replaceNode((AbstractInsnNode)paramFieldInsnNode, abstractInsnNode, insnList);
  }
  
  private AbstractInsnNode injectAtGetField(InsnList paramInsnList, Target paramTarget, FieldInsnNode paramFieldInsnNode, boolean paramBoolean, Type paramType1, Type paramType2) {
    paramBoolean = false;
  }
  
  private AbstractInsnNode injectAtPutField(InsnList paramInsnList, Target paramTarget, FieldInsnNode paramFieldInsnNode, boolean paramBoolean, Type paramType1, Type paramType2) {
    paramBoolean = false;
  }
  
  protected boolean checkDescriptor(String paramString1, Target paramTarget, String paramString2) {
    if (this.methodNode.desc.equals(paramString1))
      return false; 
    int i = paramString1.indexOf(')');
    String str = String.format("%s%s%s", new Object[] { paramString1.substring(0, i), Joiner.on("").join((Object[])paramTarget.arguments), paramString1.substring(i) });
    if (this.methodNode.desc.equals(str))
      return true; 
    throw new InvalidInjectionException(this.info, this.annotationType + " method " + paramString2 + " " + this + " has an invalid signature. Expected " + paramString1 + " but found " + this.methodNode.desc);
  }
  
  protected void injectAtConstructor(Target paramTarget, InjectionNodes.InjectionNode paramInjectionNode) {
    RedirectInjector$ConstructorRedirectData redirectInjector$ConstructorRedirectData = (RedirectInjector$ConstructorRedirectData)paramInjectionNode.getDecoration("ctor");
    boolean bool = ((Boolean)paramInjectionNode.getDecoration("wildcard")).booleanValue();
    TypeInsnNode typeInsnNode = (TypeInsnNode)paramInjectionNode.getCurrentTarget();
    AbstractInsnNode abstractInsnNode = paramTarget.get(paramTarget.indexOf((AbstractInsnNode)typeInsnNode) + 1);
    MethodInsnNode methodInsnNode = paramTarget.findInitNodeFor(typeInsnNode);
    throw new InvalidInjectionException(this.info, this.annotationType + " ctor invocation was not found in " + paramTarget);
  }
  
  private static String getGetArrayHandlerDescriptor(AbstractInsnNode paramAbstractInsnNode, Type paramType1, Type paramType2) {
    return (paramAbstractInsnNode.getOpcode() == 190) ? Bytecode.generateDescriptor(Type.INT_TYPE, (Object[])getArrayArgs(paramType2, 0, new Type[0])) : Bytecode.generateDescriptor(paramType1, (Object[])getArrayArgs(paramType2, 1, new Type[0]));
  }
  
  private static Type[] getArrayArgs(Type paramType, int paramInt, Type... paramVarArgs) {
    int i = paramType.getDimensions() + paramInt;
    Type[] arrayOfType = new Type[i + paramVarArgs.length];
    for (byte b = 0; b < arrayOfType.length; b++)
      arrayOfType[b] = paramType; 
    return arrayOfType;
  }
}
