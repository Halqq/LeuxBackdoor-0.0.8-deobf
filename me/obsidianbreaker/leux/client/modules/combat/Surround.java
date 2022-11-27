package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Surround extends Module {
  public Setting hybrid = create("Hybrid", "SurroundHybrid", true);
  
  public Setting center = create("Center", "SurroundCenter", true);
  
  public Setting render = create("Render", "SurroundRender", true);
  
  public Vec3d center_block = Vec3d.field_186680_a;
  
  public Setting b = create("Blue", "SurroundBlue", 100, 0, 255);
  
  public Setting renderMode = create("Render Mode", "SurroundMode", "Both", combobox(new String[] { "Quads", "Lines", "Both" }));
  
  public Vec3d[] surround_targets = new Vec3d[] { new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, -1.0D, 0.0D), new Vec3d(0.0D, -1.0D, 1.0D), new Vec3d(-1.0D, -1.0D, 0.0D), new Vec3d(0.0D, -1.0D, -1.0D), new Vec3d(0.0D, -1.0D, 0.0D) };
  
  public Vec3d[] surround_targets_face = new Vec3d[] { 
      new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(-1.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, -1.0D), new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(-1.0D, 0.0D, 0.0D), new Vec3d(0.0D, 0.0D, -1.0D), new Vec3d(1.0D, -1.0D, 0.0D), new Vec3d(0.0D, -1.0D, 1.0D), 
      new Vec3d(-1.0D, -1.0D, 0.0D), new Vec3d(0.0D, -1.0D, -1.0D), new Vec3d(0.0D, -1.0D, 0.0D) };
  
  public Setting block_head = create("Block Face", "SurroundBlockFace", false);
  
  public Setting g = create("Green", "SurroundGreen", 100, 0, 255);
  
  public BlockPos targetPos;
  
  public Setting tick_timeout = create("Ticks til timeout", "SurroundTicks", 20, 10, 50);
  
  public Setting r = create("Red", "SurroundRed", 0, 0, 255);
  
  public Setting a = create("Alpha", "SurroundAlpha", 30, 0, 255);
  
  public int tick_runs = 0;
  
  public int offset_step = 0;
  
  public Setting rotate = create("Rotate", "SurroundRotate", false);
  
  public Setting triggerable = create("Toggle", "SurroundToggle", true);
  
  public Setting tick_for_place = create("Blocks per tick", "SurroundTickToPlace", 1, 1, 8);
  
  public Setting swing = create("Swing", "SurroundSwing", "Mainhand", combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
  
  public int y_level = 0;
  
  public void enable() {
    (give up)null;
    if (find_in_hotbar() == -1) {
      set_disable();
      return;
    } 
    if (mc.field_71439_g != null) {
      this.y_level = (int)Math.round(mc.field_71439_g.field_70163_u);
      this.center_block = get_center(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v);
      if (this.center.get_value(true)) {
        mc.field_71439_g.field_70159_w = 0.0D;
        mc.field_71439_g.field_70179_y = 0.0D;
      } 
    } 
  }
  
  public Surround() {
    super(Category.combat);
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
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g != null) {
      if (this.center_block != Vec3d.field_186680_a && this.center.get_value(true)) {
        double d1 = Math.abs(this.center_block.field_72450_a - mc.field_71439_g.field_70165_t);
        double d2 = Math.abs(this.center_block.field_72449_c - mc.field_71439_g.field_70161_v);
        if (d1 <= 0.1D && d2 <= 0.1D) {
          this.center_block = Vec3d.field_186680_a;
        } else {
          double d3 = this.center_block.field_72450_a - mc.field_71439_g.field_70165_t;
          double d4 = this.center_block.field_72449_c - mc.field_71439_g.field_70161_v;
          mc.field_71439_g.field_70159_w = d3 / 2.0D;
          mc.field_71439_g.field_70179_y = d4 / 2.0D;
        } 
      } 
      if ((int)Math.round(mc.field_71439_g.field_70163_u) != this.y_level && this.hybrid.get_value(true)) {
        set_disable();
        return;
      } 
      if (!this.triggerable.get_value(true) && this.tick_runs >= this.tick_timeout.get_value(1)) {
        this.tick_runs = 0;
        set_disable();
        return;
      } 
      byte b = 0;
      while (b < this.tick_for_place.get_value(1)) {
        if (this.offset_step >= (this.block_head.get_value(true) ? this.surround_targets_face.length : this.surround_targets.length)) {
          this.offset_step = 0;
          break;
        } 
        BlockPos blockPos = new BlockPos(this.block_head.get_value(true) ? this.surround_targets_face[this.offset_step] : this.surround_targets[this.offset_step]);
        this.targetPos = (new BlockPos(mc.field_71439_g.func_174791_d())).func_177982_a(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
        boolean bool = true;
        if (!mc.field_71441_e.func_180495_p(this.targetPos).func_185904_a().func_76222_j())
          bool = false; 
        for (Entity entity : mc.field_71441_e.func_72839_b(null, new AxisAlignedBB(this.targetPos))) {
          if (entity instanceof net.minecraft.entity.item.EntityItem || entity instanceof net.minecraft.entity.item.EntityXPOrb)
            continue; 
          bool = false;
        } 
        if (BlockUtil.placeBlock(this.targetPos, find_in_hotbar(), this.rotate.get_value(true), this.rotate.get_value(true), this.swing))
          b++; 
        this.offset_step++;
      } 
      this.tick_runs++;
    } 
  }
  
  public Vec3d get_center(double paramDouble1, double paramDouble2, double paramDouble3) {
    (give up)null;
    double d1 = Math.floor(paramDouble1) + 0.5D;
    double d2 = Math.floor(paramDouble2);
    double d3 = Math.floor(paramDouble3) + 0.5D;
    return new Vec3d(d1, d2, d3);
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.render.get_value(true) && this.targetPos != null)
      if (this.renderMode.in("Quads")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.targetPos.func_177958_n(), this.targetPos.func_177956_o(), this.targetPos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Lines")) {
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.targetPos.func_177958_n(), this.targetPos.func_177956_o(), this.targetPos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Both")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.targetPos.func_177958_n(), this.targetPos.func_177956_o(), this.targetPos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.targetPos.func_177958_n(), this.targetPos.func_177956_o(), this.targetPos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      }  
  }
}
