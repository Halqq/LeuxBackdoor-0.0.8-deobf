package org.spongepowered.asm.mixin.gen;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.lib.Type;
import org.spongepowered.asm.lib.tree.FieldNode;
import org.spongepowered.asm.lib.tree.MethodNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.gen.throwables.InvalidAccessorException;
import org.spongepowered.asm.mixin.injection.struct.MemberInfo;
import org.spongepowered.asm.mixin.refmap.IMixinContext;
import org.spongepowered.asm.mixin.struct.SpecialMethodInfo;
import org.spongepowered.asm.mixin.transformer.MixinTargetContext;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.util.Bytecode;

public class AccessorInfo extends SpecialMethodInfo {
  protected static final Pattern PATTERN_ACCESSOR = Pattern.compile("^(get|set|is|invoke|call)(([A-Z])(.*?))(_\\$md.*)?$");
  
  protected final Type[] argTypes;
  
  protected final Type returnType;
  
  protected final AccessorInfo$AccessorType type;
  
  private final Type targetFieldType;
  
  protected final MemberInfo target;
  
  protected FieldNode targetField;
  
  protected MethodNode targetMethod;
  
  public AccessorInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode) {
    this(paramMixinTargetContext, paramMethodNode, Accessor.class);
  }
  
  protected AccessorInfo(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, Class paramClass) {
    super(paramMixinTargetContext, paramMethodNode, Annotations.getVisible(paramMethodNode, paramClass));
    this.argTypes = Type.getArgumentTypes(paramMethodNode.desc);
    this.returnType = Type.getReturnType(paramMethodNode.desc);
    this.type = initType();
    this.targetFieldType = initTargetFieldType();
    this.target = initTarget();
  }
  
  protected AccessorInfo$AccessorType initType() {
    return this.returnType.equals(Type.VOID_TYPE) ? AccessorInfo$AccessorType.FIELD_SETTER : AccessorInfo$AccessorType.FIELD_GETTER;
  }
  
  protected Type initTargetFieldType() {
    switch (AccessorInfo$1.$SwitchMap$org$spongepowered$asm$mixin$gen$AccessorInfo$AccessorType[this.type.ordinal()]) {
      case 1:
        if (this.argTypes.length > 0)
          throw new InvalidAccessorException(this.mixin, this + " must take exactly 0 arguments, found " + this.argTypes.length); 
        return this.returnType;
      case 2:
        if (this.argTypes.length != 1)
          throw new InvalidAccessorException(this.mixin, this + " must take exactly 1 argument, found " + this.argTypes.length); 
        return this.argTypes[0];
    } 
    throw new InvalidAccessorException(this.mixin, "Computed unsupported accessor type " + this.type + " for " + this);
  }
  
  protected MemberInfo initTarget() {
    MemberInfo memberInfo = new MemberInfo(getTargetName(), null, this.targetFieldType.getDescriptor());
    this.annotation.visit("target", memberInfo.toString());
    return memberInfo;
  }
  
  protected String getTargetName() {
    String str = (String)Annotations.getValue(this.annotation);
    if (Strings.isNullOrEmpty(str)) {
      String str1 = inflectTarget();
      throw new InvalidAccessorException(this.mixin, "Failed to inflect target name for " + this + ", supported prefixes: [get, set, is]");
    } 
    return (MemberInfo.parse(str, (IMixinContext)this.mixin)).name;
  }
  
  protected String inflectTarget() {
    return inflectTarget(this.method.name, this.type, toString(), (IMixinContext)this.mixin, this.mixin.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE));
  }
  
  public static String inflectTarget(String paramString1, AccessorInfo$AccessorType paramAccessorInfo$AccessorType, String paramString2, IMixinContext paramIMixinContext, boolean paramBoolean) {
    Matcher matcher = PATTERN_ACCESSOR.matcher(paramString1);
    if (matcher.matches()) {
      String str1 = matcher.group(1);
      String str2 = matcher.group(3);
      String str3 = matcher.group(4);
      String str4 = String.format("%s%s", new Object[] { toLowerCase(str2, !isUpperCase(str3)), str3 });
      if (!paramAccessorInfo$AccessorType.isExpectedPrefix(str1))
        LogManager.getLogger("mixin").warn("Unexpected prefix for {}, found [{}] expecting {}", new Object[] { paramString2, str1, paramAccessorInfo$AccessorType.getExpectedPrefixes() }); 
      return (MemberInfo.parse(str4, paramIMixinContext)).name;
    } 
    return null;
  }
  
  public final MemberInfo getTarget() {
    return this.target;
  }
  
  public final Type getTargetFieldType() {
    return this.targetFieldType;
  }
  
  public final FieldNode getTargetField() {
    return this.targetField;
  }
  
  public final MethodNode getTargetMethod() {
    return this.targetMethod;
  }
  
  public final Type getReturnType() {
    return this.returnType;
  }
  
  public final Type[] getArgTypes() {
    return this.argTypes;
  }
  
  public String toString() {
    return String.format("%s->@%s[%s]::%s%s", new Object[] { this.mixin.toString(), Bytecode.getSimpleName(this.annotation), this.type.toString(), this.method.name, this.method.desc });
  }
  
  public void locate() {
    this.targetField = findTargetField();
  }
  
  public MethodNode generate() {
    MethodNode methodNode = this.type.getGenerator(this).generate();
    Bytecode.mergeAnnotations(this.method, methodNode);
    return methodNode;
  }
  
  private FieldNode findTargetField() {
    return (FieldNode)findTarget(this.classNode.fields);
  }
  
  protected Object findTarget(List paramList) {
    Object object = null;
    ArrayList arrayList = new ArrayList();
    for (Object object1 : paramList) {
      String str1 = getNodeDesc(object1);
      if (!str1.equals(this.target.desc))
        continue; 
      String str2 = getNodeName(object1);
      if (str2.equals(this.target.name))
        object = object1; 
      if (str2.equalsIgnoreCase(this.target.name))
        arrayList.add(object1); 
    } 
    if (arrayList.size() > 1)
      LogManager.getLogger("mixin").debug("{} found an exact match for {} but other candidates were found!", new Object[] { this, this.target }); 
    return object;
  }
  
  private static String getNodeDesc(Object paramObject) {
    return (paramObject instanceof MethodNode) ? ((MethodNode)paramObject).desc : ((paramObject instanceof FieldNode) ? ((FieldNode)paramObject).desc : null);
  }
  
  private static String getNodeName(Object paramObject) {
    return (paramObject instanceof MethodNode) ? ((MethodNode)paramObject).name : ((paramObject instanceof FieldNode) ? ((FieldNode)paramObject).name : null);
  }
  
  public static AccessorInfo of(MixinTargetContext paramMixinTargetContext, MethodNode paramMethodNode, Class<Accessor> paramClass) {
    if (paramClass == Accessor.class)
      return new AccessorInfo(paramMixinTargetContext, paramMethodNode); 
    if (paramClass == Invoker.class)
      return new InvokerInfo(paramMixinTargetContext, paramMethodNode); 
    throw new InvalidAccessorException(paramMixinTargetContext, "Could not parse accessor for unknown type " + paramClass.getName());
  }
  
  private static String toLowerCase(String paramString, boolean paramBoolean) {
    paramBoolean = false;
  }
  
  private static boolean isUpperCase(String paramString) {
    return paramString.toUpperCase().equals(paramString);
  }
}
