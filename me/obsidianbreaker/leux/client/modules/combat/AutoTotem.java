package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class AutoTotem extends Module {
  public int last_slot;
  
  public Setting delay = create("Delay", "TotemDelay", false);
  
  public boolean switching = false;
  
  public void update() {
    (give up)null;
    if (mc.field_71462_r == null || mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiInventory) {
      if (this.switching) {
        swap_items(this.last_slot, 2);
        return;
      } 
      if (mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_190931_a)
        swap_items(get_item_slot(), this.delay.get_value(true) ? 1 : 0); 
    } 
  }
  
  public AutoTotem() {
    super(Category.combat);
  }
  
  public int get_item_slot() {
    (give up)null;
    if (Items.field_190929_cY == mc.field_71439_g.func_184592_cb().func_77973_b())
      return -1; 
    for (byte b = 36;; b--) {
      Item item = mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b();
      if (item == Items.field_190929_cY) {
        9;
        return b;
      } 
    } 
  }
  
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
}
