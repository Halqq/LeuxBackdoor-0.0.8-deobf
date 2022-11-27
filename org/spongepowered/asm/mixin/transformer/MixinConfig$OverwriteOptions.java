package org.spongepowered.asm.mixin.transformer;

import com.google.gson.annotations.SerializedName;

class MixinConfig$OverwriteOptions {
  @SerializedName("conformVisibility")
  boolean conformAccessModifiers;
  
  @SerializedName("requireAnnotations")
  boolean requireOverwriteAnnotations;
}
