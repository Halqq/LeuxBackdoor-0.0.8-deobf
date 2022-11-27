package me.obsidianbreaker.leux.client.modules.movement;

import give up;
import me.obsidianbreaker.leux.client.event.EventCancellable;
import me.obsidianbreaker.leux.client.event.events.EventEntity;
import me.obsidianbreaker.leux.client.event.events.EventMove;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;

public class Velocity extends Module {
  public Setting nopush = create("NoPush", "NoPush", false);
  
  @EventHandler
  public Listener player_move = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  @EventHandler
  public Listener explosion = new Listener(Velocity::lambda$new$2, new java.util.function.Predicate[0]);
  
  @EventHandler
  public Listener damage = new Listener(Velocity::lambda$new$1, new java.util.function.Predicate[0]);
  
  public static void lambda$new$2(EventEntity.EventColision paramEventColision) {
    (give up)null;
    if (paramEventColision.get_entity() == mc.field_71439_g)
      paramEventColision.cancel(); 
  }
  
  public static void lambda$new$1(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (paramReceivePacket.get_era() == EventCancellable.Era.EVENT_PRE)
      if (paramReceivePacket.get_packet() instanceof SPacketEntityVelocity) {
        SPacketEntityVelocity sPacketEntityVelocity = (SPacketEntityVelocity)paramReceivePacket.get_packet();
        if (sPacketEntityVelocity.func_149412_c() == mc.field_71439_g.func_145782_y()) {
          paramReceivePacket.cancel();
          sPacketEntityVelocity.field_149415_b = (int)(sPacketEntityVelocity.field_149415_b * Float.intBitsToFloat(2.1387753E9F ^ 0x7F7B1EC5));
          sPacketEntityVelocity.field_149416_c = (int)(sPacketEntityVelocity.field_149416_c * Float.intBitsToFloat(2.1174999E9F ^ 0x7E367BDB));
          sPacketEntityVelocity.field_149414_d = (int)(sPacketEntityVelocity.field_149414_d * Float.intBitsToFloat(2.1363785E9F ^ 0x7F568C4D));
        } 
      } else if (paramReceivePacket.get_packet() instanceof SPacketExplosion) {
        paramReceivePacket.cancel();
        SPacketExplosion sPacketExplosion = (SPacketExplosion)paramReceivePacket.get_packet();
        sPacketExplosion.field_149152_f *= Float.intBitsToFloat(2.13237466E9F ^ 0x7F197477);
        sPacketExplosion.field_149153_g *= Float.intBitsToFloat(2.13123827E9F ^ 0x7F081D7D);
        sPacketExplosion.field_149159_h *= Float.intBitsToFloat(2.13603418E9F ^ 0x7F514BAA);
      }  
  }
  
  public Velocity() {
    super(Category.movement);
  }
  
  public void lambda$new$0(EventMove paramEventMove) {
    (give up)null;
    if (mc.field_71439_g.func_70093_af() || mc.field_71439_g.func_70617_f_() || mc.field_71439_g.field_70134_J || mc.field_71439_g.func_180799_ab() || mc.field_71439_g.func_70090_H() || mc.field_71439_g.field_71075_bZ.field_75100_b)
      return; 
    if (this.nopush.get_value(true)) {
      float f1 = mc.field_71439_g.field_71158_b.field_192832_b;
      float f2 = mc.field_71439_g.field_71158_b.field_78902_a;
      if (f1 == 0.0F && f2 == 0.0F) {
        paramEventMove.set_x(0.0D);
        paramEventMove.set_z(0.0D);
      } 
      paramEventMove.cancel();
    } 
  }
}
