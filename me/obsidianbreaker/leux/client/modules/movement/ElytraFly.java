package me.obsidianbreaker.leux.client.modules.movement;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import java.util.Objects;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.event.events.EventPlayerTravel;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MathUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.obsidianbreaker.leux.client.util.Timer;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;

public class ElytraFly extends Module {
  public Setting pitch_spoof = create("Pitch Spoof", "PitchSpoof", false);
  
  public Setting speed = create("Speed", "Speed", 1.8200000524520874D, 0.0D, 10.0D);
  
  public Setting mode = create("Mode", "Mode", "Normal", combobox(new String[] { "Normal", "Tarzan", "Superior", "Packet", "Control" }));
  
  @EventHandler
  public Listener packet_event = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting cancel_at_height = create("Cancel At Height", "CancelHeight", 5, 0, 10);
  
  public Setting cancel_in_water = create("Cancel In Water", "CancelWater", true);
  
  public Timer acceleration_reset_timer = new Timer();
  
  public int elytra_slot = -1;
  
  public Timer packet_timer = new Timer();
  
  public Setting glide_speed = create("Glide Speed", "GlideSpeed", 1.0D, 0.0D, 10.0D);
  
  public Setting instant_fly = create("Instant Fly", "InstaFly", true);
  
  public Setting down_speed = create("Down Speed", "DownSpeed", 1.8200000524520874D, 0.0D, 10.0D);
  
  @EventHandler
  public Listener on_travel = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Timer instant_fly_timer = new Timer();
  
  public Timer acceleration_timer = new Timer();
  
  public boolean send_message = false;
  
  public Setting accelerate = create("Accelerate", "Accelerate", true);
  
  public Setting v_acceleration_timer = create("Timer", "AccelerationTimer", 1000, 0, 10000);
  
  public Setting rotation_pitch = create("Rotation Pitch", "RotationPitch", 0.0D, -90.0D, 90.0D);
  
  public Setting equip_elytra = create("Equip Elytra", "EquipElytra", false);
  
  public void lambda$new$1(EventPacket paramEventPacket) {
    (give up)null;
    if (paramEventPacket.get_packet() instanceof CPacketPlayer && this.pitch_spoof.get_value(true)) {
      if (!mc.field_71439_g.func_184613_cA())
        return; 
      if (paramEventPacket.get_packet() instanceof CPacketPlayer.PositionRotation && this.pitch_spoof.get_value(true)) {
        CPacketPlayer.PositionRotation positionRotation = (CPacketPlayer.PositionRotation)paramEventPacket.get_packet();
        ((NetHandlerPlayClient)Objects.<NetHandlerPlayClient>requireNonNull(mc.func_147114_u())).func_147297_a((Packet)new CPacketPlayer.Position(positionRotation.field_149479_a, positionRotation.field_149477_b, positionRotation.field_149478_c, positionRotation.field_149474_g));
        paramEventPacket.cancel();
      } else if (paramEventPacket.get_packet() instanceof CPacketPlayer.Rotation && this.pitch_spoof.get_value(true)) {
        paramEventPacket.cancel();
      } 
    } 
  }
  
  public ElytraFly() {
    super(Category.movement);
  }
  
  public void Accelerate() {
    (give up)null;
    if (this.acceleration_reset_timer.passed(this.v_acceleration_timer.get_value(1))) {
      this.acceleration_reset_timer.reset();
      this.acceleration_timer.reset();
      this.send_message = false;
    } 
    float f = (float)this.speed.get_value(1.0D);
    double[] arrayOfDouble = MathUtil.directionSpeed(f);
    mc.field_71439_g.field_70181_x = -(this.glide_speed.get_value(1.0D) / 10000.0D);
    if (mc.field_71439_g.field_71158_b.field_78902_a != 0.0F || mc.field_71439_g.field_71158_b.field_192832_b != 0.0F) {
      mc.field_71439_g.field_70159_w = arrayOfDouble[0];
      mc.field_71439_g.field_70179_y = arrayOfDouble[1];
    } else {
      mc.field_71439_g.field_70159_w = 0.0D;
      mc.field_71439_g.field_70179_y = 0.0D;
    } 
    if (mc.field_71439_g.field_71158_b.field_78899_d)
      mc.field_71439_g.field_70181_x = -this.down_speed.get_value(1.0D); 
    mc.field_71439_g.field_184618_aE = 0.0F;
    mc.field_71439_g.field_70721_aZ = 0.0F;
    mc.field_71439_g.field_184619_aG = 0.0F;
  }
  
