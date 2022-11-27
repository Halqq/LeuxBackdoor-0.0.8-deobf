package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.BlockInteractHelper;
import me.obsidianbreaker.leux.client.util.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class SelfTrap extends Module {
  public Setting g = create("Green", "SelfTrapGreen", 100, 0, 255);
  
  public Setting b = create("Blue", "SelfTrapBlue", 100, 0, 255);
  
  public Setting toggle = create("Toggle", "SelfTrapToggle", false);
  
  public BlockPos trap_pos;
  
  public Setting render = create("Render", "SelfTrapRender", true);
  
  public Setting a = create("Alpha", "SelfTrapAlpha", 30, 0, 255);
  
  public Setting renderMode = create("Render Mode", "SelfTrapMode", "Both", combobox(new String[] { "Quads", "Lines", "Both" }));
  
  public Setting rotate = create("Rotate", "SelfTrapRotate", false);
  
  public boolean sneak;
  
  public Setting r = create("Red", "SelfTrapRed", 0, 0, 255);
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.render.get_value(true) && this.trap_pos != null)
      if (this.renderMode.in("Quads")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.trap_pos.func_177958_n(), this.trap_pos.func_177956_o(), this.trap_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Lines")) {
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.trap_pos.func_177958_n(), this.trap_pos.func_177956_o(), this.trap_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } else if (this.renderMode.in("Both")) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), this.trap_pos.func_177958_n(), this.trap_pos.func_177956_o(), this.trap_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), this.trap_pos.func_177958_n(), this.trap_pos.func_177956_o(), this.trap_pos.func_177952_p(), 1.0F, 1.0F, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      }  
  }
  
  public void update() {
    (give up)null;
    Vec3d vec3d = MathUtil.interpolateEntity((Entity)mc.field_71439_g, mc.func_184121_ak());
    this.trap_pos = new BlockPos(vec3d.field_72450_a, vec3d.field_72448_b + 2.0D, vec3d.field_72449_c);
    if (is_trapped() && !this.toggle.get_value(true)) {
      set_disable();
    } else {
      int i = mc.field_71439_g.field_71071_by.field_70461_c;
      int j = find_in_hotbar();
      if (j == -1) {
        toggle();
      } else {
        mc.field_71439_g.field_71071_by.field_70461_c = j;
        mc.field_71442_b.func_78765_e();
        BlockInteractHelper.ValidResult validResult = BlockInteractHelper.valid(this.trap_pos);
        if (validResult == BlockInteractHelper.ValidResult.AlreadyBlockThere && !mc.field_71441_e.func_180495_p(this.trap_pos).func_185904_a().func_76222_j()) {
          mc.field_71439_g.field_71071_by.field_70461_c = i;
        } else if (validResult == BlockInteractHelper.ValidResult.NoNeighbors) {
          BlockPos[] arrayOfBlockPos = { this.trap_pos.func_177978_c(), this.trap_pos.func_177968_d(), this.trap_pos.func_177974_f(), this.trap_pos.func_177976_e(), this.trap_pos.func_177984_a(), this.trap_pos.func_177977_b().func_177976_e() };
          int k = arrayOfBlockPos.length;
          byte b = 0;
          while (true) {
            if (b < k) {
              BlockPos blockPos = arrayOfBlockPos[b];
              BlockInteractHelper.ValidResult validResult1 = BlockInteractHelper.valid(blockPos);
              if (validResult1 == BlockInteractHelper.ValidResult.NoNeighbors || validResult1 == BlockInteractHelper.ValidResult.NoEntityCollision || !place_blocks(blockPos)) {
                b++;
                continue;
              } 
            } else {
              mc.field_71439_g.field_71071_by.field_70461_c = i;
              break;
            } 
            mc.field_71439_g.field_71071_by.field_70461_c = i;
            return;
          } 
        } else {
          place_blocks(this.trap_pos);
          mc.field_71439_g.field_71071_by.field_70461_c = i;
        } 
      } 
    } 
  }
  
  public SelfTrap() {
    super(Category.combat);
  }
  
  public boolean place_blocks(BlockPos paramBlockPos) {
    (give up)null;
    set_disable();
    return false;
  }
  
  public void disable() {
    (give up)null;
    if (this.sneak) {
      this.sneak = false;
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.STOP_SNEAKING));
    } 
  }
  
  public boolean is_trapped() {
    (give up)null;
    if (this.trap_pos == null)
      return false; 
    IBlockState iBlockState = mc.field_71441_e.func_180495_p(this.trap_pos);
    return (iBlockState.func_177230_c() != Blocks.field_150350_a && iBlockState.func_177230_c() != Blocks.field_150355_j && iBlockState.func_177230_c() != Blocks.field_150353_l);
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
}
