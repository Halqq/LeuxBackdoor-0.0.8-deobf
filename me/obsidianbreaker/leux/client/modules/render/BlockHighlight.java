package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class BlockHighlight extends Module {
  public int color_b;
  
  public int color_g;
  
  public Setting l_a = create("Outline A", "HighlightLineA", 255, 0, 255);
  
  public Setting g = create("G", "HighlightG", 70, 0, 255);
  
  public Setting r = create("R", "HighlightR", 45, 0, 255);
  
  public boolean outline = false;
  
  public Setting rgb = create("RGB Effect", "HighlightRGBEffect", false);
  
  public Setting mode = create("Mode", "HighlightMode", "Pretty", combobox(new String[] { "Pretty", "Solid", "Outline" }));
  
  public Setting b = create("B", "HighlightB", 170, 0, 255);
  
  public int color_r;
  
  public Setting a = create("A", "HighlightA", 35, 0, 255);
  
  public boolean solid = false;
  
  public void disable() {
    (give up)null;
    this.outline = false;
    this.solid = false;
  }
  
  public BlockHighlight() {
    super(Category.render);
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (mc.field_71439_g != null && mc.field_71441_e != null) {
      float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
      int i = Color.HSBtoRGB(arrayOfFloat[0], 1.0F, 1.0F);
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
      RayTraceResult rayTraceResult = mc.field_71476_x;
      if (rayTraceResult.field_72313_a == RayTraceResult.Type.BLOCK) {
        BlockPos blockPos = rayTraceResult.func_178782_a();
        if (this.solid) {
          RenderHelp.prepare("quads");
          RenderHelp.draw_cube(blockPos, this.color_r, this.color_g, this.color_b, this.a.get_value(1), "all");
          RenderHelp.release();
        } 
        if (this.outline) {
          RenderHelp.prepare("lines");
          RenderHelp.draw_cube_line(blockPos, this.color_r, this.color_g, this.color_b, this.l_a.get_value(1), "all");
          RenderHelp.release();
        } 
      } 
    } 
  }
}
