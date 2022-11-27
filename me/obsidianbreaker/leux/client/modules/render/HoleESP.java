package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class HoleESP extends Module {
  public int color_g_o;
  
  public boolean outline = false;
  
  public Map dual_hole_sides = new HashMap<>();
  
  public int safe_sides;
  
  public boolean glowOutline = false;
  
  public int color_g_b;
  
  public Setting line_a = create("Outline A", "HoleESPLineOutlineA", 60, 0, 255);
  
  public Setting bb = create("B", "HoleESPBb", 80, 0, 255);
  
  public Setting bo = create("B", "HoleESPBo", 20, 0, 255);
  
  public ArrayList dual_holes = new ArrayList();
  
  public Setting off_set = create("Height", "HoleESPOffSetSide", 1.0D, 0.0D, 1.0D);
  
  public int color_r_b;
  
  public Setting ro = create("R", "HoleESPRo", 230, 0, 255);
  
  public Setting bedrock_enable = create("Bedrock Holes", "HoleESPBedrockHoles", true);
  
  public Setting obsidian_enable = create("Obsidian Holes", "HoleESPObsidianHoles", true);
  
  public Setting rb = create("R", "HoleESPRb", 50, 0, 255);
  
  public int color_b_b;
  
  public Setting hide_own = create("Hide Own", "HoleESPHideOwn", false);
  
  public ArrayList holes = new ArrayList();
  
  public Setting go = create("G", "HoleESPGo", 20, 0, 255);
  
  public int color_r_o;
  
  public Setting ab = create("A", "HoleESPAb", 30, 0, 255);
  
  public Setting mode = create("Mode", "HoleESPMode", "Pretty", combobox(new String[] { "Pretty", "Solid", "Outline", "Glow", "Glow 2" }));
  
  public int color_b_o;
  
  public Setting ao = create("A", "HoleESPAo", 30, 0, 255);
  
  public int color_b;
  
  public Setting range = create("Range", "HoleESPRange", 8, 1, 12);
  
  public int color_a;
  
  public boolean solid = false;
  
  public int color_g;
  
  public int color_r;
  
  public boolean glow = false;
  
  public Setting dual_enable = create("Dual holes", "HoleESPDualHoles", true);
  
  public Setting gb = create("G", "HoleESPGb", 210, 0, 255);
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (!this.holes.isEmpty() || !this.dual_holes.isEmpty()) {
      float f = (float)this.off_set.get_value(1.0D);
      for (Pair pair : this.holes) {
        if (((Boolean)pair.getValue()).booleanValue()) {
          this.color_r = this.color_r_b;
          this.color_g = this.color_g_b;
          this.color_b = this.color_b_b;
          this.color_a = this.ab.get_value(1);
        } else if (!((Boolean)pair.getValue()).booleanValue()) {
          this.color_r = this.color_r_o;
          this.color_g = this.color_g_o;
          this.color_b = this.color_b_o;
          this.color_a = this.ao.get_value(1);
        } else {
          continue;
        } 
        if (this.hide_own.get_value(true) && ((BlockPos)pair.getKey()).equals(new BlockPos(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v)))
          continue; 
        if (this.solid) {
          RenderHelp.prepare("quads");
          RenderHelp.draw_cube(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.0963337E9F ^ 0x7ED8B9B5), f, Float.intBitsToFloat(1.11041408E9F ^ 0x7DAF92E7), this.color_r, this.color_g, this.color_b, this.color_a, "all");
          RenderHelp.release();
        } 
        if (this.outline) {
          RenderHelp.prepare("lines");
          RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.08932045E9F ^ 0x7F6DB602), f, Float.intBitsToFloat(1.08639194E9F ^ 0x7F410692), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), "all");
          RenderHelp.release();
        } 
        if (this.glow) {
          RenderHelp.prepare("lines");
          RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.09037248E9F ^ 0x7F7DC372), Float.intBitsToFloat(2.11738931E9F ^ 0x7E34CBDB), Float.intBitsToFloat(1.0971072E9F ^ 0x7EE486D5), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), "all");
          RenderHelp.release();
          RenderHelp.prepare("quads");
          RenderHelp.draw_gradiant_cube(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.08627251E9F ^ 0x7F3F33CE), f, Float.intBitsToFloat(1.08890906E9F ^ 0x7F676EDE), new Color(this.color_r, this.color_g, this.color_b, this.color_a), new Color(0, 0, 0, 0), "all");
          RenderHelp.release();
        } 
        if (this.glowOutline) {
          RenderHelp.prepare("lines");
          RenderHelp.draw_gradiant_outline(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), f, new Color(this.color_r, this.color_g, this.color_b, this.line_a.get_value(1)), new Color(0, 0, 0, 0), "all");
          RenderHelp.release();
        } 
      } 
      for (Pair pair : this.dual_holes) {
        BlockPos blockPos = new BlockPos((Entity)mc.field_71439_g);
        if (this.hide_own.get_value(true) && (((BlockPos)pair.getKey()).equals(new BlockPos(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v)) || ((BlockPos)pair.getKey()).equals(blockPos.func_177971_a((Vec3i)orientConv(oppositeIntOrient(((Integer)this.dual_hole_sides.get(pair.getKey())).intValue()))))))
          continue; 
        if (((Boolean)pair.getValue()).booleanValue()) {
          this.color_r = this.color_r_b;
          this.color_g = this.color_g_b;
          this.color_b = this.color_b_b;
          this.color_a = this.ab.get_value(1);
        } else if (!((Boolean)pair.getValue()).booleanValue()) {
          this.color_r = this.color_r_o;
          this.color_g = this.color_g_o;
          this.color_b = this.color_b_o;
          this.color_a = this.ao.get_value(1);
        } else {
          continue;
        } 
        if (this.solid) {
          RenderHelp.prepare("quads");
          RenderHelp.draw_cube(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.08737715E9F ^ 0x7F500F2D), f, Float.intBitsToFloat(1.08443866E9F ^ 0x7F2338A7), this.color_r, this.color_g, this.color_b, this.color_a, getDirectionsToRenderQuad((BlockPos)pair.getKey()));
          RenderHelp.release();
        } 
        if (this.outline) {
          RenderHelp.prepare("lines");
          RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.08788186E9F ^ 0x7F57C285), f, Float.intBitsToFloat(1.08871245E9F ^ 0x7F646F1A), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), getDirectionsToRenderOutline((BlockPos)pair.getKey()));
          RenderHelp.release();
        } 
        if (this.glow) {
          RenderHelp.prepare("lines");
          RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.08428941E9F ^ 0x7F20F197), Float.intBitsToFloat(2.13613491E9F ^ 0x7F52D4EF), Float.intBitsToFloat(1.09017165E9F ^ 0x7F7AB302), this.color_r, this.color_g, this.color_b, this.line_a.get_value(1), getDirectionsToRenderOutline((BlockPos)pair.getKey()));
          RenderHelp.release();
          RenderHelp.prepare("quads");
          RenderHelp.draw_gradiant_cube(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), Float.intBitsToFloat(1.08710054E9F ^ 0x7F4BD6AA), f, Float.intBitsToFloat(1.11492979E9F ^ 0x7DF47A47), new Color(this.color_r, this.color_g, this.color_b, this.color_a), new Color(0, 0, 0, 0), getDirectionsToRenderQuad((BlockPos)pair.getKey()));
          RenderHelp.release();
        } 
        if (this.glowOutline) {
          RenderHelp.prepare("lines");
          RenderHelp.draw_gradiant_outline(RenderHelp.get_buffer_build(), ((BlockPos)pair.getKey()).func_177958_n(), ((BlockPos)pair.getKey()).func_177956_o(), ((BlockPos)pair.getKey()).func_177952_p(), f, new Color(this.color_r, this.color_g, this.color_b, this.line_a.get_value(1)), new Color(0, 0, 0, 0), getDirectionsToRenderOutline((BlockPos)pair.getKey()));
          RenderHelp.release();
        } 
      } 
    } 
  }
  
  public int oppositeIntOrient(int paramInt) {
    (give up)null;
    byte b = 0;
    switch (paramInt) {
      case 0:
        b = 5;
        break;
      case 1:
        b = 3;
        break;
      case 2:
        b = 4;
        break;
      case 3:
        b = 1;
        break;
      case 4:
        b = 2;
        break;
    } 
    return b;
  }
  
  public String getDirectionsToRenderQuad(BlockPos paramBlockPos) {
    (give up)null;
    int i = ((Integer)this.dual_hole_sides.get(paramBlockPos)).intValue();
    switch (i) {
      case 1:
        return "east-south-west-top-bottom";
      case 2:
        return "north-south-west-top-bottom";
      case 3:
        return "north-east-west-top-bottom";
      case 4:
        return "north-east-south-top-bottom";
    } 
    return "all";
  }
  
  public HoleESP() {
    super(Category.render);
  }
  
  public BlockPos player_as_blockpos() {
    (give up)null;
    return new BlockPos(Math.floor(mc.field_71439_g.field_70165_t), Math.floor(mc.field_71439_g.field_70163_u), Math.floor(mc.field_71439_g.field_70161_v));
  }
  
  public void update() {
    (give up)null;
    this.color_r_b = this.rb.get_value(1);
    this.color_g_b = this.gb.get_value(1);
    this.color_b_b = this.bb.get_value(1);
    this.color_r_o = this.ro.get_value(1);
    this.color_g_o = this.go.get_value(1);
    this.color_b_o = this.bo.get_value(1);
    this.holes.clear();
    this.dual_holes.clear();
    this.dual_hole_sides.clear();
    if (mc.field_71439_g != null || mc.field_71441_e != null) {
      if (this.mode.in("Pretty")) {
        this.outline = true;
        this.solid = true;
        this.glow = false;
        this.glowOutline = false;
      } 
      if (this.mode.in("Solid")) {
        this.outline = false;
        this.solid = true;
        this.glow = false;
        this.glowOutline = false;
      } 
      if (this.mode.in("Outline")) {
        this.outline = true;
        this.solid = false;
        this.glow = false;
        this.glowOutline = false;
      } 
      if (this.mode.in("Glow")) {
        this.outline = false;
        this.solid = false;
        this.glow = true;
        this.glowOutline = false;
      } 
      if (this.mode.in("Glow 2")) {
        this.outline = false;
        this.solid = false;
        this.glow = true;
        this.glowOutline = true;
      } 
      int i = (int)Math.ceil(this.range.get_value(1));
      List list = sphere(player_as_blockpos(), i);
      for (BlockPos blockPos : list) {
        if (!mc.field_71441_e.func_180495_p(blockPos).func_177230_c().equals(Blocks.field_150350_a) || !mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 1, 0)).func_177230_c().equals(Blocks.field_150350_a) || !mc.field_71441_e.func_180495_p(blockPos.func_177982_a(0, 2, 0)).func_177230_c().equals(Blocks.field_150350_a))
          continue; 
        boolean bool = true;
        this.safe_sides = 0;
        byte b = -1;
        byte b1 = 0;
        for (BlockPos blockPos1 : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
          Block block = mc.field_71441_e.func_180495_p(blockPos.func_177971_a((Vec3i)blockPos1)).func_177230_c();
          if (block != Blocks.field_150357_h && block != Blocks.field_150343_Z && block != Blocks.field_150477_bB && block != Blocks.field_150467_bQ) {
            bool = false;
            break;
          } 
          if (block == Blocks.field_150357_h)
            this.safe_sides++; 
          b1++;
        } 
        if (this.safe_sides == 5) {
          if (!this.bedrock_enable.get_value(true))
            continue; 
        } else {
          if (!this.obsidian_enable.get_value(true))
            continue; 
          this.holes.add(new Pair(blockPos, Boolean.valueOf(false)));
          continue;
        } 
        this.holes.add(new Pair(blockPos, Boolean.valueOf(true)));
      } 
    } 
  }
  
  public boolean isBlockHole(BlockPos paramBlockPos) {
    (give up)null;
    if (!is_active())
      update(); 
    return (this.holes.contains(new Pair(paramBlockPos, Boolean.valueOf(true))) || this.holes.contains(new Pair(paramBlockPos, Boolean.valueOf(false))));
  }
  
  public boolean checkDual(BlockPos paramBlockPos, int paramInt) {
    (give up)null;
    byte b = -1;
    for (BlockPos blockPos : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
      if (paramInt != oppositeIntOrient(++b)) {
        Block block = mc.field_71441_e.func_180495_p(paramBlockPos.func_177971_a((Vec3i)blockPos)).func_177230_c();
        if (block != Blocks.field_150357_h && block != Blocks.field_150343_Z && block != Blocks.field_150477_bB && block != Blocks.field_150467_bQ)
          return false; 
        if (block == Blocks.field_150357_h)
          this.safe_sides++; 
      } 
    } 
    return true;
  }
  
  public List sphere(BlockPos paramBlockPos, float paramFloat) {
    (give up)null;
    byte b = 0;
    ArrayList<BlockPos> arrayList = new ArrayList();
    int i = paramBlockPos.func_177958_n();
    int j = paramBlockPos.func_177956_o();
    int k = paramBlockPos.func_177952_p();
    for (int m = i - (int)paramFloat; m <= i + paramFloat; m++) {
      for (int n = k - (int)paramFloat; n <= k + paramFloat; n++) {
        for (int i1 = j - (int)paramFloat; i1 < j + paramFloat; i1++) {
          double d = ((i - m) * (i - m) + (k - n) * (k - n) + (j - i1) * (j - i1));
          if (d < (paramFloat * paramFloat)) {
            BlockPos blockPos = new BlockPos(m, i1 + b, n);
            arrayList.add(blockPos);
          } 
        } 
      } 
    } 
    return arrayList;
  }
  
  public String getDirectionsToRenderOutline(BlockPos paramBlockPos) {
    (give up)null;
    int i = ((Integer)this.dual_hole_sides.get(paramBlockPos)).intValue();
    switch (i) {
      case 1:
        return "downeast-upeast-downsouth-upsouth-downwest-upwest-southwest-southeast";
      case 2:
        return "downnorth-upnorth-downsouth-upsouth-downwest-upwest-northwest-southwest";
      case 3:
        return "upnorth-downnorth-upeast-downeast-upwest-downwest-northeast-northwest";
      case 4:
        return "upnorth-downnorth-upeast-downeast-upsouth-downsouth-northeast-southeast";
    } 
    return "all";
  }
  
  public BlockPos orientConv(int paramInt) {
    (give up)null;
    BlockPos blockPos = null;
    switch (paramInt) {
      case 0:
        blockPos = new BlockPos(0, -1, 0);
        break;
      case 1:
        blockPos = new BlockPos(0, 0, -1);
        break;
      case 2:
        blockPos = new BlockPos(1, 0, 0);
        break;
      case 3:
        blockPos = new BlockPos(0, 0, 1);
        break;
      case 4:
        blockPos = new BlockPos(-1, 0, 0);
        break;
      case 5:
        blockPos = new BlockPos(0, 1, 0);
        break;
    } 
    return blockPos;
  }
}
