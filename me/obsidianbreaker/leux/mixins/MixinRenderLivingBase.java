package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventRenderEntityModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({RenderLivingBase.class})
public abstract class MixinRenderLivingBase extends Render {
  protected MixinRenderLivingBase(RenderManager paramRenderManager, ModelBase paramModelBase, float paramFloat) {
    super(paramRenderManager);
  }
  
  @Redirect(method = {"renderModel"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
  @Redirect(method = {"renderModel"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
  private void renderModelHook(ModelBase paramModelBase, Entity paramEntity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    if (Leux.get_hack_manager().get_module_with_tag("Chams").is_active()) {
      EventRenderEntityModel eventRenderEntityModel = new EventRenderEntityModel(0, paramModelBase, paramEntity, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
      Leux.get_hack_manager().get_module_with_tag("Chams").on_render_model(eventRenderEntityModel);
      if (eventRenderEntityModel.isCancelled())
        return; 
    } 
    paramModelBase.func_78088_a(paramEntity, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);
  }
}