  public void enable() {
    (give up)null;
    this.elytra_slot = -1;
    if (this.equip_elytra.get_value(true) && mc.field_71439_g != null && mc.field_71439_g.func_184582_a(EntityEquipmentSlot.CHEST).func_77973_b() != Items.field_185160_cR) {
      byte b = 0;
      while (true) {
        44;
        ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70301_a(b);
        if (itemStack.func_190926_b() || itemStack.func_77973_b() != Items.field_185160_cR)
          continue; 
        itemStack.func_77973_b();
        this.elytra_slot = b;
        if (this.elytra_slot != -1) {
          b = (mc.field_71439_g.func_184582_a(EntityEquipmentSlot.CHEST).func_77973_b() != Items.field_190931_a) ? 1 : 0;
          mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, this.elytra_slot, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
          mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, 6, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
        } 
        break;
      } 
    } 
  }
  
  public void handle_control_mode(EventPlayerTravel paramEventPlayerTravel) {
    (give up)null;
    double[] arrayOfDouble = MathUtil.directionSpeed(this.speed.get_value(1.0D));
    if (mc.field_71439_g.field_71158_b.field_78902_a != 0.0F || mc.field_71439_g.field_71158_b.field_192832_b != 0.0F) {
      mc.field_71439_g.field_70159_w = arrayOfDouble[0];
      mc.field_71439_g.field_70179_y = arrayOfDouble[1];
      mc.field_71439_g.field_70159_w -= mc.field_71439_g.field_70159_w * (Math.abs(mc.field_71439_g.field_70125_A) + 90.0F) / 90.0D - mc.field_71439_g.field_70159_w;
      mc.field_71439_g.field_70179_y -= mc.field_71439_g.field_70179_y * (Math.abs(mc.field_71439_g.field_70125_A) + 90.0F) / 90.0D - mc.field_71439_g.field_70179_y;
    } else {
      mc.field_71439_g.field_70159_w = 0.0D;
      mc.field_71439_g.field_70179_y = 0.0D;
    } 
    mc.field_71439_g.field_70181_x = -MathUtil.degToRad(mc.field_71439_g.field_70125_A) * mc.field_71439_g.field_71158_b.field_192832_b;
    mc.field_71439_g.field_184618_aE = 0.0F;
    mc.field_71439_g.field_70721_aZ = 0.0F;
    mc.field_71439_g.field_184619_aG = 0.0F;
    paramEventPlayerTravel.cancel();
  }
  
