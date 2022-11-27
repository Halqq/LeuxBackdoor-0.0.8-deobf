package me.obsidianbreaker.leux.client.modules.player;

import give up;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class AutoArmor extends Module {
  public Setting player_range = create("Player Range", "AAPlayerRange", 8, 0, 20);
  
  public Setting delay = create("Delay", "AADelay", 2, 0, 5);
  
  public int delay_count;
  
  public Setting crystal_range = create("Crystal Range", "AACrystalRange", 13, 0, 20);
  
  public Setting put_back = create("Equip Armour", "AAEquipArmour", true);
  
  public Setting smart_mode = create("Smart Mode", "AASmartMode", true);
  
  public Setting boot_percent = create("Boot Percent", "AATBootPercent", 80, 0, 100);
  
  public Setting chest_percent = create("Chest Percent", "AATChestPercent", 80, 0, 100);
  
  public static boolean lambda$is_player_in_range$0(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return !FriendUtil.isFriend(paramEntityPlayer.func_70005_c_());
  }
  
  public boolean is_space() {
    (give up)null;
    for (Map.Entry entry : get_inv().entrySet()) {
      ItemStack itemStack = (ItemStack)entry.getValue();
      if (itemStack.func_77973_b() != Items.field_190931_a)
        continue; 
      return true;
    } 
    return false;
  }
  
  public static Map get_inv_slots(int paramInt1, int paramInt2) {
    (give up)null;
    HashMap<Object, Object> hashMap = new HashMap<>();
    while (paramInt1 <= paramInt2) {
      hashMap.put(Integer.valueOf(paramInt1), mc.field_71439_g.field_71069_bz.func_75138_a().get(paramInt1));
      paramInt1++;
    } 
    return hashMap;
  }
  
  public static boolean lambda$is_crystal_in_range$1(Entity paramEntity) {
    (give up)null;
    return paramEntity instanceof net.minecraft.entity.item.EntityEnderCrystal;
  }
  
  public boolean is_player_in_range(int paramInt) {
    (give up)null;
    for (Entity entity : mc.field_71441_e.field_73010_i.stream().filter(AutoArmor::lambda$is_player_in_range$0).collect(Collectors.toList())) {
      if (entity == mc.field_71439_g || mc.field_71439_g.func_70032_d(entity) >= paramInt)
        continue; 
      return true;
    } 
    return false;
  }
  
  public AutoArmor() {
    super(Category.player);
  }
  
  public void take_off() {
    (give up)null;
    if (!is_space())
      return; 
    for (Map.Entry entry : get_armour().entrySet()) {
      ItemStack itemStack = (ItemStack)entry.getValue();
      if (!is_healed(itemStack))
        continue; 
      mc.field_71442_b.func_187098_a(0, ((Integer)entry.getKey()).intValue(), 0, ClickType.QUICK_MOVE, (EntityPlayer)mc.field_71439_g);
      return;
    } 
  }
  
  public boolean is_healed(ItemStack paramItemStack) {
    (give up)null;
    if (paramItemStack.func_77973_b() == Items.field_151175_af || paramItemStack.func_77973_b() == Items.field_151161_ac) {
      double d4 = paramItemStack.func_77958_k();
      double d5 = (paramItemStack.func_77958_k() - paramItemStack.func_77952_i());
      double d6 = d5 / d4 * 100.0D;
      return (d6 >= this.boot_percent.get_value(1));
    } 
    double d1 = paramItemStack.func_77958_k();
    double d2 = (paramItemStack.func_77958_k() - paramItemStack.func_77952_i());
    double d3 = d2 / d1 * 100.0D;
    return (d3 >= this.chest_percent.get_value(1));
  }
  
  public boolean is_crystal_in_range(int paramInt) {
    (give up)null;
    for (Entity entity : mc.field_71441_e.field_72996_f.stream().filter(AutoArmor::lambda$is_crystal_in_range$1).collect(Collectors.toList())) {
      if (mc.field_71439_g.func_70032_d(entity) >= paramInt)
        continue; 
      return true;
    } 
    return false;
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g.field_70173_aa % 2 == 0)
      return; 
    boolean bool = false;
    if (this.delay_count < this.delay.get_value(0)) {
      this.delay_count++;
      return;
    } 
    this.delay_count = 0;
    if (this.smart_mode.get_value(true) && !is_crystal_in_range(this.crystal_range.get_value(1)) && !is_player_in_range(this.player_range.get_value(1)))
      bool = true; 
    if (!this.put_back.get_value(true))
      return; 
    if (mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer && !(mc.field_71462_r instanceof net.minecraft.client.renderer.InventoryEffectRenderer))
      return; 
    int[] arrayOfInt1 = new int[4];
    int[] arrayOfInt2 = new int[4];
    byte b = 0;
    while (true) {
      4;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70440_f(b);
      if (itemStack.func_77973_b() instanceof ItemArmor)
        arrayOfInt2[b] = ((ItemArmor)itemStack.func_77973_b()).field_77879_b; 
      arrayOfInt1[b] = -1;
    } 
  }
  
  public void enable() {
    (give up)null;
    this.delay_count = 0;
  }
  
  public static Map get_inv() {
    (give up)null;
    return get_inv_slots(9, 44);
  }
  
  public static Map get_armour() {
    (give up)null;
    return get_inv_slots(5, 8);
  }
}
