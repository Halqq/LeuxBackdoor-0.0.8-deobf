package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class Offhand extends Module {
  public boolean switching = false;
  
  public Setting totem_ca_disabled = create("TotemWhenCAOff", "TotemWhenCAOff", true);
  
  public Setting totem_elytra = create("Totem Elytra", "OffhandTotemElytra", false);
  
  public Setting totem_switch = create("Totem HP", "OffhandTotemHP", 15, 0, 36);
  
  public int last_slot;
  
  public Setting fall_dist = create("Fall Distance", "FallDistance", 20.0D, 0.0D, 50.0D);
  
  public Setting sword_gap = create("Sword Gap", "OffhandSwordGap", false);
  
  public Setting switch_mode = create("Offhand", "OffhandOffhand", "Crystal", combobox(new String[] { "Totem", "Crystal", "Gapple" }));
  
  public Setting delay = create("Delay", "OffhandDelay", false);
  
  public Setting right_click_gap = create("Right Click Gap", "OffhandRightGap", false);
  
  public void swap_items(int paramInt1, int paramInt2) {
    (give up)null;
    if (paramInt1 == -1)
      return; 
    mc.field_71442_b.func_187098_a(0, paramInt1, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
    mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
    mc.field_71442_b.func_187098_a(0, paramInt1, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
    if (paramInt2 == 1) {
      mc.field_71442_b.func_187098_a(0, paramInt1, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
      this.switching = true;
      this.last_slot = paramInt1;
    } 
    if (paramInt2 == 2) {
      mc.field_71442_b.func_187098_a(0, 45, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
      mc.field_71442_b.func_187098_a(0, paramInt1, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
      this.switching = false;
    } 
    mc.field_71442_b.func_78765_e();
  }
  
  public String array_detail() {
    (give up)null;
    return this.switch_mode.get_current_value() + ", " + this.totem_switch.get_value(1);
  }
  
  public Offhand() {
    super(Category.combat);
  }
  
  public int get_item_slot(Item paramItem) {
    (give up)null;
    if (paramItem == mc.field_71439_g.func_184592_cb().func_77973_b())
      return -1; 
    for (byte b = 36;; b--) {
      Item item = mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b();
      if (item == paramItem) {
        9;
        return b;
      } 
    } 
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71462_r == null || mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiInventory) {
      if (this.switching) {
        swap_items(this.last_slot, 2);
        return;
      } 
      float f = mc.field_71439_g.func_110143_aJ() + mc.field_71439_g.func_110139_bj();
      if (f > this.totem_switch.get_value(1) && mc.field_71439_g.field_70143_R <= this.fall_dist.get_value(1.0D)) {
        if (this.sword_gap.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword && Leux.get_module_manager().get_module_with_tag("CrystalAuraNew").is_disabled() && Leux.get_module_manager().get_module_with_tag("CrystalAuraOld").is_disabled()) {
          swap_items(get_item_slot(Items.field_151153_ao), this.delay.get_value(true) ? 1 : 0);
          return;
        } 
        if (this.right_click_gap.get_value(true) && mc.field_71474_y.field_74313_G.func_151470_d() && !(mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemExpBottle) && !(mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemBlock) && !(mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemAppleGold) && !(mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemChorusFruit) && !(mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemBow)) {
          swap_items(get_item_slot(Items.field_151153_ao), this.delay.get_value(true) ? 1 : 0);
          return;
        } 
        if (this.totem_elytra.get_value(true) && mc.field_71439_g.func_184613_cA()) {
          swap_items(get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0);
          return;
        } 
        if (this.switch_mode.in("Crystal"))
          if (this.totem_ca_disabled.get_value(true)) {
            if (Leux.get_module_manager().get_module_with_tag("CrystalAuraNew").is_active() || Leux.get_module_manager().get_module_with_tag("CrystalAuraOld").is_active()) {
              swap_items(get_item_slot(Items.field_185158_cP), 0);
              return;
            } 
          } else {
            swap_items(get_item_slot(Items.field_185158_cP), 0);
            return;
          }  
        if (this.switch_mode.in("Totem")) {
          swap_items(get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0);
          return;
        } 
        if (this.switch_mode.in("Gapple")) {
          swap_items(get_item_slot(Items.field_151153_ao), this.delay.get_value(true) ? 1 : 0);
          return;
        } 
        swap_items(get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0);
        return;
      } 
      swap_items(get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0);
      if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190931_a)
        swap_items(get_item_slot(Items.field_190929_cY), this.delay.get_value(true) ? 1 : 0); 
    } 
  }
}
