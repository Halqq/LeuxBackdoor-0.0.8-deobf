package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import java.util.Objects;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventMove;
import me.obsidianbreaker.leux.client.event.events.EventPlayerJump;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.init.MobEffects;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;

public class Speed extends Module {
  @EventHandler
  public Listener player_move = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting auto_sprint = create("Auto Sprint", "StrafeSprint", true);
  
  public Setting auto_jump = create("Auto Jump", "StrafeAutoJump", false);
  
  public Setting backward = create("Backwards", "StrafeBackwards", true);
  
  public Setting bypass = create("Bypass", "StrafeBypass", true);
  
  public Setting on_water = create("On Water", "StrafeOnWater", true);
  
  @EventHandler
  public Listener on_jump = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting speed_mode = create("Mode", "StrafeMode", "Strafe", combobox(new String[] { "Strafe", "On Ground" }));
  
  public void lambda$new$1(EventMove paramEventMove) {
    (give up)null;
    if (Leux.get_module_manager().get_module_with_tag("ElytraFly").is_active())
      return; 
    if (this.speed_mode.in("On Ground"))
      return; 
    if ((mc.field_71439_g.func_70090_H() || mc.field_71439_g.func_180799_ab()) && !this.speed_mode.get_value(true))
      return; 
    if (mc.field_71439_g.func_70093_af() || mc.field_71439_g.func_70617_f_() || mc.field_71439_g.field_70134_J || mc.field_71439_g.func_180799_ab() || mc.field_71439_g.func_70090_H() || mc.field_71439_g.field_71075_bZ.field_75100_b)
      return; 
    float f1 = Float.intBitsToFloat(1.10223693E9F ^ 0x7F21D5C9);
    float f2 = mc.field_71439_g.field_71158_b.field_192832_b;
    float f3 = mc.field_71439_g.field_71158_b.field_78902_a;
    float f4 = mc.field_71439_g.field_70177_z;
    if (mc.field_71439_g.func_70644_a(MobEffects.field_76424_c)) {
      int i = ((PotionEffect)Objects.<PotionEffect>requireNonNull(mc.field_71439_g.func_70660_b(MobEffects.field_76424_c))).func_76458_c();
      f1 *= Float.intBitsToFloat(1.10077581E9F ^ 0x7E051807) * (i + 1);
    } 
    if (!this.bypass.get_value(true))
      f1 *= Float.intBitsToFloat(1.08935706E9F ^ 0x7F6E94A5); 
    if (f2 == Float.intBitsToFloat(2.1281207E9F ^ 0x7ED88BA3) && f3 == Float.intBitsToFloat(2.13428083E9F ^ 0x7F368A59)) {
      paramEventMove.set_x(0.0D);
      paramEventMove.set_z(0.0D);
    } else {
      if (f2 != Float.intBitsToFloat(2.09719603E9F ^ 0x7D00AC2F)) {
        if (f3 > Float.intBitsToFloat(2.13384358E9F ^ 0x7F2FDE57)) {
          f4 += ((f2 > Float.intBitsToFloat(2.12243354E9F ^ 0x7E81C43F)) ? -45 : 45);
        } else if (f3 < Float.intBitsToFloat(2.13195302E9F ^ 0x7F130565)) {
          f4 += ((f2 > Float.intBitsToFloat(2.1031264E9F ^ 0x7D5B298F)) ? 45 : -45);
        } 
        f3 = Float.intBitsToFloat(2.12509734E9F ^ 0x7EAA69AD);
        if (f2 > Float.intBitsToFloat(2.13179213E9F ^ 0x7F10911B)) {
          f2 = Float.intBitsToFloat(1.08880256E9F ^ 0x7F65CEED);
        } else if (f2 < Float.intBitsToFloat(2.1353655E9F ^ 0x7F471770)) {
          f2 = Float.intBitsToFloat(-1.05970061E9F ^ 0x7F56409E);
        } 
      } 
      double d1 = Math.cos(Math.toRadians((f4 + Float.intBitsToFloat(1.01525235E9F ^ 0x7E37859F))));
      double d2 = Math.sin(Math.toRadians((f4 + Float.intBitsToFloat(1.03588141E9F ^ 0x7F0A4BB1))));
      paramEventMove.set_x((f2 * f1) * d1 + (f3 * f1) * d2);
      paramEventMove.set_z((f2 * f1) * d2 - (f3 * f1) * d1);
    } 
    paramEventMove.cancel();
  }
  
