package me.obsidianbreaker.leux.client.guiscreen.hud;

import com.google.common.collect.Lists;
import give up;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.Draw;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.DrawnUtil;
import me.obsidianbreaker.leux.client.util.FontUtil;
import net.minecraft.util.math.MathHelper;

public class ModulesArrayList extends Pinnable {
  public int scaled_width;
  
  public int scaled_height;
  
  public boolean flag = true;
  
  public Integer lambda$render$0(Module paramModule) {
    (give up)null;
    return Integer.valueOf(get((paramModule.array_detail() == null) ? paramModule.get_tag() : (paramModule.get_tag() + Leux.g + " [" + Leux.r + paramModule.array_detail() + Leux.g + "]" + Leux.r), "width"));
  }
  
  public void updateResolution() {
    (give up)null;
    this.scaled_width = this.mc.field_71443_c;
    this.scaled_height = this.mc.field_71440_d;
    byte b = 1;
    boolean bool = this.mc.func_152349_b();
    int i = this.mc.field_71474_y.field_74335_Z;
    i = 1000;
    while (b < i && this.scaled_width / (b + 1) >= 320 && this.scaled_height / (b + 1) >= 240)
      b++; 
    b % 2;
    true;
    double d1 = this.scaled_width / b;
    double d2 = this.scaled_height / b;
    this.scaled_width = MathHelper.func_76143_f(d1);
    this.scaled_height = MathHelper.func_76143_f(d2);
  }
  
  public ModulesArrayList() {
    super("Array List", "ArrayList", Float.intBitsToFloat(1.08809152E9F ^ 0x7F5AF599), 0, 0);
  }
  
  public void render() {
    (give up)null;
    updateResolution();
    int i = 2;
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int n = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    List list = (List)Leux.get_hack_manager().get_array_active_hacks().stream().sorted(Comparator.comparing(this::lambda$render$0)).collect(Collectors.toList());
    byte b = 0;
    if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R") || Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L"))
      list = Lists.reverse(list); 
    for (Module module : list) {
      this.flag = true;
      if (module.get_category().get_tag().equals("CLIENT"))
        continue; 
      for (String str : DrawnUtil.hidden_tags) {
        if (module.get_tag().equalsIgnoreCase(str)) {
          this.flag = false;
          break;
        } 
        if (!this.flag)
          break; 
      } 
      if (this.flag) {
        String str = (module.array_detail() == null) ? module.get_tag() : (module.get_tag() + Leux.g + " [" + Leux.r + module.array_detail() + Leux.g + "]" + Leux.r);
        if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Free")) {
          create_line(str, docking(2, str), i, j, k, m, n);
          i += get(str, "height") + 2;
          if (get(str, "width") > get_width())
            set_width(get(str, "width") + 2); 
          set_height(i);
          continue;
        } 
        if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R")) {
          FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), str, this.scaled_width - 2 - FontUtil.getStringWidth(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), str), 3 + b * 10, (new Draw.ClientColor(j, k, m, n)).hex());
          b++;
        } 
        if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L")) {
          FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), str, 2, 3 + b * 10, (new Draw.ClientColor(j, k, m, n)).hex());
          b++;
        } 
        if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom R")) {
          FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), str, this.scaled_width - 2 - FontUtil.getStringWidth(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), str), this.scaled_height - b * 10, (new Draw.ClientColor(j, k, m, n)).hex());
          b++;
        } 
        if (Leux.get_setting_manager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom L")) {
          FontUtil.drawStringWithShadow(Leux.get_setting_manager().get_setting_with_tag("Settings", "CustomFont").get_value(true), str, 2, this.scaled_height - b * 10, (new Draw.ClientColor(j, k, m, n)).hex());
          b++;
        } 
      } 
    } 
  }
}
