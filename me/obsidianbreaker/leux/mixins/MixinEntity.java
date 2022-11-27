package me.obsidianbreaker.leux.mixins;

import me.obsidianbreaker.leux.client.event.EventClientBus;
import me.obsidianbreaker.leux.client.event.events.EventEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({Entity.class})
public class MixinEntity {
  @Shadow
  public double field_70159_w;
  
  @Shadow
  public double field_70181_x;
  
  @Shadow
  public double field_70179_y;
  
  @Redirect(method = {"applyEntityCollision"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
  @Redirect(method = {"applyEntityCollision"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
  public void velocity(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3) {
    EventEntity.EventColision eventColision = new EventEntity.EventColision(paramEntity, paramDouble1, paramDouble2, paramDouble3);
    EventClientBus.EVENT_BUS.post(eventColision);
    if (eventColision.isCancelled())
      return; 
    paramEntity.field_70159_w += paramDouble1;
    paramEntity.field_70181_x += paramDouble2;
    paramEntity.field_70179_y += paramDouble3;
    paramEntity.field_70160_al = true;
  }
  
  @Shadow
  @Shadow
  public void func_70091_d(MoverType paramMoverType, double paramDouble1, double paramDouble2, double paramDouble3) {}
}
