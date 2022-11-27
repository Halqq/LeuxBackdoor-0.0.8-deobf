package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber({Side.CLIENT})
public class LongJump extends Module {
  public Setting packet = create("Packet", "LGPacket", false);
  
  public static boolean boostable;
  
  public Setting toggle = create("Toggle", "LGToggle", false);
  
  public static boolean jumped = false;
  
  public Setting speed = create("Speed", "LGSpeed", 30.0D, 1.0D, 100.0D);
  
  @SubscribeEvent
  @SubscribeEvent
  public static void onJump(LivingEvent.LivingJumpEvent paramLivingJumpEvent) {
    (give up)null;
    if (mc.field_71439_g != null && mc.field_71441_e != null && paramLivingJumpEvent.getEntity() == mc.field_71439_g && (mc.field_71439_g.field_71158_b.field_192832_b != Float.intBitsToFloat(2.13588685E9F ^ 0x7F4F0BD3) || mc.field_71439_g.field_71158_b.field_78902_a != Float.intBitsToFloat(2.13760589E9F ^ 0x7F6946CE))) {
      jumped = true;
      boostable = true;
    } 
  }
  
  public double getDirection() {
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
    return Math.toRadians(f1);
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null || mc.field_71441_e == null)
      return; 
    if (jumped) {
      if (mc.field_71439_g.field_70122_E || mc.field_71439_g.field_71075_bZ.field_75100_b) {
        jumped = false;
        mc.field_71439_g.field_70159_w = 0.0D;
        mc.field_71439_g.field_70179_y = 0.0D;
        if (this.packet.get_value(true)) {
          mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
          mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t + mc.field_71439_g.field_70159_w, 0.0D, mc.field_71439_g.field_70161_v + mc.field_71439_g.field_70179_y, mc.field_71439_g.field_70122_E));
        } 
        if (!this.toggle.get_value(true))
          toggle(); 
        return;
      } 
      if (mc.field_71439_g.field_71158_b.field_192832_b == Float.intBitsToFloat(2.12538189E9F ^ 0x7EAEC0F7) && mc.field_71439_g.field_71158_b.field_78902_a == Float.intBitsToFloat(2.11084045E9F ^ 0x7DD0DE4F))
        return; 
      double d = getDirection();
      mc.field_71439_g.field_70159_w = -Math.sin(d) * ((float)Math.sqrt(mc.field_71439_g.field_70159_w * mc.field_71439_g.field_70159_w + mc.field_71439_g.field_70179_y * mc.field_71439_g.field_70179_y) * (boostable ? (this.speed.get_value(0) / Float.intBitsToFloat(1.04563277E9F ^ 0x7F731713)) : Float.intBitsToFloat(1.09971981E9F ^ 0x7E0C6457)));
      mc.field_71439_g.field_70179_y = Math.cos(d) * ((float)Math.sqrt(mc.field_71439_g.field_70159_w * mc.field_71439_g.field_70159_w + mc.field_71439_g.field_70179_y * mc.field_71439_g.field_70179_y) * (boostable ? (this.speed.get_value(0) / Float.intBitsToFloat(1.07176102E9F ^ 0x7EC1C679)) : Float.intBitsToFloat(1.08521114E9F ^ 0x7F2F01F4)));
      boostable = false;
      if (!this.toggle.get_value(true))
        toggle(); 
    } 
    if (mc.field_71439_g.field_71158_b.field_192832_b == Float.intBitsToFloat(2.11466035E9F ^ 0x7E0B27FB) && mc.field_71439_g.field_71158_b.field_78902_a == Float.intBitsToFloat(2.13849779E9F ^ 0x7F76E2DF) && jumped) {
      mc.field_71439_g.field_70159_w = 0.0D;
      mc.field_71439_g.field_70179_y = 0.0D;
    } 
  }
  
  public LongJump() {
    super(Category.movement);
  }
  
  static {
    boostable = false;
  }
}
