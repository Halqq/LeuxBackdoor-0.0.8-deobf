package org.spongepowered.asm.mixin.transformer;

import com.google.gson.annotations.SerializedName;
import java.util.List;

class MixinConfig$InjectorOptions {
  @SerializedName("defaultRequire")
  int defaultRequireValue = 0;
  
  @SerializedName("defaultGroup")
  String defaultGroup = "default";
  
  @SerializedName("injectionPoints")
  List injectionPoints;
  
  @SerializedName("maxShiftBy")
  int maxShiftBy = 0;
}
