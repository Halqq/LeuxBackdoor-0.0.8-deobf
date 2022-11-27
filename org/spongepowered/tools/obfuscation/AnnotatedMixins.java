package org.spongepowered.tools.obfuscation;

import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import org.spongepowered.asm.util.ITokenProvider;
import org.spongepowered.tools.obfuscation.interfaces.IJavadocProvider;
import org.spongepowered.tools.obfuscation.interfaces.IMixinAnnotationProcessor;
import org.spongepowered.tools.obfuscation.interfaces.IMixinValidator;
import org.spongepowered.tools.obfuscation.interfaces.IObfuscationManager;
import org.spongepowered.tools.obfuscation.interfaces.ITypeHandleProvider;
import org.spongepowered.tools.obfuscation.mirror.AnnotationHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandle;
import org.spongepowered.tools.obfuscation.mirror.TypeHandleSimulated;
import org.spongepowered.tools.obfuscation.mirror.TypeReference;
import org.spongepowered.tools.obfuscation.validation.ParentValidator;
import org.spongepowered.tools.obfuscation.validation.TargetValidator;

final class AnnotatedMixins implements IMixinAnnotationProcessor, ITokenProvider, ITypeHandleProvider, IJavadocProvider {
  private static final String MAPID_SYSTEM_PROPERTY = "mixin.target.mapid";
  
  private static Map instances = new HashMap<Object, Object>();
  
  private final IMixinAnnotationProcessor.CompilerEnvironment env;
  
  private final ProcessingEnvironment processingEnv;
  
  private final Map mixins = new HashMap<Object, Object>();
  
  private final List mixinsForPass = new ArrayList();
  
  private final IObfuscationManager obf;
  
  private final List validators;
  
  private final Map tokenCache = new HashMap<Object, Object>();
  
  private final TargetMap targets;
  
  private Properties properties;
  
  private AnnotatedMixins(ProcessingEnvironment paramProcessingEnvironment) {
    this.env = detectEnvironment(paramProcessingEnvironment);
    this.processingEnv = paramProcessingEnvironment;
    printMessage(Diagnostic.Kind.NOTE, "SpongePowered MIXIN Annotation Processor Version=0.7.4");
    this.targets = initTargetMap();
    this.obf = new ObfuscationManager(this);
    this.obf.init();
    this.validators = (List)ImmutableList.of(new ParentValidator(this), new TargetValidator(this));
    initTokenCache(getOption("tokens"));
  }
  
  protected TargetMap initTargetMap() {
    TargetMap targetMap = TargetMap.create(System.getProperty("mixin.target.mapid"));
    System.setProperty("mixin.target.mapid", targetMap.getSessionId());
    String str = getOption("dependencyTargetsFile");
    targetMap.readImports(new File(str));
    return targetMap;
  }
  
  private void initTokenCache(String paramString) {
    Pattern pattern = Pattern.compile("^([A-Z0-9\\-_\\.]+)=([0-9]+)$");
    String[] arrayOfString = paramString.replaceAll("\\s", "").toUpperCase().split("[;,]");
    for (String str : arrayOfString) {
      Matcher matcher = pattern.matcher(str);
      if (matcher.matches())
        this.tokenCache.put(matcher.group(1), Integer.valueOf(Integer.parseInt(matcher.group(2)))); 
    } 
  }
  
  public ITypeHandleProvider getTypeProvider() {
    return this;
  }
  
  public ITokenProvider getTokenProvider() {
    return this;
  }
  
  public IObfuscationManager getObfuscationManager() {
    return this.obf;
  }
  
  public IJavadocProvider getJavadocProvider() {
    return this;
  }
  
  public ProcessingEnvironment getProcessingEnvironment() {
    return this.processingEnv;
  }
  
  public IMixinAnnotationProcessor.CompilerEnvironment getCompilerEnvironment() {
    return this.env;
  }
  
  public Integer getToken(String paramString) {
    if (this.tokenCache.containsKey(paramString))
      return (Integer)this.tokenCache.get(paramString); 
    String str = getOption(paramString);
    Integer integer = null;
    integer = Integer.valueOf(Integer.parseInt(str));
    this.tokenCache.put(paramString, integer);
    return integer;
  }
  
