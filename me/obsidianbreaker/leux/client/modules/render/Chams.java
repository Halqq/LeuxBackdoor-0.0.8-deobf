package me.obsidianbreaker.leux.client.modules.render;

import give up;
import java.awt.Color;
import me.obsidianbreaker.leux.client.event.events.EventRender;
import me.obsidianbreaker.leux.client.event.events.EventRenderEntityModel;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.obsidianbreaker.leux.client.util.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class Chams extends Module {
  public Setting scale = create("Factor", "ChamsFactor", 0.0D, -1.0D, 1.0D);
  
  public Setting cwidth = create("Crystals Width", "Width", 2.3D, 0.0D, 10.0D);
  
  public Setting rainbow_mode = create("Rainbow", "ChamsRainbow", false);
  
  public Setting mobs = create("Mobs", "ChamsMobs", false);
  
  public Setting players = create("Players", "ChamsPlayers", false);
  
  public Setting sat = create("Satiation", "ChamsSatiation", 0.8D, 0.0D, 1.0D);
  
  public Setting crystals = create("Crystals", "Crystals", true);
  
  public Setting g = create("G", "ChamsG", 120, 0, 255);
  
  public Setting r = create("R", "ChamsR", 120, 0, 255);
  
  public Setting xpbottles = create("Xp Bottles", "ChamsBottles", false);
  
  public Setting box_a = create("Box A", "ChamsABox", 100, 0, 255);
  
  public Setting items = create("Items", "ChamsItems", false);
  
  public Setting a = create("A", "ChamsA", 100, 0, 255);
  
  public Setting width = create("Width", "ChamsWdith", 2.0D, 0.5D, 5.0D);
  
  public Setting brightness = create("Brightness", "ChamsBrightness", 0.8D, 0.0D, 1.0D);
  
  public Setting b = create("B", "ChamsB", 240, 0, 255);
  
  public Setting xporbs = create("Xp Orbs", "ChamsXPO", false);
  
  public Setting self = create("Self", "ChamsSelf", false);
  
  public Setting mode = create("Mode", "ChamsMode", "Outline", combobox(new String[] { "Outline", "Wireframe" }));
  
  public Setting top = create("Top", "ChamsTop", false);
  
  public Setting lines = create("Crystals Lines", "Lines", true);
  
  public Setting pearl = create("Pearls", "ChamsPearls", false);
  
  public void cycle_rainbow() {
    (give up)null;
    float[] arrayOfFloat = { (float)(System.currentTimeMillis() % 11520L) / 11520.0F };
    int i = Color.HSBtoRGB(arrayOfFloat[0], this.sat.get_value(1), this.brightness.get_value(1));
    this.r.set_value(i >> 16 & 0xFF);
    this.g.set_value(i >> 8 & 0xFF);
    this.b.set_value(i & 0xFF);
  }
  
  public Chams() {
    super(Category.render);
  }
  
  public void render(EventRender paramEventRender) {
    (give up)null;
    if (this.items.get_value(true)) {
      byte b = 0;
      for (Entity entity : mc.field_71441_e.field_72996_f) {
        if (entity instanceof net.minecraft.entity.item.EntityItem && mc.field_71439_g.func_70068_e(entity) < 2500.0D) {
          Vec3d vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.func_184121_ak());
          AxisAlignedBB axisAlignedBB = new AxisAlignedBB((entity.func_174813_aQ()).field_72340_a - 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72338_b - 0.0D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72339_c - 0.05D - entity.field_70161_v + vec3d.field_72449_c, (entity.func_174813_aQ()).field_72336_d + 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72337_e + 0.1D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72334_f + 0.05D - entity.field_70161_v + vec3d.field_72449_c);
          GlStateManager.func_179094_E();
          GlStateManager.func_179147_l();
          GlStateManager.func_179097_i();
          GlStateManager.func_179120_a(770, 771, 0, 1);
          GlStateManager.func_179090_x();
          GlStateManager.func_179132_a(false);
          GL11.glEnable(2848);
          GL11.glHint(3154, 4354);
          GL11.glLineWidth(Float.intBitsToFloat(1.0853673E9F ^ 0x7F3163C1));
          RenderGlobal.func_189696_b(axisAlignedBB.func_186662_g(this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(1.01006874E9F ^ 0x7F4B6CF8), this.g.get_value(1) / Float.intBitsToFloat(1.03810579E9F ^ 0x7E9F3CD3), this.b.get_value(1) / Float.intBitsToFloat(1.05244979E9F ^ 0x7DC41C0F), this.box_a.get_value(1) / Float.intBitsToFloat(1.03793613E9F ^ 0x7EA2A5F3));
          GL11.glDisable(2848);
          GlStateManager.func_179132_a(true);
          GlStateManager.func_179126_j();
          GlStateManager.func_179098_w();
          GlStateManager.func_179084_k();
          GlStateManager.func_179121_F();
          RenderUtil.drawBlockOutline(axisAlignedBB.func_186662_g(this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(1.09631411E9F ^ 0x7ED86D3F));
          if (++b >= 50)
            break; 
        } 
      } 
    } 
    if (this.xporbs.get_value(true)) {
      byte b = 0;
      for (Entity entity : mc.field_71441_e.field_72996_f) {
        if (entity instanceof net.minecraft.entity.item.EntityXPOrb && mc.field_71439_g.func_70068_e(entity) < 2500.0D) {
          Vec3d vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.func_184121_ak());
          AxisAlignedBB axisAlignedBB = new AxisAlignedBB((entity.func_174813_aQ()).field_72340_a - 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72338_b - 0.0D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72339_c - 0.05D - entity.field_70161_v + vec3d.field_72449_c, (entity.func_174813_aQ()).field_72336_d + 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72337_e + 0.1D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72334_f + 0.05D - entity.field_70161_v + vec3d.field_72449_c);
          GlStateManager.func_179094_E();
          GlStateManager.func_179147_l();
          GlStateManager.func_179097_i();
          GlStateManager.func_179120_a(770, 771, 0, 1);
          GlStateManager.func_179090_x();
          GlStateManager.func_179132_a(false);
          GL11.glEnable(2848);
          GL11.glHint(3154, 4354);
          GL11.glLineWidth(Float.intBitsToFloat(1.08387853E9F ^ 0x7F1AAC42));
          RenderGlobal.func_189696_b(axisAlignedBB.func_186662_g(this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(1.01380282E9F ^ 0x7F126750), this.g.get_value(1) / Float.intBitsToFloat(1.03180678E9F ^ 0x7EFF1F43), this.b.get_value(1) / Float.intBitsToFloat(1.01371302E9F ^ 0x7F130876), this.box_a.get_value(1) / Float.intBitsToFloat(1.01459834E9F ^ 0x7F068AC7));
          GL11.glDisable(2848);
          GlStateManager.func_179132_a(true);
          GlStateManager.func_179126_j();
          GlStateManager.func_179098_w();
          GlStateManager.func_179084_k();
          GlStateManager.func_179121_F();
          RenderUtil.drawBlockOutline(axisAlignedBB.func_186662_g(this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(1.0874112E9F ^ 0x7F509415));
          if (++b >= 50)
            break; 
        } 
      } 
    } 
    if (this.pearl.get_value(true)) {
      byte b = 0;
      for (Entity entity : mc.field_71441_e.field_72996_f) {
        if (entity instanceof net.minecraft.entity.item.EntityEnderPearl && mc.field_71439_g.func_70068_e(entity) < 2500.0D) {
          Vec3d vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.func_184121_ak());
          AxisAlignedBB axisAlignedBB = new AxisAlignedBB((entity.func_174813_aQ()).field_72340_a - 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72338_b - 0.0D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72339_c - 0.05D - entity.field_70161_v + vec3d.field_72449_c, (entity.func_174813_aQ()).field_72336_d + 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72337_e + 0.1D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72334_f + 0.05D - entity.field_70161_v + vec3d.field_72449_c);
          GlStateManager.func_179094_E();
          GlStateManager.func_179147_l();
          GlStateManager.func_179097_i();
          GlStateManager.func_179120_a(770, 771, 0, 1);
          GlStateManager.func_179090_x();
          GlStateManager.func_179132_a(false);
          GL11.glEnable(2848);
          GL11.glHint(3154, 4354);
          GL11.glLineWidth(Float.intBitsToFloat(1.10425766E9F ^ 0x7E51A283));
          RenderGlobal.func_189696_b(axisAlignedBB.func_186662_g(this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(1.00688582E9F ^ 0x7F7CDBB6), this.g.get_value(1) / Float.intBitsToFloat(1.01180019E9F ^ 0x7F31D868), this.b.get_value(1) / Float.intBitsToFloat(1.00821485E9F ^ 0x7F67234B), this.box_a.get_value(1) / Float.intBitsToFloat(1.03286202E9F ^ 0x7EEF3945));
          GL11.glDisable(2848);
          GlStateManager.func_179132_a(true);
          GlStateManager.func_179126_j();
          GlStateManager.func_179098_w();
          GlStateManager.func_179084_k();
          GlStateManager.func_179121_F();
          RenderUtil.drawBlockOutline(axisAlignedBB.func_186662_g(this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(1.10845414E9F ^ 0x7D91AADF));
          if (++b >= 50)
            break; 
        } 
      } 
    } 
    if (this.xpbottles.get_value(true)) {
      byte b = 0;
      for (Entity entity : mc.field_71441_e.field_72996_f) {
        if (entity instanceof net.minecraft.entity.item.EntityExpBottle && mc.field_71439_g.func_70068_e(entity) < 2500.0D) {
          Vec3d vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.func_184121_ak());
          AxisAlignedBB axisAlignedBB = new AxisAlignedBB((entity.func_174813_aQ()).field_72340_a - 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72338_b - 0.0D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72339_c - 0.05D - entity.field_70161_v + vec3d.field_72449_c, (entity.func_174813_aQ()).field_72336_d + 0.05D - entity.field_70165_t + vec3d.field_72450_a, (entity.func_174813_aQ()).field_72337_e + 0.1D - entity.field_70163_u + vec3d.field_72448_b, (entity.func_174813_aQ()).field_72334_f + 0.05D - entity.field_70161_v + vec3d.field_72449_c);
          GlStateManager.func_179094_E();
          GlStateManager.func_179147_l();
          GlStateManager.func_179097_i();
          GlStateManager.func_179120_a(770, 771, 0, 1);
          GlStateManager.func_179090_x();
          GlStateManager.func_179132_a(false);
          GL11.glEnable(2848);
          GL11.glHint(3154, 4354);
          GL11.glLineWidth(Float.intBitsToFloat(1.1145632E9F ^ 0x7DEEE2BF));
          RenderGlobal.func_189696_b(axisAlignedBB.func_186662_g(this.scale.get_value(1)), this.r.get_value(1) / Float.intBitsToFloat(1.033584E9F ^ 0x7EE43D6F), this.g.get_value(1) / Float.intBitsToFloat(1.03467923E9F ^ 0x7ED4F3DF), this.b.get_value(1) / Float.intBitsToFloat(1.01370509E9F ^ 0x7F14E981), this.box_a.get_value(1) / Float.intBitsToFloat(1.05099757E9F ^ 0x7DDBF35F));
          GL11.glDisable(2848);
          GlStateManager.func_179132_a(true);
          GlStateManager.func_179126_j();
          GlStateManager.func_179098_w();
          GlStateManager.func_179084_k();
          GlStateManager.func_179121_F();
          RenderUtil.drawBlockOutline(axisAlignedBB.func_186662_g(this.scale.get_value(1)), new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1)), Float.intBitsToFloat(1.0973184E9F ^ 0x7EE7BFE7));
          if (++b >= 50)
            break; 
        } 
      } 
    } 
  }
  
  public void on_render_model(EventRenderEntityModel paramEventRenderEntityModel) {
    (give up)null;
    if (paramEventRenderEntityModel.stage != 0 || paramEventRenderEntityModel.entity == null || (!this.self.get_value(true) && paramEventRenderEntityModel.entity.equals(mc.field_71439_g)) || (!this.players.get_value(true) && paramEventRenderEntityModel.entity instanceof net.minecraft.entity.player.EntityPlayer) || (!this.mobs.get_value(true) && paramEventRenderEntityModel.entity instanceof net.minecraft.entity.monster.EntityMob))
      return; 
    Color color = new Color(this.r.get_value(1), this.g.get_value(1), this.b.get_value(1), this.a.get_value(1));
    boolean bool = mc.field_71474_y.field_74347_j;
    mc.field_71474_y.field_74347_j = false;
    float f = mc.field_71474_y.field_74333_Y;
    mc.field_71474_y.field_74333_Y = Float.intBitsToFloat(9.5944102E8F ^ 0x7F33A8A0);
    if (this.top.get_value(true))
      paramEventRenderEntityModel.modelBase.func_78088_a(paramEventRenderEntityModel.entity, paramEventRenderEntityModel.limbSwing, paramEventRenderEntityModel.limbSwingAmount, paramEventRenderEntityModel.age, paramEventRenderEntityModel.headYaw, paramEventRenderEntityModel.headPitch, paramEventRenderEntityModel.scale); 
    if (this.mode.in("outline")) {
      RenderUtil.renderOne(this.width.get_value(1));
      paramEventRenderEntityModel.modelBase.func_78088_a(paramEventRenderEntityModel.entity, paramEventRenderEntityModel.limbSwing, paramEventRenderEntityModel.limbSwingAmount, paramEventRenderEntityModel.age, paramEventRenderEntityModel.headYaw, paramEventRenderEntityModel.headPitch, paramEventRenderEntityModel.scale);
      GlStateManager.func_187441_d(this.width.get_value(1));
      RenderUtil.renderTwo();
      paramEventRenderEntityModel.modelBase.func_78088_a(paramEventRenderEntityModel.entity, paramEventRenderEntityModel.limbSwing, paramEventRenderEntityModel.limbSwingAmount, paramEventRenderEntityModel.age, paramEventRenderEntityModel.headYaw, paramEventRenderEntityModel.headPitch, paramEventRenderEntityModel.scale);
      GlStateManager.func_187441_d(this.width.get_value(1));
      RenderUtil.renderThree();
      RenderUtil.renderFour(color);
      paramEventRenderEntityModel.modelBase.func_78088_a(paramEventRenderEntityModel.entity, paramEventRenderEntityModel.limbSwing, paramEventRenderEntityModel.limbSwingAmount, paramEventRenderEntityModel.age, paramEventRenderEntityModel.headYaw, paramEventRenderEntityModel.headPitch, paramEventRenderEntityModel.scale);
      GlStateManager.func_187441_d(this.width.get_value(1));
      RenderUtil.renderFive();
    } else {
      GL11.glPushMatrix();
      GL11.glPushAttrib(1048575);
      GL11.glPolygonMode(1028, 6913);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GlStateManager.func_179112_b(770, 771);
      GlStateManager.func_179131_c(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
      GlStateManager.func_187441_d(this.width.get_value(1));
      paramEventRenderEntityModel.modelBase.func_78088_a(paramEventRenderEntityModel.entity, paramEventRenderEntityModel.limbSwing, paramEventRenderEntityModel.limbSwingAmount, paramEventRenderEntityModel.age, paramEventRenderEntityModel.headYaw, paramEventRenderEntityModel.headPitch, paramEventRenderEntityModel.scale);
      GL11.glPopAttrib();
      GL11.glPopMatrix();
    } 
    if (!this.top.get_value(true))
      paramEventRenderEntityModel.modelBase.func_78088_a(paramEventRenderEntityModel.entity, paramEventRenderEntityModel.limbSwing, paramEventRenderEntityModel.limbSwingAmount, paramEventRenderEntityModel.age, paramEventRenderEntityModel.headYaw, paramEventRenderEntityModel.headPitch, paramEventRenderEntityModel.scale); 
    mc.field_71474_y.field_74347_j = bool;
    mc.field_71474_y.field_74333_Y = f;
    paramEventRenderEntityModel.cancel();
  }
  
  public void update() {
    (give up)null;
    if (this.rainbow_mode.get_value(true))
      cycle_rainbow(); 
  }
}
