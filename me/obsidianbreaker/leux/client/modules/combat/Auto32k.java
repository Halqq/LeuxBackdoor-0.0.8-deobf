package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.BlockUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

public class Auto32k extends Module {
  public int shulker_slot;
  
  public boolean setup;
  
  public boolean place_redstone;
  
  public Setting delay = create("Delay", "AutotkDelay", 25, 0, 50);
  
  public int redstone_slot;
  
  public int[] rot;
  
  public Setting debug = create("Debug", "AutotkDebug", false);
  
  public Setting rotate = create("Rotate", "Autotkrotate", false);
  
  public Setting autooff = create("Auto Disable", "AutoDisable", false);
  
  public BlockPos pos;
  
  public int hopper_slot;
  
  public boolean dispenser_done;
  
  public Setting swing = create("Swing", "AutotkSwing", "Offhand", combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
  
  public int ticks_past;
  
  public Setting place_mode = create("Place Mode", "AutotkPlaceMode", "Auto", combobox(new String[] { "Auto", "Looking", "Hopper" }));
  
  public void enable() {
    (give up)null;
    this.ticks_past = 0;
    this.setup = false;
    this.dispenser_done = false;
    this.place_redstone = false;
    this.hopper_slot = -1;
    byte b1 = -1;
    this.redstone_slot = -1;
    this.shulker_slot = -1;
    byte b2 = -1;
    byte b = 0;
    while (true) {
      9;
      Item item = mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b();
      if (item == Item.func_150898_a((Block)Blocks.field_150438_bZ)) {
        this.hopper_slot = b;
      } else if (item == Item.func_150898_a(Blocks.field_150367_z)) {
        b1 = b;
      } else if (item == Item.func_150898_a(Blocks.field_150451_bX)) {
        this.redstone_slot = b;
      } else if (item instanceof net.minecraft.item.ItemShulkerBox) {
        this.shulker_slot = b;
      } else if (item instanceof net.minecraft.item.ItemBlock) {
        b2 = b;
      } 
    } 
  }
  
  public Auto32k() {
    super(Category.combat);
  }
  
  public void update() {
    (give up)null;
    if (this.autooff.get_value(true) && this.ticks_past > 50 && !(mc.field_71462_r instanceof GuiHopper)) {
      MessageUtil.send_client_message("Inactive too long, disabling");
      set_disable();
      return;
    } 
    if (this.setup && this.ticks_past > this.delay.get_value(1)) {
      if (!this.dispenser_done) {
        mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71070_bA.field_75152_c, 36 + this.shulker_slot, 0, ClickType.QUICK_MOVE, (EntityPlayer)mc.field_71439_g);
        this.dispenser_done = true;
        if (this.debug.get_value(true))
          MessageUtil.send_client_message("Sent item"); 
      } 
      if (!this.place_redstone) {
        BlockUtil.placeBlock(this.pos.func_177982_a(0, 2, 0), this.redstone_slot, this.rotate.get_value(true), false, this.swing);
        if (this.debug.get_value(true))
          MessageUtil.send_client_message("Placed redstone"); 
        this.place_redstone = true;
        return;
      } 
      if (!this.place_mode.in("Hopper") && mc.field_71441_e.func_180495_p(this.pos.func_177982_a(this.rot[0], 1, this.rot[1])).func_177230_c() instanceof net.minecraft.block.BlockShulkerBox && mc.field_71441_e.func_180495_p(this.pos.func_177982_a(this.rot[0], 0, this.rot[1])).func_177230_c() != Blocks.field_150438_bZ && this.place_redstone && this.dispenser_done && !(mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiInventory)) {
        BlockUtil.placeBlock(this.pos.func_177982_a(this.rot[0], 0, this.rot[1]), this.hopper_slot, this.rotate.get_value(true), false, this.swing);
        BlockUtil.openBlock(this.pos.func_177982_a(this.rot[0], 0, this.rot[1]));
        if (this.debug.get_value(true))
          MessageUtil.send_client_message("In the hopper"); 
      } 
      if (mc.field_71462_r instanceof GuiHopper) {
        GuiHopper guiHopper = (GuiHopper)mc.field_71462_r;
        byte b = 32;
        while (true) {
          40;
          if (EnchantmentHelper.func_77506_a(Enchantments.field_185302_k, guiHopper.field_147002_h.func_75139_a(b).func_75211_c()) > 5) {
            mc.field_71439_g.field_71071_by.field_70461_c = b - 32;
            if (!(((Slot)guiHopper.field_147002_h.field_75151_b.get(0)).func_75211_c().func_77973_b() instanceof net.minecraft.item.ItemAir)) {
              boolean bool = true;
              if ((((GuiContainer)mc.field_71462_r).field_147002_h.func_75139_a(0).func_75211_c()).field_190928_g)
                bool = false; 
              if (!(((GuiContainer)mc.field_71462_r).field_147002_h.func_75139_a(this.shulker_slot + 32).func_75211_c()).field_190928_g)
                bool = false; 
              mc.field_71442_b.func_187098_a(((GuiContainer)mc.field_71462_r).field_147002_h.field_75152_c, 0, this.shulker_slot, ClickType.SWAP, (EntityPlayer)mc.field_71439_g);
              disable();
            } 
            break;
          } 
        } 
      } 
    } 
    this.ticks_past++;
  }
}
