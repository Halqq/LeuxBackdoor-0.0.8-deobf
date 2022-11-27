package me.obsidianbreaker.leux.client.modules.render;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class Brightness extends Module {
  public Setting mode = create("Mode", "Mode", "Gamma", combobox(new String[] { "Gamma", "Potion" }));
  
  public float old_gamma_value;
  
  public void disable() {
    (give up)null;
    mc.field_71474_y.field_74333_Y = this.old_gamma_value;
    mc.field_71439_g.func_184589_d(MobEffects.field_76439_r);
  }
  
  public void enable() {
    (give up)null;
    this.old_gamma_value = mc.field_71474_y.field_74333_Y;
  }
  
  public Brightness() {
    super(Category.render);
  }
  
  public void update() {
    (give up)null;
    if (this.mode.in("Gamma")) {
      mc.field_71474_y.field_74333_Y = Float.intBitsToFloat(1.05583328E9F ^ 0x7F4EBCBE);
    } else {
      mc.field_71474_y.field_74333_Y = Float.intBitsToFloat(1.11478208E9F ^ 0x7DF2396F);
      mc.field_71439_g.func_70690_d(new PotionEffect(MobEffects.field_76439_r, 5210));
    } 
  }
  
  public String array_detail() {
    (give up)null;
    return this.mode.get_current_value();
  }
}
