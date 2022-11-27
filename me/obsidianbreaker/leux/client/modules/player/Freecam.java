package me.obsidianbreaker.leux.client.modules.player;

import give up;
import me.obsidianbreaker.leux.client.event.events.EventMove;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MathUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;

public class Freecam extends Module {
  public EntityOtherPlayerMP soul;
  
  public double z;
  
  @EventHandler
  public Listener listener_move = new Listener(Freecam::lambda$new$0, new java.util.function.Predicate[0]);
  
  public double x;
  
  public Setting speed_moveme = create("Speed Movement", "FreecamSpeedMovement", 0.4D, 0.0D, 2.0D);
  
  public float yaw;
  
  public double y;
  
  public float pitch;
  
  @EventHandler
  public Listener listener_packet = new Listener(Freecam::lambda$new$2, new java.util.function.Predicate[0]);
  
  public Entity riding_entity;
  
  public boolean is_riding;
  
  @EventHandler
  public Listener listener_push = new Listener(Freecam::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting speed_updown = create("Speed Up/Down", "FreecamSpeedUpDown", 0.4D, 0.0D, 2.0D);
  
  public static void lambda$new$0(EventMove paramEventMove) {
    (give up)null;
    mc.field_71439_g.field_70145_X = true;
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g != null && mc.field_71441_e != null) {
      mc.field_71439_g.field_70145_X = true;
      mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
      mc.field_71439_g.field_71155_g = 5000.0F;
      mc.field_71439_g.field_70747_aH = 0.5F;
      double[] arrayOfDouble = MathUtil.movement_speed(this.speed_moveme.get_value(1.0D) / 2.0D);
      if (mc.field_71439_g.field_71158_b.field_78902_a != 0.0F || mc.field_71439_g.field_71158_b.field_192832_b != 0.0F) {
        mc.field_71439_g.field_70159_w = arrayOfDouble[0];
        mc.field_71439_g.field_70179_y = arrayOfDouble[1];
      } else {
        mc.field_71439_g.field_70159_w = 0.0D;
        mc.field_71439_g.field_70179_y = 0.0D;
      } 
      mc.field_71439_g.func_70031_b(false);
      if (mc.field_71474_y.field_74314_A.func_151470_d())
        mc.field_71439_g.field_70181_x += this.speed_updown.get_value(1.0D); 
      if (mc.field_71474_y.field_74311_E.func_151470_d())
        mc.field_71439_g.field_70181_x -= this.speed_updown.get_value(1.0D); 
    } 
  }
  
  public void disable() {
    (give up)null;
    mc.field_71439_g.func_70080_a(this.x, this.y, this.z, this.yaw, this.pitch);
    mc.field_71441_e.func_73028_b(-100);
    this.soul = null;
    this.x = 0.0D;
    this.y = 0.0D;
    this.z = 0.0D;
    this.yaw = 0.0F;
    this.pitch = 0.0F;
    mc.field_71439_g.field_70159_w = mc.field_71439_g.field_70181_x = mc.field_71439_g.field_70179_y = 0.0D;
    if (mc.field_71439_g != null && mc.field_71441_e != null && this.is_riding)
      mc.field_71439_g.func_184205_a(this.riding_entity, true); 
  }
  
  public Freecam() {
    super(Category.player);
  }
  
  public static void lambda$new$1(PlayerSPPushOutOfBlocksEvent paramPlayerSPPushOutOfBlocksEvent) {
    (give up)null;
    paramPlayerSPPushOutOfBlocksEvent.setCanceled(true);
  }
  
  public void enable() {
    (give up)null;
    if (mc.field_71439_g != null && mc.field_71441_e != null) {
      this.is_riding = (mc.field_71439_g.func_184187_bx() != null);
      if (mc.field_71439_g.func_184187_bx() == null) {
        this.x = mc.field_71439_g.field_70165_t;
        this.y = mc.field_71439_g.field_70163_u;
        this.z = mc.field_71439_g.field_70161_v;
      } else {
        this.riding_entity = mc.field_71439_g.func_184187_bx();
        mc.field_71439_g.func_184210_p();
      } 
      this.pitch = mc.field_71439_g.field_70125_A;
      this.yaw = mc.field_71439_g.field_70177_z;
      this.soul = new EntityOtherPlayerMP((World)mc.field_71441_e, mc.func_110432_I().func_148256_e());
      this.soul.func_82149_j((Entity)mc.field_71439_g);
      this.soul.field_70759_as = mc.field_71439_g.field_70759_as;
      mc.field_71441_e.func_73027_a(-100, (Entity)this.soul);
      mc.field_71439_g.field_70145_X = true;
    } 
  }
  
  public static void lambda$new$2(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketPlayer || paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketInput)
      paramSendPacket.cancel(); 
  }
}
