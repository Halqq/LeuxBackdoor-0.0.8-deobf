package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class NoEntityTrace extends Module {
  public boolean focus = false;
  
  public static boolean lambda$update$3(EntityLivingBase paramEntityLivingBase) {
    (give up)null;
    return !paramEntityLivingBase.field_70128_L;
  }
  
  public void update() {
    (give up)null;
    mc.field_71441_e.field_72996_f.stream().filter(NoEntityTrace::lambda$update$0).filter(NoEntityTrace::lambda$update$1).map(NoEntityTrace::lambda$update$2).filter(NoEntityTrace::lambda$update$3).forEach(this::process);
    RayTraceResult rayTraceResult = mc.field_71476_x;
    this.focus = (rayTraceResult.field_72313_a == RayTraceResult.Type.ENTITY);
  }
  
  public static boolean lambda$update$0(Entity paramEntity) {
    (give up)null;
    return paramEntity instanceof EntityLivingBase;
  }
  
  public static boolean lambda$update$1(Entity paramEntity) {
    (give up)null;
    return (mc.field_71439_g == paramEntity);
  }
  
  public static EntityLivingBase lambda$update$2(Entity paramEntity) {
    (give up)null;
    return (EntityLivingBase)paramEntity;
  }
  
  public void process(EntityLivingBase paramEntityLivingBase) {
    (give up)null;
    RayTraceResult rayTraceResult = paramEntityLivingBase.func_174822_a(6.0D, mc.func_184121_ak());
    if (this.focus && rayTraceResult.field_72313_a == RayTraceResult.Type.BLOCK) {
      BlockPos blockPos = rayTraceResult.func_178782_a();
      if (mc.field_71474_y.field_74312_F.func_151470_d())
        mc.field_71442_b.func_180512_c(blockPos, EnumFacing.UP); 
    } 
  }
  
  public NoEntityTrace() {
    super(Category.misc);
  }
}
