package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;

public class Criticals extends Module {
  @EventHandler
  public Listener listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting mode = create("Mode", "Mode", "Packet", combobox(new String[] { "Packet", "Jump" }));
  
  public Criticals() {
    super(Category.combat);
  }
  
  public String array_detail() {
    (give up)null;
    return this.mode.get_current_value();
  }
  
  public void lambda$new$0(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (paramSendPacket.get_packet() instanceof CPacketUseEntity && ((mc.field_71439_g.func_184614_ca().func_77973_b() instanceof net.minecraft.item.ItemSword && mc.field_71439_g.func_184592_cb().func_77973_b() instanceof net.minecraft.item.ItemAppleGold) || (Leux.get_module_manager().get_module_with_tag("CrystalAuraOld").is_disabled() && Leux.get_module_manager().get_module_with_tag("CrystalAuraNew").is_disabled()))) {
      CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)paramSendPacket.get_packet();
      if (cPacketUseEntity.func_149565_c() == CPacketUseEntity.Action.ATTACK && mc.field_71439_g.field_70122_E)
        if (this.mode.in("Packet")) {
          mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + 0.10000000149011612D, mc.field_71439_g.field_70161_v, false));
          mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketPlayer.Position(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u, mc.field_71439_g.field_70161_v, false));
        } else if (this.mode.in("Jump")) {
          mc.field_71439_g.func_70664_aZ();
        }  
    } 
  }
}
