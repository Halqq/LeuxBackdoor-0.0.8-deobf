package me.obsidianbreaker.leux.client.modules.misc;

import give up;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.obsidianbreaker.leux.client.event.events.EventPacket;
import me.obsidianbreaker.leux.client.guiscreen.settings.Setting;
import me.obsidianbreaker.leux.client.modules.Category;
import me.obsidianbreaker.leux.client.modules.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;

public class AutoRacist extends Module {
  public CharSequence nigga = "nigga";
  
  public String[] random_correction = new String[] { "Yuo jstu got nea nae'd by LuxBakdor", "LeuxBackdoor just stopped me from saying something racially incorrect!", "<Insert nword word here>", "Im an edgy teenager and saying the nword makes me feel empowered on the internet.", "My mom calls me a late bloomer", "I really do think im funny", "Niger is a great county, I do say so myself", "Mommy and daddy are wrestling in the bedroom again so im going to play minecraft until their done", "How do you open the impact GUI?", "What time does FitMC do his basehunting livestreams?" };
  
  public Setting anti_nword = create("AntiNword", "AutoRacismAntiNword", false);
  
  public Random r = new Random();
  
  public Setting chanter = create("Chanter", "AutoRacismChanter", true);
  
  public CharSequence nigger = "nigger";
  
  public List chants = new ArrayList();
  
  public Setting delay = create("Delay", "AutoRacistDelay", 6, 0, 100);
  
  @EventHandler
  public Listener listener = new Listener(this::lambda$new$0, new java.util.function.Predicate[0]);
  
  public int tick_delay;
  
  public void lambda$new$0(EventPacket.SendPacket paramSendPacket) {
    (give up)null;
    if (!(paramSendPacket.get_packet() instanceof CPacketChatMessage))
      return; 
    if (this.anti_nword.get_value(true)) {
      String str = ((CPacketChatMessage)paramSendPacket.get_packet()).func_149439_c().toLowerCase();
      if (str.contains(this.nigger) || str.contains(this.nigga)) {
        String str1 = Integer.toString((int)mc.field_71439_g.field_70165_t);
        String str2 = Integer.toString((int)mc.field_71439_g.field_70161_v);
        str1 + " " + str2;
        str = random_string(this.random_correction);
        mc.field_71439_g.field_71174_a.func_147297_a((Packet)new CPacketChatMessage("I am not nigger"));
      } 
      ((CPacketChatMessage)paramSendPacket.get_packet()).field_149440_a = str;
    } 
  }
  
  public void update() {
    (give up)null;
    if (this.chanter.get_value(true)) {
      this.tick_delay++;
      if (this.tick_delay < this.delay.get_value(1) * 10)
        return; 
      String str1 = this.chants.get(this.r.nextInt(this.chants.size()));
      String str2 = get_random_name();
      if (str2.equals(mc.field_71439_g.func_70005_c_()))
        return; 
      mc.field_71439_g.func_71165_d(str1.replace("<player>", str2));
      this.tick_delay = 0;
    } 
  }
  
  public String get_random_name() {
    (give up)null;
    List<EntityPlayer> list = mc.field_71441_e.field_73010_i;
    return ((EntityPlayer)list.get(this.r.nextInt(list.size()))).func_70005_c_();
  }
  
  public void enable() {
    (give up)null;
    this.tick_delay = 0;
    this.chants.add("<player> nigger");
    this.chants.add("Luscius is god");
    this.chants.add("#LEUXMODE");
    this.chants.add("<player>, i have ur stash coords");
    this.chants.add("justice 4 ObsidianBreaker");
    this.chants.add("WarriorCrystal loves spaghetti");
    this.chants.add("stop being gay and join Leux");
    this.chants.add("imagine not being from Leux");
    this.chants.add("<player> join Leux and stop being a nn");
    this.chants.add("<player>, ur password has leaked");
    this.chants.add(":rolf:");
    this.chants.add("<player>, Sazked wants sex with you");
  }
  
  public String random_string(String[] paramArrayOfString) {
    (give up)null;
    return paramArrayOfString[this.r.nextInt(paramArrayOfString.length)];
  }
  
  public AutoRacist() {
    super(Category.misc);
  }
}
