package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.obsidianbreaker.leux.client.Leux;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.Vec3d;

public class Announcer extends Module {
  public static float thisTickHealth;
  
  public static float lastTickHealth;
  
  public static Queue message_q;
  
  public Setting movement_string = create("Movement", "AnnouncerMovement", "FUCK", combobox(new String[] { "Aha x", "Leyta", "FUCK" }));
  
  public static int delay_count;
  
  public static Map consumed_items;
  
  public static float lostHealth;
  
  public static Map dropped_items;
  
  public Setting delay = create("Delay Seconds", "AnnouncerDelay", 4, 0, 20);
  
  public static Map mined_blocks;
  
  public static Vec3d thisTickPos;
  
  public Setting units = create("Units", "AnnouncerUnits", "Meters", combobox(new String[] { "Meters", "Feet", "Yards", "Inches" }));
  
  public static double distanceTraveled;
  
  public static Map placed_blocks;
  
  public Setting queue_size = create("Queue Size", "AnnouncerQueueSize", 5, 1, 20);
  
  public Setting smol = create("Small Text", "AnnouncerSmallText", false);
  
  public static float gainedHealth;
  
  @EventHandler
  public Listener receive_listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public Setting max_distance = create("Max Distance", "AnnouncerMaxDist", 144, 12, 1200);
  
  public Setting min_distance = create("Min Distance", "AnnouncerMinDist", 12, 1, 100);
  
  public static Vec3d lastTickPos;
  
  public boolean first_run;
  
  public Setting suffix = create("Suffix", "AnnouncerSuffix", true);
  
  @EventHandler
  public Listener send_listener = new Listener(this::lambda$new$1, new java.util.function.Predicate[0]);
  
  public static DecimalFormat df = new DecimalFormat();
  
  public Announcer() {
    super(Category.misc);
  }
  
  public static double round(double paramDouble, int paramInt) {
    paramInt = 2;
    (give up)null;
    BigDecimal bigDecimal1 = new BigDecimal(paramDouble);
    BigDecimal bigDecimal2 = bigDecimal1.setScale(paramInt, 4);
    return bigDecimal2.doubleValue();
  }
  
  public void enable() {
    (give up)null;
    this.first_run = true;
    df = new DecimalFormat("#.#");
    df.setRoundingMode(RoundingMode.CEILING);
    Vec3d vec3d = mc.field_71439_g.func_174791_d();
    thisTickPos = vec3d;
    distanceTraveled = 0.0D;
    float f = mc.field_71439_g.func_110143_aJ() + mc.field_71439_g.func_110139_bj();
    thisTickHealth = f;
    lostHealth = Float.intBitsToFloat(2.13116237E9F ^ 0x7F06F529);
    gainedHealth = Float.intBitsToFloat(2.12466803E9F ^ 0x7EA3DC61);
    delay_count = 0;
  }
  
  public void send_cycle() {
    (give up)null;
    delay_count++;
    if (delay_count > this.delay.get_value(1) * 20) {
      delay_count = 0;
      composeGameTickData();
      composeEventData();
      Iterator<String> iterator = message_q.iterator();
      if (iterator.hasNext()) {
        String str = iterator.next();
        send_message(str);
        message_q.remove(str);
      } 
    } 
  }
  
  public void queue_message(String paramString) {
    (give up)null;
    if (message_q.size() > this.queue_size.get_value(1))
      return; 
    message_q.add(paramString);
  }
  
  public void composeEventData() {
    (give up)null;
    for (Map.Entry entry : mined_blocks.entrySet()) {
      queue_message("We be mining " + entry.getValue() + " " + (String)entry.getKey() + " out here");
      mined_blocks.remove(entry.getKey());
    } 
    for (Map.Entry entry : placed_blocks.entrySet()) {
      queue_message("We be placing " + entry.getValue() + " " + (String)entry.getKey() + " out here");
      placed_blocks.remove(entry.getKey());
    } 
    for (Map.Entry entry : dropped_items.entrySet()) {
      queue_message("I just dropped " + entry.getValue() + " " + (String)entry.getKey() + ", whoops!");
      dropped_items.remove(entry.getKey());
    } 
    for (Map.Entry entry : consumed_items.entrySet()) {
      queue_message("NOM NOM, I just ate " + entry.getValue() + " " + (String)entry.getKey() + ", yummy");
      consumed_items.remove(entry.getKey());
    } 
  }
  
  static {
    message_q = new ConcurrentLinkedQueue();
    mined_blocks = new ConcurrentHashMap<>();
    placed_blocks = new ConcurrentHashMap<>();
    dropped_items = new ConcurrentHashMap<>();
    consumed_items = new ConcurrentHashMap<>();
  }
  
  public void lambda$new$0(EventPacket.ReceivePacket paramReceivePacket) {
    (give up)null;
    if (mc.field_71441_e == null)
      return; 
    if (paramReceivePacket.get_packet() instanceof net.minecraft.network.play.server.SPacketUseBed)
      queue_message("I am going to bed now, goodnight"); 
  }
  
