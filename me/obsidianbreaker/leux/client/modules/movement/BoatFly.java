package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import java.util.Objects;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.event.events.EventPlayerTravel;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.PlayerUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;

public class BoatFly extends Module {
  public Setting vSpeed = create("VSpeed", "VSpeed", 1.0D, 0.10000000149011612D, 10.0D);
  
  public Setting tickDelay = create("TickDelay", "TickDelay", 1, 0, 20);
  
  public PlayerUtil util = new PlayerUtil();
  
  public Setting gSpeed = create("GSpeed", "GSpeed", 0.10000000149011612D, 0.0D, 1.0D);
  
  @EventHandler
  public Listener onSendPacket = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  @EventHandler
  public Listener onReceivePacket = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting hSpeed = create("HSpeed", "HSpeed", 1.0D, 0.10000000149011612D, 10.0D);
  
  public Setting fixYaw = create("FixYaw", "FixYaw", true);
  
  public Setting bypass = create("Bypass", "Bypass", false);
  
  @EventHandler
  public Listener onTravel = new Listener(this::lambda$new$2, new java.util.function.Predicate[0]);
  
  public Setting gravity = create("Gravity", "Gravity", true);
  
  public static boolean $assertionsDisabled;
  
  public int ticks;
  
  public void lambda$new$0(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (mc.field_71439_g == null || !mc.field_71439_g.func_184218_aH() || !this.bypass.get_value(true))
      return; 
    if (paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketVehicleMove && this.ticks++ >= this.tickDelay.get_value(1) + 1) {
      mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketUseEntity(Objects.<Entity>requireNonNull(mc.field_71439_g.func_184187_bx()), EnumHand.MAIN_HAND));
      this.ticks = 0;
      return;
    } 
    if (paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketPlayer.Rotation || paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketInput)
      paramSendPacket.cancel(); 
  }
  
  public void lambda$new$1(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (mc.field_71439_g == null || !mc.field_71439_g.func_184218_aH() || !this.bypass.get_value(true))
      return; 
    if (paramReceivePacket.get_packet() instanceof net.minecraft.network.play.server.SPacketMoveVehicle || paramReceivePacket.get_packet() instanceof net.minecraft.network.play.server.SPacketPlayerPosLook)
      paramReceivePacket.cancel(); 
  }
  
  public BoatFly() {
    super(Category.movement);
  }
  
  public void lambda$new$2(EventPlayerTravel paramEventPlayerTravel) {
    (give up)null;
    if (mc.field_71439_g == null || !mc.field_71439_g.func_184218_aH())
      return; 
    Entity entity = mc.field_71439_g.func_184187_bx();
    if (this.fixYaw.get_value(true)) {
      assert false;
      entity.field_70177_z = mc.field_71439_g.field_70177_z;
    } 
    assert false;
    entity.func_189654_d(!this.gravity.get_value(true));
    if (mc.field_71439_g.field_71158_b.field_192832_b != Float.intBitsToFloat(2.12990285E9F ^ 0x7EF3BD3B) || mc.field_71439_g.field_71158_b.field_78902_a != Float.intBitsToFloat(2.11920154E9F ^ 0x7E5072EB))
      this.util.setBoatSpeed(this.hSpeed.get_value(1.0D), entity); 
    entity.field_70181_x = mc.field_71439_g.field_71158_b.field_78899_d ? -this.vSpeed.get_value(1.0D) : ((mc.field_71439_g.field_70173_aa % 2 != 0) ? (-this.gSpeed.get_value(1.0D) / 10.0D) : (mc.field_71439_g.field_71158_b.field_78901_c ? this.vSpeed.get_value(1.0D) : (this.gSpeed.get_value(1.0D) / 10.0D)));
    paramEventPlayerTravel.cancel();
  }
}
