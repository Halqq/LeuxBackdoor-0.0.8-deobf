package me.obsidianbreaker.leux.client.util;

import give up;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DamageUtil {
  public static int getItemDamage(ItemStack paramItemStack) {
    (give up)null;
    return paramItemStack.func_77958_k() - paramItemStack.func_77952_i();
  }
  
  public static float getDamageInPercent(ItemStack paramItemStack) {
    (give up)null;
    return getItemDamage(paramItemStack) / paramItemStack.func_77958_k() * Float.intBitsToFloat(1.03649894E9F ^ 0x7F0FB817);
  }
  
  public static int getRoundedDamage(ItemStack paramItemStack) {
    (give up)null;
    return (int)getDamageInPercent(paramItemStack);
  }
  
  public static boolean hasDurability(ItemStack paramItemStack) {
    (give up)null;
    Item item = paramItemStack.func_77973_b();
    return (item instanceof net.minecraft.item.ItemArmor || item instanceof net.minecraft.item.ItemSword || item instanceof net.minecraft.item.ItemTool || item instanceof net.minecraft.item.ItemShield);
  }
}
