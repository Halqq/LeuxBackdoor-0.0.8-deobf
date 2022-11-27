package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TotemCount extends Pinnable {
  public int totems = 0;
  
  public static boolean lambda$render$0(ItemStack paramItemStack) {
    (give up)null;
    return (paramItemStack.func_77973_b() == Items.field_190929_cY);
  }
  
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    if (this.mc.field_71439_g != null) {
      if (is_on_gui())
        create_rect(0, 0, get_width(), get_height(), 0, 0, 0, 50); 
      GlStateManager.func_179094_E();
      RenderHelper.func_74520_c();
      this.totems = this.mc.field_71439_g.field_71071_by.field_70462_a.stream().filter(TotemCount::lambda$render$0).mapToInt(ItemStack::func_190916_E).sum();
      int n = 0;
      byte b = 0;
      while (true) {
        45;
        ItemStack itemStack1 = this.mc.field_71439_g.field_71071_by.func_70301_a(b);
        ItemStack itemStack2 = this.mc.field_71439_g.func_184592_cb();
        if (itemStack2.func_77973_b() == Items.field_190929_cY) {
          n = itemStack2.func_77976_d();
        } else {
          n = 0;
        } 
        if (itemStack1.func_77973_b() == Items.field_190929_cY) {
          this.mc.func_175599_af().func_180450_b(itemStack1, get_x(), get_y());
          create_line(Integer.toString(this.totems + n), 18, 16 - get(Integer.toString(this.totems + n), "height"), i, j, k, m);
        } 
      } 
    } 
  }
  
  public TotemCount() {
    super("Totem Count", "TotemCount", Float.intBitsToFloat(1.08459738E9F ^ 0x7F25A486), 0, 0);
  }
}
