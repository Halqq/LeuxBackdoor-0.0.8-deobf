package me.obsidianbreaker.leux.client.guiscreen.hud;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.render.pinnables.Pinnable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PvpHud extends Pinnable {
  public void render() {
    (give up)null;
    int i = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    int j = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    int k = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    int m = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
    String str1 = get_totems();
    String str2 = trap_enabled();
    String str3 = aura_enabled();
    String str4 = surround_enabled();
    String str5 = holefill_enabled();
    String str6 = socks_enabled();
    String str7 = selftrap_enabled();
    String str8 = killaura_enabled();
    create_line(str1, docking(1, str1), 2, i, j, k, m);
    create_line(str3, docking(1, str3), 13, i, j, k, m);
    create_line(str2, docking(1, str2), 24, i, j, k, m);
    create_line(str4, docking(1, str4), 34, i, j, k, m);
    create_line(str5, docking(1, str5), 45, i, j, k, m);
    create_line(str6, docking(1, str6), 56, i, j, k, m);
    create_line(str7, docking(1, str7), 67, i, j, k, m);
    create_line(str8, docking(1, str8), 78, i, j, k, m);
    set_width(get(str4, "width") + 2);
    set_height(get(str4, "height") * 5);
  }
  
  public String surround_enabled() {
    (give up)null;
    return Leux.get_hack_manager().get_module_with_tag("Surround").is_active() ? "Surround" : "§cSurround";
  }
  
  public String killaura_enabled() {
    (give up)null;
    return Leux.get_hack_manager().get_module_with_tag("KillAura").is_active() ? "KillAura" : "§cKillAura";
  }
  
  public String holefill_enabled() {
    (give up)null;
    return Leux.get_hack_manager().get_module_with_tag("HoleFill").is_active() ? "HoleFill" : "§cHoleFill";
  }
  
  public String get_totems() {
    (give up)null;
    int i = offhand() + this.mc.field_71439_g.field_71071_by.field_70462_a.stream().filter(PvpHud::lambda$get_totems$0).mapToInt(ItemStack::func_190916_E).sum();
    return (i >= 1) ? ("Totems " + i) : ("§cTotems " + i);
  }
  
  public PvpHud() {
    super("PVP Hud", "pvphud", Float.intBitsToFloat(1.08994688E9F ^ 0x7F7744E2), 0, 0);
  }
  
  public String aura_enabled() {
    (give up)null;
    return (Leux.get_hack_manager().get_module_with_tag("CrystalAuraOld").is_active() || Leux.get_hack_manager().get_module_with_tag("CrystalAuraNew").is_active()) ? "Aura" : "§cAura";
  }
  
  public int offhand() {
    (give up)null;
    return (this.mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190929_cY) ? 1 : 0;
  }
  
  public String selftrap_enabled() {
    (give up)null;
    return Leux.get_hack_manager().get_module_with_tag("SelfTrap").is_active() ? "SelfTrap" : "§cSelfTrap";
  }
  
  public String socks_enabled() {
    (give up)null;
    return Leux.get_hack_manager().get_module_with_tag("AntiCityBoss").is_active() ? "Socks" : "§cSocks";
  }
  
  public static boolean lambda$get_totems$0(ItemStack paramItemStack) {
    (give up)null;
    return (paramItemStack.func_77973_b() == Items.field_190929_cY);
  }
  
  public String trap_enabled() {
    (give up)null;
    return Leux.get_hack_manager().get_module_with_tag("AutoTrap").is_active() ? "AutoTrap" : "§cAutoTrap";
  }
}
