package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.util.ArrayList;
import java.util.List;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.lwjgl.opengl.GL11;

public class VoidESP extends Module {
  public Setting void_radius = create("Range", "VoidESPRange", 6, 1, 10);
  
  public ICamera camera = (ICamera)new Frustum();
  
  public List void_blocks = new ArrayList();
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
    Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
    Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
    (new ArrayList(this.void_blocks)).forEach(this::lambda$render$0);
  }
  
  public boolean is_void_hole(BlockPos paramBlockPos) {
    (give up)null;
    return (paramBlockPos.func_177956_o() != 0) ? false : (!(mc.field_71441_e.func_180495_p(paramBlockPos).func_177230_c() != Blocks.field_150350_a));
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    this.void_blocks.clear();
    Vec3i vec3i = new Vec3i(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v);
    for (int i = vec3i.func_177958_n() - this.void_radius.get_value(1); i < vec3i.func_177958_n() + this.void_radius.get_value(1); i++) {
      for (int j = vec3i.func_177952_p() - this.void_radius.get_value(1); j < vec3i.func_177952_p() + this.void_radius.get_value(1); j++) {
        for (int k = vec3i.func_177956_o() + this.void_radius.get_value(1); k > vec3i.func_177956_o() - this.void_radius.get_value(1); k--) {
          BlockPos blockPos = new BlockPos(i, k, j);
          if (is_void_hole(blockPos))
            this.void_blocks.add(blockPos); 
        } 
      } 
    } 
  }
  
  public void lambda$render$0(BlockPos paramBlockPos) {
    (give up)null;
    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(paramBlockPos.func_177958_n() - (mc.func_175598_ae()).field_78730_l, paramBlockPos.func_177956_o() - (mc.func_175598_ae()).field_78731_m, paramBlockPos.func_177952_p() - (mc.func_175598_ae()).field_78728_n, (paramBlockPos.func_177958_n() + 1) - (mc.func_175598_ae()).field_78730_l, (paramBlockPos.func_177956_o() + 1) - (mc.func_175598_ae()).field_78731_m, (paramBlockPos.func_177952_p() + 1) - (mc.func_175598_ae()).field_78728_n);
    this.camera.func_78547_a((mc.func_175606_aa()).field_70165_t, (mc.func_175606_aa()).field_70163_u, (mc.func_175606_aa()).field_70161_v);
    if (this.camera.func_78546_a(new AxisAlignedBB(axisAlignedBB.field_72340_a + (mc.func_175598_ae()).field_78730_l, axisAlignedBB.field_72338_b + (mc.func_175598_ae()).field_78731_m, axisAlignedBB.field_72339_c + (mc.func_175598_ae()).field_78728_n, axisAlignedBB.field_72336_d + (mc.func_175598_ae()).field_78730_l, axisAlignedBB.field_72337_e + (mc.func_175598_ae()).field_78731_m, axisAlignedBB.field_72334_f + (mc.func_175598_ae()).field_78728_n))) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179147_l();
      GlStateManager.func_179097_i();
      GlStateManager.func_179120_a(770, 771, 0, 1);
      GlStateManager.func_179090_x();
      GlStateManager.func_179132_a(false);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glLineWidth(Float.intBitsToFloat(1.0835849E9F ^ 0x7F563194));
      RenderGlobal.func_189694_a(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f, Float.intBitsToFloat(1.02584832E9F ^ 0x7E5A341B), Float.intBitsToFloat(1.0627127E9F ^ 0x7EF7B573), Float.intBitsToFloat(1.05621363E9F ^ 0x7F048A63), Float.intBitsToFloat(1.07393677E9F ^ 0x7F02F99D));
      RenderGlobal.func_189695_b(axisAlignedBB.field_72340_a, axisAlignedBB.field_72338_b, axisAlignedBB.field_72339_c, axisAlignedBB.field_72336_d, axisAlignedBB.field_72337_e, axisAlignedBB.field_72334_f, Float.intBitsToFloat(1.04164064E9F ^ 0x7D692CCF), Float.intBitsToFloat(1.0527991E9F ^ 0x7F60707E), Float.intBitsToFloat(1.05595213E9F ^ 0x7F008D13), Float.intBitsToFloat(1.08733363E9F ^ 0x7EAE2363));
      GL11.glDisable(2848);
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179126_j();
      GlStateManager.func_179098_w();
      GlStateManager.func_179084_k();
      GlStateManager.func_179121_F();
    } 
  }
  
  public VoidESP() {
    super(Category.render);
  }
}
