package me.obsidianbreaker.leux.mixins;

import java.awt.Color;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.util.Wrapper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderEnderCrystal.class})
public abstract class MixinEnderCrystal {
  @Shadow
  public ModelBase field_76995_b;
  
  @Shadow
  public ModelBase field_188316_g;
  
  @Final
  @Shadow
  private static ResourceLocation field_110787_a;
  
  @Shadow
  @Shadow
  public abstract void func_76986_a(EntityEnderCrystal paramEntityEnderCrystal, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2);
  
  @Redirect(method = {"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
  @Redirect(method = {"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
  private void render1(ModelBase paramModelBase, Entity paramEntity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    if (Leux.get_hack_manager().get_module_with_tag("Chams").is_active() && Leux.get_setting_manager().get_setting_with_tag("Chams", "Crystals").get_value(true))
      return; 
    paramModelBase.func_78088_a(paramEntity, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
  }
  
  @Redirect(method = {"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", ordinal = 1))
  @Redirect(method = {"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", ordinal = 1))
  private void render2(ModelBase paramModelBase, Entity paramEntity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    if (Leux.get_hack_manager().get_module_with_tag("Chams").is_active() && Leux.get_setting_manager().get_setting_with_tag("Chams", "Crystals").get_value(true))
      return; 
    paramModelBase.func_78088_a(paramEntity, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
  }
  
  @Inject(method = {"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at = {@At("RETURN")}, cancellable = true)
  @Inject(method = {"doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V"}, at = {@At("RETURN")}, cancellable = true)
  public void IdoRender(EntityEnderCrystal paramEntityEnderCrystal, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, CallbackInfo paramCallbackInfo) {
    if (Leux.get_hack_manager().get_module_with_tag("Chams").is_active() && Leux.get_setting_manager().get_setting_with_tag("Chams", "Crystals").get_value(true)) {
      Color color = new Color(Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsR").get_value(1), Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsG").get_value(1), Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsB").get_value(1));
      GL11.glPushMatrix();
      float f1 = paramEntityEnderCrystal.field_70261_a + paramFloat2;
      GlStateManager.func_179137_b(paramDouble1, paramDouble2, paramDouble3);
      (Wrapper.getMinecraft().func_175598_ae()).field_78724_e.func_110577_a(field_110787_a);
      float f2 = MathHelper.func_76126_a(f1 * 0.2F) / 2.0F + 0.5F;
      f2 += f2 * f2;
      GL11.glEnable(32823);
      GL11.glPolygonOffset(1.0F, -1.0E7F);
      GL11.glPushAttrib(1048575);
      if (!Leux.get_setting_manager().get_setting_with_tag("Chams", "Lines").get_value(true)) {
        GL11.glPolygonMode(1028, 6914);
      } else {
        GL11.glPolygonMode(1028, 6913);
      } 
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, Leux.get_setting_manager().get_setting_with_tag("Chams", "ChamsA").get_value(1) / 255.0F);
      if (Leux.get_setting_manager().get_setting_with_tag("Chams", "Lines").get_value(true))
        GL11.glLineWidth((float)Leux.get_setting_manager().get_setting_with_tag("Chams", "Width").get_value(1.0D)); 
      if (paramEntityEnderCrystal.func_184520_k()) {
        this.field_76995_b.func_78088_a((Entity)paramEntityEnderCrystal, 0.0F, f1 * 3.0F, f2 * 0.2F, 0.0F, 0.0F, 0.0625F);
      } else {
        this.field_188316_g.func_78088_a((Entity)paramEntityEnderCrystal, 0.0F, f1 * 3.0F, f2 * 0.2F, 0.0F, 0.0F, 0.0625F);
      } 
      GL11.glPopAttrib();
      GL11.glPolygonOffset(1.0F, 100000.0F);
      GL11.glDisable(32823);
      GL11.glPopMatrix();
    } 
  }
}