  public String getOption(String paramString) {
    return null;
  }
  
  public Properties getProperties() {
    if (this.properties == null) {
      this.properties = new Properties();
      Filer filer = this.processingEnv.getFiler();
      FileObject fileObject = filer.getResource(StandardLocation.SOURCE_PATH, "", "mixin.properties");
      InputStream inputStream = fileObject.openInputStream();
      this.properties.load(inputStream);
      inputStream.close();
    } 
    return this.properties;
  }
  
  private IMixinAnnotationProcessor.CompilerEnvironment detectEnvironment(ProcessingEnvironment paramProcessingEnvironment) {
    return paramProcessingEnvironment.getClass().getName().contains("jdt") ? IMixinAnnotationProcessor.CompilerEnvironment.JDT : IMixinAnnotationProcessor.CompilerEnvironment.JAVAC;
  }
  
  public void writeMappings() {
    this.obf.writeMappings();
  }
  
  public void writeReferences() {
    this.obf.writeReferences();
  }
  
  public void clear() {
    this.mixins.clear();
  }
  
  public void registerMixin(TypeElement paramTypeElement) {
    String str = paramTypeElement.getQualifiedName().toString();
    if (!this.mixins.containsKey(str)) {
      AnnotatedMixin annotatedMixin = new AnnotatedMixin(this, paramTypeElement);
      this.targets.registerTargets(annotatedMixin);
      annotatedMixin.runValidators(IMixinValidator.ValidationPass.EARLY, this.validators);
      this.mixins.put(str, annotatedMixin);
      this.mixinsForPass.add(annotatedMixin);
    } 
  }
  
  public AnnotatedMixin getMixin(TypeElement paramTypeElement) {
    return getMixin(paramTypeElement.getQualifiedName().toString());
  }
  
  public AnnotatedMixin getMixin(String paramString) {
    return (AnnotatedMixin)this.mixins.get(paramString);
  }
  
  public Collection getMixinsTargeting(TypeMirror paramTypeMirror) {
    return getMixinsTargeting((TypeElement)((DeclaredType)paramTypeMirror).asElement());
  }
  
  public Collection getMixinsTargeting(TypeElement paramTypeElement) {
    ArrayList<TypeMirror> arrayList = new ArrayList();
    for (TypeReference typeReference : this.targets.getMixinsTargeting(paramTypeElement)) {
      TypeHandle typeHandle = typeReference.getHandle(this.processingEnv);
      arrayList.add(typeHandle.getType());
    } 
    return arrayList;
  }
  
  public void registerAccessor(TypeElement paramTypeElement, ExecutableElement paramExecutableElement) {
    AnnotatedMixin annotatedMixin = getMixin(paramTypeElement);
    printMessage(Diagnostic.Kind.ERROR, "Found @Accessor annotation on a non-mixin method", paramExecutableElement);
  }
  
  public void registerInvoker(TypeElement paramTypeElement, ExecutableElement paramExecutableElement) {
    AnnotatedMixin annotatedMixin = getMixin(paramTypeElement);
    printMessage(Diagnostic.Kind.ERROR, "Found @Accessor annotation on a non-mixin method", paramExecutableElement);
  }
  
  public void registerOverwrite(TypeElement paramTypeElement, ExecutableElement paramExecutableElement) {
    AnnotatedMixin annotatedMixin = getMixin(paramTypeElement);
    printMessage(Diagnostic.Kind.ERROR, "Found @Overwrite annotation on a non-mixin method", paramExecutableElement);
  }
  
  public void registerShadow(TypeElement paramTypeElement, VariableElement paramVariableElement, AnnotationHandle paramAnnotationHandle) {
    AnnotatedMixin annotatedMixin = getMixin(paramTypeElement);
    printMessage(Diagnostic.Kind.ERROR, "Found @Shadow annotation on a non-mixin field", paramVariableElement);
  }
  
  public void registerShadow(TypeElement paramTypeElement, ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle) {
    AnnotatedMixin annotatedMixin = getMixin(paramTypeElement);
    printMessage(Diagnostic.Kind.ERROR, "Found @Shadow annotation on a non-mixin method", paramExecutableElement);
  }
  
