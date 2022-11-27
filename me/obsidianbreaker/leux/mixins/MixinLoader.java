package me.obsidianbreaker.leux.mixins;

import java.util.Map;
import javax.annotation.Nullable;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class MixinLoader implements IFMLLoadingPlugin {
  public MixinLoader() {
    MixinBootstrap.init();
    Mixins.addConfiguration("mixins.leux.json");
    MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
  }
  
  public String[] getASMTransformerClass() {
    return new String[0];
  }
  
  public String getModContainerClass() {
    return null;
  }
  
  @Nullable
  @Nullable
  public String getSetupClass() {
    return null;
  }
  
  public void injectData(Map paramMap) {}
  
  public String getAccessTransformerClass() {
    return null;
  }
}
