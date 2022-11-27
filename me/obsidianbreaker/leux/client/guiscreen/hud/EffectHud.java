package me.obsidianbreaker.leux.client.guiscreen.hud;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EffectHud extends Pinnable {
  public void render() {
    (give up)null;
    byte b = 12;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    ArrayList arrayList = new ArrayList(this.mc.field_71439_g.func_70651_bq());
    Comparator comparator = this::lambda$render$0;
    arrayList.sort(comparator);
    Iterator<PotionEffect> iterator = arrayList.iterator();
    while (true) {
      PotionEffect potionEffect;
      if (iterator.hasNext()) {
        potionEffect = iterator.next();
        if (potionEffect.func_188419_a() == MobEffects.field_76420_g) {
          String str = ChatFormatting.DARK_RED + get_friendly_potion_name(potionEffect) + " " + ChatFormatting.RESET + Potion.func_188410_a(potionEffect, Float.intBitsToFloat(1.0974016E9F ^ 0x7EE904F7));
          create_line(str, docking(1, str), b, i, j, k, m);
          b += 12;
          continue;
        } 
      } else {
        set_width(get("weakness", "width") + 12);
        set_height(get("weakness", "height") + 36);
        return;
      } 
      if (potionEffect.func_188419_a() == MobEffects.field_76424_c) {
        String str = ChatFormatting.BLUE + get_friendly_potion_name(potionEffect) + " " + ChatFormatting.RESET + Potion.func_188410_a(potionEffect, Float.intBitsToFloat(1.09715264E9F ^ 0x7EE53873));
        create_line(str, docking(1, str), b, i, j, k, m);
        b += 12;
        continue;
      } 
      if (potionEffect.func_188419_a() == MobEffects.field_76437_t) {
        String str = ChatFormatting.GRAY + get_friendly_potion_name(potionEffect) + " " + ChatFormatting.RESET + Potion.func_188410_a(potionEffect, Float.intBitsToFloat(1.08679514E9F ^ 0x7F472DAE));
        create_line(str, docking(1, str), b, i, j, k, m);
        b += 12;
        continue;
      } 
      if (potionEffect.func_188419_a() == MobEffects.field_76430_j) {
        String str = ChatFormatting.GREEN + get_friendly_potion_name(potionEffect) + " " + ChatFormatting.RESET + Potion.func_188410_a(potionEffect, Float.intBitsToFloat(1.08852762E9F ^ 0x7F619CED));
        create_line(str, docking(1, str), b, i, j, k, m);
        b += 12;
        continue;
      } 
      if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDAllPotions").get_value(true)) {
        String str = get_friendly_potion_name(potionEffect) + " " + Potion.func_188410_a(potionEffect, Float.intBitsToFloat(1.10781402E9F ^ 0x7D87E69F));
        create_line(str, docking(1, str), b, i, j, k, m);
        b += 12;
      } 
    } 
  }
  
  public static String get_name_duration_string(PotionEffect paramPotionEffect) {
    (give up)null;
    return String.format("%s (%s)", new Object[] { get_friendly_potion_name(paramPotionEffect), Potion.func_188410_a(paramPotionEffect, Float.intBitsToFloat(1.08425203E9F ^ 0x7F205FA9)) });
  }
  
  public EffectHud() {
    super("Effect Hud", "effecthud", Float.intBitsToFloat(1.09628723E9F ^ 0x7ED80421), 0, 0);
  }
  
  public int lambda$render$0(PotionEffect paramPotionEffect1, PotionEffect paramPotionEffect2) {
    (give up)null;
    String str1 = get_friendly_potion_name(paramPotionEffect1) + " " + ChatFormatting.GRAY + Potion.func_188410_a(paramPotionEffect1, Float.intBitsToFloat(1.08231731E9F ^ 0x7F02D9E0));
    String str2 = get_friendly_potion_name(paramPotionEffect2) + " " + ChatFormatting.GRAY + Potion.func_188410_a(paramPotionEffect2, Float.intBitsToFloat(1.11497114E9F ^ 0x7DF51BC7));
    float f = (this.mc.field_71466_p.func_78256_a(str2) - this.mc.field_71466_p.func_78256_a(str1));
    return (f != Float.intBitsToFloat(2.13289434E9F ^ 0x7F2162B6)) ? (int)f : str2.compareTo(str1);
  }
  
  public static String get_friendly_potion_name(PotionEffect paramPotionEffect) {
    (give up)null;
    String str = I18n.func_135052_a(paramPotionEffect.func_188419_a().func_76393_a(), new Object[0]);
    if (paramPotionEffect.func_76458_c() == 1) {
      str = str + " " + I18n.func_135052_a("enchantment.level.2", new Object[0]);
    } else if (paramPotionEffect.func_76458_c() == 2) {
      str = str + " " + I18n.func_135052_a("enchantment.level.3", new Object[0]);
    } else if (paramPotionEffect.func_76458_c() == 3) {
      str = str + " " + I18n.func_135052_a("enchantment.level.4", new Object[0]);
    } 
    return str;
  }
}
