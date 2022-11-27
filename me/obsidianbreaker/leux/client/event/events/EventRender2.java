package me.obsidianbreaker.leux.client.event.events;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.math.Vec3d;

public class EventRender2 extends EventCancellable {
  public Vec3d renderPos;
  
  public Tessellator tessellator;
  
  public Vec3d getRenderPos() {
    (give up)null;
    return this.renderPos;
  }
  
  public void resetTranslation() {
    (give up)null;
    setTranslation(this.renderPos);
  }
  
  public Tessellator getTessellator() {
    (give up)null;
    return this.tessellator;
  }
  
  public EventRender2(Tessellator paramTessellator, Vec3d paramVec3d) {
    this.tessellator = paramTessellator;
    this.renderPos = paramVec3d;
  }
  
  public BufferBuilder getBuffer() {
    (give up)null;
    return this.tessellator.func_178180_c();
  }
  
  public void setTranslation(Vec3d paramVec3d) {
    (give up)null;
    getBuffer().func_178969_c(-paramVec3d.field_72450_a, -paramVec3d.field_72448_b, -paramVec3d.field_72449_c);
  }
}
