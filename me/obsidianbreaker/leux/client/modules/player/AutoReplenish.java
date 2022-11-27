package me.obsidianbreaker.leux.client.modules.player;

import give up;
import java.util.HashMap;
import java.util.Map;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class AutoReplenish extends Module {
  public int delay_step = 0;
  
  public Setting threshold = create("Threshold", "AutoReplenishThreshold", 32, 1, 63);
  
  public Setting tickdelay = create("Delay", "AutoReplenishDelay", 2, 1, 10);
  
  public Setting mode = create("Mode", "AutoReplenishMode", "Xp", combobox(new String[] { "All", "Crystals", "Xp", "Both" }));
  
  public Map get_inv_slots(int paramInt1, int paramInt2) {
    (give up)null;
    HashMap<Object, Object> hashMap = new HashMap<>();
    while (paramInt1 <= paramInt2) {
      hashMap.put(Integer.valueOf(paramInt1), mc.field_71439_g.field_71069_bz.func_75138_a().get(paramInt1));
      paramInt1++;
    } 
    return hashMap;
  }
  
  public Map get_hotbar() {
    (give up)null;
    return get_inv_slots(36, 44);
  }
  
  public Map get_inventory() {
    (give up)null;
    return get_inv_slots(9, 35);
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer)
      return; 
    if (this.delay_step < this.tickdelay.get_value(1)) {
      this.delay_step++;
      return;
    } 
    this.delay_step = 0;
    Pair pair = findReplenishableHotbarSlot();
  }
  
  public AutoReplenish() {
    super(Category.player);
  }
  
  public int findCompatibleInventorySlot(ItemStack paramItemStack) {
    (give up)null;
    int i = -1;
    int j = 999;
    for (Map.Entry entry : get_inventory().entrySet()) {
      ItemStack itemStack = (ItemStack)entry.getValue();
      if (itemStack.field_190928_g || itemStack.func_77973_b() == Items.field_190931_a || !isCompatibleStacks(paramItemStack, itemStack))
        continue; 
      int k = ((ItemStack)mc.field_71439_g.field_71069_bz.func_75138_a().get(((Integer)entry.getKey()).intValue())).field_77994_a;
      if (j <= k)
        continue; 
      j = k;
      i = ((Integer)entry.getKey()).intValue();
    } 
    return i;
  }
  
  public boolean isCompatibleStacks(ItemStack paramItemStack1, ItemStack paramItemStack2) {
    (give up)null;
    if (!paramItemStack1.func_77973_b().equals(paramItemStack2.func_77973_b()))
      return false; 
    if (paramItemStack1.func_77973_b() instanceof ItemBlock && paramItemStack2.func_77973_b() instanceof ItemBlock) {
      Block block1 = ((ItemBlock)paramItemStack1.func_77973_b()).func_179223_d();
      Block block2 = ((ItemBlock)paramItemStack2.func_77973_b()).func_179223_d();
      if (!block1.field_149764_J.equals(block2.field_149764_J))
        return false; 
    } 
    return (paramItemStack1.func_82833_r().equals(paramItemStack2.func_82833_r()) && paramItemStack1.func_77952_i() == paramItemStack2.func_77952_i());
  }
  
  public Pair findReplenishableHotbarSlot() {
    (give up)null;
    Pair pair = null;
    for (Map.Entry entry : get_hotbar().entrySet()) {
      ItemStack itemStack = (ItemStack)entry.getValue();
      if (itemStack.field_190928_g || itemStack.func_77973_b() == Items.field_190931_a || !itemStack.func_77985_e() || itemStack.field_77994_a >= itemStack.func_77976_d() || itemStack.field_77994_a > this.threshold.get_value(1))
        continue; 
      int i = findCompatibleInventorySlot(itemStack);
      if (i == -1)
        continue; 
      pair = new Pair(Integer.valueOf(i), entry.getKey());
    } 
    return pair;
  }
}
