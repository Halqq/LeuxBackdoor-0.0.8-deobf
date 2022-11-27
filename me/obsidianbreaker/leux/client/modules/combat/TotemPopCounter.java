package me.obsidianbreaker.leux.client.modules.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import give up;
import java.util.HashMap;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.FriendUtil;
import me.obsidianbreaker.leux.client.util.MessageUtil;
import me.obsidianbreaker.leux.client.util.Notification;
import me.obsidianbreaker.leux.client.util.NotificationUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.world.World;

public class TotemPopCounter extends Module {
  public static ChatFormatting reset;
  
  public static HashMap totem_pop_counter = new HashMap<>();
  
  @EventHandler
  public Listener packet_event = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public static ChatFormatting bold;
  
  public static ChatFormatting green;
  
  public static ChatFormatting red = ChatFormatting.RED;
  
  public boolean should_notify;
  
  public TotemPopCounter() {
    super(Category.combat);
  }
  
  public void update() {
    (give up)null;
    this.should_notify = Leux.get_setting_manager().get_setting_with_tag("HUD", "NotificationTotem").get_value(true);
    for (EntityPlayer entityPlayer : mc.field_71441_e.field_73010_i) {
      if (totem_pop_counter.containsKey(entityPlayer.func_70005_c_()) && (entityPlayer.field_70128_L || entityPlayer.func_110143_aJ() <= Float.intBitsToFloat(2.12261082E9F ^ 0x7E84788F))) {
        int i = ((Integer)totem_pop_counter.get(entityPlayer.func_70005_c_())).intValue();
        totem_pop_counter.remove(entityPlayer.func_70005_c_());
        if (entityPlayer == mc.field_71439_g)
          continue; 
        if (FriendUtil.isFriend(entityPlayer.func_70005_c_())) {
          MessageUtil.send_client_message("" + reset + green + bold + entityPlayer.func_70005_c_() + reset + " died after popping " + bold + i + reset + " totems");
          if (this.should_notify)
            NotificationUtil.send_notification(new Notification(entityPlayer.func_70005_c_() + " died after popping " + i + " totems")); 
          continue;
        } 
        MessageUtil.send_client_message("" + reset + red + bold + entityPlayer.func_70005_c_() + reset + " died after popping " + bold + i + reset + " totems");
        if (this.should_notify)
          NotificationUtil.send_notification(new Notification(entityPlayer.func_70005_c_() + " died after popping " + i + " totems")); 
      } 
    } 
  }
  
  public void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (paramReceivePacket.get_packet() instanceof SPacketEntityStatus) {
      SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)paramReceivePacket.get_packet();
      if (sPacketEntityStatus.func_149160_c() == 35) {
        Entity entity = sPacketEntityStatus.func_149161_a((World)mc.field_71441_e);
        int i = 1;
        if (totem_pop_counter.containsKey(entity.func_70005_c_())) {
          i = ((Integer)totem_pop_counter.get(entity.func_70005_c_())).intValue();
          totem_pop_counter.put(entity.func_70005_c_(), Integer.valueOf(++i));
        } else {
          totem_pop_counter.put(entity.func_70005_c_(), Integer.valueOf(i));
        } 
        if (entity == mc.field_71439_g)
          return; 
        if (FriendUtil.isFriend(entity.func_70005_c_())) {
          MessageUtil.send_client_message("" + reset + green + bold + entity.func_70005_c_() + reset + " has popped " + bold + i + reset + " totems, help him lmao.");
          if (this.should_notify)
            NotificationUtil.send_notification(new Notification(entity.func_70005_c_() + " has popped " + i + " totems, help him")); 
        } else {
          MessageUtil.send_client_message("" + reset + red + bold + entity.func_70005_c_() + reset + " has popped " + bold + i + reset + " totems, he is so ez.");
          if (this.should_notify)
            NotificationUtil.send_notification(new Notification(entity.func_70005_c_() + " has popped " + i + " totems")); 
        } 
      } 
    } 
  }
  
  static {
    green = ChatFormatting.GREEN;
    bold = ChatFormatting.BOLD;
    reset = ChatFormatting.RESET;
  }
}
