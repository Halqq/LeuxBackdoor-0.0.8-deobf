package me.obsidianbreaker.leux.client.modules.player;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

public class FastUse extends Module {
  public Setting fast_place = create("Place", "FastPlace", false);
  
  public Setting bow = create("Bow", "FastBow", true);
  
  public Setting everything = create("Everything", "FastEverything", false);
  
  public Setting crystal = create("Crystal", "FastCrystal", false);
  
  public Setting fast_break = create("Break", "FastBreak", false);
  
  public Setting exp = create("EXP", "FastExp", true);
  
  public FastUse() {
    super(Category.player);
  }
  
  public void update() {
    (give up)null;
    Item item1 = mc.field_71439_g.func_184614_ca().func_77973_b();
    Item item2 = mc.field_71439_g.func_184592_cb().func_77973_b();
    boolean bool1 = item1 instanceof net.minecraft.item.ItemExpBottle;
    boolean bool2 = item2 instanceof net.minecraft.item.ItemExpBottle;
    boolean bool3 = item1 instanceof net.minecraft.item.ItemEndCrystal;
    boolean bool4 = item2 instanceof net.minecraft.item.ItemEndCrystal;
    boolean bool5 = item1 instanceof net.minecraft.item.ItemBow;
    boolean bool6 = item2 instanceof net.minecraft.item.ItemBow;
    if (this.everything.get_value(true))
      mc.field_71467_ac = 0; 
    if (bool1 | bool2 && this.exp.get_value(true))
      mc.field_71467_ac = 0; 
    if (bool5 | bool6 && this.bow.get_value(true) && mc.field_71439_g.func_184587_cr() && mc.field_71439_g.func_184612_cw() >= 3) {
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, mc.field_71439_g.func_174811_aO()));
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayerTryUseItem(mc.field_71439_g.func_184600_cs()));
      mc.field_71439_g.func_184597_cx();
    } 
    if (bool3 | bool4 && this.crystal.get_value(true))
      mc.field_71467_ac = 0; 
    if (!(bool1 | bool2 | bool3 | bool4) && this.fast_place.get_value(true))
      mc.field_71467_ac = 0; 
    if (this.fast_break.get_value(true))
      mc.field_71442_b.field_78781_i = 0; 
  }
}