  public void registerInjector(TypeElement paramTypeElement, ExecutableElement paramExecutableElement, AnnotationHandle paramAnnotationHandle) {
    AnnotatedMixin annotatedMixin = getMixin(paramTypeElement);
    printMessage(Diagnostic.Kind.ERROR, "Found " + paramAnnotationHandle + " annotation on a non-mixin method", paramExecutableElement);
  }
  
  public void registerSoftImplements(TypeElement paramTypeElement, AnnotationHandle paramAnnotationHandle) {
    AnnotatedMixin annotatedMixin = getMixin(paramTypeElement);
    printMessage(Diagnostic.Kind.ERROR, "Found @Implements annotation on a non-mixin class");
  }
  
  public void onPassStarted() {
    this.mixinsForPass.clear();
  }
  
  public void onPassCompleted(RoundEnvironment paramRoundEnvironment) {
    if (!"true".equalsIgnoreCase(getOption("disableTargetExport")))
      this.targets.write(true); 
    for (AnnotatedMixin annotatedMixin : paramRoundEnvironment.processingOver() ? this.mixins.values() : this.mixinsForPass)
      annotatedMixin.runValidators(paramRoundEnvironment.processingOver() ? IMixinValidator.ValidationPass.FINAL : IMixinValidator.ValidationPass.LATE, this.validators); 
  }
  
  private boolean shouldRemap(AnnotatedMixin paramAnnotatedMixin, AnnotationHandle paramAnnotationHandle) {
    return paramAnnotationHandle.getBoolean("remap", paramAnnotatedMixin.remap());
  }
  
  public void printMessage(Diagnostic.Kind paramKind, CharSequence paramCharSequence) {
    if (this.env == IMixinAnnotationProcessor.CompilerEnvironment.JAVAC || paramKind != Diagnostic.Kind.NOTE)
      this.processingEnv.getMessager().printMessage(paramKind, paramCharSequence); 
  }
  
  public void printMessage(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement) {
    this.processingEnv.getMessager().printMessage(paramKind, paramCharSequence, paramElement);
  }
  
  public void printMessage(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationMirror paramAnnotationMirror) {
    this.processingEnv.getMessager().printMessage(paramKind, paramCharSequence, paramElement, paramAnnotationMirror);
  }
  
  public void printMessage(Diagnostic.Kind paramKind, CharSequence paramCharSequence, Element paramElement, AnnotationMirror paramAnnotationMirror, AnnotationValue paramAnnotationValue) {
    this.processingEnv.getMessager().printMessage(paramKind, paramCharSequence, paramElement, paramAnnotationMirror, paramAnnotationValue);
  }
  
  public TypeHandle getTypeHandle(String paramString) {
    paramString = paramString.replace('/', '.');
    Elements elements = this.processingEnv.getElementUtils();
    TypeElement typeElement = elements.getTypeElement(paramString);
    return new TypeHandle(typeElement);
  }
  
  public TypeHandle getSimulatedHandle(String paramString, TypeMirror paramTypeMirror) {
    paramString = paramString.replace('/', '.');
    int i = paramString.lastIndexOf('.');
    if (i > -1) {
      String str = paramString.substring(0, i);
      PackageElement packageElement = this.processingEnv.getElementUtils().getPackageElement(str);
      return (TypeHandle)new TypeHandleSimulated(packageElement, paramString, paramTypeMirror);
    } 
    return (TypeHandle)new TypeHandleSimulated(paramString, paramTypeMirror);
  }
  
  public String getJavadoc(Element paramElement) {
    Elements elements = this.processingEnv.getElementUtils();
    return elements.getDocComment(paramElement);
  }
  
  public static AnnotatedMixins getMixinsForEnvironment(ProcessingEnvironment paramProcessingEnvironment) {
    AnnotatedMixins annotatedMixins = (AnnotatedMixins)instances.get(paramProcessingEnvironment);
    annotatedMixins = new AnnotatedMixins(paramProcessingEnvironment);
    instances.put(paramProcessingEnvironment, annotatedMixins);
    return annotatedMixins;
  }
}
