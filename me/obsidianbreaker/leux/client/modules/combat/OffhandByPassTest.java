package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class OffhandByPassTest extends Module {
  public Setting swordGap = create("Sword Gap", "EAOffhandSwordGap", false);
  
  public Setting mode = create("Mode", "EAOffhandMode", "Crystal", combobox(new String[] { "Crystal", "Gapple" }));
  
  public Setting minHealth = create("Min Health", "EAOffhandMinHealth", 16.5D, 0.1D, 36.0D);
  
  public Setting searchHotbar = create("Search Hotbar", "EAOffhandSearchHotbar", false);
  
  public void switchToItem(Item paramItem) {
    (give up)null;
    if (mc.field_71439_g.func_184592_cb().func_77973_b() != paramItem) {
      byte b = -1;
      byte b1 = 44;
      while (true) {
        this.searchHotbar.get_value(true) ? 0 : 8;
        if (mc.field_71439_g.field_71071_by.func_70301_a(b1).func_77973_b() == paramItem)
          b = b1; 
      } 
    } 
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g != null && mc.field_71441_e != null) {
      Item item = this.mode.in("Crystal") ? Items.field_185158_cP : Items.field_151153_ao;
      if (this.swordGap.get_value(true) && mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword)
        item = Items.field_151153_ao; 
      if ((this.mode.in("Crystal") && mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_151153_ao) || mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP || (mc.field_71439_g.func_110143_aJ() + mc.field_71439_g.func_110139_bj()) < this.minHealth.get_value(1.0D))
        item = Items.field_190929_cY; 
      switchToItem(item);
    } 
  }
  
  public OffhandByPassTest() {
    super(Category.combat);
  }
}
