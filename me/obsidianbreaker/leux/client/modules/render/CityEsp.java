package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.turok.draw.RenderHelp;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class CityEsp extends Module {
  public List blocks = new ArrayList();
  
  public Setting r = create("R", "CityR", 130, 0, 255);
  
  public Setting b = create("B", "CityB", 180, 0, 255);
  
  public boolean outline = false;
  
  public Setting a = create("A", "CityA", 50, 0, 255);
  
  public Setting off_set = create("Height", "CityOffSetSide", 0.5D, 0.0D, 1.0D);
  
  public Setting mode = create("Mode", "CityMode", "Pretty", combobox(new String[] { "Pretty", "Solid", "Outline" }));
  
  public Setting g = create("G", "CityG", 32, 0, 255);
  
  public Setting endcrystal_mode = create("EndCrystal", "CityEndCrystal", true);
  
  public boolean solid = false;
  
  public Setting range = create("Range", "CityRange", 6, 1, 12);
  
  public void update() {
    (give up)null;
    this.blocks.clear();
    Iterator<EntityPlayer> iterator = mc.field_71441_e.field_73010_i.iterator();
    while (true) {
      EntityPlayer entityPlayer;
      if (iterator.hasNext()) {
        entityPlayer = iterator.next();
        if (mc.field_71439_g.func_70032_d((Entity)entityPlayer) > this.range.get_value(1) || mc.field_71439_g == entityPlayer)
          continue; 
      } else {
        return;
      } 
      BlockPos blockPos = EntityUtil.is_cityable(entityPlayer, this.endcrystal_mode.get_value(true));
      this.blocks.add(blockPos);
    } 
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    float f = (float)this.off_set.get_value(1.0D);
    for (BlockPos blockPos : this.blocks) {
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
      if (this.solid) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(RenderHelp.get_buffer_build(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0F, f, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } 
      if (this.outline) {
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p(), 1.0F, f, 1.0F, this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1), "all");
        RenderHelp.release();
      } 
    } 
  }
  
  public CityEsp() {
    super(Category.render);
  }
}