  public void lambda$new$0(EventPlayerJump paramEventPlayerJump) {
    (give up)null;
    if (this.speed_mode.in("Strafe"))
      paramEventPlayerJump.cancel(); 
  }
  
  public Speed() {
    super(Category.movement);
  }
  
  public float get_rotation_yaw() {
    (give up)null;
    float f1 = mc.field_71439_g.field_70177_z;
    if (mc.field_71439_g.field_191988_bg < 0.0F)
      f1 += 180.0F; 
    float f2 = 1.0F;
    if (mc.field_71439_g.field_191988_bg < 0.0F) {
      f2 = -0.5F;
    } else if (mc.field_71439_g.field_191988_bg > 0.0F) {
      f2 = 0.5F;
    } 
    if (mc.field_71439_g.field_70702_br > 0.0F)
      f1 -= 90.0F * f2; 
    if (mc.field_71439_g.field_70702_br < 0.0F)
      f1 += 90.0F * f2; 
    return f1 * 0.017453292F;
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g.func_184218_aH())
      return; 
    if (mc.field_71439_g.func_70093_af())
      return; 
    if (mc.field_71439_g.field_70134_J)
      return; 
    if ((mc.field_71439_g.func_70090_H() || mc.field_71439_g.func_180799_ab()) && !this.on_water.get_value(true))
      return; 
    if (Leux.get_module_manager().get_module_with_tag("ElytraFly").is_active())
      return; 
    if (mc.field_71439_g.field_191988_bg != Float.intBitsToFloat(2.13411686E9F ^ 0x7F3409F1) || mc.field_71439_g.field_70702_br != Float.intBitsToFloat(2.12505254E9F ^ 0x7EA9BA99)) {
      if (mc.field_71439_g.field_191988_bg < Float.intBitsToFloat(2.11223616E9F ^ 0x7DE62A9F) && !this.backward.get_value(true))
        return; 
      if (this.auto_sprint.get_value(true))
        mc.field_71439_g.func_70031_b(true); 
      if (mc.field_71439_g.field_70122_E && this.speed_mode.in("Strafe")) {
        if (this.auto_jump.get_value(true))
          mc.field_71439_g.field_70181_x = 0.4050000011920929D; 
        float f = get_rotation_yaw() * Float.intBitsToFloat(1.13392154E9F ^ 0x7F18BEE9);
        mc.field_71439_g.field_70159_w -= (MathHelper.func_76126_a(f) * Float.intBitsToFloat(1.08302528E9F ^ 0x7EC16B8B));
        mc.field_71439_g.field_70179_y += (MathHelper.func_76134_b(f) * Float.intBitsToFloat(1.09606976E9F ^ 0x7F187E76));
      } else if (mc.field_71439_g.field_70122_E && this.speed_mode.in("On Ground")) {
        float f = get_rotation_yaw();
        mc.field_71439_g.field_70159_w -= (MathHelper.func_76126_a(f) * Float.intBitsToFloat(1.07390592E9F ^ 0x7E4E4C23));
        mc.field_71439_g.field_70179_y += (MathHelper.func_76134_b(f) * Float.intBitsToFloat(1.09601766E9F ^ 0x7F1F2A3D));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.4D, mc.field_71439_g.field_70161_v, false));
      } 
    } 
    if (mc.field_71474_y.field_74314_A.func_151470_d() && mc.field_71439_g.field_70122_E)
      mc.field_71439_g.field_70181_x = 0.4050000011920929D; 
  }
}