  public void lambda$new$1(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (mc.field_71441_e == null)
      return; 
    if (paramSendPacket.get_packet() instanceof CPacketPlayerDigging) {
      CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)paramSendPacket.get_packet();
      if (mc.field_71439_g.func_184614_ca().func_77973_b() != Items.field_190931_a && (cPacketPlayerDigging.func_180762_c().equals(CPacketPlayerDigging.Action.DROP_ITEM) || cPacketPlayerDigging.func_180762_c().equals(CPacketPlayerDigging.Action.DROP_ALL_ITEMS))) {
        String str = mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r();
        if (dropped_items.containsKey(str)) {
          dropped_items.put(str, Integer.valueOf(((Integer)dropped_items.get(str)).intValue() + 1));
        } else {
          dropped_items.put(str, Integer.valueOf(1));
        } 
      } 
      if (cPacketPlayerDigging.func_180762_c().equals(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
        String str = mc.field_71441_e.func_180495_p(cPacketPlayerDigging.func_179715_a()).func_177230_c().func_149732_F();
        if (mined_blocks.containsKey(str)) {
          mined_blocks.put(str, Integer.valueOf(((Integer)mined_blocks.get(str)).intValue() + 1));
        } else {
          mined_blocks.put(str, Integer.valueOf(1));
        } 
      } 
    } else {
      if (paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketUpdateSign)
        queue_message("I just updated a sign with some epic text"); 
      if (paramSendPacket.get_packet() instanceof net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock) {
        ItemStack itemStack = mc.field_71439_g.field_71071_by.func_70448_g();
        if (itemStack.func_190926_b())
          return; 
        if (itemStack.func_77973_b() instanceof net.minecraft.item.ItemBlock) {
          String str = mc.field_71439_g.field_71071_by.func_70448_g().func_82833_r();
          if (placed_blocks.containsKey(str)) {
            placed_blocks.put(str, Integer.valueOf(((Integer)placed_blocks.get(str)).intValue() + 1));
          } else {
            placed_blocks.put(str, Integer.valueOf(1));
          } 
        } else {
          if (itemStack.func_77973_b() == Items.field_185158_cP) {
            String str = "Crystals";
            if (placed_blocks.containsKey(str)) {
              placed_blocks.put(str, Integer.valueOf(((Integer)placed_blocks.get(str)).intValue() + 1));
            } else {
              placed_blocks.put(str, Integer.valueOf(1));
            } 
          } 
          return;
        } 
        return;
      } 
    } 
  }
  
  public void composeGameTickData() {
    (give up)null;
    if (this.first_run) {
      this.first_run = false;
      return;
    } 
    if (distanceTraveled >= 1.0D) {
      if (distanceTraveled < (this.delay.get_value(1) * this.min_distance.get_value(1)))
        return; 
      if (distanceTraveled > (this.delay.get_value(1) * this.max_distance.get_value(1))) {
        distanceTraveled = 0.0D;
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      if (this.movement_string.in("Aha x"))
        stringBuilder.append("aha x, I just traveled "); 
      if (this.movement_string.in("FUCK"))
        stringBuilder.append("FUCK, I just fucking traveled "); 
      if (this.movement_string.in("Leyta"))
        stringBuilder.append("leyta bitch, I just traveled "); 
      if (this.units.in("Feet")) {
        stringBuilder.append(round(distanceTraveled * 3.2808D, 2));
        if ((int)distanceTraveled == 1.0D) {
          stringBuilder.append(" Foot");
        } else {
          stringBuilder.append(" Feet");
        } 
      } 
      if (this.units.in("Yards")) {
        stringBuilder.append(round(distanceTraveled * 1.0936D, 2));
        if ((int)distanceTraveled == 1.0D) {
          stringBuilder.append(" Yard");
        } else {
          stringBuilder.append(" Yards");
        } 
      } 
      if (this.units.in("Inches")) {
        stringBuilder.append(round(distanceTraveled * 39.37D, 2));
        if ((int)distanceTraveled == 1.0D) {
          stringBuilder.append(" Inch");
        } else {
          stringBuilder.append(" Inches");
        } 
      } 
      if (this.units.in("Meters")) {
        stringBuilder.append(round(distanceTraveled, 2));
        if ((int)distanceTraveled == 1.0D) {
          stringBuilder.append(" Meter");
        } else {
          stringBuilder.append(" Meters");
        } 
      } 
      queue_message(stringBuilder.toString());
      distanceTraveled = 0.0D;
    } 
    if (lostHealth != Float.intBitsToFloat(2.13205466E9F ^ 0x7F14926C)) {
      String str = "HECK! I just lost " + df.format(lostHealth) + " health D:";
      queue_message(str);
      lostHealth = Float.intBitsToFloat(2.13052749E9F ^ 0x7EFD44DF);
    } 
    if (gainedHealth != Float.intBitsToFloat(2.12433485E9F ^ 0x7E9EC703)) {
      String str = "#Leuxmode I now have " + df.format(gainedHealth) + " more health";
      queue_message(str);
      gainedHealth = Float.intBitsToFloat(2.12765274E9F ^ 0x7ED16771);
    } 
  }
  
  public void update() {
    (give up)null;
    if (mc.field_71439_g == null || mc.field_71441_e == null) {
      set_disable();
      return;
    } 
    get_tick_data();
    send_cycle();
  }
  
  public void send_message(String paramString) {
    (give up)null;
    if (this.suffix.get_value(true)) {
      String str = " ⚝ ";
      paramString = paramString + str + Leux.smoth("sponsored by leux");
    } 
    if (this.smol.get_value(true))
      paramString = Leux.smoth(paramString.toLowerCase()); 
    mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketChatMessage(paramString.replaceAll("§", "")));
  }
  
  public void get_tick_data() {
    (give up)null;
    lastTickPos = thisTickPos;
    thisTickPos = mc.field_71439_g.func_174791_d();
    distanceTraveled += thisTickPos.func_72438_d(lastTickPos);
    lastTickHealth = thisTickHealth;
    thisTickHealth = mc.field_71439_g.func_110143_aJ() + mc.field_71439_g.func_110139_bj();
    float f = thisTickHealth - lastTickHealth;
    if (f < 0.0F) {
      lostHealth += f * -1.0F;
    } else {
      gainedHealth += f;
    } 
  }
}
