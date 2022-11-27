package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventCollisionBoxToList;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.EntityUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class Jesus extends Module {
  public static AxisAlignedBB WATER_WALK_AA = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.99D, 1.0D);
  
  @EventHandler
  public Listener listener_collision = new Listener(Jesus::lambda$new$0, new java.util.function.Predicate[0]);
  
  @EventHandler
  public Listener listener = new Listener(Jesus::lambda$new$1, new java.util.function.Predicate[0]);
  
  public static void lambda$new$0(EventCollisionBoxToList paramEventCollisionBoxToList) {
    (give up)null;
    if (mc.field_71439_g != null && paramEventCollisionBoxToList.getBlock() instanceof net.minecraft.block.BlockLiquid && (EntityUtil.isDrivenByPlayer(paramEventCollisionBoxToList.getEntity()) || paramEventCollisionBoxToList.getEntity() == mc.field_71439_g) && !(paramEventCollisionBoxToList.getEntity() instanceof net.minecraft.entity.item.EntityBoat) && !mc.field_71439_g.func_70093_af() && mc.field_71439_g.field_70143_R < Float.intBitsToFloat(1.05254061E9F ^ 0x7EFC7EDB) && !EntityUtil.isInWater((Entity)mc.field_71439_g) && (EntityUtil.isAboveWater((Entity)mc.field_71439_g, false) || EntityUtil.isAboveWater(mc.field_71439_g.func_184187_bx(), false)) && isAboveBlock((Entity)mc.field_71439_g, paramEventCollisionBoxToList.getPos())) {
      AxisAlignedBB axisAlignedBB = WATER_WALK_AA.func_186670_a(paramEventCollisionBoxToList.getPos());
      if (paramEventCollisionBoxToList.getEntityBox().func_72326_a(axisAlignedBB))
        paramEventCollisionBoxToList.getCollidingBoxes().add(axisAlignedBB); 
      paramEventCollisionBoxToList.cancel();
    } 
  }
  
  public static boolean isAboveLand(Entity paramEntity) {
    (give up)null;
    return false;
  }
  
  public Jesus() {
    super(Category.movement);
  }
  
  public static void lambda$new$1(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (paramSendPacket.get_packet() instanceof CPacketPlayer && EntityUtil.isAboveWater((Entity)mc.field_71439_g, true) && !EntityUtil.isInWater((Entity)mc.field_71439_g) && !isAboveLand((Entity)mc.field_71439_g)) {
      int i = mc.field_71439_g.field_70173_aa % 2;
      CPacketPlayer cPacketPlayer = (CPacketPlayer)paramSendPacket.get_packet();
      cPacketPlayer.field_149477_b += 0.02D;
    } 
  }
  
  public void update() {
    (give up)null;
    if (Leux.get_module_manager().get_module_with_tag("Freecam").is_disabled() && EntityUtil.isInWater((Entity)mc.field_71439_g) && !mc.field_71439_g.func_70093_af()) {
      mc.field_71439_g.field_70181_x = 0.1D;
      if (mc.field_71439_g.func_184187_bx() != null && !(mc.field_71439_g.func_184187_bx() instanceof net.minecraft.entity.item.EntityBoat))
        (mc.field_71439_g.func_184187_bx()).field_70181_x = 0.3D; 
    } 
  }
  
  public static boolean isAboveBlock(Entity paramEntity, BlockPos paramBlockPos) {
    (give up)null;
    return (paramEntity.field_70163_u >= paramBlockPos.func_177956_o());
  }
}
