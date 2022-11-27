package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.obsidianbreaker.leux.client.util.EzMessageUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class AutoGG extends Module {
  public Setting pop = create("Pop", "Pop", false);
  
  @EventHandler
  public Listener packet_event = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public static ConcurrentHashMap targeted_players;
  
  @EventHandler
  public Listener send_listener = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public Setting excuse = create("Excuse", "Excuse", false);
  
  @EventHandler
  public Listener living_death_listener = new Listener(this::lambda$new$2, new java.util.function.Predicate[0]);
  
  public Setting ez = create("Ez", "Ez", true);
  
  public int diedTime = 0;
  
  public int delay_count = 0;
  
  public static HashMap totem_pop_counter = new HashMap<>();
  
  public static void lambda$update$3(Object paramObject1, Object paramObject2) {
    (give up)null;
    if (((Integer)paramObject2).intValue() <= 0) {
      targeted_players.remove(paramObject1);
    } else {
      targeted_players.put(paramObject1, Integer.valueOf(((Integer)paramObject2).intValue() - 1));
    } 
  }
  
  public static void add_target(String paramString) {
    (give up)null;
    if (!Objects.equals(paramString, mc.field_71439_g.func_70005_c_()))
      targeted_players.put(paramString, Integer.valueOf(20)); 
  }
  
  public void announce(String paramString) {
    (give up)null;
    if (this.delay_count < 150)
      return; 
    this.delay_count = 0;
    targeted_players.remove(paramString);
    String str = EzMessageUtil.get_message().replace("[", "").replace("]", "");
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketChatMessage(str));
  }
  
  public AutoGG() {
    super(Category.misc);
  }
  
  public void update() {
    (give up)null;
    if (this.pop.get_value(true))
      for (EntityPlayer entityPlayer : mc.field_71441_e.field_73010_i) {
        if (!totem_pop_counter.containsKey(entityPlayer.func_70005_c_()) || (!entityPlayer.field_70128_L && entityPlayer.func_110143_aJ() > Float.intBitsToFloat(2.13202304E9F ^ 0x7F1416D4)))
          continue; 
        int i = ((Integer)totem_pop_counter.get(entityPlayer.func_70005_c_())).intValue();
        totem_pop_counter.remove(entityPlayer.func_70005_c_());
        if (entityPlayer == mc.field_71439_g)
          continue; 
        mc.field_71439_g.func_71165_d(entityPlayer.func_70005_c_() + " died after popping " + i + " totems");
      }  
    if (this.ez.get_value(true)) {
      for (Entity entity : mc.field_71441_e.func_72910_y()) {
        if (entity instanceof EntityPlayer) {
          EntityPlayer entityPlayer = (EntityPlayer)entity;
          if (entityPlayer.func_110143_aJ() <= Float.intBitsToFloat(2.13736832E9F ^ 0x7F65A6FD) && targeted_players.containsKey(entityPlayer.func_70005_c_()))
            announce(entityPlayer.func_70005_c_()); 
        } 
      } 
      targeted_players.forEach(AutoGG::lambda$update$3);
      this.delay_count++;
    } 
    if (this.excuse.get_value(true)) {
      if (this.diedTime > 0)
        this.diedTime--; 
      if (mc.field_71439_g.field_70128_L)
        this.diedTime = 500; 
      if (!mc.field_71439_g.field_70128_L && this.diedTime > 0) {
        Random random = new Random();
        int i = random.nextInt(10) + 1;
        if (i == 1)
          mc.field_71439_g.func_71165_d("U win bc u r a fucking ping player"); 
        if (i == 2)
          mc.field_71439_g.func_71165_d("LMAO luckiest player ever"); 
        if (i == 3)
          mc.field_71439_g.func_71165_d("Bruh, i was testing settings"); 
        if (i == 5)
          mc.field_71439_g.func_71165_d("Fucking pingspikes"); 
        if (i == 6)
          mc.field_71439_g.func_71165_d("Imagine saying ez"); 
        if (i == 7)
          mc.field_71439_g.func_71165_d("I have highping, stop saying ez"); 
        if (i == 8)
          mc.field_71439_g.func_71165_d("I was afk bruhh"); 
        if (i == 9)
          mc.field_71439_g.func_71165_d("Dont say ez, its cringe"); 
        if (i == 10)
          mc.field_71439_g.func_71165_d("Im so lagged :("); 
        this.diedTime = 0;
      } 
    } 
  }
  
  public void lambda$new$2(LivingDeathEvent paramLivingDeathEvent) {
    (give up)null;
    if (this.ez.get_value(true)) {
      if (mc.field_71439_g == null)
        return; 
      EntityLivingBase entityLivingBase = paramLivingDeathEvent.getEntityLiving();
      return;
    } 
  }
  
  static {
    targeted_players = new ConcurrentHashMap<>();
  }
  
  public void lambda$new$1(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (this.ez.get_value(true)) {
      if (mc.field_71439_g == null)
        return; 
      if (paramSendPacket.get_packet() instanceof CPacketUseEntity) {
        CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)paramSendPacket.get_packet();
        if (cPacketUseEntity.func_149565_c().equals(CPacketUseEntity.Action.ATTACK)) {
          Entity entity = cPacketUseEntity.func_149564_a((World)mc.field_71441_e);
          if (entity instanceof EntityPlayer)
            add_target(entity.func_70005_c_()); 
        } 
      } 
    } 
  }
  
  public void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    SPacketEntityStatus sPacketEntityStatus;
    if (this.pop.get_value(true) && paramReceivePacket.get_packet() instanceof SPacketEntityStatus && (sPacketEntityStatus = (SPacketEntityStatus)paramReceivePacket.get_packet()).func_149160_c() == 35) {
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
      mc.field_71439_g.func_71165_d("ez pop " + entity.func_70005_c_() + " " + i + " totems");
    } 
  }
}
