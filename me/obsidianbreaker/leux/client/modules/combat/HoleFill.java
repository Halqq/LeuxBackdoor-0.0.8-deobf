package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class HoleFill extends Module {
  public Setting b = create("Blue", "HoleFillBlue", 100, 0, 255);
  
  public Setting a = create("Alpha", "HoleFillAlpha", 30, 0, 255);
  
  public BlockPos pos_to_fill;
  
  public Setting hole_range = create("Range", "HoleFillRange", 4, 1, 6);
  
  public Setting hole_toggle = create("Toggle", "HoleFillToggle", false);
  
  public Setting render = create("Render", "HoleFillRender", true);
  
  public ArrayList holes = new ArrayList();
  
  public Setting hole_rotate = create("Rotate", "HoleFillRotate", false);
  
  public Setting r = create("Red", "HoleFillRed", 0, 0, 255);
  
  public Setting g = create("Green", "HoleFillGreen", 100, 0, 255);
  
  public Setting renderMode = create("Render Mode", "HoleFillMode", "Both", combobox(new String[] { "Quads", "Lines", "Both" }));
  
  public HoleFill() {
    super(Category.combat);
  }
  
  public void enable() {
    (give up)null;
    find_new_holes();
  }
  
  public void update() {
    (give up)null;
    if (find_in_hotbar() == -1) {
      disable();
    } else {
      if (this.holes.isEmpty()) {
        if (!this.hole_toggle.get_value(true)) {
          set_disable();
          return;
        } 
        find_new_holes();
      } 
      this.pos_to_fill = null;
      for (Object object : new ArrayList(this.holes))
        BlockPos blockPos = (BlockPos)object; 
      int i = find_in_hotbar();
      if (find_in_hotbar() == -1) {
        disable();
      } else if (this.pos_to_fill != null) {
        int j = mc.field_71439_g.field_71071_by.field_70461_c;
        mc.field_71439_g.field_71071_by.field_70461_c = i;
        mc.field_71442_b.func_78765_e();
        if (place_blocks(this.pos_to_fill))
          this.holes.remove(this.pos_to_fill); 
        mc.field_71439_g.field_71071_by.field_70461_c = j;
      } 
    } 
  }
  
  public boolean place_blocks(BlockPos paramBlockPos) {
    (give up)null;
    if (!mc.field_71441_e.func_180495_p(paramBlockPos).func_185904_a().func_76222_j())
      return false; 
    if (!BlockInteractHelper.checkForNeighbours(paramBlockPos))
      return false; 
    EnumFacing[] arrayOfEnumFacing = EnumFacing.values();
    int i = arrayOfEnumFacing.length;
    byte b = 0;
    while (b < i) {
      EnumFacing enumFacing1 = arrayOfEnumFacing[b];
      BlockPos blockPos = paramBlockPos.func_177972_a(enumFacing1);
      EnumFacing enumFacing2 = enumFacing1.func_176734_d();
      if (!BlockInteractHelper.canBeClicked(blockPos)) {
        b++;
        continue;
      } 
      if (BlockInteractHelper.blackList.contains(mc.field_71441_e.func_180495_p(blockPos).func_177230_c()))
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_SNEAKING)); 
      Vec3d vec3d = (new Vec3d((Vec3i)blockPos)).func_72441_c(0.5D, 0.5D, 0.5D).func_178787_e((new Vec3d(enumFacing2.func_176730_m())).func_186678_a(0.5D));
      if (this.hole_rotate.get_value(true))
        BlockInteractHelper.faceVectorPacketInstant(vec3d); 
      mc.field_71442_b.func_187099_a(mc.field_71439_g, mc.field_71441_e, blockPos, enumFacing2, vec3d, EnumHand.MAIN_HAND);
      mc.field_71439_g.func_184609_a(EnumHand.MAIN_HAND);
      return true;
    } 
    return false;
  }
  
  public int find_in_hotbar() {
    (give up)null;
    byte b = 0;
    while (true) {
      9;
      ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
      if (itemStack != ItemStack.field_190927_a && itemStack.func_77973_b() instanceof ItemBlock) {
        Block block = ((ItemBlock)itemStack.func_77973_b()).func_179223_d();
        if (block instanceof net.minecraft.block.BlockEnderChest)
          return b; 
        if (block instanceof net.minecraft.block.BlockObsidian)
          return b; 
      } 
    } 
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.render.get_value(true) && this.pos_to_fill != null)
      if (this.renderMode.in("Quads")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.pos_to_fill.func_177958_n(), this.pos_to_fill.func_177956_o(), this.pos_to_fill.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Lines")) {
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.pos_to_fill.func_177958_n(), this.pos_to_fill.func_177956_o(), this.pos_to_fill.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Both")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.pos_to_fill.func_177958_n(), this.pos_to_fill.func_177956_o(), this.pos_to_fill.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.pos_to_fill.func_177958_n(), this.pos_to_fill.func_177956_o(), this.pos_to_fill.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      }  
  }
  
  public void find_new_holes() {
    (give up)null;
    this.holes.clear();
    for (BlockPos blockPos : BlockInteractHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), this.hole_range.get_value(1), this.hole_range.get_value(1), false, true, 0)) {
      if (!mc.field_71441_e.func_180495_p(blockPos).func_177230_c().equals(Blocks.field_150350_a) || !mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 1, 0)).func_177230_c().equals(Blocks.field_150350_a) || !mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150350_a))
        continue; 
      boolean bool = true;
      BlockPos[] arrayOfBlockPos = { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
      int i = arrayOfBlockPos.length;
      byte b = 0;
      while (b < i) {
        BlockPos blockPos1 = arrayOfBlockPos[b];
        Block block = mc.field_71441_e.func_180495_p(blockPos.func_177971_a((Vec3i)blockPos1)).func_177230_c();
        if (block == Blocks.field_150357_h || block == Blocks.field_150343_Z || block == Blocks.field_150477_bB || block == Blocks.field_150467_bQ) {
          b++;
          continue;
        } 
        bool = false;
      } 
      this.holes.add(blockPos);
    } 
  }
  
  public void disable() {
    (give up)null;
    this.holes.clear();
  }
}
