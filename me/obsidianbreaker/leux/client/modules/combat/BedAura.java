package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.stream.Collectors;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.BlockUtil;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.math.BlockPos;

public class BedAura extends Module {
  public Setting range = create("Range", "BedAuraRange", 5, 0, 6);
  
  public BedAura$spoof_face spoof_looking;
  
  public Setting delay = create("Delay", "BedAuraDelay", 6, 0, 20);
  
  public Setting hard = create("Hard Rotate", "BedAuraRotate", false);
  
  public BlockPos render_pos;
  
  public Setting swing = create("Swing", "BedAuraSwing", "Mainhand", combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
  
  public int counter;
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.render_pos == null)
      return; 
    RenderHelp.prepare("lines");
    RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.render_pos.func_177958_n(), this.render_pos.func_177956_o(), this.render_pos.func_177952_p(), 1.0F, 0.2F, 1.0F, 255, 20, 20, 180, "all");
    RenderHelp.release();
  }
  
  public BedAura() {
    super(Category.combat);
  }
  
  public void break_bed() {
    (give up)null;
    for (BlockPos blockPos : BlockInteractHelper.getSphere(get_pos_floor((EntityPlayer)mc.field_71439_g), this.range.get_value(1), this.range.get_value(1), false, true, 0).stream().filter(BedAura::is_bed).collect(Collectors.toList())) {
      if (mc.field_71439_g.func_70093_af())
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING)); 
      BlockUtil.openBlock(blockPos);
    } 
  }
  
  public boolean is_space() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      if (mc.field_71439_g.field_71069_bz.func_75139_a(b).func_75216_d())
        return true; 
    } 
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (this.counter > this.delay.get_value(1)) {
      this.counter = 0;
      place_bed();
      break_bed();
      refill_bed();
    } 
    this.counter++;
  }
  
  public void disable() {
    (give up)null;
    this.render_pos = null;
  }
  
  public static boolean lambda$place_bed$0(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return !FriendUtil.isFriend(paramEntityPlayer.func_70005_c_());
  }
  
  public static BlockPos get_pos_floor(EntityPlayer paramEntityPlayer) {
    (give up)null;
    return new BlockPos(Math.floor(paramEntityPlayer.field_70165_t), Math.floor(paramEntityPlayer.field_70163_u), Math.floor(paramEntityPlayer.field_70161_v));
  }
  
  public void refill_bed() {
    (give up)null;
    if (!(mc.field_71462_r instanceof net.minecraft.client.gui.inventory.GuiContainer) && is_space()) {
      byte b = 9;
      while (true) {
        35;
        if (mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b() == Items.field_151104_aV) {
          mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, b, 0, ClickType.QUICK_MOVE, (EntityPlayer)mc.field_71439_g);
          break;
        } 
      } 
    } 
  }
  
  public void place_bed() {
    (give up)null;
    if (find_bed() == -1)
      return; 
    int i = find_bed();
    BlockPos blockPos = null;
    EntityPlayer entityPlayer = null;
    float f = this.range.get_value(1);
    for (EntityPlayer entityPlayer1 : mc.field_71441_e.field_73010_i.stream().filter(BedAura::lambda$place_bed$0).collect(Collectors.toList())) {
      if (entityPlayer1 == mc.field_71439_g || f < mc.field_71439_g.func_70032_d((Entity)entityPlayer1))
        continue; 
      boolean bool = true;
      BlockPos blockPos1 = get_pos_floor(entityPlayer1).func_177977_b();
      BlockPos blockPos2 = check_side_block(blockPos1);
      blockPos = blockPos2.func_177984_a();
      entityPlayer = entityPlayer1;
      f = mc.field_71439_g.func_70032_d((Entity)entityPlayer1);
      bool = false;
      BlockPos blockPos3 = get_pos_floor(entityPlayer1);
      BlockPos blockPos4 = check_side_block(blockPos3);
      blockPos = blockPos4.func_177984_a();
      entityPlayer = entityPlayer1;
      f = mc.field_71439_g.func_70032_d((Entity)entityPlayer1);
    } 
  }
  
  public static boolean is_bed(BlockPos paramBlockPos) {
    (give up)null;
    Block block = mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c();
    return (block == Blocks.field_150324_C);
  }
  
  public BlockPos check_side_block(BlockPos paramBlockPos) {
    (give up)null;
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177974_f()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(paramBlockPos.func_177974_f().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
      this.spoof_looking = BedAura$spoof_face.WEST;
      return paramBlockPos.func_177974_f();
    } 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177978_c()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(paramBlockPos.func_177978_c().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
      this.spoof_looking = BedAura$spoof_face.SOUTH;
      return paramBlockPos.func_177978_c();
    } 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177976_e()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(paramBlockPos.func_177976_e().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
      this.spoof_looking = BedAura$spoof_face.EAST;
      return paramBlockPos.func_177976_e();
    } 
    if (mc.field_71441_e.func_180495_p(paramBlockPos.func_177968_d()).func_177230_c() != Blocks.field_150350_a && mc.field_71441_e.func_180495_p(paramBlockPos.func_177968_d().func_177984_a()).func_177230_c() == Blocks.field_150350_a) {
      this.spoof_looking = BedAura$spoof_face.NORTH;
      return paramBlockPos.func_177968_d();
    } 
    return null;
  }
  
  public int find_bed() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      if (mc.field_71439_g.field_71071_by.func_70301_a(b).func_77973_b() == Items.field_151104_aV)
        return b; 
    } 
  }
  
  public void enable() {
    (give up)null;
    this.render_pos = null;
    this.counter = 0;
  }
}
