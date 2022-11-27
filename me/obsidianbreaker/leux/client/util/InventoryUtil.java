package me.obsidianbreaker.leux.client.util;

import give up;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;

public class InventoryUtil {
  public static Minecraft mc = Minecraft.func_71410_x();
  
  public static void switchToSlot(int paramInt) {
    (give up)null;
    mc.field_71439_g.field_71071_by.field_70461_c = paramInt;
  }
  
  public static void switchToSlotGhost(Item paramItem) {
    (give up)null;
    switchToSlotGhost(getHotbarItemSlot(paramItem));
  }
  
  public static void switchToSlot(Item paramItem) {
    (give up)null;
    mc.field_71439_g.field_71071_by.field_70461_c = getHotbarItemSlot(paramItem);
  }
  
  public static int getHotbarItemSlot(Item paramItem) {
    (give up)null;
    byte b = -1;
    byte b1 = 0;
    while (true) {
      9;
      if (mc.field_71439_g.field_71071_by.func_70301_a(b1).func_77973_b() != paramItem)
        continue; 
      b = b1;
      -1;
      return mc.field_71439_g.field_71071_by.field_70461_c;
    } 
  }
  
  public static void switchToSlotGhost(int paramInt) {
    (give up)null;
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketHeldItemChange(paramInt));
  }
  
  public static boolean isNull(ItemStack paramItemStack) {
    (give up)null;
    return (paramItemStack.func_77973_b() instanceof net.minecraft.item.ItemAir);
  }
  
  public static void switchToHotbarSlot(int paramInt, boolean paramBoolean) {
    (give up)null;
    if (mc.field_71439_g.field_71071_by.field_70461_c != paramInt);
  }
  
  public static boolean getHeldItem(Item paramItem) {
    (give up)null;
    return (mc.field_71439_g.func_184614_ca().func_77973_b().equals(paramItem) || mc.field_71439_g.func_184592_cb().func_77973_b().equals(paramItem));
  }
  
  public static int getHotbarSlot(Block paramBlock) {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      Item item = mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b();
      if (item instanceof ItemBlock && ((ItemBlock)item).func_179223_d().equals(paramBlock))
        return b; 
    } 
  }
  
  public static int getHotbarSlot(Item paramItem) {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      Item item = mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b();
      if (paramItem.equals(item))
        return b; 
    } 
  }
}
