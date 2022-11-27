package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.Vec3d;

public class EventRender extends EventCancellable {
  public Tessellator tessellator;
  
  public ScaledResolution res = new ScaledResolution(Minecraft.func_71410_x());
  
  public Vec3d render_pos;
  
  public Vec3d get_render_pos() {
    (give up)null;
    return this.render_pos;
  }
  
  public double get_screen_width() {
    (give up)null;
    return this.res.func_78327_c();
  }
  
  public Tessellator get_tessellator() {
    (give up)null;
    return this.tessellator;
  }
  
  public void reset_translation() {
    (give up)null;
    set_translation(this.render_pos);
  }
  
  public BufferBuilder get_buffer_build() {
    (give up)null;
    return this.tessellator.func_178180_c();
  }
  
  public double get_screen_height() {
    (give up)null;
    return this.res.func_78324_d();
  }
  
  public EventRender(Tessellator paramTessellator, Vec3d paramVec3d) {
    this.tessellator = paramTessellator;
    this.render_pos = paramVec3d;
  }
  
  public void set_translation(Vec3d paramVec3d) {
    (give up)null;
    get_buffer_build().func_178969_c(-paramVec3d.field_72450_a, -paramVec3d.field_72448_b, -paramVec3d.field_72449_c);
  }
}
