package me.obsidianbreaker.leux.client.modules.combat;

import give up;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.world.World;

public class AntiChainPop extends Module {
  @EventHandler
  public Listener packet_event = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting module = create("Module", "Module", "Burrow", combobox(new String[] { "Burrow", "Surround" }));
  
  public AntiChainPop() {
    super(Category.combat);
  }
  
  public void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (paramReceivePacket.get_packet() instanceof SPacketEntityStatus) {
      SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)paramReceivePacket.get_packet();
      if (sPacketEntityStatus.func_149160_c() == 35) {
        Entity entity = sPacketEntityStatus.func_149161_a((World)mc.field_71441_e);
        if (entity != mc.field_71439_g)
          return; 
        if (this.module.in("Burrow")) {
          Leux.get_module_manager().get_module_with_tag("Burrow").set_enable();
        } else {
          Leux.get_module_manager().get_module_with_tag("Surround").set_enable();
        } 
        MessageUtil.send_client_message("Attempting stop chain popping");
      } 
    } 
  }
}
