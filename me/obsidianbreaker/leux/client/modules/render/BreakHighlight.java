package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import java.util.ArrayList;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BreakHighlight extends Module {
  public Setting rgb = create("RGB Effect", "HighlightRGBEffect", false);
  
  public boolean solid = false;
  
  public boolean outline = false;
  
  public Setting g = create("G", "BreakG", 0, 0, 255);
  
  public Setting l_a = create("Outline A", "BreakLineA", 255, 0, 255);
  
  public Setting range = create("Range", "BreakRange", 8, 0, 25);
  
  public Setting a = create("A", "BreakA", 35, 0, 255);
  
  public Setting b = create("B", "BreakB", 80, 0, 255);
  
  public Setting r = create("R", "BreakR", 146, 0, 255);
  
  public Setting mode = create("Mode", "HighlightMode", "Pretty", combobox(new String[] { "Pretty", "Solid", "Outline" }));
  
  public int color_g;
  
  public ArrayList BlocksBeingBroken = new ArrayList();
  
  public int color_b;
  
  public int color_r;
  
  public void lambda$render$0(Integer paramInteger, DestroyBlockProgress paramDestroyBlockProgress) {
    (give up)null;
    BlockPos blockPos = paramDestroyBlockProgress.func_180246_b();
    if (mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_150350_a)
      return; 
    if (blockPos.func_185332_f((int)mc.field_71439_g.field_70165_t, (int)mc.field_71439_g.field_70163_u, (int)mc.field_71439_g.field_70161_v) <= this.range.get_value(1)) {
      if (this.solid) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), Float.intBitsToFloat(1.09676275E9F ^ 0x7EDF4545), Float.intBitsToFloat(1.10505626E9F ^ 0x7E5DD207), Float.intBitsToFloat(1.11817357E9F ^ 0x7D25F99F), this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } 
      if (this.outline) {
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), Float.intBitsToFloat(1.08815501E9F ^ 0x7F5BED76), Float.intBitsToFloat(1.08292698E9F ^ 0x7F0C27B6), Float.intBitsToFloat(1.08874483E9F ^ 0x7F64ED76), this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.l_a.get_value(1), "all");
        RenderHelp.release();
      } 
    } 
  }
  
  public BreakHighlight() {
    super(Category.render);
  }
  
  public void disable() {
    (give up)null;
    this.outline = false;
    this.solid = false;
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (mc.field_71439_g != null && mc.field_71441_e != null) {
      float[] arrayOfFloat = { (float)(System.currentTimeMillis() % (1371101054L ^ 0x51B97A7EL)) / Float.intBitsToFloat(9.5294278E8F ^ 0x7EF8C0B7) };
      int i = Color.HSBtoRGB(arrayOfFloat[0], Float.intBitsToFloat(1.08362918E9F ^ 0x7F16DE67), Float.intBitsToFloat(1.13930701E9F ^ 0x7C6871FF));
      if (this.rgb.get_value(true)) {
        this.color_r = i >> 16 & 0xFF;
        this.color_g = i >> 8 & 0xFF;
        this.color_b = i & 0xFF;
        this.r.set_value(this.color_r);
        this.g.set_value(this.color_g);
        this.b.set_value(this.color_b);
      } else {
        this.color_r = this.r.get_value(1);
        this.color_g = this.g.get_value(2);
        this.color_b = this.b.get_value(3);
      } 
      if (this.mode.in("Pretty")) {
        this.outline = true;
        this.solid = true;
      } 
      if (this.mode.in("Solid")) {
        this.outline = false;
        this.solid = true;
      } 
      if (this.mode.in("Outline")) {
        this.outline = true;
        this.solid = false;
      } 
      mc.field_71438_f.field_72738_E.forEach(this::lambda$render$0);
    } 
  }
}