  public void lambda$new$0(Object paramObject) {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (mc.field_71439_g.func_184582_a(EntityEquipmentSlot.CHEST).func_77973_b() != Items.field_185160_cR)
      return; 
    if (!mc.field_71439_g.func_184613_cA()) {
      if (!mc.field_71439_g.field_70122_E && this.instant_fly.get_value(true)) {
        if (!this.instant_fly_timer.passed(1319076718L ^ 0x4E9F8086L))
          return; 
        this.instant_fly_timer.reset();
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketEntityAction((Entity)mc.field_71439_g, CPacketEntityAction.Action.START_FALL_FLYING));
      } 
      return;
    } 
    if (this.mode.in("Normal") || this.mode.in("Tarzan") || this.mode.in("Packet")) {
      handle_normal_mode_elytra((EventPlayerTravel)paramObject);
    } else if (this.mode.in("Superior")) {
      handle_immediate_mode_elytra((EventPlayerTravel)paramObject);
    } else if (this.mode.in("Control")) {
      handle_control_mode((EventPlayerTravel)paramObject);
    } 
  }
  
  public void handle_immediate_mode_elytra(EventPlayerTravel paramEventPlayerTravel) {
    (give up)null;
    if (mc.field_71439_g.field_71158_b.field_78901_c) {
      double d = Math.sqrt(mc.field_71439_g.field_70159_w * mc.field_71439_g.field_70159_w + mc.field_71439_g.field_70179_y * mc.field_71439_g.field_70179_y);
      if (d > 1.0D)
        return; 
      double[] arrayOfDouble1 = MathUtil.directionSpeedNoForward(this.speed.get_value(1.0D));
      mc.field_71439_g.field_70159_w = arrayOfDouble1[0];
      mc.field_71439_g.field_70181_x = -(this.glide_speed.get_value(1.0D) / 10000.0D);
      mc.field_71439_g.field_70179_y = arrayOfDouble1[1];
      paramEventPlayerTravel.cancel();
      return;
    } 
    mc.field_71439_g.func_70016_h(0.0D, 0.0D, 0.0D);
    paramEventPlayerTravel.cancel();
    double[] arrayOfDouble = MathUtil.directionSpeed(this.speed.get_value(1.0D));
    if (mc.field_71439_g.field_71158_b.field_78902_a != 0.0F || mc.field_71439_g.field_71158_b.field_192832_b != 0.0F) {
      mc.field_71439_g.field_70159_w = arrayOfDouble[0];
      mc.field_71439_g.field_70181_x = -(this.glide_speed.get_value(1.0D) / 10000.0D);
      mc.field_71439_g.field_70179_y = arrayOfDouble[1];
    } 
    if (mc.field_71439_g.field_71158_b.field_78899_d)
      mc.field_71439_g.field_70181_x = -this.down_speed.get_value(1.0D); 
    mc.field_71439_g.field_184618_aE = 0.0F;
    mc.field_71439_g.field_70721_aZ = 0.0F;
    mc.field_71439_g.field_184619_aG = 0.0F;
  }
  
  public void handle_normal_mode_elytra(EventPlayerTravel paramEventPlayerTravel) {
    (give up)null;
    double d = mc.field_71439_g.field_70163_u;
    if (d <= this.cancel_at_height.get_value(1)) {
      if (!this.send_message) {
        MessageUtil.send_client_message(ChatFormatting.RED + "WARNING, you must scaffold up or use fireworks, as YHeight <= CancelAtHeight!");
        this.send_message = true;
      } 
      return;
    } 
    boolean bool2 = (mc.field_71439_g.field_71158_b.field_192832_b > Float.intBitsToFloat(2.13031373E9F ^ 0x7EFA023B) || mc.field_71439_g.field_71158_b.field_78902_a > Float.intBitsToFloat(2.12605645E9F ^ 0x7EB90C01)) ? true : false;
    boolean bool1 = (!mc.field_71439_g.func_70090_H() && !mc.field_71439_g.func_180799_ab() && this.cancel_in_water.get_value(true)) ? true : false;
    if (mc.field_71439_g.field_71158_b.field_78901_c) {
      paramEventPlayerTravel.cancel();
      Accelerate();
      return;
    } 
    if (mc.field_71439_g.field_70125_A <= this.rotation_pitch.get_value(1) || this.mode.in("Tarzan"));
    paramEventPlayerTravel.cancel();
    Accelerate();
  }
  
  public void disable() {
    (give up)null;
    if (mc.field_71439_g == null)
      return; 
    if (this.elytra_slot != -1) {
      boolean bool = (!mc.field_71439_g.field_71071_by.func_70301_a(this.elytra_slot).func_190926_b() || mc.field_71439_g.field_71071_by.func_70301_a(this.elytra_slot).func_77973_b() != Items.field_190931_a) ? true : false;
      mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, 6, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
      mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, this.elytra_slot, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
      mc.field_71442_b.func_187098_a(mc.field_71439_g.field_71069_bz.field_75152_c, 6, 0, ClickType.PICKUP, (EntityPlayer)mc.field_71439_g);
    } 
  }
}
