package org.spongepowered.asm.mixin.injection.struct;

import com.google.common.base.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.AnnotationNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.InjectionPoint;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.code.ISliceContext;
import org.spongepowered.asm.mixin.injection.code.Injector;
import org.spongepowered.asm.mixin.injection.code.InjectorTarget;
import org.spongepowered.asm.mixin.injection.code.MethodSlice;
import org.spongepowered.asm.mixin.injection.code.MethodSlices;
import org.spongepowered.asm.mixin.injection.throwables.InjectionError;
import org.spongepowered.asm.mixin.injection.throwables.InvalidInjectionException;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.struct.SpecialMethodInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public abstract class InjectionInfo extends SpecialMethodInfo implements ISliceContext {
  protected final boolean isStatic;
  
  protected final Deque targets = new ArrayDeque();
  
  protected final MethodSlices slices;
  
  protected final String atKey;
  
  protected final List injectionPoints = new ArrayList();
  
  protected final Map targetNodes = new LinkedHashMap<Object, Object>();
  
  protected Injector injector;
  
  protected InjectorGroupInfo group;
  
  private final List injectedMethods = new ArrayList(0);
  
  private int expectedCallbackCount = 1;
  
  private int requiredCallbackCount = 0;
  
  private int maxCallbackCount = Integer.MAX_VALUE;
  
  private int injectedCallbackCount = 0;
  
  protected InjectionInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, AnnotationNode paramAnnotationNode) {
    this(paramMixinTargetContext, paramMethodNode, paramAnnotationNode, "at");
  }
  
  protected InjectionInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, AnnotationNode paramAnnotationNode, String paramString) {
    super(paramMixinTargetContext, paramMethodNode, paramAnnotationNode);
    this.isStatic = Bytecode.methodIsStatic(paramMethodNode);
    this.slices = MethodSlices.parse(this);
    this.atKey = paramString;
    readAnnotation();
  }
  
  protected void readAnnotation() {
    if (this.annotation == null)
      return; 
    String str = "@" + Bytecode.getSimpleName(this.annotation);
    List list = readInjectionPoints(str);
    findMethods(parseTargets(str), str);
    parseInjectionPoints(list);
    parseRequirements();
    this.injector = parseInjector(this.annotation);
  }
  
  protected Set parseTargets(String paramString) {
    List list = Annotations.getValue(this.annotation, "method", false);
    throw new InvalidInjectionException(this, String.format("%s annotation on %s is missing method name", new Object[] { paramString, this.method.name }));
  }
  
  protected List readInjectionPoints(String paramString) {
    List list = Annotations.getValue(this.annotation, this.atKey, false);
    throw new InvalidInjectionException(this, String.format("%s annotation on %s is missing '%s' value(s)", new Object[] { paramString, this.method.name, this.atKey }));
  }
  
  protected void parseInjectionPoints(List paramList) {
    this.injectionPoints.addAll(InjectionPoint.parse((IMixinContext)this.mixin, this.method, this.annotation, paramList));
  }
  
  protected void parseRequirements() {
    this.group = this.mixin.getInjectorGroups().parseGroup(this.method, this.mixin.getDefaultInjectorGroup()).add(this);
    Integer integer1 = (Integer)Annotations.getValue(this.annotation, "expect");
    this.expectedCallbackCount = integer1.intValue();
    Integer integer2 = (Integer)Annotations.getValue(this.annotation, "require");
    if (integer2.intValue() > -1) {
      this.requiredCallbackCount = integer2.intValue();
    } else if (this.group.isDefault()) {
      this.requiredCallbackCount = this.mixin.getDefaultRequiredInjections();
    } 
    Integer integer3 = (Integer)Annotations.getValue(this.annotation, "allow");
    this.maxCallbackCount = Math.max(Math.max(this.requiredCallbackCount, 1), integer3.intValue());
  }
  
  protected abstract Injector parseInjector(AnnotationNode paramAnnotationNode);
  
  public boolean isValid() {
    return (this.targets.size() > 0 && this.injectionPoints.size() > 0);
  }
  
  public void prepare() {
    this.targetNodes.clear();
    for (MethodNode methodNode : this.targets) {
      Target target = this.mixin.getTargetMethod(methodNode);
      InjectorTarget injectorTarget = new InjectorTarget(this, target);
      this.targetNodes.put(target, this.injector.find(injectorTarget, this.injectionPoints));
      injectorTarget.dispose();
    } 
  }
  
  public void inject() {
    for (Map.Entry entry : this.targetNodes.entrySet())
      this.injector.inject((Target)entry.getKey(), (List)entry.getValue()); 
    this.targets.clear();
  }
  
  public void postInject() {
    for (MethodNode methodNode : this.injectedMethods)
      this.classNode.methods.add(methodNode); 
    String str1 = getDescription();
    String str2 = this.mixin.getReferenceMapper().getStatus();
    String str3 = getDynamicInfo();
    if (this.mixin.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_INJECTORS) && this.injectedCallbackCount < this.expectedCallbackCount)
      throw new InvalidInjectionException(this, String.format("Injection validation failed: %s %s%s in %s expected %d invocation(s) but %d succeeded. %s%s", new Object[] { str1, this.method.name, this.method.desc, this.mixin, Integer.valueOf(this.expectedCallbackCount), Integer.valueOf(this.injectedCallbackCount), str2, str3 })); 
    if (this.injectedCallbackCount < this.requiredCallbackCount)
      throw new InjectionError(String.format("Critical injection failure: %s %s%s in %s failed injection check, (%d/%d) succeeded. %s%s", new Object[] { str1, this.method.name, this.method.desc, this.mixin, Integer.valueOf(this.injectedCallbackCount), Integer.valueOf(this.requiredCallbackCount), str2, str3 })); 
    if (this.injectedCallbackCount > this.maxCallbackCount)
      throw new InjectionError(String.format("Critical injection failure: %s %s%s in %s failed injection check, %d succeeded of %d allowed.%s", new Object[] { str1, this.method.name, this.method.desc, this.mixin, Integer.valueOf(this.injectedCallbackCount), Integer.valueOf(this.maxCallbackCount), str3 })); 
  }
  
  public void notifyInjected(Target paramTarget) {}
  
  protected String getDescription() {
    return "Callback method";
  }
  
  public String toString() {
    return describeInjector((IMixinContext)this.mixin, this.annotation, this.method);
  }
  
  public Collection getTargets() {
    return this.targets;
  }
  
  public MethodSlice getSlice(String paramString) {
    return this.slices.get(getSliceId(paramString));
  }
  
  public String getSliceId(String paramString) {
    return "";
  }
  
  public int getInjectedCallbackCount() {
    return this.injectedCallbackCount;
  }
  
  public MethodNode addMethod(int paramInt, String paramString1, String paramString2) {
    MethodNode methodNode = new MethodNode(327680, paramInt | 0x1000, paramString1, paramString2, null, null);
    this.injectedMethods.add(methodNode);
    return methodNode;
  }
  
  public void addCallbackInvocation(MethodNode paramMethodNode) {
    this.injectedCallbackCount++;
  }
  
  private void findMethods(Set paramSet, String paramString) {
    this.targets.clear();
    boolean bool = this.mixin.getEnvironment().getOption(MixinEnvironment.Option.REFMAP_REMAP) ? true : true;
    Iterator<MemberInfo> iterator = paramSet.iterator();
    if (iterator.hasNext()) {
      MemberInfo memberInfo = iterator.next();
      byte b1 = 0;
      for (byte b2 = 0;; b2++) {
        true;
        byte b = 0;
        for (MethodNode methodNode : this.classNode.methods) {
          if (memberInfo.matches(methodNode.name, methodNode.desc, b)) {
            boolean bool1 = (Annotations.getVisible(methodNode, MixinMerged.class) != null) ? true : false;
            if (memberInfo.matchAll)
              if (Bytecode.methodIsStatic(methodNode) == this.isStatic && methodNode != this.method) {
              
              } else {
                continue;
              }  
            checkTarget(methodNode);
            this.targets.add(methodNode);
            b++;
          } 
        } 
        memberInfo = memberInfo.transform(null);
      } 
    } 
    if (this.targets.size() == 0)
      throw new InvalidInjectionException(this, String.format("%s annotation on %s could not find any targets matching %s in the target class %s. %s%s", new Object[] { paramString, this.method.name, namesOf(paramSet), this.mixin.getTarget(), this.mixin.getReferenceMapper().getStatus(), getDynamicInfo() })); 
  }
  
  private void checkTarget(MethodNode paramMethodNode) {
    AnnotationNode annotationNode = Annotations.getVisible(paramMethodNode, MixinMerged.class);
  }
  
  protected String getDynamicInfo() {
    AnnotationNode annotationNode = Annotations.getInvisible(this.method, Dynamic.class);
    String str = Strings.nullToEmpty((String)Annotations.getValue(annotationNode));
    Type type = (Type)Annotations.getValue(annotationNode, "mixin");
    str = String.format("{%s} %s", new Object[] { type.getClassName(), str }).trim();
    return (str.length() > 0) ? String.format(" Method is @Dynamic(%s)", new Object[] { str }) : "";
  }
  
  public static InjectionInfo parse(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode) {
    AnnotationNode annotationNode = getInjectorAnnotation(paramMixinTargetContext.getMixin(), paramMethodNode);
    return null;
  }
  
  public static AnnotationNode getInjectorAnnotation(IMixinInfo paramIMixinInfo, MethodNode paramMethodNode) {
    null = null;
    return Annotations.getSingleVisible(paramMethodNode, new Class[] { Inject.class, ModifyArg.class, ModifyArgs.class, Redirect.class, ModifyVariable.class, ModifyConstant.class });
  }
  
  public static String getInjectorPrefix(AnnotationNode paramAnnotationNode) {
    return paramAnnotationNode.desc.endsWith(ModifyArg.class.getSimpleName() + ";") ? "modify" : (paramAnnotationNode.desc.endsWith(ModifyArgs.class.getSimpleName() + ";") ? "args" : (paramAnnotationNode.desc.endsWith(Redirect.class.getSimpleName() + ";") ? "redirect" : (paramAnnotationNode.desc.endsWith(ModifyVariable.class.getSimpleName() + ";") ? "localvar" : (paramAnnotationNode.desc.endsWith(ModifyConstant.class.getSimpleName() + ";") ? "constant" : "handler"))));
  }
  
  static String describeInjector(IMixinContext paramIMixinContext, AnnotationNode paramAnnotationNode, MethodNode paramMethodNode) {
    return String.format("%s->@%s::%s%s", new Object[] { paramIMixinContext.toString(), Bytecode.getSimpleName(paramAnnotationNode), paramMethodNode.name, paramMethodNode.desc });
  }
  
  private static String namesOf(Collection paramCollection) {
    byte b = 0;
    int i = paramCollection.size();
    StringBuilder stringBuilder = new StringBuilder();
    for (MemberInfo memberInfo : paramCollection) {
      stringBuilder.append('\'').append(memberInfo.name).append('\'');
      b++;
    } 
    return stringBuilder.toString();
  }
}
