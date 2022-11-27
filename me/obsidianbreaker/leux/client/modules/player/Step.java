package me.obsidianbreaker.leux.client.modules.player;

import give up;
import java.text.DecimalFormat;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class Step extends Module {
  public Setting height = create("Height", "StepHeight", 2.0D, 0.0D, 3.0D);
  
  public Setting mode = create("Mode", "StepMode", "Vanilla", combobox(new String[] { "Vanilla", "Default" }));
  
  public double get_n_normal() {
    (give up)null;
    mc.field_71439_g.field_70138_W = Float.intBitsToFloat(1.07395546E9F ^ 0x7F03427B);
    double d = -1.0D;
    AxisAlignedBB axisAlignedBB = mc.field_71439_g.func_174813_aQ().func_72317_d(0.0D, 0.05D, 0.0D).func_186662_g(0.05D);
    if (!mc.field_71441_e.func_184144_a((Entity)mc.field_71439_g, axisAlignedBB.func_72317_d(0.0D, 2.0D, 0.0D)).isEmpty())
      return 100.0D; 
    for (AxisAlignedBB axisAlignedBB1 : mc.field_71441_e.func_184144_a((Entity)mc.field_71439_g, axisAlignedBB)) {
      if (axisAlignedBB1.field_72337_e > d)
        d = axisAlignedBB1.field_72337_e; 
    } 
    return d - mc.field_71439_g.field_70163_u;
  }
  
  public String array_detail() {
    (give up)null;
    return this.mode.get_current_value();
  }
  
  public void update() {
    (give up)null;
    if (!mc.field_71439_g.field_70122_E || mc.field_71439_g.func_70617_f_() || mc.field_71439_g.func_70090_H() || mc.field_71439_g.func_180799_ab() || mc.field_71439_g.field_71158_b.field_78901_c || mc.field_71439_g.field_70145_X)
      return; 
    if (mc.field_71439_g.field_191988_bg == Float.intBitsToFloat(2.12779622E9F ^ 0x7ED39823) && mc.field_71439_g.field_70702_br == Float.intBitsToFloat(2.12468979E9F ^ 0x7EA4319F))
      return; 
    double d = get_n_normal();
    if (this.mode.in("Default")) {
      if (d < 0.0D || d > 2.0D)
        return; 
      if (d == 2.0D) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.42D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.78D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.63D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.51D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.9D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.21D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.45D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.43D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.func_70107_b(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 2.0D, mc.field_71439_g.field_70161_v);
      } 
      if (d == 1.5D) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.41999998688698D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.7531999805212D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.00133597911214D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.16610926093821D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.24918707874468D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.1707870772188D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.func_70107_b(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.0D, mc.field_71439_g.field_70161_v);
      } 
      if (d == 1.0D) {
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.41999998688698D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.7531999805212D, mc.field_71439_g.field_70161_v, mc.field_71439_g.field_70122_E));
        mc.field_71439_g.func_70107_b(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 1.0D, mc.field_71439_g.field_70161_v);
      } 
    } else if (this.mode.in("Vanilla")) {
      mc.field_71439_g.field_70138_W = Float.parseFloat((new DecimalFormat("#")).format(this.height.get_value(1.0D)));
    } 
  }
  
  public void disable() {
    (give up)null;
    mc.field_71439_g.field_70138_W = 0.5F;
  }
  
  public Step() {
    super(Category.player);
  }
}
